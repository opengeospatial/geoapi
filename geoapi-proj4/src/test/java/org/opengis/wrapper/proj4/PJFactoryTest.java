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

import org.opengis.util.FactoryException;
import org.opengis.referencing.crs.GeographicCRS;

import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Tests the {@link PJFactory} class.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class PJFactoryTest {
    /**
     * Tests the creation of the EPSG:4326 geographic CRS. The interesting part of this test
     * is the check for axis order. The result will depend on whatever the axis orientations
     * map has been properly created.
     *
     * @throws FactoryException If an error occurred while creating the CRS objects.
     */
    @Test
    public void testEPSG_4326() throws FactoryException {
        final PJFactory.EPSG factory = new PJFactory.EPSG();
        final GeographicCRS crs = factory.createGeographicCRS("EPSG:4326");
        // Use Proj.4 specific API to check axis order.
        final PJDatum pj = (PJDatum) crs.getDatum();
        assertArrayEquals(new char[] {'n', 'e', 'u'}, pj.getAxisDirections());
    }
}
