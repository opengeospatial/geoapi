/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2001 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.pt;

// Various JDK's classes
import java.io.Serializable;


/**
 * A box defined by two positions.
 * The two positions must have the same dimension.
 * Each of the ordinate values in the minimum point must be less than or equal
 * to the corresponding ordinate value in the maximum point.  Please note that
 * these two points may be outside the valid domain of their coordinate system.
 * (Of course the points and envelope do not explicitly reference a coordinate
 * system, but their implicit coordinate system is defined by their context.)
 *
 * @version 1.01
 * @since   1.00
 * @author Martin Daly
 * @author Martin Desruisseaux
 *
 * @deprecated Replaced by {@link org.opengis.spatialschema.geometry.Envelope}.
 */
@Deprecated
public class PT_Envelope implements Cloneable, Serializable {
    /**
     * Use <code>serialVersionUID</code> from first
     * draft for interoperability with CSS 1.00.
     */
    private static final long serialVersionUID = -1819256261961411213L;

    /**
     * Point containing minimum ordinate values.
     *
     * @deprecated Replaced by {@link org.opengis.spatialschema.geometry.DirectPosition#getLowerCorner}.
     */
    @Deprecated
    public PT_CoordinatePoint minCP;

    /**
     * Point containing maximum ordinate values.
     *
     * @deprecated Replaced by {@link org.opengis.spatialschema.geometry.DirectPosition#getUpperCorner}.
     */
    @Deprecated
    public PT_CoordinatePoint maxCP;

    /**
     * Construct an empty envelope. Caller must
     * initialize {@link #minCP} and {@link #maxCP}.
     */
    public PT_Envelope() {
    }

    /**
     * Returns a hash value for this envelope.
     * This value need not remain consistent between
     * different implementations of the same class.
     */
    public int hashCode() {
        int code = 0;
        if (minCP != null) code ^= minCP.hashCode();
        if (maxCP != null) code ^= maxCP.hashCode();
        return code;
    }

    /**
     * Compares the specified object with
     * this envelope for equality.
     */
    public boolean equals(final Object object) {
        if (object!=null && getClass().equals(object.getClass())) {
            final PT_Envelope that = (PT_Envelope) object;
            return (minCP==that.minCP || (minCP!=null && minCP.equals(that.minCP))) &&
                   (maxCP==that.maxCP || (maxCP!=null && maxCP.equals(that.maxCP)));
        }
        return false;
    }

    /**
     * Returns a deep copy of this envelope.
     *
     * @deprecated Replaced by an interface.
     */
    @Deprecated
    public Object clone() {
        try {
            final PT_Envelope copy = (PT_Envelope) super.clone();
            if (copy.minCP!=null) copy.minCP = (PT_CoordinatePoint) copy.minCP.clone();
            if (copy.maxCP!=null) copy.maxCP = (PT_CoordinatePoint) copy.maxCP.clone();
            return copy;
        } catch (CloneNotSupportedException exception) {
            // Should not happen, since we are cloneable.
            throw new InternalError(exception.getMessage());
        }
    }

    /**
     * Returns a string representation of this envelope.
     * The returned string is implementation dependent.
     * It is usually provided for debugging purposes only.
     */
    public String toString() {
        final StringBuffer buffer = new StringBuffer("PT_Envelope");
        buffer.append('[');
        buffer.append(minCP);
        buffer.append(", ");
        buffer.append(maxCP);
        buffer.append(']');
        return buffer.toString();
    }
}
