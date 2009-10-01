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
package org.opengis.sld;

import java.util.Collection;

import java.util.List;

import org.opengis.feature.Feature;
import org.opengis.feature.type.Name;
import org.opengis.filter.Filter;
import org.opengis.metadata.citation.OnLineResource;
import org.opengis.style.Style;


/**
 * Factory used in the production of SLD objects.
 * <p>
 * This factory is responsible for the production of sld objects; where noted
 * these create methods are in agreement with the Style Layer Descriptor 1.1
 * specification.
 * 
 * This factory is pending. We need feedback from implementors before writting methods
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
     * @param online : OnLineResource, can not be null.
     */
    SLDLibrary createSLDLibrary(OnLineResource online);
    
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
    RemoteOWS createRemoteOWS(String service, OnLineResource online);
    
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
    FeatureTypeConstraint createFeatureTypeConstraint(Name name, Filter filter, List<Extent> extents);
    
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
