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
package org.opengis.metadata;

import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * List of names of feature types with the same spatial representation (same as spatial attributes).
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.0
 */
@UML(identifier="MD_FeatureTypeList", specification=ISO_19115)
public interface FeatureTypeList {
    /**
     * Instance of a type defined in the spatial schema.
     *
     * @return Instance of a type defined in the spatial schema.
     */
    @UML(identifier="spatialObject", obligation=MANDATORY, specification=ISO_19115)
    String getSpatialObject();

    /**
     * Name of the spatial schema used.
     *
     * @return Name of the spatial schema used.
     */
    @UML(identifier="spatialSchemaName", obligation=MANDATORY, specification=ISO_19115)
    String getSpatialSchemaName();
}
