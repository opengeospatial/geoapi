/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2024 Open Geospatial Consortium, Inc.
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

import java.util.Random;
import java.util.Arrays;
import java.awt.geom.Point2D;

import org.opengis.geometry.DirectPosition;
import org.opengis.referencing.operation.Matrix;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.MathTransform1D;
import org.opengis.referencing.operation.MathTransform2D;
import org.opengis.referencing.operation.TransformException;
import org.opengis.test.ToleranceModifiers;
import org.opengis.test.ToleranceModifier;
import org.opengis.test.CalculationType;
import org.opengis.test.Configuration;

import org.opengis.test.TestCase;

import static java.lang.StrictMath.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.opengis.test.Assertions.assertStrictlyPositive;


/**
 * Base class for {@link MathTransform} implementation tests. Subclasses shall assign a value
 * to the {@link #transform} field before to invoke any method in this class. The specified
 * math transform shall support the following mandatory operations:
 *
 * <ul>
 *   <li>{@link MathTransform#getSourceDimensions()}</li>
 *   <li>{@link MathTransform#getTargetDimensions()}</li>
 *   <li>{@link MathTransform#transform(DirectPosition, DirectPosition)}</li>
 * </ul>
 *
 * All other operations are optional. However subclasses shall declare which methods, if any,
 * are unsupported. By default every operations are assumed supported. Tests can be disabled
 * on a case-by-case basis by setting the appropriate
 * <code>is&lt;<var>Operation</var>&gt;Supported</code> fields to {@code false}.
 *
 * <p>After {@code TransformTestCase} has been setup, subclasses can invoke any of the {@code verify}
 * methods in their JUnit test methods. Callers must supply the input coordinate points to be used
 * for testing purpose, since the range of valid values is usually transform-dependent.</p>
 *
 * <p>Some general rules:</p>
 * <ul>
 *   <li>Coordinate values, or information used for computing coordinate values, are always
 *       specified as arguments to the {@code verify} methods. Everything else are fields in
 *       the {@code TransformTestCase} object.</li>
 *   <li>The methods in this class do not {@linkplain org.opengis.test.Validators#validate(MathTransform)
 *       validate} the transform. It is caller responsibility to validate the transform if wanted.</li>
 *   <li>Unless otherwise indicated, every {@code verify} methods are independent. For example, invoking
 *       {@link #verifyConsistency(float[])} does not imply a call to {@link #verifyInverse(float[])}
 *       or {@link #verifyDerivative(double[])}. The later methods must be invoked explicitly if wanted.</li>
 * </ul>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   2.2
 */
@SuppressWarnings("strictfp")   // Because we still target Java 11.
public strictfp abstract class TransformTestCase extends TestCase {
    /**
     * The maximal offset (in number of coordinate points), exclusive, to apply when testing
     * {@code MathTransform.transform(…)} with overlapping arrays.  Higher values make the
     * tests more extensive but slower. Small values like 8 are usually enough.
     */
    private static final int POINTS_OFFSET = 8;

    /**
     * The transform being tested. Subclasses should assign a value to this field,
     * together with the {@link #tolerance} field, before any test is run.
     *
     * <p>All {@link ParameterizedTransformTest} test methods will set this field to a non-null value.
     * Implementers can use this value for their own assertions after any test method has been run.</p>
     *
     * @see #tolerance
     */
    protected MathTransform transform;

    /**
     * {@code true} if {@link MathTransform#transform(double[],int,double[],int,int)}
     * is supported. The default value is {@code true}. Vendor can set this value to
     * {@code false} in order to test a transform which is not fully implemented.
     *
     * @see #isFloatToFloatSupported
     * @see #isDoubleToFloatSupported
     * @see #isFloatToDoubleSupported
     * @see #verifyConsistency(float[])
     */
    protected boolean isDoubleToDoubleSupported;

    /**
     * {@code true} if {@link MathTransform#transform(float[],int,float[],int,int)}
     * is supported. The default value is {@code true}. Vendor can set this value to
     * {@code false} in order to test a transform which is not fully implemented.
     *
     * @see #isDoubleToDoubleSupported
     * @see #isDoubleToFloatSupported
     * @see #isFloatToDoubleSupported
     * @see #verifyConsistency(float[])
     */
    protected boolean isFloatToFloatSupported;

    /**
     * {@code true} if {@link MathTransform#transform(double[],int,float[],int,int)}
     * is supported. The default value is {@code true}. Vendor can set this value to
     * {@code false} in order to test a transform which is not fully implemented.
     *
     * @see #isDoubleToDoubleSupported
     * @see #isFloatToFloatSupported
     * @see #isFloatToDoubleSupported
     * @see #verifyConsistency(float[])
     */
    protected boolean isDoubleToFloatSupported;

    /**
     * {@code true} if {@link MathTransform#transform(float[],int,double[],int,int)}
     * is supported. The default value is {@code true}. Vendor can set this value to
     * {@code false} in order to test a transform which is not fully implemented.
     *
     * @see #isDoubleToDoubleSupported
     * @see #isFloatToFloatSupported
     * @see #isDoubleToFloatSupported
     * @see #verifyConsistency(float[])
     */
    protected boolean isFloatToDoubleSupported;

    /**
     * {@code true} if the destination array can be the same as the source array,
     * and the source and target region of the array can overlap. The default value
     * is {@code true}. Vendor can set this value to {@code false} in order to test
     * a transform which is not fully implemented.
     *
     * @see #verifyConsistency(float[])
     */
    protected boolean isOverlappingArraySupported;

    /**
     * {@code true} if {@link MathTransform#inverse()} is supported. The default value
     * is {@code true}. Vendor can set this value to {@code false} in order to test a
     * transform which is not fully implemented.
     *
     * @see #verifyTransform(double[], double[])
     */
    protected boolean isInverseTransformSupported;

    /**
     * {@code true} if {@link MathTransform#derivative(DirectPosition)} is supported. The
     * default value is {@code true}. Vendor can set this value to {@code false} in order
     * to test a transform which is not fully implemented.
     *
     * @see #derivativeDeltas
     * @see #verifyDerivative(double[])
     *
     * @since 3.1
     */
    protected boolean isDerivativeSupported;

    /**
     * The deltas to use for approximating {@linkplain MathTransform#derivative(DirectPosition) math
     * transform derivatives} by the <a href="http://en.wikipedia.org/wiki/Finite_difference">finite
     * differences</a> method. Each value in this array is the delta to use for the corresponding
     * dimension, in units of the source coordinates of the {@linkplain #transform} being tested.
     * The array length is theoretically the {@linkplain MathTransform#getSourceDimensions() number
     * of source dimensions}, but different lengths are accepted for developers convenience. If the
     * array is smaller than the number of dimensions, then the last delta value will be reused for
     * all remaining dimensions.
     *
     * <p>Testers shall provide a non-null value if the {@link #isDerivativeSupported} flag is set to
     * {@code true}. Smaller delta would theoretically increase the finite difference precision.
     * However in practice too small deltas <em>decrease</em> the precision, because of floating
     * point errors when subtracting big numbers that are close in magnitude. In the particular
     * case of map projections, experience suggests that a distance of 100 metres converted to
     * decimal degrees is a good compromise. The conversion from metres to degrees can be done using
     * the standard nautical mile length ({@value org.opengis.test.ToleranceModifiers#NAUTICAL_MILE}
     * metres by minute of angle) as below:</p>
     *
     * {@snippet lang="java" :
     * derivativeDeltas = new double[] {100.0 / (60 * 1852)};      // Approximately 100 metres.
     * }
     *
     * @see #isDerivativeSupported
     * @see #verifyDerivative(double[])
     *
     * @since 3.1
     */
    protected double[] derivativeDeltas;

    /**
     * Maximum difference to be accepted when comparing a transformed coordinate value with
     * the expected value. By default this threshold is constant for all dimensions of all
     * coordinates to be compared. If a subclass needs to adjust the tolerance threshold
     * according the dimension or the coordinates values, then it should assign a value to
     * the {@link #toleranceModifier} field in addition to this one.
     *
     * <p><b>Example:</b> the comparisons of geographic coordinates require increasing tolerance
     * in longitude values as the latitude get closer to a pole. For such comparisons, this
     * {@code tolerance} field shall be set to a threshold value in <em>metres</em> and the
     * {@link #toleranceModifier} field shall be assigned the {@link ToleranceModifier#GEOGRAPHIC}
     * value. See the {@code GEOGRAPHIC} modifier javadoc for more information.</p>
     *
     * <p>The default value is 0, which means that strict equality will be required. Subclasses
     * should set a more suitable tolerance threshold when {@linkplain #transform} is assigned
     * a value.</p>
     *
     * @see #transform
     * @see #toleranceModifier
     */
    protected double tolerance;

    /**
     * Optional modification to the {@linkplain #tolerance} threshold before to compare a
     * coordinate tuple. {@link ToleranceModifier} instance assigned to this field (if any)
     * are {@linkplain #transform}-dependent. The modifications applied by a particular
     * {@code ToleranceModifier} instance to the tolerance thresholds can be position-dependent.
     *
     * <p>Common values assigned to this field are {@link ToleranceModifier#PROJECTION} and
     * {@link ToleranceModifier#GEOGRAPHIC}.</p>
     *
     * @since 3.1
     */
    protected ToleranceModifier toleranceModifier;

    /**
     * Cached information for {@link #getToleranceModifier()} purpose. The {@code cachedModifier}
     * field contains the value returned by {@code getToleranceModifier()}, cached because needed
     * every time an {@code assertCoordinateEquals(…)} method is invoked (which typically occur
     * often).  The {@link #modifierUsedByCache} and {@link #transformUsedByCache} fields contain
     * the values of {@link #toleranceModifier} and {@link #transform} respectively at the time
     * the {@code cachedModifier} value has been computed. They are used in order to detect when
     * {@code cachedModifier} needs to be recalculated.
     *
     * @see #getToleranceModifier()
     */
    private transient ToleranceModifier cachedModifier, modifierUsedByCache;

    /**
     * Cached information for {@link #getToleranceModifier()} purpose.
     * See the {@link #cachedModifier} javadoc for more information.
     *
     * @see #getToleranceModifier()
     */
    private transient MathTransform transformUsedByCache;

    /**
     * {@code true} if the {@link #getToleranceModifier()} method found at least one
     * {@link ToleranceModifier} registered on the classpath. We presume that the
     * implementer specified such tolerance modifier in order to relax the tolerance
     * threshold.
     */
    private boolean isToleranceRelaxed;

    /**
     * Creates a new test without factory and with the given {@code isFooSupported} flags.
     * The given array must be the result of a call to {@link #getEnabledKeys(int)}.
     *
     * @param  isEnabled  the enabled status of all options.
     */
    TransformTestCase(final boolean[] isEnabled) {
        setEnabledFlags(isEnabled);
    }

    /**
     * Creates a test case initialized to default values. The {@linkplain #transform}
     * is initially null, the {@linkplain #tolerance} threshold is initially zero and
     * all <code>is&lt;</code><var>Operation</var><code>&gt;Supported</code> are set
     * to {@code true}.
     */
    @SuppressWarnings("this-escape")
    protected TransformTestCase() {
        setEnabledFlags(getEnabledFlags(getEnabledKeys(0)));
    }

    /**
     * Sets all {@code isFooSupported} fields to the values in the given array.
     * The given array must be the result of a call to {@link #getEnabledKeys(int)}.
     *
     * <p>This work is usually performed right into the constructor. However in the particular case
     * of {@code TransformTestCase}, we allow the configuration to be supplied externally because
     * {@link AuthorityFactoryTest} will use this class internally with a set of flags determined
     * from a different set of factories than the factories given to the constructor of this class.</p>
     *
     * @param  isEnabled  the enabled status of all options.
     */
    private void setEnabledFlags(final boolean[] isEnabled) {
        isDoubleToDoubleSupported   = isEnabled[0];
        isFloatToFloatSupported     = isEnabled[1];
        isDoubleToFloatSupported    = isEnabled[2];
        isFloatToDoubleSupported    = isEnabled[3];
        isOverlappingArraySupported = isEnabled[4];
        isInverseTransformSupported = isEnabled[5];
        isDerivativeSupported       = isEnabled[6];
    }

    /**
     * {@return the keys to gives to the {@link #setEnabledFlags(boolean[])} method}.
     * The elements in the returned array <strong>must</strong> be in the order expected
     * by the {@link #setEnabledFlags(boolean[])} method for setting the fields.
     *
     * @param  extraSpace  additional empty slots to allocate at the end of the returned array.
     */
    static Configuration.Key<Boolean>[] getEnabledKeys(final int extraSpace) {
        @SuppressWarnings({"unchecked","rawtypes"})
        final Configuration.Key<Boolean>[] keys = new Configuration.Key[7 + extraSpace];
        keys[0] = Configuration.Key.isDoubleToDoubleSupported;
        keys[1] = Configuration.Key.isFloatToFloatSupported;
        keys[2] = Configuration.Key.isDoubleToFloatSupported;
        keys[3] = Configuration.Key.isFloatToDoubleSupported;
        keys[4] = Configuration.Key.isOverlappingArraySupported;
        keys[5] = Configuration.Key.isInverseTransformSupported;
        keys[6] = Configuration.Key.isDerivativeSupported;
        return keys;
    }

    /**
     * Returns information about the configuration of the test which has been run.
     * This method returns a map containing:
     *
     * <ul>
     *   <li>All the following keys defined in the {@link org.opengis.test.Configuration.Key} enumeration,
     *       associated to the value {@link Boolean#TRUE} or {@link Boolean#FALSE}:
     *     <ul>
     *       <li>{@link #isDoubleToDoubleSupported}</li>
     *       <li>{@link #isFloatToFloatSupported}</li>
     *       <li>{@link #isDoubleToFloatSupported}</li>
     *       <li>{@link #isFloatToDoubleSupported}</li>
     *       <li>{@link #isOverlappingArraySupported}</li>
     *       <li>{@link #isInverseTransformSupported}</li>
     *       <li>{@link #isDerivativeSupported}</li>
     *     </ul>
     *   </li>
     * </ul>
     *
     * @return {@inheritDoc}
     *
     * @since 3.1
     */
    @Override
    public Configuration configuration() {
        final Configuration op = super.configuration();
        assertNull(op.put(Configuration.Key.isDoubleToDoubleSupported,   isDoubleToDoubleSupported));
        assertNull(op.put(Configuration.Key.isFloatToFloatSupported,     isFloatToFloatSupported));
        assertNull(op.put(Configuration.Key.isDoubleToFloatSupported,    isDoubleToFloatSupported));
        assertNull(op.put(Configuration.Key.isFloatToDoubleSupported,    isFloatToDoubleSupported));
        assertNull(op.put(Configuration.Key.isOverlappingArraySupported, isOverlappingArraySupported));
        assertNull(op.put(Configuration.Key.isInverseTransformSupported, isInverseTransformSupported));
        assertNull(op.put(Configuration.Key.isDerivativeSupported,       isDerivativeSupported));
        assertNull(op.put(Configuration.Key.isToleranceRelaxed,          isToleranceRelaxed));
        return op;
    }

    /**
     * Transforms the given coordinates and verifies that the result is equal (within a positive
     * delta) to the expected ones. If the difference between an expected and actual coordinate value
     * is greater than the {@linkplain #tolerance tolerance} threshold (after optional
     * {@linkplain #toleranceModifier tolerance modification}), then the assertion fails.
     *
     * <p>If {@link #isInverseTransformSupported} is {@code true}, then this method will also
     * transform the expected coordinate points using the {@linkplain MathTransform#inverse()
     * inverse transform} and compare with the source coordinates.</p>
     *
     * @param  coordinates  the coordinate points to transform.
     * @param  expected     the expect result of the transformation, or
     *         {@code null} if {@code coordinates} is expected to be null.
     * @throws TransformException if the transformation failed.
     *
     * @see #isInverseTransformSupported
     */
    protected void verifyTransform(final double[] coordinates, final double[] expected)
            throws TransformException
    {
        /*
         * Checks the state of this TransformTestCase object, including a shallow inspection of
         * the MathTransform. We check only the parts that are significant to this test method.
         * Full MathTransform validation is not the job of this method.
         */
        @SuppressWarnings("LocalVariableHidesMemberVariable")
        final MathTransform transform = this.transform;             // Protect from changes.
        assertNotNull(transform, "TransformTestCase.transform shall be assigned a value.");
        final int sourceDimension = transform.getSourceDimensions();
        final int targetDimension = transform.getTargetDimensions();
        assertStrictlyPositive(sourceDimension, "Source dimension shall be positive.");
        assertStrictlyPositive(targetDimension, "Target dimension shall be positive.");
        final MathTransform inverse;
        if (isInverseTransformSupported) {
            final Configuration.Key<Boolean> oldTip = configurationTip;
            configurationTip = Configuration.Key.isInverseTransformSupported;
            inverse = transform.inverse();
            assertNotNull(inverse, "MathTransform.inverse() shall not return null.");
            assertEquals(targetDimension, inverse.getSourceDimensions(),
                    "Inconsistent source dimension of the inverse transform.");
            assertEquals(sourceDimension, inverse.getTargetDimensions(),
                    "Inconsistent target dimension of the inverse transform.");
            configurationTip = oldTip;
        } else {
            inverse = null;
        }
        /*
         * Checks the method arguments.
         */
        if (expected == null) {
            assertNull(coordinates);
            return;
        }
        assertNotNull(coordinates);
        assertEquals(0, coordinates.length % sourceDimension,
                "Source dimension is not a divisor of the coordinates array length.");
        assertEquals(0, expected.length % targetDimension,
                "Target dimension is not a divisor of the expected array length.");
        final int numPts = expected.length / targetDimension;
        assertEquals(numPts, coordinates.length / sourceDimension, "Mismatched number of points.");
        /*
         * Now performs the test.
         */
        final SimpleDirectPosition source = new SimpleDirectPosition(sourceDimension);
        final SimpleDirectPosition target = new SimpleDirectPosition(targetDimension);
        final SimpleDirectPosition back   = new SimpleDirectPosition(sourceDimension);
        for (int i=0; i<numPts; i++) {
            final int sourceOffset = i * sourceDimension;
            final int targetOffset = i * targetDimension;
            System.arraycopy(coordinates, sourceOffset, source.coordinates, 0, sourceDimension);
            assertSame(target, transform.transform(source, target),
                    "MathTransform.transform(DirectPosition, …) shall use the given target.");
            assertCoordinatesEqual(targetDimension,
                                   expected, targetOffset,              // Expected coordinates
                                   target.coordinates, 0, 1,            // Actual coordinates
                                   CalculationType.DIRECT_TRANSFORM,
                                   i, "Unexpected transform result.");
            assertCoordinatesEqual(sourceDimension,
                                   coordinates, sourceOffset,           // Expected coordinates
                                   source.coordinates, 0, 1,            // Actual coordinates
                                   CalculationType.IDENTITY,
                                   i, "Source coordinate has been modified.");
            /*
             * Tests the inverse transform, if supported. We could use the 'target' point directly,
             * which contain the result of the transform performed by the application under testing.
             * Nevertheless we overwrite the 'target' point with the 'expected' coordinate provided
             * in argument to this method. It is not necessarily more accurate since the expected
             * coordinates are often provided with limited precision. However, this allow more
             * consistent behavior.
             */
            if (inverse != null) {
                System.arraycopy(expected, targetOffset, target.coordinates, 0, targetDimension);
                assertSame(back, inverse.transform(target, back),
                        "MathTransform.transform(DirectPosition, …) shall use the given target.");
                assertCoordinateEquals(source.coordinates, back.coordinates, i, CalculationType.INVERSE_TRANSFORM,
                        "Unexpected result of inverse transform.");
                assertCoordinatesEqual(targetDimension,
                                       expected, targetOffset,          // Expected coordinates
                                       target.coordinates, 0, 1,        // Actual coordinates
                                       CalculationType.IDENTITY,
                                       i, "Source coordinate has been modified.");
            }
        }
    }

    /**
     * Transforms the given coordinates, applies the inverse transform and compares with the
     * original values. If a difference between the expected and actual coordinate values is
     * greater than the {@linkplain #tolerance tolerance} threshold (after optional
     * {@linkplain #toleranceModifier tolerance modification}), then the assertion fails.
     *
     * <p>At the difference of {@link #verifyTransform(double[],double[])}, this method does
     * not require an array of expected values. The expected values are calculated from
     * the transform itself.</p>
     *
     * @param  coordinates  the source coordinates to transform.
     * @throws TransformException if at least one coordinate cannot be transformed.
     */
    protected void verifyInverse(final double... coordinates) throws TransformException {
        assertTrue(isInverseTransformSupported, "isInverseTransformSupported == false.");
        /*
         * Checks the state of this TransformTestCase object, including a shallow inspection of
         * the MathTransform. We check only the parts that are significant to this test method.
         * Full MathTransform validation is not the job of this method.
         */
        @SuppressWarnings("LocalVariableHidesMemberVariable")
        final MathTransform transform = this.transform;                 // Protect from changes.
        assertNotNull(transform, "TransformTestCase.transform shall be assigned a value.");
        final int sourceDimension = transform.getSourceDimensions();
        final int targetDimension = transform.getTargetDimensions();
        assertStrictlyPositive(sourceDimension, "Source dimension shall be positive.");
        assertStrictlyPositive(targetDimension, "Target dimension shall be positive.");
        final MathTransform inverse = transform.inverse();
        assertNotNull(inverse, "MathTransform.inverse() shall not return null.");
        assertEquals(targetDimension, inverse.getSourceDimensions(),
                "Inconsistent source dimension of the inverse transform.");
        assertEquals(sourceDimension, inverse.getTargetDimensions(),
                "Inconsistent target dimension of the inverse transform.");
        /*
         * Checks the method arguments.
         */
        assertNotNull(coordinates, "Coordinates array expected in argument.");
        assertEquals(0, coordinates.length % sourceDimension,
                "Source dimension is not a divisor of the coordinates array length.");
        final int numPts = coordinates.length / sourceDimension;
        /*
         * Now performs the test.
         */
        final var source = new SimpleDirectPosition(sourceDimension);
        final var back   = new SimpleDirectPosition(sourceDimension);
        DirectPosition target = null;
        for (int i=0; i<numPts; i++) {
            final int offset = i*sourceDimension;
            System.arraycopy(coordinates, offset, source.coordinates, 0, sourceDimension);
            target = transform.transform(source, target);
            assertNotNull(target, "MathTransform.transform(DirectPosition, …) shall not return null.");
            assertEquals(targetDimension, target.getDimension(),
                    "Transformed point has wrong dimension.");
            assertSame(back, inverse.transform(target, back),
                    "MathTransform.transform(DirectPosition, …) shall use the given target.");
            assertCoordinateEquals(source.coordinates, back.coordinates, i, CalculationType.INVERSE_TRANSFORM,
                    "Unexpected result of inverse transform.");
            assertCoordinatesEqual(sourceDimension,
                                   coordinates, offset,             // Expected coordinates
                                   source.coordinates, 0, 1,        // Actual coordinates
                                   CalculationType.IDENTITY,
                                   i, "Source coordinate has been modified.");
        }
    }

    /**
     * Transforms the given coordinates, applies the inverse transform and compares with the
     * original values. If a difference between the expected and actual coordinate values is
     * greater than the {@linkplain #tolerance tolerance} threshold (after optional
     * {@linkplain #toleranceModifier tolerance modification}), then the assertion fails.
     *
     * <p>The default implementation delegates to {@link #verifyInverse(double[])}.</p>
     *
     * @param  coordinates  the source coordinates to transform.
     * @throws TransformException if at least one coordinate cannot be transformed.
     */
    protected void verifyInverse(final float... coordinates) throws TransformException {
        assertTrue(isInverseTransformSupported, "isInverseTransformSupported == false.");
        final double[] sourceDoubles = new double[coordinates.length];
        for (int i=0; i<coordinates.length; i++) {
            sourceDoubles[i] = coordinates[i];
        }
        verifyInverse(sourceDoubles);
        final int dimension = transform.getSourceDimensions();
        assertCoordinatesEqual(dimension,
                               coordinates,   0,                    // Expected coordinates
                               sourceDoubles, 0,                    // Actual coordinates
                               coordinates.length / dimension,      // Number of coordinate tuples
                               CalculationType.IDENTITY,
                               "Unexpected change in source coordinates.");
    }

    /**
     * Transforms coordinates using various versions of {@code MathTransform.transform(…)}
     * and verifies that they produce the same numerical values. The values calculated by
     * {@link MathTransform#transform(DirectPosition,DirectPosition)} are used as the reference.
     * Other transform methods (operating on arrays) will be compared against that reference,
     * unless their checks were disabled (see class javadoc for details).
     *
     * <p>This method expects an array of {@code float} values instead of {@code double}
     * for making sure that the {@code MathTransform.transform(float[], …)} and
     * {@code MathTransform.transform(double[], …)} methods produces the same numerical values.
     * The {@code double} values may show extra digits when formatted in base 10, but this is not
     * significant if their IEEE 754 representation (which use base 2) are equivalent.</p>
     *
     * <p>This method does not verify the inverse transform or the derivatives. If desired,
     * those later methods can be verified with the {@link #verifyInverse(float[])} and
     * {@link #verifyDerivative(double[])} methods respectively.</p>
     *
     * @param  sourceFloats  the source coordinates to transform as an array of {@code float} values.
     * @return the transformed coordinates, returned for convenience.
     * @throws TransformException if at least one coordinate cannot be transformed.
     *
     * @see #isDoubleToDoubleSupported
     * @see #isFloatToFloatSupported
     * @see #isDoubleToFloatSupported
     * @see #isFloatToDoubleSupported
     * @see #isOverlappingArraySupported
     */
    protected float[] verifyConsistency(final float... sourceFloats) throws TransformException {
        @SuppressWarnings("LocalVariableHidesMemberVariable")
        final MathTransform transform = this.transform;                 // Protect from changes.
        assertNotNull(transform, "TransformTestCase.transform shall be assigned a value.");
        final int sourceDimension = transform.getSourceDimensions();
        final int targetDimension = transform.getTargetDimensions();
        assertEquals(0, sourceFloats.length % sourceDimension,
                "Source dimension is not a divisor of the coordinates array length.");
        final int numPts = sourceFloats.length / sourceDimension;
        final float [] targetFloats    = new float [max(sourceDimension, targetDimension) * (numPts + POINTS_OFFSET)];
        final float [] expectedFloats  = new float [targetDimension * numPts];
        final double[] sourceDoubles   = new double[sourceFloats.length];
        final double[] targetDoubles   = new double[targetFloats.length];
        final double[] expectedDoubles = new double[expectedFloats.length];
        /*
         * Copies the source coordinates (to be used later) and performs the transformations using
         * MathTransform.transform(DirectPosition) method. Result is stored in the "transformed"
         * array and will not be modified anymore from that point.
         */
        for (int i=0; i<sourceDoubles.length; i++) {
            sourceDoubles[i] = sourceFloats[i];
        }
        if (true) {         // MathTransform.transform(DirectPosition) is not optional in this test.
            final SimpleDirectPosition sourcePosition = new SimpleDirectPosition(sourceDimension);
            DirectPosition targetPosition = null;
            int targetOffset = 0;
            for (int i=0; i < sourceDoubles.length; i += sourceDimension) {
                System.arraycopy(sourceDoubles, i, sourcePosition.coordinates, 0, sourceDimension);
                targetPosition = transform.transform(sourcePosition, targetPosition);
                assertNotNull(targetPosition, "MathTransform.transform(DirectPosition, …) shall not return null.");
                assertNotSame(sourcePosition, targetPosition,
                        "MathTransform.transform(DirectPosition, …) shall not overwrite the source position.");
                assertEquals(targetDimension, targetPosition.getDimension(), "MathTransform.transform(DirectPosition)"
                        + " must return a position having the same dimension as MathTransform.getTargetDimension().");
                for (int j=0; j<targetDimension; j++) {
                    expectedFloats[targetOffset] = (float) (expectedDoubles[targetOffset] = targetPosition.getOrdinate(j));
                    targetOffset++;
                }
            }
            assertEquals(expectedFloats.length, targetOffset);
        }
        /*
         * Tests transformation in distincts (non-overlapping) arrays.
         */
        final Configuration.Key<Boolean> oldTip = configurationTip;
        if (isDoubleToDoubleSupported) {
            configurationTip = Configuration.Key.isDoubleToDoubleSupported;
            Arrays.fill(targetDoubles, Double.NaN);
            transform.transform(sourceDoubles, 0, targetDoubles, 0, numPts);
            assertCoordinatesEqual(sourceDimension,
                                   sourceFloats, 0,                     // Expected coordinates
                                   sourceDoubles, 0, numPts,            // Actual coordinates
                                   CalculationType.IDENTITY,
                                   "MathTransform.transform(double[],0,double[],0,n) modified a source coordinate.");
            assertCoordinatesEqual(targetDimension,
                                   expectedDoubles, 0,                  // Expected coordinates
                                   targetDoubles, 0, numPts,            // Actual coordinates
                                   CalculationType.DIRECT_TRANSFORM,
                                   "MathTransform.transform(double[],0,double[],0,n) error.");
        }
        if (isFloatToFloatSupported) {
            configurationTip = Configuration.Key.isFloatToFloatSupported;
            Arrays.fill(targetFloats, Float.NaN);
            transform.transform(sourceFloats, 0, targetFloats, 0, numPts);
            assertCoordinatesEqual(sourceDimension,
                                   sourceDoubles, 0,                    // Expected coordinates
                                   sourceFloats, 0, numPts,             // Actual coordinates
                                   CalculationType.IDENTITY,
                                   "MathTransform.transform(float[],0,float[],0,n) modified a source coordinate.");
            assertCoordinatesEqual(targetDimension,
                                   expectedFloats, 0,                   // Expected coordinates
                                   targetFloats, 0, numPts,             // Actual coordinates
                                   CalculationType.DIRECT_TRANSFORM,
                                   "MathTransform.transform(float[],0,float[],0,n) error.");
        }
        if (isDoubleToFloatSupported) {
            configurationTip = Configuration.Key.isDoubleToFloatSupported;
            Arrays.fill(targetFloats, Float.NaN);
            transform.transform(sourceDoubles, 0, targetFloats, 0, numPts);
            assertCoordinatesEqual(sourceDimension,
                                   sourceFloats, 0,                     // Expected coordinates
                                   sourceDoubles, 0, numPts,            // Actual coordinates
                                   CalculationType.IDENTITY,
                                   "MathTransform.transform(double[],0,float[],0,n) modified a source coordinate.");
            assertCoordinatesEqual(targetDimension,
                                   expectedFloats, 0,                   // Expected coordinates
                                   targetFloats, 0, numPts,             // Actual coordinates
                                   CalculationType.DIRECT_TRANSFORM,
                                   "MathTransform.transform(double[],0,float[],0,n) error.");
        }
        if (isFloatToDoubleSupported) {
            configurationTip = Configuration.Key.isFloatToDoubleSupported;
            Arrays.fill(targetDoubles, Double.NaN);
            transform.transform(sourceFloats, 0, targetDoubles, 0, numPts);
            assertCoordinatesEqual(sourceDimension,
                                   sourceDoubles, 0,                    // Expected coordinates
                                   sourceFloats, 0, numPts,             // Actual coordinates
                                   CalculationType.IDENTITY,
                                   "MathTransform.transform(float[],0,double[],0,n) modified a source coordinate.");
            assertCoordinatesEqual(targetDimension,
                                   expectedDoubles, 0,                  // Expected coordinates
                                   targetDoubles, 0, numPts,            // Actual coordinates
                                   CalculationType.DIRECT_TRANSFORM,
                                   "MathTransform.transform(float[],0,double[],0,n) error.");
        }
        /*
         * Tests transformation in overlapping arrays.
         */
        if (isOverlappingArraySupported) {
            configurationTip = Configuration.Key.isOverlappingArraySupported;
            for (int sourceOffset=0; sourceOffset < POINTS_OFFSET*sourceDimension; sourceOffset += sourceDimension) {
                for (int targetOffset=0; targetOffset < POINTS_OFFSET*targetDimension; targetOffset += targetDimension) {
                    System.arraycopy(sourceFloats,  0, targetFloats,  sourceOffset, sourceFloats .length);
                    System.arraycopy(sourceDoubles, 0, targetDoubles, sourceOffset, sourceDoubles.length);
                    transform.transform(targetFloats,  sourceOffset, targetFloats,  targetOffset, numPts);
                    transform.transform(targetDoubles, sourceOffset, targetDoubles, targetOffset, numPts);
                    assertCoordinatesEqual(targetDimension,
                                           expectedFloats, 0,                       // Expected coordinates
                                           targetFloats, targetOffset, numPts,      // Actual coordinates
                                           CalculationType.DIRECT_TRANSFORM,
                                           "MathTransform.transform(float[],0,float[],0,n) error.");
                    assertCoordinatesEqual(targetDimension,
                                           expectedFloats, 0,                       // Expected coordinates
                                           targetDoubles, targetOffset, numPts,     // Actual coordinates
                                           CalculationType.DIRECT_TRANSFORM,
                                           "MathTransform.transform(double[],0,double[],0,n) error.");
                }
            }
        }
        configurationTip = oldTip;
        /*
         * Tests MathTransform1D methods.
         */
        if (transform instanceof MathTransform1D) {
            assertEquals(1, sourceDimension, "MathTransform1D.getSourceDimension()");
            assertEquals(1, targetDimension, "MathTransform1D.getTargetDimension()");
            final MathTransform1D transform1D = (MathTransform1D) transform;
            for (int i=0; i<sourceFloats.length; i++) {
                targetDoubles[i] = transform1D.transform(sourceFloats[i]);
            }
            assertCoordinatesEqual(1, expectedDoubles, 0,               // Expected coordinates
                                   targetDoubles, 0, numPts,            // Actual coordinates
                                   CalculationType.DIRECT_TRANSFORM,
                                   "MathTransform1D.transform(double) error.");
        }
        /*
         * Tests MathTransform2D methods.
         */
        if (transform instanceof MathTransform2D) {
            assertEquals(2, sourceDimension, "MathTransform2D.getSourceDimension()");
            assertEquals(2, targetDimension, "MathTransform2D.getTargetDimension()");
            final MathTransform2D transform2D = (MathTransform2D) transform;
            final Point2D.Float  source = new Point2D.Float();
            final Point2D.Double target = new Point2D.Double();
            for (int i=0; i<sourceFloats.length;) {
                source.x = sourceFloats[i];
                source.y = sourceFloats[i+1];
                assertSame(target, transform2D.transform(source, target),
                        "MathTransform2D.transform(Point2D, …) shall use the given target.");
                assertNotNull(target, "MathTransform2D.transform(Point2D, …) shall not return null.");
                targetDoubles[i++] = target.x;
                targetDoubles[i++] = target.y;
            }
            assertCoordinatesEqual(2, expectedDoubles, 0,               // Expected coordinates
                                   targetDoubles, 0, numPts,            // Actual coordinates
                                   CalculationType.DIRECT_TRANSFORM,
                                   "MathTransform2D.transform(Point2D,Point2D) error.");
        }
        return expectedFloats;
    }

    /**
     * Computes the {@linkplain MathTransform#derivative(DirectPosition) derivative at the given point}
     * and compares the result with the <a href="http://en.wikipedia.org/wiki/Finite_difference">finite
     * differences</a> approximation.
     *
     * <p>All the three common forms of finite differences (<i>forward difference</i>,
     * <i>backward difference</i> and <i>central difference</i>) are computed.
     * If the finite difference method was a "perfect" approximation, all those three forms
     * would produce identical results. In practice the results will differ, especially in
     * areas where the derivative function varies fast. The difference between the results
     * will be used as an estimation of the approximation accuracy.</p>
     *
     * <p>The distance between the two points used by the <i>central difference</i>
     * approximation shall be specified in the {@link #derivativeDeltas} array, in units
     * of the source CRS. If the length of the {@code derivativeDeltas} array is smaller
     * than the number of source dimensions, then the last delta value is used for all
     * additional dimensions. This allows specifying a single delta value (in an array
     * of length 1) for all dimensions.</p>
     *
     * <p>This method created the following objects:</p>
     * <ul>
     *   <li>{@code expected} - the expected derivative result estimated by the central
     *       difference method.</li>
     *   <li>{@code tolmat} - a <i>tolerance matrix</i> containing, for each matrix element,
     *       the largest difference found between the three approximation methods. The values in
     *       this matrix will not be lower than the {@linkplain #toleranceModifier modified}
     *       {@linkplain #tolerance} threshold.</li>
     * </ul>
     *
     * Those information are then passed to the {@link #assertMatrixEquals(Matrix, Matrix, Matrix, String)} method.
     * Implementers can override the latter method, for example in order to overwrite the tolerance values.
     *
     * @param  coordinates  the point where to compute the derivative, in units of the source CRS.
     * @throws TransformException if the derivative cannot be computed, or a point cannot be transformed.
     *
     * @see MathTransform#derivative(DirectPosition)
     * @see #assertMatrixEquals(Matrix, Matrix, Matrix, String)
     *
     * @since 3.1
     */
    protected void verifyDerivative(final double... coordinates) throws TransformException {
        assertTrue(isDerivativeSupported, "isDerivativeSupported == false.");
        @SuppressWarnings("LocalVariableHidesMemberVariable")
        final MathTransform   transform = this.transform;                               // Protect from changes.
        @SuppressWarnings("LocalVariableHidesMemberVariable")
        final double[] derivativeDeltas = this.derivativeDeltas;                        // Protect from changes.
        assertNotNull(transform, "TransformTestCase.transform shall be assigned a value.");
        assertNotNull(derivativeDeltas, "TransformTestCase.derivativeDeltas shall be assigned a value.");
        assertNotEquals(0, derivativeDeltas.length, "TransformTestCase.derivativeDeltas shall not be empty.");
        assertEquals(transform.getSourceDimensions(), coordinates.length,
                "Coordinate dimension shall be equal to the transform source dimension.");
        /*
         * Invoke the MathTransform.derivative(DirectPosition) method to test
         * and validate the result.
         */
        final int sourceDim = transform.getSourceDimensions();
        final int targetDim = transform.getTargetDimensions();
        final SimpleDirectPosition S0 = new SimpleDirectPosition(sourceDim);
        final SimpleDirectPosition T0 = new SimpleDirectPosition(targetDim);
        S0.setCoordinate(coordinates);
        S0.unmodifiable = true;
        assertSame(T0, transform.transform(S0, T0));
        T0.unmodifiable = true;
        final Matrix matrix = transform.derivative(S0);
        final String message = "MathTransform.derivative(" + S0 + ')';
        assertNotNull(matrix, message);
        assertEquals(sourceDim, matrix.getNumCol(), "Unexpected number of columns.");
        assertEquals(targetDim, matrix.getNumRow(), "Unexpected number of rows.");
        /*
         * Get the user-specified tolerances.
         */
        final double[] tolerances = new double[targetDim];
        Arrays.fill(tolerances, tolerance);
        final ToleranceModifier modifier = getToleranceModifier();
        if (modifier != null) {
            modifier.adjust(tolerances, T0, CalculationType.TRANSFORM_DERIVATIVE);
        }
        /*
         * Compute an approximation of the expected derivative.
         */
        final Matrix approx = new SimpleMatrix(targetDim, sourceDim, new double[sourceDim * targetDim]);
        final Matrix tolmat = new SimpleMatrix(targetDim, sourceDim, new double[sourceDim * targetDim]);
        final SimpleDirectPosition S1 = new SimpleDirectPosition(sourceDim);
        final SimpleDirectPosition S2 = new SimpleDirectPosition(sourceDim);
        final SimpleDirectPosition T1 = new SimpleDirectPosition(targetDim);
        final SimpleDirectPosition T2 = new SimpleDirectPosition(targetDim);
        for (int i=0; i<sourceDim; i++) {
            S1.setCoordinate(coordinates);
            S2.setCoordinate(coordinates);
            final double coordinate = coordinates[i];
            final double delta = derivativeDeltas[min(i, derivativeDeltas.length-1)];
            S1.setOrdinate(i, coordinate - delta/2);
            S2.setOrdinate(i, coordinate + delta/2);
            assertSame(T1, transform.transform(S1, T1));
            assertSame(T2, transform.transform(S2, T2));
            for (int j=0; j<targetDim; j++) {
                final double dc = (T2.getOrdinate(j) - T1.getOrdinate(j)) /  delta;         // Central difference
                final double df = (T2.getOrdinate(j) - T0.getOrdinate(j)) / (delta/2);      // Forward difference
                final double db = (T0.getOrdinate(j) - T1.getOrdinate(j)) / (delta/2);      // Backward difference
                approx.setElement(j, i, dc);
                tolmat.setElement(j, i, max(tolerances[j], max(abs(df - db), max(abs(dc - db), abs(dc - df)))));
            }
        }
        /*
         * Now compare the matrix elements. If the transform implements also
         * the MathTransform1D or MathTransform2D interface, check consistency.
         */
        assertMatrixEquals(approx, matrix, tolmat, message);
        if (transform instanceof MathTransform1D) {
            assertEquals(1, sourceDim, "MathTransform1D.getSourceDimensions()");
            assertEquals(1, targetDim, "MathTransform1D.getTargetDimensions()");
            assertMatrixEquals(matrix, new SimpleMatrix(1, 1, ((MathTransform1D) transform).derivative(coordinates[0])),
                               tolmat, "MathTransform1D.derivative(double) error.");
        }
        if (transform instanceof MathTransform2D) {
            assertEquals(2, sourceDim, "MathTransform2D.getSourceDimensions()");
            assertEquals(2, targetDim, "MathTransform2D.getTargetDimensions()");
            assertMatrixEquals(matrix, ((MathTransform2D) transform).derivative(new Point2D.Double(coordinates[0], coordinates[1])),
                               tolmat, "MathTransform2D.derivative(Point2D) error.");
        }
    }

    /**
     * Verifies all supported transform operations in the given domain. First, this method creates
     * a grid of regularly spaced points along all source dimensions in the given envelope.
     * Next, if the given random number generator is non-null, then this method adds small
     * random displacements to every points and shuffle the coordinates in random order.
     * Finally this method delegates the resulting array of coordinates to the following
     * methods:
     *
     * <ul>
     *   <li>{@link #verifyConsistency(float[])}</li>
     *   <li>{@link #verifyInverse(float[])}</li>
     *   <li>{@link #verifyDerivative(double[])}</li>
     * </ul>
     *
     * The generated coordinates array is returned in case callers want to perform more tests
     * in addition to the above-cited verifications.
     *
     * @param  minOrdinates     the minimal coordinate values of the domain where to test the transform.
     * @param  maxOrdinates     the maximal coordinate values of the domain where to test the transform.
     * @param  numOrdinates     the number of points along each dimension.
     * @param  randomGenerator  an optional random number generator, or {@code null} for testing using a regular grid.
     * @return the generated random coordinates inside the given domain of validity.
     * @throws TransformException if a transform or a derivative cannot be computed.
     *
     * @since 3.1
     */
    protected float[] verifyInDomain(final double[] minOrdinates, final double[] maxOrdinates,
            final int[] numOrdinates, final Random randomGenerator) throws TransformException
    {
        @SuppressWarnings("LocalVariableHidesMemberVariable")
        final MathTransform transform = this.transform;             // Protect from changes.
        assertNotNull(transform, "TransformTestCase.transform shall be assigned a value.");
        final int dimension = transform.getSourceDimensions();
        assertEquals(dimension, minOrdinates.length, "The minOrdinates array doesn't have the expected length.");
        assertEquals(dimension, maxOrdinates.length, "The maxOrdinates array doesn't have the expected length.");
        assertEquals(dimension, numOrdinates.length, "The numOrdinates array doesn't have the expected length.");
        int numPoints = 1;
        for (int i=0; i<dimension; i++) {
            numPoints *= numOrdinates[i];
            assertTrue(numPoints >= 0, "Invalid numOrdinates value.");
        }
        final float[] coordinates = new float[numPoints * dimension];
        /*
         * Initialize the coordinate values for each dimension, and shuffle
         * the result if a random numbers generator has been specified.
         */
        int step = 1;
        for (int dim=0; dim<dimension; dim++) {
            final int    n     =  numOrdinates[dim];
            final double delta = (maxOrdinates[dim] - minOrdinates[dim]) / n;
            final double start =  minOrdinates[dim] + delta/2;
            int ordinateIndex=0, count=0;
            float coordinate = (float) start;
            for (int i=dim; i<coordinates.length; i+=dimension) {
                coordinates[i] = coordinate;
                if (randomGenerator != null) {
                    coordinates[i] += (randomGenerator.nextFloat() - 0.5f) * (float) delta;
                }
                if (++count == step) {
                    count = 0;
                    if (++ordinateIndex == n) {
                        ordinateIndex = 0;
                    }
                    coordinate = (float) (ordinateIndex*delta + start);
                }
            }
            step *= numOrdinates[dim];
        }
        if (randomGenerator != null) {
            final float[] buffer = new float[dimension];
            for (int i=coordinates.length; (i -= dimension) >= 0;) {
                final int t = randomGenerator.nextInt(numPoints) * dimension;
                System.arraycopy(coordinates, t, buffer,      0, dimension);
                System.arraycopy(coordinates, i, coordinates, t, dimension);
                System.arraycopy(buffer,      0, coordinates, i, dimension);
            }
        }
        /*
         * Delegate to other methods defined in this class.
         */
        verifyConsistency(coordinates);
        final Configuration.Key<Boolean> oldTip = configurationTip;
        if (isInverseTransformSupported) {
            configurationTip = Configuration.Key.isInverseTransformSupported;
            verifyInverse(coordinates);
        }
        if (isDerivativeSupported) {
            configurationTip = Configuration.Key.isDerivativeSupported;
            final double[] point = new double[dimension];
            for (int i=0; i<coordinates.length; i+=dimension) {
                for (int j=0; j<dimension; j++) {
                    point[j] = coordinates[i+j];
                }
                verifyDerivative(point);
            }
        }
        configurationTip = oldTip;
        return coordinates;
    }

    /**
     * Asserts that a single coordinate is equal to the expected one within a positive delta.
     * If the comparison fails, the given message is completed with the expected and actual
     * values, and the index of the coordinate where the failure was found.
     *
     * @param expected  the array of expected coordinate values.
     * @param actual    the array of coordinate values to check against the expected ones.
     * @param index     the index of the coordinate point being compared, for message formatting.
     * @param mode      indicates if the coordinates being compared are the result of a direct or
     *                  inverse transform, or if strict equality is requested.
     * @param message   the message to report in case of failure, or {@code null} if none.
     * @throws TransformFailure if at least one coordinate value is not equal to the expected value.
     *
     * @since 3.1
     */
    protected final void assertCoordinateEquals(final float[] expected, final float[] actual,
            final int index, final CalculationType mode, final String message) throws TransformFailure
    {
        final int dimension = expected.length;
        assertEquals(dimension, actual.length, "Coordinate array lengths differ.");
        assertCoordinatesEqual(dimension, expected, 0, actual, 0, 1, mode, index, message);
    }

    /**
     * Asserts that a single coordinate is equal to the expected one within a positive delta.
     * If the comparison fails, the given message is completed with the expected and actual
     * values, and the index of the coordinate where the failure was found.
     *
     * @param expected  the array of expected coordinate values.
     * @param actual    the array of coordinate values to check against the expected ones.
     * @param index     the index of the coordinate point being compared, for message formatting.
     * @param mode      indicates if the coordinates being compared are the result of a direct or
     *                  inverse transform, or if strict equality is requested.
     * @param message   the message to report in case of failure, or {@code null} if none.
     * @throws TransformFailure if at least one coordinate value is not equal to the expected value.
     *
     * @since 3.1
     */
    protected final void assertCoordinateEquals(final float[] expected, final double[] actual,
            final int index, final CalculationType mode, final String message) throws TransformFailure
    {
        final int dimension = expected.length;
        assertEquals(dimension, actual.length, "Coordinate array lengths differ.");
        assertCoordinatesEqual(dimension, expected, 0, actual, 0, 1, mode, index, message);
    }

    /**
     * Asserts that a single coordinate is equal to the expected one within a positive delta.
     * If the comparison fails, the given message is completed with the expected and actual
     * values, and the index of the coordinate where the failure was found.
     *
     * @param expected  the array of expected coordinate values.
     * @param actual    the array of coordinate values to check against the expected ones.
     * @param index     the index of the coordinate point being compared, for message formatting.
     * @param mode      indicates if the coordinates being compared are the result of a direct or
     *                  inverse transform, or if strict equality is requested.
     * @param message   the message to report in case of failure, or {@code null} if none.
     * @throws TransformFailure if at least one coordinate value is not equal to the expected value.
     *
     * @since 3.1
     */
    protected final void assertCoordinateEquals(final double[] expected, final float[] actual,
            final int index, final CalculationType mode, final String message) throws TransformFailure
    {
        final int dimension = expected.length;
        assertEquals(dimension, actual.length, "Coordinate array lengths differ.");
        assertCoordinatesEqual(dimension, expected, 0, actual, 0, 1, mode, index, message);
    }

    /**
     * Asserts that a single coordinate is equal to the expected one within a positive delta.
     * If the comparison fails, the given message is completed with the expected and actual
     * values, and the index of the coordinate where the failure was found.
     *
     * @param expected  the array of expected coordinate values.
     * @param actual    the array of coordinate values to check against the expected ones.
     * @param index     the index of the coordinate point being compared, for message formatting.
     * @param mode      indicates if the coordinates being compared are the result of a direct or
     *                  inverse transform, or if strict equality is requested.
     * @param message   the message to report in case of failure, or {@code null} if none.
     * @throws TransformFailure if at least one coordinate value is not equal to the expected value.
     *
     * @since 3.1
     */
    protected final void assertCoordinateEquals(final double[] expected, final double[] actual,
            final int index, final CalculationType mode, final String message) throws TransformFailure
    {
        final int dimension = expected.length;
        assertEquals(dimension, actual.length, "Coordinate array lengths differ.");
        assertCoordinatesEqual(dimension, expected, 0, actual, 0, 1, mode, index, message);
    }

    /**
     * Asserts that coordinate values are equal to the expected ones within a positive delta.
     * If the comparison fails, the given message is completed with the expected and actual
     * values, and the index of the coordinate where the failure was found.
     *
     * @param dimension       the dimension of each coordinate points in the arrays.
     * @param expectedPts     the array of expected coordinate values.
     * @param expectedOffset  index of the first valid coordinate in the {@code expectedPts} array.
     * @param actualPts       the array of coordinate values to check against the expected ones.
     * @param actualOffset    index of the first valid coordinate in the {@code actualPts} array.
     * @param numPoints       number of coordinate points to compare.
     * @param mode            indicates if the coordinates being compared are the result of a direct
     *                        or inverse transform, or if strict equality is requested.
     * @param message         the message to report in case of failure, or {@code null} if none.
     * @throws TransformFailure if at least one coordinate value is not equal to the expected value.
     *
     * @since 3.1
     */
    protected final void assertCoordinatesEqual(final int dimension,
            final float[] expectedPts, final int expectedOffset,
            final float[] actualPts,   final int actualOffset,
            final int numPoints, final CalculationType mode,
            final String message) throws TransformFailure
    {
        assertCoordinatesEqual(dimension, expectedPts, expectedOffset,
                actualPts, actualOffset, numPoints, mode, 0, message);
    }

    /**
     * Asserts that coordinate values are equal to the expected ones within a positive delta.
     * If the comparison fails, the given message is completed with the expected and actual
     * values, and the index of the coordinate where the failure was found.
     *
     * @param dimension       the dimension of each coordinate points in the arrays.
     * @param expectedPts     the array of expected coordinate values.
     * @param expectedOffset  index of the first valid coordinate in the {@code expectedPts} array.
     * @param actualPts       the array of coordinate values to check against the expected ones.
     * @param actualOffset    index of the first valid coordinate in the {@code actualPts} array.
     * @param numPoints       number of coordinate points to compare.
     * @param mode            indicates if the coordinates being compared are the result of a direct
     *                        or inverse transform, or if strict equality is requested.
     * @param message         the message to report in case of failure, or {@code null} if none.
     * @throws TransformFailure if at least one coordinate value is not equal to the expected value.
     *
     * @since 3.1
     */
    protected final void assertCoordinatesEqual(final int dimension,
            final float[]  expectedPts, final int expectedOffset,
            final double[] actualPts,   final int actualOffset,
            final int numPoints, final CalculationType mode,
            final String message) throws TransformFailure
    {
        assertCoordinatesEqual(dimension, expectedPts, expectedOffset,
                actualPts, actualOffset, numPoints, mode, 0, message);
    }

    /**
     * Asserts that coordinate values are equal to the expected ones within a positive delta.
     * If the comparison fails, the given message is completed with the expected and actual
     * values, and the index of the coordinate where the failure was found.
     *
     * @param dimension       the dimension of each coordinate points in the arrays.
     * @param expectedPts     the array of expected coordinate values.
     * @param expectedOffset  index of the first valid coordinate in the {@code expectedPts} array.
     * @param actualPts       the array of coordinate values to check against the expected ones.
     * @param actualOffset    index of the first valid coordinate in the {@code actualPts} array.
     * @param numPoints       number of coordinate points to compare.
     * @param mode            indicates if the coordinates being compared are the result of a direct
     *                        or inverse transform, or if strict equality is requested.
     * @param message         the message to report in case of failure, or {@code null} if none.
     * @throws TransformFailure if at least one coordinate value is not equal to the expected value.
     *
     * @since 3.1
     */
    protected final void assertCoordinatesEqual(final int dimension,
            final double[] expectedPts, final int expectedOffset,
            final float[]  actualPts,   final int actualOffset,
            final int numPoints, final CalculationType mode,
            final String message) throws TransformFailure
    {
        assertCoordinatesEqual(dimension, expectedPts, expectedOffset,
                actualPts, actualOffset, numPoints, mode, 0, message);
    }

    /**
     * Asserts that coordinate values are equal to the expected ones within a positive delta.
     * If the comparison fails, the given message is completed with the expected and actual
     * values, and the index of the coordinate where the failure was found.
     *
     * @param dimension       the dimension of each coordinate points in the arrays.
     * @param expectedPts     the array of expected coordinate values.
     * @param expectedOffset  index of the first valid coordinate in the {@code expectedPts} array.
     * @param actualPts       the array of coordinate values to check against the expected ones.
     * @param actualOffset    index of the first valid coordinate in the {@code actualPts} array.
     * @param numPoints       number of coordinate points to compare.
     * @param mode            indicates if the coordinates being compared are the result of a direct
     *                        or inverse transform, or if strict equality is requested.
     * @param message         the message to report in case of failure, or {@code null} if none.
     * @throws TransformFailure if at least one coordinate value is not equal to the expected value.
     *
     * @since 3.1
     */
    protected final void assertCoordinatesEqual(final int dimension,
            final double[] expectedPts, final int expectedOffset,
            final double[] actualPts,   final int actualOffset,
            final int numPoints, final CalculationType mode,
            final String message) throws TransformFailure
    {
        assertCoordinatesEqual(dimension, expectedPts, expectedOffset,
                actualPts, actualOffset, numPoints, mode, 0, message);
    }

    /**
     * Implementation of public assertion methods with the addition of the coordinate
     * index to be reported in error message.
     *
     * @param dimension       the dimension of each coordinate points in the arrays.
     * @param expectedPts     the {@code float[]} or {@code double[]} array of expected coordinate values.
     * @param expectedOffset  index of the first valid coordinate in the {@code expectedPts} array.
     * @param actualPts       the {@code float[]} or {@code double[]} array of coordinate values to check against the expected ones.
     * @param actualOffset    index of the first valid coordinate in the {@code actualPts} array.
     * @param numPoints       number of coordinate points to compare.
     * @param mode            indicates if the coordinates being compared are the result of a direct
     *                        or inverse transform, or if strict equality is requested.
     * @param reportedIndex   in case of failure, index of the point (not coordinate) to report in the error message.
     * @param message         the header part of the message to format in case of failure, or {@code null} if none.
     * @throws TransformFailure if at least one coordinate value is not equal to the expected value.
     */
    private void assertCoordinatesEqual(final int dimension,
            final Object expectedPts, int expectedOffset,
            final Object actualPts,   int actualOffset,
            final int numPoints, final CalculationType mode,
            final int reportedIndex, final String message)
            throws TransformFailure
    {
        final boolean useDouble = isDoubleArray(expectedPts) && isDoubleArray(actualPts);
        final SimpleDirectPosition actual   = new SimpleDirectPosition(dimension);
        final SimpleDirectPosition expected = new SimpleDirectPosition(dimension);
        final double[] tolerances = new double[dimension];
        final ToleranceModifier modifier = getToleranceModifier();
        for (int i=0; i<numPoints; i++) {
            actual  .setCoordinate(actualPts,   actualOffset,   useDouble);
            expected.setCoordinate(expectedPts, expectedOffset, useDouble);
            normalize(expected, actual, mode);
            Arrays.fill(tolerances, (mode != CalculationType.IDENTITY) ? tolerance : 0);
            if (modifier != null) {
                modifier.adjust(tolerances, expected, mode);
            }
            for (int mismatch=0; mismatch<dimension; mismatch++) {
                final double a = actual  .getOrdinate(mismatch);
                final double e = expected.getOrdinate(mismatch);
                /*
                 * This method uses !(a <= b) expressions instead of (a > b) for catching NaN.
                 * The next condition working on bit patterns is for NaN and Infinity values.
                 */
                final double delta = abs(e - a);
                final double tol = tolerances[mismatch];
                if (!(delta <= tol) && Double.doubleToLongBits(a) != Double.doubleToLongBits(e)) {
                    /*
                     * Format an error message with the coordinate values followed by the
                     * difference with the expected value.
                     */
                    final String lineSeparator = System.getProperty("line.separator", "\n");
                    final StringBuilder buffer = new StringBuilder(1000);
                    appendErrorHeader(buffer, message);
                    buffer.append(lineSeparator)
                            .append("• DirectPosition").append(dimension).append("D[").append(reportedIndex + i)
                            .append("]: Expected ").append(expected).append(" but got ").append(actual).append('.')
                            .append(lineSeparator).append("• The delta at coordinate ").append(mismatch).append(" is ");
                    if (useDouble) {
                        buffer.append(delta);
                    } else {
                        buffer.append((float) delta);
                    }
                    buffer.append(" which is ").append((float) (delta / tol)).append(" times the tolerance threshold.");
                    if (modifier != null) {
                        buffer.append(lineSeparator).append("• The tolerance were calculated by ").append(modifier);
                    }
                    String wkt = null;
                    try {
                        wkt = transform.toWKT();
                    } catch (Exception ignore) {
                        // WKT formatting is optional, so ignore.
                    }
                    if (wkt != null) {
                        buffer.append(lineSeparator).append("• The transform Well-Known Text (WKT) is below:")
                              .append(lineSeparator).append(wkt);
                    }
                    throw new TransformFailure(buffer.toString());
                }
            }
            expectedOffset += dimension;
            actualOffset   += dimension;
        }
    }

    /**
     * {@return the tolerance modifier to use for comparing coordinate values}. The user-specified
     * value in {@link #toleranceModifier} is merged with any implementation-specific modifiers,
     * and the result is cached in {@link #cachedModifier} for reuse.
     *
     * @see #cachedModifier
     * @see #modifierUsedByCache
     * @see #transformUsedByCache
     */
    private ToleranceModifier getToleranceModifier() {
        if (cachedModifier == null || modifierUsedByCache != toleranceModifier || transformUsedByCache != transform) {
            transformUsedByCache = transform;
            modifierUsedByCache = toleranceModifier;
            final ToleranceModifier foundOnTheClasspath = null;     // TODO: provide a way for users to relax tolerance.
            isToleranceRelaxed |= (foundOnTheClasspath != null);
            cachedModifier = ToleranceModifiers.concatenate(toleranceModifier, foundOnTheClasspath);
        }
        return cachedModifier;
    }

    /**
     * Asserts that a matrix of derivatives is equal to the expected ones within a positive delta.
     * If the comparison fails, the given message is completed with the expected and actual matrixes
     * values.
     *
     * <p>For each matrix element, the tolerance value is given by the corresponding element in the
     * {@code tolmat} matrix. This tolerance matrix is initialized by the
     * {@link #verifyDerivative(double[])} method to the differences found between the 3 forms of
     * finite difference (<i>forward</i>, <i>backward</i>, <i>central</i>).
     * Developers can override this method and overwrite the {@code tolmat} elements if they
     * wish different tolerance values.</p>
     *
     * @param  expected  the expected matrix of derivative values, estimated by finite differences.
     * @param  actual    the actual matrix computed by the transform to be tested.
     * @param  tolmat    the tolerance value for each matrix elements, or {@code null} for a strict comparison.
     * @param  message   the message to report in case of failure, or {@code null} if none.
     * @throws DerivativeFailure if at least one matrix element is not equal to the expected value.
     *
     * @see #verifyDerivative(double[])
     * @see org.opengis.test.Assertions#assertMatrixEquals(Matrix, Matrix, double, String)
     *
     * @since 3.1
     */
    protected void assertMatrixEquals(final Matrix expected, final Matrix actual, final Matrix tolmat, final String message)
            throws DerivativeFailure
    {
        final int numRow = expected.getNumRow();
        final int numCol = expected.getNumCol();
        assertEquals(numRow, actual.getNumRow(), "Wrong number of rows.");
        assertEquals(numCol, actual.getNumCol(), "Wrong number of columns.");
        for (int i=0; i<numCol; i++) {
            for (int j=0; j<numRow; j++) {
                final double e = expected.getElement(j, i);
                final double a = actual  .getElement(j, i);
                final double d = abs(e - a);
                final double tol = (tolmat != null) ? tolmat.getElement(j, i) : 0;
                if (!(d <= tol) && Double.doubleToLongBits(a) != Double.doubleToLongBits(e)) {
                    final String lineSeparator = System.getProperty("line.separator", "\n");
                    final StringBuilder buffer = new StringBuilder(1000);
                    appendErrorHeader(buffer, message);
                    buffer.append(lineSeparator).append("Matrix(").append(j).append(',').append(i)
                            .append("): expected ").append(e).append(" but got ").append(a)
                            .append(" (a difference of ").append(d).append(')').append(lineSeparator)
                            .append("Expected matrix (may be approximate):").append(lineSeparator);
                    SimpleMatrix.toString(expected, buffer, lineSeparator);
                    buffer.append(lineSeparator).append("Actual matrix:").append(lineSeparator);
                    SimpleMatrix.toString(actual, buffer, lineSeparator);
                    if (tolmat != null) {
                        buffer.append(lineSeparator).append("Tolerance matrix:").append(lineSeparator);
                        SimpleMatrix.toString(tolmat, buffer, lineSeparator);
                    }
                    throw new DerivativeFailure(buffer.toString());
                }
            }
        }
    }

    /**
     * Invoked for preparing the header of a test failure message. The default implementation
     * just append the given message. Subclasses can override this message in order to provide
     * additional information.
     *
     * @param buffer   the buffer in which to append the header.
     * @param message  user supplied message to append, or {@code null}.
     */
    void appendErrorHeader(final StringBuilder buffer, final String message) {
        if (message != null) {
            buffer.append(message.trim());
        }
    }

    /**
     * Returns {@code true} if the given array is an array of {@code double} primitive types.
     *
     * @param  array  the object to test.
     * @return whether the given object is an array of {@code double} values.
     */
    private static boolean isDoubleArray(final Object array) {
        return array.getClass().getComponentType() == Double.TYPE;
    }

    /**
     * Invoked by all {@code assertCoordinateEqual(…)} methods before two positions are compared.
     * This method allows subclasses to replace some equivalent coordinate values by a unique value.
     * For example, implementations may ensure that longitude values are contained in the ±180°
     * range, applying 360° shifts if needed.
     *
     * <p>The default implementation does nothing. Subclasses can modify the {@code actual} coordinate
     * values directly using the {@link DirectPosition#setOrdinate(int, double)} method.</p>
     *
     * @param expected  the expected coordinate value provided by the test case.
     * @param actual    the coordinate value computed by the {@linkplain #transform} being tested.
     * @param mode      indicates if the coordinates being compared are the result of a direct
     *                  or inverse transform, or if strict equality is requested.
     *
     * @since 3.1
     */
    protected void normalize(DirectPosition expected, DirectPosition actual, CalculationType mode) {
    }
}
