/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.referencing.operation;

// J2SE direct dependencies
import java.awt.Shape;
import java.awt.geom.Point2D;


/**
 * Transforms two-dimensional coordinate points.
 * {@link CoordinateOperation#getMathTransform} may returns instance of this
 * interface when source and destination coordinate systems are both two dimensional.
 * <code>MathTransform2D</code> extends {@link MathTransform} by adding some methods
 * for easier interoperability with
 *
 * <A HREF="http://java.sun.com/products/java-media/2D/">Java2D</A>.
 *
 * If the transformation is affine, then <code>MathTransform</code> shall be an
 * immutable instance of {@link java.awt.geom.AffineTransform}.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 1.0
 */
public interface MathTransform2D extends MathTransform {
    /**
     * Transforms the specified <code>ptSrc</code> and stores the result in <code>ptDst</code>.
     * If <code>ptDst</code> is <code>null</code>, a new {@link Point2D} object is allocated
     * and then the result of the transformation is stored in this object. In either case,
     * <code>ptDst</code>, which contains the transformed point, is returned for convenience.
     * If <code>ptSrc</code> and <code>ptDst</code> are the same object, the input point is
     * correctly overwritten with the transformed point.
     *
     * @param ptSrc the specified coordinate point to be transformed.
     * @param ptDst the specified coordinate point that stores the
     *              result of transforming <code>ptSrc</code>, or
     *              <code>null</code>.
     * @return the coordinate point after transforming <code>ptSrc</code>
     *         and stroring the result in <code>ptDst</code>.
     * @throws TransformException if the point can't be transformed.
     */
    Point2D transform(final Point2D ptSrc, final Point2D ptDst) throws TransformException;

    /**
     * Transform the specified shape. This method may replace straight lines by
     * quadratic curves when applicable. It may also do the opposite (replace
     * curves by straight lines). The returned shape doesn't need to have the
     * same number of points than the original shape.
     *
     * @param  shape Shape to transform.
     * @return Transformed shape, or <code>shape</code> if
     *         this transform is the identity transform.
     * @throws TransformException if a transform failed.
     */
    Shape createTransformedShape(final Shape shape) throws TransformException;

    /**
     * Gets the derivative of this transform at a point. The derivative is the
     * matrix of the non-translating portion of the approximate affine map at
     * the point.
     *
     * @param  point The coordinate point where to evaluate the derivative. Null value is
     *         accepted only if the derivative is the same everywhere. For example affine
     *         transform accept null value since they produces identical derivative no
     *         matter the coordinate value. But most map projection will requires a non-null
     *         value.
     * @return The derivative at the specified point as a 2&times;2 matrix.  This method
     *         never returns an internal object: changing the matrix will not change the
     *         state of this math transform.
     * @throws NullPointerException if the derivative dependents on coordinate
     *         and <code>point</code> is <code>null</code>.
     * @throws TransformException if the derivative can't be evaluated at the
     *         specified point.
     */
    Matrix derivative(final Point2D point) throws TransformException;
}
