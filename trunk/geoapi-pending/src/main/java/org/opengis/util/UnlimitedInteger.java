/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2010 Open Geospatial Consortium, Inc.
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
package org.opengis.util;

import org.opengis.annotation.UML;
import static org.opengis.annotation.Specification.*;


/**
 * An {@linkplain Integer integer} with associated infinite flag. This implementation uses
 * {@link Integer#MAX_VALUE} as a sentinel value for positive infinity. This approach is
 * consistent with J2SE {@link java.util.Collection#size()} contract. For consistency,
 * {@link Integer#MIN_VALUE} is a sentinal value for negative infinity.
 *
 * @author Jody Garnett (Refractions Research)
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.1
 *
 * @deprecated ISO uses this type only for the multiplicity of associations in UML models,
 *      which is not something that we try to represent in GeoAPI. Even if we wanted this
 *      information, custom class can not be used in Java annotations.
 */
@Deprecated
@UML(identifier="UnlimitedInteger", specification=ISO_19103)
public final class UnlimitedInteger extends Number implements Comparable<UnlimitedInteger> {
    /**
     * For compatibility with different versions.
     */
    private static final long serialVersionUID = 4748246369364771836L;

    /**
     * A constant holding the negative infinity.
     */
    public static final UnlimitedInteger NEGATIVE_INFINITY = new UnlimitedInteger(Integer.MIN_VALUE);

    /**
     * A constant holding the positive infinity.
     */
    public static final UnlimitedInteger POSITIVE_INFINITY = new UnlimitedInteger(Integer.MAX_VALUE);

    /**
     * A constant holding the minimum finite value a {@code UnlimitedInteger} can have.
     */
    public static final int MIN_VALUE = Integer.MIN_VALUE + 1;

    /**
     * A constant holding the maximum finite value a {@code UnlimitedInteger} can have.
     */
    public static final int MAX_VALUE = Integer.MAX_VALUE - 1;

    /**
     * The integer value.
     */
    private final int value;

    /**
     * Constructs a newly allocated {@code UnlimitedInteger} object that represents the specified
     * {@code int} value. {@link Integer#MAX_VALUE} and {@link Integer#MIN_VALUE MIN_VALUE} maps
     * to positive and negative infinities respectively.
     *
     * @param value The integer value to wrap in a {@code UnlimitedInteger}.
     */
    public UnlimitedInteger(final int value) {
        this.value = value;
    }

    /**
     * Returns {@code true} if this integer represents a positive or negative infinity.
     *
     * @return {@code true} if this integer represents a positive or negative infinity.
     */
    public boolean isInfinite(){
        return (value == Integer.MAX_VALUE) || (value == Integer.MIN_VALUE);
    }

    /**
     * Returns the value of this {@code UnlimitedInteger} as an {@code int}. Positive and negative
     * infinities map to {@link Integer#MAX_VALUE} and {@link Integer#MIN_VALUE MIN_VALUE}
     * respectively.
     */
    @Override
    public int intValue() {
        return value;
    }

    /**
     * Returns the value of this {@code UnlimitedInteger} as a {@code long}. Positive and negative
     * infinities map to {@link Long#MAX_VALUE} and {@link Long#MIN_VALUE MIN_VALUE} respectively.
     */
    @Override
    public long longValue() {
        return value;
    }

    /**
     * Returns the value of this {@code UnlimitedInteger} as a {@code float}.
     * Positive and negative infinities map to {@link Float#POSITIVE_INFINITY}
     * and {@link Float#NEGATIVE_INFINITY NEGATIVE_INFINITY} respectively.
     */
    @Override
    public float floatValue() {
        switch (value) {
            case Integer.MAX_VALUE: return Float.POSITIVE_INFINITY;
            case Integer.MIN_VALUE: return Float.NEGATIVE_INFINITY;
            default:                return value;
        }
    }

    /**
     * Returns the value of this {@code UnlimitedInteger} as a {@code double}.
     * Positive and negative infinities map to {@link Double#POSITIVE_INFINITY}
     * and {@link Double#NEGATIVE_INFINITY NEGATIVE_INFINITY} respectively.
     */
    @Override
    public double doubleValue() {
        switch (value) {
            case Integer.MAX_VALUE: return Double.POSITIVE_INFINITY;
            case Integer.MIN_VALUE: return Double.NEGATIVE_INFINITY;
            default:                return value;
        }
    }

    /**
     * Returns a string representation of this number.
     */
    @Override
    public String toString() {
        switch (value) {
            case Integer.MAX_VALUE: return  "\u221E"; // Infinity symbol.
            case Integer.MIN_VALUE: return "-\u221E";
            default: return Integer.toString(value);
        }
    }

    /**
     * Returns a hash code for this integer.
     */
    @Override
    public int hashCode() {
        return value;
    }

    /**
     * Compares this number with the specified object for equality.
     *
     * @param  object The object to compare with this unlimited integer for equality.
     * @return {@code true} if both objects are equal.
     */
    @Override
    public boolean equals(final Object object) {
        return (object instanceof UnlimitedInteger) && ((UnlimitedInteger) object).value == value;
    }

    /**
     * Compares two {@code UnlimitedInteger} objects numerically.
     *
     * @param   other the unlimited integer to be compared.
     * @return  {@code 0} if this {@code UnlimitedInteger} is equal to the given value,
     *          {@code -1} if this {@code UnlimitedInteger} is numerically less than the given value, and
     *          {@code +1} if this {@code UnlimitedInteger} is numerically greater than the given value,
     */
    public int compareTo(final UnlimitedInteger other) {
        if (value < other.value) return -1;
        if (value > other.value) return +1;
        return 0;
    }
}
