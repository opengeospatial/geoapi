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

import org.opengis.spatialschema.geometry.Envelope;

public interface Feature {
    /**
     * Returns the description of this feature's type.
     */
    public FeatureType getFeatureType();

    /**
     * Returns the value of the named attribute of this <code>Feature</code>.
     * If the maximum cardinality of this attribute is one, then this method
     * returns the value of the attribute.  Otherwise, if the maximum
     * cardinality of this attribute is greater than one, then this method will
     * return an instance of <code>java.util.Collection</code>.
     *
     * @param name The name of the Feature attribute to retrieve.
     *
     * @throws IllegalArgumentException If an attribute of the given name does
     *   not exist in this feature's type.
     */
    public Object getAttribute(String name);

    public Envelope getBounds();

    /**
     * Returns the value of the indexed attribute of this <code>Feature</code>.
     * If the maximum cardinality of this attribute is one, then this method
     * returns the value of the attribute.  Otherwise, if the maximum
     * cardinality of this attribute is greater than one, then this method will
     * return an instance of <code>java.util.Collection</code>.
     *
     * @param index The index of the Feature attribute to retrieve.  This index
     *   is the same as the index of the corresponding <code>FeatureAttributeType</code>
     *   in the array of <code>FeatureAttributeType</code>s returned by
     *   <code>FeatureType.getAttributeDescriptors()</code>.
     *
     * @throws IndexOutOfBoundsException If the index is negative or greater than
     *   the number of possible attributes minus one.
     */
    public Object getAttribute(int index);

    /**
     * This sets the value of the named attribute.  The value can either be a
     * single object, if the maximum cardinality of the given attribute is one,
     * or the value can be an instance of <code>java.lang.Collection</code> if
     * the attribute's maximum cardinality is greater than one.
     *
     * @param name The name of the attribute whose value to set.
     * @param value The new value of the given attribute.
     *
     * @throws IllegalArgumentException if you passed a Collection and it's a
     *   single-valued attribute, or if the given name does not match any of the
     *   attributes of this feature.
     *
     * @throws ClassCastException This exception may be thrown if the attribute 
     *   type is a type other than <code>Object</code> in the <code>FeatureType</code>
     *   and an incorrect type is passed in.
     */
    public void setAttribute(String name, Object value);

    /**
     * This sets the value of the given attribute.  The value can either be a
     * single object, if the maximum cardinality of the given attribute is one,
     * or the value can be an instance of <code>java.lang.Collection</code> if
     * the attribute's maximum cardinality is greater than one.
     *
     * @param index Zero based index of the attribute to set.
     *
     * @throws IllegalArgumentException if you passed a Collection and it's a
     *   single-valued attribute.
     *
     * @throws IndexOutOfBoundsException if the index is negative or greater than the number
     *   of attributes of this feature minute one.
     */
    public void setAttribute(int index, Object value);

    /**
     * This method returns a String that uniquely identifies this Feature
     * instance with this Java virtual machine (and perhaps uniquely in a
     * broader scope as well).  This value is not necessarily one of the
     * attributes of this feature.  Some features may implement this method by
     * concatenating this feature's type name with the String values of all of
     * the primary key attributes.  (This is only a suggestion, however, and
     * a given Feature implementation may choose to compute the ID in whatever
     * way makes sense.)
     */
    public String getID();

    /**
     * Returns the collection in which we are contained.
     */
    public FeatureCollection getParent();
}
