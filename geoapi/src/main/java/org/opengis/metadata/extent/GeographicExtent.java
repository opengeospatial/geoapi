/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2024 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.extent;

import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Base interface for geographic area of the resource.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @version 3.1
 * @since   1.0
 */
@Classifier(Stereotype.ABSTRACT)
@UML(identifier="EX_GeographicExtent", specification=ISO_19115)
public interface GeographicExtent {
    /**
     * Indication of whether the bounding polygon encompasses an area covered by the data
     * (<dfn>inclusion</dfn>) or an area where data is not present (<dfn>exclusion</dfn>).
     * The default value is {@code true}.
     *
     * @return {@code true} for inclusion, {@code false} for exclusion, or {@code null} if unspecified.
     *
     * @departure rename
     *   The ISO identifier is {@code "extentTypeCode"} and defines the value 1 for inclusion,
     *   and 0 for exclusion. GeoAPI uses a name which better expresses the meaning of the return value.
     */
    @UML(identifier="extentTypeCode", obligation=OPTIONAL, specification=ISO_19115)
    default Boolean getInclusion() {
        return null;
    }
}
