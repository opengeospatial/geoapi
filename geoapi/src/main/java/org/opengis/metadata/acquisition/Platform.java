/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2009-2011 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
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
 * @author  Cédric Briançon (Geomatys)
 * @version 3.0
 * @since   2.3
 *
 * @navassoc 1 - - Citation
 * @navassoc 1 - - Identifier
 * @navassoc - - - ResponsibleParty
 * @navassoc - - - Instrument
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
