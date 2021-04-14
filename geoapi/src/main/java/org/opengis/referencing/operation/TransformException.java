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

import org.opengis.geometry.DirectPosition;  // For javadoc


/**
 * Common superclass for a number of transformation-related exceptions.
 * {@code TransformException} are thrown by {@link MathTransform}
 * when a coordinate transformation can't be {@linkplain MathTransform#inverse inverted}
 * ({@link NoninvertibleTransformException}), when the
 * {@linkplain MathTransform#derivative derivative} can't be computed or when a coordinate
 * can't be {@linkplain MathTransform#transform(DirectPosition, DirectPosition) transformed}.
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
