package org.opengis.feature;

/**
 * Class that lists the attributes and type of child Features that a given
 * class of Features can have.  <code>FeatureType<code> objects must be insantiated first
 * in order to insantiate feature objects.  The factory for creating 
 * <code>FeatureType</code> intances is obtained from the <code>FeatureCanvas</code>.
 *
 * @author Chris Dillard, Jake Fear
 */
public interface FeatureType {

    /**
     * Returns the name of this FeatureType.  This is not meant to be presented
     * to the user and is probably subject to some naming restrictions.
     */
    public String getName();

    /**
     * Returns the array of FeatureAttributes that comprise the primary key
     * of this feature.
     */
    public FeatureAttributeDescriptor [] getPrimaryKeyAttributes();

    /**
     * Returns an array of FeatureAttributeDescriptor instances that lists all of
     * the attributes that a Feature of this type will have.
     */
    public FeatureAttributeDescriptor [] getAttributeDescriptors();

    /**
     * Returns the descriptor of the shape that should be used for "default"
     * drawing of features of this type.  This may only be null when features of
     * this type do not have geometric attributes.
     */
    public FeatureAttributeDescriptor getDefaultShapeAttribute();

    /**
     * Returns the FeatureTypes that could potentially be child Features of
     * this feature type.  Each returned element may in turn have child 
     * <code>FeatureType</code> instances of its own.
     */
    public FeatureType [] getChildren();

    /**
     * Returns a new, unpopulated instance of this type of Feature.  When the
     * object is returned, all of its attributes are null.
     */
    public Feature createFeature();
}
