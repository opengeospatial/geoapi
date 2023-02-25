/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2007-2021 Open Geospatial Consortium, Inc.
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
 * @deprecated As of ISO 19115:2014, replaced by {@link AssociatedResource}.
 */
@Deprecated
@UML(identifier="MD_AggregateInformation", specification=ISO_19115, version=2003)
public interface AggregateInformation extends AssociatedResource {
    /**
     * Citation information about the aggregate dataset.
     *
     * @return citation information about the aggregate dataset, or {@code null}.
     *
     * @condition {@linkplain #getAggregateDataSetIdentifier() Aggregate data set identifier} not provided.
     *
     * @deprecated As of ISO 19115:2014, replaced by {@link #getName()}.
     */
    @Deprecated
    @UML(identifier="aggregateDataSetName", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    Citation getAggregateDataSetName();

    /**
     * Identification information about aggregate dataset.
     *
     * @return identification information about aggregate dataset, or {@code null}.
     *
     * @condition {@linkplain #getAggregateDataSetName() Aggregate data set name} not provided.
     *
     * @deprecated As of ISO 19115:2014, replaced by an identifier of {@link #getAggregateDataSetName()}.
     */
    @Deprecated
    @UML(identifier="aggregateDataSetIdentifier", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    Identifier getAggregateDataSetIdentifier();
}
