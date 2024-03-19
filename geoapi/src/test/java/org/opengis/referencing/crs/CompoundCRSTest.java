/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2023-2024 Open Geospatial Consortium, Inc.
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
package org.opengis.referencing.crs;

import java.util.List;
import org.opengis.referencing.cs.CoordinateSystem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests {@link CompoundCRS} default methods.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public final class CompoundCRSTest {
    /**
     * Creates a new test case.
     */
    public CompoundCRSTest() {
    }

    /**
     * Tests {@link CompoundCRS#getCoordinateSystem()}.
     */
    @Test
    public void testCoordinateSystem() {
        final CompoundCRS crs = new CompoundCRSMock(
                new CoordinateSystemMock(0, 2, 0),
                new CoordinateSystemMock(1, 1, 2),
                new CoordinateSystemMock(2, 2, 3));

        final CoordinateSystem cs = crs.getCoordinateSystem();
        assertTrue(cs.getName().getCode().startsWith("Coordinate system of " + CompoundCRSMock.NAME));
        verifyAxes(cs, "x0:0", "x0:1", "x1:2", "x2:3", "x2:4");

        String message = assertThrows(IndexOutOfBoundsException.class, () -> cs.getAxis(6)).getMessage();
        assertTrue(message.contains("6"));

        message = assertThrows(IndexOutOfBoundsException.class, () -> cs.getAxis(-1)).getMessage();
        assertTrue(message.contains("-1"));
    }

    /**
     * Tests {@link CompoundCRS#getSingleComponents()}.
     */
    @Test
    public void testGetSingleComponents() {
        final CompoundCRS crs = new CompoundCRSMock(
                new CoordinateSystemMock(0, 2, 0),
                new CompoundCRSMock(
                    new CoordinateSystemMock(1, 1, 2),
                    new CoordinateSystemMock(2, 2, 3)));

        List<SingleCRS> components = crs.getSingleComponents();
        assertEquals(3, components.size());
        verifyAxes(components.get(0).getCoordinateSystem(), "x0:0", "x0:1");
        verifyAxes(components.get(1).getCoordinateSystem(), "x1:2");
        verifyAxes(components.get(2).getCoordinateSystem(), "x2:3", "x2:4");
    }

    /**
     * Verifies that the given coordinate system has the expected axes.
     *
     * @param cs    the coordinate system to validate.
     * @param axes  the expected coordinate system axes (identified by abbreviations).
     */
    private static void verifyAxes(final CoordinateSystem cs, final String... axes) {
        assertEquals(axes.length, cs.getDimension());
        for (int i=0; i<axes.length; i++) {
            assertEquals(axes[i], cs.getAxis(i).getAbbreviation());
        }
    }
}
