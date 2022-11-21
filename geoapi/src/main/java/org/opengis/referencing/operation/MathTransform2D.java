/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.referencing.operation;

import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.AffineTransform;


/**
 * Transforms two-dimensional coordinate points. {@link CoordinateOperation#getMathTransform()} may
 * returns instance of this interface when source and destination coordinate systems are both two
 * dimensional. {@code MathTransform2D} extends {@link MathTransform} by adding some methods for
 * easier inter-operability with <a href="https://docs.oracle.com/javase/8/docs/technotes/guides/2d/">Java2D</a>.
 *
 * @departure integration
 *   This interface is not part of OGC specification. It has been added in GeoAPI for
 *   close integration with the <cite>Java2D</cite> library. The API defined in this
 *   interface matches the {@code java.awt.geom.AffineTransform} API.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 */
public interface MathTransform2D extends MathTransform {
    /**
     * Transforms the specified {@code ptSrc} and stores the result in {@code ptDst}.
     * If {@code ptDst} is {@code null}, a new {@link Point2D} object is allocated
     * and then the result of the transformation is stored in this object. In either case,
     * {@code ptDst}, which contains the transformed point, is returned for convenience.
     * If {@code ptSrc} and {@code ptDst} are the same object, the input point is
     * correctly overwritten with the transformed point.
     *
     * @param  ptSrc  the coordinate point to be transformed.
     * @param  ptDst  the coordinate point that stores the result of transforming {@code ptSrc},
     *         or {@code null} if a new point shall be created.
     * @return the coordinate point after transforming {@code ptSrc} and storing the result
     *         in {@code ptDst} or in a new point if {@code ptDst} was null.
     * @throws TransformException if the point cannot be transformed.
     *
     * @see AffineTransform#transform(Point2D, Point2D)
     */
    Point2D transform(final Point2D ptSrc, final Point2D ptDst) throws TransformException;

    /**
     * Transforms the specified shape. This method may replace straight lines by quadratic curves
     * when applicable. It may also do the opposite (replace curves by straight lines). The returned
     * shape doesn't need to have the same number of points than the original shape.
     *
     * @param  shape  the Shape to transform.
     * @return the transformed shape. Some implementations may returns
     *         {@code shape} unmodified if this transform is identity.
     * @throws TransformException if a transform failed.
     *
     * @see AffineTransform#createTransformedShape(Shape)
     */
    Shape createTransformedShape(final Shape shape) throws TransformException;

    /**
     * Gets the derivative of this transform at a point. The derivative is the
     * matrix of the non-translating portion of the approximate affine map at
     * the point.
     *
     * @param  point  the coordinate point where to evaluate the derivative.
     *         Null value is accepted only if the derivative is the same everywhere.
     *         For example, affine transform accept null value since they produces identical derivative
     *         no matter the coordinate value. But most map projection will requires a non-null value.
     * @return the derivative at the specified point as a 2×2 matrix.
     *         This method never returns an internal object:
     *         changing the matrix will not change the state of this math transform.
     * @throws NullPointerException if the derivative dependents on coordinate and {@code point} is {@code null}.
     * @throws TransformException if the derivative cannot be evaluated at the specified point.
     */
    Matrix derivative(final Point2D point) throws TransformException;

    /**
     * Creates the inverse transform of this object.
     *
     * @return the inverse transform.
     * @throws NoninvertibleTransformException if the transform cannot be inverted.
     *
     * @see AffineTransform#createInverse()
     */
    @Override
    MathTransform2D inverse() throws NoninvertibleTransformException;
}
