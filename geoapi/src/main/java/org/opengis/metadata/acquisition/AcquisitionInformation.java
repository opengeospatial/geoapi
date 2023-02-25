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

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Designations for the measuring instruments, the platform carrying them, and the mission to
 * which the data contributes.
 *
 * @author  Cédric Briançon (Geomatys)
 * @version 3.1
 * @since   2.3
 */
@UML(identifier="MI_AcquisitionInformation", specification=ISO_19115_2)
public interface AcquisitionInformation {
    /**
     * Identifies the plan as implemented by the acquisition.
     *
     * @return plan as implemented by the acquisition.
     */
    @UML(identifier="acquisitionPlan", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends Plan> getAcquisitionPlans() {
        return Collections.emptyList();
    }

    /**
     * Identifies the requirement the data acquisition intends to satisfy.
     *
     * @return requirement the data acquisition intends to satisfy.
     */
    @UML(identifier="acquisitionRequirement", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends Requirement> getAcquisitionRequirements() {
        return Collections.emptyList();
    }

    /**
     * A record of the environmental circumstances during the data acquisition.
     *
     * @return record of the environmental circumstances, or {@code null}.
     */
    @UML(identifier="environmentalConditions", obligation=OPTIONAL, specification=ISO_19115_2)
    default EnvironmentalRecord getEnvironmentalConditions() {
        return null;
    }

    /**
     * General information about the instrument used in data acquisition.
     *
     * @return instrument used in data acquisition.
     */
    @UML(identifier="instrument", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends Instrument> getInstruments() {
        return Collections.emptyList();
    }

    /**
     * Identification of the area or object to be sensed.
     *
     * @return area or object to be sensed.
     */
    @UML(identifier="objective", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends Objective> getObjectives() {
        return Collections.emptyList();
    }

    /**
     * General information about an identifiable activity which provided the data.
     *
     * @return identifiable activity which provided the data.
     */
    @UML(identifier="operation", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends Operation> getOperations() {
        return Collections.emptyList();
    }

    /**
     * General information about the platform from which the data were taken.
     *
     * @return platform from which the data were taken.
     */
    @UML(identifier="platform", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends Platform> getPlatforms() {
        return Collections.emptyList();
    }
}
