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
package org.opengis.cv;

// Input/output
import java.io.Serializable;


/**
 * Describes the color entry in a color table.
 *
 * @version 1.00
 * @since   1.00
 */
public final class CV_PaletteInterpretation implements Serializable {
    /**
     * Use <code>serialVersionUID</code> from first
     * draft for interoperability with GCS 1.00.
     */
    private static final long serialVersionUID = -1722525684694793520L;

    /**
     * The enum value.
     */
    public final int value;

    /**
     * Gray Scale color palette.
     */
    public static final int CV_Gray = 0;

    /**
     * RGB (Red Green Blue) color palette.
     */
    public static final int CV_RGB = 1;

    /**
     * CYMK (Cyan Yellow Magenta blacK) color palette.
     */
    public static final int CV_CMYK = 2;

    /**
     * HSL (Hue Saturation Lightness) color palette.
     */
    public static final int CV_HLS = 3;

    /**
     * Construct a new enum value.
     */
    public CV_PaletteInterpretation(final int value) {
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
            return ((CV_PaletteInterpretation) object).value == value;
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
        final StringBuffer buffer = new StringBuffer("CV_PaletteInterpretation");
        buffer.append('[');
        buffer.append(value);
        buffer.append(']');
        return buffer.toString();
    }
}
