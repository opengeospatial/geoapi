/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2009 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.test.referencing;

import javax.measure.unit.NonSI;

import org.opengis.referencing.cs.*;
import org.opengis.referencing.crs.*;
import org.opengis.referencing.datum.*;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;

import org.junit.Test;
import org.opengis.test.TestCase;

import static org.junit.Assume.*;
import static org.opengis.test.Validators.*;


/**
 * Tests {@linkplain CoordinateReferenceSystem Coordinate Reference System} and related objects
 * from the {@code org.opengis.referencing.crs} packages. CRS instances are created using the
 * authority factory given at construction time.
 *
 * @author Cédric Briançon (Geomatys)
 * @since GeoAPI 2.3
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
        assertEquals("WGS 84", crs.getName().toString());
        /*
         * Coordinate system validation.
         */
        final EllipsoidalCS cs = crs.getCoordinateSystem();
        final CoordinateSystemAxis latitude  = cs.getAxis(0);
        final CoordinateSystemAxis longitude = cs.getAxis(1);
        assertEquals("Geodetic latitude",  latitude.getName().toString());
        assertEquals(AxisDirection.NORTH,  latitude.getDirection());
        assertEquals(NonSI.DEGREE_ANGLE,   latitude.getUnit());
        assertEquals("Geodetic longitude", longitude.getName().toString());
        assertEquals(AxisDirection.EAST,   longitude.getDirection());
        assertEquals(NonSI.DEGREE_ANGLE,   longitude.getUnit());
        /*
         * Datum validation.
         */
        final GeodeticDatum datum = crs.getDatum();
        assertEquals("World Geodetic System 1984", datum.getName().toString());
        final PrimeMeridian pm = datum.getPrimeMeridian();
        assertEquals(0.0, pm.getGreenwichLongitude(), 0.0);
        assertEquals(NonSI.DEGREE_ANGLE, pm.getAngularUnit());
    }
}
