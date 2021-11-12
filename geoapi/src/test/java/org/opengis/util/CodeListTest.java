/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2007-2011 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.opengis.metadata.identification.CharacterSet;

import org.junit.*;
import static org.junit.Assert.*;


/**
 * Tests every {@link CodeList}.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0.2
 * @since   2.0
 */
public final class CodeListTest {
    /**
     * The logger to use.
     */
    private static final Logger LOGGER = Logger.getLogger("org.opengis");

    /**
     * Tests the {@link CharacterSet} code list. At the difference of other code lists,
     * its {@link CodeList#matches} method is overridden.
     */
    @Test
    public void testCharacterSet() {
        final CodeList<CharacterSet> code = CharacterSet.UTF_8;
        assertEquals ("UTF_8", code.name());
        assertEquals ("utf8",  code.identifier());
        assertSame   (code, CharacterSet.valueOf("UTF_8"));
        assertNotSame(code, CharacterSet.valueOf("UTF_7"));
    }

    /**
     * Tests the instantiation of every code lists.
     */
    @Test
    public void testAll() {
        int count = 0;
        @SuppressWarnings("rawtypes")
        final Class<CodeList> base = CodeList.class;
        final ClassScanner scanner = new ClassScanner();
        while (scanner.hasNext()) {
            final Class<?> candidate = scanner.next();
            if (!base.equals(candidate) && base.isAssignableFrom(candidate)) {
                // SimpleEnumeratioType is a special case to avoid for now.
                final String name = candidate.getCanonicalName();
                if (name.equals("org.opengis.util.SimpleEnumerationType")) {
                    continue;
                }
                if (name.equals("org.opengis.filter.sort.SortOrder")) {
                    continue;
                }
                @SuppressWarnings({"unchecked","rawtypes"})
                final Class<? extends CodeList<?>> codeType = (Class) candidate.asSubclass(CodeList.class);
                assertValid(codeType);
                count++;
            }
        }
        LOGGER.log(Level.FINE, "Found {0} code lists.", count);
        if (count == 0) {
            LOGGER.warning("No CodeList found.");
        }
    }

    /**
     * Ensures that the name declared in the code list match the field names.
     */
    private static void assertValid(final Class<? extends CodeList<?>> classe) {
        Method method;
        int modifiers;
        String fullName;
        /*
         * Gets the values() method, which should public and static.
         * Then gets every CodeList instances returned by values().
         */
        final String className = classe.getCanonicalName();
        fullName = className + ".values()";
        try {
            method = classe.getMethod("values", (Class<?>[]) null);
        } catch (NoSuchMethodException e) {
            fail(fullName + " method is missing.");
            return;
        }
        assertNotNull(method);
        modifiers = method.getModifiers();
        assertTrue(fullName + " is not public.", Modifier.isPublic(modifiers));
        assertTrue(fullName + " is not static.", Modifier.isStatic(modifiers));
        final CodeList<?>[] values;
        try {
            values = (CodeList<?>[]) method.invoke(null, (Object[]) null);
        } catch (IllegalAccessException e) {
            fail(fullName + " is not accessible.");
            return;
        } catch (InvocationTargetException e) {
            fail("Call to " + fullName + " failed.\n" + e.getTargetException());
            return;
        }
        assertNotNull(fullName + " returned null.", values);
        /*
         * Gets the family() method, to be used when we will test every
         * code list instances.
         */
        fullName = className + ".family()";
        try {
            method = classe.getMethod("family", (Class<?>[]) null);
        } catch (NoSuchMethodException e) {
            fail(fullName + " method is missing.");
            return;
        }
        assertNotNull(method);
        modifiers = method.getModifiers();
        assertTrue (fullName + " is not public.", Modifier.isPublic(modifiers));
        assertFalse(fullName + " is static.",     Modifier.isStatic(modifiers));
        /*
         * Tests every CodeList instances returned by values().
         * Every field should be public, static and final.
         */
        for (final CodeList<?> value : values) {
            final String name = value.name();
            fullName = className + '.' + name;
            assertTrue(fullName + ": unexpected type.", classe.isInstance(value));
            final Field field;
            try {
                field = classe.getField(name);
            } catch (NoSuchFieldException e) {
                @SuppressWarnings("rawtypes")
                final Class<? extends CodeList> valueClass = value.getClass();
                if (!classe.equals(valueClass) && classe.isAssignableFrom(valueClass)) {
                    // Do not fails if valueClass is a subclass of classe.
                    continue;
                }
                fail(fullName + " field not found.");
                continue;
            }
            assertNotNull(field);
            modifiers = field.getModifiers();
            assertEquals(fullName + ": unexpected name mismatch.", name, field.getName());
            assertTrue  (fullName + " is not public.", Modifier.isPublic(modifiers));
            assertTrue  (fullName + " is not static.", Modifier.isStatic(modifiers));
            assertTrue  (fullName + " is not final.",  Modifier.isFinal (modifiers));
            Object constant;
            try {
                constant = field.get(null);
            } catch (IllegalAccessException e) {
                fail(fullName + " is not accessible.");
                continue;
            }
            assertSame(fullName + " is not the expected instance.", value, constant);
            final CodeList<?>[] family;
            try {
                family = (CodeList<?>[]) method.invoke(constant, (Object[]) null);
            } catch (IllegalAccessException e) {
                fail(className + ".family() is not accessible.");
                return;
            } catch (InvocationTargetException e) {
                fail("Call to " + className + ".family() failed.\n" + e.getTargetException());
                return;
            }
            assertTrue(className + ".family() mismatch.", Arrays.equals(values, family));
        }
        /*
         * Gets the private VALUES field only if CodeList is the direct parent.
         */
        if (classe.getSuperclass().equals(CodeList.class)) {
            fullName = className + ".VALUES";
            final Field field;
            try {
                field = classe.getDeclaredField("VALUES");
            } catch (NoSuchFieldException e) {
                fail(fullName + " private list is missing.");
                return;
            }
            modifiers = field.getModifiers();
            assertTrue (Modifier.isStatic   (modifiers));
            assertTrue (Modifier.isFinal    (modifiers));
            assertFalse(Modifier.isPublic   (modifiers));
            assertFalse(Modifier.isProtected(modifiers));
            field.setAccessible(true);
            final ArrayList<?> asList;
            try {
                final Object candidate = field.get(null);
                assertEquals(fullName + " is not an ArrayList.", ArrayList.class, candidate.getClass());
                asList = (ArrayList<?>) candidate;
            } catch (IllegalAccessException e) {
                fail(className + ".VALUES is not accessible.");
                return;
            }
            assertEquals(Arrays.asList(values), asList);
        }
        /*
         * Tries to create a new element.
         */
        try {
            method = classe.getMethod("valueOf", String.class);
        } catch (NoSuchMethodException e) {
            return;
        }
        final CodeList<?> value;
        try {
            value = classe.cast(method.invoke(null, "Dummy"));
        } catch (IllegalAccessException e) {
            fail(e.toString());
            return;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            fail(e.getTargetException().toString());
            return;
        }
        assertEquals("Dummy", value.name());
    }
}
