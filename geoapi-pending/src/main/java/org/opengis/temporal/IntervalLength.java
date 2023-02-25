/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2006-2023 Open Geospatial Consortium, Inc.
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

import javax.measure.Unit;
import org.opengis.annotation.UML;
import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.Obligation.*;


/**
 * A data type for intervals of time which supports the expression of duration in
 * terms of a specified multiple of a single unit of time.
 *
 * @author Stephane Fellah (Image Matters)
 * @author Alexander Petkov
 * @author Martin Desruisseaux (Geomatys)
 * @author Remi Marechal (Geomatys).
 * @since   2.3
 * @version 4.0
 */
@UML(identifier="TM_IntervalLength", specification=ISO_19108)
public interface IntervalLength extends Duration {
    /**
     * Returns {@link Unit} of measure used to express the length of the interval.
     *
     * @return {@link Unit} of measure used to express the length of the interval.
     */
    @UML(identifier="unit", obligation = MANDATORY, specification=ISO_19108)
    Unit getUnit();

    /**
     * Returns positive integer that is the base of the multiplier of the unit.
     *
     * @return positive integer that is the base of the multiplier of the unit.
     */
    @UML(identifier="radix", obligation = MANDATORY, specification=ISO_19108)
    int getRadix();

    /**
     * Returns the exposant of the base.
     *
     * @return the exposant of the base.
     */
    @UML(identifier="factor", obligation = MANDATORY, specification=ISO_19108)
    int getFactor();

    /**
     * Returns the length of the time interval as an integer multiple of one
     * {@linkplain #getRadix radix}<sup>(-{@linkplain #getFactor factor})</sup>
     * of the {@linkplain #getUnit specified unit}.
     *
     * @return the length of the time interval.
     */
    @UML(identifier="value", obligation = MANDATORY, specification=ISO_19108)
    int getValue();
}
