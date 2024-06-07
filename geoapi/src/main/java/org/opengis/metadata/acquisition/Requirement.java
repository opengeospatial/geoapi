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

import java.util.Date;
import java.util.Collection;
import java.util.Collections;

import org.opengis.annotation.UML;
import org.opengis.metadata.Identifier;
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.citation.ResponsibleParty;

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
     * <div class="warning"><b>Upcoming API change — generalization</b><br>
     * As of ISO 19115:2014, {@code ResponsibleParty} is replaced by the {@link Responsibility} parent interface.
     * This change may be applied in GeoAPI 4.0.
     * </div>
     *
     * @return origin of requirement.
     */
    @UML(identifier="requestor", obligation=MANDATORY, specification=ISO_19115_2, version=2003)
    Collection<? extends ResponsibleParty> getRequestors();

    /**
     * Person(s), or body(ies), to receive results of requirement.
     *
     * <div class="warning"><b>Upcoming API change — generalization</b><br>
     * As of ISO 19115:2014, {@code ResponsibleParty} is replaced by the {@link Responsibility} parent interface.
     * This change may be applied in GeoAPI 4.0.
     * </div>
     *
     * @return person(s), or body(ies), to receive results.
     */
    @UML(identifier="recipient", obligation=MANDATORY, specification=ISO_19115_2, version=2003)
    Collection<? extends ResponsibleParty> getRecipients();

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
     *
     * <div class="warning"><b>Upcoming API change — temporal schema</b><br>
     * As of Java 8, the {@code java.time} package is a better match for the different
     * types of date defined by ISO 19108 (<cite>Temporal Schema</cite>) or ISO 19103.
     * The return value of this method may be changed to {@link java.time.temporal.Temporal} in GeoAPI 4.0.
     * </div>
     *
     * @return date and time after which collection is no longer valid.
     */
    @UML(identifier="expiryDate", obligation=MANDATORY, specification=ISO_19115_2)
    Date getExpiryDate();

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
