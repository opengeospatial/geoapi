/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2021 Open Geospatial Consortium, Inc.
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
package org.opengis.metadata.quality;

import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Test based on external knowledge or experience of the data product.
 * This external knowledge may include, but is not limited to, one or more non-quantitative quality information usage,
 * lineage and purpose or other data quality reports on the data set or data used to produce the data set.
 * Indirect evaluation can be subjective.
 *
 * <p>In some cases it might not be possible to report indirectly evaluated data quality as quantitative results.
 * In those cases the data quality may be described in textual form using a descriptive result.</p>
 *
 * @author  Alexis Gaillard (Geomatys)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 *
 * @see FullInspection
 * @see SampleBasedInspection
 *
 * @since 3.1
 */
@UML(identifier="DQ_IndirectEvaluation", specification=ISO_19157)
public interface IndirectEvaluation extends DataEvaluation {
    /**
     * Information on which data are used as sources in deductive evaluation method.
     *
     * @return information on which data are used.
     */
    @UML(identifier="deductiveSource", obligation=MANDATORY, specification=ISO_19157)
    InternationalString getDeductiveSource();
}
