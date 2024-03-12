/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2022-2023 Open Geospatial Consortium, Inc.
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
import java.util.Collections;
import java.time.temporal.Temporal;
import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;
import org.opengis.metadata.citation.Citation;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Description of the evaluation method and procedure applied.
 * Data quality evaluation method should be included for each applied {@linkplain Element data quality measure}.
 * It describes, or references documentation describing, the methodology used to apply a data quality measure
 * to the data specified by a {@linkplain DataQuality#getScope() data quality scope}.
 * {@code EvaluationMethod} can be specialized with {@link DataEvaluation} or {@link AggregationDerivation} subtypes.
 *
 * @author  Alexis Gaillard (Geomatys)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 *
 * @see Element#getEvaluationMethod()
 *
 * @since 3.1
 */
@UML(identifier="DQ_EvaluationMethod", specification=ISO_19157)
public interface EvaluationMethod {
    /**
     * Type of method used to evaluate quality of the data.
     *
     * @return type of method used to evaluate quality, or {@code null} if none.
     */
    @UML(identifier="evaluationMethodType", obligation=OPTIONAL, specification=ISO_19157)
    default EvaluationMethodType getEvaluationMethodType() {
        return null;
    }

    /**
     * Description of the evaluation method.
     *
     * @return description of the evaluation method, or {@code null} if none.
     */
    @UML(identifier="evaluationMethodDescription", obligation=OPTIONAL, specification=ISO_19157)
    default InternationalString getEvaluationMethodDescription() {
        return null;
    }

    /**
     * Reference to the procedure information.
     *
     * @return reference to the procedure information, or {@code null} if none.
     */
    @UML(identifier="evaluationProcedure", obligation=OPTIONAL, specification=ISO_19157)
    default Citation getEvaluationProcedure() {
        return null;
    }

    /**
     * Information on documents which are referenced in developing and applying a data quality evaluation method.
     *
     * @return documents referenced in data quality evaluation method.
     *
     * @departure rename
     *   Renamed from {@code "referenceDoc"} to {@code "referenceDocument"}
     *   for avoiding abbreviation (a Java usage).
     */
    @UML(identifier="referenceDoc", obligation=OPTIONAL, specification=ISO_19157)
    default Collection<? extends Citation> getReferenceDocuments() {
        return Collections.emptyList();
    }

    /**
     * Date or range of dates on which a data quality measure was applied.
     * The collection size is 1 for a single date, or 2 for a range.
     * Returns an empty collection if this information is not available.
     *
     * @return date or range of dates on which a data quality measure was applied.
     */
    @UML(identifier="dateTime", obligation=OPTIONAL, specification=ISO_19157)
    default Collection<? extends Temporal> getDates() {
        return Collections.emptyList();
    }
}
