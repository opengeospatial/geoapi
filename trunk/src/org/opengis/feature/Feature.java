package org.opengis.feature;

/**
 * <p>Interface implemented by <code>FeatureCanvas</code>es as a way to allow an
 * application developer to inject feature data into a map.  User's of the 
 * feature canvas interface are expected to adapt any incoming data into
 * <code>Feature</code> objects so that they can be rendered.  The actual
 * feature implementation is hidden by the canvas so that it retains control
 * how they are instantiated and (potentially) serialized.</p>
 *
 * @author Chris Dillard, Jake Fear
 */
public interface Feature {

    /**
     * Returns the description of this feature's type.
     */
    public FeatureType getFeatureType();

    /**
     * If the maximum cardinality of this attribute is one, then this method
     * returns the value of the attribute.  Otherwise, if the maximum
     * cardinality of this attribute is greater than one, then this method will
     * return an instance of <code>java.util.Collection</code>.
     *
     * @param name The name of the Feature attribute to retrieve.
     */
    public Object getAttribute(String name);

    /**
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
     * the number of possible attributes minus one.
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
     * type is a type other than <code>Object</code> in the <code>FeatureType</code>
     * and an incorrect type is passed in.
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
     * @throws IndexOutOfBoundsException if the index is greater than the number
     *   of attributes of this feature.
     */
    public void setAttribute(int index, Object value);
}
