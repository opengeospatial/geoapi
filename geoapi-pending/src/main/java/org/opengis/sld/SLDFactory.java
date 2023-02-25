/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2008-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
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
package org.opengis.sld;

import java.util.Collection;

import java.util.List;

import org.opengis.feature.Feature;
import org.opengis.util.GenericName;
import org.opengis.filter.Filter;
import org.opengis.metadata.citation.OnlineResource;
import org.opengis.style.Style;


/**
 * Factory used in the production of SLD objects.
 * <p>
 * This factory is responsible for the production of sld objects; where noted
 * these create methods are in agreement with the Style Layer Descriptor 1.1
 * specification.
 *
 * This factory is pending. We need feedback from implementers before writing methods
 * in this one.
 *
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
public interface SLDFactory {

    /**
     * Create an empty Style layer descriptor.
     */
    StyledLayerDescriptor createSLD();

    /**
     * Create an SLD library, an SLD library holds a online
     * reference to a SLD file.
     * @param online : OnlineResource, cannot be null.
     */
    SLDLibrary createSLDLibrary(OnlineResource online);

    /**
     * Create a default named layer.
     */
    NamedLayer createNamedLayer();

    /**
     * Create a default user layer.
     */
    UserLayer createUserLayer();

    /**
     * Create a default named style.
     */
    NamedStyle createNamedStyle();

    /**
     * Create a default User style.
     * @return Style : this object is a OGC SE Style defined in the style package.
     */
    Style createUserStyle();

    /**
     * Create a RemoteOWS information object.
     * @param service : cannot be null
     * @param online : cannot be null
     */
    RemoteOWS createRemoteOWS(String service, OnlineResource online);

    /**
     * Create a Inline feature content.
     * @param features : collection of features, can be null
     */
    InlineFeature createInLineFeature(Collection<Collection<Feature>> features);

    /**
     * Create a default layer coverage constraints.
     */
    LayerCoverageConstraints createLayerCoverageConstraints();

    /**
     * Create a default layer feature constraints.
     */
    LayerFeatureConstraints createLayerFeatureConstraints();

    /**
     * Create a coverage constraint.
     *
     * @param name : cannot be null
     * @param extent : can be null
     */
    CoverageConstraint createCoverageConstraint(String name, CoverageExtent extent);

    /**
     * Create a feature type constraint.
     *
     * @param name : can be null
     * @param filter : can be null
     * @param extents : can be null
     */
    FeatureTypeConstraint createFeatureTypeConstraint(GenericName name, Filter filter, List<Extent> extents);

    /**
     * Create a coverage extent.
     *
     * @param timeperiod : cannot be null
     */
    CoverageExtent createCoverageExtent(String timeperiod);

    /**
     * Create a coverage extent.
     *
     * @param ranges : can be null or empty
     */
    CoverageExtent createCoverageExtent(List<RangeAxis> ranges);

    /**
     * Create a feature extent.
     *
     * @param name : cannot be null
     * @param value : cannot be null
     */
    Extent createExtent(String name, String value);

    /**
     * Create a Range axis.
     *
     * @param name : cannot be null
     * @param value : cannot be null
     */
    RangeAxis createRangeAxis(String name, String value);

}
