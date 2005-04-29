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

// JDK's classes
import java.io.Serializable;


/**
 * Parameters for a geographic transformation into WGS84. 
 * The Bursa Wolf parameters should be applied to geocentric coordinates,
 * where the X axis points towards the Greenwich Prime Meridian, the Y axis
 * points East, and the Z axis points North.
 *
 * @version 1.01
 * @since   1.00
 * @author Martin Daly
 * @author Martin Desruisseaux
 *
 * @deprecated No direct replacement.
 */
public class CS_WGS84ConversionInfo implements Cloneable, Serializable {
    /**
     * Use <code>serialVersionUID</code> from first
     * draft for interoperability with CSS 1.00.
     */
    private static final long serialVersionUID = -8059158004784472940L;

    /** Bursa Wolf shift in meters. */
    public double dx;

    /** Bursa Wolf shift in meters. */
    public double dy;

    /** Bursa Wolf shift in meters. */
    public double dz;

    /** Bursa Wolf rotation in arc seconds. */
    public double ex;

    /** Bursa Wolf rotation in arc seconds. */
    public double ey;

    /** Bursa Wolf rotation in arc seconds. */
    public double ez;

    /** Bursa Wolf scaling in parts per million. */
    public double ppm;

    /** Human readable text describing intended region of transformation. */
    public String areaOfUse;

    /**
     * Construct an object with
     * all parameters set to 0.
     */
    public CS_WGS84ConversionInfo(){
    }

    /**
     * Returns a hash value for this object.
     * This value need not remain consistent between
     * different implementations of the same class.
     */
    public int hashCode() {
        final long code = (Double.doubleToLongBits(dx ) << 0) ^
                          (Double.doubleToLongBits(dy ) << 1) ^
                          (Double.doubleToLongBits(dz ) << 2) ^
                          (Double.doubleToLongBits(ex ) << 3) ^
                          (Double.doubleToLongBits(ey ) << 4) ^
                          (Double.doubleToLongBits(ez ) << 5) ^
                          (Double.doubleToLongBits(ppm) << 6);
        return (int)(code >>> 32) ^ (int)code;
    }

    /**
     * Returns a copy of this object.
     */
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException exception) {
            // Should not happen, since we are cloneable.
            throw new InternalError(exception.getMessage());
        }
    }

    /**
     * Compares the specified object with
     * this object for equality.
     */
    public boolean equals(final Object object) {
        if (object instanceof CS_WGS84ConversionInfo) {
            final CS_WGS84ConversionInfo that = (CS_WGS84ConversionInfo) object;
            return Double.doubleToLongBits(this.dx)  == Double.doubleToLongBits(that.dx)  &&
                   Double.doubleToLongBits(this.dy)  == Double.doubleToLongBits(that.dy)  &&
                   Double.doubleToLongBits(this.dz)  == Double.doubleToLongBits(that.dz)  &&
                   Double.doubleToLongBits(this.ex)  == Double.doubleToLongBits(that.ex)  &&
                   Double.doubleToLongBits(this.ey)  == Double.doubleToLongBits(that.ey)  &&
                   Double.doubleToLongBits(this.ez)  == Double.doubleToLongBits(that.ez)  &&
                   Double.doubleToLongBits(this.ppm) == Double.doubleToLongBits(that.ppm) &&
                   (areaOfUse==that.areaOfUse || (areaOfUse!=null && areaOfUse.equals(that.areaOfUse)));
        }
        else return false;
    }

    /**
     * Returns a string représentation of this object.
     * The returned string is implementation dependent.
     * It is usually provided for debugging purposes only.
     */
    public String toString() {
        final StringBuffer buffer = new StringBuffer("CS_WGS84ConversionInfo");
        buffer.append('[');
        buffer.append(areaOfUse);
        buffer.append(": shift=(");
        buffer.append(dx); buffer.append(", ");
        buffer.append(dy); buffer.append(", ");
        buffer.append(dz); buffer.append("), rotation=(");
        buffer.append(ex); buffer.append(", ");
        buffer.append(ey); buffer.append(", ");
        buffer.append(ez); buffer.append("), ppm=");
        buffer.append(ppm);
        buffer.append(']');
        return buffer.toString();
    }
}
