/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.feature;


/**
 * Descriptor that gives the name, data type, and cardinality of an attribute
 * from a {@link FeatureType}.
 * <p>
 * <b>Author's note:</b> In the OGC Feature abstract model, attributes have both
 * a name and a type.  Then the type itself has a name and a datatype.  This is
 * apparently done so that attributes of different names, perhaps in different
 * types of Features, can share a type.  However, this seems a little confusing.
 * Here, the name of the Feature attribute and its datatype are rolled into one
 * object.
 *
 * @since GeoAPI 2.0
 */
public interface FeatureAttributeDescriptor {
    /**
     * Returns the name of this attribute.  This is the name that can be used to
     * retrieve the value of an attribute and usually maps to either an XML
     * element name or a column name in a relational database.
     */
    String getName();

    /**
     * Returns a constant from {@link DataType}.  The return value of this method
     * indicates how the return values of {@link #getSize}, {@link #getPrecision}, and
     * {@link #getObjectClass} should be interpreted.  For attributes whose maximum
     * cardinality is greater than one, this should return the data type of
     * the individual elements of the collection.
     */
    DataType getDataType();

    /**
     * Returns a number that indicates the size of a given attribute.  See the documentation
     * for the various constants of {@link DataType} for how to interpret this value.  As an
     * example, when the data type is {@link DataType#STRING STRING}, this value indicates
     * the maximum length of the string.
     */
    int getSize();

    /**
     * For attributes of type {@link DataType#DECIMAL DECIMAL}, this returns the maximum number
     * of places after the decimal point.  For other types, this must always return zero.
     */
    int getPrecision();

    /**
     * For attributes of type {@link DataType#OBJECT OBJECT}, this returns the Java {@link Class}
     * object that class or interface that all values of this attribute can be cast to.
     */
    Class getObjectClass();

    /**
     * Returns the minimum number of occurrences of this attribute on a given
     * feature.  The vast majority of data sources and data consumers will only
     * function with this value being zero or one.  If the minimum number of
     * occurrences is zero, this is equivalent, in SQL terms, to the attribute
     * being nillable.
     */
    int getMinimumOccurrences();

    /**
     * Returns the maximum number of occurrences of this attribute on a given
     * feature.  The vast majority of data sources and data consumers will only
     * function with this value being one.  A value of {@link Integer#MAX_VALUE}
     * indicates that the maximum number of occurrences is unbounded.
     */
    int getMaximumOccurrences();

    /**
     * Returns {@code true} if this attribute forms all or part of the unique identifying
     * value for the feature it is contained by.  The primary key attributes uniquely
     * identify this feature from other features of the same type.  This is different
     * from the {@linkplain Feature#getID feature's ID}, which must uniquely identify
     * the {@link Feature} among all feature types.
     */
    boolean isPrimaryKey();
}
