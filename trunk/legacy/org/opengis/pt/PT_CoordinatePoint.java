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
package org.opengis.pt;

// Various JDK's classes
import java.util.Arrays;
import java.io.Serializable;


/**
 * A position defined by a list of numbers.
 * The ordinate values are indexed from 0 to (<code>NumDim-1</code>),
 * where <code>NumDim</code> is the dimension of the coordinate system
 * the coordinate point belongs in.
 *
 * @version 1.01
 * @since   1.00
 * @author Martin Daly
 * @author Martin Desruisseaux
 */
public class PT_CoordinatePoint implements Cloneable, Serializable {
    /**
     * Use <code>serialVersionUID</code> from first
     * draft for interoperability with CSS 1.00.
     */
    private static final long serialVersionUID = -5747198890219811554L;

    /**
     * The ordinates of the coordinate point.
     */
    public double[] ord;

    /**
     * Construct an empty coordinate point.
     * Caller must initialize {@link #ord}.
     */
    public PT_CoordinatePoint() {
    }

    /**
     * Construct a 2D coordinate from
     * the specified ordinates.
     */
    public PT_CoordinatePoint(final double x, final double y) {
        ord = new double[] {x,y};
    }

    /**
     * Construct a 3D coordinate from
     * the specified ordinates.
     */
    public PT_CoordinatePoint(final double x, final double y, final double z) {
        ord = new double[] {x,y,z};
    }

    /**
     * Returns a hash value for this coordinate.
     * This value need not remain consistent between
     * different implementations of the same class.
     */
    public int hashCode() {
        long code=0;
        if (ord != null) {
            for (int i=ord.length; --i>=0;) {
                code = (code << 1) ^ Double.doubleToLongBits(ord[i]);
            }
        }
        return (int)(code >>> 32) ^ (int)code;
    }

    /**
     * Compares the specified object with
     * this coordinate for equality.
     */
    public boolean equals(final Object object) {
        if (object!=null && getClass().equals(object.getClass())) {
            final PT_CoordinatePoint that = (PT_CoordinatePoint) object;
            return Arrays.equals(this.ord, that.ord);
        }
        return false;
    }

    /**
     * Returns a deep copy of this coordinate.
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
     * Returns a string representation of this coordinate.
     * The returned string is implementation dependent.
     * It is usually provided for debugging purposes only.
     */
    public String toString() {
        final StringBuffer buffer=new StringBuffer("PT_CoordinatePoint");
        buffer.append('[');
        if (ord != null) {
            for (int i=0; i<ord.length; i++) {
                if (i!=0) buffer.append(", ");
                buffer.append(ord[i]);
            }
        }
        buffer.append(']');
        return buffer.toString();
    }
}
