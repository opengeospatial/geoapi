package org.opengis.feature;

import java.util.Iterator;

import org.opengis.filter.Filter;

/**
 * Class that lists the attributes and type of child Features that a given
 * class of Features can have.  <code>FeatureType</code> objects must be insantiated first
 * in order to instantiate feature objects.  The factory for creating 
 * <code>FeatureType</code> instances is obtained from the <code>FeatureCanvas</code>.
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
     * <p>
     * The returned object will be an instance of <code>FeatureCollection</code>
     * if and only if the <code>getChildren()</code> method returns an array of
     * length greater than zero.
     * <p>
     * The <code>Feature</code> that is returned is not persisted or displayed
     * until the caller takes further action, such as adding the feature to a
     * layer or calling this type's <code>addFeature</code> method.
     *
     * @throws UnsupportedOperationException Throws this exception if this
     *   feature type does not support the creation of new instances.
     */
    public Feature createFeature() throws UnsupportedOperationException;

    /**
     * If this FeatureType supports it, this method returns an Iterator for all
     * of the Feature instances that pass the given Filter.  A null Filter is
     * interpreted as meaning "retrieve all features".
     *
     * @throws UnsupportedOperationException Throws this exception if this
     *   feature type does not support retrieving its feature instances.
     */
    public Iterator getFeatures(Filter filter) throws UnsupportedOperationException;

    /**
     * Returns true if this feature type supports the <code>addFeature</code>
     * method.
     */
    public boolean getSupportsAdd();

    /**
     * If this FeatureType supports it, this method adds the given feature's
     * data to the backing store for this feature type.
     *
     * @throws UnsupportedOperationException Throws this exception if this
     *   feature type does not support writing to a backing store.
     * @throws IllegalArgumentException Throws this exception if the feature is
     *   not of this type.
     */
    public void addFeature(Feature f) throws UnsupportedOperationException, IllegalArgumentException;

    public void removeFeature(Feature f) throws UnsupportedOperationException, IllegalArgumentException;
}
