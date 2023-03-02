/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2022-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.quality;

import java.util.List;
import java.util.Collection;
import java.time.temporal.Temporal;


/**
 * A simple implementation of {@link EvaluationMethod} for testing purposes.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class EvaluationMethodImpl implements EvaluationMethod {
    /**
     * Range of dates on which a data quality measure was applied.
     */
    private final Temporal startTime, endTime;

    /**
     * Creates a new evaluation method.
     *
     * @param  startTime  start time on which a data quality measure was applied.
     * @param  endTime    end time on which a data quality measure was applied.
     */
    EvaluationMethodImpl(final Temporal startTime, final Temporal endTime) {
        this.startTime = startTime;
        this.endTime   = endTime;
    }

    /**
     * Returns the range of dates on which a data quality measure was applied.
     */
    @Override
    public Collection<Temporal> getDates() {
        return List.of(startTime, endTime);
    }
}
