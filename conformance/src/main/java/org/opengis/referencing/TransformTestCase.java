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
package org.opengis.referencing;

import java.util.Arrays;
import org.junit.Before;
import org.opengis.TestCase;
import org.opengis.geometry.DirectPosition;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;


/**
 * Base class of tests performing coordinate transformations. This base class provides a
 * {@link #transform} method stressing a {@link MathTransform} instance. Math transforms
 * are required to support {@link MathTransform#transform(DirectPosition,DirectPosition)}
 * method. The other methods (working on arrays) can be disabled on a case-by-case basis
 * if the math transform to be tested is not fully implemented.
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
     * Creates an unitialized test.
     */
    protected TransformTestCase() {
    }

    /**
     * Transforms coordinates using various versions of {@code MathTransform.transform(...)}
     * and asserts that they produce the same numerical values. The values calculated by
     * {@link MathTransform#transform(DirectPosition,DirectPosition)} are used as the reference.
     * All other transform methods (operating on arrays) will be compared against that reference.
     * By default all transform methods are tested, but some tests can be disabled by setting the
     * corresponding {@code isFooSupported} fields to {@code false}.
     * <p>
     * This method expects an array of {@code float} values instead than {@code double}
     * for making sure that the {@code MathTransform.transform(float[], ...)} and
     * {@code MathTransform.transform(double[], ...)} methods get the same numerical values.
     * (Note: the {@code double} values may show extra digits when formatted in base 10, but
     * this is not significant if their IEEE 754 representation in base 2 are equivalent).
     *
     * @param  sourceFloats The source coordinates to transform as an array of {@code float} values.
     * @param  eps Comparaison threshold when comparing transformed coordinates. If the
     *         {@link MathTransform} implementation converts all ordinates to {@code double}
     *         before to perform its internal computation (which is usually the case), then
     *         this value should be strictly zero.
     * @return The transformed coordinates.
     * @throws TransformException if at least one coordinate can't be transformed.
     */
    protected float[] testConsistency(final float[] sourceFloats, final float eps)
            throws TransformException
    {
        final MathTransform transform = this.transform; // Protect from changes.
        assertNotNull("The TransformTestCase.transform field must be assigned a value.", transform);
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
                assertEquals("MathTransform.transform(double[],0,double[],0,n) modified a source coordinate.",
                        sourceFloats[i], (float) sourceDoubles[i], STRICT, i, sourceDimension);
            }
            for (int i=0; i<transformed.length; i++) {
                assertEquals("MathTransform.transform(double[],0,double[],0,n) error.",
                        transformed[i], (float) targetDoubles[i], eps, i, targetDimension);
            }
        }
        if (isFloatToFloatSupported) {
            Arrays.fill(targetFloats, Float.NaN);
            transform.transform(sourceFloats, 0, targetFloats, 0, numPts);
            for (int i=0; i<sourceFloats.length; i++) {
                assertEquals("MathTransform.transform(float[],0,float[],0,n) modified a source coordinate.",
                        (float) sourceDoubles[i], sourceFloats[i], STRICT, i, sourceDimension);
            }
            for (int i=0; i<transformed.length; i++) {
                assertEquals("MathTransform.transform(float[],0,float[],0,n) error.",
                        transformed[i], targetFloats[i], eps, i, targetDimension);
            }
        }
        if (isDoubleToFloatSupported) {
            Arrays.fill(targetFloats, Float.NaN);
            transform.transform(sourceDoubles, 0, targetFloats, 0, numPts);
            for (int i=0; i<sourceDoubles.length; i++) {
                assertEquals("MathTransform.transform(double[],0,float[],0,n) modified a source coordinate.",
                        sourceFloats[i], (float) sourceDoubles[i], STRICT, i, sourceDimension);
            }
            for (int i=0; i<transformed.length; i++) {
                assertEquals("MathTransform.transform(double[],0,float[],0,n) error.",
                        transformed[i], targetFloats[i], eps, i, targetDimension);
            }
        }
        if (isFloatToDoubleSupported) {
            Arrays.fill(targetDoubles, Double.NaN);
            transform.transform(sourceFloats, 0, targetDoubles, 0, numPts);
            for (int i=0; i<sourceFloats.length; i++) {
                assertEquals("MathTransform.transform(float[],0,double[],0,n) modified a source coordinate.",
                        (float) sourceDoubles[i], sourceFloats[i], STRICT, i, sourceDimension);
            }
            for (int i=0; i<transformed.length; i++) {
                assertEquals("MathTransform.transform(float[],0,double[],0,n) error.",
                        transformed[i], (float) targetDoubles[i], eps, i, targetDimension);
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
                        assertEquals("MathTransform.transform(float[],0,float[],0,n) error.",
                                transformed[i], targetFloats[targetOffset + i], eps, i, targetDimension);
                        assertEquals("MathTransform.transform(double[],0,double[],0,n) error.",
                                transformed[i], (float) targetDoubles[targetOffset + i], eps, i, targetDimension);
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
     *
     * @param message   The message to print in case of failure.
     * @param expected  The expected value.
     * @param actual    The value to check against the expected one.
     * @param delta     The maximum delta between expected and actual values.
     * @param index     The index of the ordinate being compared. Used only in case of failure.
     * @param dimension The dimension of coordinates being compared. Used only in case of failure.
     */
    private static void assertEquals(final String message, final float expected, final float actual,
            final float delta, final int index, final int dimension)
    {
        // Following condition uses !(a <= b) instead than (a > b) for catching NaN.
        if (!(Math.abs(actual - expected) <= delta)) {
            // Following condition checks for NaN and Infinity values.
            if (Float.floatToIntBits(actual) != Float.floatToIntBits(expected)) {
                throw new TransformFailure(message + System.getProperty("line.separator", "\n") +
                        "Expected " + expected + " but got " + actual + " at DirectPosition[" +
                        (index / dimension) + "].ordinate(" + (index % dimension) + ").");
            }
        }
    }
}
