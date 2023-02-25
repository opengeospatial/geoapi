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
package org.opengis.metadata.constraint;

import java.util.Collection;
import java.util.Collections;

import org.opengis.annotation.UML;
import org.opengis.util.InternationalString;
import org.opengis.metadata.citation.Responsibility;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.ISO_19115;


/**
 * Information about resource release constraints.
 *
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@UML(identifier="MD_Releasability", specification=ISO_19115)
public interface Releasability {
    /**
     * Parties to which the release statement applies.
     * Returns an empty collection if none.
     *
     * @return parties to which the release statement applies.
     */
    @UML(identifier="addressee", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends Responsibility> getAddressees() {
        return Collections.emptyList();
    }

    /**
     * Release statement.
     * May be {@code null} if unspecified.
     *
     * @return release statement, or {@code null} if none.
     */
    @UML(identifier="statement", obligation=OPTIONAL, specification=ISO_19115)
    default InternationalString getStatement() {
        return null;
    }

    /**
     * Components in determining releasability.
     * Returns an empty collection if none.
     *
     * @return components in determining releasability.
     */
    @UML(identifier="disseminationConstraints", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<Restriction> getDisseminationConstraints() {
        return Collections.emptySet();          // Use Set instead of List for hash-safe final classes.
    }
}
