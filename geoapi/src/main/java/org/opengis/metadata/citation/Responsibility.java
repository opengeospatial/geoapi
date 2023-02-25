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
package org.opengis.metadata.citation;

import java.util.Collection;
import java.util.Collections;
import org.opengis.annotation.UML;
import org.opengis.metadata.extent.Extent;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.ISO_19115;


/**
 * Information about the party and their role.
 *
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@UML(identifier="CI_Responsibility", specification=ISO_19115)
public interface Responsibility {
    /**
     * Function performed by the responsible party.
     *
     * @return function performed by the responsible party.
     */
    @UML(identifier="role", obligation=MANDATORY, specification=ISO_19115)
    Role getRole();

    /**
     * Spatial or temporal extents of the role.
     * Returns an empty collection if none.
     *
     * @return spatial or temporal extent of the role.
     */
    @UML(identifier="extent", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends Extent> getExtents() {
        return Collections.emptyList();
    }

    /**
     * Information about the parties.
     * Returns an empty collection if none.
     *
     * @return information about the parties.
     */
    @UML(identifier="party", obligation=MANDATORY, specification=ISO_19115)
    Collection<? extends Party> getParties();
}
