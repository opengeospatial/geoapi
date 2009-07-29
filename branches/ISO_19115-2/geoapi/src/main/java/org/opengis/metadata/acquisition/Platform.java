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
import org.opengis.metadata.citation.ResponsibleParty;
import org.opengis.util.InternationalString;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Designation of the platform used to acquire the dataset.
 *
 * @author Cédric Briançon (Geomatys)
 *
 * @since GeoAPI 2.3
 */
@UML(identifier="MI_Platform", specification=ISO_19115_2)
public interface Platform {
    /**
     * Source where information about the platform is described.
     *
     * @return Source where information about the platform is described.
     */
    @UML(identifier="citation", obligation=OPTIONAL, specification=ISO_19115_2)
    Citation getCitation();

    /**
     * Unique identification of the platform.
     *
     * @return Unique identification of the platform.
     */
    @UML(identifier="identifier", obligation=MANDATORY, specification=ISO_19115_2)
    Identifier getIdentifier();

    /**
     * Narrative description of the platform supporting the instrument.
     *
     * @return Narrative description of the platform.
     */
    @UML(identifier="description", obligation=MANDATORY, specification=ISO_19115_2)
    InternationalString getDescription();

    /**
     * Organization responsible for building, launch, or operation of the platform.
     *
     * @return Organization responsible for building, launch, or operation of the platform.
     */
    @UML(identifier="sponsor", obligation=OPTIONAL, specification=ISO_19115_2)
    Collection<? extends ResponsibleParty> getSponsors();

    /**
     * Instrument(s) mounted on a platform.
     *
     * @return Instrument(s) mounted on a platform.
     */
    @UML(identifier="instrument", obligation=MANDATORY, specification=ISO_19115_2)
    Collection<? extends Instrument> getInstruments();
}
