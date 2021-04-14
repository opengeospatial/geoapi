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
import org.opengis.metadata.extent.Extent;
import org.opengis.util.InternationalString;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Describes the characteristics, spatial and temporal extent of the intended object to be
 * observed.
 *
 * @author  Cédric Briançon (Geomatys)
 * @version 3.0
 * @since   2.3
 */
@UML(identifier="MI_Objective", specification=ISO_19115_2)
public interface Objective {
    /**
     * Code used to identify the objective.
     *
     * @return objective identifiers.
     */
    @UML(identifier="identifier", obligation=MANDATORY, specification=ISO_19115_2)
    Collection<? extends Identifier> getIdentifiers();

    /**
     * Priority applied to the target.
     *
     * @return priority applied, or {@code null}.
     */
    @UML(identifier="priority", obligation=OPTIONAL, specification=ISO_19115_2)
    default InternationalString getPriority() {
        return null;
    }

    /**
     * Collection technique for the objective.
     *
     * @return collection technique for the objective.
     */
    @UML(identifier="type", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends ObjectiveType> getTypes() {
        return Collections.emptySet();
    }

    /**
     * Role or purpose performed by or activity performed at the objective.
     *
     * @return role or purpose performed by or activity performed at the objective.
     */
    @UML(identifier="function", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends InternationalString> getFunctions() {
        return Collections.emptyList();
    }

    /**
     * Extent information including the bounding box, bounding polygon, vertical and
     * temporal extent of the objective.
     *
     * @return extent information.
     */
    @UML(identifier="extent", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends Extent> getExtents() {
        return Collections.emptyList();
    }

    /**
     * Event or events associated with objective completion.
     *
     * <div class="note"><b>Known typo:</b>
     * "occurrence" is missing a "r" in the UML diagram of ISO 19115-2 specification.
     * The {@code UML} annotation below reflects that spelling.
     * The method reflects that spelling in GeoAPI 3.1, but will be changed to
     * {@code getObjectiveOccurrences()} in GeoAPI 4.0.</div>
     *
     * @return events associated with objective completion.
     */
    @UML(identifier="objectiveOccurence", obligation=MANDATORY, specification=ISO_19115_2)
    Collection<? extends Event> getObjectiveOccurences();

    /**
     * Pass of the platform over the objective.
     *
     * @return pass of the platform.
     */
    @UML(identifier="pass", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends PlatformPass> getPass() {
        return Collections.emptyList();
    }

    /**
     * Instrument which senses the objective data.
     *
     * @return instrument which senses the objective data.
     */
    @UML(identifier="sensingInstrument", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends Instrument> getSensingInstruments() {
        return Collections.emptyList();
    }
}
