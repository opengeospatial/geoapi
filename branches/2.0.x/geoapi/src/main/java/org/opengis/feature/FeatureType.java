/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.feature;

// J2SE direct dependencies
import java.util.List;

// OpenGIS direct dependencies
import org.opengis.util.GenericName;
import org.opengis.util.LocalName;   // For javadoc

// Annotations
import org.opengis.annotation.Extension;
import org.opengis.annotation.XmlElement;


/**
 * Describes a feature in an application namespace. A {@code FeatureType}
 * represents features as an object that contains zero or more attribute
 * objects, one of which will generally be a geometry, but no geometry and
 * multiple geometries are allowed. Note that instances of this class are
 * henceforth referred to as schemas.
 * <p>
 * With one exception, the type of an attribute is considered to be its cannonical
 * definition by the {@code FeatureType}. For example, an attribute type might be
 * a {@code javax.sound.midi.Sequence} object, which contains a {@code float} public
 * field called {@code PPQ}. The fact that this attribute exists is not known by the
 * {@code FeatureType} itself. If a caller asks this {@code FeatureType} for all of
 * its attributes, the {@code FeatureType} will tell the caller that it has an attribute
 * of type {@code javax.sound.midi.Sequence}, but not that this attribute has a sub-attribute
 * (field) called {@code PPQ}. It is the responsibility of the callers to understand
 * the objects it is asking for and manipulate them appropriately. The sole exception
 * is if the type stored in the {@code FeatureType} is a {@link Feature} type. In this
 * case, all information about sub-attributes are stored and passed to calling classes
 * upon request. The style of reference (XPath) is defined in and mediated by
 * {@code FeatureType} implementations.
 * <p>
 * It is the responsibility of the implementing class to ensure that the {@code FeatureType}
 * is always in a valid state. This means that each attribute tuple must be fully initialized
 * and valid. The minimum valid {@code FeatureType} is one with nulls for namespace, type, and
 * attributes; this is clearly a trivial case, since it is so constrained that it would not allow
 * for any feature construction. There are a few conventions of which implementers of this interface
 * must be aware in order to successfully manage a {@code FeatureType}:
 * <ul>
 *   <li><p><b>Immutability</b><br>
 *       <em>Feature types must be implemented as immutable objects!</em></p></li>
 *
 *   <li><p><b>XPath</b><br>
 *       XPath is the standard used to access all attributes (flat, nested, and multiple),
 *       via a single, unified string. Using XPath to access attributes has the convenient
 *       side-benefit of making them appear to be non-nested and non-multiple to callers with
 *       no awareness of XPath. This greatly simplifies accessing and manipulating data. However,
 *       it does put extra burden on the implementers of {@code FeatureType} to understand and
 *       correctly implement XPath pointers. Note that the {@link Feature} object does not
 *       understand XPath at all and relies on implementors of this interface to interpret
 *       XPath references. Fortunately, XPath is quite simple and has a clearly written
 *       <a href="http://www.w3.org/TR/xpath">specification</a>.</p></li>
 *
 *   <li><p><b>Feature Creation</b><br>
 *       {@code FeatureType} also must provide methods for the creation of {@link Feature}s.
 *       The creating {@code FeatureType} should check to see if the passed in objects validate
 *       against its attribute types, and if it does should return a new feature.</p></li>
 * </ul>
 *
 * @author Rob Hranac (VFNY)
 * @author Chris Holmes (TOPP)
 * @author David Zwiers (Refractions Research)
 * @author Jody Garnett (Refractions Research)
 * @since GeoAPI 2.0
 *
 * @todo The following XML elements are not yet defined in this interface:
 *       {@code Title}, {@code Abstract}, {@code Keywords}, {@code DefaultSRS},
 *       {@code OtherSRS}, {@code NoSRS}, {@code Operations}, {@code OutputFormats},
 *       {@code WGS84BoundingBox}, {@code MetadataURL}.
 */
@XmlElement("FeatureType")
public interface FeatureType {
    /**
     * Returns the name of this {@code FeatureType}, including any namespace prefix.
     * <p>
     * The typical usage of these {@code GenericName}s will be as follows:  In most
     * cases, the {@code GenericName} will have a local part that is be the name of the
     * XML element used to encode such features as GML.  The scope of the name
     * will either be null (if the XML element is to have no namespace) or will
     * be a {@link LocalName} whose {@link LocalName#toString() toString()} gives
     * the URI of an XML namespace.
     */
    @XmlElement("Name")
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
     * Returns a list of descriptors that lists all of the attributes that
     * a {@link Feature} of this type will have.
     */
    List<FeatureAttributeDescriptor> getAttributeDescriptors();

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
