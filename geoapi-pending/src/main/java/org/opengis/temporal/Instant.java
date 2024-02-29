/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2006-2024 Open Geospatial Consortium, Inc.
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
package org.opengis.temporal;

import java.util.Date;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;

/**
 * A zero-dimensional geometric primitive that represents position in time, equivalent to a point
 * in space.
 *
 * @departure integration
 *   In ISO 19108 Position interface is define as a union class, which does not exist in java.
 *   We choose to omit Position class and add getDate() method into {@link Instant} interface.
 *
 * @author Stephane Fellah (Image Matters)
 * @author Alexander Petkov
 * @author Martin Desruisseaux (Geomatys)
 * @author Remi Marechal (Geomatys).
 * @since   2.3
 * @version 4.0
 */
@UML(identifier="TM_Instant", specification=ISO_19108)
public interface Instant extends TemporalGeometricPrimitive {
    /**
     * Returns {@link Date} used for describing temporal positions in ISO8601 format referenced to the
     * Gregorian calendar and UTC.
     *
     * @return {@link Date} used for describing temporal positions in ISO8601 format.
     */
    @UML(identifier="date8601", obligation=OPTIONAL, specification=ISO_19108)
    Date getDate();

    /**
     * Returns {@link TemporalPosition} which contain an association between one from
     * four classes and a {@link TemporalReferenceSystem}.
     * The four possibles classes are {@link TemporalCoordinate}, {@link OrdinalPosition},
     * {@link CalendarDate}, or {@link ClockTime}.
     *
     * @return {@link TemporalPosition} which contain an association between one from
     * four classes and a {@link TemporalReferenceSystem}.
     */
    TemporalPosition getTemporalPosition();
}
