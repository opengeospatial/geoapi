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
package org.opengis.observation;

import org.opengis.observation.coverage.DiscreteTimeInstantCoverage;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.Obligation.*;

/**
 * Specialized Observation, in which the result is a compact representation of a time-instant coverage 
 * which samples a property of the feature of interest at different times.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/om">Implementation specification 1.0</A>
 * @author Open Geospatial Consortium
 * @author Guilhem Legal (Geomatys)
 * @since GeoAPI 2.3
 */
@UML(identifier="TimeSeriesObservation", specification=OGC_07022)
public interface TimeSeriesObservation extends DiscreteCoverageObservation {
    
    /**
     * compact representation of a time-instant coverage which samples a property of the feature of interest at different times
     */
    @UML(identifier="result", obligation=MANDATORY, specification=OGC_07022)
    DiscreteTimeInstantCoverage getResult();
    
}