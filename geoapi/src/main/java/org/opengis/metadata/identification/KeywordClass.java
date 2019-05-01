/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2014-2019 Open Geospatial Consortium, Inc.
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
    URI getConceptIdentifier();

    /**
     * A reference that binds the keyword class to a formal conceptualization
     * of a knowledge domain for use in semantic processing.
     *
     * <div class="note"><b>Note:</b>
     * {@linkplain Keywords#getKeywords() Keywords} in the associated {@link Keywords} list
     * must be within the scope of this ontology.
     * </div>
     *
     * @return a reference that binds the keyword class to a formal conceptualization.
     */
    @UML(identifier="ontology", obligation=MANDATORY, specification=ISO_19115)
    Citation getOntology();
}
