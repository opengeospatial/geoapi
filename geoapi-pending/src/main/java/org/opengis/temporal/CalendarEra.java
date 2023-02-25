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

import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;
import org.opengis.referencing.IdentifiedObject;

/**
 * Characteristics of each calendar era.
 *
 * @author Stephane Fellah (Image Matters)
 * @author Alexander Petkov
 * @author Martin Desruisseaux (Geomatys)
 * @author Remi Marechal (Geomatys).
 * @since   2.3
 * @version 4.0 
 */
@UML(identifier="TM_CalendarEra", specification=ISO_19108)
public interface CalendarEra extends IdentifiedObject {
    
    /**
     * Returns name or description of a mythical or historic event which fixes the position
     * of the base scale of the {@link CalendarEra}.
     * 
     * @return name or description of a mythical or historic event which fixes the position
     * of the base scale of the {@link CalendarEra}.
     */
    @UML(identifier="referenceEvent", obligation=MANDATORY, specification=ISO_19108)
    InternationalString getReferenceEvent();

    /**
     * Returns date of the reference event expressed as a date in the given calendar.
     * 
     * @return date of the reference event expressed as a date in the given calendar.
     */
    @UML(identifier="referenceDate", obligation=MANDATORY, specification=ISO_19108)
    CalendarDate getReferenceDate();
    
    /**
     * Returns the {@linkplain JulianDate julian date} that corresponds to the reference date.
     * 
     * @return the {@linkplain JulianDate julian date} that corresponds to the reference date.
     */
    @UML(identifier="julianReference", obligation=MANDATORY, specification=ISO_19108)
    JulianDate getJulianReference();

    /**
     * Returns the {@linkplain Period period} for which the {@linkplain CalendarEra calendar era}
     * was used as a basis for dating.
     *
     * @return the {@linkplain Period period} for which the {@linkplain CalendarEra calendar era}
     * was used as a basis for dating.
     */
    @UML(identifier="epochOfUse", obligation=MANDATORY, specification=ISO_19108)
    Period getEpochOfUse();
}
