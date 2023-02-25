/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2014-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
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
package org.opengis.metadata.identification;

import org.opengis.annotation.UML;
import org.opengis.metadata.citation.Citation;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.ISO_19115;


/**
 * Associated resource information.
 *
 * <p><b>Conditional properties:</b></p>
 * Following property has default method but shall nevertheless be implemented if the corresponding condition is met:
 * <ul>
 *   <li>{@linkplain #getMetadataReference() Metadata reference} is mandatory if the resource
 *       {@linkplain #getName() name} is not provided.</li>
 * </ul>
 *
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@UML(identifier="MD_AssociatedResource", specification=ISO_19115)
public interface AssociatedResource {
    /**
     * Citation information about the associated resource.
     *
     * @return citation information about the associated resource, or {@code null} if none.
     *
     * @condition Mandatory if the {@linkplain #getMetadataReference() metadata reference} is not documented.
     */
    @UML(identifier="name", obligation=CONDITIONAL, specification=ISO_19115)
    Citation getName();

    /**
     * Type of relation between the resources.
     *
     * @return the type of relation between the resources.
     */
    @UML(identifier="associationType", obligation=MANDATORY, specification=ISO_19115)
    AssociationType getAssociationType();

    /**
     * Type of initiative under which the associated resource was produced.
     *
     * @return the type of initiative under which the associated resource was produced, or {@code null} if none.
     */
    @UML(identifier="initiativeType", obligation=OPTIONAL, specification=ISO_19115)
    default InitiativeType getInitiativeType() {
        return null;
    }

    /**
     * Reference to the metadata of the associated resource.
     *
     * @return reference to the metadata of the associated resource, or {@code null} if none.
     *
     * @condition Mandatory if the {@linkplain #getName() name} is not documented.
     */
    @UML(identifier="metadataReference", obligation=CONDITIONAL, specification=ISO_19115)
    default Citation getMetadataReference() {
        return null;
    }
}
