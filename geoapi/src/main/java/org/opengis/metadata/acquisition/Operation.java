/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2009-2021 Open Geospatial Consortium, Inc.
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
import org.opengis.metadata.Identifier;
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.identification.Progress;
import org.opengis.util.InternationalString;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Designations for the operation used to acquire the dataset.
 *
 * @author  Cédric Briançon (Geomatys)
 * @version 3.1
 * @since   2.3
 */
@UML(identifier="MI_Operation", specification=ISO_19115_2)
public interface Operation {
    /**
     * Description of the mission on which the platform observations are made and the
     * objectives of that mission.
     *
     * @return description of the mission, or {@code null}.
     */
    @UML(identifier="description", obligation=OPTIONAL, specification=ISO_19115_2)
    default InternationalString getDescription() {
        return null;
    }

    /**
     * Identification of the mission.
     *
     * @return identification of the mission, or {@code null}.
     */
    @UML(identifier="citation", obligation=OPTIONAL, specification=ISO_19115_2)
    default Citation getCitation() {
        return null;
    }

    /**
     * Unique identification of the operation.
     *
     * @return unique identification of the operation.
     */
    @UML(identifier="identifier", obligation=OPTIONAL, specification=ISO_19115_2)
    default Identifier getIdentifier() {
        return null;
    }

    /**
     * Status of the data acquisition.
     *
     * @return status of the data acquisition.
     */
    @UML(identifier="status", obligation=MANDATORY, specification=ISO_19115_2)
    Progress getStatus();

    /**
     * Collection technique for the operation.
     *
     * @return collection technique for the operation, or {@code null}.
     */
    @UML(identifier="type", obligation=OPTIONAL, specification=ISO_19115_2)
    default OperationType getType() {
        return null;
    }

    /**
     * Sub-missions that make up part of a larger mission.
     *
     * @return sub-missions.
     */
    @UML(identifier="childOperation", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends Operation> getChildOperations() {
        return Collections.emptyList();
    }

    /**
     * Object(s) or area(s) of interest to be sensed.
     *
     * @return object(s) or area(s) of interest.
     */
    @UML(identifier="objective", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends Objective> getObjectives() {
        return Collections.emptyList();
    }

    /**
     * Heritage of the operation.
     *
     * @return heritage of the operation, or {@code null}.
     */
    @UML(identifier="parentOperation", obligation=OPTIONAL, specification=ISO_19115_2)
    default Operation getParentOperation() {
        return null;
    }

    /**
     * Plan satisfied by the operation.
     *
     * @return plan satisfied by the operation, or {@code null}.
     */
    @UML(identifier="plan", obligation=OPTIONAL, specification=ISO_19115_2)
    default Plan getPlan() {
        return null;
    }

    /**
     * Platform (or platforms) used in the operation.
     *
     * @return Platforms used in the operation.
     */
    @UML(identifier="platform", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends Platform> getPlatforms() {
        return Collections.emptyList();
    }

    /**
     * Record of an event occurring during an operation.
     *
     * @return record of an event occurring during an operation.
     */
    @UML(identifier="significantEvent", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends Event> getSignificantEvents() {
        return Collections.emptyList();
    }
}
