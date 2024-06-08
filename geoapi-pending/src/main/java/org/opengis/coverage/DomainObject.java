/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2005-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.coverage;

import java.util.Set;
import org.opengis.geometry.Geometry;
import org.opengis.temporal.TemporalPrimitive;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Represents an element of the domain of the {@linkplain Coverage coverage}. It is an aggregation
 * of objects that may include any combination of {@linkplain Geometry geometry}, or spatial or
 * temporal objects such as {@linkplain org.opengis.coverage.grid.GridPoint grid point}.
 *
 * @param <G> The type of the geometry member.
 *
 * @version ISO 19123:2004
 * @author  Stephane Fellah
 * @author  Martin Desruisseaux (IRD)
 * @since   GeoAPI 2.1
 *
 * @see Coverage#getDomainElements
 */
@UML(identifier="CV_DomainObject", specification=ISO_19123)
public interface DomainObject<G extends Geometry> {
    /**
     * Returns the set of geometries of which this domain is composed.
     * The set may be empty.
     *
     * @return the spatial component of the domain.
     */
    @UML(identifier="spatialElement", obligation=OPTIONAL, specification=ISO_19123)
    Set<G> getSpatialElements();

    /**
     * Returns the set of geometric primitives of which this domain is composed.
     * The set may be empty.
     *
     * @return the temporal component of the domain.
     */
    @UML(identifier="temporalElement", obligation=OPTIONAL, specification=ISO_19123)
    Set<TemporalPrimitive> getTemporalElements();
}
