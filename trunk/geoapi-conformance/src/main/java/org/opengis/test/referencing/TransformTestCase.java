/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2008-2011 Open Geospatial Consortium, Inc.
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

import java.util.Random;
import java.util.Arrays;
import java.lang.reflect.Array;
import java.awt.geom.Point2D;

import org.opengis.geometry.DirectPosition;
import org.opengis.referencing.operation.Matrix;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.MathTransform2D;
import org.opengis.referencing.operation.TransformException;

import org.junit.Before;
import org.opengis.test.TestCase;

import static java.lang.StrictMath.*;
import static org.opengis.test.Assert.*;


/**
 * Base class for {@link MathTransform} implementation tests. Subclasses shall assign a value
 * to the {@link #transform} field before to invoke any method in this class. The specified
 * math transform shall support the following mandatory operations:
 * <p>
 * <ul>
 *   <li>{@link MathTransform#getSourceDimensions()}</li>
 *   <li>{@link MathTransform#getTargetDimensions()}</li>
 *   <li>{@link MathTransform#transform(DirectPosition, DirectPosition)}</li>
 * </ul>
 * <p>
 * All other operations are optional. However subclasses shall declare which methods, if any,
 * are unsupported. By default every operations are assumed supported. Tests can be disabled
 * on a case-by-case basis by setting the appropriate
 * <code>is&lt;</code><var>Operation</var><code>&gt;Supported</code> fields to {@code false}.
 * <p>
 * After {@code TransformTestCase} has been setup, subclasses can invoke any of the {@code verify}
 * methods in their JUnit test methods. Callers must supply the input coordinate points to be used
 * for testing purpose, since the range of valid values is usually transform-dependent.
 * <p>
 * Some general rules:
 * <ul>
 *   <li>Coordinate values, or information used for computing coordinate values, are always
 *       specified as arguments to the {@code verify} methods. Everything else are fields in
 *       the {@code TransformTestCase} object.</li>
 *   <li>The methods in this class do not {@linkplain org.opengis.test.Validators#validate(MathTransform)
 *       validate} the transform. It is caller responsibility to validate the transform if wanted.</li>
 *   <li>Unless otherwise indicated, every {@code verify} methods are independent. For example invoking
 *       {@link #verifyConsistency(float[])} does not imply a call to {@link #verifyInverse(float[])}
 *       or {@link #verifyDerivative(double[])}. The later methods must be invoked explicitely if wanted.</li>
 * </ul>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
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
     *
     * @see #isFloatToFloatSupported
     * @see #isDoubleToFloatSupported
     * @see #isFloatToDoubleSupported
     * @see #verifyConsistency(float[])
     */
    protected boolean isDoubleToDoubleSupported = true;

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
    protected boolean isFloatToFloatSupported = true;

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
    protected boolean isDoubleToFloatSupported = true;

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
    protected boolean isFloatToDoubleSupported = true;

    /**
     * {@code true} if the destination array can be the same than the source array,
     * and the source and target region of the array can overlap. The default value
     * is {@code true}. Vendor can set this value to {@code false} in order to test
     * a transform which is not fully implemented.
     *
     * @see #verifyConsistency(float[])
     */
    protected boolean isOverlappingArraySupported = true;

    /**
     * {@code true} if {@link MathTransform#inverse()} is supported. The default value
     * is {@code true}. Vendor can set this value to {@code false} in order to test a
     * transform which is not fully implemented.
     *
     * @see #verifyTransform(double[], double[])
     */
    protected boolean isInverseTransformSupported = true;

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
    protected boolean isDerivativeSupported = true;

    /**
     * The deltas to use for approximating {@linkplain MathTransform#derivative(DirectPosition)
     * math transform derivatives} by the <cite>finite differences</cite> method. The length of
     * this array shall be equals to the {@linkplain MathTransform#getSourceDimensions() number
     * of source dimensions} of the {@linkplain #transform} being tested. Each value in this
     * array is the delta to use for the corresponding dimension.
     * <p>
     * Testers shall provide a non-null value if the {@link #isDerivativeSupported} flag is
     * {@code true}.
     *
     * @see #isDerivativeSupported
     * @see #verifyDerivative(double[])
     *
     * @since 3.1
     */
    protected double[] derivativeDeltas;

    /**
     * Maximum difference to be accepted when comparing a transformed ordinate value with
     * the expected one. By default this threshold is absolute; no special computation is
     * performed for taking in account the magnitude of the ordinate being compared. If a
     * subclass needs to set a relative tolerance threshold instead than an absolute one,
     * it should override the {@link #tolerance(double, int, ComparisonType)} method.
     * <p>
     * The default value is 0, which means that strict equality will be required. Subclasses
     * should set a more suitable tolerance threshold when {@linkplain #transform} is assigned
     * a value.
     *
     * @see #transform
     * @see #tolerance(double, int, ComparisonType)
     */
    protected double tolerance;

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
     *
     * @deprecated Replaced by {@link #tolerance(double, int, ComparisonType)}.
     */
    @Deprecated
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
        assertTrue("isDerivativeSupported",       isDerivativeSupported      );
    }

    /**
     * Transforms the given coordinates and verifies that the result is equals (within a positive
     * delta) to the expected ones. If the difference between an expected and actual ordinate value
     * is greater than the {@linkplain #tolerance(double, int, ComparisonType) tolerance} threshold,
     * then the assertion fails.
     * <p>
     * If {@link #isInverseTransformSupported} is {@code true}, then this method will also
     * transform the expected coordinate points using the {@linkplain MathTransform#inverse
     * inverse transform} and compare with the source coordinates.
     *
     * @param  coordinates The coordinate points to transform.
     * @param  expected The expect result of the transformation, or
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
                    expected, targetOffset, target.ordinates, 0, 1, ComparisonType.DIRECT_TRANSFORM, i);
            assertCoordinatesEqual("Source coordinate has been modified.", sourceDimension,
                    coordinates, sourceOffset, source.ordinates, 0, 1, ComparisonType.STRICT, i);

            if (inverse == null) {
                continue;
            }
            System.arraycopy(expected, targetOffset, target.ordinates, 0, targetDimension);
            assertSame("MathTransform.transform(DirectPosition,...) shall use the given target.",
                    back, inverse.transform(target, back));
            assertCoordinateEquals("Unexpected result of inverse transform.",
                    source.ordinates, back.ordinates, i, ComparisonType.INVERSE_TRANSFORM);
            assertCoordinatesEqual("Source coordinate has been modified.", targetDimension,
                    expected, targetOffset, target.ordinates, 0, 1, ComparisonType.STRICT, i);
        }
    }

    /**
     * Transforms the given coordinates, applies the inverse transform and compares with the
     * original values. If a difference between the expected and actual ordinate values is
     * greater than the {@linkplain #tolerance(double, int, ComparisonType) tolerance} threshold,
     * then the assertion fails.
     * <p>
     * At the difference of {@link #verifyTransform(double[],double[])}, this method do
     * not require an array of expected values. The expected values are calculated from
     * the transform itself.
     *
     * @param  coordinates The source coordinates to transform.
     * @throws TransformException if at least one coordinate can't be transformed.
     */
    protected void verifyInverse(final double... coordinates) throws TransformException {
        assertTrue("isInverseTransformSupported == false.", isInverseTransformSupported);
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
                    source.ordinates, back.ordinates, i, ComparisonType.INVERSE_TRANSFORM);
            assertCoordinatesEqual("Source coordinate has been modified.", sourceDimension,
                    coordinates, offset, source.ordinates, 0, 1, ComparisonType.STRICT, i);
        }
    }

    /**
     * Transforms the given coordinates, applies the inverse transform and compares with the
     * original values. If a difference between the expected and actual ordinate values is
     * greater than the {@linkplain #tolerance(double, int, ComparisonType) tolerance} threshold,
     * then the assertion fails.
     * <p>
     * The default implementation delegates to {@link #verifyInverse(double[])}.
     *
     * @param  coordinates The source coordinates to transform.
     * @throws TransformException if at least one coordinate can't be transformed.
     */
    protected void verifyInverse(final float... coordinates) throws TransformException {
        assertTrue("isInverseTransformSupported == false.", isInverseTransformSupported);
        final double[] sourceDoubles = new double[coordinates.length];
        for (int i=0; i<coordinates.length; i++) {
            sourceDoubles[i] = coordinates[i];
        }
        verifyInverse(sourceDoubles);
        final int dimension = transform.getSourceDimensions();
        assertCoordinatesEqual("Unexpected change in source coordinates.", dimension,
                coordinates, 0, sourceDoubles, 0, coordinates.length / dimension, ComparisonType.STRICT);
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
     * This method does not verify the inverse transform or the derivatives. If desired,
     * those later methods can be verified with the {@link #verifyInverse(float[])} and
     * {@link #verifyDerivative(double[])} methods respectively.
     *
     * @param  sourceFloats The source coordinates to transform as an array of {@code float} values.
     * @return The transformed coordinates, returned for convenience.
     * @throws TransformException if at least one coordinate can't be transformed.
     *
     * @see #isDoubleToDoubleSupported
     * @see #isFloatToFloatSupported
     * @see #isDoubleToFloatSupported
     * @see #isFloatToDoubleSupported
     * @see #isOverlappingArraySupported
     */
    protected float[] verifyConsistency(final float... sourceFloats) throws TransformException {
        final MathTransform transform = this.transform; // Protect from changes.
        assertNotNull("TransformTestCase.transform shall be assigned a value.", transform);
        final int sourceDimension = transform.getSourceDimensions();
        final int targetDimension = transform.getTargetDimensions();
        assertEquals("Source dimension is not a divisor of the coordinates array length.",
                0, sourceFloats.length % sourceDimension);
        final int numPts = sourceFloats.length / sourceDimension;
        final float [] targetFloats    = new float [max(sourceDimension, targetDimension) * (numPts + POINTS_OFFSET)];
        final float [] expectedFloats  = new float [targetDimension * numPts];
        final double[] sourceDoubles   = new double[sourceFloats.length];
        final double[] targetDoubles   = new double[targetFloats.length];
        final double[] expectedDoubles = new double[expectedFloats.length];
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
                assertNotSame("MathTransform.transform(DirectPosition,...) shall not overwrite " +
                        "the source position.", sourcePosition, targetPosition);
                assertEquals("MathTransform.transform(DirectPosition) must return a position having " +
                        "the same dimension than MathTransform.getTargetDimension().",
                        targetDimension, targetPosition.getDimension());
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
        if (isDoubleToDoubleSupported) {
            Arrays.fill(targetDoubles, Double.NaN);
            transform.transform(sourceDoubles, 0, targetDoubles, 0, numPts);
            assertCoordinatesEqual("MathTransform.transform(double[],0,double[],0,n) modified a source coordinate.",
                    sourceDimension, sourceFloats, 0, sourceDoubles, 0, numPts, ComparisonType.STRICT);
            assertCoordinatesEqual("MathTransform.transform(double[],0,double[],0,n) error.",
                    targetDimension, expectedDoubles, 0, targetDoubles, 0, numPts, ComparisonType.DIRECT_TRANSFORM);
        }
        if (isFloatToFloatSupported) {
            Arrays.fill(targetFloats, Float.NaN);
            transform.transform(sourceFloats, 0, targetFloats, 0, numPts);
            assertCoordinatesEqual("MathTransform.transform(float[],0,float[],0,n) modified a source coordinate.",
                    sourceDimension, sourceDoubles, 0, sourceFloats, 0, numPts, ComparisonType.STRICT);
            assertCoordinatesEqual("MathTransform.transform(float[],0,float[],0,n) error.",
                    targetDimension, expectedFloats, 0, targetFloats, 0, numPts, ComparisonType.DIRECT_TRANSFORM);
        }
        if (isDoubleToFloatSupported) {
            Arrays.fill(targetFloats, Float.NaN);
            transform.transform(sourceDoubles, 0, targetFloats, 0, numPts);
            assertCoordinatesEqual("MathTransform.transform(double[],0,float[],0,n) modified a source coordinate.",
                    sourceDimension, sourceFloats, 0, sourceDoubles, 0, numPts, ComparisonType.STRICT);
            assertCoordinatesEqual("MathTransform.transform(double[],0,float[],0,n) error.",
                    targetDimension, expectedFloats, 0, targetFloats, 0, numPts, ComparisonType.DIRECT_TRANSFORM);
        }
        if (isFloatToDoubleSupported) {
            Arrays.fill(targetDoubles, Double.NaN);
            transform.transform(sourceFloats, 0, targetDoubles, 0, numPts);
            assertCoordinatesEqual("MathTransform.transform(float[],0,double[],0,n) modified a source coordinate.",
                    sourceDimension, sourceDoubles, 0, sourceFloats, 0, numPts, ComparisonType.STRICT);
            assertCoordinatesEqual("MathTransform.transform(float[],0,double[],0,n) error.",
                    targetDimension, expectedDoubles, 0, targetDoubles, 0, numPts, ComparisonType.DIRECT_TRANSFORM);
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
                            targetDimension, expectedFloats, 0, targetFloats, targetOffset, numPts, ComparisonType.DIRECT_TRANSFORM);
                    assertCoordinatesEqual("MathTransform.transform(double[],0,double[],0,n) error.",
                            targetDimension, expectedFloats, 0, targetDoubles, targetOffset, numPts, ComparisonType.DIRECT_TRANSFORM);
                }
            }
        }
        /*
         * Tests MathTransform2D methods.
         */
        if (transform instanceof MathTransform2D) {
            assertEquals("MathTransform2D.getSourceDimension()", 2, sourceDimension);
            assertEquals("MathTransform2D.getTargetDimension()", 2, targetDimension);
            final MathTransform2D transform2D = (MathTransform2D) transform;
            final Point2D.Float  source = new Point2D.Float();
            final Point2D.Double target = new Point2D.Double();
            for (int i=0; i<sourceFloats.length;) {
                source.x = sourceFloats[i];
                source.y = sourceFloats[i+1];
                assertSame("MathTransform2D.transform(Point2D,...) shall use the given target.",
                        target, transform2D.transform(source, target));
                assertNotNull("MathTransform2D.transform(Point2D,...) shall not return null.", target);
                targetDoubles[i++] = target.x;
                targetDoubles[i++] = target.y;
            }
            assertCoordinatesEqual("MathTransform2D.transform(Point2D,Point2D) error.",
                    2, expectedDoubles, 0, targetDoubles, 0, numPts, ComparisonType.DIRECT_TRANSFORM);
        }
        return expectedFloats;
    }

    /**
     * Computes the derivative at the given point, and compares the matrix values with estimations
     * computed from the corners of a cube around the given point. This method uses the <cite>finite
     * central differences</cite> approximation of derivatives in order to estimate the expected
     * result of {@link MathTransform#derivative(DirectPosition)}.
     * <p>
     * The distance between 2 points to use for computing derivatives shall be specified
     * by the {@link #derivativeDeltas} array, in units of the source CRS. If the length
     * of the {@code derivativeDeltas} array is smaller than the number of source dimensions,
     * then the last delta value is used for all additional dimensions. This allows specifying
     * a single delta value (in an array of length 1) for all dimensions.
     *
     * @param  coordinate The point where to compute the derivative, in units of the source CRS.
     * @throws TransformException If the derivative can not be computed, or a point can not be
     *         transformed.
     *
     * @since 3.1
     */
    protected void verifyDerivative(final double... coordinate) throws TransformException {
        assertTrue("isDerivativeSupported == false.", isDerivativeSupported);
        final MathTransform   transform = this.transform; // Protect from changes.
        final double[] derivativeDeltas = this.derivativeDeltas; // Protect from changes.
        assertNotNull("TransformTestCase.transform shall be assigned a value.", transform);
        assertNotNull("TransformTestCase.derivativeDeltas shall be assigned a value.", derivativeDeltas);
        assertTrue   ("TransformTestCase.derivativeDeltas shall not be empty.", derivativeDeltas.length != 0);
        assertEquals ("Coordinate dimension shall be equals to the transform source dimension.",
                transform.getSourceDimensions(), coordinate.length);

        final Matrix matrix = transform.derivative(new SimpleDirectPosition(coordinate));
        final int sourceDim = matrix.getNumCol();
        final int targetDim = matrix.getNumRow();
        assertEquals("Unexpected number of columns.", transform.getSourceDimensions(), sourceDim);
        assertEquals("Unexpected number of rows.",    transform.getTargetDimensions(), targetDim);

        final Matrix approx = new SimpleMatrix(targetDim, sourceDim, new double[sourceDim * targetDim]);
        final SimpleDirectPosition S1 = new SimpleDirectPosition(sourceDim);
        final SimpleDirectPosition S2 = new SimpleDirectPosition(sourceDim);
        final SimpleDirectPosition T1 = new SimpleDirectPosition(targetDim);
        final SimpleDirectPosition T2 = new SimpleDirectPosition(targetDim);
        for (int i=0; i<sourceDim; i++) {
            S1.setCoordinate(coordinate);
            S2.setCoordinate(coordinate);
            final double ordinate = coordinate[i];
            final double delta = derivativeDeltas[min(i, derivativeDeltas.length-1)];
            S1.setOrdinate(i, ordinate - delta/2);
            S2.setOrdinate(i, ordinate + delta/2);
            assertSame(T1, transform.transform(S1, T1));
            assertSame(T2, transform.transform(S2, T2));
            for (int j=0; j<targetDim; j++) {
                approx.setElement(j, i, (T2.getOrdinate(j) - T1.getOrdinate(j)) / delta);
            }
        }
        /*
         * Now compare the matrixes elements. If the transform implements
         * the MathTransform2D interface, check also the consistency.
         */
        assertMatrixEquals("MathTransform.derivative(DirectPosition) error.",
                approx, matrix, ComparisonType.DERIVATIVE);
        if (transform instanceof MathTransform2D) {
            assertEquals("MathTransform2D.getSourceDimensions()", 2, sourceDim);
            assertEquals("MathTransform2D.getTargetDimensions()", 2, targetDim);
            assertMatrixEquals("MathTransform2D.derivative(Point2D) error.", matrix,
                    ((MathTransform2D) transform).derivative(new Point2D.Double(coordinate[0], coordinate[1])),
                    ComparisonType.STRICT);
        }
    }

    /**
     * Verifies all supported transform operations in the given domain. First, this method creates
     * a grid of regularly spaced points along all source dimensions in the given envelope.
     * Next, if the given random number generator is non-null, then this method adds small
     * random displacements to every points and shuffle the coordinates in random order.
     * Finally this method delegates the resulting array of coordinates to the following
     * methods:
     * <p>
     * <ul>
     *   <li>{@link #verifyConsistency(float[])}</li>
     *   <li>{@link #verifyInverse(float[])}</li>
     *   <li>{@link #verifyDerivative(double[])}</li>
     * </ul>
     *
     * @param  minOrdinates The minimal ordinate values of the domain where to test the transform.
     * @param  maxOrdinates The maximal ordinate values of the domain where to test the transform.
     * @param  numOrdinates The number of points along each dimension.
     * @param  randomGenerator An optional random number generator, or {@code null} for testing
     *         using a regular grid.
     * @throws TransformException If a transform or a derivative can not be computed.
     *
     * @since 3.1
     */
    protected void verifyInDomain(final double[] minOrdinates, final double[] maxOrdinates,
            final int[] numOrdinates, final Random randomGenerator) throws TransformException
    {
        final MathTransform transform = this.transform; // Protect from changes.
        assertNotNull("TransformTestCase.transform shall be assigned a value.", transform);
        final int dimension = transform.getSourceDimensions();
        assertEquals("The minOrdinates array doesn't have the expected length.", dimension, minOrdinates.length);
        assertEquals("The maxOrdinates array doesn't have the expected length.", dimension, maxOrdinates.length);
        assertEquals("The numOrdinates array doesn't have the expected length.", dimension, numOrdinates.length);
        int numPoints = 1;
        for (int i=0; i<dimension; i++) {
            numPoints *= numOrdinates[i];
            assertTrue("Invalid numOrdinates value.", numPoints >= 0);
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
            float ordinate = (float) start;
            for (int i=dim; i<coordinates.length; i+=dimension) {
                coordinates[i] = ordinate;
                if (randomGenerator != null) {
                    coordinates[i] += (randomGenerator.nextFloat() - 0.5f) * delta;
                }
                if (++count == step) {
                    count = 0;
                    if (++ordinateIndex == n) {
                        ordinateIndex = 0;
                    }
                    ordinate = (float) (ordinateIndex*delta + start);
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
        if (isInverseTransformSupported) {
            verifyInverse(coordinates);
        }
        if (isDerivativeSupported) {
            final double[] point = new double[dimension];
            for (int i=0; i<coordinates.length; i+=dimension) {
                for (int j=0; j<dimension; j++) {
                    point[j] = coordinates[i+j];
                }
                verifyDerivative(point);
            }
        }
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
     * @param mode     Whatever the coordinates being compared are the result of a direct or
     *                 inverse transform, or whatever strict equality is requested.
     */
    protected final void assertCoordinateEquals(final String message, final float[] expected,
            final float[] actual, final int index, final ComparisonType mode)
    {
        final int dimension = expected.length;
        assertEquals(dimension, actual.length);
        assertCoordinatesEqual(message, dimension, expected, 0, actual, 0, 1, mode, index);
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
     * @param mode     Whatever the coordinates being compared are the result of a direct or
     *                 inverse transform, or whatever strict equality is requested.
     */
    protected final void assertCoordinateEquals(final String message, final float[] expected,
            final double[] actual, final int index, final ComparisonType mode)
    {
        final int dimension = expected.length;
        assertEquals(dimension, actual.length);
        assertCoordinatesEqual(message, dimension, expected, 0, actual, 0, 1, mode, index);
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
     * @param mode     Whatever the coordinates being compared are the result of a direct or
     *                 inverse transform, or whatever strict equality is requested.
     */
    protected final void assertCoordinateEquals(final String message, final double[] expected,
            final float[] actual, final int index, final ComparisonType mode)
    {
        final int dimension = expected.length;
        assertEquals(dimension, actual.length);
        assertCoordinatesEqual(message, dimension, expected, 0, actual, 0, 1, mode, index);
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
     * @param mode     Whatever the coordinates being compared are the result of a direct or
     *                 inverse transform, or whatever strict equality is requested.
     */
    protected final void assertCoordinateEquals(final String message, final double[] expected,
            final double[] actual, final int index, final ComparisonType mode)
    {
        final int dimension = expected.length;
        assertEquals(dimension, actual.length);
        assertCoordinatesEqual(message, dimension, expected, 0, actual, 0, 1, mode, index);
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
     * @param mode           Whatever the coordinates being compared are the result of a direct
     *                       or inverse transform, or whatever strict equality is requested.
     */
    protected final void assertCoordinatesEqual(
            final String  message,     final int dimension,
            final float[] expectedPts, final int expectedOffset,
            final float[] actualPts,   final int actualOffset,
            final int     numPoints,   final ComparisonType mode)
    {
        assertCoordinatesEqual(message, dimension, expectedPts, expectedOffset,
                actualPts, actualOffset, numPoints, mode, 0);
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
     * @param mode           Whatever the coordinates being compared are the result of a direct
     *                       or inverse transform, or whatever strict equality is requested.
     */
    protected final void assertCoordinatesEqual(
            final String   message,     final int dimension,
            final float[]  expectedPts, final int expectedOffset,
            final double[] actualPts,   final int actualOffset,
            final int      numPoints,   final ComparisonType mode)
    {
        assertCoordinatesEqual(message, dimension, expectedPts, expectedOffset,
                actualPts, actualOffset, numPoints, mode, 0);
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
     * @param mode           Whatever the coordinates being compared are the result of a direct
     *                       or inverse transform, or whatever strict equality is requested.
     */
    protected final void assertCoordinatesEqual(
            final String   message,     final int dimension,
            final double[] expectedPts, final int expectedOffset,
            final float [] actualPts,   final int actualOffset,
            final int      numPoints,   final ComparisonType mode)
    {
        assertCoordinatesEqual(message, dimension, expectedPts, expectedOffset,
                actualPts, actualOffset, numPoints, mode, 0);
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
     * @param mode           Whatever the coordinates being compared are the result of a direct
     *                       or inverse transform, or whatever strict equality is requested.
     */
    protected final void assertCoordinatesEqual(
            final String   message,     final int dimension,
            final double[] expectedPts, final int expectedOffset,
            final double[] actualPts,   final int actualOffset,
            final int      numPoints,   final ComparisonType mode)
    {
        assertCoordinatesEqual(message, dimension, expectedPts, expectedOffset,
                actualPts, actualOffset, numPoints, mode, 0);
    }

    /**
     * Implementation of public assertion methods with the addition of the coordinate
     * index to be reported in error message.
     *
     * @param message        The header part of the message to format in case of failure.
     * @param dimension      The dimension of each coordinate points in the arrays.
     * @param expectedPts    The {@code float[]} or {@code double[]} array of expected coordinate values.
     * @param expectedOffset Index of the first valid ordinate in the {@code expectedPts} array.
     * @param actualPts      The {@code float[]} or {@code double[]} array of coordinate values to
     *                       check against the expected ones.
     * @param actualOffset   Index of the first valid ordinate in the {@code actualPts} array.
     * @param numPoints      Number of coordinate points to compare.
     * @param mode           Whatever the coordinates being compared are the result of a direct
     *                       or inverse transform, or whatever strict equality is requested.
     * @param reportedIndex  In case of failure, index of the point (not ordinate) to report in
     *                       the error message.
     */
    private void assertCoordinatesEqual(
            final String   message,     final int dimension,
            final Object   expectedPts, int expectedOffset,
            final Object   actualPts,   int actualOffset,
            final int      numPoints,   final ComparisonType mode, final int reportedIndex)
    {
        final boolean useDouble = isDoubleArray(expectedPts) && isDoubleArray(actualPts);
        final SimpleDirectPosition actual   = new SimpleDirectPosition(dimension);
        final SimpleDirectPosition expected = new SimpleDirectPosition(dimension);
        for (int i=0; i<numPoints; i++) {
            actual.setCoordinate(actualPts, actualOffset, useDouble);
            expected.setCoordinate(expectedPts, expectedOffset, useDouble);
            final int mismatch = indexOfMismatch(expected, actual, mode);
            if (mismatch >= 0) {
                /*
                 * Found a mismatched ordinate value. We are going to thrown an exception.
                 * First, compute now (before we increment the offsets) the difference between
                 * the expected ordinate value and the actual one.
                 */
                final double diff  = abs(Array.getDouble(expectedPts, expectedOffset + mismatch)
                                       - Array.getDouble(actualPts,   actualOffset   + mismatch));
                final Number delta = useDouble ? Double.valueOf(diff) : Float.valueOf((float) diff);
                /*
                 * Format an error message with the coordinate values followed by the
                 * difference with the expected value.
                 */
                final String lineSeparator = System.getProperty("line.separator", "\n");
                final StringBuilder buffer = new StringBuilder(message).append(lineSeparator)
                        .append("DirectPosition").append(dimension).append("D[")
                        .append(reportedIndex + i).append("]: Expected (");
                for (int j=0; j<dimension; j++) {
                    if (j != 0) buffer.append(", ");
                    buffer.append(Array.get(expectedPts, expectedOffset++));
                }
                buffer.append(") but got (");
                for (int j=0; j<dimension; j++) {
                    if (j != 0) buffer.append(", ");
                    buffer.append(Array.get(actualPts, actualOffset++));
                }
                throw new TransformFailure(buffer.append(").").append(lineSeparator)
                        .append("The delta at ordinate ").append(mismatch).append(" is ")
                        .append(delta).toString());
            }
            expectedOffset += dimension;
            actualOffset   += dimension;
        }
    }

    /**
     * @deprecated The {@code boolean} argument has been replaced by a {@link ComparisonType} argument.
     *
     * @param message  The message to print in case of failure.
     * @param expected The array of expected ordinate values.
     * @param actual   The array of ordinate values to check against the expected ones.
     * @param index    The index of the coordinate point being compared, for message formatting.
     * @param strict   {@code true} for ignoring the {@linkplain #tolerance(double) tolerance}
     *                 threshold. In such case, ordinate values are checked for strict equality.
     */
    @Deprecated
    protected final void assertCoordinateEquals(final String message, final float[] expected,
            final float[] actual, final int index, final boolean strict)
    {
        assertCoordinateEquals(message, expected, actual, index,
                strict ? ComparisonType.STRICT : ComparisonType.DIRECT_TRANSFORM);
    }

    /**
     * @deprecated The {@code boolean} argument has been replaced by a {@link ComparisonType} argument.
     *
     * @param message  The message to print in case of failure.
     * @param expected The array of expected ordinate values.
     * @param actual   The array of ordinate values to check against the expected ones.
     * @param index    The index of the coordinate point being compared, for message formatting.
     * @param strict   {@code true} for ignoring the {@linkplain #tolerance(double) tolerance}
     *                 threshold. In such case, ordinate values are checked for strict equality.
     */
    @Deprecated
    protected final void assertCoordinateEquals(final String message, final float[] expected,
            final double[] actual, final int index, final boolean strict)
    {
        assertCoordinateEquals(message, expected, actual, index,
                strict ? ComparisonType.STRICT : ComparisonType.DIRECT_TRANSFORM);
    }

    /**
     * @deprecated The {@code boolean} argument has been replaced by a {@link ComparisonType} argument.
     *
     * @param message  The message to print in case of failure.
     * @param expected The array of expected ordinate values.
     * @param actual   The array of ordinate values to check against the expected ones.
     * @param index    The index of the coordinate point being compared, for message formatting.
     * @param strict   {@code true} for ignoring the {@linkplain #tolerance(double) tolerance}
     *                 threshold. In such case, ordinate values are checked for strict equality.
     */
    @Deprecated
    protected final void assertCoordinateEquals(final String message, final double[] expected,
            final float[] actual, final int index, final boolean strict)
    {
        assertCoordinateEquals(message, expected, actual, index,
                strict ? ComparisonType.STRICT : ComparisonType.DIRECT_TRANSFORM);
    }

    /**
     * @deprecated The {@code boolean} argument has been replaced by a {@link ComparisonType} argument.
     *
     * @param message  The message to print in case of failure.
     * @param expected The array of expected ordinate values.
     * @param actual   The array of ordinate values to check against the expected ones.
     * @param index    The index of the coordinate point being compared, for message formatting.
     * @param strict   {@code true} for ignoring the {@linkplain #tolerance(double) tolerance}
     *                 threshold. In such case, ordinate values are checked for strict equality.
     */
    @Deprecated
    protected final void assertCoordinateEquals(final String message, final double[] expected,
            final double[] actual, final int index, final boolean strict)
    {
        assertCoordinateEquals(message, expected, actual, index,
                strict ? ComparisonType.STRICT : ComparisonType.DIRECT_TRANSFORM);
    }

    /**
     * @deprecated The {@code boolean} argument has been replaced by a {@link ComparisonType} argument.
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
    @Deprecated
    protected final void assertCoordinatesEqual(
            final String  message,     final int dimension,
            final float[] expectedPts, final int expectedOffset,
            final float[] actualPts,   final int actualOffset,
            final int     numPoints,   final boolean strict)
    {
        assertCoordinatesEqual(message, dimension,
                expectedPts, expectedOffset, actualPts, actualOffset, numPoints,
                strict ? ComparisonType.STRICT : ComparisonType.DIRECT_TRANSFORM);
    }

    /**
     * @deprecated The {@code boolean} argument has been replaced by a {@link ComparisonType} argument.
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
    @Deprecated
    protected final void assertCoordinatesEqual(
            final String   message,     final int dimension,
            final float[]  expectedPts, final int expectedOffset,
            final double[] actualPts,   final int actualOffset,
            final int numPoints,        final boolean strict)
    {
        assertCoordinatesEqual(message, dimension,
                expectedPts, expectedOffset, actualPts, actualOffset, numPoints,
                strict ? ComparisonType.STRICT : ComparisonType.DIRECT_TRANSFORM);
    }

    /**
     * @deprecated The {@code boolean} argument has been replaced by a {@link ComparisonType} argument.
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
    @Deprecated
    protected final void assertCoordinatesEqual(
            final String   message,     final int dimension,
            final double[] expectedPts, final int expectedOffset,
            final float [] actualPts,   final int actualOffset,
            final int      numPoints,   final boolean strict)
    {
        assertCoordinatesEqual(message, dimension,
                expectedPts, expectedOffset, actualPts, actualOffset, numPoints,
                strict ? ComparisonType.STRICT : ComparisonType.DIRECT_TRANSFORM);
    }

    /**
     * @deprecated The {@code boolean} argument has been replaced by a {@link ComparisonType} argument.
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
    @Deprecated
    protected final void assertCoordinatesEqual(
            final String   message,     final int dimension,
            final double[] expectedPts, final int expectedOffset,
            final double[] actualPts,   final int actualOffset,
            final int      numPoints,   final boolean strict)
    {
        assertCoordinatesEqual(message, dimension,
                expectedPts, expectedOffset, actualPts, actualOffset, numPoints,
                strict ? ComparisonType.STRICT : ComparisonType.DIRECT_TRANSFORM);
    }

    /**
     * Asserts that a matrix of derivatives is equals to the expected ones within a positive delta.
     * If the comparison fails, the given message is completed with the expected and actual
     * matrixes.
     *
     * @param message  The message to print in case of failure.
     * @param expected The expected matrix.
     * @param actual   The actual matrix.
     * @param mode     Whatever the comparison should be {@linkplain ComparisonType#STRICT strict} or
     *                 accept the {@linkplain ComparisonType#DERIVATIVE derivative} tolerance threshold.
     *
     * @since 3.1
     */
    protected final void assertMatrixEquals(final String message,
            final Matrix expected, final Matrix actual, final ComparisonType mode)
    {
        final int numRow = expected.getNumRow();
        final int numCol = expected.getNumCol();
        assertEquals("Wrong number of rows.",    numRow, actual.getNumRow());
        assertEquals("Wrong number of columns.", numCol, actual.getNumCol());
        for (int i=0; i<numCol; i++) {
            for (int j=0; j<numRow; j++) {
                final double e = expected.getElement(j, i);
                final double a = actual  .getElement(j, i);
                final double d = abs(e - a);
                /*
                 * Now compare the matrixes elements. Note that we still invoke the tolerance(double)
                 * method even if the given arguments are not ordinate values. We do that because the
                 * matrix can be interpreted as the displacement in target CRS space when the ordinate
                 * values are increased by 1 in the source CRS space, so it still related to ordinates.
                 *
                 * The argument value given to the tolerance method is 'actual' rather than 'expected'
                 * because the actual value is probably more accurate than the one approximated from
                 * finite differences.
                 */
                if (!(d <= tolerance(a, j, mode)) && Double.doubleToLongBits(a) != Double.doubleToLongBits(e)) {
                    final StringBuilder buffer = new StringBuilder(512);
                    final String lineSeparator = System.getProperty("line.separator", "\n");
                    buffer.append(message).append(lineSeparator).append("Matrix(").append(j).append(',').append(i)
                            .append("): expected ").append(e).append(" but got ").append(a)
                            .append(" (a difference of ").append(d).append(')').append(lineSeparator)
                            .append("Expected matrix (may be approximative):").append(lineSeparator);
                    SimpleMatrix.toString(expected, buffer, lineSeparator);
                    buffer.append(lineSeparator).append("Actual matrix:").append(lineSeparator);
                    SimpleMatrix.toString(actual, buffer, lineSeparator);
                    throw new DerivativeFailure(buffer.toString());
                }
            }
        }
    }

    /**
     * Returns {@code true} if the given array is an array of {@code double} primitive types.
     */
    private static boolean isDoubleArray(final Object array) {
        return array.getClass().getComponentType() == Double.TYPE;
    }

    /**
     * Returns the index within the given position of an ordinate value which is not approximatively
     * equals to the expected value. If all ordinate values are approximatively equal to the expected
     * values, then this method returns -1.
     * <p>
     * The default implementation computes the {@linkplain StrictMath#abs(double) absolute}
     * differences between the expected and actual ordinate values, and return the index of the first
     * dimension having a difference greater than the {@linkplain #tolerance(double, int, ComparisonType)
     * tolerance} threshold. The comparison implemented in this method is robust to
     * {@linkplain Double#NaN NaN} and infinite values.
     * <p>
     * This method can be overridden by subclasses wanting to handle some dimensions in a special
     * way. Some typical special cases are:
     * <p>
     * <ul>
     *   <li>Allowing a greater tolerance threshold along the vertical axis compared to the
     *       horizontal axis.</li>
     *   <li>In a geographic CRS, ignoring offsets of 360° in longitude.</li>
     *   <li>In a geographic CRS, ignoring the longitude value if the latitude is at a pole.</li>
     * </ul>
     * <p>
     * This GeoAPI method does not implement any of the above special case.
     * It is up to subclasses to implement their own special cases if they need to.
     *
     * @param  expected The expected position.
     * @param  actual   The actual position.
     * @param  mode     Whatever the coordinates being compared are the result of a direct
     *                  or inverse transform, or whatever strict equality is requested.
     * @return Dimension where a mismatched value has been found, or -1 is all ordinate values
     *         are approximatively equal.
     *
     * @since 3.1
     *
     * @see #tolerance(double, int, ComparisonType)
     */
    protected int indexOfMismatch(final DirectPosition expected, final DirectPosition actual, final ComparisonType mode) {
        final int dimension = expected.getDimension();
        assertEquals("The position does not have the expected number of dimensions.",
                dimension, actual.getDimension());
        for (int i=0; i<dimension; i++) {
            final double a = actual.getOrdinate(i);
            final double e = expected.getOrdinate(i);
            /*
             * This method uses !(a <= b) expressions instead than (a > b) for catching NaN.
             * The next condition working on bit patterns is for NaN and Infinity values.
             */
            if (!(abs(a - e) <= tolerance(e, i, mode)) && Double.doubleToLongBits(a) != Double.doubleToLongBits(e)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns the tolerance threshold for comparing the given ordinate value. The default
     * implementation returns the {@link #tolerance} value directly (except for strict mode),
     * thus implementing an absolute tolerance threshold. If a subclass needs a relative
     * tolerance threshold instead, it can override this method as below:
     *
     * <blockquote><code>
     * return super.tolerance(ordinate, dimension, mode) * Math.abs(ordinate);
     * </code></blockquote>
     *
     * This method is provided as an alternative easier to override than the
     * {@link #indexOfMismatch(DirectPosition, DirectPosition, ComparisonType) indexOfMismatch}
     * method. However this alternative is suitable only for tolerance thresholds that do not
     * depend on other dimensions. For example this method does not allow to ignore longitude
     * when the latitude is ±90°.
     *
     * @param  ordinate  The ordinate value being compared.
     * @param  dimension The dimension of the ordinate being compared. The first dimension is 0.
     * @param  mode      Whatever the coordinates being compared are the result of a direct
     *                   or inverse transform, or whatever strict equality is requested.
     * @return The absolute tolerance threshold to use for comparing the given ordinate.
     *
     * @see #indexOfMismatch(DirectPosition, DirectPosition, ComparisonType)
     *
     * @since 3.1
     */
    protected double tolerance(final double ordinate, final int dimension, final ComparisonType mode) {
        return (mode != ComparisonType.STRICT) ? tolerance : 0;
    }


    /**
     * The kind of comparison being performed. This information can be used by subclasses of
     * {@link TransformTestCase} wanting finer grain tolerance threshold than the one provided
     * by the default implementation. For example when testing the conversions from a geographic
     * CRS (axes in degrees) to a projected CRS (axes in metres), a precision of 10 centimetres
     * corresponds to a {@linkplain TransformTestCase#tolerance tolerance} value of 0.1 during
     * direct conversions, but approximatively 1E-6 during inverse conversions.
     *
     * @author  Martin Desruisseaux (Geomatys)
     * @version 3.1
     * @since   3.1
     *
     * @see TransformTestCase#tolerance(double, int, ComparisonType)
     */
    protected static enum ComparisonType {
        /**
         * Ignores the {@linkplain TransformTestCase#tolerance tolerance} threshold.
         * Ordinate values are checked for strict equality.
         */
        STRICT,

        /**
         * The ordinate values to compare are the result of a direct calculation
         * performed by the {@linkplain TransformTestCase#transform transform tested}.
         */
        DIRECT_TRANSFORM,

        /**
         * The ordinate values to compare are the result of the
         * {@linkplain MathTransform#inverse() inverse transform}.
         */
        INVERSE_TRANSFORM,

        /**
         * The values to compare are {@linkplain MathTransform#derivative(DirectPosition) derivatives}.
         * In case of doubt, implementations can use for the derivative values the same threshold than
         * for the {@link #DIRECT_TRANSFORM} case.
         */
        DERIVATIVE
    }
}
