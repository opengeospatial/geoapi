/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.referencing.operation;


/**
 * Transforms one-dimensional coordinate points.
 * {@link CoordinateOperation#getMathTransform} may returns instance of this
 * interface when source and destination coordinate systems are both one dimensional.
 * {@code MathTransform1D} extends {@link MathTransform} by adding a simple method
 * transforming a value without the overhead of creating data array.
 *
 * @departure extension
 *   This interface is not part of OGC specification. It has been added as a complement
 *   of <code>MathTransform2D</code> and because the 1D case provides opportunities for
 *   optimization through a <code>transform</code> method accepting a single <code>double</code>
 *   primitive type.
 *
 * @author  Martin Desruisseaux (IRD)
 * @since   GeoAPI 1.0
 */
public interface MathTransform1D extends MathTransform {
    /**
     * Transforms the specified value.
     *
     * @param value The value to transform.
     * @return the transformed value.
     * @throws TransformException if the value can't be transformed.
     */
    double transform(double value) throws TransformException;

    /**
     * Gets the derivative of this function at a value. The derivative is the
     * 1&times;1 matrix of the non-translating portion of the approximate affine
     * map at the value.
     *
     * @param  value The value where to evaluate the derivative.
     * @return The derivative at the specified point.
     * @throws TransformException if the derivative can't be evaluated at the
     *         specified point.
     */
    double derivative(double value) throws TransformException;

    /**
     * Creates the inverse transform of this object.
     *
     * @return The inverse transform.
     * @throws NoninvertibleTransformException if the transform can't be inversed.
     *
     * @since GeoAPI 2.2
     */
    MathTransform1D inverse() throws NoninvertibleTransformException;
}
