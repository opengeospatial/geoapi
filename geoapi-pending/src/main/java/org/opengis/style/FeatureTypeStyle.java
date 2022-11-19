/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.style;

import java.util.List;
import java.util.Set;
import org.opengis.annotation.UML;
import org.opengis.annotation.XmlElement;

import org.opengis.util.GenericName;
import org.opengis.filter.ResourceId;
import org.opengis.metadata.citation.OnlineResource;
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
     * @return description with usual informations used
     * for user interfaces.
     */
    @XmlElement("Description")
    @UML(identifier="description", obligation=OPTIONAL, specification=ISO_19117)
    Description getDescription();

    /**
     * Returns an identification of features object.
     *
     * <p>
     * ISO 19117 extends FeatureTypeStyle be providing this method.
     * This method enable the possibility to use a feature type style
     * on a given list of features only, which is not possible in OGC SE.
     * </p>
     */
    @UML(identifier="definedForInst", obligation=OPTIONAL, specification=ISO_19117)
    ResourceId getFeatureInstanceIDs();

    /**
     * Returns the names of the feature type that this style is meant to act
     * upon.
     *
     * <p>
     * In OGC Symbology Encoding define this method to return a single
     * String, and ISO 19117 use a Collection of String. We've chosen
     * ISO because it is more logic that a featureTypeStyle can be applied
     * to multiple featuretypes and not limited to a single one.
     * </p>
     *
     * @return the name of the feature type that this style is meant
     * to act upon.
     */
    @XmlElement("FeatureTypeName")
    @UML(identifier="definedFor", obligation=OPTIONAL, specification=ISO_19117)
    Set<GenericName> featureTypeNames();

    /**
     * Returns a collection that identifies the more general "type" of geometry
     * that this style is meant to act upon.
     * In the current OGC SE specifications, this is an experimental element and
     * can take only one of the following values:
     *
     * <ul>
     *   <li>{@code generic:point}</li>
     *   <li>{@code generic:line}</li>
     *   <li>{@code generic:polygon}</li>
     *   <li>{@code generic:text}</li>
     *   <li>{@code generic:raster}</li>
     *   <li>{@code generic:any}</li>
     * </ul>
     */
    @XmlElement("SemanticTypeIdentifier")
    Set<SemanticType> semanticTypeIdentifiers();

    /**
     * Returns the list of rules contained by this style.
     *
     * @return the list of rules. cannot be null but can be empty.
     */
    @XmlElement("Rule")
    @UML(identifier="portrayalRule", obligation=MANDATORY, specification=ISO_19117)
    List<? extends Rule> rules();

    /**
     * It is common to have a style coming from a external xml file, this method
     * provide a way to get the original source if there is one.
     * OGC SLD specification can use this method to know if a style must be
     * written completely or if writing the online resource path is enough.
     *
     * @return OnlineResource or null
     */
    OnlineResource getOnlineResource();

    /**
     * calls the visit method of a StyleVisitor
     *
     * @param visitor the style visitor
     */
    Object accept(StyleVisitor visitor, Object extraData);
}
