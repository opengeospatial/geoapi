/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.feature;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.opengis.util.CodeList;

/**
 * Enumeration class whose static constants give the possible data types for
 * Feature attributes.
 *
 * @author Chris Dillard
 */
public final class DataType extends CodeList<DataType> implements Serializable, Cloneable {
    /**
	 * Serialization constant for compatibility with future versions.
	 */
	private static final long serialVersionUID = 1L;

	private static final Collection<DataType> VALUES;
	private static CodeList [] VALUES_ARRAY = null;

	static {
		VALUES = new ArrayList<DataType>();
	}

	/**
     * Used internally to instantiate new types.
     */
    private DataType(String name) {
		super(name, VALUES);
    }

    /**
     * Returns a reasonable hash code for this object if it is to be used in a
     * key in a Map.
     */
    public int hashCode() {
        return ordinal();
    }

    /**
     * Returns an exact copy of this object.  Since instances of this class are
     * immutable, this actually just returns this object.
     */
    public /*{DataType}*/ Object clone() {
        return this;
    }

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
     * represents some complex type.  Note that some consumers of Feature will
     * likely not be able to deal with complex types and will simply use the
     * toString method of this object to store the value as a String.
     */
    public static final DataType OBJECT   = new DataType("OBJECT");

    /**
     * Constant to be used when the value of an attribute is a geometry of some
     * kind.  Such a value must be one of the allowed subclasses of GM_Object.
     * See the package documentation for <code>org.opengis.feature</code> for
     * more details on the allowed geometries.
     */
    public static final DataType GEOMETRY = new DataType("GEOMETRY");

    /**
     * Constant to be used when the value of an attribute is an instance of
     * <code>org.opengis.sld.FeatureStyle</code>.  Such an attribute may be
     * used in the portrayal of the geometry contained by a feature.
     */
    public static final DataType STYLE    = new DataType("STYLE");

	@Override
	public CodeList[] family() {
		if (VALUES_ARRAY == null) {
			CodeList [] result = new CodeList[VALUES.size()];
			VALUES.toArray(result);
			VALUES_ARRAY = result;
		}
		return VALUES_ARRAY;
	}
}
