/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2011-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
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
package org.opengis.test.util;

import java.util.Locale;
import org.opengis.util.InternationalString;


/**
 * An international string consisting of a single string for all locales.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class SimpleInternationalString implements InternationalString {
    /**
     * The string returned by {@link #toString()} and others methods
     * from the {@link CharSequence} interface.
     */
    private final String text;

    /**
     * Creates a new international string for the given text.
     *
     * @param text  the text to wrap in an {@link InternationalString}.
     */
    SimpleInternationalString(final String text) {
        this.text = text;
    }

    /**
     * Returns the length of the string.
     */
    @Override
    public int length() {
        return text.length();
    }

    /**
     * Returns the character of the string at the specified index.
     *
     * @param  index  the index of the character.
     * @return the character at the specified index.
     * @throws IndexOutOfBoundsException if the specified index is out of bounds.
     */
    @Override
    public char charAt(final int index) throws IndexOutOfBoundsException {
        return text.charAt(index);
    }

    /**
     * Returns a subsequence of the string. The subsequence is a {@link String} object starting
     * with the character value at the specified index and ending with the character value at
     * index {@code end - 1}.
     *
     * @param   start  the start index, inclusive.
     * @param   end    the end index, exclusive.
     * @return  The specified subsequence.
     * @throws  IndexOutOfBoundsException if {@code start} or {@code end} is out of range.
     */
    @Override
    public CharSequence subSequence(final int start, final int end) throws IndexOutOfBoundsException {
        return text.substring(start, end);
    }

    /**
     * Returns the string representation, which is unique for all locales.
     */
    @Override
    public String toString() {
        return text;
    }

    /**
     * Returns the same string for all locales. This is the string given to the constructor.
     */
    @Override
    public String toString(final Locale locale) {
        return text;
    }

    /**
     * Compares this string with the specified object for order.
     *
     * @param  object  the string to compare with this string.
     * @return a negative number if this string is before the given string,
     *         a positive number if after, or 0 if equals.
     */
    @Override
    public int compareTo(final InternationalString object) {
        return text.compareTo(object.toString());
    }

    /**
     * Compares this international string with the specified object for equality.
     *
     * @param  object  the object to compare with this international string.
     * @return {@code true} if the given object is equal to this string.
     */
    @Override
    public boolean equals(final Object object) {
        if (object instanceof SimpleInternationalString) {
            return text.equals(((SimpleInternationalString) object).text);
        }
        return false;
    }

    /**
     * Returns a hash code value for this international text.
     */
    @Override
    public int hashCode() {
        return text.hashCode() ^ 354396380;
    }
}
