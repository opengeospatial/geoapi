/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.spatialschema.geometry;

// J2SE directdependencies
import java.util.List;
import java.util.ArrayList;

// OpenGIS direct dependencies
import org.opengis.util.CodeList;


/**
 * The type of {@linkplain PrecisionModel precision model}.
 *
 * @author Jody Garnett
 * @since GeoAPI 2.1
 */
public class PrecisionModelType extends CodeList<PrecisionModelType> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -2771887290382853282L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<PrecisionModelType> VALUES = new ArrayList<PrecisionModelType>(3);

    /**
     * Fixed Precision indicates that coordinates have a fixed number of decimal places.
     */
    public static final PrecisionModelType FIXED = new PrecisionModelType("FIXED");

    /**
     * Floating precision corresponds to the standard Java double-precision floating-point
     * representation, which is based on the IEEE-754 standard.
     */
    public static final PrecisionModelType FLOATING = new PrecisionModelType("FLOATING");

    /**
     * Floating single precision corresponds to the standard Java single-precision
     * floating-point representation, which is based on the IEEE-754 standard.
     */
    public static final PrecisionModelType FLOATING_SINGLE = new PrecisionModelType("FLOATING_SINGLE");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    public PrecisionModelType(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code PrecisionModelType}s.
     */
    public static PrecisionModelType[] values() {
        synchronized (VALUES) {
            return (PrecisionModelType[]) VALUES.toArray(new PrecisionModelType[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{PrecisionModelType}*/ CodeList[] family() {
        return values();
    }
}
