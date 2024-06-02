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
import java.time.temporal.Temporal;
import org.opengis.annotation.UML;
import org.opengis.metadata.Identifier;
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.citation.Responsibility;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Requirement to be satisfied by the planned data acquisition.
 *
 * @author  Cédric Briançon (Geomatys)
 * @version 3.1
 * @since   2.3
 */
@UML(identifier="MI_Requirement", specification=ISO_19115_2)
public interface Requirement {
    /**
     * Identification of reference or guidance material for the requirement.
     *
     * @return identification of reference or guidance material, or {@code null}.
     */
    @UML(identifier="citation", obligation=OPTIONAL, specification=ISO_19115_2)
    default Citation getCitation() {
        return null;
    }

    /**
     * Unique name, or code, for the requirement.
     *
     * @return unique name or code.
     */
    @UML(identifier="identifier", obligation=MANDATORY, specification=ISO_19115_2)
    Identifier getIdentifier();

    /**
     * Origin of requirement.
     *
     * @return origin of requirement.
     */
    @UML(identifier="requestor", obligation=MANDATORY, specification=ISO_19115_2)
    Collection<? extends Responsibility> getRequestors();

    /**
     * Person(s), or body(ies), to receive results of requirement.
     *
     * @return person(s), or body(ies), to receive results.
     */
    @UML(identifier="recipient", obligation=MANDATORY, specification=ISO_19115_2)
    Collection<? extends Responsibility> getRecipients();

    /**
     * Relative ordered importance, or urgency, of the requirement.
     *
     * @return relative ordered importance, or urgency.
     */
    @UML(identifier="priority", obligation=MANDATORY, specification=ISO_19115_2)
    Priority getPriority();

    /**
     * Required or preferred acquisition date and time.
     *
     * @return required or preferred acquisition date and time.
     */
    @UML(identifier="requestedDate", obligation=MANDATORY, specification=ISO_19115_2)
    RequestedDate getRequestedDate();

    /**
     * Date and time after which collection is no longer valid.
     * Should be an instance of {@link java.time.LocalDateTime}, {@link java.time.OffsetDateTime} or
     * {@link java.time.ZonedDateTime}, depending how timezone is defined. Other types are also allowed.
     *
     * @return date and time after which collection is no longer valid.
     */
    @UML(identifier="expiryDate", obligation=MANDATORY, specification=ISO_19115_2)
    Temporal getExpiryDate();

    /**
     * Plan that identifies solution to satisfy the requirement.
     *
     * @return plan that identifies solution to satisfy the requirement.
     */
    @UML(identifier="satisfiedPlan", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends Plan> getSatisfiedPlans() {
        return Collections.emptyList();
    }
}
