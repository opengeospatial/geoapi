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
     * is the check for axis order. The result will depend on whether the axis orientations
     * map has been properly created or not.
     *
     * @throws FactoryException if an error occurred while creating the CRS objects.
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
