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
package org.opengis.metadata;

import org.opengis.annotation.UML;
import org.opengis.metadata.maintenance.ScopeCode;
import org.opengis.util.InternationalString;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.ISO_19115;


/**
 * Class of information to which the referencing entity applies.
 *
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@UML(identifier="MD_MetadataScope", specification=ISO_19115)
public interface MetadataScope {
    /**
     * Code for the scope.
     *
     * @return code for the scope.
     */
    @UML(identifier="resourceScope", obligation=MANDATORY, specification=ISO_19115)
    ScopeCode getResourceScope();

    /**
     * Description of the scope.
     * May be {@code null} if unspecified.
     *
     * @return the description of the scope, or {@code null} if none.
     */
    @UML(identifier="name", obligation=OPTIONAL, specification=ISO_19115)
    default InternationalString getName() {
        return null;
    }
}
