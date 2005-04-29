/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2001 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.ct;

// Various JDK's classes
import java.io.Serializable;


/**
 * Flags indicating parts of domain covered by a convex hull.
 * These flags can be combined.  For example, the value 3 corresponds to
 * a combination of {@link #CT_DF_Inside} and {@link #CT_DF_Outside},
 * which means that some parts of the convex hull are inside the domain,
 * and some parts of the convex hull are outside the domain.
 *
 * @version 1.01
 * @since   1.00
 * @author Martin Daly
 * @author Martin Desruisseaux
 *
 * @deprecated No direct replacement.
 */
public class CT_DomainFlags implements Serializable {
    /**
     * Use <code>serialVersionUID</code> from first
     * draft for interoperability with CSS 1.00.
     */
    private static final long serialVersionUID = 5618714041608095806L;

    /**
     * At least one point in a convex hull is inside the transform's domain.
     */
    public static final int CT_DF_Inside=1;

    /**
     * At least one point in a convex hull is outside the transform's domain.
     */
    public static final int CT_DF_Outside=2;

    /**
     * At least one point in a convex hull is not transformed continuously.
     * As an example, consider a "Longitude_Rotation" transform which adjusts
     * longitude coordinates to take account of a change in Prime Meridian.
     * If the rotation is 5 degrees east, then the point (Lat=0,Lon=175)
     * is not transformed continuously, since it is on the meridian line
     * which will be split at +180/-180 degrees.
     */
    public static final int CT_DF_Discontinuous=4;

    /**
     * The enum value.
     */
    public int value;

    /**
     * Construct an empty enum value. Caller
     * must initialize {@link #value}.
     */
    public CT_DomainFlags() {
    }

    /**
     * Construct a new enum value.
     */
    public CT_DomainFlags(final int value) {
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
            return ((CT_DomainFlags) object).value == value;
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
        final StringBuffer buffer = new StringBuffer("CT_DomainFlags");
        buffer.append('[');
        buffer.append(value);
        buffer.append(']');
        return buffer.toString();
    }
}
