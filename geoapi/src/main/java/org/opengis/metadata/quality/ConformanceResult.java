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

import org.opengis.util.InternationalString;
import org.opengis.metadata.citation.Citation;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information about the outcome of evaluating the obtained value (or set of values) against
 * a specified acceptable conformance quality level.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @version 3.0
 * @since   2.0
 *
 * @navassoc 1 - - Citation
 */
@UML(identifier="DQ_ConformanceResult", specification=ISO_19115)
public interface ConformanceResult extends Result {
    /**
     * Citation of product specification or user requirement against which data is being evaluated.
     *
     * @return Citation of product specification or user requirement.
     */
    @UML(identifier="specification", obligation=MANDATORY, specification=ISO_19115)
    Citation getSpecification();

    /**
     * Explanation of the meaning of conformance for this result.
     *
     * @return Explanation of the meaning of conformance.
     */
    @UML(identifier="explanation", obligation=MANDATORY, specification=ISO_19115)
    InternationalString getExplanation();

    /**
     * Indication of the conformance result.
     *
     * @return Indication of the conformance result.
     */
    @UML(identifier="pass", obligation=MANDATORY, specification=ISO_19115)
    Boolean pass();
}
