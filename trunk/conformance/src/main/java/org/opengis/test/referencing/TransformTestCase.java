/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.test.referencing;

import java.util.Arrays;

import org.opengis.geometry.DirectPosition;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;

import org.junit.Before;
import org.opengis.test.TestCase;


/**
 * Base class for tests of {@link MathTransform} implementations. Subclasses shall assign a value
 * to the {@link #transform} field before to invoke any method in this class. The assigned math
 * transform must support the {@link MathTransform#transform(DirectPosition,DirectPosition)}
 * method. By default the other transform methods (working on arrays) are assumed supported,
 * but their tests can be disabled on a case-by-case basis by setting some
 * <code>is&lt;</code><var>Operation</var><code>&gt;Supported</code> fields to {@code false}.
 * <p>
 * Once the fields are assigned their values, subclasses can invoke any of the {@code verify}
 * methods in their test methods. Callers must supply the input coordinate points to be used
 * for testing purpose, since the range of valid values is usually transform-dependant.
 * <p>
 * Methods in this class do not {@linkplain org.opengis.test.Validators#validate(MathTransform)
 * validate} the transform. It is caller responsability to validate the transform if wanted.
 *
 * @author Martin Desruisseaux (Geomatys)
 * @since GeoAPI 2.2
 */
public strictfp abstract class TransformTestCase extends TestCase {
    /**
     * The maximal offset (in number of coordinate points), exclusive, to apply when testing
     * {@code MathTransform.transform(...)} with overlapping arrays.  Higher values make the
     * tests more extensive but slower. Small values like 8 are usually enough.
     */
    private static final int POINTS_OFFSET = 8;

    /**
     * The comparaison threshold when floating points are required to be strictly equal.
     */
    private static final float STRICT = 0f;

    /**
     * The transform being tested. Subclasses should assign a value to this field,
     * typically in some method annotated with JUnit {@link Before} annotation.
     */
    protected MathTransform transform;

    /**
     * {@code true} if {@link MathTransform#transform(double[],int,double[],int,int)
     * is supported. The default value is {@code true}. Vendor can set this value to
     * {@code false} in order to test a transform which is not fully implemented.
     */
    protected boolean isDoubleToDoubleSupported = true;

    /**
     * {@code true} if {@link MathTransform#transform(float[],int,float[],int,int)
     * is supported. The default value is {@code true}. Vendor can set this value to
     * {@code false} in order to test a transform which is not fully implemented.
     */
    protected boolean isFloatToFloatSupported = true;

    /**
     * {@code true} if {@link MathTransform#transform(double[],int,float[],int,int)
     * is supported. The default value is {@code true}. Vendor can set this value to
     * {@code false} in order to test a transform which is not fully implemented.
     */
    protected boolean isDoubleToFloatSupported = true;

    /**
     * {@code true} if {@link MathTransform#transform(float[],int,double[],int,int)
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
     * Maximum <em>relative</em> difference to be accepted when comparing a transformed
     * ordinate value with the expected one. This tolerance threshold is relative to the
     * expected ordinate value. For each ordinate being compared, the absolute tolerance
     * threshold ({@code delta}) will be computed by the following pseudo-code (handling
     * of NaN and infinity values omitted for simplicity):
     *
     * <blockquote><code>
     * delta = Math.max(1, Math.abs(expectedOrdinateValue)) * tolerance;
     * </code></blockquote>
     *
     * The default value is {@code 1E-10}.
     */
    protected double tolerance = 1E-10;

    /**
     * Creates an unitialized test.
     */
    protected TransformTestCase() {
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
     * Transforms the given coordinate and verifies that the result is equals (within a positive
     * delta) to the expected one. If the relative difference between an expected and actual
     * ordinate value is greater than the {@linkplain #tolerance} value, then the assertion fails.
     * <p>
     * If {@link #isInverseTransformSupported} is {@code true}, then this method will also
     * transform the expected coordinate point using the {@linkplain MathTransform#inverse
     * inverse transform} and compare the result with the source coordinate.
     * <p>
     * The default implementation delegates to {@link #verifyTransform(double[][],double[][])}.
     *
     * @param  coordinate A single coordinate point to transform.
     * @param  expected The expect result of the transformation, or
     *         {@code null} if {@code coordinate} is expected to be null.
     * @throws TransformException if the transformation failed.
     */
    protected void verifyTransform(final double[] coordinate, final double[] expected)
            throws TransformException
    {
        if (expected == null) {
            assertNull(coordinate);
        } else {
            assertNotNull(coordinate);
            verifyTransform(new double[][] {coordinate}, new double[][] {expected});
        }
    }

    /**
     * Transforms the given coordinates and verifies that the result is equals (within a positive
     * delta) to the expected ones. If the relative difference between an expected and actual
     * value is greater than the {@linkplain #tolerance} value, then the assertion fails.
     * <p>
     * If {@link #isInverseTransformSupported} is {@code true}, then this method will also
     * transform the expected coordinate points using the {@linkplain MathTransform#inverse
     * inverse transform} and compare with the source coordinates.
     *
     * @param  coordinates An array of coordinate points to transform.
     * @param  expected The expect results of the transformations, or
     *         {@code null} if {@code coordinates} is expected to be null.
     * @throws TransformException if the transformation failed.
     */
    protected void verifyTransform(final double[][] coordinates, final double[][] expected)
            throws TransformException
    {
        if (expected == null) {
            assertNull(coordinates);
            return;
        }
        assertNotNull(coordinates);
        assertEquals("Mismatched arrays length.", expected.length, coordinates.length);

        final double tolerance = this.tolerance;
        final MathTransform transform = this.transform; // Protect from changes.
        assertNotNull("TransformTestCase.transform shall be assigned a value.", transform);
        final MathTransform inverse;
        if (isInverseTransformSupported) {
            inverse = transform.inverse();
            assertNotNull("MathTransform.inverse() shall not return null.", inverse);
        } else {
            inverse = null;
        }

        SimpleDirectPosition source = null;
        DirectPosition target = null;
        DirectPosition back = null;
        for (int i=0; i<coordinates.length; i++) {
            final double[] sourceCoordinate = coordinates[i];
            final double[] targetCoordinate = expected[i];
            if (targetCoordinate == null) {
                assertNull(sourceCoordinate);
                return;
            }
            assertNotNull(sourceCoordinate);
            final int sourceDimension = sourceCoordinate.length;
            final int targetDimension = targetCoordinate.length;
            if (source == null || source.getDimension() != sourceDimension) {
                source = new SimpleDirectPosition(sourceDimension);
                back = null;
            }
            System.arraycopy(sourceCoordinate, 0, source.ordinates, 0, sourceDimension);
            if (target != null && target.getDimension() != targetDimension) {
                target = null;
            }
            target = transform.transform(source, target);
            assertNotNull("MathTransform.transform(DirectPosition,...) shall not return null.", target);
            assertEquals("Transformed point has wrong dimension.", targetDimension, target.getDimension());
            for (int j=0; j<targetDimension; j++) {
                assertDoubleEquals("Unexpected transform result.",
                        targetCoordinate[j], target.getOrdinate(j), tolerance, i, j);
            }
            for (int j=0; j<sourceDimension; j++) {
                assertDoubleEquals("Source coordinate has been modified.",
                        sourceCoordinate[j], source.ordinates[j], STRICT, i, j);
            }
            if (inverse != null) {
                back = inverse.transform(target, back);
                assertNotNull("MathTransform.transform(DirectPosition,...) shall not return null.", back);
                assertEquals("Inverse-transformed point has wrong dimension.", sourceDimension, back.getDimension());
                for (int j=0; j<sourceDimension; j++) {
                    assertDoubleEquals("Unexpected result of inverse transform.",
                                source.ordinates[j], back.getOrdinate(j), tolerance, i, j);
                }
            }
        }
    }

    /**
     * Transforms the given coordinates, applies the inverse transform and compares with the
     * original values. If a relative difference between the expected and actual values is
     * greater than the {@linkplain #tolerance} value, then the assertion fails.
     * <p>
     * At the difference of {@link #verifyTransform(double[],double[])}, this method do
     * not require an array of expected values. The expected values are calculated from
     * the transform itself.
     *
     * @param  coordinates The source coordinates to transform.
     * @throws TransformException if at least one coordinate can't be transformed.
     */
    protected void verifyInverse(final double[] coordinates) throws TransformException {
        if (coordinates == null) {
            throw new NullPointerException("Coordinates array expected in argument.");
        }
        if (!isInverseTransformSupported) {
            throw new IllegalStateException("\"isInverseTransformSupported\" is set to false.");
        }
        final double tolerance = this.tolerance;
        final MathTransform transform = this.transform; // Protect from changes.
        assertNotNull("TransformTestCase.transform shall be assigned a value.", transform);
        final MathTransform inverse = transform.inverse();
        final int sourceDimension = transform.getSourceDimensions();
        final int targetDimension = transform.getTargetDimensions();
        assertEquals("Source dimension is not a divisor of the coordinates array length.",
                0, coordinates.length % sourceDimension);
        DirectPosition targetPoint = null;
        DirectPosition sourcePoint = null;
        final SimpleDirectPosition givenPoint = new SimpleDirectPosition(sourceDimension);
        for (int i=0; i < coordinates.length; i += sourceDimension) {
            System.arraycopy(coordinates, i, givenPoint.ordinates, 0, sourceDimension);
            targetPoint = transform.transform(givenPoint, targetPoint);
            assertNotNull("MathTransform.transform(DirectPosition,...) shall not return null.", targetPoint);
            sourcePoint = inverse.transform(targetPoint, sourcePoint);
            assertNotNull("MathTransform.transform(DirectPosition,...) shall not return null.", sourcePoint);
            assertEquals("Transformed point has wrong dimension.",
                    targetDimension, targetPoint.getDimension());
            assertEquals("Inverse-transformed point has wrong dimension.",
                    sourceDimension, sourcePoint.getDimension());
            for (int j=0; j<sourceDimension; j++) {
                assertDoubleEquals("Source coordinate has been modified.",
                        coordinates[i+j], givenPoint.ordinates[j], STRICT, i, j);
                assertDoubleEquals("Unexpected result of inverse transform.",
                        givenPoint.ordinates[j], sourcePoint.getOrdinate(j), tolerance, i, j);
            }
        }
    }

    /**
     * Transforms the given coordinates, applies the inverse transform and compares with the
     * original values. If a relative difference between the expected and actual values is
     * greater than the {@linkplain #tolerance} value, then the assertion fails.
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
        for (int i=0; i<coordinates.length; i++) {
            assertFloatEquals("Unexpected change in source coordinates.",
                    coordinates[i], (float) sourceDoubles[i], STRICT, i, dimension);
        }
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
        final float tolerance = (float) this.tolerance;
        final MathTransform transform = this.transform; // Protect from changes.
        assertNotNull("TransformTestCase.transform shall be assigned a value.", transform);
        final int sourceDimension = transform.getSourceDimensions();
        final int targetDimension = transform.getTargetDimensions();
        assertEquals("Source dimension is not a divisor of the coordinates array length.",
                0, sourceFloats.length % sourceDimension);
        final int numPts = sourceFloats.length / sourceDimension;
        final float [] transformed   = new float [targetDimension * numPts];
        final float [] targetFloats  = new float [targetDimension * (numPts + POINTS_OFFSET)];
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
                assertNotNull("MathTransform.transform(DirectPosition) must return a non-null position.", targetPosition);
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
            for (int i=0; i<sourceDoubles.length; i++) {
                assertFloatEquals("MathTransform.transform(double[],0,double[],0,n) modified a source coordinate.",
                        sourceFloats[i], (float) sourceDoubles[i], STRICT, i, sourceDimension);
            }
            for (int i=0; i<transformed.length; i++) {
                assertFloatEquals("MathTransform.transform(double[],0,double[],0,n) error.",
                        transformed[i], (float) targetDoubles[i], tolerance, i, targetDimension);
            }
        }
        if (isFloatToFloatSupported) {
            Arrays.fill(targetFloats, Float.NaN);
            transform.transform(sourceFloats, 0, targetFloats, 0, numPts);
            for (int i=0; i<sourceFloats.length; i++) {
                assertFloatEquals("MathTransform.transform(float[],0,float[],0,n) modified a source coordinate.",
                        (float) sourceDoubles[i], sourceFloats[i], STRICT, i, sourceDimension);
            }
            for (int i=0; i<transformed.length; i++) {
                assertFloatEquals("MathTransform.transform(float[],0,float[],0,n) error.",
                        transformed[i], targetFloats[i], tolerance, i, targetDimension);
            }
        }
        if (isDoubleToFloatSupported) {
            Arrays.fill(targetFloats, Float.NaN);
            transform.transform(sourceDoubles, 0, targetFloats, 0, numPts);
            for (int i=0; i<sourceDoubles.length; i++) {
                assertFloatEquals("MathTransform.transform(double[],0,float[],0,n) modified a source coordinate.",
                        sourceFloats[i], (float) sourceDoubles[i], STRICT, i, sourceDimension);
            }
            for (int i=0; i<transformed.length; i++) {
                assertFloatEquals("MathTransform.transform(double[],0,float[],0,n) error.",
                        transformed[i], targetFloats[i], tolerance, i, targetDimension);
            }
        }
        if (isFloatToDoubleSupported) {
            Arrays.fill(targetDoubles, Double.NaN);
            transform.transform(sourceFloats, 0, targetDoubles, 0, numPts);
            for (int i=0; i<sourceFloats.length; i++) {
                assertFloatEquals("MathTransform.transform(float[],0,double[],0,n) modified a source coordinate.",
                        (float) sourceDoubles[i], sourceFloats[i], STRICT, i, sourceDimension);
            }
            for (int i=0; i<transformed.length; i++) {
                assertFloatEquals("MathTransform.transform(float[],0,double[],0,n) error.",
                        transformed[i], (float) targetDoubles[i], tolerance, i, targetDimension);
            }
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
                    for (int i=0; i<transformed.length; i++) {
                        assertFloatEquals("MathTransform.transform(float[],0,float[],0,n) error.",
                                transformed[i], targetFloats[targetOffset + i], tolerance, i, targetDimension);
                        assertFloatEquals("MathTransform.transform(double[],0,double[],0,n) error.",
                                transformed[i], (float) targetDoubles[targetOffset + i], tolerance, i, targetDimension);
                    }
                }
            }
        }
        return transformed;
    }

    /**
     * Asserts that two ordinate values are equal to within a positive delta. If the comparaison
     * fails, the given message is completed with the expected and actual values, and the index
     * of the coordinate where the failure was found.
     * <p>
     * Be aware that arguments doesn't have the same meaning than {@link #assertDoubleEquals}.
     * This method is not public partially for this reason.
     *
     * @param message   The message to print in case of failure.
     * @param expected  The expected value.
     * @param actual    The value to check against the expected one.
     * @param tolerance The maximum relative tolerance between expected and actual values.
     * @param index     The index of the ordinate being compared. Used only in case of failure.
     * @param dimension The dimension of coordinates being compared. Used only in case of failure.
     */
    private static void assertFloatEquals(final String message, final float expected, final float actual,
            final float tolerance, final int index, final int dimension)
    {
        // Note: this method uses !(a <= b) expressions instead than (a > b) for catching NaN.
        float delta = Math.abs(tolerance * expected);
        if (!(delta >= tolerance)) {
            delta = tolerance;
        }
        if (!(Math.abs(actual - expected) <= delta)) {
            // Following condition checks for NaN and Infinity values.
            if (Float.floatToIntBits(actual) != Float.floatToIntBits(expected)) {
                throw new TransformFailure(message + System.getProperty("line.separator", "\n") +
                        "Expected " + expected + " but got " + actual + " at DirectPosition[" +
                        (index / dimension) + "].ordinate(" + (index % dimension) + ").");
            }
        }
    }

    /**
     * Asserts that two ordinate values are equal to within a positive delta. If the comparaison
     * fails, the given message is completed with the expected and actual values, and the index
     * of the coordinate where the failure was found.
     * <p>
     * Be aware that arguments doesn't have the same meaning than {@link #assertFloatEquals}.
     * This method is not public partially for this reason.
     *
     * @param message   The message to print in case of failure.
     * @param expected  The expected value.
     * @param actual    The value to check against the expected one.
     * @param tolerance The maximum relative tolerance between expected and actual values.
     * @param point     The index of the point being compared. Used only in case of failure.
     * @param ordinate  The index of ordinate in the above point. Used only in case of failure.
     */
    private static void assertDoubleEquals(final String message, final double expected, final double actual,
            final double tolerance, final int point, final int ordinate)
    {
        // Note: this method uses !(a <= b) expressions instead than (a > b) for catching NaN.
        double delta = Math.abs(tolerance * expected);
        if (!(delta >= tolerance)) {
            delta = tolerance;
        }
        if (!(Math.abs(actual - expected) <= delta)) {
            // Following condition checks for NaN and Infinity values.
            if (Double.doubleToLongBits(actual) != Double.doubleToLongBits(expected)) {
                throw new TransformFailure(message + System.getProperty("line.separator", "\n") +
                        "Expected " + expected + " but got " + actual + " at DirectPosition[" +
                        point + "].ordinate(" + ordinate + ").");
            }
        }
    }
}
