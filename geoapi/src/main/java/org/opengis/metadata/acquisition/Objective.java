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

import java.util.Set;
import java.util.List;
import java.util.Collection;
import org.opengis.annotation.UML;
import org.opengis.metadata.Identifier;
import org.opengis.metadata.extent.Extent;
import org.opengis.util.InternationalString;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Describes the characteristics, spatial and temporal extent of the intended object to be
 * observed.
 *
 * @author  Cédric Briançon (Geomatys)
 * @version 4.0
 * @since   2.3
 */
@UML(identifier="MI_Objective", specification=ISO_19115_2)
public interface Objective {
    /**
     * Code used to identify the objective.
     *
     * @return objective identifiers.
     */
    @UML(identifier="identifier", obligation=MANDATORY, specification=ISO_19115_2)
    Collection<? extends Identifier> getIdentifiers();

    /**
     * Priority applied to the target.
     *
     * @return priority applied, or {@code null}.
     */
    @UML(identifier="priority", obligation=OPTIONAL, specification=ISO_19115_2)
    default InternationalString getPriority() {
        return null;
    }

    /**
     * Collection technique for the objective.
     *
     * @return collection technique for the objective.
     */
    @UML(identifier="type", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<ObjectiveType> getTypes() {
        return Set.of();
    }

    /**
     * Role or purpose performed by or activity performed at the objective.
     *
     * @return role or purpose performed by or activity performed at the objective.
     */
    @UML(identifier="function", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends InternationalString> getFunctions() {
        return List.of();
    }

    /**
     * Extent information including the bounding box, bounding polygon, vertical and
     * temporal extent of the objective.
     *
     * @return extent information.
     */
    @UML(identifier="extent", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends Extent> getExtents() {
        return List.of();
    }

    /**
     * Event or events associated with objective completion.
     *
     * <div class="note"><b>Known typo:</b>
     * "occurrence" is missing a "r" in the UML diagram of ISO 19115-2 specification.
     * The {@code UML} annotation below reflects that spelling.</div>
     *
     * @return events associated with objective completion.
     */
    @UML(identifier="objectiveOccurence", obligation=MANDATORY, specification=ISO_19115_2)
    Collection<? extends Event> getObjectiveOccurrences();

    /**
     * Pass of the platform over the objective.
     *
     * @return pass of the platform.
     */
    @UML(identifier="pass", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends PlatformPass> getPass() {
        return List.of();
    }

    /**
     * Instrument which senses the objective data.
     *
     * @return instrument which senses the objective data.
     */
    @UML(identifier="sensingInstrument", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends Instrument> getSensingInstruments() {
        return List.of();
    }
}
