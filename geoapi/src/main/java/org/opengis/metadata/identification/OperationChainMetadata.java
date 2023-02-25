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
import org.opengis.annotation.UML;
import org.opengis.util.InternationalString;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.ISO_19115;


/**
 * Operation chain information.
 *
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@UML(identifier="SV_OperationChainMetadata", specification=ISO_19115)
public interface OperationChainMetadata {
    /**
     * The name as used by the service for this chain.
     *
     * @return name as used by the service for this chain.
     */
    @UML(identifier="name", obligation=MANDATORY, specification=ISO_19115)
    InternationalString getName();

    /**
     * A narrative explanation of the services in the chain and resulting output.
     *
     * @return narrative explanation of the services in the chain and resulting output, or {@code null} if none.
     */
    @UML(identifier="description", obligation=OPTIONAL, specification=ISO_19115)
    default InternationalString getDescription() {
        return null;
    }

    /**
     * Ordered information about the operations applied by the chain.
     *
     * @return information about the operations applied by the chain.
     */
    @UML(identifier="operation", obligation=MANDATORY, specification=ISO_19115)
    List<? extends OperationMetadata> getOperations();
}
