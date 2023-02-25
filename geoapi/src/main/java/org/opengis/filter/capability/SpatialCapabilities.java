/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2006-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.filter.capability;

import java.util.Map;
import java.util.List;
import java.util.Collection;
import org.opengis.util.ScopedName;
import org.opengis.filter.SpatialOperatorName;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Specification.ISO_19143;


/**
 * Advertisement of which spatial operators and geometric operands the service supports.
 * Spatial capabilities include the ability to filter spatial data of specified geometry types
 * based on the definition of a bounding box (BBOX) as well as the ability to process the spatial operators
 * Equals, Disjoint, Touches, Within, Overlaps, Crosses, Intersects, Contains, DWithin and Beyond.
 *
 * @author  Torsten Friebe (lat/lon)
 * @author  Justin Deoliveira (The Open Planning Project)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 *
 * @see FilterCapabilities#getSpatialCapabilities()
 *
 * @since 3.1
 */
@UML(identifier="SpatialCapabilities", specification=ISO_19143)
public interface SpatialCapabilities {
    /**
     * Advertises which geometry operands are supported by the service.
     * Geometry operands listed here are defined globally, indicating that
     * all spatial operators know how to process the specified operands.
     *
     * @return geometry operands supported globally by the service.
     */
    @UML(identifier="geometryOperand", obligation=MANDATORY, specification=ISO_19143)
    Collection<? extends ScopedName> getGeometryOperands();

    /**
     * Advertises which spatial operators are supported by the service.
     * Keys are spatial operator names and values are geometry operands defined
     * {@linkplain #getGeometryOperands() globally} or locally for each spatial operator,
     * indicating that the specific operator knows how to process the specified operands.
     *
     * @return spatial operators supported by the service.
     *
     * @departure easeOfUse
     *   GeoAPI replaces the {@code SpatialOperatorDescription} type by {@code Map.Entry}.
     *   It reduces the number of interfaces and makes easy to query the operands for a specific operator.
     */
    @UML(identifier="spatialOperator", obligation=MANDATORY, specification=ISO_19143)
    Map<SpatialOperatorName, List<? extends ScopedName>> getSpatialOperators();
}
