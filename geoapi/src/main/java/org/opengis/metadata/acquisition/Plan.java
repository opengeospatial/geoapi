/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2009-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
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
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.identification.Progress;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Designations for the planning information related to meeting the data acquisition requirements.
 *
 * @author  Cédric Briançon (Geomatys)
 * @version 3.1
 * @since   2.3
 */
@UML(identifier="MI_Plan", specification=ISO_19115_2)
public interface Plan {
    /**
     * Manner of sampling geometry that the planner expects for collection of objective data.
     *
     * @return manner of sampling geometry, or {@code null}.
     */
    @UML(identifier="type", obligation=OPTIONAL, specification=ISO_19115_2)
    default GeometryType getType() {
        return null;
    }

    /**
     * Current status of the plan (pending, completed, etc.)
     *
     * @return current status of the plan.
     */
    @UML(identifier="status", obligation=MANDATORY, specification=ISO_19115_2)
    Progress getStatus();

    /**
     * Identification of authority requesting target collection.
     *
     * @return identification of authority requesting target collection.
     */
    @UML(identifier="citation", obligation=MANDATORY, specification=ISO_19115_2)
    Citation getCitation();

    /**
     * Identification of the activity or activities that satisfy a plan.
     *
     * @return identification of the activity or activities.
     */
    @UML(identifier="operation", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends Operation> getOperations() {
        return Collections.emptyList();
    }

    /**
     * Requirement satisfied by the plan.
     *
     * @return requirement satisfied by the plan.
     */
    @UML(identifier="satisfiedRequirement", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends Requirement> getSatisfiedRequirements() {
        return Collections.emptyList();
    }
}
