/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.util;

import java.util.Map;  // For javadoc
import java.util.Locale;


/**
 * A {@linkplain String string} that has been internationalized into several {@linkplain Locale locales}.
 * This interface is used as a replacement for the {@link String} type whenever an attribute needs to be
 * internationalization capable.
 *
 * <P>The {@linkplain Comparable natural ordering} is defined by the {@linkplain String#compareTo
 * lexicographical ordering of strings} in the default locale, as returned by {@link #toString()}.
 * This string also defines the {@linkplain CharSequence character sequence} for this object.</P>
 *
 * @departure extension
 *   Added this new type in order to distinguish between localizable and non-localizable character
 *   strings. Not all character strings should be localizable; for example <cite>Well Know Text</cite>
 *   or code names should probably be language neutral. Since the ISO/OGC UML does not say which character
 *   strings are localizable and which ones are not, we have done our own guesses in GeoAPI.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.0
 *
 * @see javax.xml.registry.infomodel.InternationalString
 * @see NameFactory#createInternationalString(Map)
 */
public interface InternationalString extends CharSequence, Comparable<InternationalString> {
    /**
     * Returns this string in the given locale. If no string is available in the given locale,
     * then some default locale is used. The default locale is implementation-dependent. It
     * may or may not be the {@linkplain Locale#getDefault() system default}.
     *
     * @param  locale The desired locale for the string to be returned, or {@code null}
     *         for a string in the implementation default locale.
     * @return The string in the given locale if available, or in the default locale otherwise.
     */
    String toString(Locale locale);

    /**
     * Returns this string in the default locale. The default locale is implementation-dependent.
     * It may or may not be the {@linkplain Locale#getDefault() system default}. If the default
     * locale is the {@linkplain Locale#getDefault() system default} (a recommended practice),
     * then invoking this method is equivalent to invoking
     * <code>{@linkplain #toString(Locale) toString}({@linkplain Locale#getDefault})</code>.
     *
     * <P>All methods from {@link CharSequence} operate on this string. This string is also
     * used as the criterion for {@linkplain Comparable natural ordering}.</P>
     *
     * @return The string in the default locale.
     */
    @Override    
    String toString();
}
