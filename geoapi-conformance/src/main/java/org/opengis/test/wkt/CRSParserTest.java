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
import javax.measure.quantity.Length;
import javax.measure.converter.UnitConverter;
import javax.measure.converter.ConversionException;
import org.opengis.metadata.extent.*;
import org.opengis.metadata.Identifier;
import org.opengis.referencing.IdentifiedObject;
import org.opengis.referencing.crs.*;
import org.opengis.referencing.cs.*;
import org.opengis.referencing.datum.*;
import org.opengis.temporal.TemporalPrimitive;
import org.opengis.util.Factory;
import org.opengis.util.FactoryException;
import org.opengis.test.TestCase;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.Test;

import static java.lang.Double.NaN;
import static java.lang.Double.isNaN;
import static org.junit.Assume.assumeTrue;
import static org.opengis.test.Assert.*;


/**
 * Tests the Well-Known Text (WKT) parser of Coordinate Reference System (CRS) objects.
 * This tests use the {@link CRSFactory#createFromWKT(String)} method of the product to test.
 *
 * <p>In order to specify their factories and run the tests in a JUnit framework, implementors can
 * define a subclass as below:</p>
 *
 * <blockquote><pre>import org.junit.runner.RunWith;
 *import org.junit.runners.JUnit4;
 *import org.opengis.test.referencing.ObjectFactoryTest;
 *
 *&#64;RunWith(JUnit4.class)
 *public class MyTest extends CRSParserTest {
 *    public MyTest() {
 *        super(new MyCRSFactory());
 *    }
 *}</pre></blockquote>
 *
 * Alternatively this test class can also be used directly in the {@link org.opengis.test.TestSuite},
 * which combine every tests defined in the GeoAPI conformance module.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @see <a href="http://docs.opengeospatial.org/is/12-063r5/12-063r5.html">WKT 2 specification</a>
 */
@RunWith(Parameterized.class)
public strictfp class CRSParserTest extends TestCase {
    /**
     * An array of length 2 containing the North and East directions, in that order. This can be an argument
     * to calls to the {@link #assertCoordinateSystemEquals(Class, AxisDirection[], CoordinateSystem)} method.
     */
    private static final AxisDirection[] NORTH_EAST = {
        AxisDirection.NORTH,
        AxisDirection.EAST
    };

    /**
     * An array of length 3 containing the geocentric X, Y and Z directions, in that order. This can be an argument
     * to calls to the {@link #assertCoordinateSystemEquals(Class, AxisDirection[], CoordinateSystem)} method.
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
     * Asserts that the given object is not null and have the given name and (optionally) identifier.
     *
     * @param property   The property being tested, for producing a message in case of assertion failure.
     * @param name       The string representation of the expected name (ignoring code space).
     * @param identifier The expected identifier code, or {@code null} if none.
     * @param object     The object to verify.
     */
    private static void assertIdentificationEquals(final String property,
            final String name, final String identifier, final IdentifiedObject object)
    {
        assertNotNull(property, object);
        assertEquals(property + ".name.code", name, object.getName().getCode());
        if (identifier != null) {
            final String m = property + ".name.identifiers[i]";
            for (final Identifier id : object.getIdentifiers()) {
                assertNotNull(m, id);
                if (identifier.equalsIgnoreCase(id.getCode())) {
                    return;
                }
            }
            fail("Identifier “" + identifier + "” not found in " + property);
        }
    }

    /**
     * Asserts that the given ellipsoid has the given axis lengths.
     * If th given ellipsoid uses a different unit of measurement than the given {@code axisUnit},
     * then this method will convert the axis lengths to {@code axisUnit} before comparing them.
     *
     * @param semiMajor The expected semi-major axis length.
     * @param inverseFlattening The expected inverse flattening.
     * @param axisUnit The unit of {@code semiMajor}.
     * @param ellipsoid The ellipsoid to test.
     */
    private static void assertEllipsoidEquals(final double semiMajor, final double inverseFlattening,
            final Unit<Length> axisUnit, final Ellipsoid ellipsoid)
    {
        assertNotNull("Ellipsoid", ellipsoid);
        /*
         * Ellipsoid in the EPSG database have up to 9 digits for inverse flattening factor.
         * So we ask for a precision of 0.5 of the last digit.
         */
        assertEquals("inverseFlattening", inverseFlattening, ellipsoid.getInverseFlattening(), 5E-10);
        /*
         * Ellipsoid in the EPSG database have up to 3 digits for the semi-major axis length.
         * So we ask for a precision of 0.5 of the last digit.
         */
        final UnitConverter c = ellipsoid.getAxisUnit().getConverterTo(axisUnit);
        assertEquals("semiMajor", semiMajor, c.convert(ellipsoid.getSemiMajorAxis()), 5E-4);
    }

    /**
     * Asserts that the given coordinate system is of the given type and have the given axis directions.
     *
     * <p>This method does not verify axis names because the names specified by ISO 19111 and ISO 19162 differ.
     * An implementation could rename ISO 19162 axis names as ISO 19111 axis names after parsing.</p>
     *
     * @param type       The expected coordinate system type.
     * @param directions The expected axis directions.
     * @param units      The expected units. If longer than {@code directions}, extra elements are ignored.
     * @param cs         The coordinate system to verify.
     */
    private static void assertCoordinateSystemEquals(final Class<? extends CoordinateSystem> type,
            final AxisDirection[] directions, final Unit<?>[] units, final CoordinateSystem cs)
    {
        assertInstanceOf("CoordinateSystem", type, cs);
        assertEquals("CoordinateSystem.dimension", directions.length, cs.getDimension());
        for (int i=0; i<directions.length; i++) {
            final CoordinateSystemAxis axis = cs.getAxis(i);
            assertNotNull("CoordinateSystem.axis[i]", axis);
            assertEquals ("CoordinateSystem.axis[i].direction", directions[i], axis.getDirection());
            assertEquals ("CoordinateSystem.axis[i].unit",      units[i],      axis.getUnit());
        }
    }

    /**
     * Asserts that the extent is equals to the given area and bounding box.
     *
     * @param description        The expected area, or {@code null} if none.
     * @param southBoundLatitude The expected minimum latitude,  or NaN if none.
     * @param westBoundLongitude The expected minimum longitude, or NaN if none.
     * @param northBoundLatitude The expected maximum latitude,  or NaN if none.
     * @param eastBoundLongitude The expected maximum longitude, or NaN if none.
     * @param extent The extent to verify. Can be null since this is optional information.
     */
    private static void assertDomainOfValidityEquals(final String description,
            final double southBoundLatitude, final double westBoundLongitude,
            final double northBoundLatitude, final double eastBoundLongitude,
            final Extent extent)
    {
        if (extent != null) {
            String unknownArea = null;
            for (final GeographicExtent e : extent.getGeographicElements()) {
                /*
                 * WKT 2 specification said that BBOX precision should be about 0.01°.
                 */
                if (e instanceof GeographicBoundingBox) {
                    final GeographicBoundingBox bbox = (GeographicBoundingBox) e;
                    if (!isNaN(southBoundLatitude)) assertEquals("southBoundLatitude", southBoundLatitude, bbox.getSouthBoundLatitude(), 0.005);
                    if (!isNaN(westBoundLongitude)) assertEquals("westBoundLongitude", westBoundLongitude, bbox.getWestBoundLongitude(), 0.005);
                    if (!isNaN(northBoundLatitude)) assertEquals("northBoundLatitude", northBoundLatitude, bbox.getNorthBoundLatitude(), 0.005);
                    if (!isNaN(eastBoundLongitude)) assertEquals("eastBoundLongitude", eastBoundLongitude, bbox.getEastBoundLongitude(), 0.005);
                }
                /*
                 * Description: optional, but if present we allow any amount of identifiers
                 * provided that at least one contain the expected string.
                 */
                if (description != null && e instanceof GeographicDescription) {
                    final String area = ((GeographicDescription) e).getGeographicIdentifier().getCode();
                    if (description.equals(area)) {
                        unknownArea = null;
                        break;
                    }
                    if (unknownArea == null) {
                        unknownArea = area; // For reporting an error message if we do not find the expected area.
                    }
                }
            }
            if (unknownArea != null) {
                assertEquals("Area", description, unknownArea);
            }
        }
    }

    /**
     * Returns the given wrapper as a primitive value, or NaN if null.
     */
    private static double toPrimitive(final Double value) {
        return (value != null) ? value : NaN;
    }

    /**
     * Asserts that the vertical extent is equals to the given values.
     *
     * @param min    The minimal vertical value.
     * @param max    The maximal vertical value.
     * @param eps    The tolerance value.
     * @param unit   The unit of {@code zmin} and {@code zmax}, or {@code null} for skipping the unit check.
     * @param extent The extent to verify. Can be null since this is optional information.
     */
    private static void assertVerticalExtentEquals(final double min, final double max, final double eps,
            final Unit<?> unit, final Extent extent)
    {
        if (extent != null) {
            for (final VerticalExtent e : extent.getVerticalElements()) {
                double minValue = toPrimitive(e.getMinimumValue());
                double maxValue = toPrimitive(e.getMaximumValue());
                if (unit != null) {
                    final VerticalCRS crs = e.getVerticalCRS();
                    if (crs != null) {
                        final VerticalCS cs = crs.getCoordinateSystem();
                        if (cs != null) {
                            assertEquals("VerticalExtent.crs.cs.dimension", 1, cs.getDimension());
                            final CoordinateSystemAxis axis = cs.getAxis(0);
                            if (axis != null) {
                                final Unit<?> u = axis.getUnit();
                                if (u != null) {
                                    final UnitConverter c;
                                    try {
                                        c = u.getConverterToAny(unit);
                                    } catch (ConversionException ex) {
                                        throw (AssertionError) new AssertionError("Expected VerticalExtent in units of “"
                                                + unit + "” but got units of “" + u + "”.").initCause(ex);
                                    }
                                    minValue = c.convert(minValue);
                                    maxValue = c.convert(maxValue);
                                }
                            }
                        }
                    }
                }
                assertEquals("VerticalExtent.minimumValue", min, minValue, eps);
                assertEquals("VerticalExtent.maximumValue", max, maxValue, eps);
            }
        }
    }

    /**
     * Asserts that the time extent is equals to the given values.
     *
     * @param startTime The start time.
     * @param endTime   The end time.
     * @param extent    The extent to verify. Can be null since this is optional information.
     */
    private static void assertTimeExtentEquals(final Date startTime, final Date endTime, final Extent extent) {
        if (extent != null) {
            for (final TemporalExtent e : extent.getTemporalElements()) {
                // TODO: can not yet be tested, as it depends on temporal interfaces.
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
    private <T> T parse(final Class<T> type, final String text) throws FactoryException {
        assumeTrue("No CRSFactory.", factory != null);
        final Object obj = factory.createFromWKT(text);
        assertInstanceOf("CRSFactory.createFromWKT", type, obj);
        return type.cast(obj);
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

        assertIdentificationEquals("crs",   "S-95",         null, crs);
        assertIdentificationEquals("datum", "Pulkovo 1995", null, crs.getDatum());
        assertEllipsoidEquals(6378245, 298.3, SI.METRE, crs.getDatum().getEllipsoid());
        assertCoordinateSystemEquals(EllipsoidalCS.class, NORTH_EAST, DDM_UNITS, crs.getCoordinateSystem());
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

        assertIdentificationEquals("crs",   "JGD2000", "4946", crs);
        assertIdentificationEquals("datum", "Japanese Geodetic Datum 2000", null, crs.getDatum());
        assertEllipsoidEquals(6378137, 298.257222101, SI.METRE, crs.getDatum().getEllipsoid());
        assertCoordinateSystemEquals(CartesianCS.class, GEOCENTRIC, MMM_UNITS, crs.getCoordinateSystem());
        assertDomainOfValidityEquals("Japan", 17.09, 122.38, 46.05, 157.64, crs.getDomainOfValidity());
        assertTimeExtentEquals(new Date(1017619200000L), new Date(1319155200000L), crs.getDomainOfValidity());
        assertNullOrEquals("scope", "Geodesy, topographic mapping and cadastre", crs.getScope());
        assertNullOrEquals("remark", "注：JGD2000ジオセントリックは現在JGD2011に代わりました。", crs.getRemarks());
    }
}
