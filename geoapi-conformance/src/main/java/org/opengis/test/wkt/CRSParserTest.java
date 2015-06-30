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
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.test.referencing.ReferencingTestCase;
import org.opengis.test.Configuration;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.Test;

import static java.lang.Double.NaN;
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
 *   <li>{@link CoordinateSystemAxis#getAbbreviation()} when they were explicitly given in the WKT and do not need transliteration.</li>
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
     * An array of length 3 containing the East, North and Up directions, in that order. This can be an argument to
     * calls to the {@link #verifyCoordinateSystem(CoordinateSystem, Class, int, AxisDirection[], Unit[])} method.
     */
    private static final AxisDirection[] EAST_NORTH_UP = {
        AxisDirection.EAST,
        AxisDirection.NORTH,
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
    protected final CRSFactory crsFactory;

    /**
     * The instance returned by {@link CRSFactory#createFromWKT(String)} after parsing the WKT.
     * Subclasses can use this field if they wish to verify additional properties after the
     * verifications done by this {@code CRSParserTest} class.
     */
    protected CoordinateReferenceSystem object;

    /**
     * {@code true} if the test methods can invoke a <code>{@linkplain #validators validators}.validate(…)}</code>
     * method after parsing. Implementors can set this flag to {@code false} if their WKT parser is known to create
     * CRS objects that differ from the ISO 19111 model. One of the main reasons for disabling validation is because
     * the axis names specified by ISO 19162 differ from the axis names specified by ISO 19111.
     */
    protected boolean isValidationEnabled;

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
     * @param crsFactory Factory for parsing {@link CoordinateReferenceSystem} instances.
     */
    public CRSParserTest(final CRSFactory crsFactory) {
        super(crsFactory);
        this.crsFactory = crsFactory;
        @SuppressWarnings("unchecked")
        final boolean[] isEnabled = getEnabledFlags(
                Configuration.Key.isValidationEnabled);
        isValidationEnabled = isEnabled[0];
    }

    /**
     * Returns information about the configuration of the test which has been run.
     * This method returns a map containing:
     *
     * <ul>
     *   <li>All the following values associated to the {@link org.opengis.test.Configuration.Key} of the same name:
     *     <ul>
     *       <li>{@link #isValidationEnabled}</li>
     *       <li>{@link #crsFactory}</li>
     *     </ul>
     *   </li>
     * </ul>
     *
     * @return {@inheritDoc}
     */
    @Override
    public Configuration configuration() {
        final Configuration op = super.configuration();
        assertNull(op.put(Configuration.Key.isValidationEnabled, isValidationEnabled));
        assertNull(op.put(Configuration.Key.crsFactory,          crsFactory));
        return op;
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
     * Compares the abbreviations of coordinate system axes against the expected values.
     * The comparison is case-sensitive, e.g. <var>h</var> (ellipsoidal height) is not the same than
     * <var>H</var> (gravity-related height).
     *
     * <p>The GeoAPI conformance tests invoke this method only for abbreviations that should not need transliteration.
     * For example the GeoAPI tests do not invoke this method for geodetic latitude and longitude axes, because some
     * implementations may keep the Greek letters φ and λ as specified in ISO 19111 while other implementations may
     * transliterate those Greek letters to the <var>P</var> and <var>L</var> Latin letters.</p>
     *
     * @param cs The coordinate system to verify.
     * @param abbreviations The expected abbreviations. Null elements are considered unrestricted.
     */
    private static void verifyAxisAbbreviations(final CoordinateSystem cs, final String... abbreviations) {
        for (int i=0; i<abbreviations.length; i++) {
            final String expected = abbreviations[i];
            if (expected != null) {
                assertEquals("CoordinateSystemAxis.getAbbreviation()", expected, cs.getAxis(i).getAbbreviation());
            }
        }
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
        assumeTrue("No CRSFactory.", crsFactory != null);
        object = crsFactory.createFromWKT(text);
        assertInstanceOf("CRSFactory.createFromWKT", type, object);
        return type.cast(object);
    }

    /**
     * Parses a three-dimensional geodetic CRS.
     * The WKT parsed by this test is (except for quote characters):
     *
     * <blockquote><pre>GEODCRS[“WGS 84”,
     *  DATUM[“World Geodetic System 1984”,
     *    ELLIPSOID[“WGS 84”, 6378137, 298.257223563,
     *      LENGTHUNIT[“metre”,1.0]]],
     *  CS[ellipsoidal,3],
     *    AXIS[“(lat)”,north,ANGLEUNIT[“degree”,0.0174532925199433]],
     *    AXIS[“(lon)”,east,ANGLEUNIT[“degree”,0.0174532925199433]],
     *    AXIS[“ellipsoidal height (h)”,up,LENGTHUNIT[“metre”,1.0]]]</pre></blockquote>
     *
     * @throws FactoryException if an error occurred during the WKT parsing.
     *
     * @see <a href="http://docs.opengeospatial.org/is/12-063r5/12-063r5.html#56">OGC 12-063r5 §8.4 example 2</a>
     */
    @Test
    public void testGeographic() throws FactoryException {
        final GeodeticCRS crs = parse(GeodeticCRS.class,
                "GEODCRS[“WGS 84”,\n" +
                "  DATUM[“World Geodetic System 1984”,\n" +
                "    ELLIPSOID[“WGS 84”, 6378137, 298.257223563,\n" +
                "      LENGTHUNIT[“metre”,1.0]]],\n" +
                "  CS[ellipsoidal,3],\n" +
                "    AXIS[“(lat)”,north,ANGLEUNIT[“degree”,0.0174532925199433]],\n" +
                "    AXIS[“(lon)”,east,ANGLEUNIT[“degree”,0.0174532925199433]],\n" +
                "    AXIS[“ellipsoidal height (h)”,up,LENGTHUNIT[“metre”,1.0]]]");

        if (isValidationEnabled) {
            validators.validate(crs);
        }
        verifyIdentification   (crs, "WGS 84", null);
        verifyDatum            (crs.getDatum(), "World Geodetic System 1984");
        verifyFlattenedSphere  (crs.getDatum().getEllipsoid(), "WGS 84", 6378137, 298.257223563, SI.METRE);
        verifyPrimeMeridian    (crs.getDatum().getPrimeMeridian(), null, 0, NonSI.DEGREE_ANGLE);
        verifyCoordinateSystem (crs.getCoordinateSystem(), EllipsoidalCS.class, 3, NORTH_EAST_UP, DDM_UNITS);
        verifyAxisAbbreviations(crs.getCoordinateSystem(), null, null, "h");
    }

    /**
     * Parses a geodetic CRS which contain a remark written using non-ASCII characters.
     * The WKT parsed by this test is (except for quote characters):
     *
     * <blockquote><pre>GEODCRS[“S-95”,
     *  DATUM[“Pulkovo 1995”,
     *    ELLIPSOID[“Krassowsky 1940”, 6378245, 298.3,
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
                "    ELLIPSOID[“Krassowsky 1940”, 6378245, 298.3,\n" +
                "      LENGTHUNIT[“metre”,1.0]]],\n" +
                "  CS[ellipsoidal,2],\n" +
                "    AXIS[“latitude”,north,ORDER[1]],\n" +
                "    AXIS[“longitude”,east,ORDER[2]],\n" +
                "    ANGLEUNIT[“degree”,0.0174532925199433],\n" +
                "  REMARK[“Система Геодеэических Координвт года 1995(СК-95)”]]");

        if (isValidationEnabled) {
            validators.validate(crs);
        }
        verifyIdentification  (crs, "S-95", null);
        verifyDatum           (crs.getDatum(), "Pulkovo 1995");
        verifyFlattenedSphere (crs.getDatum().getEllipsoid(), "Krassowsky 1940", 6378245, 298.3, SI.METRE);
        verifyPrimeMeridian   (crs.getDatum().getPrimeMeridian(), null, 0, NonSI.DEGREE_ANGLE);
        verifyCoordinateSystem(crs.getCoordinateSystem(), EllipsoidalCS.class, 2, NORTH_EAST_UP, DDM_UNITS);
        assertNullOrEquals("remark", "Система Геодеэических Координвт года 1995(СК-95)", crs.getRemarks());
    }

    /**
     * Parses a geodetic CRS which contains a remark and an identifier.
     * The WKT parsed by this test is (except for quote characters):
     *
     * <blockquote><pre>GEODCRS[“NAD83”,
     *  DATUM[“North American Datum 1983”,
     *    ELLIPSOID[“GRS 1980”, 6378137, 298.257222101, LENGTHUNIT[“metre”,1.0]]],
     *  CS[ellipsoidal,2],
     *    AXIS[“latitude”,north],
     *    AXIS[“longitude”,east],
     *    ANGLEUNIT[“degree”,0.017453292519943],
     *    ID[“EPSG”,4269],
     *    REMARK[“1986 realisation”]]</pre></blockquote>
     *
     * @throws FactoryException if an error occurred during the WKT parsing.
     *
     * @see <a href="http://docs.opengeospatial.org/is/12-063r5/12-063r5.html#56">OGC 12-063r5 §8.4 example 3</a>
     */
    @Test
    public void testGeographicWithId() throws FactoryException {
        final GeodeticCRS crs = parse(GeodeticCRS.class,
                "GEODCRS[“NAD83”,\n" +
                "  DATUM[“North American Datum 1983”,\n" +
                "    ELLIPSOID[“GRS 1980”, 6378137, 298.257222101, LENGTHUNIT[“metre”,1.0]]],\n" +
                "  CS[ellipsoidal,2],\n" +
                "    AXIS[“latitude”,north],\n" +
                "    AXIS[“longitude”,east],\n" +
                "    ANGLEUNIT[“degree”,0.017453292519943],\n" +
                "  ID[“EPSG”,4269],\n" +
                "  REMARK[“1986 realisation”]]");

        if (isValidationEnabled) {
            validators.validate(crs);
        }
        verifyIdentification  (crs, "NAD83", "4269");
        verifyDatum           (crs.getDatum(), "North American Datum 1983");
        verifyFlattenedSphere (crs.getDatum().getEllipsoid(), "GRS 1980", 6378137, 298.257222101, SI.METRE);
        verifyPrimeMeridian   (crs.getDatum().getPrimeMeridian(), null, 0, NonSI.DEGREE_ANGLE);
        verifyCoordinateSystem(crs.getCoordinateSystem(), EllipsoidalCS.class, 2, NORTH_EAST_UP, DDM_UNITS);
        assertNullOrEquals("remark", "1986 realisation", crs.getRemarks());
    }

    /**
     * Parses a geodetic CRS with a prime meridian other than Greenwich and all angular units in grads.
     * The WKT parsed by this test is (except for quote characters):
     *
     * <blockquote><pre>GEODCRS[“NTF (Paris)”,
     *  DATUM[“Nouvelle Triangulation Francaise”,
     *    ELLIPSOID[“Clarke 1880 (IGN)”, 6378249.2, 293.4660213]],
     *  PRIMEM[“Paris”,2.5969213],
     *  CS[ellipsoidal,2],
     *    AXIS[“latitude”,north,ORDER[1]],
     *    AXIS[“longitude”,east,ORDER[2]],
     *    ANGLEUNIT[“grad”,0.015707963267949],
     *  REMARK[“Nouvelle Triangulation Française”]]</pre></blockquote>
     *
     * @throws FactoryException if an error occurred during the WKT parsing.
     *
     * @see <a href="http://docs.opengeospatial.org/is/12-063r5/12-063r5.html#56">OGC 12-063r5 §8.4 example 4</a>
     */
    @Test
    public void testGeographicWithGradUnits() throws FactoryException {
        final GeodeticCRS crs = parse(GeodeticCRS.class,
                "GEODCRS[“NTF (Paris)”,\n" +
                "  DATUM[“Nouvelle Triangulation Francaise”,\n" +
                "    ELLIPSOID[“Clarke 1880 (IGN)”, 6378249.2, 293.4660213]],\n" +
                "  PRIMEM[“Paris”,2.5969213],\n" +
                "  CS[ellipsoidal,2],\n" +
                "    AXIS[“latitude”,north,ORDER[1]],\n" +
                "    AXIS[“longitude”,east,ORDER[2]],\n" +
                "    ANGLEUNIT[“grad”,0.015707963267949],\n" +
                "  REMARK[“Nouvelle Triangulation Française”]]");

        if (isValidationEnabled) {
            validators.validate(crs);
        }
        verifyIdentification  (crs, "NTF (Paris)", null);
        verifyDatum           (crs.getDatum(), "Nouvelle Triangulation Francaise");
        verifyFlattenedSphere (crs.getDatum().getEllipsoid(), "Clarke 1880 (IGN)", 6378249.2, 293.4660213, SI.METRE);
        verifyPrimeMeridian   (crs.getDatum().getPrimeMeridian(), "Paris", 2.5969213, NonSI.GRADE);
        verifyCoordinateSystem(crs.getCoordinateSystem(), EllipsoidalCS.class, 2, NORTH_EAST_UP, NonSI.GRADE);
        assertNullOrEquals("remark", "Nouvelle Triangulation Française", crs.getRemarks());
    }

    /**
     * Parses a geodetic CRS with Cartesian coordinate system.
     * The WKT parsed by this test is (except for quote characters):
     *
     * <blockquote><pre>GEODETICCRS[“JGD2000”,
     *  DATUM[“Japanese Geodetic Datum 2000”,
     *    ELLIPSOID[“GRS 1980”, 6378137, 298.257222101]],
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
                "    ELLIPSOID[“GRS 1980”, 6378137, 298.257222101]],\n" +
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

        if (isValidationEnabled) {
            validators.validate(crs);
        }
        verifyIdentification   (crs, "JGD2000", "4946");
        verifyDatum            (crs.getDatum(), "Japanese Geodetic Datum 2000");
        verifyFlattenedSphere  (crs.getDatum().getEllipsoid(), "GRS 1980", 6378137, 298.257222101, SI.METRE);
        verifyPrimeMeridian    (crs.getDatum().getPrimeMeridian(), null, 0, NonSI.DEGREE_ANGLE);
        verifyCoordinateSystem (crs.getCoordinateSystem(), CartesianCS.class, 3, GEOCENTRIC, MMM_UNITS);
        verifyAxisAbbreviations(crs.getCoordinateSystem(), "X", "Y", "Z");
        verifyGeographicExtent (crs.getDomainOfValidity(), "Japan", 17.09, 122.38, 46.05, 157.64);
        verifyTimeExtent       (crs.getDomainOfValidity(), new Date(1017619200000L), new Date(1319155200000L), 1);
        assertNullOrEquals("scope", "Geodesy, topographic mapping and cadastre", crs.getScope());
        assertNullOrEquals("remark", "注：JGD2000ジオセントリックは現在JGD2011に代わりました。", crs.getRemarks());
    }

    /**
     * Parses a projected CRS with linear units in metres and axes in (<var>y</var>,<var>x</var>) order.
     * The WKT parsed by this test is (except for quote characters):
     *
     * <blockquote><pre>PROJCRS[“ETRS89 Lambert Azimuthal Equal Area CRS”,
     *  BASEGEODCRS[“ETRS89”,
     *    DATUM[“ETRS89”,
     *      ELLIPSOID[“GRS 80”, 6378137, 298.257222101, LENGTHUNIT[“metre”,1.0]]]],
     *  CONVERSION[“LAEA”,
     *    METHOD[“Lambert Azimuthal Equal Area”,ID[“EPSG”,9820]],
     *    PARAMETER[“Latitude of natural origin”,  52.0, ANGLEUNIT[“degree”,0.0174532925199433]],
     *    PARAMETER[“Longitude of natural origin”, 10.0, ANGLEUNIT[“degree”,0.0174532925199433]],
     *    PARAMETER[“False easting”,  4321000.0, LENGTHUNIT[“metre”,1.0]],
     *    PARAMETER[“False northing”, 3210000.0, LENGTHUNIT[“metre”,1.0]]],
     *  CS[Cartesian,2],
     *    AXIS[“(y)”,north,ORDER[1]],
     *    AXIS[“(x)”,east,ORDER[2]],
     *    LENGTHUNIT[“metre”,1.0],
     *  SCOPE[“Description of a purpose”],
     *  AREA[“An area description”],
     *  ID[“EuroGeographics”,“ETRS-LAEA”]]</pre></blockquote>
     *
     * @throws FactoryException if an error occurred during the WKT parsing.
     *
     * @see <a href="http://docs.opengeospatial.org/is/12-063r5/12-063r5.html#68">OGC 12-063r5 §9.5 example 1</a>
     */
    @Test
    public void testProjected() throws FactoryException {
        final ProjectedCRS crs = parse(ProjectedCRS.class,
                "PROJCRS[“ETRS89 Lambert Azimuthal Equal Area CRS”,\n" +
                "  BASEGEODCRS[“ETRS89”,\n" +
                "    DATUM[“ETRS89”,\n" +
                "      ELLIPSOID[“GRS 80”, 6378137, 298.257222101, LENGTHUNIT[“metre”,1.0]]]],\n" +
                "  CONVERSION[“LAEA”,\n" +
                "    METHOD[“Lambert Azimuthal Equal Area”,ID[“EPSG”,9820]],\n" +
                "    PARAMETER[“Latitude of natural origin”,  52.0, ANGLEUNIT[“degree”,0.0174532925199433]],\n" +
                "    PARAMETER[“Longitude of natural origin”, 10.0, ANGLEUNIT[“degree”,0.0174532925199433]],\n" +
                "    PARAMETER[“False easting”,  4321000.0, LENGTHUNIT[“metre”,1.0]],\n" +
                "    PARAMETER[“False northing”, 3210000.0, LENGTHUNIT[“metre”,1.0]]],\n" +
                "  CS[Cartesian,2],\n" +
                "    AXIS[“(y)”,north,ORDER[1]],\n" +
                "    AXIS[“(x)”,east,ORDER[2]],\n" +
                "    LENGTHUNIT[“metre”,1.0],\n" +
                "  SCOPE[“Description of a purpose”],\n" +
                "  AREA[“An area description”],\n" +
                "  ID[“EuroGeographics”,“ETRS-LAEA”]]");

        if (isValidationEnabled) {
            validators.validate(crs);
        }
        verifyIdentification   (crs, "ETRS89 Lambert Azimuthal Equal Area CRS", "ETRS-LAEA");
        verifyIdentification   (crs.getBaseCRS(), "ETRS89", null);
        verifyIdentification   (crs.getConversionFromBase(), "LAEA", null);
        verifyDatum            (crs.getDatum(), "ETRS89");
        verifyFlattenedSphere  (crs.getDatum().getEllipsoid(), "GRS 80", 6378137, 298.257222101, SI.METRE);
        verifyPrimeMeridian    (crs.getDatum().getPrimeMeridian(), null, 0, NonSI.DEGREE_ANGLE);
        verifyCoordinateSystem (crs.getCoordinateSystem(), CartesianCS.class, 2, NORTH_EAST_UP, SI.METRE);
        verifyAxisAbbreviations(crs.getCoordinateSystem(), "y", "x");

        final ParameterValueGroup group = crs.getConversionFromBase().getParameterValues();
        verifyParameter(group, "Latitude of natural origin",  52.0, NonSI.DEGREE_ANGLE);
        verifyParameter(group, "Longitude of natural origin", 10.0, NonSI.DEGREE_ANGLE);
        verifyParameter(group, "False easting",          4321000.0, SI.METRE);
        verifyParameter(group, "False northing",         3210000.0, SI.METRE);

        verifyGeographicExtent(crs.getDomainOfValidity(), "An area description", NaN, NaN, NaN, NaN);
        assertNullOrEquals("scope", "Description of a purpose", crs.getScope());
    }

    /**
     * Parses a projected CRS with linear units in feet.
     * The WKT parsed by this test is (except for quote characters):
     *
     * <blockquote><pre>PROJCRS[“NAD27 / Texas South Central”,
     *  BASEGEODCRS[“NAD27”,
     *    DATUM[“North American Datum 1927”,
     *      ELLIPSOID[“Clarke 1866”, 20925832.164, 294.97869821,
     *        LENGTHUNIT[“US survey foot”,0.304800609601219]]]],
     *  CONVERSION[“Texas South Central SPCS27”,
     *    METHOD[“Lambert Conic Conformal (2SP)”,ID[“EPSG”,9802]],
     *    PARAMETER[“Latitude of false origin”,27.83333333333333,
     *      ANGLEUNIT[“degree”,0.0174532925199433],ID[“EPSG”,8821]],
     *    PARAMETER[“Longitude of false origin”,-99.0,
     *      ANGLEUNIT[“degree”,0.0174532925199433],ID[“EPSG”,8822]],
     *    PARAMETER[“Latitude of 1st standard parallel”,28.383333333333,
     *      ANGLEUNIT[“degree”,0.0174532925199433],ID[“EPSG”,8823]],
     *    PARAMETER[“Latitude of 2nd standard parallel”,30.283333333333,
     *      ANGLEUNIT[“degree”,0.0174532925199433],ID[“EPSG”,8824]],
     *    PARAMETER[“Easting at false origin”,2000000.0,
     *      LENGTHUNIT[“US survey foot”,0.304800609601219],ID[“EPSG”,8826]],
     *    PARAMETER[“Northing at false origin”,0.0,
     *      LENGTHUNIT[“US survey foot”,0.304800609601219],ID[“EPSG”,8827]]],
     *  CS[Cartesian,2],
     *    AXIS[“(x)”,east],
     *    AXIS[“(y)”,north],
     *    LENGTHUNIT[“US survey foot”,0.304800609601219],
     *  REMARK[“Fundamental point: Meade’s Ranch KS, latitude 39°13'26.686"N, longitude 98°32'30.506"W.”]]</pre></blockquote>
     *
     * @throws FactoryException if an error occurred during the WKT parsing.
     *
     * @see <a href="http://docs.opengeospatial.org/is/12-063r5/12-063r5.html#68">OGC 12-063r5 §9.5 example 2</a>
     */
    @Test
    public void testProjectedWithFootUnits() throws FactoryException {
        final ProjectedCRS crs = parse(ProjectedCRS.class,
                "PROJCRS[“NAD27 / Texas South Central”,\n" +
                "  BASEGEODCRS[“NAD27”,\n" +
                "    DATUM[“North American Datum 1927”,\n" +
                "      ELLIPSOID[“Clarke 1866”, 20925832.164, 294.97869821,\n" +
                "        LENGTHUNIT[“US survey foot”,0.304800609601219]]]],\n" +
                "  CONVERSION[“Texas South Central SPCS27”,\n" +
                "    METHOD[“Lambert Conic Conformal (2SP)”,ID[“EPSG”,9802]],\n" +
                "    PARAMETER[“Latitude of false origin”,27.83333333333333,\n" +
                "      ANGLEUNIT[“degree”,0.0174532925199433],ID[“EPSG”,8821]],\n" +
                "    PARAMETER[“Longitude of false origin”,-99.0,\n" +
                "      ANGLEUNIT[“degree”,0.0174532925199433],ID[“EPSG”,8822]],\n" +
                "    PARAMETER[“Latitude of 1st standard parallel”,28.383333333333,\n" +
                "      ANGLEUNIT[“degree”,0.0174532925199433],ID[“EPSG”,8823]],\n" +
                "    PARAMETER[“Latitude of 2nd standard parallel”,30.283333333333,\n" +
                "      ANGLEUNIT[“degree”,0.0174532925199433],ID[“EPSG”,8824]],\n" +
                "    PARAMETER[“Easting at false origin”,2000000.0,\n" +
                "      LENGTHUNIT[“US survey foot”,0.304800609601219],ID[“EPSG”,8826]],\n" +
                "    PARAMETER[“Northing at false origin”,0.0,\n" +
                "      LENGTHUNIT[“US survey foot”,0.304800609601219],ID[“EPSG”,8827]]],\n" +
                "  CS[Cartesian,2],\n" +
                "    AXIS[“(x)”,east],\n" +
                "    AXIS[“(y)”,north],\n" +
                "    LENGTHUNIT[“US survey foot”,0.304800609601219],\n" +
                "  REMARK[“Fundamental point: Meade’s Ranch KS, latitude 39°13'26.686\"N, longitude 98°32'30.506\"W.”]]");

        if (isValidationEnabled) {
            validators.validate(crs);
        }
        verifyIdentification   (crs, "NAD27 / Texas South Central", null);
        verifyIdentification   (crs.getBaseCRS(), "NAD27", null);
        verifyIdentification   (crs.getConversionFromBase(), "Texas South Central SPCS27", null);
        verifyDatum            (crs.getDatum(), "North American Datum 1927");
        verifyFlattenedSphere  (crs.getDatum().getEllipsoid(), "Clarke 1866", 20925832.164, 294.97869821, NonSI.FOOT_SURVEY_US);
        verifyPrimeMeridian    (crs.getDatum().getPrimeMeridian(), null, 0, NonSI.DEGREE_ANGLE);
        verifyCoordinateSystem (crs.getCoordinateSystem(), CartesianCS.class, 2, EAST_NORTH_UP, NonSI.FOOT_SURVEY_US);
        verifyAxisAbbreviations(crs.getCoordinateSystem(), "x", "y");

        final ParameterValueGroup group = crs.getConversionFromBase().getParameterValues();
        verifyParameter(group, "Latitude of false origin",          27.83333333333333, NonSI.DEGREE_ANGLE);
        verifyParameter(group, "Longitude of false origin",        -99.0,              NonSI.DEGREE_ANGLE);
        verifyParameter(group, "Latitude of 1st standard parallel", 28.383333333333,   NonSI.DEGREE_ANGLE);
        verifyParameter(group, "Latitude of 2nd standard parallel", 30.283333333333,   NonSI.DEGREE_ANGLE);
        verifyParameter(group, "Easting at false origin",           2000000.0,         NonSI.FOOT_SURVEY_US);
        verifyParameter(group, "Northing at false origin",          0.0,               NonSI.FOOT_SURVEY_US);

        assertNullOrEquals("remark", "Fundamental point: Meade’s Ranch KS, latitude 39°13'26.686\"N, longitude 98°32'30.506\"W.", crs.getRemarks());
    }

    /**
     * Parses a projected CRS with implicit units.
     * The WKT parsed by this test is (except for quote characters and the line feed in {@code REMARK}):
     *
     * <blockquote><pre>PROJCRS[“NAD83 UTM 10”,
     *  BASEGEODCRS[“NAD83(86)”,
     *    DATUM[“North American Datum 1983”,
     *      ELLIPSOID[“GRS 1980”,6378137,298.257222101]],
     *    ANGLEUNIT[“degree”,0.0174532925199433]],
     *    PRIMEM[“Greenwich”,0],
     *  CONVERSION[“UTM zone 10N”,ID[“EPSG”,16010],
     *    METHOD[“Transverse Mercator”],
     *    PARAMETER[“Latitude of natural origin”,0.0],
     *    PARAMETER[“Longitude of natural origin”,-123.0],
     *    PARAMETER[“Scale factor”,0.9996],
     *    PARAMETER[“False easting”,500000.0],
     *    PARAMETER[“False northing”,0.0]],
     *  CS[Cartesian,2],
     *    AXIS[“(E)”,east,ORDER[1]],
     *    AXIS[“(N)”,north,ORDER[2]],
     *    LENGTHUNIT[“metre”,1.0],
     *  REMARK[“In this example units are implied. This is allowed for backward compatibility.
     *          It is recommended that units are explicitly given in the string,
     *          as in the previous two examples.”]]</pre></blockquote>
     *
     * @throws FactoryException if an error occurred during the WKT parsing.
     *
     * @see <a href="http://docs.opengeospatial.org/is/12-063r5/12-063r5.html#68">OGC 12-063r5 §9.5 example 3</a>
     */
    @Test
    public void testProjectedWithImplicitUnits() throws FactoryException {
        final ProjectedCRS crs = parse(ProjectedCRS.class,
                "PROJCRS[“NAD83 UTM 10”,\n" +
                "  BASEGEODCRS[“NAD83(86)”,\n" +
                "    DATUM[“North American Datum 1983”,\n" +
                "      ELLIPSOID[“GRS 1980”, 6378137, 298.257222101]],\n" +
                "    ANGLEUNIT[“degree”,0.0174532925199433]],\n" +
                "    PRIMEM[“Greenwich”,0],\n" +
                "  CONVERSION[“UTM zone 10N”,ID[“EPSG”,16010],\n" +
                "    METHOD[“Transverse Mercator”],\n" +
                "    PARAMETER[“Latitude of natural origin”,0.0],\n" +
                "    PARAMETER[“Longitude of natural origin”,-123.0],\n" +
                "    PARAMETER[“Scale factor”,0.9996],\n" +
                "    PARAMETER[“False easting”,500000.0],\n" +
                "    PARAMETER[“False northing”,0.0]],\n" +
                "  CS[Cartesian,2],\n" +
                "    AXIS[“(E)”,east,ORDER[1]],\n" +
                "    AXIS[“(N)”,north,ORDER[2]],\n" +
                "    LENGTHUNIT[“metre”,1.0],\n" +
                "  REMARK[“In this example units are implied. This is allowed for backward compatibility." +
                         " It is recommended that units are explicitly given in the string," +
                         " as in the previous two examples.”]]");

        if (isValidationEnabled) {
            validators.validate(crs);
        }
        verifyIdentification   (crs, "NAD83 UTM 10", null);
        verifyIdentification   (crs.getBaseCRS(), "NAD83(86)", null);
        verifyIdentification   (crs.getConversionFromBase(), "UTM zone 10N", "16010");
        verifyDatum            (crs.getDatum(), "North American Datum 1983");
        verifyFlattenedSphere  (crs.getDatum().getEllipsoid(), "GRS 1980", 6378137, 298.257222101, SI.METRE);
        verifyPrimeMeridian    (crs.getDatum().getPrimeMeridian(), null, 0, NonSI.DEGREE_ANGLE);
        verifyCoordinateSystem (crs.getCoordinateSystem(), CartesianCS.class, 2, EAST_NORTH_UP, SI.METRE);
        verifyAxisAbbreviations(crs.getCoordinateSystem(), "E", "N");

        final ParameterValueGroup group = crs.getConversionFromBase().getParameterValues();
        verifyParameter(group, "Latitude of natural origin",     0.0, NonSI.DEGREE_ANGLE);
        verifyParameter(group, "Longitude of natural origin", -123.0, NonSI.DEGREE_ANGLE);
        verifyParameter(group, "Scale factor",                0.9996, Unit.ONE);
        verifyParameter(group, "False easting",             500000.0, SI.METRE);
        verifyParameter(group, "False northing",                 0.0, SI.METRE);
    }
}
