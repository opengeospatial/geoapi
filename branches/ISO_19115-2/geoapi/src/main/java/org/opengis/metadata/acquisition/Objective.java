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
import org.opengis.metadata.Identifier;
import org.opengis.metadata.extent.Extent;
import org.opengis.util.InternationalString;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Describes the characteristics, spatial and temporal extent of the intended object to be
 * observed.
 *
 * @author Cédric Briançon (Geomatys)
 *
 * @since GeoAPI 2.3
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
