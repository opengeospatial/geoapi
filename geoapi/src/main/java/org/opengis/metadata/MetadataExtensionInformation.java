/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata;

import java.util.Collection;
import java.util.Collections;
import org.opengis.metadata.citation.OnlineResource;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information describing metadata extensions.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="MD_MetadataExtensionInformation", specification=ISO_19115)
public interface MetadataExtensionInformation {
    /**
     * Information about on-line sources containing the community profile name and
     * the extended metadata elements and information for all new metadata elements.
     *
     * @return on-line sources to community profile name and extended metadata elements, or {@code null}.
     */
    @UML(identifier="extensionOnLineResource", obligation=OPTIONAL, specification=ISO_19115)
    default OnlineResource getExtensionOnLineResource() {
        return null;
    }

    /**
     * Provides information about a new metadata element, not found in ISO 19115,
     * which is required to describe the resource.
     *
     * @return new metadata element not found in ISO 19115.
     */
    @UML(identifier="extendedElementInformation", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends ExtendedElementInformation> getExtendedElementInformation() {
        return Collections.emptyList();
    }
}
