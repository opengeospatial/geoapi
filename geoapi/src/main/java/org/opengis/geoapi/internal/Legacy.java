/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2023 Open Geospatial Consortium, Inc.
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
package org.opengis.geoapi.internal;

import java.util.Objects;
import java.util.Collection;
import java.util.Date;
import java.time.Instant;
import java.time.Year;
import java.time.YearMonth;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.LocalTime;
import java.time.OffsetTime;
import java.time.DateTimeException;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.chrono.ChronoZonedDateTime;
import org.opengis.metadata.citation.CitationDate;
import org.opengis.metadata.citation.DateType;
import org.opengis.metadata.extent.Extent;
import org.opengis.referencing.IdentifiedObject;
import org.opengis.referencing.ObjectDomain;
import org.opengis.util.InternationalString;
import org.opengis.temporal.TemporalPrimitive;


/**
 * Provides replacements for deprecated methods from legacy OGC/ISO specifications.
 * Methods in this class will be deleted when the corresponding deprecated methods
 * are removed from public API.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public final class Legacy {
    /**
     * Do not allow instantiation of this class.
     */
    private Legacy() {
    }

    /**
     * Returns the first non-null scope found in the given collection.
     *
     * @param  domains  the value of {@link IdentifiedObject#getDomains()}.
     * @return a description of domain of usage, or {@code null} if none.
     */
    public static InternationalString getScope(final Collection<ObjectDomain> domains) {
        if (domains == null) return null;
        return domains.stream().map(ObjectDomain::getScope).filter(Objects::nonNull).findFirst().orElse(null);
    }

    /**
     * Returns the first non-null domain of validity found in the given collection.
     *
     * @param  domains  the value of {@link IdentifiedObject#getDomains()}.
     * @return the valid domain, or {@code null} if not available.
     */
    public static Extent getDomainOfValidity(final Collection<ObjectDomain> domains) {
        if (domains == null) return null;
        return domains.stream().map(ObjectDomain::getDomainOfValidity).filter(Objects::nonNull).findFirst().orElse(null);
    }

    /**
     * Converts a temporal primitive to a legacy {@link Date} object.
     * See {@link #toDate(Temporal)} for more information.
     *
     * @param  t  the temporal primitive to convert, or {@code null}.
     * @return the given temporal primitive as a date, or {@code null} if none.
     * @throws DateTimeException if this method does not know how to convert the given temporal object.
     * @throws ArithmeticException if numeric overflow occurs.
     */
    public static Date toDate(final TemporalPrimitive t) {
        return (t instanceof org.opengis.temporal.Instant) ? toDate(((org.opengis.temporal.Instant) t).getPosition()) : null;
    }

    /**
     * Converts a {@link java.time} object to a legacy {@link Date} object.
     * If the timezone is not specified, UTC is assumed.
     * This method is invoked only by the default implementation of deprecated methods
     * and may be removed in a future GeoAPI version.
     *
     * @param  t  the date to convert, or {@code null}.
     * @return the given temporal object as a date, or {@code null} if the given argument was null.
     * @throws DateTimeException if this method does not know how to convert the given temporal object.
     * @throws ArithmeticException if numeric overflow occurs.
     */
    public static Date toDate(final Temporal t) {
        if (t == null) {
            return null;
        }
        final Instant instant;
        if (t instanceof Instant) {
            instant = (Instant) t;
        } else if (t instanceof ChronoZonedDateTime) {
            instant = ((ChronoZonedDateTime) t).toInstant();
        } else {
            final OffsetDateTime odt;
            if (t instanceof OffsetDateTime) {
                odt = (OffsetDateTime) t;
            } else if (t instanceof ZonedDateTime) {
                odt = ((ZonedDateTime) t).toOffsetDateTime();
            } else if (t instanceof LocalDateTime) {
                odt = ((LocalDateTime) t).atOffset(ZoneOffset.UTC);
            } else {
                final LocalDate date;
                if (t instanceof LocalDate) {
                    date = (LocalDate) t;
                } else if (t instanceof YearMonth) {
                    date = ((YearMonth) t).atDay(1);
                } else if (t instanceof Year) {
                    date = ((Year) t).atDay(1);
                } else {
                    // Following may throw `DateTimeException` if the temporal does not support the field.
                    return new Date(Math.multiplyExact(t.getLong(ChronoField.INSTANT_SECONDS), 1000));
                }
                odt = date.atTime(OffsetTime.of(LocalTime.MIDNIGHT, ZoneOffset.UTC));
            }
            instant = odt.toInstant();
        }
        // Do not use `Date.from(Instant)` because we want the `ArithmeticException` in case of overflow.
        return new Date(instant.toEpochMilli());
    }

    /**
     * Returns the first date of the specified type in the given collection of citation dates.
     * This is used for default implementation of deprecated methods inherited from older standards.
     *
     * @param  dates  the citation dates from which to get a date of the specified type.
     * @param  type   the type of date to search.
     * @return first occurrence of a date of the specified type.
     */
    @SuppressWarnings("deprecation")
    public static Date getDate(final Iterable<? extends CitationDate> dates, final DateType type) {
        if (dates != null) {
            CitationDate fallback = null;
            for (CitationDate info : dates) {
                if (type.equals(info.getDateType())) {
                    return info.getDate();
                } else if (fallback == null) {
                    fallback = info;
                }
            }
            if (fallback != null) {
                return fallback.getDate();
            }
        }
        return null;
    }
}
