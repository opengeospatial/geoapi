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
package org.proj4;

import org.opengis.referencing.operation.TransformException;

import org.junit.*;
import static org.junit.Assert.*;


/**
 * Tests the {@link PJ} class.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class PJTest {
    /**
     * Ensures that the given object is the WGS84 definition.
     */
    private static void assertIsWGS84(final PJ pj) {
        assertEquals("+proj=latlong +datum=WGS84 +ellps=WGS84 +towgs84=0,0,0", pj.getDefinition().trim());
        assertEquals("Lat/long (Geodetic alias)", pj.toString().trim());
        assertEquals(PJ.Type.GEOGRAPHIC, pj.getType());
        assertEquals(6378137.0,          pj.getSemiMajorAxis(),         1E-9);
        assertEquals(6356752.314245179,  pj.getSemiMinorAxis(),         1E-9);
        assertEquals(0.0,                pj.getGreenwichLongitude(),    0.0);
        assertEquals(1.0,                pj.getLinearUnitToMetre(true), 0.0);
        assertArrayEquals(new char[] {'e', 'n', 'u'}, pj.getAxisDirections());
    }

    /**
     * Tests the creation of a simple WGS84 object.
     */
    @Test
    public void testWGS84() {
        final PJ pj = new PJ("+proj=latlong +datum=WGS84");
        assertIsWGS84(pj);
        /*
         * Finalize should never be invoked explicitely. However we do an exception in this
         * test suite in order to ensure that no error is thrown, and that all properties
         * are correctly mapped to 0 (as a safety).
         */
        pj.finalize();
        assertNull(pj.getType());
        assertTrue(Double.isNaN(pj.getSemiMajorAxis()));
        assertTrue(Double.isNaN(pj.getSemiMinorAxis()));
        assertTrue(Double.isNaN(pj.getGreenwichLongitude()));
    }

    /**
     * Tests the creation of the EPSG:3395 projected CRS
     */
    @Test
    public void testEPSG3395() {
        final PJ pj = new PJ("+init=epsg:3395");
        assertEquals(PJ.Type.PROJECTED, pj.getType());
        assertArrayEquals(new char[] {'e', 'n', 'u'}, pj.getAxisDirections());
        assertEquals(1.0, pj.getLinearUnitToMetre(true), 0.0);
        assertIsWGS84(new PJ(pj, PJ.Type.GEOGRAPHIC));
    }

    /**
     * Ensures that the native code correctly detects the case of null pointers.
     * This is important in order to ensure that we don't have a JVM crash.
     *
     * @throws TransformException Should never happen.
     */
    @Test(expected = NullPointerException.class)
    public void testNullPointerException() throws TransformException {
        final PJ pj = new PJ("+proj=latlong +datum=WGS84");
        pj.transform(null, 2, null, 0, 1);
    }

    /**
     * Ensures that the native code correctly detects the case of index out of bounds.
     * This is important in order to ensure that we don't have a JVM crash.
     *
     * @throws TransformException Should never happen.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsException() throws TransformException {
        final PJ pj = new PJ("+proj=latlong +datum=WGS84");
        pj.transform(pj, 2, new double[5], 2, 2);
    }
}
