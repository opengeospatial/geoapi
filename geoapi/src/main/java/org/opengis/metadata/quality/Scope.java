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
package org.opengis.metadata.quality;

import java.util.Collection;

import org.opengis.metadata.extent.Extent;
import org.opengis.metadata.maintenance.ScopeCode;
import org.opengis.metadata.maintenance.ScopeDescription;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Description of the data specified by the scope.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.0
 *
 * @navassoc 1 - - ScopeCode
 * @navassoc - - - ScopeDescription
 * @navassoc 1 - - Extent
 */
@UML(identifier="DQ_Scope", specification=ISO_19115)
public interface Scope {
    /**
     * Hierarchical level of the data specified by the scope.
     *
     * @return Hierarchical level of the data.
     */
    @UML(identifier="level", obligation=MANDATORY, specification=ISO_19115)
    ScopeCode getLevel();

    /**
     * Detailed description about the level of the data specified by the scope.
     * Shall be defined only if the {@linkplain #getLevel level} is not equal
     * to {@link ScopeCode#DATASET DATASET} or {@link ScopeCode#SERIES SERIES}.
     *
     * @return Detailed description about the level of the data.
     *
     * @since 2.1
     */
    @UML(identifier="levelDescription", obligation=CONDITIONAL, specification=ISO_19115)
    Collection<? extends ScopeDescription> getLevelDescription();

    /**
     * Information about the spatial, vertical and temporal extent of the data specified by the
     * scope.
     *
     * @return Information about the extent of the data, or {@code null}.
     */
    @UML(identifier="extent", obligation=OPTIONAL, specification=ISO_19115)
    Extent getExtent();
}
