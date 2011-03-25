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

import java.util.Collection;

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
 *
 * @navassoc - - - Identifier
 * @navassoc - - - ObjectiveType
 * @navassoc - - - Extent
 * @navassoc - - - Event
 * @navassoc - - - PlatformPass
 * @navassoc - - - Instrument
 */
@UML(identifier="MI_Objective", specification=ISO_19115_2)
public interface Objective {
    /**
     * Code used to identify the objective.
     *
     * @return Identify the objective.
     */
    @UML(identifier="identifier", obligation=MANDATORY, specification=ISO_19115_2)
    Collection<? extends Identifier> getIdentifiers();

    /**
     * Priority applied to the target.
     *
     * @return Priority applied.
     */
    @UML(identifier="priority", obligation=OPTIONAL, specification=ISO_19115_2)
    InternationalString getPriority();

    /**
     * Collection technique for the objective.
     *
     * @return Collection technique for the objective.
     */
    @UML(identifier="type", obligation=OPTIONAL, specification=ISO_19115_2)
    Collection<? extends ObjectiveType> getTypes();

    /**
     * Role or purpose performed by or activity performed at the objective.
     *
     * @return Role or purpose performed by or activity performed at the objective.
     */
    @UML(identifier="function", obligation=OPTIONAL, specification=ISO_19115_2)
    Collection<? extends InternationalString> getFunctions();

    /**
     * Extent information including the bounding box, bounding polygon, vertical and
     * temporal extent of the objective.
     *
     * @return Extent information.
     */
    @UML(identifier="extent", obligation=OPTIONAL, specification=ISO_19115_2)
    Collection<? extends Extent> getExtents();

    /**
     * Event or events associated with objective completion.
     *
     * @return Events associated with objective completion.
     */
    @UML(identifier="objectiveOccurence", obligation=MANDATORY, specification=ISO_19115_2)
    Collection<? extends Event> getObjectiveOccurences();

    /**
     * Pass of the platform over the objective.
     *
     * @return Pass of the platform.
     */
    @UML(identifier="pass", obligation=OPTIONAL, specification=ISO_19115_2)
    Collection<? extends PlatformPass> getPass();

    /**
     * Instrument which senses the objective data.
     *
     * @return Instrument which senses the objective data.
     */
    @UML(identifier="sensingInstrument", obligation=OPTIONAL, specification=ISO_19115_2)
    Collection<? extends Instrument> getSensingInstruments();
}
