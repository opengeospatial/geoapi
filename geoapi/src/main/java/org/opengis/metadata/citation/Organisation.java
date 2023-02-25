/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2014-2023 Open Geospatial Consortium, Inc.
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
import java.util.Collections;
import org.opengis.annotation.UML;
import org.opengis.metadata.identification.BrowseGraphic;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.ISO_19115;


/**
 * Information about the party if the party is an organisation.
 *
 * <p><b>Conditional properties:</b></p>
 * Following properties have default methods but shall nevertheless be implemented if the corresponding condition is met:
 * <ul>
 *   <li>At least one of {@linkplain #getIndividual() individual} and {@linkplain #getLogo() logo} properties
 *       shall be documented if the {@linkplain #getName() name} (in parent interface) is not documented.</li>
 * </ul>
 *
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@UML(identifier="CI_Organisation", specification=ISO_19115)
public interface Organisation extends Party {
    /**
     * Graphics identifying organisation.
     * Returns an empty collection if there is none.
     *
     * @return graphic identifying organisation.
     *
     * @condition Mandatory if {@linkplain #getName() name} or {@linkplain Individual#getPositionName() position name}
     *            is not documented.
     */
    @UML(identifier="logo", obligation=CONDITIONAL, specification=ISO_19115)
    default Collection<? extends BrowseGraphic> getLogo() {
        return Collections.emptyList();
    }

    /**
     * Individuals in the named organisation.
     * Returns an empty collection if there is none.
     *
     * @return individuals in the named organisation.
     */
    @UML(identifier="individual", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends Individual> getIndividual() {
        return Collections.emptyList();
    }
}
