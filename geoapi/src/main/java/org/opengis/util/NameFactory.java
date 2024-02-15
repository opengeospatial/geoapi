/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2005-2011 Open Geospatial Consortium, Inc.
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

import java.util.Map;
import java.util.Locale;


/**
 * Factory for {@linkplain GenericName generic names} and
 * {@linkplain InternationalString international strings}.
 *
 * {@note Despite the "<code>create</code>" name, implementations may return cached instances.}
 *
 * @departure extension
 *   Added in order to provide constructors for <code>GenericName</code> and related interfaces.
 *
 * @author  Jesse Crossley (SYS Technologies)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.0
 * @since   2.0
 */
public interface NameFactory {
    /**
     * Creates an international string from a set of strings in different locales.
     *
     * @param strings
     *          String value for each locale key.
     * @return The international string.
     */
    InternationalString createInternationalString(Map<Locale,String> strings);

    /**
     * Creates a namespace having the given name and separators. The {@code properties} argument
     * is optional: if non-null, the given properties may be given to the namespace to be created.
     * The properties can include for example the separator character to be used between the
     * {@linkplain GenericName#getParsedNames() parsed names}.
     * <p>
     * Implementations are encouraged to recognize at least the properties listed in the following
     * table. Additional implementation-specific properties can be added. Unknown properties shall
     * be ignored.
     * <p>
     * <table border='1'>
     *   <tr bgcolor="#CCCCFF" class="TableHeadingColor">
     *     <th nowrap>Property name</th>
     *     <th nowrap>Purpose</th>
     *   </tr>
     *   <tr>
     *     <td nowrap>&nbsp;{@code "separator"}&nbsp;</td>
     *     <td nowrap>&nbsp;The separator to insert between {@linkplain GenericName#getParsedNames
     *     parsed names} in that namespace. For HTTP namespace, it is {@code "."}. For URN namespace,
     *     it is typically {@code ":"}.</td>
     *   </tr>
     *   <tr>
     *     <td nowrap>&nbsp;{@code "separator.head"}&nbsp;</td>
     *     <td nowrap>&nbsp;The separator to insert between the namespace and the
     *     {@linkplain GenericName#head head}. For HTTP namespace, it is {@code "://"}.
     *     For URN namespace, it is typically {@code ":"}. If this entry is omitted, then
     *     the default shall be the same value as the {@code "separator"} entry.</td>
     *   </tr>
     * </table>
     *
     * @param name
     *          The name of the namespace to be returned. This argument can be created using
     *          <code>{@linkplain #createGenericName createGenericName}(null, parsedNames)</code>.
     * @param properties
     *          An optional map of properties to be assigned to the namespace.
     * @return A namespace having the given name and separators.
     *
     * @since 2.3
     */
    NameSpace createNameSpace(GenericName name, Map<String,?> properties);

    /**
     * Creates a type name from the given character sequence. The character sequence shall
     * complies to the same restriction as {@link #createLocalName createLocalName}.
     *
     * @param scope
     *          The {@linkplain GenericName#scope scope} of the type name to be created,
     *          or {@code null} for a global namespace.
     * @param name
     *          The type name as a string or an international string.
     * @return The type name for the given character sequence.
     *
     * @since 2.3
     */
    TypeName createTypeName(NameSpace scope, CharSequence name);

    /**
     * Creates a local name from the given character sequence. The character sequence can be either
     * a {@link String} or an {@link InternationalString} instance. In the latter case, implementations
     * can use an arbitrary {@linkplain Locale locale} (typically {@link Locale#ENGLISH ENGLISH},
     * but not necessarily) for the unlocalized string to be returned by {@link LocalName#toString()}.
     *
     * @param scope
     *          The {@linkplain GenericName#scope scope} of the local name to be created,
     *          or {@code null} for a global namespace.
     * @param name
     *          The local name as a string or an international string.
     * @return The local name for the given character sequence.
     *
     * @since 2.2
     */
    LocalName createLocalName(NameSpace scope, CharSequence name);

    /**
     * Creates a local or scoped name from an array of parsed names. The array elements can be either
     * {@link String} or {@link InternationalString} instances. In the latter case, implementations
     * can use an arbitrary {@linkplain Locale locale} (typically {@link Locale#ENGLISH ENGLISH},
     * but not necessarily) for the unlocalized string to be returned by {@link GenericName#toString()}.
     * <p>
     * If the length of the {@code parsedNames} array is 1, then this method returns an instance
     * of {@link LocalName}. If the length is 2 or more, then this method returns an instance of
     * {@link ScopedName}.
     *
     * @param scope
     *          The {@linkplain GenericName#scope scope} of the generic name to be created,
     *          or {@code null} for a global namespace.
     * @param parsedNames
     *          The local names as an array of strings or international strings.
     *          This array must contains at least one element.
     * @return The generic name for the given parsed names.
     *
     * @since 2.2
     */
    GenericName createGenericName(NameSpace scope, CharSequence... parsedNames);

    /**
     * Constructs a generic name from a qualified name. This method splits the given name around a
     * separator inferred from the given scope, or an implementation-dependent default separator if
     * the given scope is null.
     * <p>
     * For example if the {@code scope} argument is the namespace {@code "urn:ogc:def"}
     * with {@code ":"} as the separator, and if the {@code name} argument is the string
     * {@code "crs:epsg:4326"}, then the result is a {@linkplain ScopedName scoped name}
     * having a {@linkplain GenericName#depth depth} of 3, which is the length of the list
     * of {@linkplain GenericName#getParsedNames parsed names} ({@code "crs"}, {@code "epsg"},
     * {@code "4326"}).
     *
     * @param scope
     *          The {@linkplain GenericName#scope scope} of the generic name to
     *          be created, or {@code null} for a global namespace.
     * @param name
     *          The qualified name, as a sequence of names separated by a scope-dependent separator.
     * @return A name parsed from the given string.
     *
     * @since 2.2
     */
    GenericName parseGenericName(NameSpace scope, CharSequence name);
}
