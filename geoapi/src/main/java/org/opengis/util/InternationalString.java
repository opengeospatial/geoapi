/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2023 Open Geospatial Consortium, Inc.
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
import org.opengis.annotation.UML;
import static org.opengis.annotation.Specification.ISO_19115;


/**
 * A {@linkplain String string} that has been internationalized into several {@linkplain Locale locales}.
 * This interface is used as a replacement for the {@link String} type whenever an attribute needs to be
 * internationalization capable.
 *
 * <p>The {@linkplain Comparable natural ordering} is defined by the {@linkplain String#compareTo
 * lexicographical ordering of strings} in the default locale, as returned by {@link #toString()}.
 * This string also defines the {@linkplain CharSequence character sequence} for this object.</p>
 *
 * @departure rename
 *   This is called {@code PT_FreeText} in ISO 19115 standard, and can be applied to all metadata
 *   elements who's data type is {@code CharacterString} and domain is “free text”.
 *   GeoAPI uses the {@code InternationalString} name for historical reasons and for consistency
 *   with similar object in Internationalization Service for J2EE.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.0
 *
 * @see NameFactory#createInternationalString(Map)
 */
@UML(identifier="PT_FreeText", specification=ISO_19115)
public interface InternationalString extends CharSequence, Comparable<InternationalString> {
    /**
     * Returns this string in the given locale. If no string is available in the given locale,
     * then some fallback locale is used. The fallback locale is implementation-dependent, and
     * is not necessarily the same than the default locale used by the {@link #toString()} method.
     *
     * <p>If the application is running on a server, the {@code locale} argument should be
     * determined from the HTTP request headers instead of the platform default locale.</p>
     *
     * @param  locale  the desired locale for the string to be returned.
     * @return the string in the given locale if available, or in an
     *         implementation-dependent fallback locale otherwise.
     *
     * @see Locale#getDefault()
     * @see Locale#ROOT
     */
    String toString(Locale locale);

    /**
     * Returns this string in the default locale. The default locale is implementation-dependent.
     * It may be the {@linkplain Locale#getDefault() system default}, the {@linkplain Locale#ROOT
     * root locale} or any other locale at implementation choice.
     *
     * <p>All methods from {@link CharSequence} operate on this string.
     * This string is also used as the criterion for {@linkplain Comparable natural ordering}.</p>
     *
     * @return the string in the default locale.
     */
    @Override
    String toString();
}
