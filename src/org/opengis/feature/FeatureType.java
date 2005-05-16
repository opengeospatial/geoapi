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

// J2SE direct dependencies
import java.util.List;

// OpenGIS direct dependencies
import org.opengis.util.GenericName;
import org.opengis.util.LocalName;   // For javadoc


/**
 *
 * @since GeoAPI 1.1
 */
public interface FeatureType {
    /**
     * Returns the name of this {@code FeatureType}.
     * <p>
     * The typical usage of these {@code GenericName}s will be as follows:  In most
     * cases, the {@code GenericName} will have a local part that is be the name of the
     * XML element used to encode such features as GML.  The scope of the name
     * will either be null (if the XML element is to have no namespace) or will
     * be a {@link LocalName} whose {@link LocalName#toString() toString()} gives
     * the URI of an XML namespace.
     */
    GenericName getName();

    /**
     * In cases where features are to be encoded as GML, the namespace portion
     * of the name of the type must be mapped to a prefix in an "xmlns" XML
     * attribute.  If the data provider desires to do so, he may return a
     * prefix from this method to indicate a preference for this mapping.  It
     * is also valid to return null, indicating that the data provider doesn't
     * know or doesn't care.
     */
    String getPreferredPrefix();

    /**
     * Returns an array of descriptors that lists all of the attributes that
     * a {@link Feature} of this type will have.
     */
    FeatureAttributeDescriptor[] getAttributeDescriptors();

    /**
     * Returns the descriptor of the shape that should be used for "default"
     * drawing of features of this type.  This may only be null when features of
     * this type do not have geometric attributes.
     */
    FeatureAttributeDescriptor getDefaultShapeAttribute();

    /**
     * Returns true if features of this type can be cast to {@link FeatureCollection}.
     */
    boolean isCollectionType();

    /**
     * Returns the {@link FeatureType}s that could potentially be child {@link Feature}s of
     * this feature type.  Each returned element may in turn have child 
     * {@code FeatureType} instances of its own.
     */
    List<FeatureType> getChildTypes();

    /**
     * Returns a new, unpopulated instance of this type of {@link Feature}.
     * When the object is returned, all of its attributes are null.
     * <p>
     * The returned object will be an instance of {@link FeatureCollection}
     * if and only if the {@link #isCollectionType} method returns {@code true}.
     * <p>
     * The {@code Feature} that is returned is not persisted or displayed
     * until the caller takes further action, such as adding the feature to a
     * collection that is backed by a data store.
     *
     * @throws UnsupportedOperationException If this feature type does not
     *   support the creation of new instances.
     */
    Feature createFeature() throws UnsupportedOperationException;
}
