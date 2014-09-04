/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2009-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.test.referencing;

import javax.measure.unit.NonSI;

import org.opengis.referencing.cs.*;
import org.opengis.referencing.crs.*;
import org.opengis.referencing.datum.*;
import org.opengis.util.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;

import org.junit.Test;
import org.opengis.test.TestCase;

import static org.junit.Assume.*;
import static org.opengis.test.Assert.*;
import static org.opengis.test.Validators.*;


/**
 * Tests {@linkplain CoordinateReferenceSystem Coordinate Reference System} and related objects
 * from the {@code org.opengis.referencing.crs} packages. CRS instances are created using the
 * authority factory given at construction time.
 *
 * @author  Cédric Briançon (Geomatys)
 * @version 3.0
 * @since   2.3
 */
public abstract class CRSTest extends TestCase {
    /**
     * The authority factory for creating a {@link CoordinateReferenceSystem} from a code,
     * or {@code null} if none.
     */
    protected final CRSAuthorityFactory factory;

    /**
     * Creates a new test using the given factory. If the given factory is {@code null},
     * then the tests will be skipped.
     *
     * @param factory Factory for creating a {@link CoordinateReferenceSystem}.
     */
    protected CRSTest(final CRSAuthorityFactory factory) {
        this.factory = factory;
    }

    /**
     * Tests the creation of the WGS84 {@linkplain CoordinateReferenceSystem crs} from the
     * EPSG code.
     *
     * @throws NoSuchAuthorityCodeException
     *          If the specified code is not found among the ones present in the database.
     * @throws FactoryException
     *          If the creation of the {@link CoordinateReferenceSystem} failed for an other raison.
     */
    @Test
    public void testCRSAuthorityCreation() throws NoSuchAuthorityCodeException, FactoryException {
        assumeNotNull(factory);
        final GeographicCRS crs = factory.createGeographicCRS("EPSG:4326");
        validate(crs);
        assertNotNull(crs);
        assertEquals("WGS 84", crs.getName().getCode());
        /*
         * Coordinate system validation.
         */
        final EllipsoidalCS cs = crs.getCoordinateSystem();
        final CoordinateSystemAxis latitude  = cs.getAxis(0);
        final CoordinateSystemAxis longitude = cs.getAxis(1);
        assertEquals("Geodetic latitude",  latitude.getName().getCode());
        assertEquals(AxisDirection.NORTH,  latitude.getDirection());
        assertEquals(NonSI.DEGREE_ANGLE,   latitude.getUnit());
        assertEquals("Geodetic longitude", longitude.getName().getCode());
        assertEquals(AxisDirection.EAST,   longitude.getDirection());
        assertEquals(NonSI.DEGREE_ANGLE,   longitude.getUnit());
        /*
         * Datum validation.
         */
        final GeodeticDatum datum = crs.getDatum();
        assertEquals("World Geodetic System 1984", datum.getName().getCode());
        final PrimeMeridian pm = datum.getPrimeMeridian();
        assertEquals(0.0, pm.getGreenwichLongitude(), 0.0);
        assertEquals(NonSI.DEGREE_ANGLE, pm.getAngularUnit());
    }
}
