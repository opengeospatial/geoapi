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
package org.opengis.sld;

// J2SE direct dependencies
import java.util.List;

// OpenGIS direct dependencies
import org.opengis.util.InternationalString;

// Annotations
import org.opengis.annotation.XmlElement;


/**
 * Represents a style that applies to the features of a given type.
 *
 * a.k.a FeatureTypeStyle in SLD 1.0.0
 *
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=1188">Implementation specification 1.0</A>
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.0
 */
@XmlElement("FeatureStyle")
public interface FeatureStyle {
    /**
     * Returns a name for this style.
     * This can be any string that uniquely identifies this style within a given
     * canvas.  It is not meant to be human-friendly.  (The "title" property is
     * meant to be human friendly.)
     */
    @XmlElement("Name")
    String getName();

    /**
     * Sets the name for this style.
     * See {@link #getName} for details.
     */
    @XmlElement("Name")
    void setName(String name);

    /**
     * Returns the human readable title of this style.
     * This can be any string, but should be fairly short as it is intended to
     * be used in list boxes or drop down menus or other selection interfaces.
     */
    @XmlElement("Title")
    InternationalString getTitle();
    
    /**
     * Sets the human readable title of this style.
     * See {@link #getTitle} for details.
     */
    @XmlElement("Title")
    void setTitle(InternationalString title);

    /**
     * Returns a human readable, prose description of this style.
     * This can be any string and can consist of any amount of text.
     */
    @XmlElement("Abstract")
    InternationalString getAbstract();
    
    /**
     * Sets the human readable, prose description of this style.
     * See {@link #getAbstract} for details.
     */
    @XmlElement("Abstract")
    void setAbstract(InternationalString abs);

    /**
     * Returns the name of the feature type that this style is meant to act
     * upon.  This may return null if a style can operate on many different
     * feature types.
     */
    @XmlElement("FeatureTypeName")
    String getFeatureTypeName();

    /**
     * Sets the name of the feature type that this style is meant to act upon.
     * See {@link #getFeatureTypeName} for details.
     */
    @XmlElement("FeatureTypeName")
    void setFeatureTypeName(String featureTypeName);

    /**
     * Returns a string that identifies the more general "type" of geometry
     * that this style is meant to act upon.
     * In the current OGC specifications, this is an experimental element and
     * can take only one of the following values:
     * <p>
     * <ul>
     *   <li>{@code generic:point}</li>
     *   <li>{@code generic:line}</li>
     *   <li>{@code generic:polygon}</li>
     *   <li>{@code generic:text}</li>
     *   <li>{@code generic:raster}</li>
     *   <li>{@code generic:any}</li>
     * </ul>
     * <p>
     * The returned list is the "live" list and can be modified, both by adding and
     * removing rules. This is why there is no {@code setSemanticTypeIdentifiers} method.
     */
    @XmlElement("SemanticTypeIdentifier")
    List<String> getSemanticTypeIdentifiers();

    /**
     * Returns the list of rules contained by this style. The returned list is
     * the "live" list and can be modified, both by adding and removing rules.
     * This is why there is no {@code setRules} method.
     */
    @XmlElement("Rule")
    List<Rule> getRules();
    
    /**
	 * <p>
	 * Returns the URL of a FeatureStyle XML document. This should be null if
	 * other elements are defined.
	 * </p>
	 * 
	 * <p>
	 * The OnlineResource element can be used instead of the other elements
	 * described to reference a FeatureStyle that is stored remotely. The URL
	 * given in the OnlineResource must reference a FeatureStyle XML document.
	 * This organization allows the more convenient use of feature-style
	 * libraries.
	 * </p>
	 */
    @XmlElement("OnlineResource")
    String getOnlineResource();

    /**
	 * <p>
	 * Returns the SLD version number that the FeatureStyle corresponds to. This
	 * value may be null.
	 * </p>
	 * 
	 * <p>
	 * This attribute is redundant when used with in-line feature styles, but it
	 * should be used with feature styles that are stored remotely and
	 * referenced, to allow applications to identify and handle SLD fragments
	 * that are of different versions.
	 * </p>
	 */
    String getVersion();
    
}
