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
package org.opengis.util;

// Annotations
import org.opengis.annotation.UML;
import org.opengis.annotation.Extension;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * An {@linkplain Integer integer} with associated infinite flag. This implementation uses
 * {@link Integer#MAX_VALUE} as a sentinal value for positive infinity. This approach is
 * consistent with J2SE {@link java.util.Collection#size()} contract. For consistency,
 * {@link Integer#MIN_VALUE} is a sentinal value for negative infinity.
 *
 * @author Jody Garnett (Refractions Research)
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.1
 */
@UML(identifier="UnlimitedInteger", specification=ISO_19103)
public final class UnlimitedInteger extends Number {
    /**
     * For compatibility with different versions.
     */
    private static final long serialVersionUID = 4748246369364771836L;

    /**
     * A constant holding the positive infinity.
     */
    private final UnlimitedInteger POSITIVE_INFINITY = new UnlimitedInteger(Integer.MAX_VALUE);

    /**
     * A constant holding the negative infinity.
     */
    private final UnlimitedInteger NEGATIVE_INFINITY = new UnlimitedInteger(Integer.MIN_VALUE);

    /**
     * The integer value.
     */
    private final int value;

    /**
     * Constructs a newly allocated {@code UnlimitedInteger} object that represents the specified
     * {@code int} value. {@link Integer#MAX_VALUE} and {@link Integer#MIN_VALUE MIN_VALUE} maps
     * to positive and negative infinities respectively.
     */
    public UnlimitedInteger(final int value) {
        this.value = value;
    }

    /**
     * Returns {@code true} if this integer represents a positive or negative infinity.
     */
    public boolean isInfinite(){
        return value == Integer.MAX_VALUE ||
               value == Integer.MIN_VALUE;
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
            default:                return Integer.toString(value);
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
     */
    @Override
    public boolean equals(final Object object) {
        return (object instanceof UnlimitedInteger) && ((UnlimitedInteger) object).value == value;
    }
}
