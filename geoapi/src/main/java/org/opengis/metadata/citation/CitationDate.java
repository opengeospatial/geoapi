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
package org.opengis.metadata.citation;

import java.util.Date;
import java.time.temporal.Temporal;
import org.opengis.geoapi.internal.Legacy;
import org.opengis.annotation.UML;
import org.opengis.annotation.Profile;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.ComplianceLevel.*;


/**
 * Reference date and event used to describe it.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   2.0
 */
@Classifier(Stereotype.DATATYPE)
@UML(identifier="CI_Date", specification=ISO_19115)
public interface CitationDate {
    /**
     * Reference date for the cited resource.
     *
     * @return reference date for the cited resource.
     *
     * @deprecated Replaced by {@link #getReferenceDate()}.
     */
    @Deprecated(since="3.1")
    default Date getDate() {
        return Legacy.toDate(getReferenceDate());
    }

    /**
     * Reference date for the cited resource.
     * The returned value should be an instance of {@link java.time.LocalDate}, {@link java.time.LocalDateTime},
     * {@link java.time.OffsetDateTime} or {@link java.time.ZonedDateTime}, depending whether hours are defined
     * and how the timezone (if any) is defined. But other types are also allowed.
     * For example, a citation date may be merely a {@link java.time.Year}.
     *
     * @return reference date for the cited resource.
     *
     * @departure historical
     *   Renamed for avoiding a conflict with the {@code getDate()} method defined in GeoAPI 3.0.
     *
     * @since 3.1
     */
    @Profile(level=CORE)
    @UML(identifier="date", obligation=MANDATORY, specification=ISO_19115)
    Temporal getReferenceDate();

    /**
     * Event used for reference date.
     *
     * @return event used for reference date.
     */
    @Profile(level=CORE)
    @UML(identifier="dateType", obligation=MANDATORY, specification=ISO_19115)
    DateType getDateType();
}
