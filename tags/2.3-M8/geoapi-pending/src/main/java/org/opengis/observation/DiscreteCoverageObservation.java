/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/package org.opengis.observation;

import org.opengis.coverage.DiscreteCoverage;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.Obligation.*;

/**
 * A DiscreteCoverageObservation is an observation whose feature of interest
 * is the larger feature, and within which the result elements geometry
 * describe its spatio-temporal decomposition.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/om">Implementation specification 1.0</A>
 * @author Open Geospatial Consortium
 * @author Guilhem Legal (Geomatys)
 * @since GeoAPI 2.3
 */
@UML(identifier="DiscreteCoverageObservation", specification=OGC_07022)
public interface DiscreteCoverageObservation extends Observation {

    /**
     * generalized discrete coverage which describes the distribution of a property on the feature of interest.
     */
    @UML(identifier="result", obligation=MANDATORY, specification=OGC_07022)
    DiscreteCoverage getResult();
    
}