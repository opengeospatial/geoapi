/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2018-2019 Open Geospatial Consortium, Inc.
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

import java.util.Locale;
import org.opengis.util.InternationalString;


/**
 * An international string consisting of a single string for all locales.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 4.0
 * @since   4.0
 */
final class Literal implements InternationalString {
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
    Literal(final String text) {
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
        if (object instanceof Literal) {
            return text.equals(((Literal) object).text);
        }
        return false;
    }

    /**
     * Returns a hash code value for this international text.
     */
    @Override
    public int hashCode() {
        return text.hashCode() ^ 336075259;
    }
}
