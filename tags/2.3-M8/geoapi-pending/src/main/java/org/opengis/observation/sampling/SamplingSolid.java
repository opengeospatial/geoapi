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
import org.opengis.geometry.primitive.Solid;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.Obligation.*;

/**
 * A "SamplingSolid" is an identified 3-D spatial feature used in sampling.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/om">Implementation specification 1.0</A>
 * @author Open Geospatial Consortium
 * @author Guilhem Legal (Geomatys)
 * @since GeoAPI 2.3
 */
@UML(identifier="SamplingSolid", specification=OGC_07022)
public interface SamplingSolid extends SpatiallyExtensiveSamplingFeature {

    /**
     * Volume of the 3d solid.
     */
    @UML(identifier="volume", obligation=OPTIONAL, specification=OGC_07022)
    Measure getVolume();

    /**
     * Geometry of the sampling solid.
     */
    @UML(identifier="shape", obligation=MANDATORY, specification=OGC_07022)
    Solid getShape();

}