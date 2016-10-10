/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2008-2011 Open Geospatial Consortium, Inc.
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
        assertEquals("0.5 km",       500, units.kilometre().getConverterTo(units.metre()) .convert(0.5),     0);
        assertEquals("0.25 day", 6*60*60, units.day()      .getConverterTo(units.second()).convert(0.25),    0);
        assertEquals("300 ppm",   300E-6, units.ppm()      .getConverterTo(units.one())   .convert(300), 1E-12);
        assertEquals("100 grad",      90, units.grad()     .getConverterTo(units.degree()).convert(100), 1E-12);
        assertEquals(Math.toRadians(30),  units.degree()   .getConverterTo(units.radian()).convert(30),  1E-12);
    }
}
