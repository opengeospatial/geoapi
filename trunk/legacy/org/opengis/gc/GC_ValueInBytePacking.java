/*
 * OpenGIS® Grid Coverage Implementation Specification
 *
 * This Java profile is derived from OpenGIS's specification
 * available on their public web site:
 *
 *     http://www.opengis.org/techno/implementation.htm
 *
 * You can redistribute it, but should not modify it unless
 * for greater OpenGIS compliance.
 */
package org.opengis.gc;

// Input/output
import java.io.Serializable;


/**
 * Order of the pixels in a byte for CV_1BIT, CV_2BIT and CV_4BIT grid values.
 *
 * @version 1.00
 * @since   1.00
 */
public final class GC_ValueInBytePacking implements Serializable {
    /**
     * Use <code>serialVersionUID</code> from first
     * draft for interoperability with GCS 1.00.
     */
    private static final long serialVersionUID = 3844801494102056065L;

    /**
     * The enum value.
     */
    public final int value;

    /** Low bit firts (little endian order). */ public static final int GC_LoBitFirst = 0;
    /** High bit first (big endian order).   */ public static final int GC_HiBitFirst = 1;

    /**
     * Construct a new enum value.
     */
    public GC_ValueInBytePacking(final int value) {
        this.value = value;
    }

    /**
     * Returns the enum value.
     */
    public int hashCode() {
        return value;
    }

    /**
     * Compares the specified object with
     * this enum for equality.
     */
    public boolean equals(final Object object) {
        if (object!=null && getClass().equals(object.getClass())) {
            return ((GC_ValueInBytePacking) object).value == value;
        } else {
            return false;
        }
    }

    /**
     * Returns a string représentation of this enum.
     * The returned string is implementation dependent.
     * It is usually provided for debugging purposes only.
     */
    public String toString() {
        final StringBuffer buffer = new StringBuffer("GC_ValueInBytePacking");
        buffer.append('[');
        buffer.append(value);
        buffer.append(']');
        return buffer.toString();
    }
}
