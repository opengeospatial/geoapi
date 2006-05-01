/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.feature;

// J2SE direct dependencies
import java.util.ArrayList;
import java.util.Collection;

// OpenGIS direct dependencies
import org.opengis.util.CodeList;


/**
 * Enumeration class whose static constants give the possible data types for
 * Feature attributes.
 *
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.0
 */
public final class DataType extends CodeList<DataType> {
    /**
     * Serialization constant for compatibility with future versions.
     */
    private static final long serialVersionUID = -139746971386145305L;
    
    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final Collection<DataType> VALUES = new ArrayList<DataType>(8);
    
    /**
     * Constant to be used when the value of an attribute is stored as a binary
     * integer.  The "size" of such as attribute should be interpreted as the
     * number of bytes used to store the integer.  Some systems may require that
     * the size be one of 1, 2, 4, or 8.
     */
    public static final DataType INTEGER  = new DataType("INTEGER");
    
    /**
     * Constant to be used when the value of an attribute is stored as a
     * string containing the decimal representation of a number.  The "size"
     * of such an attribute should be interpreted as the maximum number of
     * digits (including the negative sign and decimal point as digits) in
     * the value.  The "precision" should be interpreted as the maximum
     * number of digits after the decimal point.
     */
    public static final DataType DECIMAL  = new DataType("DECIMAL");
    
    /**
     * Constant to be used when the value of an attribute is stored as a
     * binary floating point value (such as IEEE-754).  The "size" of such an
     * attribute should be interpreted as the number of bytes used to store
     * the number.  Some systems may require that the size be either 4 or 8.
     */
    public static final DataType DOUBLE   = new DataType("DOUBLE");
    
    /**
     * Constant to be used when the value of an attribute is stored as a
     * string of arbitrary characters.  The "size" of such an attribute should
     * be interpreted as the maximum number of characters that can be stored in
     * the given attribute.
     */
    public static final DataType STRING   = new DataType("STRING");
    
    /**
     * Constant to be used when the value of an attribute is a date/time.
     */
    public static final DataType DATETIME = new DataType("DATETIME");
    
    /**
     * Constant to be used when the value of an attribute is a Java object that
     * represents some complex type.  Note that some consumers of {@link Feature}
     * will likely not be able to deal with complex types and will simply use the
     * {@link Object#toString} method of this object to store the value as a String.
     */
    public static final DataType OBJECT   = new DataType("OBJECT");
    
    /**
     * Constant to be used when the value of an attribute is a geometry of some
     * kind.  Such a value must be one of the allowed subclasses of
     * {@link org.opengis.spatialschema.geometry.Geometry}.
     * See the package documentation for {@link org.opengis.feature} for
     * more details on the allowed geometries.
     */
    public static final DataType GEOMETRY = new DataType("GEOMETRY");
    
    /**
     * Constant to be used when the value of an attribute is an instance of
     * {@link org.opengis.sld.FeatureStyle}.  Such an attribute may be
     * used in the portrayal of the geometry contained by a feature.
     */
    public static final DataType STYLE    = new DataType("STYLE");
    
    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    private DataType(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of {@code DataType}s.
     */
    public static DataType[] values() {
        synchronized (VALUES) {
            return (DataType[]) VALUES.toArray(new DataType[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{DataType}*/ CodeList[] family() {
        return values();
    }
}
