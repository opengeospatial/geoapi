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

import java.util.List;
import javax.measure.unit.NonSI;

import org.opengis.referencing.cs.*;
import org.opengis.referencing.crs.*;
import org.opengis.referencing.datum.*;
import org.opengis.referencing.AuthorityFactory;
import org.opengis.util.Factory;
import org.opengis.util.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.opengis.test.TestCase;

import static org.junit.Assume.*;
import static org.opengis.test.Assert.*;
import static org.opengis.test.Validators.*;


/**
 * Tests the creation of referencing objects from the {@linkplain AuthorityFactory authority
 * factories} given at construction time.
 *
 * In order to specify their factories and run the tests in a JUnit framework, implementors can
 * define a subclass as below:
 *
 * <blockquote><pre>import org.junit.runner.RunWith;
 *import org.junit.runners.JUnit4;
 *import org.opengis.test.referencing.AuthorityFactoryTest;
 *
 *&#64;RunWith(JUnit4.class)
 *public class MyTest extends AuthorityFactoryTest {
 *    public MyTest() {
 *        super(new MyCRSAuthorityFactory(), new MyCSAuthorityFactory(), new MyDatumAuthorityFactory());
 *    }
 *}</pre></blockquote>
 *
 * Alternatively this test class can also be used directly in the {@link org.opengis.test.TestSuite},
 * which combine every tests defined in the GeoAPI conformance module.
 *
 * @author  Cédric Briançon (Geomatys)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   2.3
 */
@RunWith(Parameterized.class)
public strictfp class AuthorityFactoryTest extends TestCase {
    /**
     * Factory to build {@link CoordinateReferenceSystem} instances, or {@code null} if none.
     */
    protected final CRSAuthorityFactory crsFactory;

    /**
     * Factory to build {@link CoordinateSystem} instances, or {@code null} if none.
     */
    protected final CSAuthorityFactory csFactory;

    /**
     * Factory to build {@link Datum} instances, or {@code null} if none.
     */
    protected final DatumAuthorityFactory datumFactory;

    /**
     * Returns a default set of factories to use for running the tests. Those factories are given
     * in arguments to the constructor when this test class is instantiated directly by JUnit (for
     * example as a {@linkplain org.junit.runners.Suite.SuiteClasses suite} element), instead than
     * subclassed by the implementor. The factories are fetched as documented in the
     * {@link #factories(Class[])} javadoc.
     *
     * @return The default set of arguments to be given to the {@code AuthorityFactoryTest} constructor.
     *
     * @since 3.1
     */
    @Parameterized.Parameters
    public static List<Factory[]> factories() {
        return factories(CRSAuthorityFactory.class, CSAuthorityFactory.class, DatumAuthorityFactory.class);
    }

    /**
     * Creates a new test using the given factories. If a given factory is {@code null},
     * then the tests which depend on it will be skipped.
     *
     * @param crsFactory   Factory for creating {@link CoordinateReferenceSystem} instances.
     * @param csFactory    Factory for creating {@link CoordinateSystem} instances.
     * @param datumFactory Factory for creating {@link Datum} instances.
     */
    public AuthorityFactoryTest(final CRSAuthorityFactory crsFactory,
            final CSAuthorityFactory csFactory, final DatumAuthorityFactory datumFactory)
    {
        this.crsFactory   = crsFactory;
        this.csFactory    = csFactory;
        this.datumFactory = datumFactory;
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
    public void testWGS84() throws NoSuchAuthorityCodeException, FactoryException {
        assumeNotNull(crsFactory);
        final GeographicCRS crs = crsFactory.createGeographicCRS("EPSG:4326");
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
