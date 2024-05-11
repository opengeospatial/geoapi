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

import java.awt.geom.AffineTransform;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.MismatchedDimensionException;
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
     * Transforms a list of coordinate point ordinal values.
     * This method is provided for efficiently transforming many points.
     * The supplied array of ordinal values will contain packed ordinal
     * values. For example, if the source dimension is 3, then the ordinals
     * will be packed in this order:
     *
     * (<var>x₀</var>,<var>y₀</var>,<var>z₀</var>,
     *  <var>x₁</var>,<var>y₁</var>,<var>z₁</var> …).
     *
     * @param  srcPts the array containing the source point coordinates.
     * @param  srcOff the offset to the first point to be transformed in the source array.
     * @param  dstPts the array into which the transformed point coordinates are returned.
     *                May be the same as {@code srcPts}.
     * @param  dstOff the offset to the location of the first transformed point that is stored in the destination array.
     * @param  numPts the number of point objects to be transformed.
     * @throws TransformException if a point cannot be transformed. Some implementations will stop at the first failure,
     *         wile some other implementations will fill the untransformable points with {@linkplain Double#NaN} values,
     *         continue and throw the exception only at end. Implementations that fall in the latter case should set the
     *         {@linkplain TransformException#getLastCompletedTransform last completed transform} to {@code this}.
     *
     * @see AffineTransform#transform(double[], int, double[], int, int)
     */
    @UML(identifier="transformList", specification=OGC_01009)
    void transform(double[] srcPts, int srcOff,
                   double[] dstPts, int dstOff, int numPts) throws TransformException;

    /**
     * Transforms a list of coordinate point ordinal values.
     * This method is provided for efficiently transforming many points.
     * The supplied array of ordinal values will contain packed ordinal
     * values.  For example, if the source dimension is 3, then the ordinals
     * will be packed in this order:
     *
     * (<var>x₀</var>,<var>y₀</var>,<var>z₀</var>,
     *  <var>x₁</var>,<var>y₁</var>,<var>z₁</var> …).
     *
     * @param  srcPts the array containing the source point coordinates.
     * @param  srcOff the offset to the first point to be transformed in the source array.
     * @param  dstPts the array into which the transformed point coordinates are returned.
     *                May be the same as {@code srcPts}.
     * @param  dstOff the offset to the location of the first transformed point that is stored in the destination array.
     * @param  numPts the number of point objects to be transformed.
     * @throws TransformException if a point cannot be transformed. Some implementations will stop at the first failure,
     *         wile some other implementations will fill the untransformable points with {@linkplain Float#NaN} values,
     *         continue and throw the exception only at end. Implementations that fall in the latter case should set the
     *         {@linkplain TransformException#getLastCompletedTransform last completed transform} to {@code this}.
     *
     * @see AffineTransform#transform(float[], int, float[], int, int)
     */
    void transform(float[] srcPts, int srcOff,
                   float[] dstPts, int dstOff, int numPts) throws TransformException;

    /**
     * Transforms a list of coordinate point ordinal values.
     * This method is provided for efficiently transforming many points.
     * The supplied array of ordinal values will contain packed ordinal
     * values.  For example, if the source dimension is 3, then the ordinals
     * will be packed in this order:
     *
     * (<var>x₀</var>,<var>y₀</var>,<var>z₀</var>,
     *  <var>x₁</var>,<var>y₁</var>,<var>z₁</var> …).
     *
     * @param  srcPts the array containing the source point coordinates.
     * @param  srcOff the offset to the first point to be transformed in the source array.
     * @param  dstPts the array into which the transformed point coordinates are returned.
     * @param  dstOff the offset to the location of the first transformed point that is stored in the destination array.
     * @param  numPts the number of point objects to be transformed.
     * @throws TransformException if a point cannot be transformed. Some implementations will stop at the first failure,
     *         wile some other implementations will fill the untransformable points with {@linkplain Double#NaN} values,
     *         continue and throw the exception only at end. Implementations that fall in the latter case should set the
     *         {@linkplain TransformException#getLastCompletedTransform last completed transform} to {@code this}.
     *
     * @see AffineTransform#transform(float[], int, double[], int, int)
     */
    void transform(float [] srcPts, int srcOff,
                   double[] dstPts, int dstOff, int numPts) throws TransformException;

    /**
     * Transforms a list of coordinate point ordinal values.
     * This method is provided for efficiently transforming many points.
     * The supplied array of ordinal values will contain packed ordinal
     * values.  For example, if the source dimension is 3, then the ordinals
     * will be packed in this order:
     *
     * (<var>x₀</var>,<var>y₀</var>,<var>z₀</var>,
     *  <var>x₁</var>,<var>y₁</var>,<var>z₁</var> …).
     *
     * @param  srcPts the array containing the source point coordinates.
     * @param  srcOff the offset to the first point to be transformed in the source array.
     * @param  dstPts the array into which the transformed point coordinates are returned.
     * @param  dstOff the offset to the location of the first transformed point that is
     *                stored in the destination array.
     * @param  numPts the number of point objects to be transformed.
     * @throws TransformException if a point cannot be transformed. Some implementations will stop at the first failure,
     *         wile some other implementations will fill the untransformable points with {@linkplain Float#NaN} values,
     *         continue and throw the exception only at end. Implementations that fall in the latter case should set the
     *         {@linkplain TransformException#getLastCompletedTransform last completed transform} to {@code this}.
     *
     * @see AffineTransform#transform(double[], int, float[], int, int)
     */
    void transform(double[] srcPts, int srcOff,
                   float [] dstPts, int dstOff, int numPts) throws TransformException;

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
     * @param  point the coordinate point where to evaluate the derivative. Null
     *         value is accepted only if the derivative is the same everywhere.
     *         For example, affine transform accept null value since they produces
     *         identical derivative no matter the coordinate value.
     *         But most map projection will requires a non-null value.
     * @return the derivative at the specified point (never {@code null}).
     *         This method never returns an internal object: changing the matrix
     *         will not change the state of this math transform.
     * @throws NullPointerException if the derivative depends on coordinate and {@code point} is {@code null}.
     * @throws MismatchedDimensionException if {@code point} does not have the expected dimension.
     * @throws TransformException if the derivative cannot be evaluated at the specified point.
     */
    @UML(identifier="derivative", specification=OGC_01009)
    Matrix derivative(final DirectPosition point)
            throws MismatchedDimensionException, TransformException;

    /**
     * Creates the inverse transform of this object. The target of the inverse transform
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
}
