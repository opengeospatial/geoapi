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
package org.opengis.metadata.quality;

import org.opengis.util.InternationalString;
import org.opengis.metadata.citation.Citation;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information about the outcome of evaluating the obtained value (or set of values) against
 * a specified acceptable conformance quality level.
 * Conformance result is often derived from a {@linkplain QuantitativeResult quantitative result}.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @author  Alexis Gaillard (Geomatys)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="DQ_ConformanceResult", specification=ISO_19157)
public interface ConformanceResult extends Result {
    /**
     * Citation of data product specification or user requirement against which data are being evaluated.
     *
     * @return citation of product specification or user requirement.
     */
    @UML(identifier="specification", obligation=MANDATORY, specification=ISO_19157)
    Citation getSpecification();

    /**
     * Explanation of the meaning of conformance for this result.
     *
     * @return explanation of the meaning of conformance, or {@code null} if none.
     *
     * @condition Optional if the {@linkplain #getResultScope() result scope} is not provided.
     */
    @UML(identifier="explanation", obligation=OPTIONAL, specification=ISO_19157)
    default InternationalString getExplanation() {
        return null;
    }

    /**
     * Indication of the conformance result.
     *
     * @return {@code true} = pass, {@code false} = fail.
     */
    @UML(identifier="pass", obligation=MANDATORY, specification=ISO_19157)
    Boolean pass();
}
