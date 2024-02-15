/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2009-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.test.referencing;

import org.opengis.referencing.cs.*;
import org.opengis.referencing.crs.*;
import org.opengis.referencing.datum.*;
import org.opengis.util.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;

import org.junit.Test;
import org.opengis.test.Units;
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
 * @version 3.0.1
 * @since   2.3
 */
public abstract class CRSTest extends TestCase {
    /**
     * The units of measurement to be used for the tests.
     */
    private final Units units;

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
        this.units   = Units.getDefault();
        this.factory = factory;
    }

    /**
     * Tests the creation of the WGS84 {@linkplain CoordinateReferenceSystem crs} from the
     * EPSG code.
     *
     * @throws NoSuchAuthorityCodeException
     *          If the specified code is not found among the ones present in the database.
     * @throws FactoryException
     *          If the creation of the {@link CoordinateReferenceSystem} failed for another reason.
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
        assertEquals(units.degree(),       latitude.getUnit());
        assertEquals("Geodetic longitude", longitude.getName().getCode());
        assertEquals(AxisDirection.EAST,   longitude.getDirection());
        assertEquals(units.degree(),       longitude.getUnit());
        /*
         * Datum validation.
         */
        final GeodeticDatum datum = crs.getDatum();
        assertEquals("World Geodetic System 1984", datum.getName().getCode());
        final PrimeMeridian pm = datum.getPrimeMeridian();
        assertEquals(0.0, pm.getGreenwichLongitude(), 0.0);
        assertEquals(units.degree(),pm.getAngularUnit());
    }
}
