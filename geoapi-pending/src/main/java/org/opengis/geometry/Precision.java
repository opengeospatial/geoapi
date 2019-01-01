/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2007-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.geometry;


/**
 * Specifies the precision model of the {@linkplain DirectPosition direct positions}
 * in a {@linkplain Geometry geometry}.
 * <p>
 * A precision model defines a grid of allowable points. The {@link #round} method
 * allows to round a direct position to the nearest allowed point. The {@link #getType}
 * method describes the collapsing behavior of a direct position.
 * <p>
 * {@code Precision} instances can be sorted by their {@linkplain #getScale scale}.
 * </p>
 *
 * @author Jody Garnett
 * @since GeoAPI 2.1
 */
public interface Precision extends Comparable<Precision> {
    /**
     * Compares this precision model with the specified one. Returns -1 is this model is
     * less accurate than the other one, +1 if it is more accurate, or 0 if they have the
     * same accuracy.
     *
     * @param other Other precision model to compare against.
     * @return a negative integer, zero, or a positive integer as this object
     *      is less than, equal to, or greater than the other.
     */
    int compareTo(Precision other);

    /**
     * Returns the maximum number of significant digits provided by this precision model..
     * <p>
     * Apparently this is usually used for output, note GML generation usually has its own concept
     * of significant digits. You may be able to capture this in terms of the getScale().
     * </p>
     *
     * @return number of significant digits
     * @see #getScale()
     *
     * @deprecated This is redundant with {@link #getScale}.
     */
    @Deprecated
    int getMaximumSignificantDigits();

    /**
     * Multiplying factor used to obtain a precise ordinate.
     * <p>
     * Multiply by this value and then divide by this value to round correctly:
     *
     * <blockquote><pre>
     * double scale = pm.getScale();
     * return Math.round(value * scale) / scale;
     * </pre></blockquote>
     *
     * So to round to {@code 3} significant digits we would have a scale of {@code 1000}.
     * Tip: the number of significant digits can be computed as below:
     *
     * <blockquote><pre>
     * int significantDigits = (int) Math.ceil(Math.log10(pm.getScale()));
     * </pre></blockquote>
     *
     * @return Multiplying factor used before rounding.
     */
    double getScale();

    /**
     * Returns the type of this precision model.
     */
    PrecisionType getType();

    /**
     * Rounds a direct position to this precision model in place.
     *
     * <p>It is likely that a {@code Precision} instance will keep different rounding rules for different
     * axis (example <var>x</var> &amp; <var>y</var> ordinates may be handled differently then height),
     * by always rounding a direct position as a whole we will enable this functionality.</p>
     */
    void round(DirectPosition position);
}
