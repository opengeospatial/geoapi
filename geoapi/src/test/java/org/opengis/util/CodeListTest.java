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

import org.opengis.metadata.*;
import org.opengis.metadata.acquisition.*;
import org.opengis.metadata.citation.*;
import org.opengis.metadata.constraint.*;
import org.opengis.metadata.content.*;
import org.opengis.metadata.distribution.*;
import org.opengis.metadata.identification.*;
import org.opengis.metadata.maintenance.*;
import org.opengis.metadata.quality.*;
import org.opengis.metadata.spatial.*;
import org.opengis.referencing.cs.*;
import org.opengis.referencing.datum.*;

import org.junit.*;
import static org.junit.Assert.*;


/**
 * Tests every {@link CodeList}.
 *
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 * @since   2.0
 */
public final class CodeListTest {
    /**
     * All code lists to be tested.
     */
    private static final Class<?>[] CODE_LISTS = {
        AssociationType          .class,
        AxisDirection            .class,
        BandDefinition           .class,
        CellGeometry             .class,
        CharacterSet             .class,
        Classification           .class,
        Context                  .class,
        CoverageContentType      .class,
        Datatype                 .class,
        DateType                 .class,
        DimensionNameType        .class,
        EvaluationMethodType     .class,
        GeometricObjectType      .class,
        GeometryType             .class,
        ImagingCondition         .class,
        InitiativeType           .class,
        KeywordType              .class,
        MaintenanceFrequency     .class,
        MediumFormat             .class,
        MediumName               .class,
        ObjectiveType            .class,
        Obligation               .class,
        OnLineFunction           .class,
        OperationType            .class,
        PixelInCell              .class,
        PixelOrientation         .class,
        PolarizationOrientation  .class,
        PresentationForm         .class,
        Priority                 .class,
        Progress                 .class,
        RangeMeaning             .class,
        Restriction              .class,
        Role                     .class,
        ScopeCode                .class,
        Sequence                 .class,
        SpatialRepresentationType.class,
        TopicCategory            .class,
        TopologyLevel            .class,
        TransferFunctionType     .class,
        Trigger                  .class,
        VerticalDatumType        .class
    };

    /**
     * Tests every code lists. This method ensures that the a {@code values()} and {@code family()}
     * method is defined for each code list, and verify each declared code lists.
     */
    @Test
    public void testAll() {
        for (final Class<?> codeClass : CODE_LISTS) {
            /*
             * Gets the values() method, which should public and static.
             * Then gets every CodeList instances returned by values().
             */
            final String className = codeClass.getCanonicalName();
            final Method valuesMethod;
            try {
                valuesMethod = codeClass.getMethod("values", (Class<?>[]) null);
            } catch (NoSuchMethodException e) {
                fail("Missing " + className + ".values() method: " + e);
                return;
            }
            assertTrue(className + ".values() is not public.", Modifier.isPublic(valuesMethod.getModifiers()));
            assertTrue(className + ".values() is not static.", Modifier.isStatic(valuesMethod.getModifiers()));
            final CodeList<?>[] values;
            try {
                values = (CodeList<?>[]) valuesMethod.invoke(null, (Object[]) null);
            } catch (IllegalAccessException e) {
                fail(className + ".values() is not accessible: " + e);
                return;
            } catch (InvocationTargetException e) {
                fail("Can not invoke " + className + ".values(): " + e.getTargetException());
                return;
            }
            assertNotNull(className + ".values() returned null.", values);
            /*
             * Tests every CodeList instances returned by values().
             * Every field should be public, static and final.
             */
            for (final CodeList<?> value : values) {
                final String valueName = value.name();
                final String fullName  = className + '.' + valueName;
                assertTrue(fullName + " is of unexpected type.", codeClass.isInstance(value));
                final Field field;
                try {
                    field = codeClass.getField(valueName);
                } catch (NoSuchFieldException e) {
                    fail(fullName + " field not found: " + e);
                    return;
                }
                final int modifiers = field.getModifiers();
                assertTrue  (fullName + " is not public.", Modifier.isPublic(modifiers));
                assertTrue  (fullName + " is not static.", Modifier.isStatic(modifiers));
                assertTrue  (fullName + " is not final.",  Modifier.isFinal (modifiers));
                assertEquals(fullName + " name mismatch.", valueName, field.getName());
                final Object constant;
                try {
                    constant = field.get(null);
                } catch (IllegalAccessException e) {
                    fail(fullName + " is not accessible: " + e);
                    continue;
                }
                assertSame(fullName + " is not the expected instance.", value, constant);
                assertArrayEquals(className + ".family() mismatch.", values, value.family());
            }
            /*
             * Gets the private VALUES field only if CodeList is the direct parent.
             */
            if (codeClass.getSuperclass().equals(CodeList.class)) {
                final String arrayName = className + ".VALUES";
                final Field field;
                try {
                    field = codeClass.getDeclaredField("VALUES");
                } catch (NoSuchFieldException e) {
                    fail(arrayName + " private list is missing: " + e);
                    return;
                }
                final int modifiers = field.getModifiers();
                assertTrue (arrayName + " is not static.", Modifier.isStatic   (modifiers));
                assertTrue (arrayName + " is not final.",  Modifier.isFinal    (modifiers));
                assertFalse(arrayName + " is protected.",  Modifier.isProtected(modifiers));
                assertFalse(arrayName + " is public.",     Modifier.isPublic   (modifiers));
                field.setAccessible(true);
                final ArrayList<?> asList;
                try {
                    final Object candidate = field.get(null);
                    assertEquals(arrayName + " is not an ArrayList.", ArrayList.class, candidate.getClass());
                    asList = (ArrayList<?>) candidate;
                } catch (IllegalAccessException e) {
                    fail(arrayName + " is not accessible: " + e);
                    return;
                }
                assertArrayEquals(arrayName + " content does not match values().", values, asList.toArray());
                /*
                 * Verifies if the VALUES ArrayList size was properly sized. We need to access to
                 * private ArrayList.elementData field in order to perform this check.  Tested on
                 * Sun's JSE 6.0. It is not mandatory to have the VALUES list properly dimensioned;
                 * it just avoid a little bit of memory reallocation at application startup time.
                 */
                final int capacity;
                try {
                    final Field candidate = ArrayList.class.getDeclaredField("elementData");
                    candidate.setAccessible(true);
                    final Object array = candidate.get(asList);
                    capacity = ((Object[]) array).length;
                } catch (Exception e) {
                    // Not a fatal error since it is implementation-specific.
                    System.err.println("Warning: " + e);
                    continue;
                }
                assertEquals(arrayName + " not properly sized.", asList.size(), capacity);
            }
            /*
             * Tries to create a new element.
             */
            final Method valueOfMethod;
            try {
                valueOfMethod = codeClass.getMethod("valueOf", String.class);
            } catch (NoSuchMethodException e) {
                fail(className + ".valueOf(String) not found: " + e);
                return;
            }
            final CodeList<?> value;
            try {
                value = (CodeList<?>) valueOfMethod.invoke(null, "MyNewCode");
            } catch (IllegalAccessException e) {
                fail(className + ".valueOf(String) is not accessible: " + e);
                return;
            } catch (InvocationTargetException e) {
                fail("Failed to invoke " + className + ".valueOf(String): " + e.getTargetException());
                return;
            }
            assertTrue(className + ".valueOf(String) did not created an instance of the expected class.", codeClass.isInstance(value));
            assertEquals("Newly created CodeList does not have the expected name.", "MyNewCode", value.name());
        }
    }

    /**
     * Tests the {@link CharacterSet} code list. At the difference of other code lists,
     * its {@link CodeList#names()} method is overridden.
     */
    @Test
    public void testCharacterSet() {
        final CodeList<CharacterSet> code = CharacterSet.UTF_8;
        assertEquals ("UTF_8", code.name());
        assertEquals ("utf8",  code.identifier());
        assertSame   (code, CharacterSet.valueOf("UTF_8"));
        assertNotSame(code, CharacterSet.valueOf("UTF_7"));
    }
}
