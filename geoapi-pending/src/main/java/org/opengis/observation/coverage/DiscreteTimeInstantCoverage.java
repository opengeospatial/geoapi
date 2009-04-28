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
package org.opengis.observation.coverage;

import org.opengis.annotation.UML;
import org.opengis.coverage.DiscreteCoverage;

import static org.opengis.annotation.Specification.*;

/**
 * Specialization of ISO 19123 CV_DiscreteTimeInstantCoverage.
 * Explicit implementation of specialized CV_DiscreteCoverage in which the coverage domain is composed of time-instants.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/om">Implementation specification 1.0</A>
 * @author Open Geospatial Consortium
 * @author Guilhem Legal (Geomatys)
 * @since GeoAPI 2.3
 */
@UML(identifier="CV_DiscreteTimeInstantCoverage", specification=OGC_07022)
 public interface DiscreteTimeInstantCoverage extends DiscreteCoverage {
     
     public TimeInstantValuePair element = null;
     
}