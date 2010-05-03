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


import org.opengis.observation.Measure;
import org.opengis.geometry.primitive.Surface;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.Obligation.*;

/**
 * A "SamplingSurface" is an identified 2-D spatial feature.
 * It may be used for various purposes, in particular for observations of cross sections through features.
 * Specialized names for SamplingSurface include CrossSection, Section, Flitch, Swath, Scene, MapHorizon.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/om">Implementation specification 1.0</A>
 * @author Open Geospatial Consortium
 * @author Guilhem Legal (Geomatys)
 * @since GeoAPI 2.3
 */
@UML(identifier="SamplingSurface", specification=OGC_07022)
public interface SamplingSurface extends SpatiallyExtensiveSamplingFeature {

    /**
     * Surface area.
     */
    @UML(identifier="area", obligation=OPTIONAL, specification=OGC_07022)
    Measure getArea();

    /**
     * Geometry of the surface.
     */
    @UML(identifier="shape", obligation=MANDATORY, specification=OGC_07022)
    Surface getShape();
    
}