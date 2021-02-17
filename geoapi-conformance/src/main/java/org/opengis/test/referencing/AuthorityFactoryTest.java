/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2009-2019 Open Geospatial Consortium, Inc.
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

import java.util.Set;
import java.util.List;
import java.awt.geom.Rectangle2D;

import org.opengis.util.Factory;
import org.opengis.util.FactoryException;
import org.opengis.referencing.cs.*;
import org.opengis.referencing.crs.*;
import org.opengis.referencing.datum.*;
import org.opengis.referencing.IdentifiedObject;
import org.opengis.referencing.AuthorityFactory;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.operation.TransformException;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.Conversion;
import org.opengis.metadata.extent.Extent;
import org.opengis.metadata.extent.GeographicExtent;
import org.opengis.metadata.extent.GeographicBoundingBox;
import org.opengis.test.CalculationType;
import org.opengis.test.ToleranceModifier;
import org.opengis.test.Configuration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assume.*;
import static org.opengis.test.Assert.*;
import static org.opengis.test.Validator.DEFAULT_TOLERANCE;


/**
 * Tests the creation of referencing objects from the {@linkplain AuthorityFactory authority
 * factories} given at construction time.
 *
 * <p>Many {@link ProjectedCRS} instances tested in this class use the same projections than the
 * {@link MathTransform} instances tested in {@link ParameterizedTransformTest}. However the later
 * test class expects (λ,φ) input ordinates in degrees and (<var>x</var>,<var>y</var>)
 * output ordinates in metres, while this {@code AuthorityFactoryTest} class expects input and
 * output ordinates in CRS-dependent units and axis order.</p>
 *
 * <div class="note"><b>Usage example:</b>
 * in order to specify their factories and run the tests in a JUnit framework, implementers can
 * define a subclass in their own test suite as in the example below:
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
 * </div>
 *
 * @see ObjectFactoryTest
 * @see ParameterizedTransformTest
 * @see org.opengis.test.TestSuite
 *
 * @author  Cédric Briançon (Geomatys)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   2.3
 */
@RunWith(Parameterized.class)
public strictfp class AuthorityFactoryTest extends ReferencingTestCase {
    /**
     * Factory to use for building {@link CoordinateReferenceSystem} instances, or {@code null} if none.
     */
    protected final CRSAuthorityFactory crsAuthorityFactory;

    /**
     * Factory to use for building {@link CoordinateSystem} instances, or {@code null} if none.
     */
    protected final CSAuthorityFactory csAuthorityFactory;

    /**
     * Factory to use for building {@link Datum} instances, or {@code null} if none.
     */
    protected final DatumAuthorityFactory datumAuthorityFactory;

    /**
     * The identified object (typically a {@link CoordinateReferenceSystem}) being tested.
     * Every test methods in this class will set this field to a non-null value.
     * Implementers can use this value for their own assertions after any test has been run.
     *
     * @since 3.1
     */
    protected IdentifiedObject object;

    /**
     * {@code true} if the longitude and latitude axes shall be swapped. This flag applies
     * only to geographic coordinates.
     *
     * <p><b>Default value:</b> {@code true}, since the majority of {@link GeographicCRS}
     * defined in the EPSG database uses the (φλ) axis order.</p>
     *
     * @since 3.1
     */
    protected boolean swapλφ = true;

    /**
     * {@code true} if the easting and northing axes shall be swapped. This flag applies only
     * to projected coordinates.
     *
     * <p><b>Default value:</b> {@code false}, since the majority of {@link ProjectedCRS} defined
     * in the EPSG database uses the (<var>x</var>,<var>y</var>) axis order.</p>
     *
     * @since 3.1
     */
    protected boolean swapxy = false;

    /**
     * {@code true} if the sign of ordinate values shall be reversed in both projected axes.
     * This flag applies only to projected coordinates. This flag is set to {@code true} for
     * <cite>South Oriented</cite> {@link ProjectedCRS}.
     *
     * <p><b>Default value:</b> {@code false}.</p>
     *
     * @since 3.1
     */
    private boolean flipxy = false;

    /**
     * {@code true} if the {@linkplain CoordinateReferenceSystem Coordinate Reference System} being
     * tested is a polar CRS. Such CRS have axis orientation like <cite>"South along 90°E"</cite>
     * instead than {@linkplain AxisDirection#EAST East} or {@linkplain AxisDirection#NORTH North}.
     *
     * <p><b>Default value:</b> {@code false}.</p>
     *
     * @since 3.1
     */
    private boolean isPolar = false;

    /**
     * The expected prime meridian of the CRS being tested, in decimal degrees from Greenwich.
     *
     * <p><b>Default value:</b> {@code 0.0}.</p>
     *
     * @since 3.1
     */
    protected double primeMeridian = 0.0;

    /**
     * Conversion factor from degrees to the CRS-specific angular units. This value is different
     * than one when the latitude or longitude angles need to be converted from degrees before to
     * run a test.
     *
     * <p><b>Default value:</b> {@code 1.0}.</p>
     *
     * @since 3.1
     */
    protected double toAngularUnit = 1.0;

    /**
     * Conversion factor from metres to the CRS-specific linear units. This value is different
     * than one when the easting or northing values need to be converted from metres before to
     * run a test.
     *
     * <p><b>Default value:</b> {@code 1.0}.</p>
     *
     * @since 3.1
     */
    protected double toLinearUnit = 1.0;

    /**
     * {@code true} if {@link #crsAuthorityFactory} and {@link #csAuthorityFactory} supports the
     * creation of coordinate system with (<var>y</var>,<var>x</var>) axis order. If this field is
     * set to {@code false}, then the tests that would normally expect (<var>y</var>,<var>x</var>)
     * axis order or <cite>South Oriented</cite> CRS will rather use the (<var>x</var>,<var>y</var>)
     * axis order and <cite>North Oriented</cite> CRS in their test.
     *
     * @since 3.1
     */
    protected boolean isAxisSwappingSupported;

    /**
     * A helper class to use for running the tests. The {@link #runProjectionTest(int)} method
     * will use the following {@code ParameterizedTransformTest} package-private methods:
     *
     * <ul>
     *   <li>{@link ParameterizedTransformTest#verifyKnownSamplePoints}</li>
     *   <li>{@link ParameterizedTransformTest#verifyInDomainOfValidity}</li>
     * </ul>
     */
    private final ParameterizedTransformTest test;

    /**
     * Returns a default set of factories to use for running the tests. Those factories are given
     * in arguments to the constructor when this test class is instantiated directly by JUnit (for
     * example as a {@linkplain org.junit.runners.Suite.SuiteClasses suite} element), instead than
     * subclassed by the implementer. The factories are fetched as documented in the
     * {@link #factories(Class[])} javadoc.
     *
     * @return the default set of arguments to be given to the {@code AuthorityFactoryTest} constructor.
     *
     * @since 3.1
     */
    @Parameterized.Parameters
    @SuppressWarnings("unchecked")
    public static List<Factory[]> factories() {
        return factories(CRSAuthorityFactory.class, CSAuthorityFactory.class, DatumAuthorityFactory.class);
    }

    /**
     * Creates a new test using the given factories. If a given factory is {@code null},
     * then the tests which depend on it will be skipped.
     *
     * @param crsFactory    factory for creating {@link CoordinateReferenceSystem} instances.
     * @param csFactory     factory for creating {@link CoordinateSystem} instances.
     * @param datumFactory  factory for creating {@link Datum} instances.
     */
    public AuthorityFactoryTest(final CRSAuthorityFactory crsFactory,
            final CSAuthorityFactory csFactory, final DatumAuthorityFactory datumFactory)
    {
        super(crsFactory, csFactory, datumFactory);
        crsAuthorityFactory   = crsFactory;
        csAuthorityFactory    = csFactory;
        datumAuthorityFactory = datumFactory;
        final Configuration.Key<Boolean>[] keys = ParameterizedTransformTest.getEnabledKeys(1);
        final int offset = keys.length - 1;                     // First free slot for our keys.
        keys[offset] = Configuration.Key.isAxisSwappingSupported;
        final boolean[] isEnabled = getEnabledFlags(keys);
        test = new ParameterizedTransformTest(isEnabled);
        isAxisSwappingSupported = isEnabled[offset];
    }

    /**
     * Returns information about the configuration of the test which has been run.
     * This method returns a map containing:
     *
     * <ul>
     *   <li>All the entries defined in the {@link ParameterizedTransformTest#configuration()
     *       ParameterizedTransformTest} class except {@code mtFactory}.</li>
     *   <li>All the following values associated to the {@link org.opengis.test.Configuration.Key} of the same name:
     *     <ul>
     *       <li>{@link #isAxisSwappingSupported}</li>
     *       <li>{@link #crsAuthorityFactory}</li>
     *       <li>{@link #csAuthorityFactory}</li>
     *       <li>{@link #datumAuthorityFactory}</li>
     *     </ul>
     *   </li>
     * </ul>
     *
     * @return the configuration of the test being run, or an empty map if none.
     *
     * @since 3.1
     */
    @Override
    public Configuration configuration() {
        final Configuration op = test.configuration();
        assertNull(op.remove(Configuration.Key.mtFactory));
        assertNull(op.put(Configuration.Key.isAxisSwappingSupported, isAxisSwappingSupported));
        assertNull(op.put(Configuration.Key.crsAuthorityFactory,     crsAuthorityFactory));
        assertNull(op.put(Configuration.Key.csAuthorityFactory,      csAuthorityFactory));
        assertNull(op.put(Configuration.Key.datumAuthorityFactory,   datumAuthorityFactory));
        return op;
    }

    /**
     * Returns the longitude value relative to the Greenwich Meridian, expressed in decimal degrees.
     *
     * @param  pm  the prime meridian from which to get the Greenwich longitude, or {@code null}.
     * @return the prime meridian in the given units, or 0 if the given prime meridian was null.
     */
    private double getGreenwichLongitude(final PrimeMeridian pm) {
        return (pm != null) ? pm.getAngularUnit().getConverterTo(units.degree()).convert(pm.getGreenwichLongitude()) : 0;
    }

    /**
     * Tests the creation of the EPSG:4326 {@link GeographicCRS}.
     *
     * @throws NoSuchAuthorityCodeException if the specified code is not found among the ones present in the database.
     * @throws FactoryException if the creation of the {@link CoordinateReferenceSystem} failed for an other raison.
     */
    @Test
    public void testWGS84() throws NoSuchAuthorityCodeException, FactoryException {
        assumeNotNull(crsAuthorityFactory);
        final GeographicCRS crs = crsAuthorityFactory.createGeographicCRS("EPSG:4326");
        assertNotNull("CRSAuthorityFactory.createGeographicCRS()", crs);
        object = crs;
        validators.validate(crs);
        verifyIdentification(crs, "WGS 84", null);
        /*
         * Coordinate system validation. In theory, the coordinate system is mandatory.
         * This is verified by the above call to validate(crs). However the user could
         * have set the Validator.requireMandatoryAttributes to 'false', in which case
         * we need to be lenient as the user wish.
         */
        final EllipsoidalCS cs = crs.getCoordinateSystem();
        if (cs != null) {
            verifyCoordinateSystem(cs, EllipsoidalCS.class,
                    new AxisDirection[] {
                        AxisDirection.NORTH,
                        AxisDirection.EAST
                    }, units.degree());
            verifyIdentification(cs.getAxis(0), "Geodetic latitude", null);
            verifyIdentification(cs.getAxis(1), "Geodetic longitude", null);
        }
        /*
         * Datum validation. Same rational about 'null' value as for the coordinate system.
         */
        final GeodeticDatum datum = crs.getDatum();
        if (datum != null) {
            verifyIdentification(datum, "World Geodetic System 1984", null);
            verifyPrimeMeridian(datum.getPrimeMeridian(), "Greenwich", 0.0, units.degree());
        }
    }

    /**
     * Verifies the horizontal axis direction of the given coordinate system. The standard
     * directions are (East,North), but the boolean argument allows to swap and flip those
     * directions.
     *
     * @param cs    the coordinate system to check, or {@code null}.
     * @param swap  {@code true} if the the easting and northing axes should be interchanged.
     * @param flip  {@code true} if the sign of both axes should be reversed.
     */
    private static void verifyAxisDirection(final String message, final CoordinateSystem cs,
            final boolean swap, final boolean flip)
    {
        if (cs != null) {
            AxisDirection X,Y;
            if (flip) {
                X = AxisDirection.WEST;
                Y = AxisDirection.SOUTH;
            } else {
                X = AxisDirection.EAST;
                Y = AxisDirection.NORTH;
            }
            if (swap) {
                final AxisDirection t = X;
                X=Y; Y=t;
            }
            assertAxisDirectionsEqual(message, cs, X, Y);
        }
    }

    /**
     * Creates a {@link ProjectedCRS} identified by the given EPSG code, and tests its
     * math transform. The set of allowed codes is documented in second column of the
     * {@link PseudoEpsgFactory#createParameters(int)} method.
     *
     * @param  code  the EPSG code of a target Coordinate Reference System.
     * @throws FactoryException if the math transform can not be created.
     * @throws TransformException if a point can not be transformed.
     */
    private void runProjectionTest(final int code) throws FactoryException, TransformException {
        if (!isAxisSwappingSupported) {
            swapλφ = swapxy = flipxy = false;
        }
        assumeNotNull(crsAuthorityFactory);
        final ProjectedCRS crs;
        try {
            crs = crsAuthorityFactory.createProjectedCRS("EPSG:" + code);
        } catch (NoSuchAuthorityCodeException e) {
            /*
             * If a code was not found, ensure that the factory does not declare that it was
             * a supported code. If the code was unsupported, then the test will be ignored.
             */
            final Set<String> authorityCodes = crsAuthorityFactory.getAuthorityCodes(ProjectedCRS.class);
            if (authorityCodes == null || !authorityCodes.contains(String.valueOf(code))) {
                assumeNoException(e);               // Will mark the test as "ignored".
            }
            throw e;                                // Will mark the test as "failed".
        }
        assertNotNull("CRSAuthorityFactory.createProjectedCRS()", crs);
        object = crs;
        validators.validate(crs);
        /*
         * Coordinate system validation. In theory, the coordinate system is mandatory.
         * This is verified by the above call to validate(crs). However the user could
         * have set the Validator.requireMandatoryAttributes to 'false', in which case
         * we need to be lenient as the user wishes.
         */
        final GeographicCRS baseCRS = crs.getBaseCRS();
        if (baseCRS != null) {
            verifyAxisDirection("BaseCRS", baseCRS.getCoordinateSystem(), swapλφ, false);
            final GeodeticDatum datum = baseCRS.getDatum();
            if (datum != null) {
                assertEquals("PrimeMeridian.greenwichLongitude", primeMeridian,
                        getGreenwichLongitude(datum.getPrimeMeridian()), DEFAULT_TOLERANCE);
            }
        }
        /*
         * Verifies axis direction only if the CRS is not polar. Polar CRS has unusual
         * axis directions like "South along 90°E". Since there is no GeoAPI code list
         * for such direction, we consider them as implementation-dependent.
         */
        if (!isPolar) {
            verifyAxisDirection("ProjectedCRS", crs.getCoordinateSystem(), swapxy, flipxy);
        }
        /*
         * Test the projection of sample point values.
         */
        final Conversion conversion = crs.getConversionFromBase();
        if (conversion != null) {
            final MathTransform projection = conversion.getMathTransform();
            if (projection != null) {
                test.description = Utilities.getName(crs);
                test.transform = projection;
                validators.validate(projection);
                /*
                 * Get the sample points and swap ordinate values if needed.
                 * Finally apply a unit conversion if the CRS doesn't use the usual units.
                 */
                final SamplePoints sample = SamplePoints.forCRS(code);
                if (primeMeridian != 0) sample.rotateLongitude(primeMeridian);
                if (swapλφ) SamplePoints.swap(sample.sourcePoints);
                if (swapxy) SamplePoints.swap(sample.targetPoints);
                if (flipxy) SamplePoints.flip(sample.targetPoints);
                test.setTolerance(swapλφ ? ToleranceModifier.PROJECTION_FROM_φλ : ToleranceModifier.PROJECTION);
                if (toLinearUnit  != 1) test.applyUnitConversion(CalculationType.DIRECT_TRANSFORM,  sample.targetPoints, toLinearUnit);
                if (toAngularUnit != 1) test.applyUnitConversion(CalculationType.INVERSE_TRANSFORM, sample.sourcePoints, toAngularUnit);
                test.verifyTransform(sample.sourcePoints, sample.targetPoints);
                /*
                 * Tests random points in every domains of validity declared in the CRS.
                 * If the CRS does not declare any domain of validity, then we will use
                 * the one which is hard-coded in the SamplePoints class.
                 */
                boolean tested = false;
                final Rectangle2D areaOfValidity = sample.areaOfValidity;
                double λmin = areaOfValidity.getMinX();
                double λmax = areaOfValidity.getMaxX();
                double φmin = areaOfValidity.getMinY();
                double φmax = areaOfValidity.getMaxY();
                final Extent extent = crs.getDomainOfValidity();
                validators.validate(extent);
                if (extent != null) {
                    for (final GeographicExtent element : extent.getGeographicElements()) {
                        if (element instanceof GeographicBoundingBox && Boolean.TRUE.equals(element.getInclusion())) {
                            final GeographicBoundingBox bbox = (GeographicBoundingBox) element;
                            λmin = bbox.getWestBoundLongitude();
                            λmax = bbox.getEastBoundLongitude();
                            φmin = bbox.getSouthBoundLatitude();
                            φmax = bbox.getNorthBoundLatitude();
                            setRect(areaOfValidity, λmin, φmin, λmax, φmax, swapλφ, toAngularUnit);
                            assertFalse("Empty geographic bounding box.", areaOfValidity.isEmpty());
                            test.verifyInDomainOfValidity(areaOfValidity);
                            tested = true;
                        }
                    }
                }
                if (!tested) {
                    setRect(areaOfValidity, λmin, φmin, λmax, φmax, swapλφ, toAngularUnit);
                    test.verifyInDomainOfValidity(areaOfValidity);
                }
            }
        }
    }

    /**
     * Sets the area of validity, swapping axis and converting units if necessary.
     */
    private static void setRect(final Rectangle2D areaOfValidity,
            double λmin, double φmin, double λmax, double φmax,
            final boolean swapλφ, final double toAngularUnit)
    {
        if (λmax < λmin) {                      // Domain crosses anti-meridian.
            if (180 + λmax < 180 - λmin) {      // Which bound is closest to anti-meridian?
                λmax += 360;                    // Example: -175° → +185°
            } else {
                λmin -= 360;                    // Example: +175° → -185°
            }
        }
        λmin *= toAngularUnit;
        λmax *= toAngularUnit;
        φmin *= toAngularUnit;
        φmax *= toAngularUnit;
        if (swapλφ) {
            double t;
            t=λmin; λmin=φmin; φmin=t;
            t=λmax; λmax=φmax; φmax=t;
        }
        areaOfValidity.setRect(λmin, φmin, λmax-λmin, φmax-φmin);
    }

    /**
     * Tests the EPSG:3002 (<cite>Makassar / NEIEZ</cite>) projected CRS.
     *
     * <table class="ogc">
     * <caption>CRS characteristics</caption>
     * <tr><td>Projection method:</td> <td>Mercator (variant A)</td></tr>
     * <tr><td>Prime meridian:</td>    <td>Greenwich</td></tr>
     * <tr><td>Source ordinates:</td>  <td>(φ,λ) in degrees</td></tr>
     * <tr><td>Output ordinates:</td>  <td>(<var>x</var>,<var>y</var>) in metres</td></tr>
     * </table>
     *
     * @throws FactoryException if the math transform can not be created.
     * @throws TransformException if the example point can not be transformed.
     *
     * @see ParameterizedTransformTest#testMercator1SP()
     */
    @Test
    public void testEPSG_3002() throws FactoryException, TransformException {
        runProjectionTest(3002);
    }

    /**
     * Tests the EPSG:3388 (<cite>Pulkovo 1942 / Caspian Sea Mercator</cite>) projected CRS.
     *
     * <table class="ogc">
     * <caption>CRS characteristics</caption>
     * <tr><td>Projection method:</td> <td>Mercator (variant B)</td></tr>
     * <tr><td>Prime meridian:</td>    <td>Greenwich</td></tr>
     * <tr><td>Source ordinates:</td>  <td>(φ,λ) in degrees</td></tr>
     * <tr><td>Output ordinates:</td>  <td>(<var>y</var>,<var>x</var>) in metres - <strong>note the axis order!</strong></td></tr>
     * </table>
     *
     * @throws FactoryException if the math transform can not be created.
     * @throws TransformException if the example point can not be transformed.
     *
     * @see ParameterizedTransformTest#testMercator2SP()
     */
    @Test
    public void testEPSG_3388() throws FactoryException, TransformException {
        swapxy = true;
        runProjectionTest(3388);
    }

    /**
     * Tests the EPSG:3857 (<cite>WGS 84 / Pseudo-Mercator</cite>) projected CRS.
     *
     * <table class="ogc">
     * <caption>CRS characteristics</caption>
     * <tr><td>Projection method:</td> <td>Mercator Popular Visualisation Pseudo Mercator</td></tr>
     * <tr><td>Prime meridian:</td>    <td>Greenwich</td></tr>
     * <tr><td>Source ordinates:</td>  <td>(φ,λ) in degrees</td></tr>
     * <tr><td>Output ordinates:</td>  <td>(<var>x</var>,<var>y</var>) in metres</td></tr>
     * </table>
     *
     * @throws FactoryException if the math transform can not be created.
     * @throws TransformException if the example point can not be transformed.
     *
     * @see ParameterizedTransformTest#testPseudoMercator()
     */
    @Test
    public void testEPSG_3857() throws FactoryException, TransformException {
        runProjectionTest(3857);
    }

    /**
     * Tests the EPSG:29873 (<cite>Timbalai 1948 / RSO Borneo (m)</cite>) projected CRS.
     *
     * <table class="ogc">
     * <caption>CRS characteristics</caption>
     * <tr><td>Projection method:</td> <td>Hotine Oblique Mercator (variant B)</td></tr>
     * <tr><td>Prime meridian:</td>    <td>Greenwich</td></tr>
     * <tr><td>Source ordinates:</td>  <td>(φ,λ) in degrees</td></tr>
     * <tr><td>Output ordinates:</td>  <td>(<var>x</var>,<var>y</var>) in metres</td></tr>
     * </table>
     *
     * @throws FactoryException if the math transform can not be created.
     * @throws TransformException if the example point can not be transformed.
     *
     * @see ParameterizedTransformTest#testHotineObliqueMercator()
     */
    @Test
    public void testEPSG_29873() throws FactoryException, TransformException {
        runProjectionTest(29873);
    }

    /**
     * Tests the EPSG:27700 (<cite>OSGB 1936 / British National Grid</cite>) projected CRS.
     *
     * <table class="ogc">
     * <caption>CRS characteristics</caption>
     * <tr><td>Projection method:</td> <td>Transverse Mercator</td></tr>
     * <tr><td>Prime meridian:</td>    <td>Greenwich</td></tr>
     * <tr><td>Source ordinates:</td>  <td>(φ,λ) in degrees</td></tr>
     * <tr><td>Output ordinates:</td>  <td>(<var>x</var>,<var>y</var>) in metres</td></tr>
     * </table>
     *
     * @throws FactoryException if the math transform can not be created.
     * @throws TransformException if the example point can not be transformed.
     *
     * @see ParameterizedTransformTest#testTransverseMercator()
     */
    @Test
    public void testEPSG_27700() throws FactoryException, TransformException {
        runProjectionTest(27700);
    }

    /**
     * Tests the EPSG:2314 (<cite>Trinidad 1903 / Trinidad Grid</cite>) projected CRS.
     *
     * <table class="ogc">
     * <caption>CRS characteristics</caption>
     * <tr><td>Projection method:</td> <td>Cassini-Soldner</td></tr>
     * <tr><td>Prime meridian:</td>    <td>Greenwich</td></tr>
     * <tr><td>Source ordinates:</td>  <td>(φ,λ) in degrees</td></tr>
     * <tr><td>Output ordinates:</td>  <td>(<var>x</var>,<var>y</var>) in Clarke's foot - <strong>note the units!</strong></td></tr>
     * </table>
     *
     * @throws FactoryException if the math transform can not be created.
     * @throws TransformException if the example point can not be transformed.
     *
     * @see ParameterizedTransformTest#testCassiniSoldner()
     */
    @Test
    public void testEPSG_2314() throws FactoryException, TransformException {
        toLinearUnit = 1/PseudoEpsgFactory.CLARKE_FEET;
        /*
         * Relax the tolerance threshold because the sample point in IOGP Publication 373-7-2 §3.2.2 — September 2019
         * has been computed for a CRS defined with slightly different numbers than in EPSG:9.8.11:2314 definition.
         * The differences are in units of measurement used for defining axis lengths and false easting/northing.
         */
        if (test.tolerance < 0.8) {
            test.tolerance = 0.8;
        }
        runProjectionTest(2314);
    }

    /**
     * Tests the EPSG:3139 (<cite>Vanua Levu 1915 / Vanua Levu Grid</cite>) projected CRS.
     *
     * <table class="ogc">
     * <caption>CRS characteristics</caption>
     * <tr><td>Projection method:</td> <td>Hyperbolic Cassini-Soldner</td></tr>
     * <tr><td>Prime meridian:</td>    <td>Greenwich</td></tr>
     * <tr><td>Source ordinates:</td>  <td>(φ,λ) in degrees</td></tr>
     * <tr><td>Output ordinates:</td>  <td>(<var>x</var>,<var>y</var>) in links - <strong>note the units!</strong></td></tr>
     * </table>
     *
     * @throws FactoryException if the math transform can not be created.
     * @throws TransformException if the example point can not be transformed.
     *
     * @see ParameterizedTransformTest#testHyperbolicCassiniSoldner()
     */
    @Test
    public void testEPSG_3139() throws FactoryException, TransformException {
        toLinearUnit = 1/PseudoEpsgFactory.LINKS;
        swapxy = true;
        runProjectionTest(3139);
    }

    /**
     * Tests the EPSG:24200 (<cite>JAD69 / Jamaica National Grid</cite>) projected CRS.
     *
     * <table class="ogc">
     * <caption>CRS characteristics</caption>
     * <tr><td>Projection method:</td> <td>Lambert Conic Conformal (1SP)</td></tr>
     * <tr><td>Prime meridian:</td>    <td>Greenwich</td></tr>
     * <tr><td>Source ordinates:</td>  <td>(φ,λ) in degrees</td></tr>
     * <tr><td>Output ordinates:</td>  <td>(<var>x</var>,<var>y</var>) in metres</td></tr>
     * </table>
     *
     * @throws FactoryException if the math transform can not be created.
     * @throws TransformException if the example point can not be transformed.
     *
     * @see ParameterizedTransformTest#testLambertConicConformal1SP()
     */
    @Test
    public void testEPSG_24200() throws FactoryException, TransformException {
        runProjectionTest(24200);
    }

    /**
     * Tests the EPSG:32040 (<cite>NAD27 / Texas South Central</cite>) projected CRS.
     *
     * <table class="ogc">
     * <caption>CRS characteristics</caption>
     * <tr><td>Projection method:</td> <td>Lambert Conic Conformal (2SP)</td></tr>
     * <tr><td>Prime meridian:</td>    <td>Greenwich</td></tr>
     * <tr><td>Source ordinates:</td>  <td>(φ,λ) in degrees</td></tr>
     * <tr><td>Output ordinates:</td>  <td>(<var>x</var>,<var>y</var>) in US feet - <strong>note the units!</strong></td></tr>
     * </table>
     *
     * @throws FactoryException if the math transform can not be created.
     * @throws TransformException if the example point can not be transformed.
     *
     * @see ParameterizedTransformTest#testLambertConicConformal2SP()
     */
    @Test
    public void testEPSG_32040() throws FactoryException, TransformException {
        toLinearUnit = PseudoEpsgFactory.R_US_FEET;
        runProjectionTest(32040);
    }

    /**
     * Tests the EPSG:31300 (<cite>Belge 1972 / Belge Lambert 72</cite>) projected CRS.
     *
     * <table class="ogc">
     * <caption>CRS characteristics</caption>
     * <tr><td>Projection method:</td> <td>Lambert Conic Conformal (2SP Belgium)</td></tr>
     * <tr><td>Prime meridian:</td>    <td>Greenwich</td></tr>
     * <tr><td>Source ordinates:</td>  <td>(φ,λ) in degrees</td></tr>
     * <tr><td>Output ordinates:</td>  <td>(<var>x</var>,<var>y</var>) in metres</td></tr>
     * </table>
     *
     * @throws FactoryException if the math transform can not be created.
     * @throws TransformException if the example point can not be transformed.
     *
     * @see ParameterizedTransformTest#testLambertConicConformalBelgium()
     */
    @Test
    public void testEPSG_31300() throws FactoryException, TransformException {
        runProjectionTest(31300);
    }

    /**
     * Tests the EPSG:3035 (<cite>ETRS89 / LAEA Europe</cite>) projected CRS.
     *
     * <table class="ogc">
     * <caption>CRS characteristics</caption>
     * <tr><td>Projection method:</td> <td>Lambert Azimuthal Equal Area</td></tr>
     * <tr><td>Prime meridian:</td>    <td>Greenwich</td></tr>
     * <tr><td>Source ordinates:</td>  <td>(φ,λ) in degrees</td></tr>
     * <tr><td>Output ordinates:</td>  <td>(<var>y</var>,<var>x</var>) in metres - <strong>note the axis order!</strong></td></tr>
     * </table>
     *
     * @throws FactoryException if the math transform can not be created.
     * @throws TransformException if the example point can not be transformed.
     *
     * @see ParameterizedTransformTest#testLambertAzimuthalEqualArea()
     */
    @Test
    public void testEPSG_3035() throws FactoryException, TransformException {
        swapxy = true;
        runProjectionTest(3035);
    }

    /**
     * Tests the EPSG:5041 (<cite>WGS 84 / UPS North (E,N)</cite>) projected CRS.
     *
     * <table class="ogc">
     * <caption>CRS characteristics</caption>
     * <tr><td>Projection method:</td> <td>Polar Stereographic (variant A)</td></tr>
     * <tr><td>Prime meridian:</td>    <td>Greenwich</td></tr>
     * <tr><td>Source ordinates:</td>  <td>(φ,λ) in degrees</td></tr>
     * <tr><td>Output ordinates:</td>  <td>(<var>x</var>,<var>y</var>) in metres</td></tr>
     * </table>
     *
     * @throws FactoryException if the math transform can not be created.
     * @throws TransformException if the example point can not be transformed.
     *
     * @see ParameterizedTransformTest#testPolarStereographicA()
     */
    @Test
    public void testEPSG_5041() throws FactoryException, TransformException {
        isPolar = true;
        runProjectionTest(5041);
    }

    /**
     * Tests the EPSG:32661 (<cite>WGS 84 / UPS North (N,E)</cite>) projected CRS.
     * This CRS is identical to EPSG:5041 except for axis order.
     *
     * <table class="ogc">
     * <caption>CRS characteristics</caption>
     * <tr><td>Projection method:</td> <td>Polar Stereographic (variant A)</td></tr>
     * <tr><td>Prime meridian:</td>    <td>Greenwich</td></tr>
     * <tr><td>Source ordinates:</td>  <td>(φ,λ) in degrees</td></tr>
     * <tr><td>Output ordinates:</td>  <td>(<var>y</var>,<var>x</var>) in metres - <strong>note the axis order!</strong></td></tr>
     * </table>
     *
     * @throws FactoryException if the math transform can not be created.
     * @throws TransformException if the example point can not be transformed.
     *
     * @see ParameterizedTransformTest#testPolarStereographicA()
     */
    @Test
    public void testEPSG_32661() throws FactoryException, TransformException {
        isPolar = true;
        swapxy  = true;
        runProjectionTest(32661);
    }

    /**
     * Tests the EPSG:3032 (<cite>WGS 84 / Australian Antarctic Polar Stereographic</cite>) projected CRS.
     *
     * <table class="ogc">
     * <caption>CRS characteristics</caption>
     * <tr><td>Projection method:</td> <td>Polar Stereographic (variant B)</td></tr>
     * <tr><td>Prime meridian:</td>    <td>Greenwich</td></tr>
     * <tr><td>Source ordinates:</td>  <td>(φ,λ) in degrees</td></tr>
     * <tr><td>Output ordinates:</td>  <td>(<var>x</var>,<var>y</var>) in metres</td></tr>
     * </table>
     *
     * @throws FactoryException if the math transform can not be created.
     * @throws TransformException if the example point can not be transformed.
     *
     * @see ParameterizedTransformTest#testPolarStereographicB()
     */
    @Test
    public void testEPSG_3032() throws FactoryException, TransformException {
        isPolar = true;
        runProjectionTest(3032);
    }

    /**
     * Tests the EPSG:28992 (<cite>Amersfoort / RD New</cite>) projected CRS.
     *
     * <table class="ogc">
     * <caption>CRS characteristics</caption>
     * <tr><td>Projection method:</td> <td>Oblique Stereographic</td></tr>
     * <tr><td>Prime meridian:</td>    <td>Greenwich</td></tr>
     * <tr><td>Source ordinates:</td>  <td>(φ,λ) in degrees</td></tr>
     * <tr><td>Output ordinates:</td>  <td>(<var>x</var>,<var>y</var>) in metres</td></tr>
     * </table>
     *
     * @throws FactoryException if the math transform can not be created.
     * @throws TransformException if the example point can not be transformed.
     *
     * @see ParameterizedTransformTest#testObliqueStereographic()
     */
    @Test
    public void testEPSG_28992() throws FactoryException, TransformException {
        runProjectionTest(28992);
    }

    /**
     * Tests the EPSG:2065 (<cite>CRS S-JTSK (Ferro) / Krovak</cite>) projected CRS.
     *
     * <table class="ogc">
     * <caption>CRS characteristics</caption>
     * <tr><td>Projection method:</td> <td>Krovak</td></tr>
     * <tr><td>Prime meridian:</td>    <td>Ferro <strong>(17°40'W from Greenwich)</strong></td></tr>
     * <tr><td>Source ordinates:</td>  <td>(φ,λ) in degrees</td></tr>
     * <tr><td>Output ordinates:</td>  <td>(<var>y</var>,<var>x</var>) in metres, <strong>south oriented (S,W)</strong></td></tr>
     * </table>
     *
     * @throws FactoryException if the math transform can not be created.
     * @throws TransformException if the example point can not be transformed.
     *
     * @see ParameterizedTransformTest#testKrovak()
     */
    @Test
    public void testEPSG_2065() throws FactoryException, TransformException {
        swapxy = true;
        flipxy = true;
        primeMeridian = -(17 + 40.0/60);
        runProjectionTest(2065);
    }
}
