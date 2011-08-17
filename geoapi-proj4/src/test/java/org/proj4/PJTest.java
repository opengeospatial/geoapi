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
package org.proj4;

import org.opengis.util.FactoryException;
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
        assertEquals(6378137.0,          pj.getSemiMajorAxis(),     1E-9);
        assertEquals(6356752.314245179,  pj.getSemiMinorAxis(),     1E-9);
        assertEquals(298.257223563,      pj.getInverseFlattening(), 1E-9);
        assertEquals(0.0,                pj.getGreenwichLongitude(), 0.0);
        assertFalse(pj.isSphere());
        assertArrayEquals(new char[] {'e', 'n', 'u'}, pj.getAxisDirections());
    }

    /**
     * Tests the creation of a simple WGS84 object.
     *
     * @throws FactoryException Should never happen.
     */
    @Test
    public void testWGS84() throws FactoryException {
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
        assertTrue(Double.isNaN(pj.getInverseFlattening()));
        assertTrue(Double.isNaN(pj.getGreenwichLongitude()));
    }

    /**
     * Tests the creation of the EPSG:3395 projected CRS
     *
     * @throws FactoryException Should never happen.
     */
    @Test
    public void testEPSG3395() throws FactoryException {
        final PJ pj = new PJ("+init=epsg:3395");
        assertEquals(PJ.Type.PROJECTED, pj.getType());
        assertArrayEquals(new char[] {'e', 'n', 'u'}, pj.getAxisDirections());
        assertIsWGS84(new PJ(pj, PJ.Type.GEOGRAPHIC));
    }

    /**
     * Ensures that the native code correctly detects the case of null pointers.
     * This is important in order to ensure that we don't have a JVM crash.
     *
     * @throws FactoryException Should never happen.
     * @throws TransformException Should never happen.
     */
    @Test(expected = NullPointerException.class)
    public void testNullPointerException() throws FactoryException, TransformException {
        final PJ pj = new PJ("+proj=latlong +datum=WGS84");
        pj.transform(null, 2, null, 0, 1);
    }

    /**
     * Ensures that the native code correctly detects the case of index out of bounds.
     * This is important in order to ensure that we don't have a JVM crash.
     *
     * @throws FactoryException Should never happen.
     * @throws TransformException Should never happen.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsException() throws FactoryException, TransformException {
        final PJ pj = new PJ("+proj=latlong +datum=WGS84");
        pj.transform(pj, 2, new double[5], 2, 2);
    }
}
