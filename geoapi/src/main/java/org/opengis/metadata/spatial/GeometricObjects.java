/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2011 Open Geospatial Consortium, Inc.
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
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Number of objects, listed by geometric object type, used in the dataset.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.0
 *
 * @navassoc - - - GeometricObjectType
 */
@UML(identifier="MD_GeometricObjects", specification=ISO_19115)
public interface GeometricObjects {
    /**
     * Name of point and vector spatial objects used to locate zero-, one-, and twodimensional
     * spatial locations in the dataset.
     *
     * @return Name of spatial objects used to locate spatial locations in the dataset.
     */
    @UML(identifier="geometricObjectType", obligation=MANDATORY, specification=ISO_19115)
    GeometricObjectType getGeometricObjectType();

    /**
     * Total number of the point or vector object type occurring in the dataset.
     *
     * @return Total number of the point or vector object type, or {@code null}.
     */
    @UML(identifier="geometricObjectCount", obligation=OPTIONAL, specification=ISO_19115)
    Integer getGeometricObjectCount();
}
