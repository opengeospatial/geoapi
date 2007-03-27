/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.identification;

// J2SE direct dependencies
import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Obligation.OPTIONAL;
import static org.opengis.annotation.Specification.ISO_19115;

import java.util.Collection;

import org.opengis.annotation.UML;
import org.opengis.metadata.citation.Citation;
import org.opengis.util.InternationalString;


/**
 * Keywords, their type and reference source.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="MD_Keywords", specification=ISO_19115)
public interface Keywords {
    /**
     * Commonly used word(s) or formalised word(s) or phrase(s) used to describe the subject.
     */
    @UML(identifier="keyword", obligation=MANDATORY, specification=ISO_19115)
    Collection<InternationalString> getKeywords();

    /**
     * Subject matter used to group similar keywords.
     */
    @UML(identifier="type", obligation=OPTIONAL, specification=ISO_19115)
    KeywordType getType();

    /**
     * Name of the formally registered thesaurus or a similar authoritative source of keywords.
     */
    @UML(identifier="thesaurusName", obligation=OPTIONAL, specification=ISO_19115)
    Citation getThesaurusName();
}
