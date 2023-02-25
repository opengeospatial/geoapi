/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2009-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
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
import org.opengis.observation.*;

import static org.opengis.annotation.Specification.*;

/**
 * Specialization of ISO 19123 CV_GeometryValuePair. 
 * Explicit implementation of CV_TimeInstantValuePair - this is the temporal equivalent to CV_PointValuePair.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/om">Implementation specification 1.0</A>
 * @author Open Geospatial Consortium
 * @author Guilhem Legal (Geomatys)
 * @since GeoAPI 2.3
 */
@UML(identifier="CV_DiscreteTimeInstantCoverage", specification=OGC_07022)
public interface TimeInstantValuePair {
    
    /**
     * Implicitly xs:anyType. Use xsi:type attribute to indicate the datatype at run-time.
     */
    Object getValue();

    TimeInstant getGeometry();
    
}
