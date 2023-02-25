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
