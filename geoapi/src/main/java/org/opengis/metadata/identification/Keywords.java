/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2019 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
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
 * When the resource described is a service, one instance of {@code Keywords} should refer to the
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
    KeywordType getType();

    /**
     * Name of the formally registered thesaurus or a similar authoritative source of keywords.
     *
     * @return name of registered thesaurus or similar authoritative source of keywords, or {@code null}.
     */
    @UML(identifier="thesaurusName", obligation=OPTIONAL, specification=ISO_19115)
    Citation getThesaurusName();

    /**
     * User-defined categorization of groups of keywords that extend or are orthogonal
     * to the standardized {@linkplain #getType() keyword type} codes.
     * Keyword classes are associated with on ontology that allow additional semantic
     * query processing.
     *
     * <div class="note"><b>Note:</b>
     * The {@linkplain #getThesaurusName() thesaurus citation} specifies a collection of instances from some ontology,
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
    KeywordClass getKeywordClass();
}
