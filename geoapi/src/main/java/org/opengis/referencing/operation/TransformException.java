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

import org.opengis.geometry.DirectPosition;  // For javadoc


/**
 * Common superclass for a number of transformation-related exceptions.
 * {@code TransformException} are thrown by {@link MathTransform}
 * when a coordinate transformation cannot be {@linkplain MathTransform#inverse inverted}
 * ({@link NoninvertibleTransformException}), when the
 * {@linkplain MathTransform#derivative derivative} cannot be computed or when a coordinate
 * cannot be {@linkplain MathTransform#transform(DirectPosition, DirectPosition) transformed}.
 * It is also thrown when {@link CoordinateOperationFactory} fails to find a path between two
 * {@linkplain org.opengis.referencing.crs.CoordinateReferenceSystem coordinate reference systems}.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   1.0
 */
public class TransformException extends Exception {
    /**
     * Serial number for inter-operability with different versions.
     */
    private static final long serialVersionUID = -8923944544398567533L;

    /**
     * The last transform that either transformed successfully all coordinates, or filled the
     * untransformable coordinates with {@linkplain Double#NaN NaN} values. This information
     * is useful in the context of concatenated transforms. May be {@code null} if unknown.
     *
     * @see #getLastCompletedTransform()
     * @see #setLastCompletedTransform(MathTransform)
     */
    @SuppressWarnings("serial")                     // Not statically typed as serializable.
    private MathTransform lastCompletedTransform;

    /**
     * Constructs an exception with no detail message.
     */
    public TransformException() {
    }

    /**
     * Constructs an exception with the specified detail message.
     *
     * @param message  the detail message, saved for later retrieval by the {@link #getMessage()} method.
     */
    public TransformException(String message) {
        super(message);
    }

    /**
     * Constructs an exception with the specified cause.
     *
     * @param cause  the cause, saved for later retrieval by the {@link #getCause()} method.
     *
     * @since 3.1
     */
    public TransformException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs an exception with the specified detail message and cause.
     *
     * @param message  the detail message, saved for later retrieval by the {@link #getMessage()} method.
     * @param cause    the cause, saved for later retrieval by the {@link #getCause()} method.
     */
    public TransformException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Returns the last transform that either transformed successfully all coordinates, or filled
     * the untransformable coordinates with {@linkplain Double#NaN NaN} values. This information
     * is useful in the context of concatenated transforms. May be {@code null} if unknown.
     *
     * @return the last reliable transform.
     */
    public MathTransform getLastCompletedTransform() {
        return lastCompletedTransform;
    }

    /**
     * Sets the last transform that either transformed successfully all coordinates, or
     * filled the untransformable coordinates with {@linkplain Double#NaN NaN} values.
     *
     * @param transform  the last reliable transform.
     */
    public void setLastCompletedTransform(final MathTransform transform) {
        lastCompletedTransform = transform;
    }
}
