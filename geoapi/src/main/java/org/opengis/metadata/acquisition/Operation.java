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

import org.opengis.annotation.UML;
import org.opengis.metadata.Identifier;
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.identification.Progress;
import org.opengis.util.InternationalString;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Designations for the operation used to acquire the dataset.
 *
 * @author  Cédric Briançon (Geomatys)
 * @version 3.1
 * @since   2.3
 */
@UML(identifier="MI_Operation", specification=ISO_19115_2)
public interface Operation {
    /**
     * Description of the mission on which the platform observations are made and the
     * objectives of that mission.
     *
     * @return description of the mission, or {@code null}.
     */
    @UML(identifier="description", obligation=OPTIONAL, specification=ISO_19115_2)
    default InternationalString getDescription() {
        return null;
    }

    /**
     * Identification of the mission.
     *
     * @return identification of the mission, or {@code null}.
     */
    @UML(identifier="citation", obligation=OPTIONAL, specification=ISO_19115_2)
    default Citation getCitation() {
        return null;
    }

    /**
     * Unique identification of the operation.
     *
     * @return unique identification of the operation.
     */
    @UML(identifier="identifier", obligation=OPTIONAL, specification=ISO_19115_2)
    default Identifier getIdentifier() {
        return null;
    }

    /**
     * Status of the data acquisition.
     *
     * @return status of the data acquisition.
     */
    @UML(identifier="status", obligation=MANDATORY, specification=ISO_19115_2)
    Progress getStatus();

    /**
     * Collection technique for the operation.
     *
     * @return collection technique for the operation, or {@code null}.
     */
    @UML(identifier="type", obligation=OPTIONAL, specification=ISO_19115_2)
    default OperationType getType() {
        return null;
    }

    /**
     * Sub-missions that make up part of a larger mission.
     *
     * @return sub-missions.
     */
    @UML(identifier="childOperation", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends Operation> getChildOperations() {
        return Collections.emptyList();
    }

    /**
     * Object(s) or area(s) of interest to be sensed.
     *
     * @return object(s) or area(s) of interest.
     */
    @UML(identifier="objective", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends Objective> getObjectives() {
        return Collections.emptyList();
    }

    /**
     * Heritage of the operation.
     *
     * @return heritage of the operation, or {@code null}.
     */
    @UML(identifier="parentOperation", obligation=OPTIONAL, specification=ISO_19115_2)
    default Operation getParentOperation() {
        return null;
    }

    /**
     * Plan satisfied by the operation.
     *
     * @return plan satisfied by the operation, or {@code null}.
     */
    @UML(identifier="plan", obligation=OPTIONAL, specification=ISO_19115_2)
    default Plan getPlan() {
        return null;
    }

    /**
     * Platform (or platforms) used in the operation.
     *
     * @return Platforms used in the operation.
     */
    @UML(identifier="platform", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends Platform> getPlatforms() {
        return Collections.emptyList();
    }

    /**
     * Record of an event occurring during an operation.
     *
     * @return record of an event occurring during an operation.
     */
    @UML(identifier="significantEvent", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends Event> getSignificantEvents() {
        return Collections.emptyList();
    }
}
