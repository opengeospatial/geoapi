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
import org.opengis.util.InternationalString;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Designations for the measuring instruments.
 *
 * @author  Cédric Briançon (Geomatys)
 * @version 3.0
 * @since   2.3
 *
 * @navassoc - - - Citation
 * @navassoc 1 - - Identifier
 * @navassoc 1 - - Platform
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
