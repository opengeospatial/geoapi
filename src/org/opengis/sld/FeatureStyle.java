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

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Represents a style that applies to the features of a given type.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/02-070.pdf">Implementation specification 1.0</A>
 * @since 1.1
 */
@UML (identifier="FeatureTypeStyle", specification=OGC_02_070)
public interface FeatureStyle {
    /**
     * Returns a name for this style.
     * This can be any string that uniquely identifies this style within a given
     * canvas.  It is not meant to be human-friendly.  (The "title" property is
     * meant to be human friendly.)
     */
    @UML (identifier="Name", obligation=OPTIONAL, specification=OGC_02_070)
    public String getName();

    /**
     * Sets the name for this style.
     * This can be any string that uniquely identifies this style within a given
     * canvas.  It is not meant to be human-friendly.  (The "title" property is
     * meant to be human friendly.)
     */
    @UML (identifier="Name", obligation=OPTIONAL, specification=OGC_02_070)
    public void setName(String name);

    /**
     * Returns the human readable title of this style.
     * This can be any string, but should be fairly short as it is intended to
     * be used in list boxes or drop down menus or other selection interfaces.
     */
    @UML (identifier="Title", obligation=OPTIONAL, specification=OGC_02_070)
    public String getTitle();
    
    /**
     * Sets the human readable title of this style.
     * This can be any string, but should be fairly short as it is intended to
     * be used in list boxes or drop down menus or other selection interfaces.
     */
    @UML (identifier="Title", obligation=OPTIONAL, specification=OGC_02_070)
    public void setTitle(String title);

    /**
     * Returns a human readable, prose description of this style.
     * This can be any string and can consist of any amount of text.
     */
    @UML (identifier="Abstract", obligation=OPTIONAL, specification=OGC_02_070)
    public String getAbstract();
    
    /**
     * Sets the human readable, prose description of this style.
     * This can be any string and can consist of any amount of text.
     */
    @UML (identifier="Abstract", obligation=OPTIONAL, specification=OGC_02_070)
    public void setAbstract(String abs);

    /**
     * Returns the name of the feature type that this style is meant to act
     * upon.  This may return null if a style can operate on many different
     * feature types.
     */
    @UML (identifier="FeatureTypeName", obligation=OPTIONAL, specification=OGC_02_070)
    public String getFeatureTypeName();

    /**
     * Sets the name of the feature type that this style is meant to act upon.
     * This may be set to null if the style can operate on many different
     * feature types.
     */
    @UML (identifier="FeatureTypeName", obligation=OPTIONAL, specification=OGC_02_070)
    public void setFeatureTypeName(String featureTypeName);

    /**
     * Returns a string that identifies the more general "type" of geometry
     * that this style is meant to act upon.
     * In the current OGC specifications, this is an experimental element and
     * can take only one of the following values:
     * <ul>
     *   <li><code>generic:point</code></li>
     *   <li><code>generic:line</code></li>
     *   <li><code>generic:polygon</code></li>
     *   <li><code>generic:text</code></li>
     *   <li><code>generic:raster</code></li>
     *   <li><code>generic:any</code></li>
     * </ul>
     */
    @UML (identifier="SemanticTypeIdentifier", obligation=OPTIONAL, specification=OGC_02_070)
    public String[] getSemanticTypeIdentifiers();

    /**
     * This sets the string array that identifies the more general "type" of geometry
     * that this style is meant to act upon.
     * In the current OGC specifications, this is an experimental element and
     * can take only one of the following values:
     * <ul>
     *   <li><code>generic:point</code></li>
     *   <li><code>generic:line</code></li>
     *   <li><code>generic:polygon</code></li>
     *   <li><code>generic:text</code></li>
     *   <li><code>generic:raster</code></li>
     *   <li><code>generic:any</code></li>
     * </ul>
     */
    @UML (identifier="SemanticTypeIdentifier", obligation=OPTIONAL, specification=OGC_02_070)
    public void setSemanticTypeIdentifiers(String sti[]);

    /**
     * Returns the list of rules contained by this style. The returned list is
     * the "live" list and can be modified, both by adding and removing rules.
     * (This is why there is no {@code setRules} method.
     */
    public List<Rule> getRules();
}
