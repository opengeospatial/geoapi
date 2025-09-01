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

import java.util.List;
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
 * @version 3.1
 * @since   2.3
 */
@UML(identifier="MI_Instrument", specification=ISO_19115_2)
public interface Instrument {
    /**
     * Complete citation of the instrument.
     *
     * @return complete citation of the instrument.
     */
    @UML(identifier="citation", obligation=OPTIONAL, specification=ISO_19115_2)
    default Collection<? extends Citation> getCitations() {
        return List.of();
    }

    /**
     * Unique identification of the instrument.
     *
     * @return unique identification of the instrument.
     */
    @UML(identifier="identifier", obligation=MANDATORY, specification=ISO_19115_2)
    Identifier getIdentifier();

    /**
     * Name of the type of instrument. Examples: framing, line-scan, push-broom, pan-frame.
     *
     * @return type of instrument.
     */
    @UML(identifier="type", obligation=MANDATORY, specification=ISO_19115_2)
    InternationalString getType();

    /**
     * Textual description of the instrument.
     *
     * @return textual description, or {@code null}.
     */
    @UML(identifier="description", obligation=OPTIONAL, specification=ISO_19115_2)
    default InternationalString getDescription() {
        return null;
    }

    /**
     * Platform on which the instrument is mounted.
     *
     * @return platform on which the instrument is mounted, or {@code null}.
     */
    @UML(identifier="mountedOn", obligation=OPTIONAL, specification=ISO_19115_2)
    default Platform getMountedOn() {
        return null;
    }
}
