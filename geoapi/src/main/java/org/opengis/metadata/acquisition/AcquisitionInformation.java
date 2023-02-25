/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2009-2011 Open Geospatial Consortium, Inc.
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

import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Designations for the measuring instruments, the platform carrying them, and the mission to
 * which the data contributes.
 *
 * @author  Cédric Briançon (Geomatys)
 * @version 3.0
 * @since   2.3
 *
 * @navassoc - - - Plan
 * @navassoc - - - Requirement
 * @navassoc 1 - - EnvironmentalRecord
 * @navassoc - - - Instrument
 * @navassoc - - - Objective
 * @navassoc - - - ScopeCode
 * @navassoc - - - Operation
 * @navassoc - - - Platform
 */
@UML(identifier="MI_AcquisitionInformation", specification=ISO_19115_2)
public interface AcquisitionInformation {
    /**
     * Identifies the plan as implemented by the acquisition.
     *
     * @return Plan as implemented by the acquisition.
     */
    @UML(identifier="acquisitionPlan", obligation=OPTIONAL, specification=ISO_19115_2)
    Collection<? extends Plan> getAcquisitionPlans();

    /**
     * Identifies the requirement the data acquisition intends to satisfy.
     *
     * @return Requirement the data acquisition intends to satisfy.
     */
    @UML(identifier="acquisitionRequirement", obligation=OPTIONAL, specification=ISO_19115_2)
    Collection<? extends Requirement> getAcquisitionRequirements();

    /**
     * A record of the environmental circumstances during the data acquisition.
     *
     * @return Record of the environmental circumstances.
     */
    @UML(identifier="environmentalConditions", obligation=OPTIONAL, specification=ISO_19115_2)
    EnvironmentalRecord getEnvironmentalConditions();

    /**
     * General information about the instrument used in data acquisition.
     *
     * @return Instrument used in data acquisition.
     */
    @UML(identifier="instrument", obligation=OPTIONAL, specification=ISO_19115_2)
    Collection<? extends Instrument> getInstruments();

    /**
     * Identification of the area or object to be sensed.
     *
     * @return Area or object to be sensed.
     */
    @UML(identifier="objective", obligation=OPTIONAL, specification=ISO_19115_2)
    Collection<? extends Objective> getObjectives();

    /**
     * General information about an identifiable activity which provided the data.
     *
     * @return Identifiable activity which provided the data.
     */
    @UML(identifier="operation", obligation=OPTIONAL, specification=ISO_19115_2)
    Collection<? extends Operation> getOperations();

    /**
     * General information about the platform from which the data were taken.
     *
     * @return Platform from which the data were taken.
     */
    @UML(identifier="platform", obligation=OPTIONAL, specification=ISO_19115_2)
    Collection<? extends Platform> getPlatforms();
}
