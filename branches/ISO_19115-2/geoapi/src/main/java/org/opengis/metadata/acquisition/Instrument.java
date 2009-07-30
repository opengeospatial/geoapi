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
import org.opengis.metadata.citation.Citation;
import org.opengis.util.InternationalString;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Designations for the measuring instruments.
 *
 * @author Cédric Briançon (Geomatys)
 *
 * @since GeoAPI 2.3
 */
@UML(identifier="MI_Instrument", specification=ISO_19115_2)
public interface Instrument {
    /**
     * Complete citation of the instrument.
     *
     * @return Complete citation of the instrument.
     */
    @UML(identifier="citation", obligation=OPTIONAL, specification=ISO_19115_2)
    Collection<? extends Citation> getCitations();

    /**
     * Unique identification of the instrument.
     *
     * @return Unique identification of the instrument.
     */
    @UML(identifier="identifier", obligation=MANDATORY, specification=ISO_19115_2)
    Identifier getIdentifier();

    /**
     * Name of the type of instrument. Examples: framing, line-scan, push-broom, pan-frame.
     *
     * @return Type of instrument.
     */
    @UML(identifier="type", obligation=MANDATORY, specification=ISO_19115_2)
    InternationalString getType();

    /**
     * Textual description of the instrument.
     *
     * @return Textual description.
     */
    @UML(identifier="description", obligation=OPTIONAL, specification=ISO_19115_2)
    InternationalString getDescription();

    /**
     * Platform on which the instrument is mounted.
     *
     * @return Platform on which the instrument is mounted.
     */
    @UML(identifier="mountedOn", obligation=OPTIONAL, specification=ISO_19115_2)
    Platform getMountedOn();
}
