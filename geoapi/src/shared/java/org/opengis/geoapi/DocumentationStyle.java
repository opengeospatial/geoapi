/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2018-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.geoapi;


/**
 * Style of the documentation to store. Documentation in XSD files are not sentence;
 * they begin with a lower-case letter instead than an upper-case one and do not finish
 * with a period. Those documentation can be read verbatim, or transformed into sentences.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @since   3.1
 * @version 3.1
 */
public enum DocumentationStyle {
    /**
     * Skip documentation. The {@link SchemaInformation.Element#documentation} field will be {@code null}.
     * This style can be used when documentation is not needed.
     */
    NONE,

    /**
     * Store documentation verbatim, without transforming them into sentences.
     */
    VERBATIM,

    /**
     * Transform documentation to sentences.
     */
    SENTENCE;

    /**
     * Prefix to omit at the beginning of sentence. Some XSD files begin their documentation with
     * {@code "Description:"}, which is not necessary.
     */
    private static final String OMIT = "Description:";

    /**
     * Parts to ignore from the description given in XSD files.
     *
     * @todo store those information in a separated map.
     */
    private static final String[] IGNORE = {
        "FGDC:",
        "Position:",
        "Postion:",         // Type found in some XSD.
        "shortName:",
        "Conditional",
        "NITF_ACFTA:",
        "Note in 19115-3"
    };

    /**
     * Known typos in XSD files. Values at even indexes are the typos
     * and values at odd indexes are the fixes.
     *
     * @see <a href="https://github.com/opengeospatial/geoapi/pull/42">Issue #42</a>
     */
    private static final String[] TYPOS = {
        "avaialble",    "available",
        "desimination", "dissemination",
        "identifer",    "identifier",
        "occurance",    "occurrence",
        "occurence",    "occurrence",
        "occured",      "occurred",
        "recieve",      "receive",
        "temportal",    "temporal"
    };

    /**
     * Returns the index {@literal >=} {@code from} of the first non-whitespace character.
     */
    private static int skipLeadingWhitespaces(final String doc, int from) {
         while (from < doc.length()) {
            final int c = doc.codePointAt(from);
            if (!Character.isWhitespace(c)) break;
            from += Character.charCount(c);
        }
        return from;
    }

    /**
     * Returns the index {@literal <} {@code from} of the last whitespace character.
     */
    private static int skipTrailingWhitespaces(final String doc, int from) {
         while (from > 0) {
            final int c = doc.codePointBefore(from);
            if (!Character.isWhitespace(c)) break;
            from -= Character.charCount(c);
        }
        return from;
    }

    /**
     * Transforms the given documentation from XSD file in to a sentence.
     * See class javadoc for details.
     *
     * @param  doc     documentation from XSD file.
     * @param  buffer  temporary buffer. Must be initially empty.
     * @return the sentence, or {@code null} if none.
     */
    static String sentence(final String doc, final StringBuilder buffer) {
        /*
         * Skip leading whitespaces and "Description:" prefix (f any),
         * then omit "annexes" on new lines after the main description.
         * If the result is an empty string, use null for "no documentation".
         */
        int startAt = skipLeadingWhitespaces(doc, 0);
        if (doc.regionMatches(true, startAt, OMIT, 0, OMIT.length())) {
            startAt = skipLeadingWhitespaces(doc, OMIT.length());
        }
        final int stopAt = beforeAnnexes(doc);
        if (startAt >= stopAt) {
            return null;
        }
        /*
         * At this point we know the sub-string to use for documentation.
         * Copy as a sentence (upper-case first letter, final period).
         */
        final int firstChar = doc.codePointAt(startAt);
        buffer.appendCodePoint(Character.toUpperCase(firstChar))
              .append(doc, startAt + Character.charCount(firstChar), stopAt);
        if (doc.charAt(stopAt - 1) != '.') {
            buffer.append('.');
        }
        // Replace multi-spaces by a single space.
        for (int i=0; (i = buffer.indexOf("  ", i)) >= 0;) {
            buffer.deleteCharAt(i);
        }
        // Documentation in XSD are not sentences. Make it a sentence.
        int i = buffer.indexOf(" NOTE: ");
        if (i > 0 && buffer.charAt(i-1) != '.') {
            buffer.insert(i, '.');
        }
        /*
         * Fix typos.
         */
        for (int t=0; t<TYPOS.length;) {
            final String typo = TYPOS[t++];
            final String fix  = TYPOS[t++];
            i = buffer.indexOf(typo);
            while (i >= 0) {
                buffer.replace(i, i + typo.length(), fix);
                i = buffer.indexOf(typo, i + fix.length());
            }
        }
        return buffer.toString();
    }

    /**
     * Returns the index after the last character to keep in the given documentation.
     * This method cut the documentation before trailing "shortName" and other annexes.
     */
    private static int beforeAnnexes(final String doc) {
        int stopAt = doc.length();
nextLn: for (int eol = stopAt; --eol >= 0;) {
            final int c = doc.charAt(eol);
            if (c == '\r' || c == '\n') {
                final int lineStart = skipLeadingWhitespaces(doc, eol);
                for (final String header : IGNORE) {
                    if (doc.regionMatches(true, lineStart, header, 0, header.length())) {
                        stopAt = eol = skipTrailingWhitespaces(doc, eol);
                        continue nextLn;
                    }
                }
                break;
            }
        }
        return skipTrailingWhitespaces(doc, stopAt);
    }
}
