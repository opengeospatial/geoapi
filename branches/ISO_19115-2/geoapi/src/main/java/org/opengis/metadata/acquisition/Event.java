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

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Identification of a significant collection point within an operation.
 *
 * @author Cédric Briançon (Geomatys)
 *
 * @since GeoAPI 2.3
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
