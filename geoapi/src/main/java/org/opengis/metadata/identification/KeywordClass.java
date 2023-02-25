/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2014-2023 Open Geospatial Consortium, Inc.
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

import java.net.URI;
import org.opengis.annotation.UML;
import org.opengis.metadata.citation.Citation;
import org.opengis.util.InternationalString;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.ISO_19115;


/**
 * Specification of a class to categorize keywords in a domain-specific
 * vocabulary that has a binding to a formal ontology.
 *
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   3.1
 *
 * @see Keywords#getKeywordClass()
 */
@UML(identifier="MD_KeywordClass", specification=ISO_19115)
public interface KeywordClass {
    /**
     * A character string to label the keyword category in natural language.
     *
     * @return the keyword category in natural language.
     */
    @UML(identifier="className", obligation=MANDATORY, specification=ISO_19115)
    InternationalString getClassName();

    /**
     * URI of concept in the ontology specified by the {@linkplain #getOntology() ontology} citation
     * and labeled by the {@linkplain #getClassName() class name}.
     *
     * @return URI of concept in the ontology, or {@code null} if none.
     */
    @UML(identifier="conceptIdentifier", obligation=OPTIONAL, specification=ISO_19115)
    default URI getConceptIdentifier() {
        return null;
    }

    /**
     * A reference that binds the keyword class to a formal conceptualization
     * of a knowledge domain for use in semantic processing.
     *
     * <div class="note"><b>Note:</b>
     * {@linkplain Keywords#getKeywords() keywords} in the associated {@link Keywords} list
     * must be within the scope of this ontology.
     * </div>
     *
     * @return a reference that binds the keyword class to a formal conceptualization.
     */
    @UML(identifier="ontology", obligation=MANDATORY, specification=ISO_19115)
    Citation getOntology();
}
