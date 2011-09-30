/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011 Open Geospatial Consortium, Inc.
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
package org.opengis.wrapper.proj4;

import javax.measure.unit.SI;

import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Tests the {@link PJDatum} class.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class PJDatumTest {
    /**
     * Ensures that the given object is the WGS84 definition.
     */
    private static void assertIsWGS84(final PJDatum pj) {
        assertEquals("+proj=latlong +datum=WGS84 +ellps=WGS84 +towgs84=0,0,0", pj.getDefinition().trim());
        assertEquals("Lat/long (Geodetic alias)", pj.toString().trim());
        assertEquals("WGS84",                 pj.getName().toString());
        assertEquals(PJDatum.Type.GEOGRAPHIC, pj.getType());
        assertEquals(6378137.0,               pj.getSemiMajorAxis(),     1E-9);
        assertEquals(6356752.314245179,       pj.getSemiMinorAxis(),     1E-9);
        assertEquals(298.257223563,           pj.getInverseFlattening(), 1E-9);
        assertEquals(0.0,                     pj.getGreenwichLongitude(), 0.0);
        assertSame  (SI.METRE,                pj.getAxisUnit());
        assertFalse(pj.isSphere());
        assertArrayEquals(new char[] {'e', 'n', 'u'}, pj.getAxisDirections());
    }

    /**
     * Tests the creation of a simple WGS84 object.
     */
    @Test
    public void testWGS84() {
        final PJDatum pj = new PJDatum(null, "+proj=latlong +datum=WGS84");
        assertIsWGS84(pj);
    }

    /**
     * Tests the creation of the EPSG:3395 projected CRS
     */
    @Test
    public void testEPSG3395() {
        final PJDatum pj = new PJDatum(null, "+init=epsg:3395");
        assertEquals(PJDatum.Type.PROJECTED, pj.getType());
        assertArrayEquals(new char[] {'e', 'n', 'u'}, pj.getAxisDirections());
        assertIsWGS84(new PJDatum(pj));
    }

    /**
     * Tests the {@link PJDatum#getLinearUnit(boolean)} method using values close but not
     * identical to the expected values.
     */
    @Test
    public void testGetLinearUnit() {
        PJDatum pj;
        pj = new PJDatum(null, "+proj=merc +to_meter=" + (1 + PJDatum.EPS/2));
        assertSame(SI.METRE, pj.getLinearUnit(false));
        pj = new PJDatum(null, "+proj=merc +to_meter=" + (1000 - PJDatum.EPS/2));
        assertSame(SI.KILOMETRE, pj.getLinearUnit(false));
    }
}
