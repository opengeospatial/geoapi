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
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;
import org.opengis.util.InternationalString;
import org.opengis.metadata.Identifier;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.ISO_19115;


/**
 * Information about the individual and / or organisation of the party.
 *
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@Classifier(Stereotype.ABSTRACT)
@UML(identifier="CI_Party", specification=ISO_19115)
public interface Party {
    /**
     * Name of the party.
     *
     * @return name of the party, or {@code null} if none.
     *
     * @condition Mandatory if the {@linkplain Organisation#getLogo() logo} and the
     *            {@linkplain Individual#getPositionName() position name} are not documented.
     */
    @UML(identifier="name", obligation=CONDITIONAL, specification=ISO_19115)
    InternationalString getName();

    /**
     * Identifiers of the party.
     *
     * @return identifiers of the party, or an empty collection if none.
     *
     * @departure rename
     *   Renamed from "{@code partyIdentifier}" to "{@code identifier}"
     *   for providing a unified method signature for identifiers.
     */
    @UML(identifier="partyIdentifier", obligation=OPTIONAL, specification=ISO_19115, version=2018)
    default Collection<? extends Identifier> getIdentifiers() {
        return Collections.emptyList();
    }

    /**
     * Contact information for the party.
     *
     * @return contact information for the party, or an empty collection if none.
     */
    @UML(identifier="contactInfo", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends Contact> getContactInfo() {
        return Collections.emptyList();
    }
}
