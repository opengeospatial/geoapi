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
package org.opengis.metadata.acquisition;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import org.opengis.annotation.UML;
import org.opengis.metadata.Identifier;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Identification of a significant collection point within an operation.
 *
 * @author  Cédric Briançon (Geomatys)
 * @version 3.1
 * @since   2.3
 */
@UML(identifier="MI_Event", specification=ISO_19115_2)
public interface Event {
    /**
     * Event name or number.
     *
     * @return event name or number.
     */
    @UML(identifier="identifier", obligation=MANDATORY, specification=ISO_19115_2)
    Identifier getIdentifier();

    /**
     * Initiator of the event.
     *
     * @return initiator of the event.
     */
    @UML(identifier="trigger", obligation=MANDATORY, specification=ISO_19115_2)
    Trigger getTrigger();

    /**
     * Meaning of the event.
     *
     * @return meaning of the event.
     */
    @UML(identifier="context", obligation=MANDATORY, specification=ISO_19115_2)
    Context getContext();

    /**
     * Relative time ordering of the event.
     *
     * @return relative time ordering.
     */
    @UML(identifier="sequence", obligation=MANDATORY, specification=ISO_19115_2)
    Sequence getSequence();

    /**
     * Time the event occurred.
     *
     * <div class="warning"><b>Upcoming API change — temporal schema</b><br>
     * The return type of this method may change in GeoAPI 4.0 release. It may be replaced by a
     * type matching more closely either ISO 19108 (<cite>Temporal Schema</cite>) or ISO 19103.
     * </div>
     *
     * @return time the event occurred.
     */
    @UML(identifier="time", obligation=MANDATORY, specification=ISO_19115_2)
    Date getTime();

    /**
     * Objective or objectives satisfied by an event.
     *
     * @return objectives satisfied by an event.
     */
    @UML(identifier="expectedObjective", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends Objective> getExpectedObjectives() {
        return Collections.emptyList();
    }

    /**
     * Pass during which an event occurs.
     *
     * @return pass during which an event occurs, or {@code null}.
     */
    @UML(identifier="relatedPass", obligation=OPTIONAL, specification=ISO_19115_2)
    default PlatformPass getRelatedPass() {
        return null;
    }

    /**
     * Instrument or instruments for which the event is meaningful.
     *
     * @return instruments for which the event is meaningful.
     */
    @UML(identifier="relatedSensor", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends Instrument> getRelatedSensors() {
        return Collections.emptyList();
    }
}
