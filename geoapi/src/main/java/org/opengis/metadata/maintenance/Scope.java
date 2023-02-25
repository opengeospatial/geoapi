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
package org.opengis.metadata.maintenance;

import java.util.Collection;
import java.util.Collections;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;
import org.opengis.metadata.extent.Extent;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * The target resource and physical extent for which information is reported.
 *
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 * @since   3.1
 */
@Classifier(Stereotype.DATATYPE)
@UML(identifier="MD_Scope", specification=ISO_19115)
public interface Scope {
    /**
     * Hierarchical level of the data specified by the scope.
     *
     * @return hierarchical level of the data.
     */
    @UML(identifier="level", obligation=MANDATORY, specification=ISO_19115)
    ScopeCode getLevel();

    /**
     * Information about the spatial, vertical and temporal extents of the resource specified by the scope.
     *
     * @return information about the extent of the resource.
     */
    @UML(identifier="extent", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends Extent> getExtents() {
        return Collections.emptyList();
    }

    /**
     * Detailed description about the level of the data specified by the scope.
     *
     * @return detailed description about the level of the data.
     */
    @UML(identifier="levelDescription", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends ScopeDescription> getLevelDescription() {
        return Collections.emptyList();
    }
}
