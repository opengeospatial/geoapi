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

import org.opengis.annotation.UML;
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.identification.Progress;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Designations for the planning information related to meeting the data acquisition requirements.
 *
 * @author Cédric Briançon (Geomatys)
 *
 * @since GeoAPI 2.3
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
