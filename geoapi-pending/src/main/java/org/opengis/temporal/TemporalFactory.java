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
package org.opengis.temporal;

import java.time.Instant;
import org.opengis.referencing.crs.TemporalCRS;

/**
 * Factory to create Temporal object.
 *
 * @author Open Geospatial Consortium
 * @author Guilhem Legal (Geomatys)
 * @author Johann Sorel  (Geomatys)
 * @author Remi Marechal (Geomatys).
 * @since   2.3
 * @version 4.0
 */
public interface TemporalFactory {
    /**
     * Creates a period.
     *
     * @param begin The instant at which the period starts.
     * @param end   The instant at which the period ends.
     */
    Period createPeriod(Instant begin, Instant end);

    /**
     * Creates a temporal position.
     *
     * @param frame                 The ReferenceSystem associated with this TemporalPosition,
     *                              if not specified, it is assumed to be an association to the Gregorian calendar and UTC.
     * @param indeterminatePosition The only value for TemporalPosition unless a
     *                              subtype of TemporalPosition is used as the data type.
     */
    TemporalPosition createTemporalPosition(TemporalCRS frame, IndeterminateValue indeterminatePosition);
}
