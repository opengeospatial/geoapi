/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The Proj.4 wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementors can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.wrapper.proj4;

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
        assertSame  (Units.METRE,             pj.getAxisUnit());
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
        assertSame(Units.METRE, pj.getLinearUnit(false));
        pj = new PJDatum(null, "+proj=merc +to_meter=" + (1000 - PJDatum.EPS/2));
        assertSame(Units.KILOMETRE, pj.getLinearUnit(false));
    }
}
