/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2009-2011 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
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
