package org.opengis.feature;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.Arrays;

/**
 * Enumeration class whose static constants give the possible data types for
 * Feature attributes.
 *
 * @author Chris Dillard
 */
public final class DataType implements Comparable, Serializable, Cloneable {
    
    private int code;
    
    private String name;

    /**
     * Used internally to instantiate new types.
     */
    private DataType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * Returns a short name that can be used to identify a particular instance
     * of this enumeration class.
     */
    public String toString() {
        return name;
    }

    /**
     * Returns a reasonable hash code for this object if it is to be used in a
     * key in a Map.
     */
    public int hashCode() {
        return code;
    }

    /**
     * Compares this object to another object of the same type.
     *
     * @throws ClassCastException Throws this exception if the given object is
     *   not also a DataType instance.
     */
    public int compareTo(Object obj) {
        DataType other = (DataType) obj;
        if (code == other.code) {
            return 0;
        }
        else {
            if (code < other.code) {
                return -1;
            }
            else
            {
                return 1;
            }
        }
    }

    /**
     * Returns an exact copy of this object.  Since instances of this class are
     * immutable, this actually just returns this object.
     */
    public Object clone() {
        return this;
    }

    /**
     * Replace de-serialized objects with one of the static constants so that
     * there aren't multiple copies floating around with the same code.  This
     * method is invoked by Java's serialization code.  This also eliminates the
     * need for an equals() method.
     */
    private Object readResolve() throws ObjectStreamException {
        int index = Arrays.binarySearch(ALL, this);
        return ALL[index];
    }

    /**
     * Constant to be used when the value of an attribute is stored as a binary
     * integer.  The "size" of such as attribute should be interpreted as the
     * number of bytes used to store the integer.  Some systems may require that
     * the size be one of 1, 2, 4, or 8.
     */
    public static final DataType INTEGER  = new DataType(0, "INTEGER");

    /**
     * Constant to be used when the value of an attribute is stored as a
     * string containing the decimal representation of a number.  The "size"
     * of such an attribute should be interpreted as the maximum number of
     * digits (including the negative sign and decimal point as digits) in
     * the value.  The "precision" should be interpreted as the maximum
     * number of digits after the decimal point.
     */
    public static final DataType DECIMAL  = new DataType(1, "DECIMAL");

    /**
     * Constant to be used when the value of an attribute is stored as a
     * binary floating point value (such as IEEE-754).  The "size" of such an
     * attribute should be interpreted as the number of bytes used to store
     * the number.  Some systems may require that the size be either 4 or 8.
     */
    public static final DataType DOUBLE   = new DataType(2, "DOUBLE");

    /**
     * Constant to be used when the value of an attribute is stored as a
     * string of arbitrary characters.  The "size" of such an attribute should
     * be interpreted as the maximum number of characters that can be stored in
     * the given attribute.
     */
    public static final DataType STRING   = new DataType(3, "STRING");

    /**
     * Constant to be used when the value of an attribute is a date/time.
     */
    public static final DataType DATETIME = new DataType(4, "DATETIME");

    /**
     * Constant to be used when the value of an attribute is a Java object that
     * represents some complex type.  Note that some consumers of Feature will
     * likely not be able to deal with complex types and will simply use the
     * toString method of this object to store the value as a String.
     */
    public static final DataType OBJECT   = new DataType(50, "OBJECT");

    /**
     * Constant to be used when the value of an attribute is a geometry of some
     * kind.  Such a value must be one of the allowed subclasses of GM_Object.
     * See the package documentation for <code>org.opengis.feature</code> for
     * more details on the allowed geometries.
     */
    public static final DataType GEOMETRY = new DataType(100, "GEOMETRY");

    /**
     * Constant to be used when the value of an attribute is an instance of
     * <code>org.opengis.sld.FeatureTypeStyle</code>.  Such an attribute may be
     * used in the portrayal of the geometry contained by a feature.
     */
    public static final DataType STYLE    = new DataType(101, "STYLE");

    /**
     * Internal array used during deserialization to replace objects with one of
     * the static constants.
     */
    private static final DataType [] ALL = new DataType [] {
        INTEGER, DECIMAL, DOUBLE, STRING,
        GEOMETRY, STYLE
    };
}
