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
 * Details of axis. 
 * This is used to label axes, and indicate the orientation.
 *
 * @version 1.01
 * @since   1.00
 * @author Martin Daly
 * @author Martin Desruisseaux
 *
 * @deprecated Replaced by {@link org.opengis.crs.cs.CoordinateSystemAxis}.
 */
public class CS_AxisInfo implements Cloneable, Serializable {
    /**
     * Use <code>serialVersionUID</code> from first
     * draft for interoperability with CSS 1.00.
     */
    private static final long serialVersionUID = 4884281115517037533L;

    /**
     * Human readable name for axis. 
     * Possible values are X, Y, Long, Lat or any other short string.
     *
     * @deprecated Replaced by {@link org.opengis.crs.cs.CoordinateSystemAxis#getName}.
     */
    public String name;

    /**
     * Gets enumerated value for orientation.
     *
     * @deprecated Replaced by {@link org.opengis.crs.cs.CoordinateSystemAxis#getDirection}.
     */
    public CS_AxisOrientationEnum orientation;

    /**
     * Construct an empty AxisInfo. Caller must
     * initialize {@link #name} and {@link #orientation}.
     */
    public CS_AxisInfo() {
    }

    /**
     * Construct an AxisInfo.
     *
     * @param name The axis name.
     * @param orientation The axis orientation.
     */
    public CS_AxisInfo(final String name, final CS_AxisOrientationEnum orientation) {
        this.name        = name;
        this.orientation = orientation;
    }

    /**
     * Returns a hash value for this AxisInfo.
     * This value need not remain consistent between
     * different implementations of the same class.
     */
    public int hashCode() {
        int code = 0;
        if (name        != null) code ^= name.hashCode();
        if (orientation != null) code ^= orientation.hashCode();
        return code;
    }

    /**
     * Returns a copy of this AxisInfo.
     */
    public Object clone() {
        try {
            CS_AxisInfo copy = (CS_AxisInfo) super.clone();
            if (copy.orientation!=null) {
                copy.orientation = new CS_AxisOrientationEnum(copy.orientation.value);
            }
            return copy;
        } catch (CloneNotSupportedException exception) {
            // Should not happen, since we are cloneable.
            throw new InternalError(exception.getMessage());
        }
    }

    /**
     * Compares the specified object with
     * this parameter for equality.
     */
    public boolean equals(final Object object) {
        if (object!=null && getClass().equals(object.getClass())) {
            final CS_AxisInfo that = (CS_AxisInfo) object;
            return (name==that.name || (name!=null && name.equals(that.name))) &&
                   (orientation==that.orientation || (orientation!=null && orientation.equals(that.orientation)));
        } else {
            return false;
        }
    }

    /**
     * Returns a string représentation of this parameter.
     * The returned string is implementation dependent.
     * It is usually provided for debugging purposes only.
     */
    public String toString() {
        final StringBuffer buffer = new StringBuffer("CS_AxisInfo");
        buffer.append('[');
        buffer.append(name);
        buffer.append(',');
        buffer.append(orientation);
        buffer.append(']');
        return buffer.toString();
    }
}
