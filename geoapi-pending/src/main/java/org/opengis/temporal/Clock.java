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


/**
 * Provides a basis for defining temporal position within a day.
 *
 * @author Alexander Petkov
 * @author Martin Desruisseaux (Geomatys)
 * @author Remi Marechal (Geomatys).
 * @since   2.3
 * @version 4.0 
 */
@UML(identifier="TM_Clock", specification=ISO_19108)
public interface Clock extends TemporalReferenceSystem {
    
    /**
     * Returns event used as the datum for this clock.
     * 
     * @return event used as the datum for this clock.
     */
    @UML(identifier="referenceEvent", obligation=MANDATORY, specification=ISO_19108)
    InternationalString getReferenceEvent();
    
    /**
     * Returns time of the reference event for this clock, usually the origin of the clock scale.
     * 
     * @return time of the reference event for this clock, usually the origin of the clock scale.
     */
    @UML(identifier="referenceTime", obligation=MANDATORY, specification=ISO_19108)
    ClockTime getReferenceTime();

    /**
     * Returns the 24-hour local or UTC time that corresponds to the reference time.
     * 
     * @return the 24-hour local or UTC time that corresponds to the reference time.
     */
    @UML(identifier="utcReference", obligation=MANDATORY, specification=ISO_19108)
    ClockTime getUTCReference();
    
    /**
     * Returns conversion of an UTC time to a time on this clock.
     * 
     * @param uTime the UTC time which will be convert.
     * @return convertion of an UTC time to a time on this clock.
     */
    @UML(identifier="clkTrans", obligation=MANDATORY, specification=ISO_19108)
    ClockTime clkTrans(ClockTime uTime);
    
    /**
     * Returns convertion from time on this clock to UTC time.
     * 
     * @param clockTime the clock time which will be convert.
     * @return convertion from time on this clock to UTC time.
     */
    @UML(identifier="utcTrans", obligation=MANDATORY, specification=ISO_19108)
    ClockTime utcTrans(ClockTime clockTime);
}
