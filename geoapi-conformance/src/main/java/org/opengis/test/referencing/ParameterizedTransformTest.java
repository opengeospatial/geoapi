/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2011-2023 Open Geospatial Consortium, Inc.
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

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Collections;
import java.util.Random;
import java.awt.geom.Rectangle2D;

import org.opengis.util.FactoryException;
import org.opengis.util.NoSuchIdentifierException;
import org.opengis.geometry.DirectPosition;
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.parameter.ParameterDescriptorGroup;
import org.opengis.referencing.crs.ProjectedCRS;
import org.opengis.referencing.operation.Matrix;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.MathTransform2D;
import org.opengis.referencing.operation.TransformException;
import org.opengis.referencing.operation.MathTransformFactory;
import org.opengis.referencing.operation.Projection;
import org.opengis.referencing.operation.Transformation;
import org.opengis.referencing.operation.SingleOperation;
import org.opengis.test.ToleranceModifiers;
import org.opengis.test.ToleranceModifier;
import org.opengis.test.CalculationType;
import org.opengis.test.Configuration;

import org.junit.Test;

import static java.lang.StrictMath.*;
import static org.junit.Assert.*;
import static org.junit.Assume.*;
import static org.opengis.test.ToleranceModifiers.NAUTICAL_MILE;


/**
 * Tests {@linkplain MathTransformFactory#createParameterizedTransform(ParameterValueGroup)
 * parameterized math transforms} from the {@code org.opengis.referencing.operation} package.
 * Math transform instances are created using the factory given at construction time.
 *
 * <p><b>Skipping tests for unsupported operations:</b><br>
 * If the tested factory throws a {@link NoSuchIdentifierException} during the invocation
 * of one of the following methods:
 *
 * <ul>
 *   <li>{@link MathTransformFactory#getDefaultParameters(String)}</li>
 *   <li>{@link MathTransformFactory#createParameterizedTransform(ParameterValueGroup)}</li>
 * </ul>
 *
 * then the tests is skipped. If any other kind of exception is thrown, or if {@code NoSuchIdentifierException}
 * is thrown under other circumstances than the invocation of above methods, then the test fails.
 *
 * <p><b>Tests and accuracy:</b><br>
 * By default, every tests expect an accuracy of 1 centimetre. This accuracy matches the precision
 * of most example points given in the EPSG guidance notice. Implementers can modify the kind of
 * tests being executed and the tolerance threshold in different ways:</p>
 *
 * <ul>
 *   <li>Set some <code>is&lt;<var>Operation</var>&gt;Supported</code> fields to {@code false}.</li>
 *   <li>Override some of the {@code testFoo()} method and set the {@link #tolerance tolerance} field
 *       before to invoke {@code super.testFoo()}.</li>
 *   <li>Override {@link #normalize(DirectPosition, DirectPosition, CalculationType)
 *       normalize(DirectPosition, DirectPosition, CalculationType)}.</li>
 *   <li>Override {@link #assertMatrixEquals(String, Matrix, Matrix, Matrix)}.</li>
 * </ul>
 *
 * <h2>Usage example:</h2>
 * in order to specify their factories and run the tests in a JUnit framework, implementers can define
 * a subclass in their own test suite as in the example below. That example shows also how implementers
 * can alter some tests (here the tolerance value for the <cite>Lambert Azimuthal Equal Area</cite> projection)
 * and add more checks to be executed after every tests (here ensuring that the {@linkplain #transform transform}
 * implements the {@link MathTransform2D} interface):
 *
 * {@snippet lang="java" :
 * import org.junit.Test;
 * import org.opengis.test.referencing.ParameterizedTransformTest;
 * import static org.junit.Assert.*;
 *
 * public class MyTest extends ParameterizedTransformTest {
 *     public MyTest() {
 *         super(new MyMathTransformFactory());
 *     }
 *
 *     @Test
 *     @Override
 *     public void testLambertAzimuthalEqualArea() throws FactoryException, TransformException {
 *         tolerance = 0.1;                              // Increase the tolerance value to 10 cm.
 *         super.testLambertAzimuthalEqualArea();
 *         // If more tests specific to this projection are wanted, do them here.
 *         // In this example, we replace the ellipsoid by a sphere and test again.
 *         // Note that spherical formulas can have an error up to 30 km compared
 *         // to ellipsoidal formulas, so we have to relax again the tolerance threshold.
 *         parameters.parameter("semi_minor").setValue(parameters.parameter("semi_major").doubleValue());
 *         tolerance = 30000;                            // Increase the tolerance value to 30 km.
 *         super.testLambertAzimuthalEqualArea();
 *     }
 *
 *     @After
 *     public void ensureAllTransformAreMath2D() {
 *         assertTrue(transform instanceof MathTransform2D);
 *     }
 * }}
 *
 * @see AffineTransformTest
 * @see AuthorityFactoryTest
 * @see org.opengis.test.TestSuite
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@SuppressWarnings("strictfp")   // Because we still target Java 11.
public strictfp class ParameterizedTransformTest extends TransformTestCase {
    /**
     * The default tolerance threshold for comparing the results of direct transforms.
     * This is set to the precision of coordinate point givens in the EPSG and IGNF
     * documentation.
     */
    private static final double TRANSFORM_TOLERANCE = 0.01;

    /**
     * The tolerance threshold for comparing the derivative coefficients. In each column of the
     * derivative matrix of a map projection, there is typically one value greater than 100000
     * (100 km - same order of magnitude than the transformed coordinate values) and all other
     * values are close to zero. However, we cannot use the {@link #TRANSFORM_TOLERANCE} value
     * in every cases because the expected derivative coefficients are computed using a numerical
     * approximation. Some empirical tests have show that the difference between <cite>forward
     * difference</cite> and <cite>backward difference</cite> can be close to 0.25, so we must
     * be prepared to increase this tolerance threshold.
     */
    private static final double DERIVATIVE_TOLERANCE = 0.01;

    /**
     * The delta value to use for computing an approximation of the derivative by finite
     * difference, in metres. The conversion from metres to degrees is performed using
     * the standard length of a nautical mile.
     *
     * <p>The 100 metres value has been determined empirically as a good compromise for map
     * projections.  Experience suggests that smaller values often <em>decrease</em> the
     * precision, because of floating point errors when subtracting big numbers that are
     * close in magnitude.</p>
     */
    private static final double DERIVATIVE_DELTA = 100;

    /**
     * The factory for creating {@link MathTransform} objects, or {@code null} if none.
     */
    protected final MathTransformFactory mtFactory;

    /**
     * The parameters of the math transform being tested. This field is set, together with the
     * {@link #transform transform} field, during the execution of every {@code testFoo()} method
     * in this class.
     *
     * <p>If this field is non-null before a test is run, then those parameters will be used directly.
     * This allow implementers to alter the parameters before to run the test one more time.</p>
     */
    protected ParameterValueGroup parameters;

    /**
     * A description of the test being run. This field is provided only for information purpose
     * (typically for producing logging or error messages); it is not actually used by the tests.
     * The value can be:
     *
     * <ul>
     *   <li>The name of the target {@link ProjectedCRS} when the {@linkplain #transform transform}
     *       being tested is a map projection</li>
     *   <li>The transformation name when the {@linkplain #transform transform} being tested is a
     *       datum shift operation.</li>
     * </ul>
     */
    protected String description;

    /**
     * Creates a new test without factory and with the given {@code isFooSupported} flags.
     * The given array must be the result of a call to {@link #getEnabledKeys(int)}.
     *
     * @param  isEnabled  the enabled status of all options.
     */
    ParameterizedTransformTest(final boolean[] isEnabled) {
        super(isEnabled);
        mtFactory = null;
    }

    /**
     * Creates a new test using the given factory. If the given factory is {@code null},
     * then the tests will be skipped.
     *
     * @param factory  factory for creating {@link MathTransform} instances.
     */
    public ParameterizedTransformTest(final MathTransformFactory factory) {
        super(factory);
        this.mtFactory = factory;
    }

    /**
     * Returns information about the configuration of the test which has been run.
     * This method returns a map containing:
     *
     * <ul>
     *   <li>All the entries defined in the {@linkplain TransformTestCase#configuration() parent class}.</li>
     *   <li>All the following values associated to the {@link org.opengis.test.Configuration.Key} of the same name:
     *     <ul>
     *       <li>{@link #mtFactory}</li>
     *     </ul>
     *   </li>
     * </ul>
     *
     * @return {@inheritDoc}
     */
    @Override
    public Configuration configuration() {
        final Configuration op = super.configuration();
        assertNull(op.put(Configuration.Key.mtFactory, mtFactory));
        return op;
    }

    /**
     * Invoked for preparing the header of a test failure message.
     *
     * @param buffer   the buffer in which to append the header.
     * @param message  user supplied message to append, or {@code null}.
     */
    @Override
    final void appendErrorHeader(final StringBuilder buffer, final String message) {
        if (description != null) {
            buffer.append("Error in “").append(description).append("”: ");
        }
        super.appendErrorHeader(buffer, message);
    }

    /**
     * Returns the error message for an unsupported operation method.
     *
     * @param  name  the operation method for which to return an error message.
     * @return error message for an unsupported operation method.
     */
    private static String unsupportedMethod(final String name) {
        return "The “" + name + "” operation method is not supported by the tested implementation.";
    }

    /**
     * Initialized the {@link #parameters} field to the default values for the given operation method.
     * If the tested implementation does not support the specified operation method, then the test will
     * be skipped.
     *
     * @param  method  the operation method for which to set parameter values.
     */
    private void createParameters(final String method) {
        assumeNotNull(mtFactory);
        try {
            parameters = mtFactory.getDefaultParameters(method);
        } catch (NoSuchIdentifierException e) {
            assumeNoException(unsupportedMethod(method), e);        // Will mark the test as "ignored".
        }
    }

    /**
     * Creates a math transform for the {@linkplain SingleOperation coordinate operation} identified by
     * {@link SamplePoints#operation} and stores the result in the {@link #transform} field.
     * The set of allowed codes is documented in second column of the
     * {@link PseudoEpsgFactory#createParameters(int)} method.
     *
     * @param  type    either {@code Projection.class} or {@code Transformation.class}.
     * @param  sample  the points which will be transformed.
     * @throws FactoryException if the math transform cannot be created.
     */
    private void createMathTransform(final Class<? extends SingleOperation> type, final SamplePoints sample)
            throws FactoryException
    {
        try {
            if (parameters == null) {
                assumeNotNull(mtFactory);
                parameters = PseudoEpsgFactory.createParameters(mtFactory, sample.operation);
                validators.validate(parameters);
            }
            if (transform == null) {
                assumeNotNull(mtFactory);
                transform = mtFactory.createParameterizedTransform(parameters);
                assertNotNull(description, transform);
                validators.validate(transform);
            }
        } catch (NoSuchIdentifierException e) {
            /*
             * If a code was not found, ensure that the factory does not declare that it was
             * a supported code. If the code was unsupported, then the test will be ignored.
             */
            final String message;
            if (parameters != null) {
                final ParameterDescriptorGroup descriptor = parameters.getDescriptor();
                if (!Collections.disjoint(Utilities.getNameAndAliases(descriptor),
                        Utilities.getNameAndAliases(mtFactory.getAvailableMethods(type))))
                {
                    throw e;                            // Will mark the test as "failed".
                }
                message = unsupportedMethod(Utilities.getName(descriptor));
            } else {
                message = "The “EPSG:" + sample.operation + "” coordinate operation uses the “" + e.getIdentifierCode()
                        + "” method, which is not supported by the tested implementation.";
            }
            assumeNoException(message, e);              // Will mark the test as "ignored".
        }
    }

    /**
     * Initializes the tolerance thresholds to their default values if the user did not specified custom thresholds.
     * This method sets the {@linkplain TransformTestCase#tolerance tolerance} threshold in units of the target CRS
     * (typically <var>metres</var> for map projections), and the {@linkplain #derivativeDeltas derivative deltas}
     * in units of the source CRS (typically <var>degrees</var> for map projections).
     * The current implementation sets the following values:
     *
     * <ul>
     *   <li>{@link #tolerance} is sets to {@link #TRANSFORM_TOLERANCE}, unless a greater
     *       tolerance threshold is already set in which case the existing value is left
     *       unchanged.</li>
     *   <li>{@link #derivativeDeltas} is set to a value in degrees corresponding to
     *       approximately 1 metre on Earth (calculated using the standard nautical mile length).
     *       A finer value can lead to more accurate derivative approximation by the
     *       {@link #verifyDerivative(double[]) verifyDerivative(double...)} method,
     *       at the expense of more sensitivity to the accuracy of the
     *       {@link MathTransform#transform MathTransform.transform(…)} method being tested.</li>
     * </ul>
     *
     * This method should be invoked <strong>after</strong> {@link #createMathTransform(Class, SamplePoints)}
     * because because it needs to know the number of dimensions.
     *
     * @param  modifier  the tolerance modifier to apply.
     */
    final void setTolerance(final ToleranceModifier modifier) {
        if (toleranceModifier == null) {
            toleranceModifier = modifier;
        }
        if (!(tolerance >= TRANSFORM_TOLERANCE)) {      // !(a >= b) instead of (a < b) in order to catch NaN.
            tolerance = TRANSFORM_TOLERANCE;
        }
        if (derivativeDeltas == null) {
            derivativeDeltas = new double[transform.getSourceDimensions()];
            Arrays.fill(derivativeDeltas, DERIVATIVE_DELTA / (60 * NAUTICAL_MILE));
        }
    }

    /**
     * Applies a unit conversion on the given coordinate values. This method is invoked by
     * {@link AuthorityFactoryTest} before to test a {@link ProjectedCRS} using different
     * units than the standard one. In addition to scale the units, this method scales also
     * the tolerance factor by the same factor.
     *
     * @param mode  {@link CalculationType#DIRECT_TRANSFORM} for scaling the output units (from
     *        metres to another linear unit), or {@link CalculationType#INVERSE_TRANSFORM} for
     *        scaling the input units (from degrees to another angular unit).
     * @param coordinates  the source or expected target points to scale.
     * @param scale  the scale factor, from standard units to the CRS units.
     */
    final void applyUnitConversion(final CalculationType mode, final double[] coordinates, final double scale) {
        for (int i=coordinates.length; --i>=0;) {
            coordinates[i] *= scale;
        }
        toleranceModifier = ToleranceModifiers.concatenate(toleranceModifier,
                ToleranceModifiers.scale(EnumSet.of(mode), scale, scale));
    }

    /**
     * Tests the transform consistency using many random points inside the area of validity.
     *
     * @param  areaOfValidity  the domain in which to create test points.
     * @throws TransformException if a point cannot be transformed.
     */
    final void verifyInDomainOfValidity(final Rectangle2D areaOfValidity) throws TransformException {
        verifyInDomain(new double[] {
            areaOfValidity.getMinX(),
            areaOfValidity.getMinY()
        }, new double[] {
            areaOfValidity.getMaxX(),
            areaOfValidity.getMaxY()
        }, new int[] {
            20, 20
        }, new Random());
    }

    /**
     * Tests the <cite>"Mercator (variant A)"</cite> (EPSG:9804) projection method.
     * First, this method transforms the point given in the <cite>Example</cite> section of the
     * EPSG guidance note and compares the {@link MathTransform} result with the expected result.
     * Next, this method transforms a random set of points in the projection area of validity
     * and ensures that the {@linkplain MathTransform#inverse() inverse transform} and the
     * {@linkplain MathTransform#derivative derivatives} are coherent.
     *
     * <p>The math transform parameters and the sample coordinates are:</p>
     *
     * <div class="horizontal-flow">
     *   <table class="ogc">
     *     <caption>CRS characteristics</caption>
     *     <tr><th>Parameter</th>                      <th>Value</th></tr>
     *     <tr><td>semi-major axis</td>                <td>6377397.155 m</td></tr>
     *     <tr><td>semi-minor axis</td>                <td>6356078.962818189 m</td></tr>
     *     <tr><td>Latitude of natural origin</td>     <td>0.0°</td></tr>
     *     <tr><td>Longitude of natural origin</td>    <td>110.0°</td></tr>
     *     <tr><td>Scale factor at natural origin</td> <td>0.997</td></tr>
     *     <tr><td>False easting</td>                  <td>3900000.0 m</td></tr>
     *     <tr><td>False northing</td>                 <td>900000.0 m</td></tr>
     *   </table>
     *   <table class="ogc">
     *     <caption>Test points</caption>
     *     <tr>
     *       <th>Source coordinates</th>
     *       <th>Expected results</th>
     *     </tr><tr class="coordinates">
     *       <td>120°E<br>3°S</td>
     *       <td>5009726.58 m<br>569150.82 m</td>
     *     </tr><tr class="coordinates">
     *       <td>110°E<br>0°N</td>
     *       <td>3900000.00 m<br>900000.00 m</td>
     *     </tr>
     *   </table>
     * </div>
     *
     * @throws FactoryException if the math transform cannot be created.
     * @throws TransformException if the example point cannot be transformed.
     *
     * @see AuthorityFactoryTest#testEPSG_3002()
     */
    @Test
    public void testMercator1SP() throws FactoryException, TransformException {
        description = "Makassar / NEIEZ";
        final SamplePoints sample = SamplePoints.forCRS(3002);
        createMathTransform(Projection.class, sample);
        setTolerance(ToleranceModifier.PROJECTION);
        verifyTransform(sample.sourcePoints, sample.targetPoints);
        verifyInDomainOfValidity(sample.areaOfValidity);
    }

    /**
     * Tests the <cite>"Mercator (variant B)"</cite> (EPSG:9805) projection method.
     * First, this method transforms the point given in the <cite>Example</cite> section of the
     * EPSG guidance note and compares the {@link MathTransform} result with the expected result.
     * Next, this method transforms a random set of points in the projection area of validity
     * and ensures that the {@linkplain MathTransform#inverse() inverse transform} and the
     * {@linkplain MathTransform#derivative derivatives} are coherent.
     *
     * <p>The math transform parameters and the sample coordinates are:</p>
     *
     * <div class="horizontal-flow">
     *   <table class="ogc">
     *     <caption>CRS characteristics</caption>
     *     <tr><th>Parameter</th>                         <th>Value</th></tr>
     *     <tr><td>semi-major axis</td>                   <td>6378245.0 m</td></tr>
     *     <tr><td>semi-minor axis</td>                   <td>6356863.018773047 m</td></tr>
     *     <tr><td>Latitude of 1st standard parallel</td> <td>42.0°</td></tr>
     *     <tr><td>Longitude of natural origin</td>       <td>51.0°</td></tr>
     *     <tr><td>False easting</td>                     <td>0.0 m</td></tr>
     *     <tr><td>False northing</td>                    <td>0.0 m</td></tr>
     *   </table>
     *   <table class="ogc">
     *     <caption>Test points</caption>
     *     <tr>
     *       <th>Source coordinates</th>
     *       <th>Expected results</th>
     *     </tr><tr class="coordinates">
     *       <td>53°E<br>53°N</td>
     *       <td>165704.29 m<br>5171848.07 m</td>
     *     </tr><tr class="coordinates">
     *       <td>51°E<br>0°N</td>
     *       <td>0.00 m<br>0.00 m</td>
     *     </tr>
     *   </table>
     * </div>
     *
     * @throws FactoryException if the math transform cannot be created.
     * @throws TransformException if the example point cannot be transformed.
     *
     * @see AuthorityFactoryTest#testEPSG_3388()
     */
    @Test
    public void testMercator2SP() throws FactoryException, TransformException {
        description = "Pulkovo 1942 / Caspian Sea Mercator";
        final SamplePoints sample = SamplePoints.forCRS(3388);
        createMathTransform(Projection.class, sample);
        setTolerance(ToleranceModifier.PROJECTION);
        verifyTransform(sample.sourcePoints, sample.targetPoints);
        verifyInDomainOfValidity(sample.areaOfValidity);
    }

    /**
     * Tests the <cite>"Mercator (variant C)"</cite> (EPSG:1044) projection method.
     * First, this method transforms the point given in the <cite>Example</cite> section of the
     * EPSG guidance note and compares the {@link MathTransform} result with the expected result.
     * Next, this method transforms a random set of points in the projection area of validity
     * and ensures that the {@linkplain MathTransform#inverse() inverse transform} and the
     * {@linkplain MathTransform#derivative derivatives} are coherent.
     *
     * <p>The math transform parameters and the sample coordinates are below.
     * Note that this is similar to {@link #testMercator2SP()}, except that the
     * <cite>"latitude of false origin"</cite> parameter is set to 42°N.</p>
     *
     * <div class="horizontal-flow">
     *   <table class="ogc">
     *     <caption>CRS characteristics</caption>
     *     <tr><th>Parameter</th>                         <th>Value</th></tr>
     *     <tr><td>semi-major axis</td>                   <td>6378245.0 m</td></tr>
     *     <tr><td>semi-minor axis</td>                   <td>6356863.018773047 m</td></tr>
     *     <tr><td>Latitude of 1st standard parallel</td> <td>42.0°</td></tr>
     *     <tr><td>Longitude of natural origin</td>       <td>51.0°</td></tr>
     *     <tr><td>Latitude of false origin</td>          <td>42.0°</td></tr>
     *     <tr><td>Easting at false origin</td>           <td>0.0 m</td></tr>
     *     <tr><td>Northing at false origin</td>          <td>0.0 m</td></tr>
     *   </table>
     *   <table class="ogc">
     *     <caption>Test points</caption>
     *     <tr>
     *       <th>Source coordinates</th>
     *       <th>Expected results</th>
     *     </tr><tr class="coordinates">
     *       <td>53°E<br>53°N</td>
     *       <td>165704.29 m<br>1351950.22 m</td>
     *     </tr><tr class="coordinates">
     *       <td>51°E<br>42°N</td>
     *       <td>0.00 m<br>0.00 m</td>
     *     </tr>
     *   </table>
     * </div>
     *
     * @throws FactoryException if the math transform cannot be created.
     * @throws TransformException if the example point cannot be transformed.
     */
    @Test
    public void testMercatorVariantC() throws FactoryException, TransformException {
        description = "Pulkovo 1942 / Caspian Sea Mercator";
        final SamplePoints sample = SamplePoints.forCRS(3388);
        sample.targetPoints[1] = 1351950.22;    // New Northing value for 53°N.
        sample.sourcePoints[3] = 42;            // New latitude where we expect a northing of 0 m.
        /*
         * Following is basically a copy-and-paste of PseudoEpsgFactory.createParameters(mtFactory, 3388)
         * with a different projection ("variant C" instead of "variant B") and one more parameter value
         * (the "Latitude of false origin").
         */
        createParameters("Mercator (variant C)");
        parameters.parameter("semi_major").setValue(6378245.0);                         // Krassowski 1940
        parameters.parameter("semi_minor").setValue(6378245.0 * (1 - 1/298.3));
        parameters.parameter("Latitude of 1st standard parallel").setValue(42.0);
        parameters.parameter("Longitude of natural origin")      .setValue(51.0);
        parameters.parameter("Latitude of false origin")         .setValue(42.0);
        validators.validate(parameters);
        /*
         * Following is common to all tests in this class.
         */
        createMathTransform(Projection.class, sample);
        setTolerance(ToleranceModifier.PROJECTION);
        verifyTransform(sample.sourcePoints, sample.targetPoints);
        verifyInDomainOfValidity(sample.areaOfValidity);
    }

    /**
     * Tests the <cite>"Mercator (Spherical)"</cite> (EPSG:1026) projection method.
     * First, this method transforms the point given in the <cite>Example</cite> section of the
     * EPSG guidance note and compares the {@link MathTransform} result with the expected result.
     * Next, this method transforms a random set of points in the projection area of validity
     * and ensures that the {@linkplain MathTransform#inverse() inverse transform} and the
     * {@linkplain MathTransform#derivative derivatives} are coherent.
     *
     * <p>The math transform parameters and the sample coordinates are below.
     * Note that the sample point is the same than for {@link #testPseudoMercator()},
     * but with a different result in projected coordinates.</p>
     *
     * <div class="horizontal-flow">
     *   <table class="ogc">
     *     <caption>CRS characteristics</caption>
     *     <tr><th>Parameter</th>                   <th>Value</th></tr>
     *     <tr><td>semi-major axis</td>             <td>6371007.0 m</td></tr>
     *     <tr><td>semi-minor axis</td>             <td>6371007.0 m</td></tr>
     *     <tr><td>Longitude of natural origin</td> <td>0.0°</td></tr>
     *     <tr><td>False easting</td>               <td>0.0 m</td></tr>
     *     <tr><td>False northing</td>              <td>0.0 m</td></tr>
     *   </table>
     *   <table class="ogc">
     *     <caption>Test points</caption>
     *     <tr>
     *       <th>Source coordinates</th>
     *       <th>Expected results</th>
     *     </tr><tr class="coordinates">
     *       <td>100°20'00.000"W<br>24°22'54.433"N</td>
     *       <td>-11156569.90 m<br>2796869.94 m</td>
     *     </tr><tr class="coordinates">
     *       <td>0°E<br>0°N</td>
     *       <td>0.00 m<br>0.00 m</td>
     *     </tr>
     *   </table>
     * </div>
     *
     * @throws FactoryException if the math transform cannot be created.
     * @throws TransformException if the example point cannot be transformed.
     */
    @Test
    public void testMercatorSpherical() throws FactoryException, TransformException {
        description = "World Spherical Mercator";
        final SamplePoints sample = SamplePoints.forCRS(3857);
        sample.targetPoints[0] = -11156569.90;    // New Easting value.
        sample.targetPoints[1] =   2796869.94;    // New Northing value.
        createParameters("Mercator (Spherical)");
        parameters.parameter("semi_major").setValue(6371007.0);
        parameters.parameter("semi_minor").setValue(6371007.0);
        validators.validate(parameters);
        createMathTransform(Projection.class, sample);
        setTolerance(ToleranceModifier.PROJECTION);
        verifyTransform(sample.sourcePoints, sample.targetPoints);
        verifyInDomainOfValidity(sample.areaOfValidity);
    }

    /**
     * Tests the <cite>"Mercator Popular Visualisation Pseudo Mercator"</cite> (EPSG:1024) projection
     * method. First, this method transforms the point given in the <cite>Example</cite> section of the
     * EPSG guidance note and compares the {@link MathTransform} result with the expected result.
     * Next, this method transforms a random set of points in the projection area of validity
     * and ensures that the {@linkplain MathTransform#inverse() inverse transform} and the
     * {@linkplain MathTransform#derivative derivatives} are coherent.
     *
     * <p>The math transform parameters and the sample coordinates are:</p>
     *
     * <div class="horizontal-flow">
     *   <table class="ogc">
     *     <caption>CRS characteristics</caption>
     *     <tr><th>Parameter</th>                   <th>Value</th></tr>
     *     <tr><td>semi-major axis</td>             <td>6378137.0 m</td></tr>
     *     <tr><td>semi-minor axis</td>             <td>6356752.314247833 m</td></tr>
     *     <tr><td>Latitude of natural origin</td>  <td>0.0°</td></tr>
     *     <tr><td>Longitude of natural origin</td> <td>0.0°</td></tr>
     *     <tr><td>False easting</td>               <td>0.0 m</td></tr>
     *     <tr><td>False northing</td>              <td>0.0 m</td></tr>
     *   </table>
     *   <table class="ogc">
     *     <caption>Test points</caption>
     *     <tr>
     *       <th>Source coordinates</th>
     *       <th>Expected results</th>
     *     </tr><tr class="coordinates">
     *       <td>100°20'00.000"W<br>24°22'54.433"N</td>
     *       <td>-11169055.58 m<br>2800000.00 m</td>
     *     </tr><tr class="coordinates">
     *       <td>0°E<br>0°N</td>
     *       <td>0.00 m<br>0.00 m</td>
     *     </tr>
     *   </table>
     * </div>
     *
     * @throws FactoryException if the math transform cannot be created.
     * @throws TransformException if the example point cannot be transformed.
     *
     * @see AuthorityFactoryTest#testEPSG_3857()
     */
    @Test
    public void testPseudoMercator() throws FactoryException, TransformException {
        description = "WGS 84 / Pseudo-Mercator";
        final SamplePoints sample = SamplePoints.forCRS(3857);
        createMathTransform(Projection.class, sample);
        setTolerance(ToleranceModifier.PROJECTION);
        verifyTransform(sample.sourcePoints, sample.targetPoints);
        verifyInDomainOfValidity(sample.areaOfValidity);
    }

    /**
     * Tests the <cite>"IGNF:MILLER"</cite> projection.
     * First, this method transforms the point given below
     * and compares the {@link MathTransform} result with the expected result. Next, this method
     * transforms a random set of points in the projection area of validity and ensures that the
     * {@linkplain MathTransform#inverse() inverse transform} and the
     * {@linkplain MathTransform#derivative derivatives} are coherent.
     *
     * <p>The math transform parameters and the sample coordinates are:</p>
     *
     * <div class="horizontal-flow">
     *   <table class="ogc">
     *     <caption>CRS characteristics</caption>
     *     <tr><th>Parameter</th>        <th>Value</th></tr>
     *     <tr><td>semi_major</td>       <td>6378137.0 m</td></tr>
     *     <tr><td>semi_minor</td>       <td>6378137.0 m</td></tr>
     *     <tr><td>central_meridian</td> <td>0.0°</td></tr>
     *     <tr><td>false_easting</td>    <td>0.0 m</td></tr>
     *     <tr><td>false_northing</td>   <td>0.0 m</td></tr>
     *   </table>
     *   <table class="ogc">
     *     <caption>Test points</caption>
     *     <tr>
     *       <th>Source coordinates</th>
     *       <th>Expected results</th>
     *     </tr><tr class="coordinates">
     *       <td>2.478917°E<br>48.805639°N</td>
     *       <td>275951.78 m<br>5910061.78 m</td>
     *     </tr><tr class="coordinates">
     *       <td>0°E<br>0°N</td>
     *       <td>0.00 m<br>0.00 m</td>
     *     </tr>
     *   </table>
     * </div>
     *
     * @throws FactoryException if the math transform cannot be created.
     * @throws TransformException if the example point cannot be transformed.
     */
    @Test
    public void testMiller() throws FactoryException, TransformException {
        description = "IGNF:MILLER";
        final SamplePoints sample = SamplePoints.forCRS(310642901);
        createMathTransform(Projection.class, sample);
        setTolerance(ToleranceModifier.PROJECTION);
        verifyTransform(sample.sourcePoints, sample.targetPoints);
        verifyInDomainOfValidity(sample.areaOfValidity);
    }

    /**
     * Tests the <cite>"Hotine Oblique Mercator (variant B)"</cite> (EPSG:9815) projection method.
     * First, this method transforms the point given in the <cite>Example</cite> section of the
     * EPSG guidance note and compares the {@link MathTransform} result with the expected result.
     * Next, this method transforms a random set of points in the projection area of validity
     * and ensures that the {@linkplain MathTransform#inverse() inverse transform} and the
     * {@linkplain MathTransform#derivative derivatives} are coherent.
     *
     * <p>The math transform parameters and the sample coordinates are:</p>
     *
     * <div class="horizontal-flow">
     *   <table class="ogc">
     *     <caption>CRS characteristics</caption>
     *     <tr><th>Parameter</th>                         <th>Value</th></tr>
     *     <tr><td>semi-major axis</td>                   <td>6377298.556 m</td></tr>
     *     <tr><td>semi-minor axis</td>                   <td>6356097.550300896 m</td></tr>
     *     <tr><td>Latitude of projection centre</td>     <td>4.0°</td></tr>
     *     <tr><td>Longitude of projection centre</td>    <td>109.6855202029758°</td></tr>
     *     <tr><td>Azimuth of initial line</td>           <td>53.31582047222222°</td></tr>
     *     <tr><td>Angle from Rectified to Skew Grid</td> <td>53.13010236111111°</td></tr>
     *     <tr><td>Scale factor on initial line</td>      <td>0.99984</td></tr>
     *     <tr><td>Easting at projection centre</td>      <td>590476.87 m</td></tr>
     *     <tr><td>Northing at projection centre</td>     <td>442857.65 m</td></tr>
     *   </table>
     *   <table class="ogc">
     *     <caption>Test points</caption>
     *     <tr>
     *       <th>Source coordinates</th>
     *       <th>Expected results</th>
     *     </tr><tr class="coordinates">
     *       <td>115°48'19.8196"E<br>5°23'14.1129"N</td>
     *       <td>679245.73 m<br>596562.78 m</td>
     *     </tr><tr class="coordinates">
     *       <td>115°E<br>4°N</td>
     *       <td>590476.87 m<br>442857.65 m</td>
     *     </tr>
     *   </table>
     * </div>
     *
     * @throws FactoryException if the math transform cannot be created.
     * @throws TransformException if the example point cannot be transformed.
     *
     * @see AuthorityFactoryTest#testEPSG_29873()
     */
    @Test
    public void testHotineObliqueMercator() throws FactoryException, TransformException {
        description = "Timbalai 1948 / RSO Borneo (m)";
        final SamplePoints sample = SamplePoints.forCRS(29873);
        createMathTransform(Projection.class, sample);
        setTolerance(ToleranceModifier.PROJECTION);
        verifyTransform(sample.sourcePoints, sample.targetPoints);
        verifyInDomainOfValidity(sample.areaOfValidity);
    }

    /**
     * Tests the <cite>"Transverse Mercator"</cite> (EPSG:9807) projection method.
     * First, this method transforms the point given in the <cite>Example</cite> section of the
     * EPSG guidance note and compares the {@link MathTransform} result with the expected result.
     * Next, this method transforms a random set of points in the projection area of validity
     * and ensures that the {@linkplain MathTransform#inverse() inverse transform} and the
     * {@linkplain MathTransform#derivative derivatives} are coherent.
     *
     * <p>The math transform parameters and the sample coordinates are:</p>
     *
     * <div class="horizontal-flow">
     *   <table class="ogc">
     *     <caption>CRS characteristics</caption>
     *     <tr><th>Parameter</th>                      <th>Value</th></tr>
     *     <tr><td>semi-major axis</td>                <td>6377563.396 m</td></tr>
     *     <tr><td>semi-minor axis</td>                <td>6356256.908909849 m</td></tr>
     *     <tr><td>Latitude of natural origin</td>     <td>49.0°</td></tr>
     *     <tr><td>Longitude of natural origin</td>    <td>-2.0°</td></tr>
     *     <tr><td>Scale factor at natural origin</td> <td>0.9996012717</td></tr>
     *     <tr><td>False easting</td>                  <td>400000.0 m</td></tr>
     *     <tr><td>False northing</td>                 <td>-100000.0 m</td></tr>
     *   </table>
     *   <table class="ogc">
     *     <caption>Test points</caption>
     *     <tr>
     *       <th>Source coordinates</th>
     *       <th>Expected results</th>
     *     </tr><tr class="coordinates">
     *       <td>00°30'E<br>50°30'N</td>
     *       <td>577274.98 m<br>69740.49 m</td>
     *     </tr><tr class="coordinates">
     *       <td>2°W<br>49°N</td>
     *       <td>400000.00 m<br>-100000.00 m</td>
     *     </tr>
     *   </table>
     * </div>
     *
     * @throws FactoryException if the math transform cannot be created.
     * @throws TransformException if the example point cannot be transformed.
     *
     * @see AuthorityFactoryTest#testEPSG_27700()
     */
    @Test
    public void testTransverseMercator() throws FactoryException, TransformException {
        description = "OSGB 1936 / British National Grid";
        final SamplePoints sample = SamplePoints.forCRS(27700);
        createMathTransform(Projection.class, sample);
        setTolerance(ToleranceModifier.PROJECTION);
        verifyTransform(sample.sourcePoints, sample.targetPoints);
        verifyInDomainOfValidity(sample.areaOfValidity);
    }

    /**
     * Tests the <cite>Transverse Mercator (South Orientated)</cite> (EPSG:9808) projection method.
     * First, this method transforms the point given in the <cite>Example</cite> section of the
     * EPSG guidance note and compares the {@link MathTransform} result with the expected result.
     * Next, this method transforms a random set of points in the projection area of validity
     * and ensures that the {@linkplain MathTransform#inverse() inverse transform} and the
     * {@linkplain MathTransform#derivative derivatives} are coherent.
     *
     * <p>The math transform parameters and the sample coordinates are:</p>
     *
     * <div class="horizontal-flow">
     *   <table class="ogc">
     *     <caption>CRS characteristics</caption>
     *     <tr><th>Parameter</th>                      <th>Value</th></tr>
     *     <tr><td>semi-major axis</td>                <td>6378137.0 m</td></tr>
     *     <tr><td>semi-minor axis</td>                <td>6356752.314247833 m</td></tr>
     *     <tr><td>Latitude of natural origin</td>     <td>0°</td></tr>
     *     <tr><td>Longitude of natural origin</td>    <td>29°</td></tr>
     *     <tr><td>Scale factor at natural origin</td> <td>1</td></tr>
     *     <tr><td>False easting</td>                  <td>0 m</td></tr>
     *     <tr><td>False northing</td>                 <td>0 m</td></tr>
     *   </table>
     *   <table class="ogc">
     *     <caption>Test points</caption>
     *     <tr>
     *       <th>Source coordinates</th>
     *       <th>Expected results</th>
     *     </tr><tr class="coordinates">
     *       <td>28°16'57.479"E<br>25°43'55.302"S</td>
     *       <td>71984.48 m<br>2847342.74 m</td>
     *     </tr><tr class="coordinates">
     *       <td>20°E<br>0°S</td>
     *       <td>0 m<br>0 m</td>
     *     </tr>
     *   </table>
     * </div>
     *
     * @throws FactoryException if the math transform cannot be created.
     * @throws TransformException if the example point cannot be transformed.
     */
    @Test
    public void testTransverseMercatorSouthOrientated() throws FactoryException, TransformException {
        description = "Hartebeesthoek94 / Lo29";
        final SamplePoints sample = SamplePoints.forCRS(2053);
        createMathTransform(Projection.class, sample);
        setTolerance(ToleranceModifier.PROJECTION);
        /*
         * In this particular case we have a conflict between the change of axis direction performed by the
         * "Transverse Mercator (South Orientated)" operation method  and the (east, north) axis directions
         * documented in the MathTransformFactory.createParameterizedTransform(…) method. We do not mandate
         * any particular behavior at this time, so we have to determine what the implementer choose to do,
         * by projecting a point in the south hemisphere and checking the sign of the result.
         */
        double[] expected = sample.targetPoints;
        final double[] check = new double[] {-0.5, -0.5};
        transform.transform(check, 0, check, 0, 1);
        if (check[1] < 0) {
            /*
             * Point in the South hemisphere have negative y values. In other words, the implementer chooses to
             * keep (east,north) directions instead of (west,south). Reverse the sign of all expected coordinates.
             */
            expected = expected.clone();
            for (int i=0; i<expected.length; i++) {
                expected[i] = -expected[i];
            }
        }
        verifyTransform(sample.sourcePoints, expected);
        verifyInDomainOfValidity(sample.areaOfValidity);
    }

    /**
     * Tests the <cite>"Cassini-Soldner"</cite> (EPSG:9806) projection method.
     * First, this method transforms the point given in the <cite>Example</cite> section of the
     * EPSG guidance note and compares the {@link MathTransform} result with the expected result.
     * Next, this method transforms a random set of points in the projection area of validity
     * and ensures that the {@linkplain MathTransform#inverse() inverse transform} and the
     * {@linkplain MathTransform#derivative derivatives} are coherent.
     *
     * <p>The math transform parameters and the sample coordinates are:</p>
     *
     * <div class="horizontal-flow">
     *   <table class="ogc">
     *     <caption>CRS characteristics</caption>
     *     <tr><th>Parameter</th>                   <th>Value</th></tr>
     *     <tr><td>semi-major axis</td>             <td>6378350.8704 m</td></tr>
     *     <tr><td>semi-minor axis</td>             <td>6356675.0184 m</td></tr>
     *     <tr><td>Latitude of natural origin</td>  <td>10.441666666666666°</td></tr>
     *     <tr><td>Longitude of natural origin</td> <td>-61.33333333333333°</td></tr>
     *     <tr><td>False easting</td>               <td>86501.46392052001 m</td></tr>
     *     <tr><td>False northing</td>              <td>65379.0134283 m</td></tr>
     *   </table>
     *   <table class="ogc">
     *     <caption>Test points</caption>
     *     <tr>
     *       <th>Source coordinates</th>
     *       <th>Expected results</th>
     *     </tr><tr class="coordinates">
     *       <td>60°00'00"W<br>10°00'00"N</td>
     *       <td>66644.94 links<br>82536.22 links</td>
     *     </tr><tr class="coordinates">
     *       <td>61°20'00"W<br>10°26'30"N</td>
     *       <td>430000.00 links<br>325000.00 links</td>
     *     </tr>
     *   </table>
     *   <p class="right-note">1 link = 0.66 feet<br>1 feet = 0.3048 metre</p>
     * </div>
     *
     * @throws FactoryException if the math transform cannot be created.
     * @throws TransformException if the example point cannot be transformed.
     *
     * @see AuthorityFactoryTest#testEPSG_2314()
     */
    @Test
    public void testCassiniSoldner() throws FactoryException, TransformException {
        description = "Trinidad 1903 / Trinidad Grid";
        final SamplePoints sample = SamplePoints.forCRS(2314);
        createMathTransform(Projection.class, sample);
        setTolerance(ToleranceModifier.PROJECTION);
        verifyTransform(sample.sourcePoints, sample.targetPoints);
        verifyInDomainOfValidity(sample.areaOfValidity);
    }

    /**
     * Tests the <cite>"Hyperbolic Cassini-Soldner"</cite> (EPSG:9833) projection method.
     * First, this method transforms the point given in the <cite>Example</cite> section of the
     * EPSG guidance note and compares the {@link MathTransform} result with the expected result.
     * Next, this method transforms a random set of points in the projection area of validity
     * and ensures that the {@linkplain MathTransform#inverse() inverse transform} and the
     * {@linkplain MathTransform#derivative derivatives} are coherent.
     *
     * <p>The math transform parameters and the sample coordinates are:</p>
     *
     * <div class="horizontal-flow">
     *   <table class="ogc">
     *     <caption>CRS characteristics</caption>
     *     <tr><th>Parameter</th>                   <th>Value</th></tr>
     *     <tr><td>semi-major axis</td>             <td>6378306.3696 m</td></tr>
     *     <tr><td>semi-minor axis</td>             <td>6356571.9960 m</td></tr>
     *     <tr><td>Latitude of natural origin</td>  <td>-16.25°</td></tr>
     *     <tr><td>Longitude of natural origin</td> <td>179.33333333333333°</td></tr>
     *     <tr><td>False easting</td>               <td>251727.9155424 m</td></tr>
     *     <tr><td>False northing</td>              <td>334519.9537680 m</td></tr>
     *   </table>
     *   <table class="ogc">
     *     <caption>Test points</caption>
     *     <tr>
     *       <th>Source coordinates</th>
     *       <th>Expected results</th>
     *     </tr><tr class="coordinates">
     *       <td>179°59′39.6115″E<br>16°50′29.2435″S</td>
     *       <td>1601528.90 links<br>1336966.01 links</td>
     *     </tr><tr class="coordinates">
     *       <td>16°15'00"S<br>179°20'00"E</td>
     *       <td>41251331.8 links<br>1662888.5 links</td>
     *     </tr>
     *   </table>
     *   <p class="right-note">1 link = 0.66 feet<br>1 feet = 0.3048 metre</p>
     * </div>
     *
     * @throws FactoryException if the math transform cannot be created.
     * @throws TransformException if the example point cannot be transformed.
     *
     * @see AuthorityFactoryTest#testEPSG_3139()
     */
    @Test
    public void testHyperbolicCassiniSoldner() throws FactoryException, TransformException {
        description = "Vanua Levu 1915 / Vanua Levu Grid";
        final SamplePoints sample = SamplePoints.forCRS(3139);
        createMathTransform(Projection.class, sample);
        setTolerance(ToleranceModifier.PROJECTION);
        verifyTransform(sample.sourcePoints, sample.targetPoints);
        verifyInDomainOfValidity(sample.areaOfValidity);
    }

    /**
     * Tests the <cite>"Lambert Conic Conformal (1SP)"</cite> (EPSG:9801) projection method.
     * First, this method transforms the point given in the <cite>Example</cite> section of the
     * EPSG guidance note and compares the {@link MathTransform} result with the expected result.
     * Next, this method transforms a random set of points in the projection area of validity
     * and ensures that the {@linkplain MathTransform#inverse() inverse transform} and the
     * {@linkplain MathTransform#derivative derivatives} are coherent.
     *
     * <p>The math transform parameters and the sample coordinates are:</p>
     *
     * <div class="horizontal-flow">
     *   <table class="ogc">
     *     <caption>CRS characteristics</caption>
     *     <tr><th>Parameter</th>                      <th>Value</th></tr>
     *     <tr><td>semi-major axis</td>                <td>6378206.4 m</td></tr>
     *     <tr><td>semi-minor axis</td>                <td>6356583.8 m</td></tr>
     *     <tr><td>Latitude of natural origin</td>     <td>18.0°</td></tr>
     *     <tr><td>Longitude of natural origin</td>    <td>-77.0°</td></tr>
     *     <tr><td>Scale factor at natural origin</td> <td>1.0</td></tr>
     *     <tr><td>False easting</td>                  <td>250000.0 m</td></tr>
     *     <tr><td>False northing</td>                 <td>150000.0 m</td></tr>
     *   </table>
     *   <table class="ogc">
     *     <caption>Test points</caption>
     *     <tr>
     *       <th>Source coordinates</th>
     *       <th>Expected results</th>
     *     </tr><tr class="coordinates">
     *       <td>76°56'37.26"W<br>17°55'55.80"N</td>
     *       <td>255966.58 m<br>142493.51 m</td>
     *     </tr><tr class="coordinates">
     *       <td>77°W<br>18°N</td>
     *       <td>250000.00 m<br>150000.00 m</td>
     *     </tr>
     *   </table>
     * </div>
     *
     * @throws FactoryException if the math transform cannot be created.
     * @throws TransformException if the example point cannot be transformed.
     *
     * @see AuthorityFactoryTest#testEPSG_24200()
     */
    @Test
    public void testLambertConicConformal1SP() throws FactoryException, TransformException {
        description = "JAD69 / Jamaica National Grid";
        final SamplePoints sample = SamplePoints.forCRS(24200);
        createMathTransform(Projection.class, sample);
        setTolerance(ToleranceModifier.PROJECTION);
        verifyTransform(sample.sourcePoints, sample.targetPoints);
        verifyInDomainOfValidity(sample.areaOfValidity);
    }

    /**
     * Tests the <cite>"Lambert Conic Conformal (2SP)"</cite> (EPSG:9802) projection method.
     * First, this method transforms the point given in the <cite>Example</cite> section of the
     * EPSG guidance note and compares the {@link MathTransform} result with the expected result.
     * Next, this method transforms a random set of points in the projection area of validity
     * and ensures that the {@linkplain MathTransform#inverse() inverse transform} and the
     * {@linkplain MathTransform#derivative derivatives} are coherent.
     *
     * <p>The math transform parameters and the sample coordinates are:</p>
     *
     * <div class="horizontal-flow">
     *   <table class="ogc">
     *     <caption>CRS characteristics</caption>
     *     <tr><th>Parameter</th>                         <th>Value</th></tr>
     *     <tr><td>semi-major axis</td>                   <td>6378206.4 m</td></tr>
     *     <tr><td>semi-minor axis</td>                   <td>6356583.8 m</td></tr>
     *     <tr><td>Latitude of false origin</td>          <td>27.833333333333333°</td></tr>
     *     <tr><td>Longitude of false origin</td>         <td>-99.0°</td></tr>
     *     <tr><td>Latitude of 1st standard parallel</td> <td>28.383333333333333°</td></tr>
     *     <tr><td>Latitude of 2nd standard parallel</td> <td>30.283333333333333°</td></tr>
     *     <tr><td>Easting at false origin</td>           <td>609601.2192024385 m</td></tr>
     *     <tr><td>Northing at false origin</td>          <td>0.0 m</td></tr>
     *   </table>
     *   <table class="ogc">
     *     <caption>Test points</caption>
     *     <tr>
     *       <th>Source coordinates</th>
     *       <th>Expected results</th>
     *     </tr><tr class="coordinates">
     *       <td>96°00'W<br>28°30'N</td>
     *       <td>2963503.91 US feet<br>254759.80 US feet</td>
     *     </tr><tr class="coordinates">
     *       <td>99°00'W<br>27°30'N</td>
     *        <td>2000000.00 US feet<br>0 US feet</td>
     *     </tr>
     *   </table>
     *   <p class="right-note">1 metre = 3.2808333… US feet</p>
     * </div>
     *
     * @throws FactoryException if the math transform cannot be created.
     * @throws TransformException if the example point cannot be transformed.
     *
     * @see AuthorityFactoryTest#testEPSG_32040()
     */
    @Test
    public void testLambertConicConformal2SP() throws FactoryException, TransformException {
        description = "NAD27 / Texas South Central";
        final SamplePoints sample = SamplePoints.forCRS(32040);
        createMathTransform(Projection.class, sample);
        setTolerance(ToleranceModifier.PROJECTION);
        verifyTransform(sample.sourcePoints, sample.targetPoints);
        verifyInDomainOfValidity(sample.areaOfValidity);
    }

    /**
     * Tests the <cite>"Lambert Conic Conformal (2SP Belgium)"</cite> (EPSG:9803) projection method.
     * First, this method transforms the point given in the <cite>Example</cite> section of the
     * EPSG guidance note and compares the {@link MathTransform} result with the expected result.
     * Next, this method transforms a random set of points in the projection area of validity
     * and ensures that the {@linkplain MathTransform#inverse() inverse transform} and the
     * {@linkplain MathTransform#derivative derivatives} are coherent.
     *
     * <p>The math transform parameters and the sample coordinates are:</p>
     *
     * <div class="horizontal-flow">
     *   <table class="ogc">
     *     <caption>CRS characteristics</caption>
     *     <tr><th>Parameter</th>                         <th>Value</th></tr>
     *     <tr><td>semi-major axis</td>                   <td>6378388.0 m</td></tr>
     *     <tr><td>semi-minor axis</td>                   <td>6356911.9461279465 m</td></tr>
     *     <tr><td>Latitude of false origin</td>          <td>90.0°</td></tr>
     *     <tr><td>Longitude of false origin</td>         <td>4.356939722222222°</td></tr>
     *     <tr><td>Latitude of 1st standard parallel</td> <td>49.83333333333333°</td></tr>
     *     <tr><td>Latitude of 2nd standard parallel</td> <td>51.16666666666667°</td></tr>
     *     <tr><td>Easting at false origin</td>           <td>150000.01256 m</td></tr>
     *     <tr><td>Northing at false origin</td>          <td>5400088.4378 m</td></tr>
     *   </table>
     *   <table class="ogc">
     *     <caption>Test points</caption>
     *     <tr>
     *       <th>Source coordinates</th>
     *       <th>Expected results</th>
     *     </tr><tr class="coordinates">
     *       <td>5°48'26.533"E<br>50°40'46.461"N</td>
     *       <td>251763.20 m<br>153034.13 m</td>
     *     </tr><tr class="coordinates">
     *       <td>4°21'24.983"E<br>90°00'00.000"N</td>
     *       <td>150000.01 m<br>5400088.44 m</td>
     *     </tr>
     *   </table>
     * </div>
     *
     * @throws FactoryException if the math transform cannot be created.
     * @throws TransformException if the example point cannot be transformed.
     *
     * @see AuthorityFactoryTest#testEPSG_31300()
     */
    @Test
    public void testLambertConicConformalBelgium() throws FactoryException, TransformException {
        description = "Belge 1972 / Belge Lambert 72";
        final SamplePoints sample = SamplePoints.forCRS(31300);
        createMathTransform(Projection.class, sample);
        setTolerance(ToleranceModifier.PROJECTION);
        verifyTransform(sample.sourcePoints, sample.targetPoints);
        verifyInDomainOfValidity(sample.areaOfValidity);
    }

    /**
     * Tests the <cite>"Lambert Conic Conformal (2SP Michigan)"</cite> (EPSG:1051) projection method.
     * First, this method transforms the point given in the <cite>Example</cite> section of the
     * EPSG guidance note and compares the {@link MathTransform} result with the expected result.
     * Next, this method transforms a random set of points in the projection area of validity
     * and ensures that the {@linkplain MathTransform#inverse() inverse transform} and the
     * {@linkplain MathTransform#derivative derivatives} are coherent.
     *
     * <p>The math transform parameters and the sample coordinates are:</p>
     *
     * <div class="horizontal-flow">
     *   <table class="ogc">
     *     <caption>CRS characteristics</caption>
     *     <tr><th>Parameter</th>                         <th>Value</th></tr>
     *     <tr><td>semi-major axis</td>                   <td>6378206.4 m</td></tr>
     *     <tr><td>semi-minor axis</td>                   <td>6356583.8 m</td></tr>
     *     <tr><td>Latitude of false origin</td>          <td>43.316666666666667°</td></tr>
     *     <tr><td>Longitude of false origin</td>         <td>-84.333333333333333°</td></tr>
     *     <tr><td>Latitude of 1st standard parallel</td> <td>44.183333333333333°</td></tr>
     *     <tr><td>Latitude of 2nd standard parallel</td> <td>45.7°</td></tr>
     *     <tr><td>Easting at false origin</td>           <td>609601.2192024385 m</td></tr>
     *     <tr><td>Northing at false origin</td>          <td>0.0 m</td></tr>
     *   </table>
     *   <table class="ogc">
     *     <caption>Test points</caption>
     *     <tr>
     *       <th>Source coordinates</th>
     *       <th>Expected results</th>
     *     </tr><tr class="coordinates">
     *       <td>83°10"W<br>43°45'N</td>
     *       <td>2308335.75 US feet<br>160210.48 US feet</td>
     *     </tr><tr class="coordinates">
     *       <td>84°20'W<br>43°19'N</td>
     *       <td>2000000.00 US feet<br>0 US feet</td>
     *     </tr>
     *   </table>
     *   <p class="right-note">1 metre = 3.2808333… US feet</p>
     * </div>
     *
     * @throws FactoryException if the math transform cannot be created.
     * @throws TransformException if the example point cannot be transformed.
     */
    @Test
    public void testLambertConicConformalMichigan() throws FactoryException, TransformException {
        description = "NAD27 / Michigan Central";
        final SamplePoints sample = SamplePoints.forCRS(6201);
        createMathTransform(Projection.class, sample);
        setTolerance(ToleranceModifier.PROJECTION);
        verifyTransform(sample.sourcePoints, sample.targetPoints);
        verifyInDomainOfValidity(sample.areaOfValidity);
    }

    /**
     * Tests the <cite>"Lambert Azimuthal Equal Area"</cite> (EPSG:9820) projection method.
     * First, this method transforms the point given in the <cite>Example</cite> section of the
     * EPSG guidance note and compares the {@link MathTransform} result with the expected result.
     * Next, this method transforms a random set of points in the projection area of validity
     * and ensures that the {@linkplain MathTransform#inverse() inverse transform} and the
     * {@linkplain MathTransform#derivative derivatives} are coherent.
     *
     * <p>The math transform parameters and the sample coordinates are:</p>
     *
     * <div class="horizontal-flow">
     *   <table class="ogc">
     *     <caption>CRS characteristics</caption>
     *     <tr><th>Parameter</th>                   <th>Value</th></tr>
     *     <tr><td>semi-major axis</td>             <td>6378137.0 m</td></tr>
     *     <tr><td>semi-minor axis</td>             <td>6356752.314140284 m</td></tr>
     *     <tr><td>Latitude of natural origin</td>  <td>52.0°</td></tr>
     *     <tr><td>Longitude of natural origin</td> <td>10.0°</td></tr>
     *     <tr><td>False easting</td>               <td>4321000.0 m</td></tr>
     *     <tr><td>False northing</td>              <td>3210000.0 m</td></tr>
     *   </table>
     *   <table class="ogc">
     *     <caption>Test points</caption>
     *     <tr>
     *       <th>Source coordinates</th>
     *       <th>Expected results</th>
     *     </tr><tr class="coordinates">
     *       <td>5°E<br>50°N</td>
     *       <td>3962799.45 m<br>2999718.85 m</td>
     *     </tr><tr class="coordinates">
     *       <td>10°E<br>52°N</td>
     *       <td>4321000.00 m<br>3210000.00 m</td>
     *     </tr>
     *   </table>
     * </div>
     *
     * @throws FactoryException if the math transform cannot be created.
     * @throws TransformException if the example point cannot be transformed.
     *
     * @see AuthorityFactoryTest#testEPSG_3035()
     */
    @Test
    public void testLambertAzimuthalEqualArea() throws FactoryException, TransformException {
        description = "ETRS89 / LAEA Europe";
        final SamplePoints sample = SamplePoints.forCRS(3035);
        createMathTransform(Projection.class, sample);
        setTolerance(ToleranceModifier.PROJECTION);
        verifyTransform(sample.sourcePoints, sample.targetPoints);
        verifyInDomainOfValidity(sample.areaOfValidity);
    }

    /**
     * Tests the <cite>"Polar Stereographic (variant A)"</cite> (EPSG:9810) projection method.
     * First, this method transforms the point given in the <cite>Example</cite> section of the
     * EPSG guidance note and compares the {@link MathTransform} result with the expected result.
     * Next, this method transforms a random set of points in the projection area of validity
     * and ensures that the {@linkplain MathTransform#inverse() inverse transform} and the
     * {@linkplain MathTransform#derivative derivatives} are coherent.
     *
     * <p>The math transform parameters and the sample coordinates are:</p>
     *
     * <div class="horizontal-flow">
     *   <table class="ogc">
     *     <caption>CRS characteristics</caption>
     *     <tr><th>Parameter</th>                      <th>Value</th></tr>
     *     <tr><td>semi-major axis</td>                <td>6378137.0 m</td></tr>
     *     <tr><td>semi-minor axis</td>                <td>6356752.314247833 m</td></tr>
     *     <tr><td>Latitude of natural origin</td>     <td>90.0°</td></tr>
     *     <tr><td>Longitude of natural origin</td>    <td>0.0°</td></tr>
     *     <tr><td>Scale factor at natural origin</td> <td>0.994</td></tr>
     *     <tr><td>False easting</td>                  <td>2000000.0 m</td></tr>
     *     <tr><td>False northing</td>                 <td>2000000.0 m</td></tr>
     *   </table>
     *   <table class="ogc">
     *     <caption>Test points</caption>
     *     <tr>
     *       <th>Source coordinates</th>
     *       <th>Expected results</th>
     *     </tr><tr class="coordinates">
     *       <td>44°E<br>73°N</td>
     *       <td>3320416.75 m<br>632668.43 m</td>
     *     </tr><tr class="coordinates">
     *       <td>0°E<br>90°N</td>
     *       <td>2000000.00 m<br>2000000.00 m</td>
     *     </tr>
     *   </table>
     * </div>
     *
     * @throws FactoryException if the math transform cannot be created.
     * @throws TransformException if the example point cannot be transformed.
     *
     * @see AuthorityFactoryTest#testEPSG_5041()
     * @see AuthorityFactoryTest#testEPSG_32661()
     */
    @Test
    public void testPolarStereographicA() throws FactoryException, TransformException {
        description = "WGS 84 / UPS North (E,N)";
        final SamplePoints sample = SamplePoints.forCRS(5041);
        createMathTransform(Projection.class, sample);
        setTolerance(ToleranceModifier.PROJECTION);
        verifyTransform(sample.sourcePoints, sample.targetPoints);
        verifyInDomainOfValidity(sample.areaOfValidity);
    }

    /**
     * Tests the <cite>"Polar Stereographic (variant B)"</cite> (EPSG:9829) projection method.
     * First, this method transforms the point given in the <cite>Example</cite> section of the
     * EPSG guidance note and compares the {@link MathTransform} result with the expected result.
     * Next, this method transforms a random set of points in the projection area of validity
     * and ensures that the {@linkplain MathTransform#inverse() inverse transform} and the
     * {@linkplain MathTransform#derivative derivatives} are coherent.
     *
     * <p>The math transform parameters and the sample coordinates are:</p>
     *
     * <div class="horizontal-flow">
     *   <table class="ogc">
     *     <caption>CRS characteristics</caption>
     *     <tr><th>Parameter</th>                     <th>Value</th></tr>
     *     <tr><th>Source coordinates</th>              <th>Expected results</th></tr>
     *     <tr><td>semi-major axis</td>               <td>6378137.0 m</td></tr>
     *     <tr><td>semi-minor axis</td>               <td>6356752.314247833 m</td></tr>
     *     <tr><td>Latitude of standard parallel</td> <td>-71.0°</td></tr>
     *     <tr><td>Longitude of origin</td>           <td>70.0°</td></tr>
     *     <tr><td>False easting</td>                 <td>6000000.0 m</td></tr>
     *     <tr><td>False northing</td>                <td>6000000.0 m</td></tr>
     *   </table>
     *   <table class="ogc">
     *     <caption>Test points</caption>
     *     <tr>
     *       <th>Source coordinates</th>
     *       <th>Expected results</th>
     *     </tr><tr class="coordinates">
     *       <td>120°E<br>75°S</td>
     *       <td>7255380.79 m<br>7053389.56 m</td>
     *     </tr><tr class="coordinates">
     *       <td>70°E<br>90°S</td>
     *       <td>6000000.00 m<br>6000000.00 m</td>
     *     </tr>
     *   </table>
     * </div>
     *
     * @throws FactoryException if the math transform cannot be created.
     * @throws TransformException if the example point cannot be transformed.
     *
     * @see AuthorityFactoryTest#testEPSG_3032()
     */
    @Test
    public void testPolarStereographicB() throws FactoryException, TransformException {
        description = "Australian Antarctic Polar Stereographic";
        final SamplePoints sample = SamplePoints.forCRS(3032);
        createMathTransform(Projection.class, sample);
        setTolerance(ToleranceModifier.PROJECTION);
        verifyTransform(sample.sourcePoints, sample.targetPoints);
        verifyInDomainOfValidity(sample.areaOfValidity);
    }

    /**
     * Tests the <cite>"Polar Stereographic (variant C)"</cite> (EPSG:9830) projection method.
     * First, this method transforms the point given in the <cite>Example</cite> section of the
     * EPSG guidance note and compares the {@link MathTransform} result with the expected result.
     * Next, this method transforms a random set of points in the projection area of validity
     * and ensures that the {@linkplain MathTransform#inverse() inverse transform} and the
     * {@linkplain MathTransform#derivative derivatives} are coherent.
     *
     * <p>The math transform parameters and the sample coordinates are:</p>
     *
     * <div class="horizontal-flow">
     *   <table class="ogc">
     *     <caption>CRS characteristics</caption>
     *     <tr><th>Parameter</th>                     <th>Value</th></tr>
     *     <tr><th>Source coordinates</th>              <th>Expected results</th></tr>
     *     <tr><td>semi-major axis</td>               <td>6378388.0 m</td></tr>
     *     <tr><td>semi-minor axis</td>               <td>6356911.9461279465 m</td></tr>
     *     <tr><td>Latitude of standard parallel</td> <td>-67°</td></tr>
     *     <tr><td>Longitude of origin</td>           <td>140°</td></tr>
     *     <tr><td>False easting</td>                 <td>300000 m</td></tr>
     *     <tr><td>False northing</td>                <td>200000 m</td></tr>
     *   </table>
     *   <table class="ogc">
     *     <caption>Test points</caption>
     *     <tr>
     *       <th>Source coordinates</th>
     *       <th>Expected results</th>
     *     </tr><tr class="coordinates">
     *       <td>140°04'17.040"E<br>66°36'18.820"S</td>
     *       <td>303169.52 m<br>244055.72 m</td>
     *     </tr><tr class="coordinates">
     *       <td>67°E<br>90°S</td>
     *       <td>300000.00 m<br>200000.00 m</td>
     *     </tr>
     *   </table>
     * </div>
     *
     * @throws FactoryException if the math transform cannot be created.
     * @throws TransformException if the example point cannot be transformed.
     *
     * @see AuthorityFactoryTest#testEPSG_3032()
     */
    @Test
    public void testPolarStereographicC() throws FactoryException, TransformException {
        description = "Petrels 1972 / Terre Adelie Polar Stereographic";
        final SamplePoints sample = SamplePoints.forCRS(2985);
        createMathTransform(Projection.class, sample);
        setTolerance(ToleranceModifier.PROJECTION);
        verifyTransform(sample.sourcePoints, sample.targetPoints);
        verifyInDomainOfValidity(sample.areaOfValidity);
    }

    /**
     * Tests the <cite>"Oblique Stereographic"</cite> (EPSG:9809) projection method.
     * First, this method transforms the point given in the <cite>Example</cite> section of the
     * EPSG guidance note and compares the {@link MathTransform} result with the expected result.
     * Next, this method transforms a random set of points in the projection area of validity
     * and ensures that the {@linkplain MathTransform#inverse() inverse transform} and the
     * {@linkplain MathTransform#derivative derivatives} are coherent.
     *
     * <p>The math transform parameters and the sample coordinates are:</p>
     *
     * <div class="horizontal-flow">
     *   <table class="ogc">
     *     <caption>CRS characteristics</caption>
     *     <tr><th>Parameter</th>                      <th>Value</th></tr>
     *     <tr><td>semi-major axis</td>                <td>6377397.155 m</td></tr>
     *     <tr><td>semi-minor axis</td>                <td>6356078.9626186555 m</td></tr>
     *     <tr><td>Latitude of natural origin</td>     <td>52.15616055555556°</td></tr>
     *     <tr><td>Longitude of natural origin</td>    <td>5.38763888888889°</td></tr>
     *     <tr><td>Scale factor at natural origin</td> <td>0.9999079</td></tr>
     *     <tr><td>False easting</td>                  <td>155000.0 m</td></tr>
     *     <tr><td>False northing</td>                 <td>463000.0 m</td></tr>
     *   </table>
     *   <table class="ogc">
     *     <caption>Test points</caption>
     *     <tr>
     *       <th>Source coordinates</th>
     *       <th>Expected results</th>
     *     </tr><tr class="coordinates">
     *       <td>6°E<br>53°N</td>
     *       <td>196105.283 m<br>557057.739 m</td>
     *     </tr><tr class="coordinates">
     *       <td>5°23'15.500"E<br>52°09'22.178"N</td>
     *       <td>155000.000 m<br>463000.000 m</td>
     *     </tr>
     *   </table>
     * </div>
     *
     * @throws FactoryException if the math transform cannot be created.
     * @throws TransformException if the example point cannot be transformed.
     *
     * @see AuthorityFactoryTest#testEPSG_28992()
     */
    @Test
    public void testObliqueStereographic() throws FactoryException, TransformException {
        description = "Amersfoort / RD New";
        final SamplePoints sample = SamplePoints.forCRS(28992);
        createMathTransform(Projection.class, sample);
        setTolerance(ToleranceModifier.PROJECTION);
        verifyTransform(sample.sourcePoints, sample.targetPoints);
        verifyInDomainOfValidity(sample.areaOfValidity);
    }

    /**
     * Tests the <cite>"American Polyconic"</cite> (EPSG:9818) projection.
     * First, this method transforms the some of the points given in Table 19, p 132 of
     * <a href="http://pubs.er.usgs.gov/usgspubs/pp/pp1395">Map Projections, a working manual</a>
     * by John P.Snyder. Next, this method transforms a random set of points in the projection
     * area of validity and ensures that the {@linkplain MathTransform#inverse() inverse transform}
     * and the {@linkplain MathTransform#derivative derivatives} are coherent.
     *
     * <p>The math transform parameters and the sample coordinates are:</p>
     *
     * <div class="horizontal-flow">
     *   <table class="ogc">
     *     <caption>CRS characteristics</caption>
     *     <tr><th>Parameter</th>                   <th>Value</th></tr>
     *     <tr><td>semi-major axis</td>             <td>6378206.4 m</td></tr>
     *     <tr><td>semi-minor axis</td>             <td>6356583.8 m</td></tr>
     *     <tr><td>Latitude of natural origin</td>  <td>0.0°</td></tr>
     *     <tr><td>Longitude of natural origin</td> <td>0.0°</td></tr>
     *     <tr><td>False easting</td>               <td>0.0 m</td></tr>
     *     <tr><td>False northing</td>              <td>0.0 m</td></tr>
     *   </table>
     *   <table class="ogc">
     *     <caption>Test points</caption>
     *     <tr>
     *       <th>Source coordinates</th>
     *       <th>Expected results</th>
     *     </tr><tr class="coordinates">
     *       <td>See source</td>
     *       <td>See source</td>
     *     </tr>
     *   </table>
     * </div>
     *
     * @throws FactoryException if the math transform cannot be created.
     * @throws TransformException if the example point cannot be transformed.
     */
    @Test
    public void testPolyconic() throws FactoryException, TransformException {
        tolerance = max(tolerance, 0.5);                        // The sample points are only accurate to 1 metre.
        description = "American Polyconic";
        final SamplePoints sample = SamplePoints.forCRS(9818);
        createMathTransform(Projection.class, sample);
        setTolerance(ToleranceModifier.PROJECTION);
        verifyTransform(sample.sourcePoints, sample.targetPoints);
        verifyInDomainOfValidity(sample.areaOfValidity);
    }

    /**
     * Tests the <cite>"Krovak"</cite> (EPSG:9819) projection.
     * First, this method transforms the point given in the <cite>Example</cite> section of the
     * EPSG guidance note and compares the {@link MathTransform} result with the expected result.
     * Next, this method transforms a random set of points in the projection area of validity
     * and ensures that the {@linkplain MathTransform#inverse() inverse transform} and the
     * {@linkplain MathTransform#derivative derivatives} are coherent.
     *
     * <p>The math transform parameters and the sample coordinates are:</p>
     *
     * <div class="horizontal-flow">
     *   <table class="ogc">
     *     <caption>CRS characteristics</caption>
     *     <tr><th>Parameter</th>                                <th>Value</th></tr>
     *     <tr><td>semi-major axis</td>                          <td>6377397.155 m</td></tr>
     *     <tr><td>semi-minor axis</td>                          <td>6356078.9626186555 m</td></tr>
     *     <tr><td>Latitude of projection centre</td>            <td>49.5°</td></tr>
     *     <tr><td>Longitude of origin</td>                      <td>24.5°</td></tr>
     *     <tr><td>Co-latitude of cone axis</td>                 <td>30.288139722222222°</td></tr>
     *     <tr><td>Latitude of pseudo standard parallel</td>     <td>78.5°</td></tr>
     *     <tr><td>Scale factor on pseudo standard parallel</td> <td>0.9999</td></tr>
     *     <tr><td>False easting</td>                            <td>0.0 m</td></tr>
     *     <tr><td>False northing</td>                           <td>0.0 m</td></tr>
     *   </table>
     *   <table class="ogc">
     *     <caption>Test points</caption>
     *     <tr>
     *       <th>Source coordinates</th>
     *       <th>Expected results</th>
     *     </tr><tr class="coordinates">
     *       <td>16°50'59.179"E<br>50°12'32.442"N</td>
     *       <td>-568990.997 m<br>-1050538.643 m</td>
     *     </tr>
     *   </table>
     * </div>
     *
     * @throws FactoryException if the math transform cannot be created.
     * @throws TransformException if the example point cannot be transformed.
     *
     * @see AuthorityFactoryTest#testEPSG_2065()
     */
    @Test
    public void testKrovak() throws FactoryException, TransformException {
        description = "CRS S-JTSK (Ferro) / Krovak";
        final SamplePoints sample = SamplePoints.forCRS(2065);
        createMathTransform(Projection.class, sample);
        setTolerance(ToleranceModifier.PROJECTION);
        verifyTransform(sample.sourcePoints, sample.targetPoints);
        verifyInDomainOfValidity(sample.areaOfValidity);
    }

    /**
     * Tests the <cite>"Orthographic"</cite> (EPSG:9840) projection.
     * First, this method transforms the point given in the <cite>Example</cite> section of the
     * EPSG guidance note and compares the {@link MathTransform} result with the expected result.
     * Next, this method transforms a random set of points in the projection area of validity
     * and ensures that the {@linkplain MathTransform#inverse() inverse transform} and the
     * {@linkplain MathTransform#derivative derivatives} are coherent.
     *
     * <p>The math transform parameters and the sample coordinates are:</p>
     *
     * <div class="horizontal-flow">
     *   <table class="ogc">
     *     <caption>CRS characteristics</caption>
     *     <tr><th>Parameter</th>                                <th>Value</th></tr>
     *     <tr><td>semi-major axis</td>                          <td>6378137.0 m</td></tr>
     *     <tr><td>semi-minor axis</td>                          <td>6356752.314247833 m</td></tr>
     *     <tr><td>Latitude of natural origin</td>               <td>55.0°</td></tr>
     *     <tr><td>Longitude of natural origin</td>              <td>5.0°</td></tr>
     *     <tr><td>False easting</td>                            <td>0.0 m</td></tr>
     *     <tr><td>False northing</td>                           <td>0.0 m</td></tr>
     *   </table>
     *   <table class="ogc">
     *     <caption>Test points</caption>
     *     <tr>
     *       <th>Source coordinates</th>
     *       <th>Expected results</th>
     *     </tr><tr class="coordinates">
     *       <td>2°07'46.38"E<br>53°48'33.82"N</td>
     *       <td>–189011.711 m<br>–128 640.567 m</td>
     *     </tr>
     *   </table>
     * </div>
     *
     * @throws FactoryException if the math transform cannot be created.
     * @throws TransformException if the example point cannot be transformed.
     */
    @Test
    public void testOrthographic() throws FactoryException, TransformException {
        description = "WGS 84 / Orthographic";
        final SamplePoints sample = SamplePoints.forCRS(9840);
        createMathTransform(Projection.class, sample);
        setTolerance(ToleranceModifier.PROJECTION);
        verifyTransform(sample.sourcePoints, sample.targetPoints);
        verifyInDomainOfValidity(sample.areaOfValidity);
    }

    /**
     * Tests the <cite>"Modified Azimuthal Equidistant"</cite> (EPSG:9832) projection.
     * First, this method transforms the point given in the <cite>Example</cite> section of the
     * EPSG guidance note and compares the {@link MathTransform} result with the expected result.
     * Next, this method transforms a random set of points in the projection area of validity
     * and ensures that the {@linkplain MathTransform#inverse() inverse transform} and the
     * {@linkplain MathTransform#derivative derivatives} are coherent.
     *
     * <p>The math transform parameters and the sample coordinates are:</p>
     *
     * <div class="horizontal-flow">
     *   <table class="ogc">
     *     <caption>CRS characteristics</caption>
     *     <tr><th>Parameter</th>                                <th>Value</th></tr>
     *     <tr><td>semi-major axis</td>                          <td>6378206.4 m</td></tr>
     *     <tr><td>semi-minor axis</td>                          <td>6356583.8 m</td></tr>
     *     <tr><td>Latitude of natural origin</td>               <td>9.546708333333333°</td></tr>
     *     <tr><td>Longitude of natural origin</td>              <td>138.168744444444444°</td></tr>
     *     <tr><td>False easting</td>                            <td>40000.0 m</td></tr>
     *     <tr><td>False northing</td>                           <td>60000.0 m</td></tr>
     *   </table>
     *   <table class="ogc">
     *     <caption>Test points</caption>
     *     <tr>
     *       <th>Source coordinates</th>
     *       <th>Expected results</th>
     *     </tr><tr class="coordinates">
     *       <td>138°11'34.908"E<br>9°35'47.493"N</td>
     *       <td>42665.90 m<br>65509.82 m</td>
     *     </tr>
     *   </table>
     * </div>
     *
     * @throws FactoryException if the math transform cannot be created.
     * @throws TransformException if the example point cannot be transformed.
     */
    @Test
    public void testModifiedAzimuthalEquidistant() throws FactoryException, TransformException {
        description = "Guam 1963 / Yap Islands";
        final SamplePoints sample = SamplePoints.forCRS(3295);
        createMathTransform(Projection.class, sample);
        setTolerance(ToleranceModifier.PROJECTION);
        verifyTransform(sample.sourcePoints, sample.targetPoints);
        verifyInDomainOfValidity(sample.areaOfValidity);
    }

    /**
     * Tests the <cite>"Abridged Molodensky"</cite> (EPSG:9605) datum shift operation.
     * First, this method transforms the point given in the <cite>Example</cite> section of the
     * EPSG guidance note and compares the {@link MathTransform} result with the expected result.
     * Next, this method transforms a random set of geographic coordinates
     * and ensures that the {@linkplain MathTransform#inverse() inverse transform} and the
     * {@linkplain MathTransform#derivative derivatives} are coherent.
     *
     * <p>The math transform parameters and the sample coordinates are:</p>
     *
     * <div class="horizontal-flow">
     *   <table class="ogc">
     *     <caption>Conversion characteristics</caption>
     *     <tr><th>Parameter</th>                         <th>Value</th></tr>
     *     <tr><td>dim</td>                               <td>3</td></tr>
     *     <tr><td>src_semi_major</td>                    <td>6378137.0 m</td></tr>
     *     <tr><td>src_semi_minor</td>                    <td>6356752.314247833 m</td></tr>
     *     <tr><td>X-axis translation</td>                <td>84.87 m</td></tr>
     *     <tr><td>Y-axis translation</td>                <td>96.49 m</td></tr>
     *     <tr><td>Z-axis translation</td>                <td>116.95 m</td></tr>
     *     <tr><td>Semi-major axis length difference</td> <td>251 m</td></tr>
     *     <tr><td>Flattening difference</td>             <td>1.41927E-05</td></tr>
     *   </table>
     *   <table class="ogc">
     *     <caption>Test points</caption>
     *     <tr>
     *       <th>Source coordinates</th>
     *       <th>Expected results</th>
     *     </tr><tr class="coordinates">
     *       <td>2°7'46.380"E<br>53°48'33.820"N<br>73.000 m</td>
     *       <td>2°7'51.477"E<br>53°48'36.563"N<br>28.091 m</td>
     *     </tr>
     *   </table>
     * </div>
     *
     * @throws FactoryException if the math transform cannot be created.
     * @throws TransformException if the example point cannot be transformed.
     */
    @Test
    public void testAbridgedMolodensky() throws FactoryException, TransformException {
        tolerance = max(tolerance, 0.001 * (NAUTICAL_MILE/60));    // 0.001 angular second (about 3 cm at equator).
        description = "WGS84 to ED50";
        final SamplePoints sample = SamplePoints.forCRS(4230);
        createMathTransform(Transformation.class, sample);
        setTolerance(ToleranceModifier.GEOGRAPHIC);
        verifyTransform(sample.sourcePoints, sample.targetPoints);
        verifyInDomain3D(sample.areaOfValidity, -1000, +1000);
    }

    /**
     * Tests the <cite>"Geographic/topocentric conversions"</cite> (EPSG:9837).
     * This method transforms the point given in the <cite>Example</cite> section of the
     * EPSG guidance note and compares the {@link MathTransform} result with the expected result.
     *
     * <p>A CRS using this method is "EPSG topocentric example A" (EPSG:5819)".
     * The math transform parameters and the sample coordinates are:</p>
     *
     * <div class="horizontal-flow">
     *   <table class="ogc">
     *     <caption>Conversion characteristics</caption>
     *     <tr><th>Parameter</th>                                <th>Value</th></tr>
     *     <tr><td>semi_major</td>                               <td>6378137.0 m</td></tr>
     *     <tr><td>semi_minor</td>                               <td>6356752.314245179 m</td></tr>
     *     <tr><td>Latitude of topocentric origin</td>           <td>55°00'00.000"N</td></tr>
     *     <tr><td>Longitude of topocentric origin</td>          <td>5°00'00.000"E</td></tr>
     *     <tr><td>Ellipsoidal height of topocentric origin</td> <td>200 m</td></tr>
     *   </table>
     *   <table class="ogc">
     *     <caption>Test points</caption>
     *     <tr>
     *       <th>Source coordinates</th>
     *       <th>Expected results</th>
     *     </tr><tr class="coordinates">
     *       <td>2°07'46.38"E<br>53°48'33.82"N<br>73.0 m</td>
     *       <td>–189013.869 m<br>–128642.040 m<br>–4220.171 m</td>
     *     </tr><tr class="coordinates">
     *       <td>5°00'00.000"E<br>55°00'00.000"N<br>200 m</td>
     *       <td>0 m<br>0 m<br>0 m</td>
     *     </tr>
     *   </table>
     * </div>
     *
     * @throws FactoryException if the math transform cannot be created.
     * @throws TransformException if the example point cannot be transformed.
     */
    @Test
    public void testGeographicTopocentric() throws FactoryException, TransformException {
        description = "EPSG topocentric example A";
        final SamplePoints sample = SamplePoints.forCRS(5819);
        createMathTransform(Transformation.class, sample);
        setTolerance(null);
        verifyTransform(sample.sourcePoints, sample.targetPoints);
        verifyInDomain3D(sample.areaOfValidity, -100, +100);
    }

    /**
     * Tests the <cite>"Geocentric/topocentric conversions"</cite> (EPSG:9836).
     * This method transforms the point given in the <cite>Example</cite> section of the
     * EPSG guidance note and compares the {@link MathTransform} result with the expected result.
     *
     * <p>A CRS using this method is "EPSG topocentric example B" (EPSG:5820)".
     * The math transform parameters and the sample coordinates are:</p>
     *
     * <div class="horizontal-flow">
     *   <table class="ogc">
     *     <caption>Conversion characteristics</caption>
     *     <tr><th>Parameter</th>                          <th>Value</th></tr>
     *     <tr><td>semi_major</td>                         <td>6378137.0 m</td></tr>
     *     <tr><td>semi_minor</td>                         <td>6356752.314245179 m</td></tr>
     *     <tr><td>Geocentric X of topocentric origin</td> <td>3652755.3058 m</td></tr>
     *     <tr><td>Geocentric Y of topocentric origin</td> <td> 319574.6799 m</td></tr>
     *     <tr><td>Geocentric Z of topocentric origin</td> <td>5201547.3536 m</td></tr>
     *   </table>
     *   <table class="ogc">
     *     <caption>Test points</caption>
     *     <tr>
     *       <th>Source coordinates</th>
     *       <th>Expected results</th>
     *     </tr><tr class="coordinates">
     *       <td>3771793.968 m<br> 140253.342 m<br>5124304.349 m</td>
     *       <td>–189013.869 m<br>–128642.040 m<br>  –4220.171 m</td>
     *     </tr><tr class="coordinates">
     *       <td>3652755.3058 m<br>319574.6799 m<br>5201547.3536 m</td>
     *       <td>0 m<br>0 m<br>0 m</td>
     *     </tr>
     *   </table>
     * </div>
     *
     * @throws FactoryException if the math transform cannot be created.
     * @throws TransformException if the example point cannot be transformed.
     */
    @Test
    public void testGeocentricTopocentric() throws FactoryException, TransformException {
        description = "EPSG topocentric example B";
        final SamplePoints sample = SamplePoints.forCRS(5820);
        createMathTransform(Transformation.class, sample);
        setTolerance(null);
        verifyTransform(sample.sourcePoints, sample.targetPoints);
        verifyInDomain3D(sample.areaOfValidity, 5000000, 5200000);
    }

    /**
     * Executes {@link #verifyInDomain(double[], double[], int[], Random)} using a three-dimensional domain.
     *
     * @param  areaOfValidity  the horizontal domain of test points.
     * @param  zmin            the vertical minimum value of test points.
     * @param  zmax            the vertical maximum value of test points.
     * @throws TransformException if a point cannot be transformed.
     */
    private void verifyInDomain3D(final Rectangle2D areaOfValidity, final double zmin, final double zmax)
            throws TransformException
    {
        verifyInDomain(new double[] {areaOfValidity.getMinX(), areaOfValidity.getMinY(), zmin},
                       new double[] {areaOfValidity.getMaxX(), areaOfValidity.getMaxY(), zmax},
                       new int[] {10, 10, 10}, new Random());
    }

    /**
     * Asserts that a matrix of derivatives is equal to the expected ones within a positive delta.
     */
    @Override
    protected void assertMatrixEquals(final String message, final Matrix expected, final Matrix actual, final Matrix tolmat)
            throws DerivativeFailure
    {
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
}
