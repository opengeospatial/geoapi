/*
 * OpenGIS® Coordinate Transformation Services Implementation Specification
 * Copyright (2001) OpenGIS consortium
 *
 * THIS COPYRIGHT NOTICE IS A TEMPORARY PATCH.   Version 1.00 of official
 * OpenGIS's interface files doesn't contain a copyright notice yet. This
 * file is a slightly modified version of official OpenGIS's interface.
 * Changes have been done in order to fix RMI problems and are documented
 * on the SEAGIS web site (seagis.sourceforge.net). THIS FILE WILL LIKELY
 * BE REPLACED BY NEXT VERSION OF OPENGIS SPECIFICATIONS.
 */
package org.opengis.crs.cs;

// Various JDK's classes
import java.io.Serializable;


/**
 * Orientation of axis. 
 * Some coordinate systems use non-standard orientations.  For example,
 * the first axis in South African grids usually points West, instead of
 * East. This information is obviously relevant for algorithms converting
 * South African grid coordinates into Lat/Long.
 *
 * @version 1.01
 * @since   1.00
 * @author Martin Daly
 * @author Martin Desruisseaux
 */
public class CS_AxisOrientationEnum implements Serializable {
    /**
     * Use <code>serialVersionUID</code> from first
     * draft for interoperability with CSS 1.00.
     */
    private static final long serialVersionUID = 951852831904615922L;

    /**
     * Unknown or unspecified axis orientation.
     * This can be used for local or fitted coordinate systems. 
     */
    public static final int CS_AO_Other=0;

    /**
     * Increasing ordinates values go North.
     * This is usually used for Grid Y coordinates and Latitude.
     */
    public static final int CS_AO_North=1;

    /**
     * Increasing ordinates values go South.
     * This is rarely used.
     */
    public static final int CS_AO_South=2;

    /**
     * Increasing ordinates values go East.
     * This is rarely used.
     */
    public static final int CS_AO_East=3;

    /**
     * Increasing ordinates values go West.
     * This is usually used for Grid X coordinates and Longitude.
     */
    public static final int CS_AO_West=4;

    /**
     * Increasing ordinates values go up.
     * This is used for vertical coordinate systems.
     */
    public static final int CS_AO_Up=5;

    /**
     * Increasing ordinates values go down.
     * This is used for vertical coordinate systems.
     */
    public static final int CS_AO_Down=6;

    /**
     * The enum value.
     */
    public int value;

    /**
     * Construct an empty enum value. Caller
     * must initialize {@link #value}.
     */
    public CS_AxisOrientationEnum() {
    }

    /**
     * Construct a new enum value.
     */
    public CS_AxisOrientationEnum(final int value) {
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
            return ((CS_AxisOrientationEnum) object).value == value;
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
        final StringBuffer buffer = new StringBuffer("CS_AxisOrientationEnum");
        buffer.append('[');
        buffer.append(value);
        buffer.append(']');
        return buffer.toString();
    }
}
