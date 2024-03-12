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
package org.opengis.metadata.quality;

import java.util.Collection;
import java.util.Iterator;

import org.opengis.metadata.extent.Extent;
import org.opengis.metadata.maintenance.ScopeCode;
import org.opengis.metadata.maintenance.ScopeDescription;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.ISO_19115;


/**
 * Description of the data specified by the scope.
 *
 * <div class="warning"><b>Upcoming API change — renaming</b><br>
 * As of ISO 19115:2014,
 * {@code DQ_Scope} (from {@link org.opengis.metadata.quality}) is replaced by
 * {@code MD_Scope} (from {@link org.opengis.metadata.maintenance}).
 * This interface will be deprecated in GeoAPI 4.0.
 * </div>
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   2.0
 */
@Classifier(Stereotype.DATATYPE)
@UML(identifier="DQ_Scope", specification=ISO_19115, version=2003)
public interface Scope extends org.opengis.metadata.maintenance.Scope {
    /**
     * Hierarchical level of the data specified by the scope.
     *
     * @return hierarchical level of the data.
     */
    @Override
    @UML(identifier="level", obligation=MANDATORY, specification=ISO_19115)
    ScopeCode getLevel();

    /**
     * Information about the spatial, vertical and temporal extent of the data specified by the scope.
     *
     * @return information about the extent of the data, or {@code null}.
     *
     * @deprecated As of ISO 19115:2014, replaced by {@link #getExtents()}.
     */
    @Deprecated(since="3.1")
    @UML(identifier="extent", obligation=OPTIONAL, specification=ISO_19115, version=2003)
    default Extent getExtent() {
        Iterator<? extends Extent> it = getExtents().iterator();
        return it.hasNext() ? it.next() : null;
    }

    /**
     * Detailed description about the level of the data specified by the scope.
     * Shall be defined only if the {@linkplain #getLevel() level} is not equal
     * to {@link ScopeCode#DATASET} or {@link ScopeCode#SERIES}.
     *
     * @return detailed description about the level of the data.
     */
    @Override
    @UML(identifier="levelDescription", obligation=CONDITIONAL, specification=ISO_19115)
    Collection<? extends ScopeDescription> getLevelDescription();
}
