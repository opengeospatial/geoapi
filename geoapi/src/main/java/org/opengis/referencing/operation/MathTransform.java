/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2024 Open Geospatial Consortium, Inc.
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
package org.opengis.referencing.operation;

import java.util.Optional;
import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;
import java.awt.geom.AffineTransform;
import org.opengis.util.FactoryException;
import org.opengis.geometry.DirectPosition;
import org.opengis.coordinate.CoordinateSet;
import org.opengis.coordinate.MismatchedDimensionException;
import org.opengis.referencing.cs.CoordinateSystem;
import org.opengis.referencing.crs.ProjectedCRS;
import org.opengis.referencing.datum.Ellipsoid;
import org.opengis.parameter.ParameterValueGroup;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;


/**
 * Transforms multi-dimensional coordinate points. A {@code MathTransform} is an object
 * that actually does the work of applying a {@linkplain Formula formula} to coordinate values.
 * The math transform does not know or care how the coordinates relate to positions in the real world.
 * For example, the affine transform applies a matrix to the coordinates without knowing how what it is doing
 * relates to the real world. So if the matrix scales <var>z</var> values by a factor of 1000,
 * then it could be converting metres into millimetres, or it could be converting kilometres into metres.
 *
 * <p>Because they have low semantic value (but high mathematical value), {@link MathTransform}s can be used
 * in applications that have nothing to do with GIS coordinates. For example, a math transform could be used
 * to map color coordinates between different color spaces, such as converting (red, green, blue) colors into
 * (hue, light, saturation) colors.</p>
 *
 * <h2>Application to coordinate operations</h2>
 * When used for coordinate operations, this interface transforms coordinate value for a point given in the
 * {@linkplain CoordinateOperation#getSourceCRS() source coordinate reference system} to coordinate value for
 * the same point in the {@linkplain CoordinateOperation#getTargetCRS() target coordinate reference system}.
 *
 * <ul>
 *   <li>In a {@linkplain Conversion conversion}, the transformation is accurate to within the
 *     limitations of the computer making the calculations.</li>
 *   <li>In a {@linkplain Transformation transformation}, where some of the operational parameters
 *     are derived from observations, the transformation is accurate to within the limitations of
 *     those observations.</li>
 * </ul>
 *
 * If a client application wishes to query the source and target
 * {@linkplain org.opengis.referencing.crs.CoordinateReferenceSystem coordinate reference systems}
 * of an operation, then it should keep hold of the {@link CoordinateOperation} interface,
 * and use the contained math transform object whenever it wishes to perform a transform.
 *
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 * @since   1.0
 *
 * @see AffineTransform
 * @see MathTransformFactory
 * @see Formula
 * @see CoordinateOperation#getMathTransform()
 */
@UML(identifier="CT_MathTransform", specification=OGC_01009)
public interface MathTransform {
    /**
     * Returns the number of dimensions of input points.
     *
     * @return the number of dimensions of input points.
     */
    @UML(identifier="getDimSource", specification=OGC_01009)
    int getSourceDimensions();

    /**
     * Returns the number of dimensions of output points.
     *
     * @return the number of dimensions of output points.
     */
    @UML(identifier="getDimTarget", specification=OGC_01009)
    int getTargetDimensions();

    /**
     * Transforms the specified {@code ptSrc} and stores the result in {@code ptDst}.
     * If {@code ptDst} is {@code null}, a new {@link DirectPosition} object is allocated
     * and then the result of the transformation is stored in this object. In either case,
     * {@code ptDst}, which contains the transformed point, is returned for convenience.
     * If {@code ptSrc} and {@code ptDst} are the same object,
     * the input point is correctly overwritten with the transformed point.
     *
     * @param  ptSrc the specified coordinate point to be transformed.
     * @param  ptDst the specified coordinate point that stores the result of transforming
     *         {@code ptSrc}, or {@code null}.
     * @return the coordinate point after transforming {@code ptSrc} and storing the result
     *         in {@code ptDst}, or a newly created point if {@code ptDst} was null.
     * @throws MismatchedDimensionException if {@code ptSrc} or
     *         {@code ptDst} doesn't have the expected dimension.
     * @throws TransformException if the point cannot be transformed.
     */
    @UML(identifier="transform", specification=OGC_01009)
    DirectPosition transform(DirectPosition ptSrc, DirectPosition ptDst)
            throws MismatchedDimensionException, TransformException;

    /**
     * Transforms an array of double-precision coordinate tuples.
     * This method is provided for efficiently transforming many points.
     * The supplied array will contain packed coordinate tuples.
     * For example, if the source dimension is 3, then the coordinates will be packed in this order:
     *
     * (<var>x₀</var>,<var>y₀</var>,<var>z₀</var>,
     *  <var>x₁</var>,<var>y₁</var>,<var>z₁</var> …).
     *
     * @param  srcPts the array containing the source coordinate tuples.
     * @param  srcOff the offset to the first tuple to be transformed in the source array.
     * @param  dstPts the array into which the transformed coordinate tuples are stored.
     *                May be the same array as {@code srcPts}.
     * @param  dstOff the offset to the first transformed coordinate that is stored in the destination array.
     * @param  numPts the number of coordinate <em>tuples</em> to be transformed.
     * @throws TransformException if a point cannot be transformed. Some implementations may stop at the first failure,
     *         while some other implementations may fill the untransformable points with {@linkplain Double#NaN} values,
     *         continue and throw the exception only at end. Implementations that fall in the latter case should set the
     *         {@linkplain TransformException#getLastCompletedTransform last completed transform} to {@code this}.
     *
     * @see #transform(DoubleBuffer, DoubleBuffer)
     * @see AffineTransform#transform(double[], int, double[], int, int)
     */
    @UML(identifier="transformList", specification=OGC_01009)
    void transform(double[] srcPts, int srcOff,
                   double[] dstPts, int dstOff, int numPts) throws TransformException;

    /**
     * Transforms an array of single-precision coordinate tuples.
     * This method is provided for efficiently transforming many points.
     * The supplied array will contain packed coordinate tuples.
     * For example, if the source dimension is 3, then the coordinates will be packed in this order:
     *
     * (<var>x₀</var>,<var>y₀</var>,<var>z₀</var>,
     *  <var>x₁</var>,<var>y₁</var>,<var>z₁</var> …).
     *
     * <p>Note: while the source and destination arrays use single-precision floating point numbers,
     * implementation should perform intermediate calculations in double-precision.</p>
     *
     * @param  srcPts the array containing the source coordinate tuples.
     * @param  srcOff the offset to the first tuple to be transformed in the source array.
     * @param  dstPts the array into which the transformed coordinate tuples are stored.
     *                May be the same array as {@code srcPts}.
     * @param  dstOff the offset to the first transformed coordinate that is stored in the destination array.
     * @param  numPts the number of coordinate <em>tuples</em> to be transformed.
     * @throws TransformException if a point cannot be transformed. Some implementations may stop at the first failure,
     *         while some other implementations may fill the untransformable points with {@linkplain Double#NaN} values,
     *         continue and throw the exception only at end. Implementations that fall in the latter case should set the
     *         {@linkplain TransformException#getLastCompletedTransform last completed transform} to {@code this}.
     *
     * @see #transform(FloatBuffer, FloatBuffer)
     * @see AffineTransform#transform(float[], int, float[], int, int)
     */
    void transform(float[] srcPts, int srcOff,
                   float[] dstPts, int dstOff, int numPts) throws TransformException;

    /**
     * Converts an array of single-precision coordinate tuples to double precision, then transform.
     * This method is provided for efficiently transforming many points.
     * The supplied array will contain packed coordinate tuples.
     * For example, if the source dimension is 3, then the coordinates will be packed in this order:
     *
     * (<var>x₀</var>,<var>y₀</var>,<var>z₀</var>,
     *  <var>x₁</var>,<var>y₁</var>,<var>z₁</var> …).
     *
     * @param  srcPts the array containing the source coordinate tuples.
     * @param  srcOff the offset to the first tuple to be transformed in the source array.
     * @param  dstPts the array into which the transformed coordinate tuples are stored.
     *                May be the same array as {@code srcPts}.
     * @param  dstOff the offset to the first transformed coordinate that is stored in the destination array.
     * @param  numPts the number of coordinate <em>tuples</em> to be transformed.
     * @throws TransformException if a point cannot be transformed. Some implementations may stop at the first failure,
     *         while some other implementations may fill the untransformable points with {@linkplain Double#NaN} values,
     *         continue and throw the exception only at end. Implementations that fall in the latter case should set the
     *         {@linkplain TransformException#getLastCompletedTransform last completed transform} to {@code this}.
     *
     * @see #transform(FloatBuffer, DoubleBuffer)
     * @see AffineTransform#transform(float[], int, double[], int, int)
     */
    void transform(float [] srcPts, int srcOff,
                   double[] dstPts, int dstOff, int numPts) throws TransformException;

    /**
     * Transforms an array of double-precision coordinate tuples, then converts to single-precision.
     * This method is provided for efficiently transforming many points.
     * The supplied array will contain packed coordinate tuples.
     * For example, if the source dimension is 3, then the coordinates will be packed in this order:
     *
     * (<var>x₀</var>,<var>y₀</var>,<var>z₀</var>,
     *  <var>x₁</var>,<var>y₁</var>,<var>z₁</var> …).
     *
     * @param  srcPts the array containing the source coordinate tuples.
     * @param  srcOff the offset to the first tuple to be transformed in the source array.
     * @param  dstPts the array into which the transformed coordinate tuples are stored.
     *                May be the same array as {@code srcPts}.
     * @param  dstOff the offset to the first transformed coordinate that is stored in the destination array.
     * @param  numPts the number of coordinate <em>tuples</em> to be transformed.
     * @throws TransformException if a point cannot be transformed. Some implementations may stop at the first failure,
     *         while some other implementations may fill the untransformable points with {@linkplain Double#NaN} values,
     *         continue and throw the exception only at end. Implementations that fall in the latter case should set the
     *         {@linkplain TransformException#getLastCompletedTransform last completed transform} to {@code this}.
     *
     * @see #transform(DoubleBuffer, FloatBuffer)
     * @see AffineTransform#transform(double[], int, float[], int, int)
     */
    void transform(double[] srcPts, int srcOff,
                   float [] dstPts, int dstOff, int numPts) throws TransformException;

    /**
     * Transforms a buffer of double-precision coordinate tuples.
     * This method is provided for efficiently transforming many points,
     * either in Java heap or in the memory of native applications (e.g. C/C++).
     * The supplied buffer will contain packed coordinate tuples.
     * For example, if the source dimension is 3, then the coordinates will be packed in this order:
     *
     * (<var>x₀</var>,<var>y₀</var>,<var>z₀</var>,
     *  <var>x₁</var>,<var>y₁</var>,<var>z₁</var> …).
     *
     * <p>The first coordinate to transform is read at index {@code srcPts.position()} and
     * the first transformed coordinate will be stored at index {@code dstPts.position()}.
     * The maximal number of coordinate <em>tuples</em> to transform is:</p>
     *
     * {@snippet lang="java" :
     * int numPts = Math.min(srcPts.remaining() / getSourceDimensions(),
     *                       dstPts.remaining() / getTargetDimensions());
     * }
     *
     * <p>If above maximum is zero, then this method returns 0.
     * Otherwise, this method <em>shall</em> transform at least one coordinate tuple.
     * It may transform any number of coordinate tuples between 1 and the above-cited maximum,
     * at implementation choice. On success, the position of the source and destination buffer
     * will be set to the index after the last coordinate read and stored respectively.
     * If an exception has been thrown, then the buffer positions are undetermined.</p>
     *
     * <h4>Default implementation</h4>
     * The default implementation delegates to {@link #transform(double[], int, double[], int, int)}.
     * Therefore, the default implementation supports only buffers on Java heap.
     * Implementations should override this method if they want to support native heaps.
     *
     * @param  srcPts the buffer containing the source coordinate tuples.
     * @param  dstPts the buffer into which the transformed coordinate tuples are stored.
     *                May be the same buffer as {@code srcPts}.
     * @return number of coordinate <em>tuples</em> actually transformed.
     * @throws UnsupportedOperationException if this implementation supports only buffers backed
     *         by accessible Java arrays, and at least one buffer is backed by native memory.
     * @throws java.nio.ReadOnlyBufferException if the destination buffer is read-only.
     * @throws TransformException if a point cannot be transformed. Some implementations may stop at the first failure,
     *         while some other implementations may fill the untransformable points with {@linkplain Double#NaN} values,
     *         continue and throw the exception only at end. Implementations that fall in the latter case should set the
     *         {@linkplain TransformException#getLastCompletedTransform last completed transform} to {@code this}.
     *
     * @see #transform(double[], int, double[], int, int)
     * @see CoordinateSet#asDoubleBuffers()
     *
     * @since 3.1
     */
    default int transform(final DoubleBuffer srcPts, final DoubleBuffer dstPts) throws TransformException {
        // The source code looks identical in the 4 variants of this method, but the compiled code differs.
        // Trying to factorize this code actually make the `MathTransform.class` file bigger.
        final int srcDim = getSourceDimensions();
        final int tgtDim = getTargetDimensions();
        final int srcOff = srcPts.position();
        final int dstOff = dstPts.position();
        final int numPts = Math.min(srcPts.remaining() / srcDim,
                                    dstPts.remaining() / tgtDim);
        transform(srcPts.array(), srcPts.arrayOffset() + srcOff,
                  dstPts.array(), dstPts.arrayOffset() + dstOff, numPts);
        srcPts.position(srcOff + numPts * srcDim);
        dstPts.position(dstOff + numPts * tgtDim);      // Must be last.
        return numPts;
    }

    /**
     * Transforms a buffer of single-precision coordinate tuples.
     * This method follows the same contract as {@link #transform(DoubleBuffer, DoubleBuffer)},
     * except that the floating-point precision of source and destination buffers are different.
     * See the double-precision variant of this method for details.
     *
     * <h4>Default implementation</h4>
     * The default implementation delegates to {@link #transform(float[], int, float[], int, int)}.
     * Therefore, the default implementation supports only buffers on Java heap.
     * Implementations should override this method if they want to support native heaps.
     *
     * @param  srcPts the buffer containing the source coordinate tuples.
     * @param  dstPts the buffer into which the transformed coordinate tuples are stored.
     *                May be the same buffer as {@code srcPts}.
     * @return number of coordinate <em>tuples</em> actually transformed.
     * @throws UnsupportedOperationException if this implementation supports only buffers backed
     *         by accessible Java arrays, and at least one buffer is backed by native memory.
     * @throws java.nio.ReadOnlyBufferException if the destination buffer is read-only.
     * @throws TransformException if a point cannot be transformed. Some implementations may stop at the first failure,
     *         while some other implementations may fill the untransformable points with {@linkplain Float#NaN} values,
     *         continue and throw the exception only at end. Implementations that fall in the latter case should set the
     *         {@linkplain TransformException#getLastCompletedTransform last completed transform} to {@code this}.
     *
     * @see #transform(float[], int, float[], int, int)
     * @see CoordinateSet#asFloatBuffers()
     *
     * @since 3.1
     */
    default int transform(final FloatBuffer srcPts, final FloatBuffer dstPts) throws TransformException {
        // The source code looks identical in the 4 variants of this method, but the compiled code differs.
        // Trying to factorize this code actually make the `MathTransform.class` file bigger.
        final int srcDim = getSourceDimensions();
        final int tgtDim = getTargetDimensions();
        final int srcOff = srcPts.position();
        final int dstOff = dstPts.position();
        final int numPts = Math.min(srcPts.remaining() / srcDim,
                                    dstPts.remaining() / tgtDim);
        transform(srcPts.array(), srcPts.arrayOffset() + srcOff,
                  dstPts.array(), dstPts.arrayOffset() + dstOff, numPts);
        srcPts.position(srcOff + numPts * srcDim);
        dstPts.position(dstOff + numPts * tgtDim);      // Must be last.
        return numPts;
    }

    /**
     * Converts simple-precision coordinate tuples to double-precision and transform them.
     * This method follows the same contract as {@link #transform(DoubleBuffer, DoubleBuffer)},
     * except that the floating-point precision of the source buffer is different.
     * See the double-precision variant of this method for details.
     *
     * <h4>Default implementation</h4>
     * The default implementation delegates to {@link #transform(float[], int, double[], int, int)}.
     * Therefore, the default implementation supports only buffers on Java heap.
     * Implementations should override this method if they want to support native heaps.
     *
     * @param  srcPts the buffer containing the source coordinate tuples.
     * @param  dstPts the buffer into which the transformed coordinate tuples are stored.
     *                May be the same buffer as {@code srcPts}.
     * @return number of coordinate <em>tuples</em> actually transformed.
     * @throws UnsupportedOperationException if this implementation supports only buffers backed
     *         by accessible Java arrays, and at least one buffer is backed by native memory.
     * @throws java.nio.ReadOnlyBufferException if the destination buffer is read-only.
     * @throws TransformException if a point cannot be transformed. Some implementations may stop at the first failure,
     *         while some other implementations may fill the untransformable points with {@linkplain Double#NaN} values,
     *         continue and throw the exception only at end. Implementations that fall in the latter case should set the
     *         {@linkplain TransformException#getLastCompletedTransform last completed transform} to {@code this}.
     *
     * @see #transform(float[], int, double[], int, int)
     * @see CoordinateSet#asFloatBuffers()
     *
     * @since 3.1
     */
    default int transform(final FloatBuffer srcPts, final DoubleBuffer dstPts) throws TransformException {
        // The source code looks identical in the 4 variants of this method, but the compiled code differs.
        // Trying to factorize this code actually make the `MathTransform.class` file bigger.
        final int srcDim = getSourceDimensions();
        final int tgtDim = getTargetDimensions();
        final int srcOff = srcPts.position();
        final int dstOff = dstPts.position();
        final int numPts = Math.min(srcPts.remaining() / srcDim,
                                    dstPts.remaining() / tgtDim);
        transform(srcPts.array(), srcPts.arrayOffset() + srcOff,
                  dstPts.array(), dstPts.arrayOffset() + dstOff, numPts);
        srcPts.position(srcOff + numPts * srcDim);
        dstPts.position(dstOff + numPts * tgtDim);      // Must be last.
        return numPts;
    }

    /**
     * Transforms coordinate tuples and converts the result to single-precision.
     * This method follows the same contract as {@link #transform(DoubleBuffer, DoubleBuffer)},
     * except that the floating-point precision of the destination buffer is different.
     * See the double-precision variant of this method for details.
     *
     * <h4>Default implementation</h4>
     * The default implementation delegates to {@link #transform(double[], int, float[], int, int)}.
     * Therefore, the default implementation supports only buffers on Java heap.
     * Implementations should override this method if they want to support native heaps.
     *
     * @param  srcPts the buffer containing the source coordinate tuples.
     * @param  dstPts the buffer into which the transformed coordinate tuples are stored.
     *                May be the same buffer as {@code srcPts}.
     * @return number of coordinate <em>tuples</em> actually transformed.
     * @throws UnsupportedOperationException if this implementation supports only buffers backed
     *         by accessible Java arrays, and at least one buffer is backed by native memory.
     * @throws java.nio.ReadOnlyBufferException if the destination buffer is read-only.
     * @throws TransformException if a point cannot be transformed. Some implementations may stop at the first failure,
     *         while some other implementations may fill the untransformable points with {@linkplain Float#NaN} values,
     *         continue and throw the exception only at end. Implementations that fall in the latter case should set the
     *         {@linkplain TransformException#getLastCompletedTransform last completed transform} to {@code this}.
     *
     * @see #transform(double[], int, float[], int, int)
     * @see CoordinateSet#asDoubleBuffers()
     *
     * @since 3.1
     */
    default int transform(final DoubleBuffer srcPts, final FloatBuffer dstPts) throws TransformException {
        // The source code looks identical in the 4 variants of this method, but the compiled code differs.
        // Trying to factorize this code actually make the `MathTransform.class` file bigger.
        final int srcDim = getSourceDimensions();
        final int tgtDim = getTargetDimensions();
        final int srcOff = srcPts.position();
        final int dstOff = dstPts.position();
        final int numPts = Math.min(srcPts.remaining() / srcDim,
                                    dstPts.remaining() / tgtDim);
        transform(srcPts.array(), srcPts.arrayOffset() + srcOff,
                  dstPts.array(), dstPts.arrayOffset() + dstOff, numPts);
        srcPts.position(srcOff + numPts * srcDim);
        dstPts.position(dstOff + numPts * tgtDim);      // Must be last.
        return numPts;
    }

    /**
     * Gets the derivative of this transform at a point.
     * The derivative is the matrix of the non-translating portion of the approximate affine map at the point.
     * The matrix will have dimensions corresponding to the source and target coordinate systems.
     * If the input dimension is <var>M</var>, and the output dimension is <var>N</var>,
     * then the matrix will have size <var>N</var>×<var>M</var>.
     * The elements of the matrix
     *
     *              {e<sub>n,m</sub> : <var>n</var>=0 … (<var>N</var>-1)}
     *
     * form a vector in the output space which is parallel to the displacement
     * caused by a small change in the <var>m</var>'th coordinate in the input space.
     *
     * <p>For example, if the input dimension is 4 and the output dimension is 3,
     * then a small displacement
     *
     * (<var>x₀</var>, <var>x₁</var>, <var>x₂</var>, <var>x₃</var>)
     *
     * in the input space will result in a displacement
     *
     * (<var>y₀</var>, <var>y₁</var>, <var>y₂</var>)
     *
     * in the output space computed as below (e<sub>n,m</sub> are the matrix elements):
     *
     * <pre>
     *┌    ┐     ┌                    ┐ ┌    ┐
     *│ y₀ │     │ e₀₀  e₀₁  e₀₂  e₀₃ │ │ x₀ │
     *│ y₁ │  =  │ e₁₀  e₁₁  e₁₂  e₁₃ │ │ x₁ │
     *│ y₂ │     │ e₂₀  e₂₁  e₂₂  e₂₃ │ │ x₂ │
     *└    ┘     └                    ┘ │ x₃ │
     *                                  └    ┘</pre>
     *
     * @param  point the coordinate point where to evaluate the derivative.
     *         Null value is accepted only if the derivative is the same everywhere.
     *         For example, an affine transform can accept null values since the transform
     *         computes the same derivative no matter the coordinate values.
     *         But most map projections will require a non-null value.
     * @return the derivative at the specified point (never {@code null}).
     *         This method never return an internal object: changing the matrix
     *         will not change the state of this math transform.
     * @throws NullPointerException if the derivative depends on coordinate and {@code point} is {@code null}.
     * @throws MismatchedDimensionException if {@code point} does not have the expected dimension.
     * @throws TransformException if the derivative cannot be evaluated at the specified point.
     */
    @UML(identifier="derivative", specification=OGC_01009)
    Matrix derivative(DirectPosition point)
            throws MismatchedDimensionException, TransformException;

    /**
     * Returns the inverse transform of this object. The target of the inverse transform
     * is the source of the original. The source of the inverse transform is the target
     * of the original. Using the original transform followed by the inverse's transform
     * will result in an identity map on the source coordinate space, when allowances for
     * error are made. This method may fail if the transform is not one to one. However,
     * all cartographic projections should succeed.
     *
     * @return the inverse transform.
     * @throws NoninvertibleTransformException if the transform cannot be inverted.
     *
     * @see AffineTransform#createInverse()
     */
    @UML(identifier="inverse", specification=OGC_01009)
    default MathTransform inverse() throws NoninvertibleTransformException {
        if (isIdentity()) {
            return this;
        }
        throw new NoninvertibleTransformException();
    }

    /**
     * Tests whether this transform does not move any points.
     *
     * @return {@code true} if this {@code MathTransform} is
     *         an identity transform; {@code false} otherwise.
     *
     * @see AffineTransform#isIdentity()
     */
    @UML(identifier="isIdentity", specification=OGC_01009)
    boolean isIdentity();

    /**
     * Returns a <i>Well-Known Text</i> (WKT) for this object.
     * Version 1 of Well-Know Text is defined in {@linkplain org.opengis.annotation.Specification#OGC_01009 OGC 01-009}.
     * This operation may fail if unsupported or if this instance contains elements that do not have WKT representation.
     *
     * @return the Well-Known Text (WKT) for this object.
     * @throws UnsupportedOperationException if this object cannot be formatted as WKT.
     *
     * @see MathTransformFactory#createFromWKT(String)
     */
    @UML(identifier="getWKT", specification=OGC_01009)
    default String toWKT() {
        throw new UnsupportedOperationException();
    }

    /**
     * Builder of a parameterized math transform using a method identified by a name or code.
     * A builder instance is created by a call to {@link MathTransformFactory#builder(String)}.
     * The {@linkplain #parameters() parameters} are set to default values and should be modified
     * in-place by the caller. If the transform requires semi-major and semi-minor axis lengths,
     * those parameters can be set directly or {@linkplain #setSourceAxes indirectly}.
     * Then, the transform is created by a call to {@link #create()}.
     * Example:
     *
     * {@snippet lang="java" :
     * MathTransformFactory  factory = ...;
     * MathTransform.Builder builder = factory.builder("Transverse Mercator");
     * ParameterValueGroup   pvGroup = builder.parameters();
     *
     * // Set the parameter values for UTM zone 54N.
     * pvGroup.parameter("Longitude of natural origin").setValue(141);
     * pvGroup.parameter("Scale factor at natural origin").setValue(0.9996);
     * pvGroup.parameter("False easting").setValue(500000);
     *
     * // Set indirectly the "semi_major" and "semi_minor" parameters.
     * Ellipdoid ellipsoid = ...;
     * builder.setSourceAxes(null, ellipsoid);
     *
     * // Get the results.
     * MathTransform mt = builder.create();
     * OperationMethod method = builder.getMethod().orElseThrow();
     * }
     *
     * <h2>Axis order, units and direction</h2>
     * By default, the source and target axes of a parameterized transform are normalized to
     * <var>east</var>, <var>north</var>, <var>up</var> (if applicable) directions in that order,
     * with angular and linear units in degrees and meters respectively
     * (see the {@linkplain #create() note on cartographic projections}).
     * However, this requirement becomes ambiguous when different axis orientations are embedded
     * in operation methods such as <cite>Transverse Mercator (South Orientated)</cite> (EPSG::9808),
     * or if the operation method uses axis directions or units that are incompatible with the
     * above-cited normalization. For avoiding ambiguities, the source and target axes can be
     * {@linkplain #setSourceAxes explicitly specified}.
     *
     * <h2>Prime meridians</h2>
     * The builder does <strong>not</strong> handle changes of prime meridian or anything else related to datum.
     * Datum changes have dedicated {@link OperationMethod}, for example <q>Longitude rotation</q> (EPSG:9601)
     * for changing the prime meridian.
     *
     * @author  Martin Desruisseaux (Geomatys)
     * @version 3.1
     * @since   3.1
     *
     * @see MathTransformFactory#builder(String)
     */
    interface Builder {
        /**
         * Returns the operation method used for creating the math transform from the parameter values.
         * This information may be known or accurate only after {@link #create()} has been invoked.
         *
         * @return the operation method used for creating the math transform from the parameter values.
         */
        default Optional<OperationMethod> getMethod() {
            return Optional.empty();
        }

        /**
         * Returns the parameter values of the transform to create.
         * Those parameters are initialized to default values, which may be implementation or method depend.
         * User-supplied values should be set directly in the returned instance with codes like
         * <code>parameter(</code><var>name</var><code>).setValue(</code><var>value</var><code>)</code>.
         *
         * @return the parameter values of the transform to create. Values should be set in-place.
         */
        ParameterValueGroup parameters();

        /**
         * Gives hints about axis lengths and their orientations in input coordinates.
         * The action performed by this call depends on the {@linkplain #getMethod() operation method}.
         * For map projections, the action may include something equivalent to the following code:
         *
         * {@snippet lang="java" :
         * parameters().parameter("semi_major").setValue(ellipsoid.getSemiMajorAxis(), ellipsoid.getAxisUnit());
         * parameters().parameter("semi_minor").setValue(ellipsoid.getSemiMinorAxis(), ellipsoid.getAxisUnit());
         * }
         *
         * For geodetic datum shifts, the action may be similar to above code but with different parameter names:
         * {@code "src_semi_major"} and {@code "src_semi_minor"}. Other operation methods may ignore the arguments.
         *
         * <h4>Axis order, units and direction</h4>
         * By default, the source axes of a parameterized transform are normalized to <var>east</var>,
         * <var>north</var>, <var>up</var> (if applicable) directions with units in degrees and meters.
         * If this requirement is ambiguous, for example because the operation method uses incompatible
         * axis directions or units, then the {@code cs} argument should be non-null for allowing the
         * implementation to resolve that ambiguity.
         *
         * @param  cs         the coordinate system defining source axis order and units, or {@code null} if none.
         * @param  ellipsoid  the ellipsoid providing source semi-axis lengths, or {@code null} if none.
         */
        default void setSourceAxes(CoordinateSystem cs, Ellipsoid ellipsoid) {
        }

        /**
         * Gives hints about axis lengths and their orientations in output coordinates.
         * The action performed by this call depends on the {@linkplain #getMethod() operation method}.
         * For datum shifts, the action may include something equivalent to the following code:
         *
         * {@snippet lang="java" :
         * parameters().parameter("tgt_semi_major").setValue(ellipsoid.getSemiMajorAxis(), ellipsoid.getAxisUnit());
         * parameters().parameter("tgt_semi_minor").setValue(ellipsoid.getSemiMinorAxis(), ellipsoid.getAxisUnit());
         * }
         *
         * <h4>Axis order, units and direction</h4>
         * By default, the target axes of a parameterized transform are normalized to <var>east</var>,
         * <var>north</var>, <var>up</var> (if applicable) directions with units in degrees and meters.
         * If this requirement is ambiguous, for example because the operation method uses incompatible
         * axis directions or units, then the {@code cs} argument should be non-null for allowing the
         * implementation to resolve that ambiguity.
         *
         * @param  cs         the coordinate system defining target axis order and units, or {@code null} if none.
         * @param  ellipsoid  the ellipsoid providing target semi-axis lengths, or {@code null} if none.
         */
        default void setTargetAxes(CoordinateSystem cs, Ellipsoid ellipsoid) {
        }

        /**
         * Creates the parameterized transform. The operation method is given by {@link #getMethod()}
         * and the parameter values should have been set on the group returned by {@link #parameters()}
         * before to invoke this constructor.
         * Example:
         *
         * {@snippet lang="java" :
         * MathTransformFactory  factory = ...;
         * MathTransform.Builder builder = factory.builder("Transverse_Mercator");
         * ParameterValueGroup   pg = builder.parameters();
         * pg.parameter("semi_major").setValue(6378137.000);
         * pg.parameter("semi_minor").setValue(6356752.314);
         * MathTransform mt = builder.create();
         * }
         *
         * <h4>Note on cartographic projections</h4>
         * Cartographic projection are used by {@link ProjectedCRS} to map geographic coordinates
         * (e.g., <var>longitude</var> and <var>latitude</var>) into (<var>easting</var>, <var>northing</var>) coordinates.
         * The latter coordinates can be imagined to lie on a plane, such as a paper map or a screen.
         * By default, cartographic projections created through this constructor will have the following properties:
         *
         * <ul>
         *   <li>Converts from (<var>longitude</var>, <var>latitude</var>) coordinates to (<var>easting</var>, <var>northing</var>).</li>
         *   <li>All angles are assumed to be degrees, and all distances are assumed to be meters.</li>
         *   <li>The domain shall be a subset of {[-180,180)×(-90,90)}.</li>
         *   <li>Axis directions are usually ({@linkplain org.opengis.referencing.cs.AxisDirection#EAST east},
         *       {@linkplain org.opengis.referencing.cs.AxisDirection#NORTH north}), but exceptions may exist
         *       for some operation methods like <cite>Lambert Conic Conformal (West Orientated)</cite>
         *       (EPSG:9826) or <cite>Transverse Mercator (South Orientated)</cite> (EPSG:9808).</li>
         * </ul>
         *
         * Although cartographic projections created by this constructor should have the properties listed above,
         * some projected coordinate reference systems have different properties.
         * For example, in Europe some projected <abbr>CRS</abbr>s use grads instead of degrees,
         * and often the {@linkplain ProjectedCRS#getBaseCRS() base geographic CRS}
         * is (<var>latitude</var>, <var>longitude</var>) instead of (<var>longitude</var>, <var>latitude</var>).
         * This means that the cartographic projection is often used as a single step in a series of conversions,
         * where the other steps change units and swap coordinates.
         * If {@linkplain #setSourceAxes source axes} and/or {@linkplain #setTargetAxes target axes}
         * have been specified, then this constructor shall concatenate those other steps.
         *
         * <h4>Ambiguity resolutions</h4>
         * When the change of axis directions is part of the map projection definition as in <cite>Transverse Mercator
         * (South Orientated)</cite>, there is a conflict with the above-cited (<var>east</var>, <var>north</var>) directions.
         * In such cases, the {@linkplain #setSourceAxes source axes} and/or {@linkplain #setTargetAxes target axes}
         * <em>should</em> be specified by the user for allowing implementations to resolve this ambiguity.
         *
         * @return the parameterized transform.
         * @throws FactoryException if the transform creation failed.
         *         This exception is thrown if some required parameters have not been supplied, or have illegal values.
         */
        @UML(identifier="MathTransformFactory.createParameterizedTransform", specification=OGC_01009)
        MathTransform create() throws FactoryException;
    }
}
