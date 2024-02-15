/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2011 Open Geospatial Consortium, Inc.
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


/**
 * Transforms one-dimensional coordinate points.
 * {@link CoordinateOperation#getMathTransform()} may returns instance of this
 * interface when source and destination coordinate systems are both one dimensional.
 * {@code MathTransform1D} extends {@link MathTransform} by adding a simple method
 * transforming a value without the overhead of creating data array.
 *
 * @departure extension
 *   This interface is not part of the OGC specification. It has been added as a complement
 *   of <code>MathTransform2D</code> and because the 1D case provides opportunities for
 *   optimization through a <code>transform</code> method accepting a single <code>double</code>
 *   primitive type.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 */
public interface MathTransform1D extends MathTransform {
    /**
     * Transforms the specified value.
     *
     * @param value The value to transform.
     * @return the transformed value.
     * @throws TransformException if the value cannot be transformed.
     */
    double transform(double value) throws TransformException;

    /**
     * Gets the derivative of this function at a value. The derivative is the
     * 1&times;1 matrix of the non-translating portion of the approximate affine
     * map at the value.
     *
     * @param  value The value where to evaluate the derivative.
     * @return The derivative at the specified point.
     * @throws TransformException if the derivative cannot be evaluated at the
     *         specified point.
     */
    double derivative(double value) throws TransformException;

    /**
     * Creates the inverse transform of this object.
     *
     * @return The inverse transform.
     * @throws NoninvertibleTransformException if the transform cannot be inverted.
     *
     * @since 2.2
     */
    MathTransform1D inverse() throws NoninvertibleTransformException;
}
