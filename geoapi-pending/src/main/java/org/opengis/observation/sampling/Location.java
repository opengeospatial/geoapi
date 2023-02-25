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
package org.opengis.observation.sampling;

import org.opengis.geometry.Geometry;
import org.opengis.metadata.extent.GeographicDescription;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.Obligation.*;

/**
 * Observations may be associated with a geospatial location. The primary location of
 * interest is usually associated with the ultimate feature-of-interest, so this is a principle
 * classifier of an observation and its result, used in indexing and discovery.
 *
 * However, the location may not be trivially available. For example: in remote sensing
 * applications, a complex processing chain is required to geolocate the scene or swath; in
 * feature-detection applications the initial observation may be made on a scene, but the
 * detected entity, which is the ultimate feature of interest, occupies some location within it.
 * The distinction between the proximate and ultimate feature of interest is a key
 * consideration in these cases (see sub-clauses 6.3.1 and O&amp;M-Part 2).
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/om">Implementation specification 1.0</A>
 * @author Open Geospatial Consortium
 * @author Guilhem Legal (Geomatys)
 * @since GeoAPI 2.3
 */
@UML(identifier="Location", specification=OGC_07022)
public interface Location {

    /**
     * @return Geometry : location geometry
     */
    @UML(identifier="geometryLocation", obligation=MANDATORY, specification=OGC_07022)
    Geometry getGeometryLocation();

    /**
     * @return GeographicDescription : named identified geographic area
     */
    @UML(identifier="nameLocation", obligation=MANDATORY, specification=OGC_07022)
    GeographicDescription getNameLocation();

}
