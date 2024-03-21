/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2024 Open Geospatial Consortium, Inc.
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
package org.opengis.referencing.cs;

import java.util.Map;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import javax.measure.quantity.Time;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;


/**
 * A 1-dimensional coordinate system containing a single time axis.
 * This coordinate system is used to describe the temporal position of a point
 * in the specified time units from a time origin implied by the temporal datum.
 *
 * <p>This type of CS can be used by coordinate reference systems of type
 * {@link org.opengis.referencing.crs.TemporalCRS}.</p>
 *
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 * @since   2.0
 *
 * @see CSAuthorityFactory#createTimeCS(String)
 * @see CSFactory#createTimeCS(Map, CoordinateSystemAxis)
 *
 * @departure integration
 *   ISO 19111:2019 renamed this interface from {@code TimeCS} to {@code TemporalCS}.
 *   GeoAPI has kept the ISO 19111:2007 interface name for historical reasons,
 *   but also to emphasize the relationship with the {@link java.time} package.
 */
@UML(identifier="TemporalCS", specification=ISO_19111)
public interface TimeCS extends CoordinateSystem {
    /**
     * Returns the unit of measurement of coordinate values. The returned unit is equivalent to the unit
     * returned by {@link CoordinateSystemAxis#getUnit()}, but expressed as a {@link java.time} object,
     * preferably an instance from the {@link ChronoUnit} enumeration.
     *
     * <p>Note that contrarily to the usual units of measurement, the duration of {@link java.time} units can vary.
     * For example {@link ChronoUnit#DAYS} is <em>estimated</em> to be about 24 hours long, because the actual
     * duration can vary due to daylight saving time changes. Implementations can specify whether the temporal
     * unit is estimated or exact with {@link TemporalUnit#isDurationEstimated()}.</p>
     *
     * <h4>Default implementation</h4>
     * The default implementation returns a {@link ChronoUnit} or an integer multiple of a {@code ChronoUnit}
     * with a duration approximately equals to the duration of the coordinate system axis unit.
     * "Approximately equals" is defined as a tolerance of twelve hours for {@code ChronoUnit}
     * of one month or longer (in which case the duration is flagged as estimated),
     * or one <abbr>ULP</abbr> otherwise.
     *
     * @return unit measurement of coordinate values, preferably as a {@link ChronoUnit} enumeration value.
     *
     * @see ChronoUnit#SECONDS
     * @see ChronoUnit#DAYS
     * @see ChronoUnit#YEARS
     *
     * @since 3.1
     */
    default TemporalUnit getUnit() {
        return TimeUnit.valueOf(getAxis(0).getUnit().asType(Time.class));
    }
}
