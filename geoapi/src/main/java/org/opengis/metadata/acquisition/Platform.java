/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2009-2023 Open Geospatial Consortium, Inc.
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
import java.util.Collections;

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
 * @version 3.1
 * @since   2.3
 */
@UML(identifier="MI_Platform", specification=ISO_19115_2)
public interface Platform {
    /**
     * Source where information about the platform is described.
     *
     * @return source where information about the platform is described, or {@code null}.
     */
    @UML(identifier="citation", obligation=OPTIONAL, specification=ISO_19115_2)
    default Citation getCitation() {
        return null;
    }

    /**
     * Unique identification of the platform.
     *
     * @return unique identification of the platform.
     */
    @UML(identifier="identifier", obligation=MANDATORY, specification=ISO_19115_2)
    Identifier getIdentifier();

    /**
     * Narrative description of the platform supporting the instrument.
     *
     * @return narrative description of the platform.
     */
    @UML(identifier="description", obligation=MANDATORY, specification=ISO_19115_2)
    InternationalString getDescription();

    /**
     * Organization responsible for building, launch, or operation of the platform.
     *
     * <div class="warning"><b>Upcoming API change — generalization</b><br>
     * As of ISO 19115:2014, {@code ResponsibleParty} is replaced by the {@link Responsibility} parent interface.
     * This change may be applied in GeoAPI 4.0.
     * </div>
     *
     * @return organization responsible for building, launch, or operation of the platform.
     */
    @UML(identifier="sponsor", obligation=OPTIONAL, specification=ISO_19115_2, version=2003)
    default Collection<? extends ResponsibleParty> getSponsors() {
        return Collections.emptyList();
    }

    /**
     * Instrument(s) mounted on a platform.
     *
     * @return instrument(s) mounted on a platform.
     */
    @UML(identifier="instrument", obligation=MANDATORY, specification=ISO_19115_2)
    Collection<? extends Instrument> getInstruments();
}
