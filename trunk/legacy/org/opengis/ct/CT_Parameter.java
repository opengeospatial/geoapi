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

// JDK's classes
import java.io.Serializable;


/**
 * A named parameter value.
 *
 * @version 1.01
 * @since   1.00
 * @author Martin Daly
 * @author Martin Desruisseaux
 *
 * @deprecated Replaced by {@link org.opengis.crs.operation.GeneralParameterValue}.
 */
public class CT_Parameter implements Cloneable, Serializable {
    /**
     * Use <code>serialVersionUID</code> from first
     * draft for interoperability with CSS 1.00.
     */
    private static final long serialVersionUID = 5082873622308235995L;

    /**
     * The parameter name.
     */
    public String name;

    /**
     * The parameter value.
     *
     * @deprecated Replaced by {@link org.opengis.crs.operation.ParameterValue#doubleValue}.
     */
    public double value;

    /**
     * Construct an empty parameter. Caller must
     * initialize {@link #name} and {@link #value}.
     */
    public CT_Parameter() {
    }

    /**
     * Construct a named parameter.
     *
     * @param name  The parameter name.
     * @param value The parameter value.
     */
    public CT_Parameter(final String name, final double value) {
        this.name  = name;
        this.value = value;
    }

    /**
     * Returns a hash value for this parameter.
     * This value need not remain consistent between
     * different implementations of the same class.
     */
    public int hashCode() {
        final long longCode = Double.doubleToLongBits(value);
        int code = (int)(longCode >>> 32) ^ (int)longCode;
        if (name != null) {
            code ^= name.hashCode();
        }
        return code;
    }

    /**
     * Returns a copy of this parameter.
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
     * this parameter for equality.
     */
    public boolean equals(final Object object) {
        if (object!=null && getClass().equals(object.getClass())) {
            final CT_Parameter that = (CT_Parameter) object;
            return Double.doubleToLongBits(this.value) == Double.doubleToLongBits(that.value) &&
                   (this.name==that.name || (name!=null && name.equals(that.name)));
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
        final StringBuffer buffer = new StringBuffer("CT_Parameter");
        buffer.append('[');
        buffer.append(name);
        buffer.append('=');
        buffer.append(value);
        buffer.append(']');
        return buffer.toString();
    }
}
