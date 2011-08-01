/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011 Open Geospatial Consortium, Inc.
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
import java.util.Arrays;
import java.util.Random;
import java.awt.geom.Rectangle2D;

import org.opengis.util.Factory;
import org.opengis.util.FactoryException;
import org.opengis.geometry.DirectPosition;
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.referencing.crs.ProjectedCRS;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;
import org.opengis.referencing.operation.MathTransformFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static java.lang.StrictMath.*;
import static org.junit.Assume.*;
import static org.opengis.test.Validators.*;


/**
 * Tests {@linkplain MathTransform} from the {@code org.opengis.referencing.operation} package.
 * Math transform instances are created using the factory given at construction time.
 *
 * In order to specify their factory and run the tests in a JUnit framework, implementors can
 * define a subclass as below:
 *
 * <blockquote><pre>import org.junit.runner.RunWith;
 *import org.junit.runners.JUnit4;
 *import org.opengis.test.referencing.MathTransformTest;
 *
 *&#64;RunWith(JUnit4.class)
 *public class MyTest extends MathTransformTest {
 *    public MyTest() {
 *        super(new MyMathTransformFactory());
 *    }
 *}</pre></blockquote>
 *
 * Alternatively this test class can also be used directly in the {@link org.opengis.test.TestSuite},
 * which combine every tests defined in the GeoAPI conformance module.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@RunWith(Parameterized.class)
public strictfp class MathTransformTest extends TransformTestCase {
    /**
     * The factory for creating {@link MathTransform} objects, or {@code null} if none.
     */
    protected final MathTransformFactory factory;

    /**
     * {@code true} if the {@linkplain #transform} being tested is a map projection
     * from a geographic CRS to a projected CRS. This flag shall be set together
     * with the {@link #tolerance} threshold before the {@code verify(...)} methods
     * are invoked.
     */
    private transient boolean isProjection;

    /**
     * {@code true} if the longitude (which is assumed to be the first ordinate)
     * should be ignored. This can be the case only if the latitude is at a pole,
     * in which case the longitude has no meaning.
     */
    private transient boolean isAtPole;

    /**
     * Returns a default set of factories to use for running the tests. Those factories are given
     * in arguments to the constructor when this test class is instantiated directly by JUnit (for
     * example as a {@linkplain org.junit.runners.Suite.SuiteClasses suite} element), instead than
     * subclassed by the implementor. The factories are fetched as documented in the
     * {@link #factories(Class[])} javadoc.
     *
     * @return The default set of arguments to be given to the {@code MathTransformTest} constructor.
     */
    @Parameterized.Parameters
    public static List<Factory[]> factories() {
        return factories(MathTransformFactory.class);
    }

    /**
     * Creates a new test using the given factory. If the given factory is {@code null},
     * then the tests will be skipped.
     *
     * @param factory Factory for creating {@link MathTransform} instances.
     */
    public MathTransformTest(final MathTransformFactory factory) {
        this.factory = factory;
    }

    /**
     * Creates a {@linkplain MathTransform math transform} for the
     * {@linkplain ProjectedCRS#getConversionFromBase() conversion} used by the CRS identified
     * by the given EPSG code, and stores the result in the {@link #transform} field. The set of
     * allowed codes is documented in the {@link PseudoEpsgFactory#createParameters(int)} method.
     * <p>
     * This method shall also set the {@link #tolerance} threshold in units of the target CRS
     * (typically metres), and the {@link #derivativeDeltas} in units of the source CRS
     * (typically degrees).
     * <p>
     * Subclasses can override this method if they want to customize the math transform creations,
     * or the tolerance and delta values.
     *
     * @param  code The EPSG code of a target Coordinate Reference System.
     * @throws FactoryException If the math transform for the given projected CRS can not be created.
     */
    private void createMathTransform(final int code) throws FactoryException {
        assumeNotNull(factory);
        final ParameterValueGroup parameters = PseudoEpsgFactory.createParameters(factory, code);
        validate(parameters);
        transform = factory.createParameterizedTransform(parameters);
        tolerance = 0.005;
        derivativeDeltas = new double[transform.getSourceDimensions()];
        Arrays.fill(derivativeDeltas, toRadians(1.0 / 60) / 1852); // Approximatively one metre.
    }

    /**
     * Runs the test for the given EPSG Coordinate Reference System code. The set of allowed codes
     * is documented in the {@link PseudoEpsgFactory#createParameters(int)} method.
     *
     * @param  code The EPSG code of a target Coordinate Reference System.
     * @throws FactoryException If the math transform can not be created.
     * @throws TransformException If the example point can not be transformed.
     */
    private void run(int code) throws FactoryException, TransformException {
        final SamplePoints sample = SamplePoints.getSamplePoints(code);
        createMathTransform(code);
        validate(transform);
        verifyTransform(sample.sourcePoints, sample.targetPoints);
        /*
         * At this point, we have been able to transform the sample points.
         * Now test the transform consistency using many random points inside
         * the area of validity.
         */
        final Rectangle2D areaOfValidity = sample.areaOfValidity;
        verifyInDomain(new double[] {
            areaOfValidity.getMinX(),
            areaOfValidity.getMinY()
        }, new double[] {
            areaOfValidity.getMaxX(),
            areaOfValidity.getMaxY()
        }, new int[] {
            50, 50
        }, new Random(code));
    }

    /**
     * Creates the "<cite>Mercator (variant A)</cite>" (EPSG:9804) projection documented
     * in the EPSG guidance note and transform the example point given by EPSG.
     * The {@link MathTransform} result is then compared with the expected result documented
     * by EPSG.
     * <p>
     * The math transform parameters are:
     * <table border="1" cellspacing="0" cellpadding="2">
     * <tr><th>Parameter</th><th>Value</th></tr>
     * <tr><td>semi-major axis</td><td>6377397.155</td></tr>
     * <tr><td>semi-minor axis</td><td>6356078.962818189</td></tr>
     * <tr><td>Latitude of natural origin</td><td>0.0</td></tr>
     * <tr><td>Longitude of natural origin</td><td>110.0</td></tr>
     * <tr><td>Scale factor at natural origin</td><td>0.997</td></tr>
     * <tr><td>False easting</td><td>3900000.0</td></tr>
     * <tr><td>False northing</td><td>900000.0</td></tr>
     * </table>
     *
     * @throws FactoryException If the math transform can not be created.
     * @throws TransformException If the example point can not be transformed.
     */
    @Test
    public void testMercator1SP() throws FactoryException, TransformException {
        isProjection = true;
        run(3002);  // "Makassar / NEIEZ"
    }

    /**
     * Creates the "<cite>Mercator (variant B)</cite>" (EPSG:9805) projection documented
     * in the EPSG guidance note and transform the example point given by EPSG.
     * The {@link MathTransform} result is then compared with the expected result documented
     * by EPSG.
     * <p>
     * The math transform parameters are:
     * <table border="1" cellspacing="0" cellpadding="2">
     * <tr><th>Parameter</th><th>Value</th></tr>
     * <tr><td>semi-major axis</td><td>6378245.0</td></tr>
     * <tr><td>semi-minor axis</td><td>6356863.018773047</td></tr>
     * <tr><td>Latitude of 1st standard parallel</td><td>42.0</td></tr>
     * <tr><td>Longitude of natural origin</td><td>51.0</td></tr>
     * <tr><td>False easting</td><td>0.0</td></tr>
     * <tr><td>False northing</td><td>0.0</td></tr>
     * </table>
     *
     * @throws FactoryException If the math transform can not be created.
     * @throws TransformException If the example point can not be transformed.
     */
    @Test
    public void testMercator2SP() throws FactoryException, TransformException {
        isProjection = true;
        run(3388);  // "Pulkovo 1942 / Caspian Sea Mercator"
    }

    /**
     * Creates the "<cite>Mercator Popular Visualisation Pseudo Mercator" (EPSG:1024) projection
     * documented in the EPSG guidance note and transform the example point given by EPSG.
     * The {@link MathTransform} result is then compared with the expected result documented
     * by EPSG.
     * <p>
     * The math transform parameters are:
     * <table border="1" cellspacing="0" cellpadding="2">
     * <tr><th>Parameter</th><th>Value</th></tr>
     * <tr><td>semi-major axis</td><td>6378137.0</td></tr>
     * <tr><td>semi-minor axis</td><td>6356752.314245179</td></tr>
     * <tr><td>Latitude of natural origin</td><td>0.0</td></tr>
     * <tr><td>Longitude of natural origin</td><td>0.0</td></tr>
     * <tr><td>False easting</td><td>0.0</td></tr>
     * <tr><td>False northing</td><td>0.0</td></tr>
     * </table>
     *
     * @throws FactoryException If the math transform can not be created.
     * @throws TransformException If the example point can not be transformed.
     */
    @Test
    public void testPseudoMercator() throws FactoryException, TransformException {
        isProjection = true;
        run(3857);  // "WGS 84 / Pseudo-Mercator"
    }

    /**
     * Creates the "<cite>Lambert Conic Conformal (1SP)</cite>" (EPSG:9801) projection
     * documented in the EPSG guidance note and transform the example point given by EPSG.
     * The {@link MathTransform} result is then compared with the expected result documented
     * by EPSG.
     * <p>
     * The math transform parameters are:
     * <table border="1" cellspacing="0" cellpadding="2">
     * <tr><th>Parameter</th><th>Value</th></tr>
     * <tr><td>semi-major axis</td><td>6378206.4</td></tr>
     * <tr><td>semi-minor axis</td><td>6356583.8</td></tr>
     * <tr><td>Latitude of natural origin</td><td>18.0</td></tr>
     * <tr><td>Longitude of natural origin</td><td>-77.0</td></tr>
     * <tr><td>Scale factor at natural origin</td><td>1.0</td></tr>
     * <tr><td>False easting</td><td>250000.0</td></tr>
     * <tr><td>False northing</td><td>150000.0</td></tr>
     * </table>
     *
     * @throws FactoryException If the math transform can not be created.
     * @throws TransformException If the example point can not be transformed.
     */
    @Test
    public void testLambertConicConformal1SP() throws FactoryException, TransformException {
        isProjection = true;
        run(24200);  // "JAD69 / Jamaica National Grid"
    }

    /**
     * Creates the "<cite>Lambert Conic Conformal (2SP)</cite>" (EPSG:9802) projection
     * documented in the EPSG guidance note and transform the example point given by EPSG.
     * The {@link MathTransform} result is then compared with the expected result documented
     * by EPSG.
     * <p>
     * The math transform parameters are:
     * <table border="1" cellspacing="0" cellpadding="2">
     * <tr><th>Parameter</th><th>Value</th></tr>
     * <tr><td>semi-major axis</td><td>6378206.4</td></tr>
     * <tr><td>semi-minor axis</td><td>6356583.8</td></tr>
     * <tr><td>Latitude of false origin</td><td>27.833333333333333</td></tr>
     * <tr><td>Longitude of false origin</td><td>-99.0</td></tr>
     * <tr><td>Latitude of 1st standard parallel</td><td>28.383333333333333</td></tr>
     * <tr><td>Latitude of 2nd standard parallel</td><td>30.283333333333333</td></tr>
     * <tr><td>Easting at false origin</td><td>609601.2192024385</td></tr>
     * <tr><td>Northing at false origin</td><td>0.0</td></tr>
     * </table>
     *
     * @throws FactoryException If the math transform can not be created.
     * @throws TransformException If the example point can not be transformed.
     */
    @Test
    public void testLambertConicConformal2SP() throws FactoryException, TransformException {
        isProjection = true;
        run(32040);  // "NAD27 / Texas South Central"
    }

    /**
     * Creates the "<cite>Lambert Conic Conformal (2SP Belgium)</cite>" (EPSG:9803) projection
     * documented in the EPSG guidance note and transform the example point given by EPSG.
     * The {@link MathTransform} result is then compared with the expected result documented
     * by EPSG.
     * <p>
     * The math transform parameters are:
     * <table border="1" cellspacing="0" cellpadding="2">
     * <tr><th>Parameter</th><th>Value</th></tr>
     * <tr><td>semi-major axis</td><td>6378388.0</td></tr>
     * <tr><td>semi-minor axis</td><td>6356911.9461279465</td></tr>
     * <tr><td>Latitude of false origin</td><td>90.0</td></tr>
     * <tr><td>Longitude of false origin</td><td>4.356939722222222</td></tr>
     * <tr><td>Latitude of 1st standard parallel</td><td>49.83333333333333</td></tr>
     * <tr><td>Latitude of 2nd standard parallel</td><td>51.16666666666667</td></tr>
     * <tr><td>Easting at false origin</td><td>150000.01256</td></tr>
     * <tr><td>Northing at false origin</td><td>5400088.4378</td></tr>
     * </table>
     *
     * @throws FactoryException If the math transform can not be created.
     * @throws TransformException If the example point can not be transformed.
     */
    @Test
    public void testLambertConicConformalBelgium() throws FactoryException, TransformException {
        isProjection = true;
        run(31300);  // "Belge 1972 / Belge Lambert 72"
    }

    /**
     * Creates the "<cite>IGNF:MILLER</cite>" (EPSG:310642901) projection documented in the
     * <a href="http://api.ign.fr/geoportail/api/doc/fr/developpeur/wmsc.html">IGN documentation</a>
     * and transform the example point given by IGNF. The {@link MathTransform} result is then
     * compared with the expected result documented by IGN.
     * <p>
     * The math transform parameters are:
     * <table border="1" cellspacing="0" cellpadding="2">
     * <tr><th>Parameter</th><th>Value</th></tr>
     * <tr><td>semi_major</td><td>6378137.0</td></tr>
     * <tr><td>semi_minor</td><td>6378137.0</td></tr>
     * <tr><td>latitude_of_center</td><td>0.0</td></tr>
     * <tr><td>longitude_of_center</td><td>0.0</td></tr>
     * <tr><td>false_easting</td><td>0.0</td></tr>
     * <tr><td>false_northing</td><td>0.0</td></tr>
     * </table>
     *
     * @throws FactoryException If the math transform can not be created.
     * @throws TransformException If the example point can not be transformed.
     */
    @Test
    public void testMiller() throws FactoryException, TransformException {
        isProjection = true;
        run(310642901);  // "IGNF:MILLER"
    }

    /**
     * Returns the index within the given position of an ordinate value which is not approximatively
     * equals to the expected value. The default implementation performs the work documented in the
     * {@linkplain TransformTestCase#indexOfMismatch parent class} except when the coordinate to
     * compare is a geographic coordinate located at the North or South pole, in which case the
     * longitude value is essentially ignored.
     */
    @Override
    protected int indexOfMismatch(final DirectPosition expected, final DirectPosition actual, final ComparisonType mode) {
        isAtPole = isProjection && mode == ComparisonType.INVERSE_TRANSFORM && abs(expected.getOrdinate(1)) == 90;
        return super.indexOfMismatch(expected, actual, mode);
    }

    /**
     * Returns the tolerance threshold for comparing the given ordinate value. The default
     * implementation applies the following rules:
     *
     * <ul>
     *   <li><p>For {@link TransformTestCase.ComparisonType#DIRECT_TRANSFORM direct transforms},
     *       return directly the {@linkplain #tolerance tolerance} value. When the transform to
     *       be tested is a map projection, this tolerance value is measured in metres.</p></li>
     *   <li><p>For {@link TransformTestCase.ComparisonType#INVERSE_TRANSFORM inverse transforms},
     *       if the transform being tested is a map projection, then the {@linkplain #tolerance
     *       tolerance} value is converted from metres to decimal degrees except for longitudes
     *       at a pole in which case the tolerance value is set to 360°.</p></li>
     *   <li><p>For {@link TransformTestCase.ComparisonType#DERIVATIVE derivatives}, returns a
     *       relative {@linkplain #tolerance tolerance} value instead than the absolute value.
     *       Relative tolerance values are required because derivative values close to a pole
     *       may tend toward infinity.</p></li>
     *   <li><p>For {@link TransformTestCase.ComparisonType#STRICT strict} comparisons,
     *       unconditionally returns 0.</p></li>
     * </ul>
     */
    @Override
    protected double tolerance(final double ordinate, final int dimension, final ComparisonType mode) {
        double tol = tolerance;
        switch (mode) {
            case STRICT: {
                tol = 0;
                break;
            }
            case DERIVATIVE: {
                tol = max(10*tol, tol * abs(ordinate));
                break;
            }
            case INVERSE_TRANSFORM: {
                if (isProjection) {
                    tol /= (1852 * 60); // 1 nautical miles = 1852 metres in 1 minute of angle.
                    if (dimension == 0 && isAtPole) {
                        tol = 360;
                    }
                }
                break;
            }
        }
        return tol;
    }
}
