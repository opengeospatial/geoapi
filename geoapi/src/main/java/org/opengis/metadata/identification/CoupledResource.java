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
package org.opengis.metadata.identification;

import java.util.List;
import java.util.Collection;
import org.opengis.annotation.UML;
import org.opengis.util.ScopedName;
import org.opengis.metadata.citation.Citation;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.ISO_19115;


/**
 * Links a given operation name (mandatory attribute of {@link OperationMetadata})
 * with a resource identified by an "identifier".
 *
 * <p><b>Constraint:</b></p>
 * <ul>
 *   <li>For one {@code CoupledResource} either {@linkplain #getResources() resources} or
 *       {@linkplain #getResourceReferences() resource references} should be used
 *       (not both for the same {@code CoupledResource}).</li>
 * </ul>
 *
 * @author  Rémi Maréchal (Geomatys)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@UML(identifier="SV_CoupledResource", specification=ISO_19115)
public interface CoupledResource {
    /**
     * Scoped identifier of the resource in the context of the given service instance.
     * This is the name of the resources (for example dataset) as it is used by a service instance
     *
     * <div class="note"><b>Examples:</b> layer name or feature type name.</div>
     *
     * @return scoped identifier of the resource in the context of the given service instance, or {@code null} if none.
     */
    @UML(identifier="scopedName", obligation=OPTIONAL, specification=ISO_19115)
    default ScopedName getScopedName() {
        return null;
    }

    /**
     * References to the resource on which the services operates.
     * Returns an empty collection if none.
     *
     * @return references to the resource on which the services operates.
     *
     * @condition Only one of {@linkplain #getResources() resources} and resource references should be non-empty.
     *
     * @see DataIdentification#getCitation()
     */
    @UML(identifier="resourceReference", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends Citation> getResourceReferences() {
        return List.of();
    }

    /**
     * The tightly coupled resources.
     * Returns an empty collection if none.
     *
     * @return tightly coupled resources.
     *
     * @condition Only one of resources and {@linkplain #getResourceReferences() resource references} should be non-empty.
     */
    @UML(identifier="resource", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends DataIdentification> getResources() {
        return List.of();
    }

    /**
     * The service operation.
     *
     * @return the service operation, or {@code null} if none.
     */
    @UML(identifier="operation", obligation=OPTIONAL, specification=ISO_19115)
    default OperationMetadata getOperation() {
        return null;
    }
}
