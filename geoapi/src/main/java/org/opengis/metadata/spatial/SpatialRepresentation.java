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
package org.opengis.metadata.spatial;

import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;
import org.opengis.metadata.maintenance.Scope;

import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.Obligation.OPTIONAL;


/**
 * Digital mechanism used to represent spatial information.
 *
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 * @since   2.0
 */
@Classifier(Stereotype.ABSTRACT)
@UML(identifier="MD_SpatialRepresentation", specification=ISO_19115)
public interface SpatialRepresentation {
    /**
     * Level and extent of the spatial representation.
     *
     * @return level and extent of the spatial representation, or {@code null} if none.
     *
     * @since 3.1
     */
    @UML(identifier="scope", obligation=OPTIONAL, specification=ISO_19115, version=2018)
    default Scope getScope() {
        return null;
    }
}
