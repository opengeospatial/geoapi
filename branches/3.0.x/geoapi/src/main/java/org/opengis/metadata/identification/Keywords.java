/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2011 Open Geospatial Consortium, Inc.
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
