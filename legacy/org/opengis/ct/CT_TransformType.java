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
 * Semantic type of transform used in coordinate transformation.
 *
 * @version 1.01
 * @since   1.00
 * @author Martin Daly
 * @author Martin Desruisseaux
 *
 * @deprecated No direct replacement. Check for instance of
 *             {@link org.opengis.referencing.operation.Conversion} or
 *             {@link org.opengis.referencing.operation.Transformation} instead.
 */
@Deprecated
public class CT_TransformType implements Serializable {
    /**
     * Use <code>serialVersionUID</code> from first
     * draft for interoperability with CSS 1.00.
     */
    private static final long serialVersionUID = -2351461390133617466L;

    /**
     * Unknown or unspecified type of transform.
     */
    public static final int CT_TT_Other=0;

    /**
     * Transform depends only on defined parameters.
     * For example, a cartographic projection.
     */
    public static final int CT_TT_Conversion=1;

    /**
     * Transform depends only on empirically derived parameters.
     * For example a datum transformation.
     */
    public static final int CT_TT_Transformation=2;

    /**
     * Transform depends on both defined and empirical parameters.
     */
    public static final int CT_TT_ConversionAndTransformation=3;

    /**
     * The enum value.
     */
    public int value;

    /**
     * Construct an empty enum value. Caller
     * must initialize {@link #value}.
     */
    public CT_TransformType() {
    }

    /**
     * Construct a new enum value.
     */
    public CT_TransformType(final int value) {
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
            return ((CT_TransformType) object).value == value;
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
        final StringBuffer buffer = new StringBuffer("CT_TransformType");
        buffer.append('[');
        buffer.append(value);
        buffer.append(']');
        return buffer.toString();
    }
}
