/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2014-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.test.metadata;

import java.time.LocalDate;
import java.time.temporal.Temporal;
import org.opengis.metadata.citation.DateType;
import org.opengis.metadata.citation.CitationDate;


/**
 * Simple {@link CitationDate} implementation.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class SimpleCitationDate implements CitationDate {
    /**
     * The value to be returned by {@link #getDateType()}.
     */
    private final DateType type;

    /**
     * The value to be returned by {@link #getReferenceDate()}.
     */
    private final LocalDate time;

    /**
     * Creates a new citation date.
     *
     * @param  type  the value to be returned by {@link #getDateType()}.
     * @param  time  the value to be returned by {@link #getDate()}.
     */
    SimpleCitationDate(final DateType type, final String time) {
        this.type = type;
        this.time = LocalDate.parse(time);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DateType getDateType() {
        return type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Temporal getReferenceDate() {
        return time;
    }
}
