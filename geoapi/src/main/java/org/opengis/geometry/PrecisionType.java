/*$************************************************************************************************
 **
 ** $Id: PrecisionModelType.java 960 2007-03-16 00:44:53Z jive $
 **
 ** $URL: https://svn.sourceforge.net/svnroot/geoapi/trunk/geoapi/src/main/java/org/opengis/spatialschema/geometry/PrecisionModelType.java $
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.geometry;

// J2SE directdependencies
import java.util.ArrayList;
import java.util.List;

import org.opengis.util.CodeList;


/**
 * The type of {@linkplain Precision precision model}.
 *
 * @author Jody Garnett
 * @since GeoAPI 2.1
 */
public class PrecisionType extends CodeList<PrecisionType> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -2771887290382853282L;

    /**
     * Indicates Precision Model uses floating point math (rather then a grid)
     */
    boolean isFloating;
    
    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List<PrecisionType> VALUES = new ArrayList<PrecisionType>(3);

    /**
     * Fixed Precision indicates that coordinates have a fixed number of decimal places.
     */
    public static final PrecisionType FIXED = new PrecisionType("FIXED", false );

    /**
     * Floating precision corresponds to the standard Java double-precision floating-point
     * representation, which is based on the IEEE-754 standard.
     */
    public static final PrecisionType DOUBLE = new PrecisionType("DOUBLE", true);

    /**
     * Floating single precision corresponds to the standard Java single-precision
     * floating-point representation, which is based on the IEEE-754 standard.
     */
    public static final PrecisionType FLOAT  = new PrecisionType("FLOAT", true);

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    public PrecisionType(final String name, boolean isFloating  ) {
        super(name, VALUES);
        this.isFloating = isFloating;        
    }

    /**
     * True if PrecisionModelType is a represented using floating point arithmatic (rather then a grid).
     * 
     * @return true if floating point arithmatic is used
     */
    public boolean isFloating(){
        return isFloating;
    }
    
    /**
     * Returns the list of {@code PrecisionModelType}s.
     */
    public static PrecisionType[] values() {
        synchronized (VALUES) {
            return (PrecisionType[]) VALUES.toArray(new PrecisionType[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{PrecisionModelType}*/ CodeList[] family() {
        return values();
    }
}
