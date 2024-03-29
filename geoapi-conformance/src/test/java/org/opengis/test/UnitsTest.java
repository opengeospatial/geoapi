/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2011-2024 Open Geospatial Consortium, Inc.
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
package org.opengis.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests {@link Units}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.0.1
 * @since   3.0.1
 */
public final class UnitsTest {
    /**
     * Creates a new test case.
     */
    public UnitsTest() {
    }

    /**
     * Verifies the symbol of base units.
     * We do not verify derived units like kilometres.
     * Part of the purpose of this test is to ensure that we got the international system (SI).
     */
    @Test
    public void verifyBaseUnitSymbol() {
        final Units units = Units.getDefault();
        assertEquals("m", units.metre().getSymbol());
        assertEquals("s", units.second().getSymbol());
    }

    /**
     * Verifies that conversions between units defined in the {@link Units} class give expected result.
     */
    @Test
    public void testUnitConversions() {
        final Units units = Units.getDefault();
        assertEquals(    500, units.kilometre().getConverterTo(units.metre()) .convert(0.5),        "0.5 km");
        assertEquals(6*60*60, units.day()      .getConverterTo(units.second()).convert(0.25),       "0.25 day");
        assertEquals( 300E-6, units.ppm()      .getConverterTo(units.one())   .convert(300), 1E-12, "300 ppm");
        assertEquals(     90, units.grad()     .getConverterTo(units.degree()).convert(100), 1E-12, "100 grad");
        assertEquals(Math.toRadians(30),  units.degree()   .getConverterTo(units.radian()).convert(30),  1E-12);
    }
}
