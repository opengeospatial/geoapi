/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2015 Open Geospatial Consortium, Inc.
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
package org.opengis.test.wkt;

import java.util.Date;
import java.util.List;
import javax.measure.unit.Unit;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import org.opengis.referencing.IdentifiedObject;
import org.opengis.referencing.crs.*;
import org.opengis.referencing.cs.*;
import org.opengis.referencing.datum.*;
import org.opengis.util.Factory;
import org.opengis.util.FactoryException;
import org.opengis.test.referencing.ReferencingTestCase;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.Test;

import static org.junit.Assume.assumeTrue;
import static org.opengis.test.Assert.*;


/**
 * Tests the Well-Known Text (WKT) parser of Coordinate Reference System (CRS) objects.
 * For running this test, vendors need to implement the {@link CRSFactory#createFromWKT(String)} method.
 * That method will be given various WKT strings from the
 * <a href="http://docs.opengeospatial.org/is/12-063r5/12-063r5.html">OGC 12-063r5 —
 * Well-known text representation of coordinate reference systems</a> specification.
 * The object returned by {@code createFromWKT(String)} will be checked for the following properties:
 *
 * <ul>
 *   <li>{@link IdentifiedObject#getName()} and {@link IdentifiedObject#getIdentifiers() getIdentifiers()} on the CRS and the datum</li>
 *   <li>{@link Ellipsoid#getSemiMajorAxis()} and {@link Ellipsoid#getInverseFlattening() getInverseFlattening()}</li>
 *   <li>{@link PrimeMeridian#getGreenwichLongitude()}</li>
 *   <li>{@link CoordinateSystem#getDimension()}</li>
 *   <li>{@link CoordinateSystemAxis#getDirection()} and {@link CoordinateSystemAxis#getUnit() getUnit()}</li>
 *   <li>{@link CoordinateReferenceSystem#getScope()} (optional – null allowed)</li>
 *   <li>{@link CoordinateReferenceSystem#getDomainOfValidity()} (optional – null allowed)</li>
 *   <li>{@link CoordinateReferenceSystem#getRemarks()} (optional – null allowed)</li>
 * </ul>
 *
 * <div class="note"><b>Usage example:</b>
 * in order to specify their factories and run the tests in a JUnit framework, implementors can
 * define a subclass in their own test suite as in the example below:
 *
 * <blockquote><pre>import org.junit.runner.RunWith;
 *import org.junit.runners.JUnit4;
 *import org.opengis.test.wkt.CRSParserTest;
 *
 *&#64;RunWith(JUnit4.class)
 *public class MyTest extends CRSParserTest {
 *    public MyTest() {
 *        super(new MyCRSFactory());
 *    }
 *}</pre></blockquote>
 * </div>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @see <a href="http://docs.opengeospatial.org/is/12-063r5/12-063r5.html">WKT 2 specification</a>
 */
@RunWith(Parameterized.class)
public strictfp class CRSParserTest extends ReferencingTestCase {
    /**
     * An array of length 3 containing the North, East and Up directions, in that order. This can be an argument to
     * calls to the {@link #verifyCoordinateSystem(CoordinateSystem, Class, int, AxisDirection[], Unit[])} method.
     */
    private static final AxisDirection[] NORTH_EAST_UP = {
        AxisDirection.NORTH,
        AxisDirection.EAST,
        AxisDirection.UP
    };

    /**
     * An array of length 3 containing the geocentric X, Y and Z directions, in that order. This can be an argument
     * to calls to the {@link #verifyCoordinateSystem(CoordinateSystem, Class, int, AxisDirection[], Unit[])} method.
     */
    private static final AxisDirection[] GEOCENTRIC = {
        AxisDirection.GEOCENTRIC_X,
        AxisDirection.GEOCENTRIC_Y,
        AxisDirection.GEOCENTRIC_Z
    };

    /**
     * An array of length 3 containing (degrees, degrees, metres) units.
     * They are the most common units of three-dimensional ellipsoidal coordinate systems.
     */
    private static final Unit<?>[] DDM_UNITS = {
        NonSI.DEGREE_ANGLE,
        NonSI.DEGREE_ANGLE,
        SI.METRE
    };

    /**
     * An array of length 3 containing (metres, metres, metres) units.
     * They are the most common units of Cartesian coordinate systems.
     */
    private static final Unit<?>[] MMM_UNITS = {
        SI.METRE,
        SI.METRE,
        SI.METRE
    };

    /**
     * The factory to use for parsing WKT strings. The {@link CRSFactory#createFromWKT(String)} method
     * of this factory will be invoked for each test defined in this {@code CRSParserTest} class.
     */
    protected final CRSFactory factory;

    /**
     * The instance returned by {@link CRSFactory#createFromWKT(String)} after parsing the WKT.
     * Subclasses can use this field if they wish to verify additional properties after the
     * verifications done by this {@code CRSParserTest} class.
     */
    protected CoordinateReferenceSystem object;

    /**
     * Returns a default set of factories to use for running the tests. Those factories are given
     * in arguments to the constructor when this test class is instantiated directly by JUnit (for
     * example as a {@linkplain org.junit.runners.Suite.SuiteClasses suite} element), instead than
     * subclassed by the implementor. The factories are fetched as documented in the
     * {@link #factories(Class[])} javadoc.
     *
     * @return The default set of arguments to be given to the {@code ObjectFactoryTest} constructor.
     */
    @Parameterized.Parameters
    @SuppressWarnings("unchecked")
    public static List<Factory[]> factories() {
        return factories(CRSFactory.class);
    }

    /**
     * Creates a new test using the given factory.
     *
     * @param factory Factory for parsing {@link CoordinateReferenceSystem} instances.
     */
    public CRSParserTest(final CRSFactory factory) {
        super(factory);
        this.factory = factory;
    }

    /**
     * Asserts that the given datum has the expected name.
     *
     * @param datum The datum to verify.
     * @param name  The string representation of the expected name (ignoring code space).
     */
    private static void verifyDatum(final Datum datum, final String name) {
        assertNotNull("datum", datum);
        assertEquals("datum.name.code", name, datum.getName().getCode());
    }

    /**
     * Asserts the the given character sequence is either null or equals to the given value.
     * This is used for optional elements like remarks.
     *
     * @param property  The property being tested, for producing a message in case of assertion failure.
     * @param expected  The expected value.
     * @param actual    The actual value.
     */
    private static void assertNullOrEquals(final String property, final String expected, final CharSequence actual) {
        if (actual != null) {
            assertEquals(property, expected, actual.toString());
        }
    }

    /**
     * Parses the given WKT.
     *
     * @param  type The expected object type.
     * @param  text The WKT string to parse.
     * @return The parsed object.
     * @throws FactoryException if an error occurred during the WKT parsing.
     */
    private <T extends CoordinateReferenceSystem> T parse(final Class<T> type, final String text) throws FactoryException {
        assumeTrue("No CRSFactory.", factory != null);
        object = factory.createFromWKT(text);
        assertInstanceOf("CRSFactory.createFromWKT", type, object);
        return type.cast(object);
    }

    /**
     * Parses a geodetic CRS which contain a remark written using non-ASCII characters.
     * The WKT parsed by this test is:
     *
     * <blockquote><pre>GEODCRS[“S-95”,
     *  DATUM[“Pulkovo 1995”,
     *    ELLIPSOID[“Krassowsky 1940”,6378245,298.3,
     *      LENGTHUNIT[“metre”,1.0]]],
     *  CS[ellipsoidal,2],
     *    AXIS[“latitude”,north,ORDER[1]],
     *    AXIS[“longitude”,east,ORDER[2]],
     *    ANGLEUNIT[“degree”,0.0174532925199433],
     *  REMARK[“Система Геодеэических Координвт года 1995(СК-95)”]]</pre></blockquote>
     *
     * @throws FactoryException if an error occurred during the WKT parsing.
     *
     * @see <a href="http://docs.opengeospatial.org/is/12-063r5/12-063r5.html#34">OGC 12-063r5 §7.3.5 example 3</a>
     */
    @Test
    public void testGeographicWithRemark() throws FactoryException {
        final GeodeticCRS crs = parse(GeodeticCRS.class,
                "GEODCRS[“S-95”,\n" +
                "  DATUM[“Pulkovo 1995”,\n" +
                "    ELLIPSOID[“Krassowsky 1940”,6378245,298.3,\n" +
                "      LENGTHUNIT[“metre”,1.0]]],\n" +
                "  CS[ellipsoidal,2],\n" +
                "    AXIS[“latitude”,north,ORDER[1]],\n" +
                "    AXIS[“longitude”,east,ORDER[2]],\n" +
                "    ANGLEUNIT[“degree”,0.0174532925199433],\n" +
                "  REMARK[“Система Геодеэических Координвт года 1995(СК-95)”]]");

        validators.validate(crs);
        verifyIdentification  (crs, "S-95", null);
        verifyDatum           (crs.getDatum(), "Pulkovo 1995");
        verifyFlattenedSphere (crs.getDatum().getEllipsoid(), "Krassowsky 1940", 6378245, 298.3, SI.METRE);
        verifyPrimeMeridian   (crs.getDatum().getPrimeMeridian(), null, 0, NonSI.DEGREE_ANGLE);
        verifyCoordinateSystem(crs.getCoordinateSystem(), EllipsoidalCS.class, 2, NORTH_EAST_UP, DDM_UNITS);
        assertNullOrEquals("remark", "Система Геодеэических Координвт года 1995(СК-95)", crs.getRemarks());
    }

    /**
     * Parses a geodetic CRS with Cartesian coordinate system.
     * The WKT parsed by this test is:
     *
     * <blockquote><pre>GEODETICCRS[“JGD2000”,
     *  DATUM[“Japanese Geodetic Datum 2000”,
     *    ELLIPSOID[“GRS 1980”,6378137,298.257222101]],
     *  CS[Cartesian,3],
     *    AXIS[“(X)”,geocentricX],
     *    AXIS[“(Y)”,geocentricY],
     *    AXIS[“(Z)”,geocentricZ],
     *    LENGTHUNIT[“metre”,1.0],
     *  SCOPE[“Geodesy, topographic mapping and cadastre”],
     *  AREA[“Japan”],
     *  BBOX[17.09,122.38,46.05,157.64],
     *  TIMEEXTENT[2002-04-01,2011-10-21],
     *  ID[“EPSG”,4946,URI[“urn:ogc:def:crs:EPSG::4946”]],
     *  REMARK[“注：JGD2000ジオセントリックは現在JGD2011に代わりました。”]]</pre></blockquote>
     *
     * @throws FactoryException if an error occurred during the WKT parsing.
     *
     * @see <a href="http://docs.opengeospatial.org/is/12-063r5/12-063r5.html#56">OGC 12-063r5 §8.4 example 1</a>
     */
    @Test
    public void testGeocentric() throws FactoryException {
        final GeodeticCRS crs = parse(GeodeticCRS.class,
                "GEODETICCRS[“JGD2000”,\n" +
                "  DATUM[“Japanese Geodetic Datum 2000”,\n" +
                "    ELLIPSOID[“GRS 1980”,6378137,298.257222101]],\n" +
                "  CS[Cartesian,3],\n" +
                "    AXIS[“(X)”,geocentricX],\n" +
                "    AXIS[“(Y)”,geocentricY],\n" +
                "    AXIS[“(Z)”,geocentricZ],\n" +
                "    LENGTHUNIT[“metre”,1.0],\n" +
                "  SCOPE[“Geodesy, topographic mapping and cadastre”],\n" +
                "  AREA[“Japan”],\n" +
                "  BBOX[17.09,122.38,46.05,157.64],\n" +
                "  TIMEEXTENT[2002-04-01,2011-10-21],\n" +
                "  ID[“EPSG”,4946,URI[“urn:ogc:def:crs:EPSG::4946”]],\n" +
                "  REMARK[“注：JGD2000ジオセントリックは現在JGD2011に代わりました。”]]");

        validators.validate(crs);
        verifyIdentification  (crs, "JGD2000", "4946");
        verifyDatum           (crs.getDatum(), "Japanese Geodetic Datum 2000");
        verifyFlattenedSphere (crs.getDatum().getEllipsoid(), "GRS 1980", 6378137, 298.257222101, SI.METRE);
        verifyPrimeMeridian   (crs.getDatum().getPrimeMeridian(), null, 0, NonSI.DEGREE_ANGLE);
        verifyCoordinateSystem(crs.getCoordinateSystem(), CartesianCS.class, 3, GEOCENTRIC, MMM_UNITS);
        verifyGeographicExtent(crs.getDomainOfValidity(), "Japan", 17.09, 122.38, 46.05, 157.64);
        verifyTimeExtent      (crs.getDomainOfValidity(), new Date(1017619200000L), new Date(1319155200000L), 1);
        assertNullOrEquals("scope", "Geodesy, topographic mapping and cadastre", crs.getScope());
        assertNullOrEquals("remark", "注：JGD2000ジオセントリックは現在JGD2011に代わりました。", crs.getRemarks());
    }
}
