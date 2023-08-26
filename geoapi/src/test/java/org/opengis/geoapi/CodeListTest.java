/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2007-2023 Open Geospatial Consortium, Inc.
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

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import org.opengis.util.CodeList;
import org.opengis.util.ControlledVocabulary;
import org.opengis.metadata.constraint.Restriction;
import org.opengis.metadata.identification.CharacterSet;
import org.opengis.annotation.UML;
import org.junit.Test;

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
     * The root directory of Java source code, or {@code null} if unspecified.
     */
    private String sourceDirectory;

    /**
     * Creates a new test case.
     */
    public CodeListTest() {
    }

    /**
     * Parse the Java source code of a {@code CodeList} implementation in order to find the initial capacity.
     * This method search for the numerical argument of the first {@code new ArrayList} instruction found in
     * the source code. Having an accurate initial capacity is not mandatory, but avoid a little bit of memory
     * reallocation at application startup time.
     *
     * @param  codeList  the code list for which to verify the initial capacity.
     * @return the declared initial capacity.
     * @throws IOException if an error occurred while reading the source file.
     * @throws NumberFormatException if the initial capacity cannot be parsed.
     */
    private int getDeclaredCapacity(final Class<?> codeList) throws IOException {
        String line;
        int start;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(
                new File(sourceDirectory, codeList.getName().replace('.', '/').concat(".java"))), "UTF-8")))
        {
            do {
                line = in.readLine();
                if (line == null) {
                    throw new EOFException();
                }
                start = line.indexOf("new ArrayList");
            } while (start < 0);
        }
        start = line.indexOf('(', start);
        if (start >= 0) {
            final int end = line.indexOf(')', ++start);
            if (end >= 0) {
                return Integer.parseInt(line.substring(start, end));
            }
        }
        throw new NumberFormatException("Cannot parse ArrayList initial capacity in following Java code line:\n" + line);
    }

    /**
     * Tests the common methods in every code lists. This method ensures that the a {@code values()} and {@code family()}
     * methods are defined for each code list, and verifies each declared code lists.
     *
     * @throws NoSuchFieldException      if a {@code CodeList} or an {@code Enum} constant cannot be found.
     * @throws NoSuchMethodException     if a {@code values()} or {@code valueOf(String)} method is not found.
     * @throws IllegalAccessException    if a {@code values()} or {@code valueOf(String)} method is not public.
     * @throws InvocationTargetException if an error occurred while invoking {@code values()} or {@code valueOf(String)}.
     * @throws IOException               if an error occurred while reading source code.
     */
    @Test
    public void testAll() throws NoSuchFieldException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, IOException
    {
        sourceDirectory = System.getProperty("maven.source.directory");
        for (final Class<?> codeClass : Content.CONTROLLED_VOCABULARY.types()) {
            /*
             * Gets the values() method, which should public and static.
             * Then gets every CodeList instances returned by values().
             */
            final String className = codeClass.getCanonicalName();
            final Method valuesMethod = codeClass.getMethod("values", (Class<?>[]) null);
            assertTrue(Modifier.isPublic(valuesMethod.getModifiers()), () -> className + ".values() is not public.");
            assertTrue(Modifier.isStatic(valuesMethod.getModifiers()), () -> className + ".values() is not static.");
            final ControlledVocabulary[] values = (ControlledVocabulary[]) valuesMethod.invoke(null, (Object[]) null);
            assertNotNull(values, () -> className + ".values() returned null.");
            /*
             * Tests every CodeList instances returned by values().
             * Every field should be public, static and final.
             * We allow field to be non-public if not declared by an OGC/ISO standard.
             */
            for (final ControlledVocabulary value : values) {
                final String valueName = value.name();
                final String fullName  = className + '.' + valueName;
                assertTrue(codeClass.isInstance(value), () -> fullName + " is of unexpected type.");
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
            }
            /*
             * Gets the private VALUES field only if CodeList is the direct parent.
             */
            if (codeClass.getSuperclass().equals(CodeList.class)) {
                final String arrayName = className + ".VALUES";
                final Field field = codeClass.getDeclaredField("VALUES");
                final int modifiers = field.getModifiers();
                assertTrue (Modifier.isStatic   (modifiers), () -> arrayName + " is not static.");
                assertTrue (Modifier.isFinal    (modifiers), () -> arrayName + " is not final.");
                assertFalse(Modifier.isProtected(modifiers), () -> arrayName + " is protected.");
                assertFalse(Modifier.isPublic   (modifiers), () -> arrayName + " is public.");
                field.setAccessible(true);
                final ArrayList<?> asList;
                try {
                    final Object candidate = field.get(null);
                    assertEquals(ArrayList.class, candidate.getClass(), () -> arrayName + " is not an ArrayList.");
                    asList = (ArrayList<?>) candidate;
                } catch (IllegalAccessException e) {
                    fail(arrayName + " is not accessible: " + e);
                    return;
                }
                assertArrayEquals(values, asList.toArray(), () -> arrayName + " content does not match values().");
                /*
                 * Verifies if the ArrayList initial capacity match the actual list size.
                 * It is not mandatory to have an accurate initial capacity, but it avoid
                 * a little bit of memory reallocation at application startup time.
                 */
                if (sourceDirectory != null) {
                    assertEquals(asList.size(), getDeclaredCapacity(codeClass),
                                 () -> arrayName + " not properly sized.");
                }
            }
            /*
             * Tests valueOf(String).
             */
            final Method valueOfMethod = codeClass.getMethod("valueOf", String.class);
            for (final ControlledVocabulary value : values) {
                assertSame(value, valueOfMethod.invoke(null, value.name()));
            }
            /*
             * Tries to create a new code list element.
             */
            if (CodeList.class.isAssignableFrom(codeClass)) {
                final CodeList<?> value = (CodeList<?>) valueOfMethod.invoke(null, "MyNewCode");
                assertTrue(codeClass.isInstance(value),
                           () -> className + ".valueOf(String) did not created an instance of the expected class.");
                assertEquals("MyNewCode", value.name(),
                             "Newly created CodeList does not have the expected name.");
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
