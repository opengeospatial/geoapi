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

import java.util.Map;
import java.util.List;
import java.util.Collections;
import javax.measure.unit.SI;
import javax.measure.unit.NonSI;

import org.opengis.referencing.cs.*;
import org.opengis.referencing.crs.*;
import org.opengis.referencing.datum.*;
import org.opengis.referencing.IdentifiedObject;
import org.opengis.util.Factory;
import org.opengis.util.FactoryException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.opengis.test.TestCase;

import static org.junit.Assume.*;
import static org.opengis.test.Assert.*;
import static org.opengis.test.Validators.*;


/**
 * Tests objects that combine all referencing sub-packages, especially {@code crs}, {@code cs} and
 * {@code datum}. The instances are created using the various factories given at construction time.
 *
 * In order to specify their factories and run the tests in a JUnit framework, implementors can
 * define a subclass as below:
 *
 * <blockquote><pre>import org.junit.runner.RunWith;
 *import org.junit.runners.JUnit4;
 *import org.opengis.test.referencing.ReferencingTest;
 *
 *&#64;RunWith(JUnit4.class)
 *public class MyTest extends ReferencingTest {
 *    public MyTest() {
 *        super(new MyCRSFactory(), new MyCSFactory(), new MyDatumFactory());
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
public strictfp class ReferencingTest extends TestCase {
    /**
     * Factory to build {@link CoordinateReferenceSystem} instances, or {@code null} if none.
     */
    protected final CRSFactory crsFactory;

    /**
     * Factory to build {@link CoordinateSystem} instances, or {@code null} if none.
     */
    protected final CSFactory csFactory;

    /**
     * Factory to build {@link Datum} instances, or {@code null} if none.
     */
    protected final DatumFactory datumFactory;

    /**
     * Returns a default set of factories to use for running the tests. Those factories are given
     * in arguments to the constructor when this test class is instantiated directly by JUnit (for
     * example as a {@linkplain org.junit.runners.Suite.SuiteClasses suite} element), instead than
     * subclassed by the implementor. The factories are fetched as documented in the
     * {@link #factories(Class[])} javadoc.
     *
     * @return The default set of arguments to be given to the {@code ReferencingTest} constructor.
     *
     * @since 3.1
     */
    @Parameterized.Parameters
    public static List<Factory[]> factories() {
        return factories(CRSFactory.class, CSFactory.class, DatumFactory.class);
    }

    /**
     * Creates a new test using the given factories. If a given factory is {@code null},
     * then the tests which depend on it will be skipped.
     *
     * @param crsFactory   Factory for creating {@link CoordinateReferenceSystem} instances.
     * @param csFactory    Factory for creating {@link CoordinateSystem} instances.
     * @param datumFactory Factory for creating {@link Datum} instances.
     */
    public ReferencingTest(final CRSFactory crsFactory, final CSFactory csFactory, final DatumFactory datumFactory) {
        this.crsFactory   = crsFactory;
        this.csFactory    = csFactory;
        this.datumFactory = datumFactory;
    }

    /**
     * Builds a map containing only one value, composed by the {@link IdentifiedObject#NAME_KEY}
     * identifier and the value specified.
     *
     * @param value The value for the name key.
     * @return A map containing only the value specified for the name key.
     */
    private static Map<String,String> createMapWithName(final String value) {
        return Collections.singletonMap(IdentifiedObject.NAME_KEY, value);
    }

    /**
     * Tests the creation of the WGS84 {@linkplain CoordinateReferenceSystem CRS}, and verifies
     * that the axes are in the given (<var>longitude</var>, <var>latitude</var>) order.
     *
     * @throws FactoryException if a factory fails to create a referencing object.
     */
    @Test
    public void testWGS84() throws FactoryException {
        /*
         * Build a geodetic datum.
         */
        assumeNotNull(datumFactory);
        PrimeMeridian primeMeridian = datumFactory.createPrimeMeridian(
                createMapWithName("Greenwich"), 0.0, NonSI.DEGREE_ANGLE);
        validate(primeMeridian);
        Ellipsoid ellipsoid = datumFactory.createEllipsoid(
                createMapWithName("WGS 84"), 6378137.0, 298.257223563, SI.METRE);
        validate(ellipsoid);
        GeodeticDatum datum = datumFactory.createGeodeticDatum(
                createMapWithName("World Geodetic System 1984"), ellipsoid, primeMeridian);
        validate(datum);
        /*
         * Build an ellipsoidal coordinate system.
         */
        assumeNotNull(csFactory);
        CoordinateSystemAxis longitudeAxis = csFactory.createCoordinateSystemAxis(
                createMapWithName("Geodetic longitude"), "\u03BB", AxisDirection.EAST, NonSI.DEGREE_ANGLE);
        validate(longitudeAxis);
        CoordinateSystemAxis latitudeAxis  = csFactory.createCoordinateSystemAxis(
                createMapWithName("Geodetic latitude"),  "\u03C6", AxisDirection.NORTH, NonSI.DEGREE_ANGLE);
        validate(latitudeAxis);
        CoordinateSystemAxis heightAxis  = csFactory.createCoordinateSystemAxis(
                createMapWithName("height"), "h", AxisDirection.UP, SI.METRE);
        validate(heightAxis);
        EllipsoidalCS cs = csFactory.createEllipsoidalCS(
                createMapWithName("WGS 84"), latitudeAxis, longitudeAxis, heightAxis);
        validate(cs);
        /*
         * Finally build the geographic coordinate reference system.
         */
        assumeNotNull(crsFactory);
        GeographicCRS crs = crsFactory.createGeographicCRS(
                createMapWithName("WGS84(DD)"), datum, cs);
        validate(crs);
        /*
         * Validations. Fetch the new references for 'cs', 'longitudeAxis', etc.,
         * which may or may not be the instance that we created.
         */
        cs = crs.getCoordinateSystem();
        longitudeAxis = cs.getAxis(1);
        latitudeAxis  = cs.getAxis(0);
        heightAxis    = cs.getAxis(2);
        assertEquals("Geodetic latitude",  latitudeAxis .getName().toString());
        assertEquals(AxisDirection.NORTH,  latitudeAxis .getDirection());
        assertEquals(NonSI.DEGREE_ANGLE,   latitudeAxis .getUnit());
        assertEquals("Geodetic longitude", longitudeAxis.getName().toString());
        assertEquals(AxisDirection.EAST,   longitudeAxis.getDirection());
        assertEquals(NonSI.DEGREE_ANGLE,   longitudeAxis.getUnit());
        assertEquals("height",             heightAxis   .getName().toString());
        assertEquals(AxisDirection.UP,     heightAxis   .getDirection());
        assertEquals(SI.METRE,             heightAxis   .getUnit());

        datum = crs.getDatum();
        assertEquals("World Geodetic System 1984", datum.getName().toString());
        primeMeridian = datum.getPrimeMeridian();
        assertEquals(0.0, primeMeridian.getGreenwichLongitude(), 0.0);
        assertEquals(NonSI.DEGREE_ANGLE, primeMeridian.getAngularUnit());
    }
}
