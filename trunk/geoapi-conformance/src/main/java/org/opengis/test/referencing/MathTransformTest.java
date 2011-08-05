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
import org.opengis.referencing.operation.Matrix;
import org.opengis.referencing.operation.CoordinateOperation;
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
 * Tests {@link MathTransform}s from the {@code org.opengis.referencing.operation} package.
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
 * <p>
 * Implementors can extend this class and alter the way the tests are performed by setting some
 * <code>is&lt;<var>Operation</var>&gt;Supported</code> fields to {@code false}, or by overriding
 * any of the following methods:
 * <p>
 * <ul>
 *   <li>{@link #createMathTransform(int)}</li>
 *   <li>{@link #normalize(DirectPosition, DirectPosition, CalculationType) normalize(DirectPosition, DirectPosition, CalculationType)}</li>
 *   <li>{@link #tolerance(DirectPosition, int, CalculationType)}</li>
 *   <li>{@link #assertMatrixEquals(String, Matrix, Matrix, Matrix)}</li>
 * </ul>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@RunWith(Parameterized.class)
public strictfp class MathTransformTest extends TransformTestCase {
    /**
     * The tolerance threshold for comparing the direct transforms result.
     * This is set to half the precision of coordinate point givens in the
     * EPSG and IGNF documentation.
     */
    private static final double TRANSFORM_TOLERANCE = 0.005;

    /**
     * The tolerance threshold for comparing the derivative coefficients. In each column of the
     * derivative matrix of a map projection, there is typically one value greater than 100000
     * (100 km - same order of magnitude than the transformed coordinate values) and all other
     * values are close to zero. However we can not use the {@link #TRANSFORM_TOLERANCE} value
     * in every cases because the expected derivative coefficients are computed using a numerical
     * approximation. Some empirical tests have show that the difference between <cite>forward
     * difference</cite> and <cite>backward difference</cite> can be close to 0.25, so we must
     * be prepared to increase this tolerance threshold.
     */
    private static final double DERIVATIVE_TOLERANCE = 0.005;

    /**
     * The delta value to use for computing an approximation of the derivative by finite
     * difference, in metres. The conversion from metres to degrees is performed using
     * the standard length of a nautical mile.
     * <p>
     * Experience show that smaller values <em>decrease</em> the precision, because of
     * floating point errors when subtracting big numbers that are close in magnitude.
     */
    private static final double DERIVATIVE_DELTA = 1;

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
     * Creates a math transform for the {@linkplain CoordinateOperation coordinate operation} identified
     * by the given EPSG code, and stores the result in the {@link TransformTestCase#transform transform}
     * field. The set of allowed codes is documented in the {@link PseudoEpsgFactory#createParameters(int)}
     * method.
     * <p>
     * This method shall also set the {@linkplain TransformTestCase#tolerance tolerance} threshold
     * in units of the target CRS (typically <var>metres</var> for map projections), and the
     * {@linkplain #derivativeDeltas derivative deltas} in units of the source CRS (typically
     * <var>degrees</var> for map projections). The default implementation sets the following values:
     *
     * <ul>
     *   <li><p>{@link TransformTestCase#tolerance} is sets to half the precision of the sample
     *       coordinate points given in the EPSG guidance document.</p></li>
     *   <li><p>{@link #derivativeDeltas} is set to a value in degrees corresponding to
     *       approximatively 1 metre on Earth (calculated using the standard nautical mile length).
     *       A finer value can lead to more accurate derivative approximation by the
     *       {@link #verifyDerivative(double[]) verifyDerivative(double...)} method,
     *       at the expense of more sensitivity to the accuracy of the
     *       {@link MathTransform#transform MathTransform.transform(...)} method being tested.</p></li>
     * </ul>
     *
     * Subclasses can override this method if they want to customize the math transform creations,
     * or the tolerance and delta values.
     *
     * @param  code The EPSG code of the {@linkplain CoordinateOperation coordinate operation} to create.
     * @throws FactoryException If the math transform for the given projected CRS can not be created.
     */
    protected void createMathTransform(final int code) throws FactoryException {
        final ParameterValueGroup parameters = PseudoEpsgFactory.createParameters(factory, code);
        transform = factory.createParameterizedTransform(parameters);
        tolerance = TRANSFORM_TOLERANCE;
        derivativeDeltas = new double[transform.getSourceDimensions()];
        Arrays.fill(derivativeDeltas, DERIVATIVE_DELTA / (60 * 1852));
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
        assumeNotNull(factory);
        final SamplePoints sample = SamplePoints.getSamplePoints(code);
        createMathTransform(sample.operation);
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
     * Testes the "<cite>Mercator (variant A)</cite>" (EPSG:9804) projection.
     * First, this method transforms the point given in the <cite>Example</cite> section of the
     * EPSG guidance note and compares the {@link MathTransform} result with the expected result.
     * Next, this method transforms a random set of points in the projection area of validity
     * and ensures that the {@linkplain MathTransform#inverse() inverse transform} and the
     * {@linkplain MathTransform#derivative derivatives} are coherent.
     * <p>
     * The math transform parameters and the sample coordinates are:
     * <table cellspacing="15"><tr valign="top"><td>
     * <table border="1" cellspacing="0" cellpadding="2">
     * <tr><th>Parameter</th>                      <th>Value</th></tr>
     * <tr><td>semi-major axis</td>                <td nowrap>6377397.155 m</td></tr>
     * <tr><td>semi-minor axis</td>                <td nowrap>6356078.962818189 m</td></tr>
     * <tr><td>Latitude of natural origin</td>     <td nowrap>0.0°</td></tr>
     * <tr><td>Longitude of natural origin</td>    <td nowrap>110.0°</td></tr>
     * <tr><td>Scale factor at natural origin</td> <td nowrap>0.997</td></tr>
     * <tr><td>False easting</td>                  <td nowrap>3900000.0 m</td></tr>
     * <tr><td>False northing</td>                 <td nowrap>900000.0 m</td></tr>
     * </table></td><td>
     * <table border="1" cellspacing="0" cellpadding="2">
     * <tr><th>Source ordinates</th>           <th>Expected results</th></tr>
     * <tr align="right"><td>110°E<br>0°N</td> <td nowrap>3900000.00 m<br>900000.00 m</td></tr>
     * <tr align="right"><td>120°E<br>3°S</td> <td nowrap>5009726.58 m<br>569150.82 m</td></tr>
     * </table></td></tr></table>
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
     * Testes the "<cite>Mercator (variant B)</cite>" (EPSG:9805) projection.
     * First, this method transforms the point given in the <cite>Example</cite> section of the
     * EPSG guidance note and compares the {@link MathTransform} result with the expected result.
     * Next, this method transforms a random set of points in the projection area of validity
     * and ensures that the {@linkplain MathTransform#inverse() inverse transform} and the
     * {@linkplain MathTransform#derivative derivatives} are coherent.
     * <p>
     * The math transform parameters and the sample coordinates are:
     * <table cellspacing="15"><tr valign="top"><td>
     * <table border="1" cellspacing="0" cellpadding="2">
     * <tr><th>Parameter</th>                         <th>Value</th></tr>
     * <tr><td>semi-major axis</td>                   <td nowrap>6378245.0 m</td></tr>
     * <tr><td>semi-minor axis</td>                   <td nowrap>6356863.018773047 m</td></tr>
     * <tr><td>Latitude of 1st standard parallel</td> <td nowrap>42.0°</td></tr>
     * <tr><td>Longitude of natural origin</td>       <td nowrap>51.0°</td></tr>
     * <tr><td>False easting</td>                     <td nowrap>0.0 m</td></tr>
     * <tr><td>False northing</td>                    <td nowrap>0.0 m</td></tr>
     * </table></td><td>
     * <table border="1" cellspacing="0" cellpadding="2">
     * <tr><th>Source ordinates</th>           <th>Expected results</th></tr>
     * <tr align="right"><td>51°E<br>0°N</td>  <td nowrap>0.00 m<br>0.00 m</td></tr>
     * <tr align="right"><td>53°E<br>53°N</td> <td nowrap>165704.29 m<br>5171848.07 m</td></tr>
     * </table></td></tr></table>
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
     * Testes the "<cite>Mercator Popular Visualisation Pseudo Mercator</cite>" (EPSG:1024) projection.
     * First, this method transforms the point given in the <cite>Example</cite> section of the
     * EPSG guidance note and compares the {@link MathTransform} result with the expected result.
     * Next, this method transforms a random set of points in the projection area of validity
     * and ensures that the {@linkplain MathTransform#inverse() inverse transform} and the
     * {@linkplain MathTransform#derivative derivatives} are coherent.
     * <p>
     * The math transform parameters and the sample coordinates are:
     * <table cellspacing="15"><tr valign="top"><td>
     * <table border="1" cellspacing="0" cellpadding="2">
     * <tr><th>Parameter</th>                   <th>Value</th></tr>
     * <tr><td>semi-major axis</td>             <td nowrap>6378137.0 m</td></tr>
     * <tr><td>semi-minor axis</td>             <td nowrap>6356752.314245179 m</td></tr>
     * <tr><td>Latitude of natural origin</td>  <td nowrap>0.0°</td></tr>
     * <tr><td>Longitude of natural origin</td> <td nowrap>0.0°</td></tr>
     * <tr><td>False easting</td>               <td nowrap>0.0 m</td></tr>
     * <tr><td>False northing</td>              <td nowrap>0.0 m</td></tr>
     * </table></td><td>
     * <table border="1" cellspacing="0" cellpadding="2">
     * <tr><th>Source ordinates</th>         <th>Expected results</th></tr>
     * <tr align="right"><td>0°E<br>0°N</td> <td nowrap>0.00 m<br>0.00 m</td></tr>
     * <tr align="right"><td>100°20'00.000"W<br>24°22'54.433"N</td>
     * <td nowrap>-11169055.58 m<br>2800000.00 m</td></tr>
     * </table></td></tr></table>
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
     * Testes the "<cite>Lambert Conic Conformal (1SP)</cite>" (EPSG:9801) projection.
     * First, this method transforms the point given in the <cite>Example</cite> section of the
     * EPSG guidance note and compares the {@link MathTransform} result with the expected result.
     * Next, this method transforms a random set of points in the projection area of validity
     * and ensures that the {@linkplain MathTransform#inverse() inverse transform} and the
     * {@linkplain MathTransform#derivative derivatives} are coherent.
     * <p>
     * The math transform parameters and the sample coordinates are:
     * <table cellspacing="15"><tr valign="top"><td>
     * <table border="1" cellspacing="0" cellpadding="2">
     * <tr><th>Parameter</th>                       <th>Value</th></tr>
     * <tr><td>semi-major axis</td>                 <td nowrap>6378206.4 m</td></tr>
     * <tr><td>semi-minor axis</td>                 <td nowrap>6356583.8 m</td></tr>
     * <tr><td>Latitude of natural origin</td>      <td nowrap>18.0°</td></tr>
     * <tr><td>Longitude of natural origin</td>     <td nowrap>-77.0°</td></tr>
     * <tr><td>Scale factor at natural origin</td>  <td nowrap>1.0</td></tr>
     * <tr><td>False easting</td>                   <td nowrap>250000.0 m</td></tr>
     * <tr><td>False northing</td>                  <td nowrap>150000.0 m</td></tr>
     * </table></td><td>
     * <table border="1" cellspacing="0" cellpadding="2">
     * <tr><th>Source ordinates</th>           <th>Expected results</th></tr>
     * <tr align="right"><td>77°W<br>18°N</td> <td nowrap>250000.00 m<br>150000.00 m</td></tr>
     * <tr align="right"><td>76°56'37.26"W<br>17°55'55.80"N</td>
     * <td nowrap>255966.58 m<br>142493.51 m</td></tr>
     * </table></td></tr></table>
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
     * Testes the "<cite>Lambert Conic Conformal (2SP)</cite>" (EPSG:9802) projection.
     * First, this method transforms the point given in the <cite>Example</cite> section of the
     * EPSG guidance note and compares the {@link MathTransform} result with the expected result.
     * Next, this method transforms a random set of points in the projection area of validity
     * and ensures that the {@linkplain MathTransform#inverse() inverse transform} and the
     * {@linkplain MathTransform#derivative derivatives} are coherent.
     * <p>
     * The math transform parameters and the sample coordinates are:
     * <table cellspacing="15"><tr valign="top"><td>
     * <table border="1" cellspacing="0" cellpadding="2">
     * <tr><th>Parameter</th>                         <th>Value</th></tr>
     * <tr><td>semi-major axis</td>                   <td nowrap>6378206.4 m</td></tr>
     * <tr><td>semi-minor axis</td>                   <td nowrap>6356583.8 m</td></tr>
     * <tr><td>Latitude of false origin</td>          <td nowrap>27.833333333333333°</td></tr>
     * <tr><td>Longitude of false origin</td>         <td nowrap>-99.0°</td></tr>
     * <tr><td>Latitude of 1st standard parallel</td> <td nowrap>28.383333333333333°</td></tr>
     * <tr><td>Latitude of 2nd standard parallel</td> <td nowrap>30.283333333333333°</td></tr>
     * <tr><td>Easting at false origin</td>           <td nowrap>609601.2192024385 m</td></tr>
     * <tr><td>Northing at false origin</td>          <td nowrap>0.0 m</td></tr>
     * </table></td><td>
     * <table border="1" cellspacing="0" cellpadding="2">
     * <tr><th>Source ordinates</th>                 <th>Expected results</th></tr>
     * <tr align="right"><td>99°00'W<br>27°30'N</td> <td nowrap>2000000.00 feet<br>0 feet</td></tr>
     * <tr align="right"><td>96°00'W<br>28°30'N</td> <td nowrap>2963503.91 feet<br>254759.80 feet</td></tr>
     * </table></td></tr></table>
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
     * Testes the "<cite>Lambert Conic Conformal (2SP Belgium)</cite>" (EPSG:9803) projection.
     * First, this method transforms the point given in the <cite>Example</cite> section of the
     * EPSG guidance note and compares the {@link MathTransform} result with the expected result.
     * Next, this method transforms a random set of points in the projection area of validity
     * and ensures that the {@linkplain MathTransform#inverse() inverse transform} and the
     * {@linkplain MathTransform#derivative derivatives} are coherent.
     * <p>
     * The math transform parameters and the sample coordinates are:
     * <table cellspacing="15"><tr valign="top"><td>
     * <table border="1" cellspacing="0" cellpadding="2">
     * <tr><th>Parameter</th>                         <th>Value</th></tr>
     * <tr><td>semi-major axis</td>                   <td nowrap>6378388.0 m</td></tr>
     * <tr><td>semi-minor axis</td>                   <td nowrap>6356911.9461279465 m</td></tr>
     * <tr><td>Latitude of false origin</td>          <td nowrap>90.0°</td></tr>
     * <tr><td>Longitude of false origin</td>         <td nowrap>4.356939722222222°</td></tr>
     * <tr><td>Latitude of 1st standard parallel</td> <td nowrap>49.83333333333333°</td></tr>
     * <tr><td>Latitude of 2nd standard parallel</td> <td nowrap>51.16666666666667°</td></tr>
     * <tr><td>Easting at false origin</td>           <td nowrap>150000.01256 m</td></tr>
     * <tr><td>Northing at false origin</td>          <td nowrap>5400088.4378 m</td></tr>
     * </table></td><td>
     * <table border="1" cellspacing="0" cellpadding="2">
     * <tr><th>Source ordinates</th>                              <th>Expected results</th></tr>
     * <tr align="right"><td>4°21'24.983"E<br>90°00'00.000"N</td> <td nowrap>150000.01 m<br>5400088.44 m</td></tr>
     * <tr align="right"><td>5°48'26.533"E<br>50°40'46.461"N</td> <td nowrap>251763.20 m<br>153034.13 m</td></tr>
     * </table></td></tr></table>
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
     * Testes the "<cite>IGNF:MILLER</cite>" (EPSG:310642901) projection.
     * First, this method transforms the point given by the
     * <a href="http://api.ign.fr/geoportail/api/doc/fr/developpeur/wmsc.html">IGN documentation</a>
     * and compares the {@link MathTransform} result with the expected result. Next, this method
     * transforms a random set of points in the projection area of validity and ensures that the
     * {@linkplain MathTransform#inverse() inverse transform} and the
     * {@linkplain MathTransform#derivative derivatives} are coherent.
     * <p>
     * The math transform parameters and the sample coordinates are:
     * <table cellspacing="15"><tr valign="top"><td>
     * <table border="1" cellspacing="0" cellpadding="2">
     * <tr><th>Parameter</th>           <th>Value</th></tr>
     * <tr><td>semi_major</td>          <td nowrap>6378137.0 m</td></tr>
     * <tr><td>semi_minor</td>          <td nowrap>6378137.0 m</td></tr>
     * <tr><td>latitude_of_center</td>  <td nowrap>0.0°</td></tr>
     * <tr><td>longitude_of_center</td> <td nowrap>0.0°</td></tr>
     * <tr><td>false_easting</td>       <td nowrap>0.0 m</td></tr>
     * <tr><td>false_northing</td>      <td nowrap>0.0 m</td></tr>
     * </table></td><td>
     * <table border="1" cellspacing="0" cellpadding="2">
     * <tr><th>Source ordinates</th>                        <th>Expected results</th></tr>
     * <tr align="right"><td>0°E<br>0°N</td>                <td nowrap>0.00 m<br>0.00 m</td></tr>
     * <tr align="right"><td>2.478917°E<br>48.805639°N</td> <td nowrap>275951.78 m<br>5910061.78 m</td></tr>
     * </table></td></tr></table>
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
     * Asserts that a matrix of derivatives is equals to the expected ones within a positive delta.
     */
    @Override
    protected void assertMatrixEquals(final String message, final Matrix expected, final Matrix actual, final Matrix tolmat) {
        if (tolmat != null) {
            final int numRow = tolmat.getNumRow();
            final int numCol = tolmat.getNumCol();
            for (int j=0; j<numRow; j++) {
                for (int i=0; i<numCol; i++) {
                    tolmat.setElement(j, i, DERIVATIVE_TOLERANCE);
                }
            }
        }
        super.assertMatrixEquals(message, expected, actual, tolmat);
    }

    /**
     * Returns the tolerance threshold for comparing the given ordinate value. The default
     * implementation applies the following rules:
     * <p>
     * <ul>
     *   <li>For {@linkplain CalculationType#DIRECT_TRANSFORM direct transforms},
     *       return directly the {@linkplain #tolerance tolerance} value. When the transform to
     *       be tested is a map projection, this tolerance value is measured in metres.</li>
     *   <li>For {@linkplain CalculationType#INVERSE_TRANSFORM inverse transforms},
     *       if the transform being tested is a map projection, then the {@linkplain #tolerance
     *       tolerance} value is converted from metres to decimal degrees except for longitudes
     *       at a pole in which case the tolerance value is set to 360°.</li>
     *   <li>For {@linkplain CalculationType#STRICT strict} comparisons,
     *       unconditionally returns 0.</li>
     * </ul>
     */
    @Override
    protected double tolerance(final DirectPosition coordinate, final int dimension, final CalculationType mode) {
        double tol = tolerance;
        switch (mode) {
            case STRICT: {
                tol = 0;
                break;
            }
            case INVERSE_TRANSFORM: {
                if (isProjection) {
                    if (dimension == 0 && abs(coordinate.getOrdinate(1)) == 90) {
                        tol = 360;
                    } else {
                        tol /= (1852 * 60); // 1 nautical miles = 1852 metres in 1 minute of angle.
                    }
                }
                break;
            }
        }
        return tol;
    }
}
