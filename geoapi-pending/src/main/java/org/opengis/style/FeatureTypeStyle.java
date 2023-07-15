/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2023 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.opengis.style;

import java.util.List;
import java.util.Set;
import org.opengis.annotation.XmlElement;
import org.opengis.feature.Feature;
import org.opengis.util.GenericName;
import org.opengis.filter.ResourceId;
import org.opengis.metadata.citation.OnlineResource;


/**
 * Defines the styling that is to be applied to a single feature type.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @author Chris Dillard (SYS Technologies)
 */
@XmlElement("FeatureTypeStyle")
public interface FeatureTypeStyle {
    /**
     * Returns a name for this style.
     * This can be any string that uniquely identifies this style within a given canvas.
     * It is not meant to be human-friendly. For a human-friendly label,
     * see the {@linkplain Description#getTitle() title} instead.
     *
     * @return a name for this style.
     */
    @XmlElement("Name")
    String getName();

    /**
     * Returns the description of this style.
     *
     * @return description with usual information used for user interfaces.
     */
    @XmlElement("Description")
    Description getDescription();

    /**
     * Returns an identification of feature instances on which to apply the style.
     * This method enable the possibility to use a feature type style on a given list
     * of features only, instead of all instances of the feature type.
     *
     * @return identification of the feature instances.
     */
    ResourceId<Feature> getFeatureInstanceIDs();

    /**
     * Returns the names of the feature type that this style is meant to act upon.
     * It is allowed to be empty but only if the feature type can be inferred by other means,
     * for example from context or using {@link SemanticType} identifiers.
     *
     * <p>
     * In OGC Symbology Encoding define this method to return a single
     * String, and ISO 19117 use a Collection of String. We've chosen
     * ISO because it is more logic that a featureTypeStyle can be applied
     * to multiple featuretypes and not limited to a single one.
     * </p>
     *
     * @return the name of the feature type that this style is meant to act upon.
     */
    @XmlElement("FeatureTypeName")
    Set<GenericName> featureTypeNames();

    /**
     * Returns the most general types of geometry that this style is meant to act upon.
     * The syntax is currently undefined, but the following values are reserved to indicate
     * that the style applies to feature with default geometry of specific type:
     *
     * <ul>
     *   <li>{@code generic:point}</li>
     *   <li>{@code generic:line}</li>
     *   <li>{@code generic:polygon}</li>
     *   <li>{@code generic:text}</li>
     *   <li>{@code generic:raster}</li>
     *   <li>{@code generic:any}</li>
     * </ul>
     *
     * @return the types of geometry that this style is meant to act upon.
     */
    @XmlElement("SemanticTypeIdentifier")
    Set<SemanticType> semanticTypeIdentifiers();

    /**
     * Returns the list of rules contained by this style.
     * Order matter: the first item in a list will be the
     * first item plotted and hence appears on the bottom.
     *
     * @return the ordered list of rules. Cannot be null but can be empty.
     */
    @XmlElement("Rule")
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
