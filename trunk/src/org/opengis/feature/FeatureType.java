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

import java.util.List;

public interface FeatureType {
    /**
     * Returns the name of this FeatureType.
     */
    public QName getName();

    /**
     * Returns an array of FeatureAttributeDescriptor instances that lists all
     * of the attributes that a Feature of this type will have.
     */
    public FeatureAttributeDescriptor [] getAttributeDescriptors();

    /**
     * Returns the descriptor of the shape that should be used for "default"
     * drawing of features of this type.  This may only be null when features of
     * this type do not have geometric attributes.
     */
    public FeatureAttributeDescriptor getDefaultShapeAttribute();

    /**
     * Returns true if features of this type can be cast to FeatureCollection.
     */
    public boolean isCollectionType();

    /**
     * Returns the FeatureTypes that could potentially be child Features of
     * this feature type.  Each returned element may in turn have child 
     * <code>FeatureType</code> instances of its own.
     */
    public List/*<FeatureType>*/ getChildTypes();

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
     * collection that is backed by a data store.
     *
     * @throws UnsupportedOperationException Throws this exception if this
     *   feature type does not support the creation of new instances.
     */
    public Feature createFeature() throws UnsupportedOperationException;
}
