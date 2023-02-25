/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2023 Open Geospatial Consortium, Inc.
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

import java.util.Collection;
import org.opengis.util.InternationalString;
import org.opengis.metadata.citation.Citation;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Keywords, their type and reference source.
 *
 * <div class="note"><b>Note:</b>
 * when the resource described is a service, one instance of {@code Keywords} should refer to the
 * service taxonomy defined in ISO 191119.</div>
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="MD_Keywords", specification=ISO_19115)
public interface Keywords {
    /**
     * Commonly used word(s) or formalised word(s) or phrase(s) used to describe the subject.
     *
     * @return word(s) or phrase(s) used to describe the subject.
     */
    @UML(identifier="keyword", obligation=MANDATORY, specification=ISO_19115)
    Collection<? extends InternationalString> getKeywords();

    /**
     * Subject matter used to group similar keywords.
     *
     * @return subject matter used to group similar keywords, or {@code null}.
     */
    @UML(identifier="type", obligation=OPTIONAL, specification=ISO_19115)
    default KeywordType getType() {
        return null;
    }

    /**
     * Name of the formally registered thesaurus or a similar authoritative source of keywords.
     *
     * @return name of registered thesaurus or similar authoritative source of keywords, or {@code null}.
     */
    @UML(identifier="thesaurusName", obligation=OPTIONAL, specification=ISO_19115)
    default Citation getThesaurusName() {
        return null;
    }

    /**
     * User-defined categorization of groups of keywords that extend or are orthogonal
     * to the standardized {@linkplain #getType() keyword type} codes.
     * Keyword classes are associated with on ontology that allow additional semantic
     * query processing.
     *
     * <div class="note"><b>Note:</b>
     * the {@linkplain #getThesaurusName() thesaurus citation} specifies a collection of instances from some ontology,
     * but is not an ontology. It might be a list of places that include rivers, mountains, counties and cities.
     * There might be a Laconte county, the city of Laconte, the Laconte River, and Mt. Laconte;
     * when searching it is useful for the user to be able to restrict the search to only rivers.
     * </div>
     *
     * @return user-defined categorization of groups of keywords, or {@code null} if none.
     *
     * @since 3.1
     */
    @UML(identifier="keywordClass", obligation=OPTIONAL, specification=ISO_19115)
    default KeywordClass getKeywordClass() {
        return null;
    }
}
