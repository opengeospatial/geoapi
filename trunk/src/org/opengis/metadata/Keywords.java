/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata;

// J2SE direct dependencies
import java.util.Locale;

// OpenGIS direct dependencies
import org.opengis.metadata.citation.Citation;


/**
 * Keywords, their type and reference source.
 *
 * @UML datatype MD_Keywords
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public interface Keywords {
    /**
     * Commonly used word(s) or formalised word(s) or phrase(s) used to describe the subject.
     *
     * @param  locale The desired locale for the keywords to be returned, or <code>null</code>
     *         for keywords in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The keywords in the given locale.
     *         If no keywords is available in the given locale, then some default locale is used.
     * @UML mandatory keyword
     */
    String[] getKeywords(Locale locale);

    /**
     * Subject matter used to group similar keywords.
     *
     * @UML optional type
     */
    KeywordType getType();

    /**
     * Name of the formally registered thesaurus or a similar authoritative source of keywords.
     *
     * @UML optional thesaurusName
     */
    Citation getThesaurusName();
}
