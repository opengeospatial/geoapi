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

import org.opengis.util.GenericName;

public interface FeatureType {
    /**
     * Returns the name of this FeatureType.
     * <p>
     * The typical usage of these GenericNames will be as follows:  In most
     * cases, the GenericName will have a local part that is be the name of the
     * XML element used to encode such features as GML.  The scope of the name
     * will either be null (if the XML element is to have no namespace) or will
     * be a LocalName whose toString() gives the URI of an XML namespace.
     */
    public GenericName getName();

    /**
     * In cases where features are to be encoded as GML, the namespace portion
     * of the name of the type must be mapped to a prefix in an "xmlns" XML
     * attribute.  If the data provider desires to do so, he may return a
     * prefix from this method to indicate a preference for this mapping.  It
     * is also valid to return null, indicating that the data provider doesn't
     * know or doesn't care.
     */
    public String getPreferredPrefix();

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
