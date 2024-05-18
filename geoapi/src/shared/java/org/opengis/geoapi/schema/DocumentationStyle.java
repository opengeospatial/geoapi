/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2018-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.geoapi.schema;


/**
 * Style of the documentation to store. Documentation in XSD files are not sentence;
 * they begin with a lower-case letter instead of an upper-case one and do not finish
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
        "Postion:",         // Typo found in some XSD.
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
     *
     * @param  doc   the documentation declared in the XSD file.
     * @param  from  index of the first character in {@code doc}.
     * @return index of the first character to use in {@code doc}.
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
     *
     * @param  doc   the documentation declared in the XSD file.
     * @param  from  index after the last character in {@code doc}.
     * @return index after the last character to use in {@code doc}.
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
     * {@return the index after the last character to keep in the given documentation}.
     * This method cut the documentation before trailing "shortName" and other annexes.
     *
     * @param  doc  the documentation declared in the XSD file.
     */
    private static int beforeAnnexes(final String doc) {
        int stopAt = doc.length();
nextLn: for (int pos = stopAt; --pos >= 0;) {
            final int c = doc.charAt(pos);
            final boolean isEOL = (c == '\r' || c == '\n');
            if (isEOL || Character.isWhitespace(c)) {
                final int lineStart = skipLeadingWhitespaces(doc, pos);
                for (final String header : IGNORE) {
                    if (doc.regionMatches(true, lineStart, header, 0, header.length())) {
                        stopAt = pos = skipTrailingWhitespaces(doc, pos);
                        continue nextLn;
                    }
                }
                if (isEOL) break;
            }
        }
        return skipTrailingWhitespaces(doc, stopAt);
    }
}
