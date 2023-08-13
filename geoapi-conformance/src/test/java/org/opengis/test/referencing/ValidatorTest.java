/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2012-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.test.referencing;

import java.util.List;
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
public class ValidatorTest {
    /**
     * Creates a new test case.
     */
    public ValidatorTest() {
    }

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
        final var directions = new ArrayList<>(List.of(
                NORTH, DISPLAY_DOWN, OTHER, WEST, DISPLAY_RIGHT, FUTURE));
        CSValidator.assertPerpendicularAxes(directions);
        assertTrue("Collection is cleaned as a side effect of internal working.", directions.isEmpty());
        directions.addAll(List.of(NORTH, DISPLAY_DOWN, OTHER, SOUTH_EAST, DISPLAY_RIGHT, FUTURE));
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
