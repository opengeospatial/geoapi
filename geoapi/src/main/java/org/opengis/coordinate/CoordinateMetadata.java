/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2024 Open Geospatial Consortium, Inc.
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
package org.opengis.coordinate;

import java.util.Optional;
import java.time.temporal.Temporal;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Metadata required to reference coordinates.
 * A {@linkplain CoordinateReferenceSystem coordinate reference system} (<abbr>CRS</abbr>) is mandatory.
 * If the <abbr>CRS</abbr> is dynamic, then a coordinate epoch is also mandatory.
 * Whether the <abbr>CRS</abbr> is dynamic is determined through the <abbr>CRS</abbr>'s reference frame definition.
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@UML(identifier="CoordinateMetadata", specification=ISO_19111)
public interface CoordinateMetadata {
    /**
     * The coordinate reference system (<abbr>CRS</abbr>) in which the coordinate tuples are given.
     *
     * @departure easeOfUse
     *   The ISO specification defines two conditional attributes: an identifier ({@code crsID}) or an association
     *   to a <abbr>CRS</abbr> object ({@code crs}), with the requirement that at least one of them shall be supplied.
     *   GeoAPI retains only the latter for client applications ease of use, therefor making this attribute mandatory.
     *   Implementers shall resolve identifiers, for example using {@link org.opengis.referencing.crs.CRSAuthorityFactory}.
     *
     * @return the coordinate reference system (CRS) of coordinate tuples.
     */
    @UML(identifier="crs", obligation=MANDATORY, specification=ISO_19111)
    CoordinateReferenceSystem getCoordinateReferenceSystem();

    /**
     * Date at which coordinate tuples referenced to a dynamic <abbr>CRS</abbr> are valid.
     * It should be an object from the {@link java.time} package such as {@link java.time.Instant}.
     * This attribute is required if the <abbr>CRS</abbr> is dynamic.
     *
     * @departure integration
     *   The ISO specification uses a decimal year in the Gregorian calendar.
     *   For example, 2017-03-25 in the Gregorian calendar is epoch 2017.23.
     *   GeoAPI delegates to the Java time package instead.
     *
     * @return epoch at which coordinate tuples are valid.
     */
    @UML(identifier="coordinateEpoch", obligation=CONDITIONAL, specification=ISO_19111)
    default Optional<Temporal> getCoordinateEpoch() {
        return Optional.empty();
    }
}
