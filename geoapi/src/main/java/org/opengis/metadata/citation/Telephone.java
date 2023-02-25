/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.citation;

import java.util.Collection;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Telephone numbers for contacting the responsible individual or organization.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @version 3.0
 * @since   1.0
 */
@UML(identifier="CI_Telephone", specification=ISO_19115)
public interface Telephone {
    /**
     * Telephone numbers by which individuals can speak to the responsible organization or individual.
     *
     * @return Telephone numbers by which individuals can speak to the responsible organization or individual.
     *
     * @since 2.1
     */
    @UML(identifier="voice", obligation=OPTIONAL, specification=ISO_19115)
    Collection<String> getVoices();

    /**
     * Telephone numbers of a facsimile machine for the responsible organization or individual.
     *
     * @return Telephone numbers of a facsimile machine for the responsible organization or individual.
     *
     * @since 2.1
     */
    @UML(identifier="facsimile", obligation=OPTIONAL, specification=ISO_19115)
    Collection<String> getFacsimiles();
}
