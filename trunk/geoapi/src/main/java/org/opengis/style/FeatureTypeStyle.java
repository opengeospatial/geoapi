/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2008 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.style;

import java.util.Collection;
import java.util.List;
import org.opengis.annotation.UML;
import org.opengis.annotation.XmlElement;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;

/**
 * Represents a style that applies to features or coverage.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.2
 */
@XmlElement("FeatureTypeStyle")
@UML(identifier="PF_FeaturePortrayal", specification=ISO_19117)
public interface FeatureTypeStyle {

    /**
     * Defaults semantic identifiers.
     * See {@link #getSemanticTypeIdentifiers} for details.
     */
    public static final String SEMANTIC_ID_LINE = "generic:line";
    public static final String SEMANTIC_ID_POLYGON = "generic:polygon";
    public static final String SEMANTIC_ID_POINT = "generic:point";
    public static final String SEMANTIC_ID_TEXT = "generic:text";
    public static final String SEMANTIC_ID_RASTER = "generic:raster";
    public static final String SEMANTIC_ID_ANY = "generic:any";


    /**
     * Returns a name for this style.
     * This can be any string that uniquely identifies this style within a given
     * canvas.  It is not meant to be human-friendly.  (The "title" property is
     * meant to be human friendly.)
     * @return a name for this style.
     */
    @XmlElement("Name")
    String getName();

    /**
     * Returns the description of this style.
     *
     * @return Description with usual informations used
     * for user interfaces.
     */
    @XmlElement("Description")
    @UML(identifier="description", obligation=OPTIONAL, specification=ISO_19117)
    Description getDescription();

    /**
     * Returns a collection of Object identifying features object.
     * 
     * <p>
     * ISO 19117 extends FeatureTypeStyle be providing this method.
     * This method enable the possibility to use a feature type style
     * on a given list of features only, which is not possible in OGC SE.
     * </p>
     * 
     * @return Collection<String>
     */
    @UML(identifier="definedForInst", obligation=OPTIONAL, specification=ISO_19117)
    Collection<Object> featureInstanceIDs();
    
    /**
     * <p>
     * Returns the names of the feature type that this style is meant to act
     * upon.  
     * </p>
     * <p>
     * In OGC Symbology Encoding define this method to return a single
     * String, and ISO 19117 use a Collection of String. We've choosen
     * ISO because it is more logic that a featureTypeStyle can be applied
     * to multiple featuretypes and not limited to a single one. 
     * </p>
     * 
     * @return the name of the feature type that this style is meant
     * to act upon.
     */
    @XmlElement("FeatureTypeName")
    @UML(identifier="definedFor", obligation=OPTIONAL, specification=ISO_19117)
    Collection<String> featureTypeNames();

    /**
     * Returns a string that identifies the more general "type" of geometry
     * that this style is meant to act upon.
     * In the current OGC SE specifications, this is an experimental element and
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
     *
     */
    @XmlElement("SemanticTypeIdentifier")
    Collection<String> semanticTypeIdentifiers();

    /**
     * Returns the list of rules contained by this style.
     *
     * @return the list of rules. can not be null but can be empty.
     */
    @XmlElement("Rule")
    @UML(identifier="portrayalRule", obligation=MANDATORY, specification=ISO_19117)
    List<Rule> rules();
    
    /**
     * calls the visit method of a StyleVisitor
     *
     * @param visitor the style visitor
     */
    void accept(StyleVisitor visitor);
    
}
