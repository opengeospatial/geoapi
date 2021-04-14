/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2012-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.test.referencing;

import java.util.Arrays;
import java.util.ArrayList;
import org.opengis.referencing.cs.AxisDirection;
import org.junit.*;

import static org.junit.Assert.*;
import static org.opengis.referencing.cs.AxisDirection.*;


/**
 * Tests {@link CSValidator}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   2.2
 */
public strictfp class ValidatorTest {
    /**
     * Tests the content of the {@link CSValidator#ORIENTATIONS} array.
     * We expect orientations in the [0…360]° range.
     */
    @Test
    public void testOrientations() {
        final AxisDirection[] directions = new AxisDirection[] {
            NORTH, NORTH_NORTH_EAST, NORTH_EAST, EAST_NORTH_EAST,
            EAST,  EAST_SOUTH_EAST,  SOUTH_EAST, SOUTH_SOUTH_EAST,
            SOUTH, SOUTH_SOUTH_WEST, SOUTH_WEST, WEST_SOUTH_WEST,
            WEST,  WEST_NORTH_WEST,  NORTH_WEST, NORTH_NORTH_WEST,
            ROW_NEGATIVE, COLUMN_POSITIVE, ROW_POSITIVE, COLUMN_NEGATIVE,
            DISPLAY_UP, DISPLAY_RIGHT, DISPLAY_DOWN, DISPLAY_LEFT
        };
        String type  = "geographic";
        int    value = 0;
        int    step  = 1;
        for (final AxisDirection direction : directions) {
            final CSValidator.Orientation orientation = CSValidator.ORIENTATIONS[direction.ordinal()];
            assertNotNull(direction.toString(), orientation);
            if (!orientation.category.equals(type)) {
                assertEquals("Expected orientations in the [0…360]° range.", 16, value);
                type  = orientation.category;
                value = 0;
                step  = 4;
            }
            assertEquals(direction.toString(), value, orientation.orientation);
            value += step;
        }
        assertEquals("Expected orientations in the [0…360]° range.", 16, value);
    }

    /**
     * Tests the {@link CSValidator#assertPerpendicularAxes(Iterable)} method.
     */
    @Test
    public void testAssertPerpendicularAxes() {
        final ArrayList<AxisDirection> directions = new ArrayList<>(Arrays.asList(
                NORTH, DISPLAY_DOWN, OTHER, WEST, DISPLAY_RIGHT, FUTURE));
        CSValidator.assertPerpendicularAxes(directions);
        assertTrue("Collection is cleaned as a side effect of internal working.", directions.isEmpty());
        directions.addAll(Arrays.asList(NORTH, DISPLAY_DOWN, OTHER, SOUTH_EAST, DISPLAY_RIGHT, FUTURE));
        try {
            CSValidator.assertPerpendicularAxes(directions);
            fail("Should have detected the non-perpendicular axes.");
        } catch (AssertionError e) {
            assertEquals("Found an angle of 135.0° between axis directions NORTH and SOUTH_EAST.", e.getMessage());
        }
    }

    /**
     * Tests the {@link CRSValidator#toLowerCase(String)} method.
     */
    @Test
    public void testLowerCase() {
        assertEquals("geodetic latitude", CRSValidator.toLowerCase("Geodetic latitude"));
        assertEquals("geocentric X", CRSValidator.toLowerCase("Geocentric X"));
    }
}
