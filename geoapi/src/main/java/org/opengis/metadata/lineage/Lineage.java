/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.lineage;

import java.util.Collection;
import java.util.Collections;
import org.opengis.util.InternationalString;
import org.opengis.metadata.maintenance.Scope;
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.maintenance.ScopeCode;
import org.opengis.annotation.UML;
import org.opengis.annotation.Profile;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.ComplianceLevel.*;


/**
 * Information about the events or source data used in constructing the data specified by
 * the scope or lack of knowledge about lineage.
 * At least one of {@linkplain #getStatement() statement}, {@linkplain #getProcessSteps() process steps}
 * and {@linkplain #getSources() sources} shall be provided.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="LI_Lineage", specification=ISO_19115)
public interface Lineage {
    /**
     * General explanation of the data producer's knowledge about the lineage of a dataset.
     *
     * @return explanation of the data producer's knowledge about the lineage, or {@code null}.
     *
     * @condition Shall be provided only if {@linkplain Scope#getLevel() scope level} is
     *            {@link ScopeCode#DATASET} or {@link ScopeCode#SERIES}.
     */
    @Profile(level=CORE)
    @UML(identifier="statement", obligation=CONDITIONAL, specification=ISO_19115)
    InternationalString getStatement();

    /**
     * Type of resource and / or extent to which the lineage information applies.
     *
     * @return type of resource and / or extent, or {@code null} if none.
     *
     * @since 3.1
     */
    @UML(identifier="scope", obligation=OPTIONAL, specification=ISO_19115)
    default Scope getScope() {
        return null;
    }

    /**
     * Additional documentation.
     *
     * <div class="note"><b>Example</b>
     * a publication that describes the whole process to generate this resource (for example a dataset).
     * </div>
     *
     * @return additional documentation.
     *
     * @since 3.1
     */
    @UML(identifier="additionalDocumentation", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends Citation> getAdditionalDocumentation() {
        return Collections.emptyList();
    }

    /**
     * Information about events in the life of a resource specified by the scope.
     *
     * @return information about events in the life of a resource.
     *
     * @condition Mandatory if {@linkplain #getStatement() statement} and
     *            {@linkplain #getSources() source} are not provided.
     */
    @UML(identifier="processStep", obligation=CONDITIONAL, specification=ISO_19115)
    Collection<? extends ProcessStep> getProcessSteps();

    /**
     * Information about the source data used in creating the data specified by the scope.
     *
     * @return information about the source data.
     *
     * @condition Mandatory if {@linkplain #getStatement() statement} and
     *            {@linkplain #getProcessSteps() process step} are not provided.
     */
    @UML(identifier="source", obligation=CONDITIONAL, specification=ISO_19115)
    Collection<? extends Source> getSources();
}
