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
import java.util.Date;

import org.opengis.annotation.UML;
import org.opengis.metadata.Identifier;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Identification of a significant collection point within an operation.
 *
 * @author  Cédric Briançon (Geomatys)
 * @version 3.0
 * @since   2.3
 *
 * @navassoc 1 - - Identifier
 * @navassoc 1 - - Trigger
 * @navassoc 1 - - Context
 * @navassoc 1 - - Sequence
 * @navassoc - - - Objective
 * @navassoc 1 - - PlatformPass
 * @navassoc - - - Instrument
 */
@UML(identifier="MI_Event", specification=ISO_19115_2)
public interface Event {
    /**
     * Event name or number.
     *
     * @return Event name or number.
     */
    @UML(identifier="identifier", obligation=MANDATORY, specification=ISO_19115_2)
    Identifier getIdentifier();

    /**
     * Initiator of the event.
     *
     * @return Initiator of the event.
     */
    @UML(identifier="trigger", obligation=MANDATORY, specification=ISO_19115_2)
    Trigger getTrigger();

    /**
     * Meaning of the event.
     *
     * @return Meaning of the event.
     */
    @UML(identifier="context", obligation=MANDATORY, specification=ISO_19115_2)
    Context getContext();

    /**
     * Relative time ordering of the event.
     *
     * @return Relative time ordering.
     */
    @UML(identifier="sequence", obligation=MANDATORY, specification=ISO_19115_2)
    Sequence getSequence();

    /**
     * Time the event occurred.
     * <p>
     * <TABLE WIDTH="80%" ALIGN="center" CELLPADDING="18" BORDER="4" BGCOLOR="#FFE0B0">
     *   <TR><TD>
     *     <P align="justify"><B>Warning:</B> The return type of this method may change
     *     in GeoAPI 3.1 release. It may be replaced by a type matching more closely
     *     either ISO 19108 (<cite>Temporal Schema</cite>) or ISO 19103.</P>
     *   </TD></TR>
     * </TABLE>
     *
     * @return Time the event occurred
     */
    @UML(identifier="time", obligation=MANDATORY, specification=ISO_19115_2)
    Date getTime();

    /**
     * Objective or objectives satisfied by an event.
     *
     * @return Objectives satisfied by an event.
     */
    @UML(identifier="expectedObjective", obligation=OPTIONAL, specification=ISO_19115_2)
    Collection<? extends Objective> getExpectedObjectives();

    /**
     * Pass during which an event occurs.
     *
     * @return Pass during which an event occurs.
     */
    @UML(identifier="relatedPass", obligation=OPTIONAL, specification=ISO_19115_2)
    PlatformPass getRelatedPass();

    /**
     * Instrument or instruments for which the event is meaningful.
     *
     * @return Instruments for which the event is meaningful.
     */
    @UML(identifier="relatedSensor", obligation=OPTIONAL, specification=ISO_19115_2)
    Collection<? extends Instrument> getRelatedSensors();
}
