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
import java.util.Arrays;
import java.io.Serializable;


/**
 * A two dimensional array of numbers. 
 *
 * @version 1.01
 * @since   1.00
 * @author Martin Daly
 * @author Martin Desruisseaux
 *
 * @deprecated Replaced by {@link org.opengis.referencing.operation.Matrix}.
 */
public class PT_Matrix implements Cloneable, Serializable {
    /**
     * Use <code>serialVersionUID</code> from first
     * draft for interoperability with CSS 1.00.
     */
    private static final long serialVersionUID = -6922782355222269340L;

    /**
     * Elements of the matrix. 
     * The elements should be stored in a rectangular two dimensional array.
     * So in Java, all <code>double[]</code> elements of the outer array must
     * have the same size.  In COM, this is represented as a 2D SAFEARRAY.
     */
    public double[][] elt;

    /**
     * Construct an empty matrix. Caller must
     * initialize {@link #elt}.
     */
    public PT_Matrix() {
    }

    /**
     * Returns a hash value for this coordinate.
     * This value need not remain consistent between
     * different implementations of the same class.
     */
    public int hashCode() {
        long code=0;
        if (elt != null) {
            for (int j=elt.length; --j>=0;) {
                final double[] row = elt[j];
                if (row != null) {
                    for (int i=row.length; --i>=0;) {
                        code = (code << 1) ^ Double.doubleToLongBits(row[i]);
                    }
                }
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
            final PT_Matrix that = (PT_Matrix) object;
            if (this.elt == that.elt) {
                return true;
            }
            if (this.elt!=null && that.elt!=null) {
                if (this.elt.length == that.elt.length) {
                    for (int j=elt.length; --j>=0;) {
                        final double[] row1 = this.elt[j];
                        final double[] row2 = that.elt[j];
                        if (!Arrays.equals(row1, row2)) {
                            return false;
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns a deep copy of this matrix.
     */
    public Object clone() {
        try {
            final PT_Matrix copy = (PT_Matrix) super.clone();
            if (copy.elt != null) {
                copy.elt = (double[][]) copy.elt.clone();
                for (int i=copy.elt.length; --i>=0;) {
                    if (copy.elt[i] != null) {
                        copy.elt[i] = (double[]) copy.elt[i].clone();
                    }
                }
            }
            return copy;
        } catch (CloneNotSupportedException exception) {
            // Should not happen, since we are cloneable.
            throw new InternalError(exception.getMessage());
        }
    }

    /**
     * Returns a string representation of this matrix.
     * The returned string is implementation dependent.
     * It is usually provided for debugging purposes only.
     */
    public String toString() {
        final StringBuffer buffer = new StringBuffer("PT_Matrix");
        buffer.append('[');
        if (elt != null) {
            for (int j=0; j<elt.length; j++) {
                if (j!=0) buffer.append(", ");
                buffer.append('[');
                final double[] row=elt[j];
                if (row != null) {
                    for (int i=0; i<row.length; i++) {
                        if (i!=0) buffer.append(", ");
                        buffer.append(row[i]);
                    }
                }
                buffer.append(']');
            }
        }
        buffer.append(']');
        return buffer.toString();
    }
}
