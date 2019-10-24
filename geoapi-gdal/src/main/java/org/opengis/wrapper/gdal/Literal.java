/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The GDAL wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementers can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.wrapper.gdal;

import java.util.Locale;
import org.opengis.util.InternationalString;


/**
 * An international string consisting of a single string for all locales.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
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
        return text.hashCode() ^ 1293463701;
    }
}
