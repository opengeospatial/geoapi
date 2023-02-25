/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2007-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.identification;

import org.opengis.metadata.Identifier;
import org.opengis.metadata.citation.Citation;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.ISO_19115;


/**
 * Aggregate dataset information.
 *
 * @author  Ely Conn (Leica Geosystems Geospatial Imaging, LLC)
 * @version 3.0
 * @since   2.1
 *
 * @navassoc 1 - - Citation
 * @navassoc 1 - - Identifier
 * @navassoc 1 - - AssociationType
 * @navassoc 1 - - InitiativeType
 */
@UML(identifier="MD_AggregateInformation", specification=ISO_19115)
public interface AggregateInformation {
    /**
     * Citation information about the aggregate dataset.
     *
     * @return Citation information about the aggregate dataset, or {@code null}.
     *
     * @condition {@linkplain #getAggregateDataSetIdentifier()} Aggregate data set identifier}
     *            not documented.
     */
    @UML(identifier="aggregateDataSetName", obligation=CONDITIONAL, specification=ISO_19115)
    Citation getAggregateDataSetName();

    /**
     * Identification information about aggregate dataset.
     *
     * @return Identification information about aggregate dataset, or {@code null}.
     *
     * @condition {@linkplain #getAggregateDataSetName() Aggregate data set name} not documented.
     */
    @UML(identifier="aggregateDataSetIdentifier", obligation=CONDITIONAL, specification=ISO_19115)
    Identifier getAggregateDataSetIdentifier();

    /**
     * Association type of the aggregate dataset.
     *
     * @return Association type of the aggregate dataset.
     */
    @UML(identifier="associationType", obligation=MANDATORY, specification=ISO_19115)
    AssociationType getAssociationType();

    /**
     * Type of initiative under which the aggregate dataset was produced.
     *
     * @return Type of initiative under which the aggregate dataset was produced, or {@code null}.
     */
    @UML(identifier="initiativeType", obligation=OPTIONAL, specification=ISO_19115)
    InitiativeType getInitiativeType();
}
