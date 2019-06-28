/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2009-2019 Open Geospatial Consortium, Inc.
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
