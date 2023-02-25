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

import java.util.Date;
import java.util.Collection;

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
 * @version 3.0
 * @since   2.3
 *
 * @navassoc 1 - - Citation
 * @navassoc 1 - - Identifier
 * @navassoc - - - ResponsibleParty
 * @navassoc 1 - - Priority
 * @navassoc 1 - - RequestDate
 * @navassoc - - - Plan
 */
@UML(identifier="MI_Requirement", specification=ISO_19115_2)
public interface Requirement {
    /**
     * Identification of reference or guidance material for the requirement.
     *
     * @return Identification of reference or guidance material.
     */
    @UML(identifier="citation", obligation=OPTIONAL, specification=ISO_19115_2)
    Citation getCitation();

    /**
     * Unique name, or code, for the requirement.
     *
     * @return Unique name or code.
     */
    @UML(identifier="identifier", obligation=MANDATORY, specification=ISO_19115_2)
    Identifier getIdentifier();

    /**
     * Origin of requirement.
     *
     * @return Origin of requirement.
     */
    @UML(identifier="requestor", obligation=MANDATORY, specification=ISO_19115_2)
    Collection<? extends ResponsibleParty> getRequestors();

    /**
     * Person(s), or body(ies), to receive results of requirement.
     *
     * @return Person(s), or body(ies), to receive results.
     */
    @UML(identifier="recipient", obligation=MANDATORY, specification=ISO_19115_2)
    Collection<? extends ResponsibleParty> getRecipients();

    /**
     * Relative ordered importance, or urgency, of the requirement.
     *
     * @return Relative ordered importance, or urgency.
     */
    @UML(identifier="priority", obligation=MANDATORY, specification=ISO_19115_2)
    Priority getPriority();

    /**
     * Required or preferred acquisition date and time.
     *
     * @return Required or preferred acquisition date and time.
     */
    @UML(identifier="requestedDate", obligation=MANDATORY, specification=ISO_19115_2)
    RequestedDate getRequestedDate();

    /**
     * Date and time after which collection is no longer valid.
     * <p>
     * <TABLE WIDTH="80%" ALIGN="center" CELLPADDING="18" BORDER="4" BGCOLOR="#FFE0B0">
     *   <TR><TD>
     *     <P align="justify"><B>Warning:</B> The return type of this method may change
     *     in GeoAPI 3.1 release. It may be replaced by a type matching more closely
     *     either ISO 19108 (<cite>Temporal Schema</cite>) or ISO 19103.</P>
     *   </TD></TR>
     * </TABLE>
     *
     * @return Date and time after which collection is no longer valid.
     */
    @UML(identifier="expiryDate", obligation=MANDATORY, specification=ISO_19115_2)
    Date getExpiryDate();

    /**
     * Plan that identifies solution to satisfy the requirement.
     *
     * @return Plan that identifies solution to satisfy the requirement.
     */
    @UML(identifier="satisfiedPlan", obligation=OPTIONAL, specification=ISO_19115_2)
    Collection<? extends Plan> getSatisfiedPlans();
}
