/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.acquisition;

import java.util.Collection;

import java.util.Date;
import org.opengis.annotation.UML;
import org.opengis.metadata.Identifier;
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.citation.ResponsibleParty;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Requirement to be satisfied by the planned data acquisition.
 *
 * @author Cédric Briançon (Geomatys)
 *
 * @since GeoAPI 2.3
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
    Collection<? extends Plan> getSatisifedPlans();
}
