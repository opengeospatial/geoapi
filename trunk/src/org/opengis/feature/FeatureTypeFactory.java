package org.opengis.feature;

/**
 * Interface through which new <code>FeatureType</code>s are made known to a
 * <code>FeatureCanvas</code>.
 */
public interface FeatureTypeFactory {
    /**
     * This method creates a new FeatureType given its list of attributes.  Note
     * that multiple instances of FeatureCanvas may share the same pool of
     * FeatureTypes, so it is always better to try retrieving a FeatureType with
     * <code>getFeatureTypeByName</code> before creating it.
     *
     * @param name Name of the new FeatureType to be created.  This value can be
     *   used to retrieve the created FeatureType later by calling
     *   <code>getFeatureTypeByName</code>.
     * @param attributes Array of attributes (created using the
     *   <code>createAttributeDescriptor</code> method) that Features of this
     *   new type will have.
     * @param defaultGeometry The descriptor of the geometric attribute that
     *   will be used when styles do not explicitly specify the property name
     *   from which to retrieve Geometry.  This parameter can be null only when
     *   the feature type does not contain any geometric properties.
     * @param childFeatureTypes The types of Features that could potentially be
     *   child Features of instances of this type.  If null or of zero length,
     *   then instances of this feature type cannot have child features and will
     *   never implement the <code>FeatureCollection</code> interface.
     * @throws IllegalArgumentException If <code>name</code> is already in use
     *   or if <code>defaultGeometry</code> refers to a column that is not
     *   geometric.
     */
    public FeatureType createFeatureType(String name, FeatureAttributeDescriptor [] attributes, FeatureAttributeDescriptor defaultGeometry, FeatureType [] childFeatureTypes);

    /**
     * This method creates a new attribute descriptor for use in creating new
     * feature types.
     *
     * @param name The name of the attribute.  This name can later be used to
     *   retrieve the value of the attribute from Features.
     * @param dataType The underlying data type of the attribute.  This must
     *   be one of the static constants from the DataType class.
     * @param size The size of the attribute.  The interpretation of "size"
     *   depends on the data type.  See the documentation of the DataType class.
     * @param precision For decimal attributes, specified the number of digits
     *   after the decimal point.  For other types of attributes, this will be
     *   ignored.
     * @param isPrimaryKey Indicates whether this attribute is part of the
     *   primary key for the feature type of which it will be a part.
     *   Composite primary keys are not prohibited by these interfaces.
     * @param objectClass If the type of the attribute is OBJECT, then this
     *   parameter indicates the Java Class of the class or interface that
     *   values of this attribute can be safely cast to.  For other types of
     *   attributes, this parameter is ignored.
     * @param minCard A non-negative integer that indicates the minimum number
     *   of occurences of this attribute.
     * @param maxCard A positive integer that indicates the maximum number of
     *   occurrences of the attribute on this feature.  Most FeatureCanvases
     *   will only support a value of 1 for this field.  A value of -1 indicates
     *   that the maximum number of occurrences is unbounded.  When this value
     *   is greater than one, <code>Feature.getAttribute()</code> will always
     *   return an instance of <code>java.util.Collection</code>.
     */
    public FeatureAttributeDescriptor createAttributeDescriptor(String name, DataType dataType, int size, int precision, boolean isPrimaryKey, Class objectClass, int minCard, int maxCard);

    /**
     * Retrieves a previously created FeatureType given its name.  Note that
     * multiple instances of FeatureCanvas may share the same pool of
     * FeatureTypes, so this may return a FeatureType that was created for
     * another FeatureCanvas.
     */
    public FeatureType getFeatureTypeByName(String name);

    public FeatureType [] getAllFeatureTypes();
}
