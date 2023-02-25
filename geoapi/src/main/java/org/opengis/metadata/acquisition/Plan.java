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
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.identification.Progress;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Designations for the planning information related to meeting the data acquisition requirements.
 *
 * @author  Cédric Briançon (Geomatys)
 * @version 3.0
 * @since   2.3
 *
 * @navassoc 1 - - GeometryType
 * @navassoc 1 - - Progress
 * @navassoc 1 - - Citation
 * @navassoc - - - Operations
 * @navassoc - - - Requirement
 */
@UML(identifier="MI_Plan", specification=ISO_19115_2)
public interface Plan {
    /**
     * Manner of sampling geometry that the planner expects for collection of objective data.
     *
     * @return Manner of sampling geometry.
     */
    @UML(identifier="type", obligation=OPTIONAL, specification=ISO_19115_2)
    GeometryType getType();

    /**
     * Current status of the plan (pending, completed, etc.)
     *
     * @return Current status of the plan.
     */
    @UML(identifier="status", obligation=MANDATORY, specification=ISO_19115_2)
    Progress getStatus();

    /**
     * Identification of authority requesting target collection.
     *
     * @return Identification of authority requesting target collection.
     */
    @UML(identifier="citation", obligation=MANDATORY, specification=ISO_19115_2)
    Citation getCitation();

    /**
     * Identification of the activity or activities that satisfy a plan.
     *
     * @return Identification of the activity or activities.
     */
    @UML(identifier="operation", obligation=OPTIONAL, specification=ISO_19115_2)
    Collection<? extends Operation> getOperations();

    /**
     * Requirement satisfied by the plan.
     *
     * @return Requirement satisfied by the plan.
     */
    @UML(identifier="satisfiedRequirement", obligation=OPTIONAL, specification=ISO_19115_2)
    Collection<? extends Requirement> getSatisfiedRequirements();
}
