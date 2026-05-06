/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2024 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.opengis.geoapi;

import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.opengis.util.CodeList;
import org.opengis.util.ControlledVocabulary;
import org.opengis.metadata.constraint.Restriction;
import org.opengis.metadata.quality.ValueStructure;
import org.opengis.metadata.identification.CharacterSet;
import org.opengis.annotation.UML;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests every {@link CodeList} types and (opportunistically) some enumerations.
 *
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 * @since   2.0
 */
public final class CodeListTest {
    /**
     * Suffix of code list values created by other tests.
     * Those values need to be ignored by {@code CodeListTest}.
     * We use the {@value} prefix in their name for identifying them.
     */
    public static final String IGNORABLE_NAME_SUFFIX = "Test";

    /**
     * Creates a new test case.
     */
    public CodeListTest() {
    }

    /**
     * Tests the common methods in every code lists. This method ensures that the a {@code values()} and
     * {@code family()} methods are defined for each code list, and verifies each declared code lists.
     *
     * @throws NoSuchFieldException      if a {@code CodeList} or an {@code Enum} constant cannot be found.
     * @throws NoSuchMethodException     if a {@code values()} or {@code valueOf(String)} method is not found.
     * @throws IllegalAccessException    if a {@code values()} or {@code valueOf(String)} method is not public.
     * @throws InvocationTargetException if an error occurred while invoking {@code values()} or {@code valueOf(String)}.
     */
    @Test
    public void testAll() throws NoSuchFieldException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException
    {
        for (final Class<?> codeClass : Content.CONTROLLED_VOCABULARY.types()) {
            final String className = codeClass.getCanonicalName();
            assertTrue(Modifier.isFinal(codeClass.getModifiers()), () -> className + " should be final.");
            /*
             * Gets the values() method, which should public and static.
             * Then gets every CodeList instances returned by values().
             */
            final Method valuesMethod = codeClass.getMethod("values", (Class<?>[]) null);
            assertTrue(Modifier.isPublic(valuesMethod.getModifiers()), () -> className + ".values() is not public.");
            assertTrue(Modifier.isStatic(valuesMethod.getModifiers()), () -> className + ".values() is not static.");
            final ControlledVocabulary[] values = assertInstanceOf(ControlledVocabulary[].class, valuesMethod.invoke(null, (Object[]) null));
            assertNotNull(values, () -> className + ".values() returned null.");
            int ignored = 0;
            /*
             * Tests every CodeList instances returned by values().
             * Every field should be public, static and final.
             * We allow field to be non-public if not declared by an OGC/ISO standard.
             */
            for (int ordinal = 0; ordinal < values.length; ordinal++) {
                final ControlledVocabulary value = values[ordinal];
                final String valueName = value.name();
                final String fullName  = className + '.' + valueName;
                assertTrue(codeClass.isInstance(value), () -> fullName + " is of unexpected type.");
                if (valueName.endsWith(IGNORABLE_NAME_SUFFIX)) {
                    ignored++;
                    continue;       // Skip values created in other tests.
                }
                final Field field = codeClass.getDeclaredField(valueName);
                final int modifiers = field.getModifiers();
                if (field.isAnnotationPresent(UML.class)) {
                    assertTrue(Modifier.isPublic(modifiers), () -> fullName + " is not public.");
                } else {
                    field.setAccessible(true);
                }
                assertTrue  (Modifier.isStatic(modifiers), () -> fullName  + " is not static.");
                assertTrue  (Modifier.isFinal (modifiers), () -> fullName  + " is not final.");
                assertEquals(valueName, field.getName(),   () -> fullName  + " name mismatch.");
                assertSame  (value,     field.get(null),   () -> fullName  + " is not the expected instance.");
                assertArrayEquals(values, value.family(),  () -> className + ".family() mismatch.");
                /*
                 * Verify ordinal, names and identifier.
                 */
                assertEquals(ordinal, value.ordinal(), () -> fullName + " ordinal mismatch.");
                final Set<String> names = Set.of(value.names());        // NullPointerException if a name is null.
                assertTrue(names.contains(valueName), () -> fullName  + ".names() is missing the name.");
            }
            /*
             * Verify that all code list values declared as static fields are present in `values()`.
             * Conversely, verify again that all values are declared as static constants.
             */
            final var remaining = new HashSet<>(Arrays.asList(values));
            for (final Field field : codeClass.getDeclaredFields()) {
                if (Modifier.isStatic(field.getModifiers())) {
                    field.setAccessible(true);
                    final Object value = field.get(null);
                    if (codeClass.isInstance(value)) {
                        final var code = (ControlledVocabulary) value;
                        if (field.getName().equals(code.name())) {  // For excluding alias such as "LICENSE".
                            assertTrue(remaining.remove(code), () -> code + " not found in values() array.");
                        }
                    }
                }
            }
            remaining.removeIf((code) -> code.name().endsWith(IGNORABLE_NAME_SUFFIX));
            assertTrue(remaining.isEmpty(), () -> "No constants for " + remaining);
            /*
             * Try to create a new code list element with `valueOf(String)`.
             */
            if (CodeList.class.isAssignableFrom(codeClass)) {
                if (codeClass != ValueStructure.class) try {
                    final Method valueOfMethod = codeClass.getMethod("valueOf", String.class);
                    for (final ControlledVocabulary value : values) {
                        assertSame(value, valueOfMethod.invoke(null, value.name()));
                    }
                    /*
                     * Tries to create a new code list element.
                     */
                    final CodeList<?> value = assertInstanceOf(CodeList.class, valueOfMethod.invoke(null, "MyNewCode"));
                    assertInstanceOf(codeClass, value, () -> className + ".valueOf(String) did not created an instance of the expected class.");
                    assertEquals("MyNewCode", value.name(), "Newly created CodeList does not have the expected name.");
                } catch (NoSuchMethodException e) {
                    fail("valueOf method not found for " + className, e);
                }
            }
        }
    }

    /**
     * Tests the {@link CharacterSet} code list. At the difference of other code lists,
     * its {@link CodeList#names()} method is overridden.
     */
    @Test
    @SuppressWarnings("deprecation")
    public void testCharacterSet() {
        final CodeList<CharacterSet> code = CharacterSet.UTF_8;
        assertEquals ("UTF_8", code.name());
        assertEquals ("utf8",  code.identifier());
        assertSame   (code, CharacterSet.valueOf("UTF_8"));
        assertNotSame(code, CharacterSet.valueOf("UTF_7"));
    }

    /**
     * Tests the {@link Restriction} code list. At the difference of other code lists,
     * its {@link CodeList#names()} method is overridden.
     */
    @Test
    public void testRestriction() {
        assertArrayEquals(new String[] {"COPYRIGHT", "copyright"}, Restriction.COPYRIGHT.names());
        assertArrayEquals(new String[] {"LICENCE", "LICENSE", "licence", "license"}, Restriction.LICENCE.names());
        assertSame(Restriction.COPYRIGHT, Restriction.valueOf("COPYRIGHT"));
        assertSame(Restriction.LICENCE,   Restriction.valueOf("LICENCE"));
        assertSame(Restriction.LICENCE,   Restriction.valueOf("LICENSE"));
    }
}
