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
package org.opengis.observation.sampling;

import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;

/**
 * Generic interface for SamplingCurve, SamplingSurface and SamplingSolid
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/om">Implementation specification 1.0</A>
 * @author Open Geospatial Consortium
 * @author Guilhem Legal (Geomatys)
 * @since GeoAPI 2.3
 */
@UML(identifier="SpatiallyExtensiveSamplingFeature", specification=OGC_07022)
public interface SpatiallyExtensiveSamplingFeature extends SamplingFeature {
    
}