/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.sld;

// J2SE direct dependencies
import java.util.List;

// OpenGIS direct dependencies
import org.opengis.util.InternationalString;

// Annotations
import org.opengis.annotation.XmlElement;
import static org.opengis.annotation.Obligation.*;


/**
 * Represents a style that applies to the features of a given type.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/02-070.pdf">Implementation specification 1.0</A>
 * @since GeoAPI 1.1
 */
@XmlElement(name="FeatureTypeStyle")
public interface FeatureStyle {
    /**
     * Returns a name for this style.
     * This can be any string that uniquely identifies this style within a given
     * canvas.  It is not meant to be human-friendly.  (The "title" property is
     * meant to be human friendly.)
     */
    @XmlElement(name="Name", obligation=OPTIONAL)
    public String getName();

    /**
     * Sets the name for this style.
     *
     * @see #getName
     */
    @XmlElement(name="Name", obligation=OPTIONAL)
    public void setName(String name);

    /**
     * Returns the human readable title of this style.
     * This can be any string, but should be fairly short as it is intended to
     * be used in list boxes or drop down menus or other selection interfaces.
     */
    @XmlElement(name="Title", obligation=OPTIONAL)
    public InternationalString getTitle();
    
    /**
     * Sets the human readable title of this style.
     *
     * @see #getTitle
     */
    @XmlElement(name="Title", obligation=OPTIONAL)
    public void setTitle(InternationalString title);

    /**
     * Returns a human readable, prose description of this style.
     * This can be any string and can consist of any amount of text.
     */
    @XmlElement(name="Abstract", obligation=OPTIONAL)
    public InternationalString getAbstract();
    
    /**
     * Sets the human readable, prose description of this style.
     *
     * @see #getAbstract
     */
    @XmlElement(name="Abstract", obligation=OPTIONAL)
    public void setAbstract(InternationalString abs);

    /**
     * Returns the name of the feature type that this style is meant to act
     * upon.  This may return null if a style can operate on many different
     * feature types.
     */
    @XmlElement(name="FeatureTypeName", obligation=OPTIONAL)
    public String getFeatureTypeName();

    /**
     * Sets the name of the feature type that this style is meant to act upon.
     *
     * @see #getFeatureTypeName
     */
    @XmlElement(name="FeatureTypeName", obligation=OPTIONAL)
    public void setFeatureTypeName(String featureTypeName);

    /**
     * Returns a string that identifies the more general "type" of geometry
     * that this style is meant to act upon.
     * In the current OGC specifications, this is an experimental element and
     * can take only one of the following values:
     * <p>
     * <ul>
     *   <li><code>generic:point</code></li>
     *   <li><code>generic:line</code></li>
     *   <li><code>generic:polygon</code></li>
     *   <li><code>generic:text</code></li>
     *   <li><code>generic:raster</code></li>
     *   <li><code>generic:any</code></li>
     * </ul>
     * <p>
     * The returned list is the "live" list and can be modified, both by adding and
     * removing rules. This is why there is no {@code setSemanticTypeIdentifiers} method.
     */
    @XmlElement(name="SemanticTypeIdentifier", obligation=OPTIONAL)
    public List<String> getSemanticTypeIdentifiers();

    /**
     * Returns the list of rules contained by this style. The returned list is
     * the "live" list and can be modified, both by adding and removing rules.
     * This is why there is no {@code setRules} method.
     */
    public List<Rule> getRules();
}
