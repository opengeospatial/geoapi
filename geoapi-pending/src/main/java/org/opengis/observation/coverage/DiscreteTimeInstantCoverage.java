/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2009-2023 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
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
