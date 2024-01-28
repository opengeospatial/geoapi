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
     * A word begins with a upper-case character following a lower-case character.
     * For example, if the given string is {@code "PixelInterleavedSampleModel"},
     * then this method returns <q>pixel interleaved sample model</q>.
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
     * @throws NullPointerException if the {@code buffer} or {@code chars} argument is null.
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
