/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2008-2019 Open Geospatial Consortium, Inc.
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
     * @param online : OnlineResource, can not be null.
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
     * @param service : can not be null
     * @param online : can not be null
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
     * @param name : can not be null
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
     * @param timeperiod : can not be null
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
     * @param name : can not be null
     * @param value : can not be null
     */
    Extent createExtent(String name, String value);

    /**
     * Create a Range axis.
     *
     * @param name : can not be null
     * @param value : can not be null
     */
    RangeAxis createRangeAxis(String name, String value);

}
