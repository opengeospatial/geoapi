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

import java.util.Optional;
import java.time.DateTimeException;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalField;
import org.opengis.geometry.DirectPosition;
import org.opengis.referencing.crs.TemporalCRS;
import org.opengis.referencing.cs.CoordinateDataType;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.OPTIONAL;
import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Specification.ISO_19108;


/**
 * A zero-dimensional temporal primitive that represents position in time, equivalent to a point in space.
 * In practice, an instant is an interval whose duration is less than the resolution of the time scale.
 *
 * @author  ISO 19108 (for abstract model and documentation)
 * @author  Stephane Fellah (Image Matters)
 * @author  Alexander Petkov
 * @author  Martin Desruisseaux (Geomatys)
 * @author  Remi Marechal (Geomatys).
 * @since   3.1
 * @version 3.1
 */
@UML(identifier="TM_Instant", specification=ISO_19108)
public interface Instant extends TemporalPrimitive {
    /**
     * The date, time or position on the time-scale represented by this primitive.
     * The position can be of the following types:
     *
     * <ul>
     *   <li><b>Date:</b> mapped to the {@link java.time.LocalDate} class.</li>
     *   <li><b>Time:</b> mapped to the {@link java.time.LocalTime} or {@link java.time.OffsetTime} classes.</li>
     *   <li><b>Date &amp; time:</b> mapped to the {@link java.time.LocalDateTime}, {@link java.time.OffsetDateTime}
     *       or {@link java.time.ZonedDateTime} classes.</li>
     *   <li><b>Temporal position:</b> mapped to a vendor-specific implementation of the {@link Temporal} interface,
     *       possibly using custom {@linkplain TemporalField temporal fields}.
     *       The returned object should also implement the {@link DirectPosition} interface for providing
     *       the coordinate value together with its coordinate reference system (<abbr>CRS</abbr>).</li>
     * </ul>
     *
     * The returned value should not be null, except if {@link #getIndeterminatePosition()} returns
     * {@link IndeterminateValue#UNKNOWN UNKNOWN} or {@link IndeterminateValue#NOW NOW}.
     *
     * @departure harmonization
     *   ISO 19108 defines this property as an union of {@code date8601}, {@code time8601}, {@code dateTime8601} or
     *   {@code anyOther} properties. Unions are not explicitly supported by the Java language, but the same result
     *   is achieved with type hierarchy. The mappings of ISO 19103 {@code Date}, {@code Time} and {@code DateTime}
     *   types are described in above Javadoc. The {@code TM_TemporalPosition} type used by the {@code anyOther}
     *   property is replaced by a vendor-specific {@link Temporal} implementation.
     *
     * @return the date, time or instant represented by this primitive.
     *         Should not be null, except if the temporal position is indeterminate for
     *         {@linkplain IndeterminateValue#UNKNOWN unknown} or {@linkplain IndeterminateValue#NOW now} reasons.
     */
    @UML(identifier="position", obligation=MANDATORY, specification=ISO_19108)
    Temporal getPosition();

    /**
     * Returns the reason why the temporal position is missing or inaccurate.
     * When the temporal position is defined, this code provides a qualifier
     * (e.g. "before" or "after") to the temporal position value.
     *
     * @departure integration
     *   Moved from {@code TM_TemporalPosition} to {@code TM_Instant} because the former is replaced by
     *   the {@link Temporal} standard Java interface, and {@code Temporal} does not have an association
     *   to the {@code IndeterminateValue} code list.
     *
     * @return the reason why the position is indeterminate.
     */
    @UML(identifier="TM_TemporalPosition.indeterminatePosition", obligation=OPTIONAL, specification=ISO_19108)
    default Optional<IndeterminateValue> getIndeterminatePosition() {
        return Optional.empty();
    }

    /**
     * Returns the distance from this instant to another instant or a period (optional operation).
     * This is the absolute value of the difference between the temporal positions.
     * If {@code other} is a period and this instant is contained within that period,
     * then this method shall return a distance of zero.
     *
     * <h4>Exceptions</h4>
     * This method may throw a {@link DateTimeException}
     * if at least one temporal position is {@linkplain #getIndeterminatePosition() indeterminate},
     * or if the temporal positions are associated to different {@link TemporalCRS},
     * or if the coordinates are {@linkplain CoordinateDataType#INTEGER ordinal values},
     * or if the {@link Temporal} objects do not support required {@linkplain TemporalField temporal fields}.
     *
     * @param  other the other object from which to measure the distance.
     * @return the distance from this instant to another instant.
     * @throws DateTimeException if the duration cannot be computed because
     *         of incompatibility between the two temporal primitives.
     * @throws UnsupportedOperationException if this operation is not supported.
     *
     * @see java.time.Duration#between(Temporal, Temporal)
     */
    @UML(identifier="TM_Separation.distance", obligation=OPTIONAL, specification=ISO_19108)
    default TemporalAmount distance(TemporalPrimitive other) {
        throw new UnsupportedOperationException();
    }
}
