/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2011 Open Geospatial Consortium, Inc.
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
import java.lang.reflect.Array;

import org.opengis.geometry.DirectPosition;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;

import org.junit.Before;
import org.opengis.test.TestCase;
import static org.opengis.test.Assert.*;


/**
 * Base class for tests of {@link MathTransform} implementations. Subclasses shall assign a value
 * to the {@link #transform} field before to invoke any method in this class. The assigned
 * transform must support the {@link MathTransform#transform(DirectPosition,DirectPosition)}
 * method. By default the other transform methods (working on arrays) are assumed supported,
 * but their tests can be disabled on a case-by-case basis by setting some
 * <code>is&lt;</code><var>Operation</var><code>&gt;Supported</code> fields to {@code false}.
 * <p>
 * Once the fields are assigned their values, subclasses can invoke any of the {@code verify}
 * methods in their test methods. Callers must supply the input coordinate points to be used
 * for testing purpose, since the range of valid values is usually transform-dependent.
 * <p>
 * Methods in this class do not {@linkplain org.opengis.test.Validators#validate(MathTransform)
 * validate} the transform. It is caller responsibility to validate the transform if wanted.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.0
 * @since   2.2
 */
public strictfp abstract class TransformTestCase extends TestCase {
    /**
     * The maximal offset (in number of coordinate points), exclusive, to apply when testing
     * {@code MathTransform.transform(...)} with overlapping arrays.  Higher values make the
     * tests more extensive but slower. Small values like 8 are usually enough.
     */
    private static final int POINTS_OFFSET = 8;

    /**
     * The transform being tested. Subclasses should assign a value to this field,
     * typically in some method annotated with JUnit {@link Before} annotation.
     * <p>
     * Subclasses should consider setting a {@linkplain #tolerance} threshold value
     * together with the transform.
     *
     * @see #tolerance
     */
    protected MathTransform transform;

    /**
     * {@code true} if {@link MathTransform#transform(double[],int,double[],int,int)}
     * is supported. The default value is {@code true}. Vendor can set this value to
     * {@code false} in order to test a transform which is not fully implemented.
     */
    protected boolean isDoubleToDoubleSupported = true;

    /**
     * {@code true} if {@link MathTransform#transform(float[],int,float[],int,int)}
     * is supported. The default value is {@code true}. Vendor can set this value to
     * {@code false} in order to test a transform which is not fully implemented.
     */
    protected boolean isFloatToFloatSupported = true;

    /**
     * {@code true} if {@link MathTransform#transform(double[],int,float[],int,int)}
     * is supported. The default value is {@code true}. Vendor can set this value to
     * {@code false} in order to test a transform which is not fully implemented.
     */
    protected boolean isDoubleToFloatSupported = true;

    /**
     * {@code true} if {@link MathTransform#transform(float[],int,double[],int,int)}
     * is supported. The default value is {@code true}. Vendor can set this value to
     * {@code false} in order to test a transform which is not fully implemented.
     */
    protected boolean isFloatToDoubleSupported = true;

    /**
     * {@code true} if the destination array can be the same than the source array,
     * and the source and target region of the array can overlap. The default value
     * is {@code true}. Vendor can set this value to {@code false} in order to test
     * a transform which is not fully implemented.
     */
    protected boolean isOverlappingArraySupported = true;

    /**
     * {@code true} if {@link MathTransform#inverse} is supported.
     */
    protected boolean isInverseTransformSupported = true;

    /**
     * Maximum difference to be accepted when comparing a transformed ordinate value with
     * the expected one. By default this threshold is absolute; no special computation is
     * performed for taking in account the magnitude of the ordinate being compared. If a
     * subclass needs to set a relative tolerance threshold instead than an absolute one,
     * it should override the {@link #tolerance(double)} method.
     * <p>
     * The default value is 0, which means that strict equality will be required. Subclasses
     * should set a more suitable tolerance threshold when {@linkplain #transform} is assigned
     * a value.
     *
     * @see #transform
     * @see #tolerance(double)
     */
    protected double tolerance = 0;

    /**
     * Creates a test case initialized to default values. The {@linkplain #transform}
     * is initially null, the {@linkplain #tolerance} threshold is initially zero and
     * all <code>is&lt;</code><var>Operation</var><code>&gt;Supported</code> are set
     * to {@code true}.
     */
    protected TransformTestCase() {
    }

    /**
     * Returns the tolerance threshold for comparing the given ordinate value. The default
     * implementation returns the {@link #tolerance} value directly, thus implementing an
     * absolute tolerance threshold. If a subclass needs a relative tolerance threshold
     * instead, it can override this method as below:
     *
     * <blockquote><code>
     * return tolerance * Math.abs(ordinate);
     * </code></blockquote>
     *
     * @param  ordinate The ordinate value being compared.
     * @return The absolute tolerance threshold to use for comparing the given ordinate.
     */
    protected double tolerance(final double ordinate) {
        return tolerance;
    }

    /**
     * Ensures that all <code>is&lt;</code><var>Operation</var><code>&gt;Supported</code> fields
     * are set to {@code true}. This method can be invoked before testing a math transform which
     * is expected to be fully implemented.
     */
    protected void assertAllTestsEnabled() {
        assertTrue("isDoubleToDoubleSupported",   isDoubleToDoubleSupported  );
        assertTrue("isFloatToFloatSupported",     isFloatToFloatSupported    );
        assertTrue("isDoubleToFloatSupported",    isDoubleToFloatSupported   );
        assertTrue("isFloatToDoubleSupported",    isFloatToDoubleSupported   );
        assertTrue("isOverlappingArraySupported", isOverlappingArraySupported);
        assertTrue("isInverseTransformSupported", isInverseTransformSupported);
    }

    /**
     * Transforms the given coordinates and verifies that the result is equals (within a positive
     * delta) to the expected ones. If the difference between an expected and actual ordinate value
     * is greater than the {@linkplain #tolerance(double) tolerance} threshold, then the assertion
     * fails.
     * <p>
     * If {@link #isInverseTransformSupported} is {@code true}, then this method will also
     * transform the expected coordinate points using the {@linkplain MathTransform#inverse
     * inverse transform} and compare with the source coordinates.
     *
     * @param  coordinates The coordinate points to transform.
     * @param  expected The expect result of the transformation, or
     *         {@code null} if {@code coordinates} is expected to be null.
     * @throws TransformException if the transformation failed.
     */
    protected void verifyTransform(final double[] coordinates, final double[] expected)
            throws TransformException
    {
        /*
         * Checks the state of this TransformTestCase object, including a shallow inspection of
         * the MathTransform. We check only the parts that are significant to this test method.
         * Full MathTransform validation is not the job of this method.
         */
        final MathTransform transform = this.transform; // Protect from changes.
        assertNotNull("TransformTestCase.transform shall be assigned a value.", transform);
        final int sourceDimension = transform.getSourceDimensions();
        final int targetDimension = transform.getTargetDimensions();
        assertStrictlyPositive("Source dimension must be positive.", sourceDimension);
        assertStrictlyPositive("Target dimension must be positive.", targetDimension);
        final MathTransform inverse;
        if (isInverseTransformSupported) {
            inverse = transform.inverse();
            assertNotNull("MathTransform.inverse() shall not return null.", inverse);
            assertEquals("Inconsistent source dimension of the inverse transform.",
                    targetDimension, inverse.getSourceDimensions());
            assertEquals("Inconsistent target dimension of the inverse transform.",
                    sourceDimension, inverse.getTargetDimensions());
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
        assertEquals("Source dimension is not a divisor of the coordinates array length.",
                0, coordinates.length % sourceDimension);
        assertEquals("Target dimension is not a divisor of the expected array length.",
                0, expected.length % targetDimension);
        final int numPts = expected.length / targetDimension;
        assertEquals("Mismatched number of points.", numPts, coordinates.length / sourceDimension);
        /*
         * Now performs the test.
         */
        final SimpleDirectPosition source = new SimpleDirectPosition(sourceDimension);
        final SimpleDirectPosition target = new SimpleDirectPosition(targetDimension);
        final SimpleDirectPosition back   = new SimpleDirectPosition(sourceDimension);
        for (int i=0; i<numPts; i++) {
            final int sourceOffset = i * sourceDimension;
            final int targetOffset = i * targetDimension;
            System.arraycopy(coordinates, sourceOffset, source.ordinates, 0, sourceDimension);
            assertSame("MathTransform.transform(DirectPosition,...) shall use the given target.",
                    target, transform.transform(source, target));
            assertCoordinatesEqual("Unexpected transform result.", targetDimension,
                    expected, targetOffset, target.ordinates, 0, 1, false, i);
            assertCoordinatesEqual("Source coordinate has been modified.", sourceDimension,
                    coordinates, sourceOffset, source.ordinates, 0, 1, true, i);

            if (inverse == null) {
                continue;
            }
            System.arraycopy(expected, targetOffset, target.ordinates, 0, targetDimension);
            assertSame("MathTransform.transform(DirectPosition,...) shall use the given target.",
                    back, inverse.transform(target, back));
            assertCoordinateEquals("Unexpected result of inverse transform.",
                    source.ordinates, back.ordinates, i, false);
            assertCoordinatesEqual("Source coordinate has been modified.", targetDimension,
                    expected, targetOffset, target.ordinates, 0, 1, true, i);
        }
    }

    /**
     * Transforms the given coordinates, applies the inverse transform and compares with the
     * original values. If a difference between the expected and actual ordinate values is
     * greater than the {@linkplain #tolerance(double) tolerance} threshold, then the assertion
     * fails.
     * <p>
     * At the difference of {@link #verifyTransform(double[],double[])}, this method do
     * not require an array of expected values. The expected values are calculated from
     * the transform itself.
     *
     * @param  coordinates The source coordinates to transform.
     * @throws TransformException if at least one coordinate can't be transformed.
     */
    protected void verifyInverse(final double[] coordinates) throws TransformException {
        /*
         * Checks the state of this TransformTestCase object, including a shallow inspection of
         * the MathTransform. We check only the parts that are significant to this test method.
         * Full MathTransform validation is not the job of this method.
         */
        final MathTransform transform = this.transform; // Protect from changes.
        assertNotNull("TransformTestCase.transform shall be assigned a value.", transform);
        final int sourceDimension = transform.getSourceDimensions();
        final int targetDimension = transform.getTargetDimensions();
        assertStrictlyPositive("Source dimension must be positive.", sourceDimension);
        assertStrictlyPositive("Target dimension must be positive.", targetDimension);
        assertTrue("isInverseTransformSupported == false.", isInverseTransformSupported);
        final MathTransform inverse = transform.inverse();
        assertNotNull("MathTransform.inverse() shall not return null.", inverse);
        assertEquals("Inconsistent source dimension of the inverse transform.",
                targetDimension, inverse.getSourceDimensions());
        assertEquals("Inconsistent target dimension of the inverse transform.",
                sourceDimension, inverse.getTargetDimensions());
        /*
         * Checks the method arguments.
         */
        assertNotNull("Coordinates array expected in argument.", coordinates);
        assertEquals("Source dimension is not a divisor of the coordinates array length.",
                0, coordinates.length % sourceDimension);
        final int numPts = coordinates.length / sourceDimension;
        /*
         * Now performs the test.
         */
        final SimpleDirectPosition source = new SimpleDirectPosition(sourceDimension);
        final SimpleDirectPosition back   = new SimpleDirectPosition(targetDimension);
        DirectPosition target = null;
        for (int i=0; i<numPts; i++) {
            final int offset = i*sourceDimension;
            System.arraycopy(coordinates, offset, source.ordinates, 0, sourceDimension);
            target = transform.transform(source, target);
            assertNotNull("MathTransform.transform(DirectPosition,...) shall not return null.", target);
            assertEquals("Transformed point has wrong dimension.",
                    targetDimension, target.getDimension());
            assertSame("MathTransform.transform(DirectPosition,...) shall use the given target.",
                    back, inverse.transform(target, back));
            assertCoordinateEquals("Unexpected result of inverse transform.",
                    source.ordinates, back.ordinates, i, false);
            assertCoordinatesEqual("Source coordinate has been modified.", sourceDimension,
                    coordinates, offset, source.ordinates, 0, 1, true, i);
        }
    }

    /**
     * Transforms the given coordinates, applies the inverse transform and compares with the
     * original values. If a difference between the expected and actual ordinate values is
     * greater than the {@linkplain #tolerance(double) tolerance} threshold, then the assertion
     * fails.
     * <p>
     * The default implementation delegates to {@link #verifyInverse(double[])}.
     *
     * @param  coordinates The source coordinates to transform.
     * @throws TransformException if at least one coordinate can't be transformed.
     */
    protected void verifyInverse(final float[] coordinates) throws TransformException {
        final double[] sourceDoubles = new double[coordinates.length];
        for (int i=0; i<coordinates.length; i++) {
            sourceDoubles[i] = coordinates[i];
        }
        verifyInverse(sourceDoubles);
        final int dimension = transform.getSourceDimensions();
        assertCoordinatesEqual("Unexpected change in source coordinates.", dimension,
                coordinates, 0, sourceDoubles, 0, coordinates.length / dimension, true);
    }

    /**
     * Transforms coordinates using various versions of {@code MathTransform.transform(...)}
     * and verifies that they produce the same numerical values. The values calculated by
     * {@link MathTransform#transform(DirectPosition,DirectPosition)} are used as the reference.
     * Other transform methods (operating on arrays) will be compared against that reference,
     * unless their checks were disabled (see class javadoc for details).
     * <p>
     * This method expects an array of {@code float} values instead than {@code double}
     * for making sure that the {@code MathTransform.transform(float[], ...)} and
     * {@code MathTransform.transform(double[], ...)} methods produces the same numerical values.
     * The {@code double} values may show extra digits when formatted in base 10, but this is not
     * significant if their IEEE 754 representation (which use base 2) are equivalent.
     * <p>
     * This method does not verify the inverse transform. The later can be verified with
     * {@link #verifyInverse(float[])} if wanted.
     *
     * @param  sourceFloats The source coordinates to transform as an array of {@code float} values.
     * @return The transformed coordinates, returned for convenience.
     * @throws TransformException if at least one coordinate can't be transformed.
     */
    protected float[] verifyConsistency(final float[] sourceFloats) throws TransformException {
        final MathTransform transform = this.transform; // Protect from changes.
        assertNotNull("TransformTestCase.transform shall be assigned a value.", transform);
        final int sourceDimension = transform.getSourceDimensions();
        final int targetDimension = transform.getTargetDimensions();
        assertEquals("Source dimension is not a divisor of the coordinates array length.",
                0, sourceFloats.length % sourceDimension);
        final int numPts = sourceFloats.length / sourceDimension;
        final float [] transformed   = new float [targetDimension * numPts];
        final float [] targetFloats  = new float [Math.max(sourceDimension, targetDimension) * (numPts + POINTS_OFFSET)];
        final double[] sourceDoubles = new double[sourceFloats.length];
        final double[] targetDoubles = new double[targetFloats.length];
        /*
         * Copies the source ordinates (to be used later) and performs the transformations using
         * MathTransform.transform(DirectPosition) method. Result is stored in the "transformed"
         * array and will not be modified anymore from that point.
         */
        for (int i=0; i<sourceDoubles.length; i++) {
            sourceDoubles[i] = sourceFloats[i];
        }
        if (true) { // MathTransform.transform(DirectPosition) is not optional in this test.
            final SimpleDirectPosition sourcePosition = new SimpleDirectPosition(sourceDimension);
            DirectPosition targetPosition = null;
            int targetOffset = 0;
            for (int i=0; i < sourceDoubles.length; i += sourceDimension) {
                System.arraycopy(sourceDoubles, i, sourcePosition.ordinates, 0, sourceDimension);
                targetPosition = transform.transform(sourcePosition, targetPosition);
                assertNotNull("MathTransform.transform(DirectPosition,...) shall not return null.", targetPosition);
                assertEquals("MathTransform.transform(DirectPosition) must return a position having " +
                        "the same dimension than MathTransform.getTargetDimension().",
                        targetDimension, targetPosition.getDimension());
                for (int j=0; j<targetDimension; j++) {
                    transformed[targetOffset++] = (float) targetPosition.getOrdinate(j);
                }
            }
            assertEquals(transformed.length, targetOffset);
        }
        /*
         * Tests transformation in distincts (non-overlapping) arrays.
         */
        if (isDoubleToDoubleSupported) {
            Arrays.fill(targetDoubles, Double.NaN);
            transform.transform(sourceDoubles, 0, targetDoubles, 0, numPts);
            assertCoordinatesEqual("MathTransform.transform(double[],0,double[],0,n) modified a source coordinate.",
                    sourceDimension, sourceFloats, 0, sourceDoubles, 0, numPts, true);
            assertCoordinatesEqual("MathTransform.transform(double[],0,double[],0,n) error.",
                    targetDimension, targetDoubles, 0, transformed, 0, numPts, false);
        }
        if (isFloatToFloatSupported) {
            Arrays.fill(targetFloats, Float.NaN);
            transform.transform(sourceFloats, 0, targetFloats, 0, numPts);
            assertCoordinatesEqual("MathTransform.transform(float[],0,float[],0,n) modified a source coordinate.",
                    sourceDimension, sourceDoubles, 0, sourceFloats, 0, numPts, true);
            assertCoordinatesEqual("MathTransform.transform(float[],0,float[],0,n) error.",
                    targetDimension, transformed, 0, targetFloats, 0, numPts, false);
        }
        if (isDoubleToFloatSupported) {
            Arrays.fill(targetFloats, Float.NaN);
            transform.transform(sourceDoubles, 0, targetFloats, 0, numPts);
            assertCoordinatesEqual("MathTransform.transform(double[],0,float[],0,n) modified a source coordinate.",
                    sourceDimension, sourceFloats, 0, sourceDoubles, 0, numPts, true);
            assertCoordinatesEqual("MathTransform.transform(double[],0,float[],0,n) error.",
                    targetDimension, transformed, 0, targetFloats, 0, numPts, false);
        }
        if (isFloatToDoubleSupported) {
            Arrays.fill(targetDoubles, Double.NaN);
            transform.transform(sourceFloats, 0, targetDoubles, 0, numPts);
            assertCoordinatesEqual("MathTransform.transform(float[],0,double[],0,n) modified a source coordinate.",
                    sourceDimension, sourceDoubles, 0, sourceFloats, 0, numPts, true);
            assertCoordinatesEqual("MathTransform.transform(float[],0,double[],0,n) error.",
                    targetDimension, transformed, 0, targetDoubles, 0, numPts, false);
        }
        /*
         * Tests transformation in overlapping arrays.
         */
        if (isOverlappingArraySupported) {
            for (int sourceOffset=0; sourceOffset < POINTS_OFFSET*sourceDimension; sourceOffset += sourceDimension) {
                for (int targetOffset=0; targetOffset < POINTS_OFFSET*targetDimension; targetOffset += targetDimension) {
                    System.arraycopy(sourceFloats,  0, targetFloats,  sourceOffset, sourceFloats .length);
                    System.arraycopy(sourceDoubles, 0, targetDoubles, sourceOffset, sourceDoubles.length);
                    transform.transform(targetFloats,  sourceOffset, targetFloats,  targetOffset, numPts);
                    transform.transform(targetDoubles, sourceOffset, targetDoubles, targetOffset, numPts);
                    assertCoordinatesEqual("MathTransform.transform(float[],0,float[],0,n) error.",
                            targetDimension, transformed, 0, targetFloats, targetOffset, numPts, false);
                    assertCoordinatesEqual("MathTransform.transform(double[],0,double[],0,n) error.",
                            targetDimension, transformed, 0, targetDoubles, targetOffset, numPts, false);
                }
            }
        }
        return transformed;
    }

    /**
     * Asserts that a single coordinate is equal to the expected one within a positive delta.
     * If the comparison fails, the given message is completed with the expected and actual
     * values, and the index of the ordinate where the failure was found.
     *
     * @param message  The message to print in case of failure.
     * @param expected The array of expected ordinate values.
     * @param actual   The array of ordinate values to check against the expected ones.
     * @param index    The index of the coordinate point being compared, for message formatting.
     * @param strict   {@code true} for ignoring the {@linkplain #tolerance(double) tolerance}
     *                 threshold. In such case, ordinate values are checked for strict equality.
     */
    protected final void assertCoordinateEquals(final String message, final float[] expected,
            final float[] actual, final int index, final boolean strict)
    {
        final int dimension = expected.length;
        assertEquals(dimension, actual.length);
        assertCoordinatesEqual(message, dimension, expected, 0, actual, 0, 1, strict, index);
    }

    /**
     * Asserts that a single coordinate is equal to the expected one within a positive delta.
     * If the comparison fails, the given message is completed with the expected and actual
     * values, and the index of the ordinate where the failure was found.
     *
     * @param message  The message to print in case of failure.
     * @param expected The array of expected ordinate values.
     * @param actual   The array of ordinate values to check against the expected ones.
     * @param index    The index of the coordinate point being compared, for message formatting.
     * @param strict   {@code true} for ignoring the {@linkplain #tolerance(double) tolerance}
     *                 threshold. In such case, ordinate values are checked for strict equality.
     */
    protected final void assertCoordinateEquals(final String message, final float[] expected,
            final double[] actual, final int index, final boolean strict)
    {
        final int dimension = expected.length;
        assertEquals(dimension, actual.length);
        assertCoordinatesEqual(message, dimension, expected, 0, actual, 0, 1, strict, index);
    }

    /**
     * Asserts that a single coordinate is equal to the expected one within a positive delta.
     * If the comparison fails, the given message is completed with the expected and actual
     * values, and the index of the ordinate where the failure was found.
     *
     * @param message  The message to print in case of failure.
     * @param expected The array of expected ordinate values.
     * @param actual   The array of ordinate values to check against the expected ones.
     * @param index    The index of the coordinate point being compared, for message formatting.
     * @param strict   {@code true} for ignoring the {@linkplain #tolerance(double) tolerance}
     *                 threshold. In such case, ordinate values are checked for strict equality.
     */
    protected final void assertCoordinateEquals(final String message, final double[] expected,
            final float[] actual, final int index, final boolean strict)
    {
        final int dimension = expected.length;
        assertEquals(dimension, actual.length);
        assertCoordinatesEqual(message, dimension, expected, 0, actual, 0, 1, strict, index);
    }

    /**
     * Asserts that a single coordinate is equal to the expected one within a positive delta.
     * If the comparison fails, the given message is completed with the expected and actual
     * values, and the index of the ordinate where the failure was found.
     *
     * @param message  The message to print in case of failure.
     * @param expected The array of expected ordinate values.
     * @param actual   The array of ordinate values to check against the expected ones.
     * @param index    The index of the coordinate point being compared, for message formatting.
     * @param strict   {@code true} for ignoring the {@linkplain #tolerance(double) tolerance}
     *                 threshold. In such case, ordinate values are checked for strict equality.
     */
    protected final void assertCoordinateEquals(final String message, final double[] expected,
            final double[] actual, final int index, final boolean strict)
    {
        final int dimension = expected.length;
        assertEquals(dimension, actual.length);
        assertCoordinatesEqual(message, dimension, expected, 0, actual, 0, 1, strict, index);
    }

    /**
     * Asserts that coordinate values are equal to the expected ones within a positive delta.
     * If the comparison fails, the given message is completed with the expected and actual
     * values, and the index of the coordinate where the failure was found.
     *
     * @param message        The message to print in case of failure.
     * @param dimension      The dimension of each coordinate points in the arrays.
     * @param expectedPts    The array of expected coordinate values.
     * @param expectedOffset Index of the first valid ordinate in the {@code expectedPts} array.
     * @param actualPts      The array of coordinate values to check against the expected ones.
     * @param actualOffset   Index of the first valid ordinate in the {@code actualPts} array.
     * @param numPoints      Number of coordinate points to compare.
     * @param strict         {@code true} for ignoring the {@linkplain #tolerance(double) tolerance}
     *                       threshold. In such case, ordinate values are checked for strict equality.
     */
    protected final void assertCoordinatesEqual(
            final String  message,     final int dimension,
            final float[] expectedPts, final int expectedOffset,
            final float[] actualPts,   final int actualOffset,
            final int     numPoints,   final boolean strict)
    {
        assertCoordinatesEqual(message, dimension, expectedPts, expectedOffset,
                actualPts, actualOffset, numPoints, strict, 0);
    }

    /**
     * Asserts that coordinate values are equal to the expected ones within a positive delta.
     * If the comparison fails, the given message is completed with the expected and actual
     * values, and the index of the coordinate where the failure was found.
     *
     * @param message        The message to print in case of failure.
     * @param dimension      The dimension of each coordinate points in the arrays.
     * @param expectedPts    The array of expected coordinate values.
     * @param expectedOffset Index of the first valid ordinate in the {@code expectedPts} array.
     * @param actualPts      The array of coordinate values to check against the expected ones.
     * @param actualOffset   Index of the first valid ordinate in the {@code actualPts} array.
     * @param numPoints      Number of coordinate points to compare.
     * @param strict         {@code true} for ignoring the {@linkplain #tolerance(double) tolerance}
     *                       threshold. In such case, ordinate values are checked for strict equality.
     */
    protected final void assertCoordinatesEqual(
            final String   message,     final int dimension,
            final float[]  expectedPts, final int expectedOffset,
            final double[] actualPts,   final int actualOffset,
            final int numPoints,        final boolean strict)
    {
        assertCoordinatesEqual(message, dimension, expectedPts, expectedOffset,
                actualPts, actualOffset, numPoints, strict, 0);
    }

    /**
     * Asserts that coordinate values are equal to the expected ones within a positive delta.
     * If the comparison fails, the given message is completed with the expected and actual
     * values, and the index of the coordinate where the failure was found.
     *
     * @param message        The message to print in case of failure.
     * @param dimension      The dimension of each coordinate points in the arrays.
     * @param expectedPts    The array of expected coordinate values.
     * @param expectedOffset Index of the first valid ordinate in the {@code expectedPts} array.
     * @param actualPts      The array of coordinate values to check against the expected ones.
     * @param actualOffset   Index of the first valid ordinate in the {@code actualPts} array.
     * @param numPoints      Number of coordinate points to compare.
     * @param strict         {@code true} for ignoring the {@linkplain #tolerance(double) tolerance}
     *                       threshold. In such case, ordinate values are checked for strict equality.
     */
    protected final void assertCoordinatesEqual(
            final String   message,     final int dimension,
            final double[] expectedPts, final int expectedOffset,
            final float [] actualPts,   final int actualOffset,
            final int      numPoints,   final boolean strict)
    {
        assertCoordinatesEqual(message, dimension, expectedPts, expectedOffset,
                actualPts, actualOffset, numPoints, strict, 0);
    }

    /**
     * Asserts that coordinate values are equal to the expected ones within a positive delta.
     * If the comparison fails, the given message is completed with the expected and actual
     * values, and the index of the coordinate where the failure was found.
     *
     * @param message        The message to print in case of failure.
     * @param dimension      The dimension of each coordinate points in the arrays.
     * @param expectedPts    The array of expected coordinate values.
     * @param expectedOffset Index of the first valid ordinate in the {@code expectedPts} array.
     * @param actualPts      The array of coordinate values to check against the expected ones.
     * @param actualOffset   Index of the first valid ordinate in the {@code actualPts} array.
     * @param numPoints      Number of coordinate points to compare.
     * @param strict         {@code true} for ignoring the {@linkplain #tolerance(double) tolerance}
     *                       threshold. In such case, ordinate values are checked for strict equality.
     */
    protected final void assertCoordinatesEqual(
            final String   message,     final int dimension,
            final double[] expectedPts, final int expectedOffset,
            final double[] actualPts,   final int actualOffset,
            final int      numPoints,   final boolean strict)
    {
        assertCoordinatesEqual(message, dimension, expectedPts, expectedOffset,
                actualPts, actualOffset, numPoints, strict, 0);
    }

    /**
     * Implementation of public assertion methods with the addition of the coordinate
     * index to be reported in error message.
     */
    private void assertCoordinatesEqual(
            final String  message,     final int dimension,
            final float[] expectedPts, int expectedOffset,
            final float[] actualPts,   int actualOffset,
            final int     numPoints,   final boolean strict, final int reportedIndex)
    {
        final int numOrdinates = numPoints * dimension;
        for (int i=0; i<numOrdinates; i++) {
            final float expected = expectedPts[expectedOffset++];
            final float actual   = actualPts  [  actualOffset++];
            /*
             * This method uses !(a <= b) expressions instead than (a > b) for catching NaN.
             * The next condition working on bit patterns is for for NaN and Infinity values.
             */
            if ((strict || !(Math.abs(actual - expected) <= (float) tolerance(expected)))
                && (Float.floatToIntBits(actual) != Float.floatToIntBits(expected)))
            {
                throw new TransformFailure(formatComparaisonFailure(message, dimension,
                        expectedPts, expectedOffset, actualPts, actualOffset, i,
                        Math.abs(actual - expected), reportedIndex));
            }
        }
    }

    /**
     * Implementation of public assertion methods with the addition of the coordinate
     * index to be reported in error message.
     */
    private void assertCoordinatesEqual(
            final String   message,     final int dimension,
            final float [] expectedPts, int expectedOffset,
            final double[] actualPts,   int actualOffset,
            final int      numPoints,   final boolean strict, final int reportedIndex)
    {
        final int numOrdinates = numPoints * dimension;
        for (int i=0; i<numOrdinates; i++) {
            final float expected = expectedPts[expectedOffset++];
            final float actual   = (float) actualPts[actualOffset++];
            if ((strict || !(Math.abs(actual - expected) <= (float) tolerance(expected)))
                && (Float.floatToIntBits(actual) != Float.floatToIntBits(expected)))
            {
                throw new TransformFailure(formatComparaisonFailure(message, dimension,
                        expectedPts, expectedOffset, actualPts, actualOffset, i,
                        Math.abs(actual - expected), reportedIndex));
            }
        }
    }

    /**
     * Implementation of public assertion methods with the addition of the coordinate
     * index to be reported in error message.
     */
    private void assertCoordinatesEqual(
            final String   message,     final int dimension,
            final double[] expectedPts, int expectedOffset,
            final float [] actualPts,   int actualOffset,
            final int      numPoints,   final boolean strict, final int reportedIndex)
    {
        final int numOrdinates = numPoints * dimension;
        for (int i=0; i<numOrdinates; i++) {
            final float expected = (float) expectedPts[expectedOffset++];
            final float actual   = actualPts[actualOffset++];
            if ((strict || !(Math.abs(actual - expected) <= (float) tolerance(expected)))
                && (Float.floatToIntBits(actual) != Float.floatToIntBits(expected)))
            {
                throw new TransformFailure(formatComparaisonFailure(message, dimension,
                        expectedPts, expectedOffset, actualPts, actualOffset, i,
                        Math.abs(actual - expected), reportedIndex));
            }
        }
    }

    /**
     * Implementation of public assertion methods with the addition of the coordinate
     * index to be reported in error message.
     */
    private void assertCoordinatesEqual(
            final String   message,     final int dimension,
            final double[] expectedPts, int expectedOffset,
            final double[] actualPts,   int actualOffset,
            final int      numPoints,   final boolean strict, final int reportedIndex)
    {
        final int numOrdinates = numPoints * dimension;
        for (int i=0; i<numOrdinates; i++) {
            final double expected = expectedPts[expectedOffset++];
            final double actual   = actualPts[actualOffset++];
            if ((strict || !(Math.abs(actual - expected) <= tolerance(expected)))
                && (Double.doubleToLongBits(actual) != Double.doubleToLongBits(expected)))
            {
                throw new TransformFailure(formatComparaisonFailure(message, dimension,
                        expectedPts, expectedOffset, actualPts, actualOffset, i,
                        Math.abs(actual - expected), reportedIndex));
            }
        }
    }

    /**
     * Formats an error message for a comparison failure.
     *
     * @param message        The header part of the message to format.
     * @param dimension      The dimension of each coordinate points in the arrays.
     * @param expectedPts    The array of expected coordinate values.
     * @param expectedOffset Index next to the point where the comparison failed.
     * @param actualPts      The array of coordinate values to check against the expected ones.
     * @param actualOffset   Index next to the point where the comparison failed.
     * @param failureIndex   Zero-based index where the comparison failed.
     * @param delta          The absolute delta between the expected and actual ordinate values.
     * @param reportedIndex  Value to add to the coordinate index reported in the error message.
     */
    private static String formatComparaisonFailure(
            final String message,     final int dimension,
            final Object expectedPts, int expectedOffset,
            final Object actualPts,   int actualOffset,
            final int failureIndex,   final Number delta, final int reportedIndex)
    {
        final int ordinate = failureIndex % dimension;
        expectedOffset -= (ordinate + 1); // Go back to the index where the coordinate begin.
        actualOffset   -= (ordinate + 1);
        final String lineSeparator = System.getProperty("line.separator", "\n");
        final StringBuilder buffer = new StringBuilder(message).append(lineSeparator)
                .append("DirectPosition").append(dimension).append("D[")
                .append(failureIndex / dimension + reportedIndex).append("]: Expected (");
        for (int j=0; j<dimension; j++) {
            if (j != 0) buffer.append(", ");
            buffer.append(Array.get(expectedPts, expectedOffset++));
        }
        buffer.append(") but got (");
        for (int j=0; j<dimension; j++) {
            if (j != 0) buffer.append(", ");
            buffer.append(Array.get(actualPts, actualOffset++));
        }
        return buffer.append(").").append(lineSeparator).append("The delta at ordinate ")
                .append(ordinate).append(" is ").append(delta).toString();
    }
}
