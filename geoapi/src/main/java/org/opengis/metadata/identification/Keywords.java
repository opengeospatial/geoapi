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
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.0
 *
 * @navassoc 1 - - KeywordType
 * @navassoc 1 - - Citation
 */
@UML(identifier="MD_Keywords", specification=ISO_19115)
public interface Keywords {
    /**
     * Commonly used word(s) or formalised word(s) or phrase(s) used to describe the subject.
     *
     * @return Word(s) or phrase(s) used to describe the subject.
     */
    @UML(identifier="keyword", obligation=MANDATORY, specification=ISO_19115)
    Collection<? extends InternationalString> getKeywords();

    /**
     * Subject matter used to group similar keywords.
     *
     * @return Subject matter used to group similar keywords, or {@code null}.
     */
    @UML(identifier="type", obligation=OPTIONAL, specification=ISO_19115)
    KeywordType getType();

    /**
     * Name of the formally registered thesaurus or a similar authoritative source of keywords.
     *
     * @return Name of registered thesaurus or similar authoritative source of keywords, or {@code null}.
     */
    @UML(identifier="thesaurusName", obligation=OPTIONAL, specification=ISO_19115)
    Citation getThesaurusName();
}
