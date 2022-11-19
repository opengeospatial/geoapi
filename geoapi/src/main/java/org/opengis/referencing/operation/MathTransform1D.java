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


/**
 * Transforms one-dimensional coordinate points.
 * {@link CoordinateOperation#getMathTransform()} may returns instance of this
 * interface when source and destination coordinate systems are both one dimensional.
 * {@code MathTransform1D} extends {@link MathTransform} by adding a simple method
 * transforming a value without the overhead of creating data array.
 *
 * @departure extension
 *   This interface is not part of the OGC specification. It has been added as a complement
 *   of {@code MathTransform2D} and because the 1D case provides opportunities for optimization
 *   through a {@code transform} method accepting a single {@code double} primitive type.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 */
public interface MathTransform1D extends MathTransform {
    /**
     * Transforms the specified value.
     *
     * @param  value  the value to transform.
     * @return the transformed value.
     * @throws TransformException if the value cannot be transformed.
     */
    double transform(double value) throws TransformException;

    /**
     * Gets the derivative of this function at a value. The derivative is the
     * 1×1 matrix of the non-translating portion of the approximate affine
     * map at the value.
     *
     * @param  value  the value where to evaluate the derivative.
     * @return the derivative at the specified point.
     * @throws TransformException if the derivative cannot be evaluated at the specified point.
     */
    double derivative(double value) throws TransformException;

    /**
     * Creates the inverse transform of this object.
     *
     * @return the inverse transform.
     * @throws NoninvertibleTransformException if the transform cannot be inverted.
     */
    @Override
    MathTransform1D inverse() throws NoninvertibleTransformException;
}
