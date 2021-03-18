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
package org.opengis.bridge.python;

import static java.lang.Character.charCount;
import static java.lang.Character.codePointAt;
import static java.lang.Character.codePointBefore;
import static java.lang.Character.isWhitespace;
import static java.lang.Character.isLowerCase;
import static java.lang.Character.toLowerCase;
import static java.lang.Character.toChars;


/**
 * Static methods working on character sequences. Used for converting names from one convention
 * (e.g. {@code "CamelCase"}) to another (e.g. {@code "snake_case"}).
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @since   4.0
 * @version 4.0
 */
final class CharSequences {
    /**
     * Do not allow instantiation of this class.
     */
    private CharSequences() {
    }

    /**
     * Returns the code point after the given index. This method completes
     * {@link Character#codePointBefore(CharSequence, int)} but is rarely used because slightly
     * inefficient (in most cases, the code point at {@code index} is known together with the
     * corresponding {@code charCount(int)} value, so the method calls should be unnecessary).
     */
    private static int codePointAfter(final CharSequence text, final int index) {
        return codePointAt(text, index + charCount(codePointAt(text, index)));
    }

    /**
     * Given a string in camel cases, returns a string with the same words separated by underscores.
     * A word begins with a upper-case character following a lower-case character. For example if the
     * given string is {@code "PixelInterleavedSampleModel"}, then this method returns <cite>"pixel
     * interleaved sample model"</cite>.
     *
     * <p>The given string is usually a programmatic identifier like a class name or a method name.</p>
     *
     * @param  identifier   an identifier with no space, words begin with an upper-case character.
     * @return the identifier with underscores inserted after what looks like words.
     */
    static String camelCaseToSnake(final CharSequence identifier) {
        final int length = identifier.length();
        final StringBuilder buffer = new StringBuilder(length + 8);
        final int lastIndex = (length != 0) ? length - charCount(codePointBefore(identifier, length)) : 0;
        int last = 0;
        for (int i=1; i<=length;) {
            final int cp;
            final boolean doAppend;
            if (i == length) {
                cp = 0;
                doAppend = true;
            } else {
                cp = codePointAt(identifier, i);
                doAppend = Character.isUpperCase(cp) && isLowerCase(codePointBefore(identifier, i));
            }
            if (doAppend) {
                final int pos = buffer.length();
                buffer.append(identifier, last, i).append('_');
                if (last < lastIndex && isLowerCase(codePointAfter(identifier, last))) {
                    final int c = buffer.codePointAt(pos);
                    final int low = toLowerCase(c);
                    if (c != low) {
                        replace(buffer, pos, pos + charCount(c), toChars(low));
                    }
                }
                last = i;
            }
            i += charCount(cp);
        }
        /*
         * Removes the trailing space and underscores, if any.
         */
        final int lg = buffer.length();
        if (lg != 0) {
            final int cp = buffer.codePointBefore(lg);
            if (isWhitespace(cp) || cp == '_') {
                buffer.setLength(lg - charCount(cp));
            }
        }
        return buffer.toString();
    }

    /**
     * Replaces the characters in a substring of the buffer with characters in the specified array.
     * The substring to be replaced begins at the specified {@code start} and extends to the
     * character at index {@code end - 1}.
     *
     * @param  buffer  the buffer in which to perform the replacement.
     * @param  start   the beginning index in the {@code buffer}, inclusive.
     * @param  end     the ending index in the {@code buffer}, exclusive.
     * @param  chars   the array that will replace previous contents.
     * @throws NullArgumentException if the {@code buffer} or {@code chars} argument is null.
     *
     * @see StringBuilder#replace(int, int, String)
     */
    private static void replace(final StringBuilder buffer, int start, final int end, final char[] chars) {
        int length = end - start;
        final int remaining = chars.length - length;
        if (remaining < 0) {
            buffer.delete(end + remaining, end);
            length = chars.length;
        }
        for (int i=0; i<length; i++) {
            buffer.setCharAt(start++, chars[i]);
        }
        if (remaining > 0) {
            buffer.insert(start, chars, length, remaining);
        }
    }
}
