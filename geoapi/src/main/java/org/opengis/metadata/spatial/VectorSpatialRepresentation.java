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

import java.util.Collection;
import java.util.Collections;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information about the vector spatial objects in the dataset.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="MD_VectorSpatialRepresentation", specification=ISO_19115)
public interface VectorSpatialRepresentation extends SpatialRepresentation {
    /**
     * Code which identifies the degree of complexity of the spatial relationships.
     *
     * @return the degree of complexity of the spatial relationships, or {@code null}.
     */
    @UML(identifier="topologyLevel", obligation=OPTIONAL, specification=ISO_19115)
    default TopologyLevel getTopologyLevel() {
        return null;
    }

    /**
     * Information about the geometric objects used in the dataset.
     *
     * @return information about the geometric objects used in the dataset, or {@code null}.
     */
    @UML(identifier="geometricObjects", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends GeometricObjects> getGeometricObjects() {
        return Collections.emptyList();
    }
}
