/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2011 Open Geospatial Consortium, Inc.
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

import org.junit.*;
import static org.junit.Assert.*;


/**
 * Tests {@link Units}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.0.1
 * @since   3.0.1
 */
public class UnitsTest {
    /**
     * Verifies the symbol of base units.
     * We do not verify derived units like kilometres.
     * Part of the purpose of this test is to ensure that we got the international system (SI).
     */
    @Test
    public void verifyBaseUnitSymbol() {
        final Units units = Units.getDefault();
        assertEquals("metre",  "m", units.metre().getSymbol());
        assertEquals("second", "s", units.second().getSymbol());
    }

    /**
     * Verifies that conversions between units defined in the {@link Units} class give expected result.
     */
    @Test
    public void testUnitConversions() {
        final Units units = Units.getDefault();
        assertEquals("0.5 km",       500, units.kilometre().getConverterTo(units.metre()) .convert(0.5),  0);
        assertEquals("0.25 day", 6*60*60, units.day()      .getConverterTo(units.second()).convert(0.25), 0);
        assertEquals("300 ppm",  300E-6,  units.ppm()      .getConverterTo(units.one())   .convert(300), 1E-12);
        assertEquals(Math.toRadians(30),  units.degree()   .getConverterTo(units.radian()).convert(30),  1E-12);
    }
}
