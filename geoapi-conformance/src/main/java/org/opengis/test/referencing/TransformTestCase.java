/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2008-2019 Open Geospatial Consortium, Inc.
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
import java.awt.geom.Point2D;

import org.opengis.util.Factory;
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
import static org.opengis.test.Assert.*;


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
 *   <li>Unless otherwise indicated, every {@code verify} methods are independent. For example invoking
 *       {@link #verifyConsistency(float[])} does not imply a call to {@link #verifyInverse(float[])}
 *       or {@link #verifyDerivative(double[])}. The later methods must be invoked explicitly if wanted.</li>
 * </ul>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   2.2
 */
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
     * {@code true} if the destination array can be the same than the source array,
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
     * <blockquote><pre>derivativeDeltas = new double[] {100.0 / (60 * 1852)};      // Approximately 100 metres.</pre></blockquote>
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
     * coordinate points. {@link ToleranceModifier} instance assigned to this field (if any)
     * are {@linkplain #transform}-dependent. The modifications applied by a particular
     * {@code ToleranceModifier} instance to the tolerance thresholds is position-dependent.
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
     * <p>Note that the above checks will not detect the case where the user invoke
     * {@link org.opengis.test.TestSuite#clear()}. We presume that typical users
     * will not invoke that method in the middle of a test (it is okay if that
     * method is invoked between two tests however).</p>
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
     *
     * @since 3.1
     */
    private boolean isToleranceRelaxed;

    /**
     * Creates a new test without factory. This constructor is provided for subclasses
     * that instantiate their {@link MathTransform} directly, without using any factory.
     */
    protected TransformTestCase() {
        setEnabledFlags(getEnabledFlags(getEnabledKeys(0)));
    }

    /**
     * Creates a new test without factory and with the given {@code isFooSupported} flags.
     * The given array must be the result of a call to {@link #getEnabledKeys(int)}.
     */
    TransformTestCase(final boolean[] isEnabled) {
        setEnabledFlags(isEnabled);
    }

    /**
     * Creates a test case initialized to default values. The {@linkplain #transform}
     * is initially null, the {@linkplain #tolerance} threshold is initially zero and
     * all <code>is&lt;</code><var>Operation</var><code>&gt;Supported</code> are set
     * to {@code true} unless at least one {@link org.opengis.test.ImplementationDetails}
     * object disabled some tests.
     *
     * @param factories  the factories to be used by the test. Those factories will be given to
     *        {@link org.opengis.test.ImplementationDetails#configuration(Factory[])} in order
     *        to decide which tests should be enabled.
     */
    @SuppressWarnings("unchecked")
    protected TransformTestCase(final Factory... factories) {
        super(factories);
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
     * Returns the keys to gives to the {@link #setEnabledFlags(boolean[])} method. The
     * elements in the returned array <strong>must</strong> be in the order expected by
     * the {@link #setEnabledFlags(boolean[])} method for setting the fields.
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
     *   <li>The {@code "isToleranceRelaxed"} key associated to the value {@link Boolean#TRUE}
     *       if the {@link ToleranceModifiers#getImplementationSpecific(MathTransform)} method
     *       found at least one {@link ToleranceModifier} on the classpath, or
     *       {@link Boolean#FALSE} otherwise.</li>
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
     * Returns the tolerance threshold for comparing the given coordinate value. The default
     * implementation returns the {@link #tolerance} value directly, thus implementing an
     * absolute tolerance threshold. If a subclass needs a relative tolerance threshold
     * instead, it can override this method as below:
     *
     * <blockquote><code>
     * return tolerance * Math.abs(coordinate);
     * </code></blockquote>
     *
     * @param  coordinate  the coordinate value being compared.
     * @return the absolute tolerance threshold to use for comparing the given coordinate.
     *
     * @deprecated Replaced by {@link #toleranceModifier}.
     */
    @Deprecated
    protected double tolerance(final double coordinate) {
        return tolerance;
    }

    /**
     * Ensures that all <code>is&lt;</code><var>Operation</var><code>&gt;Supported</code> fields
     * are set to {@code true}. This method can be invoked before testing a math transform which
     * is expected to be fully implemented.
     *
     * @deprecated No replacement.
     */
    @Deprecated
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
        final MathTransform transform = this.transform;             // Protect from changes.
        assertNotNull("TransformTestCase.transform shall be assigned a value.", transform);
        final int sourceDimension = transform.getSourceDimensions();
        final int targetDimension = transform.getTargetDimensions();
        assertStrictlyPositive("Source dimension shall be positive.", sourceDimension);
        assertStrictlyPositive("Target dimension shall be positive.", targetDimension);
        final MathTransform inverse;
        if (isInverseTransformSupported) {
            final Configuration.Key<Boolean> oldTip = configurationTip;
            configurationTip = Configuration.Key.isInverseTransformSupported;
            inverse = transform.inverse();
            assertNotNull("MathTransform.inverse() shall not return null.", inverse);
            assertEquals("Inconsistent source dimension of the inverse transform.",
                    targetDimension, inverse.getSourceDimensions());
            assertEquals("Inconsistent target dimension of the inverse transform.",
                    sourceDimension, inverse.getTargetDimensions());
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
            System.arraycopy(coordinates, sourceOffset, source.coordinates, 0, sourceDimension);
            assertSame("MathTransform.transform(DirectPosition, …) shall use the given target.",
                    target, transform.transform(source, target));
            assertCoordinatesEqual("Unexpected transform result.", targetDimension,
                    expected, targetOffset, target.coordinates, 0, 1, CalculationType.DIRECT_TRANSFORM, i);
            assertCoordinatesEqual("Source coordinate has been modified.", sourceDimension,
                    coordinates, sourceOffset, source.coordinates, 0, 1, CalculationType.IDENTITY, i);
            /*
             * Tests the inverse transform, if supported. We could use the 'target' point directly,
             * which contain the result of the transform performed by the application under testing.
             * Nevertheless we overwrite the 'target' point with the 'expected' coordinate provided
             * in argument to this method. It is not necessarily more accurate since the expected
             * coordinates are often provided with limited precision. However this allow more
             * consistent behavior.
             */
            if (inverse != null) {
                System.arraycopy(expected, targetOffset, target.coordinates, 0, targetDimension);
                assertSame("MathTransform.transform(DirectPosition, …) shall use the given target.",
                        back, inverse.transform(target, back));
                assertCoordinateEquals("Unexpected result of inverse transform.",
                        source.coordinates, back.coordinates, i, CalculationType.INVERSE_TRANSFORM);
                assertCoordinatesEqual("Source coordinate has been modified.", targetDimension,
                        expected, targetOffset, target.coordinates, 0, 1, CalculationType.IDENTITY, i);
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
     * @throws TransformException if at least one coordinate can't be transformed.
     */
    protected void verifyInverse(final double... coordinates) throws TransformException {
        assertTrue("isInverseTransformSupported == false.", isInverseTransformSupported);
        /*
         * Checks the state of this TransformTestCase object, including a shallow inspection of
         * the MathTransform. We check only the parts that are significant to this test method.
         * Full MathTransform validation is not the job of this method.
         */
        final MathTransform transform = this.transform;                 // Protect from changes.
        assertNotNull("TransformTestCase.transform shall be assigned a value.", transform);
        final int sourceDimension = transform.getSourceDimensions();
        final int targetDimension = transform.getTargetDimensions();
        assertStrictlyPositive("Source dimension shall be positive.", sourceDimension);
        assertStrictlyPositive("Target dimension shall be positive.", targetDimension);
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
        final SimpleDirectPosition back   = new SimpleDirectPosition(sourceDimension);
        DirectPosition target = null;
        for (int i=0; i<numPts; i++) {
            final int offset = i*sourceDimension;
            System.arraycopy(coordinates, offset, source.coordinates, 0, sourceDimension);
            target = transform.transform(source, target);
            assertNotNull("MathTransform.transform(DirectPosition, …) shall not return null.", target);
            assertEquals("Transformed point has wrong dimension.",
                    targetDimension, target.getDimension());
            assertSame("MathTransform.transform(DirectPosition, …) shall use the given target.",
                    back, inverse.transform(target, back));
            assertCoordinateEquals("Unexpected result of inverse transform.",
                    source.coordinates, back.coordinates, i, CalculationType.INVERSE_TRANSFORM);
            assertCoordinatesEqual("Source coordinate has been modified.", sourceDimension,
                    coordinates, offset, source.coordinates, 0, 1, CalculationType.IDENTITY, i);
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
                coordinates, 0, sourceDoubles, 0, coordinates.length / dimension, CalculationType.IDENTITY);
    }

    /**
     * Transforms coordinates using various versions of {@code MathTransform.transform(…)}
     * and verifies that they produce the same numerical values. The values calculated by
     * {@link MathTransform#transform(DirectPosition,DirectPosition)} are used as the reference.
     * Other transform methods (operating on arrays) will be compared against that reference,
     * unless their checks were disabled (see class javadoc for details).
     *
     * <p>This method expects an array of {@code float} values instead than {@code double}
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
     * @throws TransformException if at least one coordinate can't be transformed.
     *
     * @see #isDoubleToDoubleSupported
     * @see #isFloatToFloatSupported
     * @see #isDoubleToFloatSupported
     * @see #isFloatToDoubleSupported
     * @see #isOverlappingArraySupported
     */
    protected float[] verifyConsistency(final float... sourceFloats) throws TransformException {
        final MathTransform transform = this.transform;                 // Protect from changes.
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
         * Copies the source coordinates (to be used later) and performs the transformations using
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
                System.arraycopy(sourceDoubles, i, sourcePosition.coordinates, 0, sourceDimension);
                targetPosition = transform.transform(sourcePosition, targetPosition);
                assertNotNull("MathTransform.transform(DirectPosition, …) shall not return null.", targetPosition);
                assertNotSame("MathTransform.transform(DirectPosition, …) shall not overwrite " +
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
        final Configuration.Key<Boolean> oldTip = configurationTip;
        if (isDoubleToDoubleSupported) {
            configurationTip = Configuration.Key.isDoubleToDoubleSupported;
            Arrays.fill(targetDoubles, Double.NaN);
            transform.transform(sourceDoubles, 0, targetDoubles, 0, numPts);
            assertCoordinatesEqual("MathTransform.transform(double[],0,double[],0,n) modified a source coordinate.",
                    sourceDimension, sourceFloats, 0, sourceDoubles, 0, numPts, CalculationType.IDENTITY);
            assertCoordinatesEqual("MathTransform.transform(double[],0,double[],0,n) error.",
                    targetDimension, expectedDoubles, 0, targetDoubles, 0, numPts, CalculationType.DIRECT_TRANSFORM);
        }
        if (isFloatToFloatSupported) {
            configurationTip = Configuration.Key.isFloatToFloatSupported;
            Arrays.fill(targetFloats, Float.NaN);
            transform.transform(sourceFloats, 0, targetFloats, 0, numPts);
            assertCoordinatesEqual("MathTransform.transform(float[],0,float[],0,n) modified a source coordinate.",
                    sourceDimension, sourceDoubles, 0, sourceFloats, 0, numPts, CalculationType.IDENTITY);
            assertCoordinatesEqual("MathTransform.transform(float[],0,float[],0,n) error.",
                    targetDimension, expectedFloats, 0, targetFloats, 0, numPts, CalculationType.DIRECT_TRANSFORM);
        }
        if (isDoubleToFloatSupported) {
            configurationTip = Configuration.Key.isDoubleToFloatSupported;
            Arrays.fill(targetFloats, Float.NaN);
            transform.transform(sourceDoubles, 0, targetFloats, 0, numPts);
            assertCoordinatesEqual("MathTransform.transform(double[],0,float[],0,n) modified a source coordinate.",
                    sourceDimension, sourceFloats, 0, sourceDoubles, 0, numPts, CalculationType.IDENTITY);
            assertCoordinatesEqual("MathTransform.transform(double[],0,float[],0,n) error.",
                    targetDimension, expectedFloats, 0, targetFloats, 0, numPts, CalculationType.DIRECT_TRANSFORM);
        }
        if (isFloatToDoubleSupported) {
            configurationTip = Configuration.Key.isFloatToDoubleSupported;
            Arrays.fill(targetDoubles, Double.NaN);
            transform.transform(sourceFloats, 0, targetDoubles, 0, numPts);
            assertCoordinatesEqual("MathTransform.transform(float[],0,double[],0,n) modified a source coordinate.",
                    sourceDimension, sourceDoubles, 0, sourceFloats, 0, numPts, CalculationType.IDENTITY);
            assertCoordinatesEqual("MathTransform.transform(float[],0,double[],0,n) error.",
                    targetDimension, expectedDoubles, 0, targetDoubles, 0, numPts, CalculationType.DIRECT_TRANSFORM);
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
                    assertCoordinatesEqual("MathTransform.transform(float[],0,float[],0,n) error.",
                            targetDimension, expectedFloats, 0, targetFloats, targetOffset, numPts, CalculationType.DIRECT_TRANSFORM);
                    assertCoordinatesEqual("MathTransform.transform(double[],0,double[],0,n) error.",
                            targetDimension, expectedFloats, 0, targetDoubles, targetOffset, numPts, CalculationType.DIRECT_TRANSFORM);
                }
            }
        }
        configurationTip = oldTip;
        /*
         * Tests MathTransform1D methods.
         */
        if (transform instanceof MathTransform1D) {
            assertEquals("MathTransform1D.getSourceDimension()", 1, sourceDimension);
            assertEquals("MathTransform1D.getTargetDimension()", 1, targetDimension);
            final MathTransform1D transform1D = (MathTransform1D) transform;
            for (int i=0; i<sourceFloats.length; i++) {
                targetDoubles[i] = transform1D.transform(sourceFloats[i]);
            }
            assertCoordinatesEqual("MathTransform1D.transform(double) error.",
                    1, expectedDoubles, 0, targetDoubles, 0, numPts, CalculationType.DIRECT_TRANSFORM);
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
                assertSame("MathTransform2D.transform(Point2D, …) shall use the given target.",
                        target, transform2D.transform(source, target));
                assertNotNull("MathTransform2D.transform(Point2D, …) shall not return null.", target);
                targetDoubles[i++] = target.x;
                targetDoubles[i++] = target.y;
            }
            assertCoordinatesEqual("MathTransform2D.transform(Point2D,Point2D) error.",
                    2, expectedDoubles, 0, targetDoubles, 0, numPts, CalculationType.DIRECT_TRANSFORM);
        }
        return expectedFloats;
    }

    /**
     * Computes the {@linkplain MathTransform#derivative(DirectPosition) derivative at the given point}
     * and compares the result with the <a href="http://en.wikipedia.org/wiki/Finite_difference">finite
     * differences</a> approximation.
     *
     * <p>All the three common forms of finite differences (<cite>forward difference</cite>,
     * <cite>backward difference</cite> and <cite>central difference</cite>) are computed.
     * If the finite difference method was a "perfect" approximation, all those three forms
     * would produce identical results. In practice the results will differ, especially in
     * areas where the derivative function varies fast. The difference between the results
     * will be used as an estimation of the approximation accuracy.</p>
     *
     * <p>The distance between the two points used by the <cite>central difference</cite>
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
     *   <li>{@code tolmat} - a <cite>tolerance matrix</cite> containing, for each matrix element,
     *       the largest difference found between the three approximation methods. The values in
     *       this matrix will not be lower than the {@linkplain #toleranceModifier modified}
     *       {@linkplain #tolerance} threshold.</li>
     * </ul>
     *
     * Those information are then passed to the {@link #assertMatrixEquals(String, Matrix, Matrix,
     * Matrix)} method. Implementers can override the later method, for example in order to overwrite
     * the tolerance values.
     *
     * @param  coordinates  the point where to compute the derivative, in units of the source CRS.
     * @throws TransformException if the derivative can not be computed, or a point can not be transformed.
     *
     * @see MathTransform#derivative(DirectPosition)
     * @see #assertMatrixEquals(String, Matrix, Matrix, Matrix)
     *
     * @since 3.1
     */
    protected void verifyDerivative(final double... coordinates) throws TransformException {
        assertTrue("isDerivativeSupported == false.", isDerivativeSupported);
        final MathTransform   transform = this.transform;                               // Protect from changes.
        final double[] derivativeDeltas = this.derivativeDeltas;                        // Protect from changes.
        assertNotNull("TransformTestCase.transform shall be assigned a value.", transform);
        assertNotNull("TransformTestCase.derivativeDeltas shall be assigned a value.", derivativeDeltas);
        assertTrue   ("TransformTestCase.derivativeDeltas shall not be empty.", derivativeDeltas.length != 0);
        assertEquals ("Coordinate dimension shall be equal to the transform source dimension.",
                transform.getSourceDimensions(), coordinates.length);
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
        assertNotNull(message, matrix);
        assertEquals("Unexpected number of columns.", sourceDim, matrix.getNumCol());
        assertEquals("Unexpected number of rows.",    targetDim, matrix.getNumRow());
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
        assertMatrixEquals(message, approx, matrix, tolmat);
        if (transform instanceof MathTransform1D) {
            assertEquals("MathTransform1D.getSourceDimensions()", 1, sourceDim);
            assertEquals("MathTransform1D.getTargetDimensions()", 1, targetDim);
            assertMatrixEquals("MathTransform1D.derivative(double) error.", matrix,
                    new SimpleMatrix(1, 1, ((MathTransform1D) transform).derivative(coordinates[0])), tolmat);
        }
        if (transform instanceof MathTransform2D) {
            assertEquals("MathTransform2D.getSourceDimensions()", 2, sourceDim);
            assertEquals("MathTransform2D.getTargetDimensions()", 2, targetDim);
            assertMatrixEquals("MathTransform2D.derivative(Point2D) error.", matrix,
                    ((MathTransform2D) transform).derivative(new Point2D.Double(coordinates[0], coordinates[1])), tolmat);
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
     * @throws TransformException if a transform or a derivative can not be computed.
     *
     * @since 3.1
     */
    protected float[] verifyInDomain(final double[] minOrdinates, final double[] maxOrdinates,
            final int[] numOrdinates, final Random randomGenerator) throws TransformException
    {
        final MathTransform transform = this.transform;             // Protect from changes.
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
            float coordinate = (float) start;
            for (int i=dim; i<coordinates.length; i+=dimension) {
                coordinates[i] = coordinate;
                if (randomGenerator != null) {
                    coordinates[i] += (randomGenerator.nextFloat() - 0.5f) * delta;
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
     * @param message   the message to print in case of failure.
     * @param expected  the array of expected coordinate values.
     * @param actual    the array of coordinate values to check against the expected ones.
     * @param index     the index of the coordinate point being compared, for message formatting.
     * @param mode      indicates if the coordinates being compared are the result of a direct or
     *                  inverse transform, or if strict equality is requested.
     * @throws TransformFailure if at least one coordinate value is not equal to the expected value.
     */
    protected final void assertCoordinateEquals(final String message, final float[] expected,
            final float[] actual, final int index, final CalculationType mode) throws TransformFailure
    {
        final int dimension = expected.length;
        assertEquals("Coordinate array lengths differ.", dimension, actual.length);
        assertCoordinatesEqual(message, dimension, expected, 0, actual, 0, 1, mode, index);
    }

    /**
     * Asserts that a single coordinate is equal to the expected one within a positive delta.
     * If the comparison fails, the given message is completed with the expected and actual
     * values, and the index of the coordinate where the failure was found.
     *
     * @param message   the message to print in case of failure.
     * @param expected  the array of expected coordinate values.
     * @param actual    the array of coordinate values to check against the expected ones.
     * @param index     the index of the coordinate point being compared, for message formatting.
     * @param mode      indicates if the coordinates being compared are the result of a direct or
     *                  inverse transform, or if strict equality is requested.
     * @throws TransformFailure if at least one coordinate value is not equal to the expected value.
     */
    protected final void assertCoordinateEquals(final String message, final float[] expected,
            final double[] actual, final int index, final CalculationType mode) throws TransformFailure
    {
        final int dimension = expected.length;
        assertEquals("Coordinate array lengths differ.", dimension, actual.length);
        assertCoordinatesEqual(message, dimension, expected, 0, actual, 0, 1, mode, index);
    }

    /**
     * Asserts that a single coordinate is equal to the expected one within a positive delta.
     * If the comparison fails, the given message is completed with the expected and actual
     * values, and the index of the coordinate where the failure was found.
     *
     * @param message   the message to print in case of failure.
     * @param expected  the array of expected coordinate values.
     * @param actual    the array of coordinate values to check against the expected ones.
     * @param index     the index of the coordinate point being compared, for message formatting.
     * @param mode      indicates if the coordinates being compared are the result of a direct or
     *                  inverse transform, or if strict equality is requested.
     * @throws TransformFailure if at least one coordinate value is not equal to the expected value.
     */
    protected final void assertCoordinateEquals(final String message, final double[] expected,
            final float[] actual, final int index, final CalculationType mode) throws TransformFailure
    {
        final int dimension = expected.length;
        assertEquals("Coordinate array lengths differ.", dimension, actual.length);
        assertCoordinatesEqual(message, dimension, expected, 0, actual, 0, 1, mode, index);
    }

    /**
     * Asserts that a single coordinate is equal to the expected one within a positive delta.
     * If the comparison fails, the given message is completed with the expected and actual
     * values, and the index of the coordinate where the failure was found.
     *
     * @param message   the message to print in case of failure.
     * @param expected  the array of expected coordinate values.
     * @param actual    the array of coordinate values to check against the expected ones.
     * @param index     the index of the coordinate point being compared, for message formatting.
     * @param mode      indicates if the coordinates being compared are the result of a direct or
     *                  inverse transform, or if strict equality is requested.
     * @throws TransformFailure if at least one coordinate value is not equal to the expected value.
     */
    protected final void assertCoordinateEquals(final String message, final double[] expected,
            final double[] actual, final int index, final CalculationType mode) throws TransformFailure
    {
        final int dimension = expected.length;
        assertEquals("Coordinate array lengths differ.", dimension, actual.length);
        assertCoordinatesEqual(message, dimension, expected, 0, actual, 0, 1, mode, index);
    }

    /**
     * Asserts that coordinate values are equal to the expected ones within a positive delta.
     * If the comparison fails, the given message is completed with the expected and actual
     * values, and the index of the coordinate where the failure was found.
     *
     * @param message         the message to print in case of failure.
     * @param dimension       the dimension of each coordinate points in the arrays.
     * @param expectedPts     the array of expected coordinate values.
     * @param expectedOffset  index of the first valid coordinate in the {@code expectedPts} array.
     * @param actualPts       the array of coordinate values to check against the expected ones.
     * @param actualOffset    index of the first valid coordinate in the {@code actualPts} array.
     * @param numPoints       number of coordinate points to compare.
     * @param mode            indicates if the coordinates being compared are the result of a direct
     *                        or inverse transform, or if strict equality is requested.
     * @throws TransformFailure if at least one coordinate value is not equal to the expected value.
     */
    protected final void assertCoordinatesEqual(
            final String  message,     final int dimension,
            final float[] expectedPts, final int expectedOffset,
            final float[] actualPts,   final int actualOffset,
            final int     numPoints,   final CalculationType mode) throws TransformFailure
    {
        assertCoordinatesEqual(message, dimension, expectedPts, expectedOffset,
                actualPts, actualOffset, numPoints, mode, 0);
    }

    /**
     * Asserts that coordinate values are equal to the expected ones within a positive delta.
     * If the comparison fails, the given message is completed with the expected and actual
     * values, and the index of the coordinate where the failure was found.
     *
     * @param message         the message to print in case of failure.
     * @param dimension       the dimension of each coordinate points in the arrays.
     * @param expectedPts     the array of expected coordinate values.
     * @param expectedOffset  index of the first valid coordinate in the {@code expectedPts} array.
     * @param actualPts       the array of coordinate values to check against the expected ones.
     * @param actualOffset    index of the first valid coordinate in the {@code actualPts} array.
     * @param numPoints       number of coordinate points to compare.
     * @param mode            indicates if the coordinates being compared are the result of a direct
     *                        or inverse transform, or if strict equality is requested.
     * @throws TransformFailure if at least one coordinate value is not equal to the expected value.
     */
    protected final void assertCoordinatesEqual(
            final String   message,     final int dimension,
            final float[]  expectedPts, final int expectedOffset,
            final double[] actualPts,   final int actualOffset,
            final int      numPoints,   final CalculationType mode) throws TransformFailure
    {
        assertCoordinatesEqual(message, dimension, expectedPts, expectedOffset,
                actualPts, actualOffset, numPoints, mode, 0);
    }

    /**
     * Asserts that coordinate values are equal to the expected ones within a positive delta.
     * If the comparison fails, the given message is completed with the expected and actual
     * values, and the index of the coordinate where the failure was found.
     *
     * @param message         the message to print in case of failure.
     * @param dimension       the dimension of each coordinate points in the arrays.
     * @param expectedPts     the array of expected coordinate values.
     * @param expectedOffset  index of the first valid coordinate in the {@code expectedPts} array.
     * @param actualPts       the array of coordinate values to check against the expected ones.
     * @param actualOffset    index of the first valid coordinate in the {@code actualPts} array.
     * @param numPoints       number of coordinate points to compare.
     * @param mode            indicates if the coordinates being compared are the result of a direct
     *                        or inverse transform, or if strict equality is requested.
     * @throws TransformFailure if at least one coordinate value is not equal to the expected value.
     */
    protected final void assertCoordinatesEqual(
            final String   message,     final int dimension,
            final double[] expectedPts, final int expectedOffset,
            final float [] actualPts,   final int actualOffset,
            final int      numPoints,   final CalculationType mode) throws TransformFailure
    {
        assertCoordinatesEqual(message, dimension, expectedPts, expectedOffset,
                actualPts, actualOffset, numPoints, mode, 0);
    }

    /**
     * Asserts that coordinate values are equal to the expected ones within a positive delta.
     * If the comparison fails, the given message is completed with the expected and actual
     * values, and the index of the coordinate where the failure was found.
     *
     * @param message         the message to print in case of failure.
     * @param dimension       the dimension of each coordinate points in the arrays.
     * @param expectedPts     the array of expected coordinate values.
     * @param expectedOffset  index of the first valid coordinate in the {@code expectedPts} array.
     * @param actualPts       the array of coordinate values to check against the expected ones.
     * @param actualOffset    index of the first valid coordinate in the {@code actualPts} array.
     * @param numPoints       number of coordinate points to compare.
     * @param mode            indicates if the coordinates being compared are the result of a direct
     *                        or inverse transform, or if strict equality is requested.
     * @throws TransformFailure if at least one coordinate value is not equal to the expected value.
     */
    protected final void assertCoordinatesEqual(
            final String   message,     final int dimension,
            final double[] expectedPts, final int expectedOffset,
            final double[] actualPts,   final int actualOffset,
            final int      numPoints,   final CalculationType mode) throws TransformFailure
    {
        assertCoordinatesEqual(message, dimension, expectedPts, expectedOffset,
                actualPts, actualOffset, numPoints, mode, 0);
    }

    /**
     * Implementation of public assertion methods with the addition of the coordinate
     * index to be reported in error message.
     *
     * @param message         the header part of the message to format in case of failure.
     * @param dimension       the dimension of each coordinate points in the arrays.
     * @param expectedPts     the {@code float[]} or {@code double[]} array of expected coordinate values.
     * @param expectedOffset  index of the first valid coordinate in the {@code expectedPts} array.
     * @param actualPts       the {@code float[]} or {@code double[]} array of coordinate values to check against the expected ones.
     * @param actualOffset    index of the first valid coordinate in the {@code actualPts} array.
     * @param numPoints       number of coordinate points to compare.
     * @param mode            indicates if the coordinates being compared are the result of a direct
     *                        or inverse transform, or if strict equality is requested.
     * @param reportedIndex   in case of failure, index of the point (not coordinate) to report in the error message.
     * @throws TransformFailure if at least one coordinate value is not equal to the expected value.
     */
    private void assertCoordinatesEqual(
            final String   message,     final int dimension,
            final Object   expectedPts, int expectedOffset,
            final Object   actualPts,   int actualOffset,
            final int      numPoints,   final CalculationType mode, final int reportedIndex)
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
                 * This method uses !(a <= b) expressions instead than (a > b) for catching NaN.
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
     * Returns the tolerance modifier to use for comparing coordinate values. The user-specified
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
            final ToleranceModifier foundOnTheClasspath = ToleranceModifiers.maximum(
                    ToleranceModifiers.getImplementationSpecific(transform));
            isToleranceRelaxed |= (foundOnTheClasspath != null);
            cachedModifier = ToleranceModifiers.concatenate(toleranceModifier, foundOnTheClasspath);
        }
        return cachedModifier;
    }

    /**
     * @deprecated The {@code boolean} argument has been replaced by a {@link CalculationType} argument.
     *
     * @param message   the message to print in case of failure.
     * @param expected  the array of expected coordinate values.
     * @param actual    the array of coordinate values to check against the expected ones.
     * @param index     the index of the coordinate point being compared, for message formatting.
     * @param strict    {@code true} for ignoring the {@linkplain #tolerance(double) tolerance} threshold.
     *                  In such case, coordinate values are checked for strict equality.
     */
    @Deprecated
    protected final void assertCoordinateEquals(final String message, final float[] expected,
            final float[] actual, final int index, final boolean strict)
    {
        assertCoordinateEquals(message, expected, actual, index,
                strict ? CalculationType.IDENTITY : CalculationType.DIRECT_TRANSFORM);
    }

    /**
     * @deprecated The {@code boolean} argument has been replaced by a {@link CalculationType} argument.
     *
     * @param message   the message to print in case of failure.
     * @param expected  the array of expected coordinate values.
     * @param actual    the array of coordinate values to check against the expected ones.
     * @param index     the index of the coordinate point being compared, for message formatting.
     * @param strict    {@code true} for ignoring the {@linkplain #tolerance(double) tolerance} threshold.
     *                  In such case, coordinate values are checked for strict equality.
     */
    @Deprecated
    protected final void assertCoordinateEquals(final String message, final float[] expected,
            final double[] actual, final int index, final boolean strict)
    {
        assertCoordinateEquals(message, expected, actual, index,
                strict ? CalculationType.IDENTITY : CalculationType.DIRECT_TRANSFORM);
    }

    /**
     * @deprecated The {@code boolean} argument has been replaced by a {@link CalculationType} argument.
     *
     * @param message   the message to print in case of failure.
     * @param expected  the array of expected coordinate values.
     * @param actual    the array of coordinate values to check against the expected ones.
     * @param index     the index of the coordinate point being compared, for message formatting.
     * @param strict    {@code true} for ignoring the {@linkplain #tolerance(double) tolerance} threshold.
     *                  In such case, coordinate values are checked for strict equality.
     */
    @Deprecated
    protected final void assertCoordinateEquals(final String message, final double[] expected,
            final float[] actual, final int index, final boolean strict)
    {
        assertCoordinateEquals(message, expected, actual, index,
                strict ? CalculationType.IDENTITY : CalculationType.DIRECT_TRANSFORM);
    }

    /**
     * @deprecated The {@code boolean} argument has been replaced by a {@link CalculationType} argument.
     *
     * @param message   the message to print in case of failure.
     * @param expected  the array of expected coordinate values.
     * @param actual    the array of coordinate values to check against the expected ones.
     * @param index     he index of the coordinate point being compared, for message formatting.
     * @param strict    {@code true} for ignoring the {@linkplain #tolerance(double) tolerance} threshold.
     *                  In such case, coordinate values are checked for strict equality.
     */
    @Deprecated
    protected final void assertCoordinateEquals(final String message, final double[] expected,
            final double[] actual, final int index, final boolean strict)
    {
        assertCoordinateEquals(message, expected, actual, index,
                strict ? CalculationType.IDENTITY : CalculationType.DIRECT_TRANSFORM);
    }

    /**
     * @deprecated The {@code boolean} argument has been replaced by a {@link CalculationType} argument.
     *
     * @param message         the message to print in case of failure.
     * @param dimension       the dimension of each coordinate points in the arrays.
     * @param expectedPts     the array of expected coordinate values.
     * @param expectedOffset  index of the first valid coordinate in the {@code expectedPts} array.
     * @param actualPts       the array of coordinate values to check against the expected ones.
     * @param actualOffset    index of the first valid coordinate in the {@code actualPts} array.
     * @param numPoints       number of coordinate points to compare.
     * @param strict          {@code true} for ignoring the {@linkplain #tolerance(double) tolerance} threshold.
     *                        In such case, coordinate values are checked for strict equality.
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
                strict ? CalculationType.IDENTITY : CalculationType.DIRECT_TRANSFORM);
    }

    /**
     * @deprecated The {@code boolean} argument has been replaced by a {@link CalculationType} argument.
     *
     * @param message         the message to print in case of failure.
     * @param dimension       the dimension of each coordinate points in the arrays.
     * @param expectedPts     the array of expected coordinate values.
     * @param expectedOffset  index of the first valid coordinate in the {@code expectedPts} array.
     * @param actualPts       the array of coordinate values to check against the expected ones.
     * @param actualOffset    index of the first valid coordinate in the {@code actualPts} array.
     * @param numPoints       number of coordinate points to compare.
     * @param strict          {@code true} for ignoring the {@linkplain #tolerance(double) tolerance} threshold.
     *                        In such case, coordinate values are checked for strict equality.
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
                strict ? CalculationType.IDENTITY : CalculationType.DIRECT_TRANSFORM);
    }

    /**
     * @deprecated The {@code boolean} argument has been replaced by a {@link CalculationType} argument.
     *
     * @param message         the message to print in case of failure.
     * @param dimension       the dimension of each coordinate points in the arrays.
     * @param expectedPts     the array of expected coordinate values.
     * @param expectedOffset  index of the first valid coordinate in the {@code expectedPts} array.
     * @param actualPts       the array of coordinate values to check against the expected ones.
     * @param actualOffset    index of the first valid coordinate in the {@code actualPts} array.
     * @param numPoints       number of coordinate points to compare.
     * @param strict          {@code true} for ignoring the {@linkplain #tolerance(double) tolerance} threshold.
     *                        In such case, coordinate values are checked for strict equality.
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
                strict ? CalculationType.IDENTITY : CalculationType.DIRECT_TRANSFORM);
    }

    /**
     * @deprecated The {@code boolean} argument has been replaced by a {@link CalculationType} argument.
     *
     * @param message         the message to print in case of failure.
     * @param dimension       the dimension of each coordinate points in the arrays.
     * @param expectedPts     the array of expected coordinate values.
     * @param expectedOffset  index of the first valid coordinate in the {@code expectedPts} array.
     * @param actualPts       the array of coordinate values to check against the expected ones.
     * @param actualOffset    index of the first valid coordinate in the {@code actualPts} array.
     * @param numPoints       number of coordinate points to compare.
     * @param strict          {@code true} for ignoring the {@linkplain #tolerance(double) tolerance} threshold.
     *                        In such case, coordinate values are checked for strict equality.
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
                strict ? CalculationType.IDENTITY : CalculationType.DIRECT_TRANSFORM);
    }

    /**
     * Asserts that a matrix of derivatives is equals to the expected ones within a positive delta.
     * If the comparison fails, the given message is completed with the expected and actual matrixes
     * values.
     *
     * <p>For each matrix element, the tolerance value is given by the corresponding element in the
     * {@code tolmat} matrix. This tolerance matrix is initialized by the
     * {@link #verifyDerivative(double[])} method to the differences found between the 3 forms of
     * finite difference (<cite>forward</cite>, <cite>backward</cite>, <cite>central</cite>).
     * Developers can override this method and overwrite the {@code tolmat} elements if they
     * wish different tolerance values.</p>
     *
     * @param  message   the message to print in case of failure.
     * @param  expected  the expected matrix of derivative values, estimated by finite differences.
     * @param  actual    the actual matrix computed by the transform to be tested.
     * @param  tolmat    the tolerance value for each matrix elements, or {@code null} for a strict comparison.
     * @throws DerivativeFailure if at least one matrix element is not equal to the expected value.
     *
     * @see #verifyDerivative(double[])
     * @see org.opengis.test.Assert#assertMatrixEquals(String, Matrix, Matrix, double)
     *
     * @since 3.1
     */
    protected void assertMatrixEquals(final String message, final Matrix expected, final Matrix actual, final Matrix tolmat)
            throws DerivativeFailure
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
     * @param message  user-supplied message to append, or {@code null}.
     */
    void appendErrorHeader(final StringBuilder buffer, final String message) {
        if (message != null) {
            buffer.append(message.trim());
        }
    }

    /**
     * Returns {@code true} if the given array is an array of {@code double} primitive types.
     */
    private static boolean isDoubleArray(final Object array) {
        return array.getClass().getComponentType() == Double.TYPE;
    }

    /**
     * Invoked by all {@code assertCoordinateEqual(…)} methods before two positions are compared.
     * This method allows subclasses to replace some equivalent coordinate values by a unique value.
     * For example implementations may ensure that longitude values are contained in the ±180°
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
