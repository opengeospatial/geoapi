/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2001 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.cs;

// Various JDK's classes
import java.io.Serializable;


/**
 * Type of the datum expressed as an enumerated value.
 * The enumeration is split into ranges which indicate the datum's type.
 * The value should be one of the predefined values, or within the range
 * for local types. This will allow OGC to coordinate the addition of new
 * interoperable codes.
 *
 * @version 1.01
 * @since   1.00
 * @author Martin Daly
 * @author Martin Desruisseaux
 *
 * @deprecated Replaced by {@link org.opengis.crs.datum.VerticalDatumType}.
 *             for the vertical case. No replacement for other cases.
 */
public class CS_DatumType implements Serializable {
    /**
     * Use <code>serialVersionUID</code> from first
     * draft for interoperability with CSS 1.00.
     */
    private static final long serialVersionUID = -8852248702227384183L;

    /**
     * Lowest possible value for horizontal datum types.
     *
     * @deprecated No replacement.
     */
    public static final int CS_HD_Min=1000;

    /**
     * Unspecified horizontal datum type.
     * Horizontal datums with this type should never supply
     * a conversion to WGS84 using Bursa Wolf parameters.
     *
     * @deprecated No replacement, since this is a horizontal datum type.
     */
    public static final int CS_HD_Other=1000;

    /**
     * These datums, such as ED50, NAD27 and NAD83, have been designed to
     * support horizontal positions on the ellipsoid as opposed to positions
     * in 3-D space.  These datums were designed mainly to support a horizontal
     * component of a position in a domain of limited extent, such as a country,
     * a region or a continent.
     *
     * @deprecated No replacement, since this is a horizontal datum type.
     */
    public static final int CS_HD_Classic=1001;

    /**
     * A geocentric datum is a "satellite age" modern geodetic datum mainly of
     * global extent, such as WGS84 (used in GPS), PZ90 (used in GLONASS) and
     * ITRF. These datums were designed to support both a horizontal
     * component of position and a vertical component of position (through
     * ellipsoidal heights).  The regional realizations of ITRF, such as
     * ETRF, are also included in this category.
     *
     * @deprecated No replacement, since this is a horizontal datum type.
     */
    public static final int CS_HD_Geocentric=1002;

    /**
     * Highest possible value for horizontal datum types.
     *
     * @deprecated No replacement.
     */
    public static final int CS_HD_Max=1999;

    /**
     * Lowest possible value for vertical datum types.
     *
     * @deprecated No replacement.
     */
    public static final int CS_VD_Min=2000;

    /**
     * Unspecified vertical datum type.
     *
     * @deprecated Replaced by {@link org.opengis.crs.datum.VerticalDatumType#OTHER_SURFACE}.
     */
    public static final int CS_VD_Other=2000;

    /**
     * A vertical datum for orthometric heights that are measured along the
     * plumb line.
     *
     * @deprecated No replacement at this time.
     */
    public static final int CS_VD_Orthometric=2001;

    /**
     * A vertical datum for ellipsoidal heights that are measured along the
     * normal to the ellipsoid used in the definition of horizontal datum.
     *
     * @deprecated No replacement at this time.
     */
    public static final int CS_VD_Ellipsoidal=2002;

    /**
     * The vertical datum of altitudes or heights in the atmosphere. These
     * are approximations of orthometric heights obtained with the help of
     * a barometer or a barometric altimeter.  These values are usually
     * expressed in one of the following units: meters, feet, millibars
     * (used to measure pressure levels),  or theta value (units used to
     * measure geopotential height).
     *
     * @deprecated Replaced by {@link org.opengis.crs.datum.VerticalDatumType#BAROMETRIC}.
     */
    public static final int CS_VD_AltitudeBarometric=2003;

    /**
     * A normal height system.
     *
     * @deprecated No replacement at this time.
     */
    public static final int CS_VD_Normal=2004;

    /**
     * A vertical datum of geoid model derived heights, also called
     * GPS-derived heights. These heights are approximations of
     * orthometric heights (<var>H</var>), constructed from the
     * ellipsoidal heights (<var>h</var>) by the use of the given
     * geoid undulation model (<var>N</var>) through the equation:
     * <var>H</var>=<var>h</var>-<var>N</var>.
     *
     * @deprecated Replaced by {@link org.opengis.crs.datum.VerticalDatumType#GEOIDAL}.
     */
    public static final int CS_VD_GeoidModelDerived=2005;

    /**
     * This attribute is used to support the set of datums generated
     * for hydrographic engineering projects where depth measurements below
     * sea level are needed. It is often called a hydrographic or a marine
     * datum. Depths are measured in the direction perpendicular
     * (approximately) to the actual equipotential surfaces of the earth's
     * gravity field, using such procedures as echo-sounding.
     *
     * @deprecated Replaced by {@link org.opengis.crs.datum.VerticalDatumType#DEPTH}.
     */
    public static final int CS_VD_Depth=2006;

    /**
     * Highest possible value for vertical datum types.
     *
     * @deprecated No replacement.
     */
    public static final int CS_VD_Max=2999;

    /**
     * Lowest possible value for local datum types.
     *
     * @deprecated No replacement.
     */
    public static final int CS_LD_Min=10000;

    /**
     * Highest possible value for local datum types.
     *
     * @deprecated No replacement.
     */
    public static final int CS_LD_Max=32767;

    /**
     * The enum value.
     */
    public int value;

    /**
     * Construct an empty enum value. Caller
     * must initialize {@link #value}.
     */
    public CS_DatumType() {
    }

    /**
     * Construct a new enum value.
     */
    public CS_DatumType(final int value) {
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
            return ((CS_DatumType) object).value == value;
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
        final StringBuffer buffer = new StringBuffer("CS_DatumType");
        buffer.append('[');
        buffer.append(value);
        buffer.append(']');
        return buffer.toString();
    }
}
