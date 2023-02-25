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
import org.opengis.metadata.lineage.Lineage;
import org.opengis.annotation.UML;
import org.opengis.annotation.Profile;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.ComplianceLevel.*;


/**
 * Quality information for the data specified by a data quality scope.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.0
 *
 * @navassoc 1 - - Scope
 * @navassoc - - - Element
 * @navassoc 1 - - Lineage
 */
@UML(identifier="DQ_DataQuality", specification=ISO_19115)
public interface DataQuality {
    /**
     * The specific data to which the data quality information applies.
     *
     * @return The specific data to which the data quality information applies.
     */
    @UML(identifier="scope", obligation=MANDATORY, specification=ISO_19115)
    Scope getScope();

    /**
     * Quantitative quality information for the data specified by the scope.
     * Shall be provided only if {@linkplain Scope#getLevel scope level} is
     * {@linkplain org.opengis.metadata.maintenance.ScopeCode#DATASET dataset}.
     *
     * @return Quantitative quality information for the data.
     *
     * @condition {@linkplain #getLineage() Lineage} not provided.
     */
    @UML(identifier="report", obligation=CONDITIONAL, specification=ISO_19115)
    Collection<? extends Element> getReports();

    /**
     * Non-quantitative quality information about the lineage of the data specified by the scope.
     * Shall be provided only if {@linkplain Scope#getLevel scope level} is
     * {@linkplain org.opengis.metadata.maintenance.ScopeCode#DATASET dataset}.
     *
     * @return Non-quantitative quality information about the lineage of the data specified,
     *         or {@code null}.
     *
     * @condition {@linkplain #getReports() Report} not provided.
     */
    @Profile(level=CORE)
    @UML(identifier="lineage", obligation=CONDITIONAL, specification=ISO_19115)
    Lineage getLineage();
}
