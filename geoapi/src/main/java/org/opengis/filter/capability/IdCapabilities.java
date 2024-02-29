/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2005-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.filter.capability;

import java.util.Collection;
import org.opengis.util.LocalName;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Specification.ISO_19143;


/**
 * Capabilities used to convey supported identifier operators.
 *
 * @author  Markus Schneider (lat/lon)
 * @author  Justin Deoliveira (The Open Planning Project)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 *
 * @see FilterCapabilities#getIdCapabilities()
 *
 * @since 3.1
 */
@UML(identifier="IdCapabilities", specification=ISO_19143)
public interface IdCapabilities {
    /**
     * Declares the names of the properties used for resource identifiers.
     * It is a list of element names that represent the resource identifier elements that the service supports.
     * These element names are considered synonyms.
     *
     * <div class="note"><b>Example:</b>
     * a catalogue may include the following elements:
     * <ul>
     *   <li>{@code cat:RecordId}</li>
     *   <li>{@code fes:ResourceId}</li>
     * </ul>
     * indicating that the service can accept the {@code cat:RecordId} or {@code fes:ResourceId} element
     * as predicates in a filter expression.
     * </div>
     *
     * @return names of properties used for resource identifiers.
     */
    @UML(identifier="resourceIdentifiers", obligation=MANDATORY, specification=ISO_19143)
    Collection<? extends LocalName> getResourceIdentifiers();
}
