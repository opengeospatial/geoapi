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

import org.opengis.geometry.Geometry;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.Obligation.*;

/**
 * A locatedSpecimen is a specialization of specimen that carries it's location 
 * informations.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/om">Implementation specification 1.0</A>
 * @author Open Geospatial Consortium
 * @author Guilhem Legal (Geomatys)
 * @since GeoAPI 2.3
 */
@UML(identifier="LocatedSpecimen", specification=OGC_07022)
public interface LocatedSpecimen extends Specimen {

    /**
     * Sampling location may be provided directly if not available through its association
     * with either the sampledFeature or a relatedSamplingFeature.
     * 
     * @return Geometry : the specimen location geometry.
     */
    @UML(identifier="samplingLocation", obligation=MANDATORY, specification=OGC_07022)
    Geometry getSamplingLocation();

}