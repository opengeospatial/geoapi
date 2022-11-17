/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2005-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.util;

import java.util.Map;
import java.util.Locale;


/**
 * Factory for {@linkplain GenericName generic names} and
 * {@linkplain InternationalString international strings}.
 *
 * <div class="note"><b>Note:</b>
 * despite the "{@code create(…)}" method names, implementations may return cached instances.</div>
 *
 * @departure extension
 *   Added in order to provide constructors for {@code GenericName} and related interfaces.
 *
 * @author  Jesse Crossley (SYS Technologies)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   2.0
 */
public interface NameFactory extends Factory {
    /**
     * Creates an international string from a set of strings in different locales.
     *
     * @param  strings  string value for each locale key.
     * @return the international string.
     */
    InternationalString createInternationalString(Map<Locale,String> strings);

    /**
     * Creates a namespace having the given name and separators. The {@code properties} argument
     * is optional: if non-null, the given properties may be given to the namespace to be created.
     * The properties can include for example the separator character to be used between the
     * {@linkplain GenericName#getParsedNames() parsed names}.
     *
     * <p>Implementations are encouraged to recognize at least the properties listed in the following
     * table. Additional implementation-specific properties can be added. Unknown properties shall
     * be ignored.</p>
     *
     * <blockquote><table class="ogc">
     *   <caption>Keys for additional standard properties</caption>
     *   <tr>
     *     <th>Property name</th>
     *     <th>Purpose</th>
     *   </tr>
     *   <tr>
     *     <td>{@code "separator"}</td>
     *     <td>The separator to insert between {@linkplain GenericName#getParsedNames() parsed names}
     *         in that namespace.</td>
     *   </tr>
     *   <tr>
     *     <td>{@code "separator.head"}</td>
     *     <td>The separator to insert between the namespace and the {@linkplain GenericName#head() head}.<br>
     *         If omitted, then the default is the same value than {@code "separator"}.</td>
     *   </tr>
     * </table></blockquote>
     *
     * <div class="note"><b>Examples:</b>
     * <ul>
     *   <li>For URN namespace, {@code separator} = {@code ":"} is typically sufficient.</li>
     *   <li>For HTTP namespace, {@code separator.head} = {@code "://"} and {@code separator} = {@code "."}.</li>
     * </ul></div>
     *
     * @param   name       the name of the namespace to be returned. This argument can be created using
     *                     <code>{@linkplain #createGenericName createGenericName}(null, namespace)</code>.
     * @param  properties  an optional map of properties to be assigned to the namespace, or {@code null} if none.
     * @return a namespace having the given name and separators.
     */
    NameSpace createNameSpace(GenericName name, Map<String,?> properties);

    /**
     * Creates a type name from the given character sequence. The character sequence shall
     * complies to the same restriction than {@link #createLocalName createLocalName}.
     *
     * @param  scope  the {@linkplain GenericName#scope() scope} of the type name to create,
     *                or {@code null} for a global namespace.
     * @param  name   the type name as a string or an international string.
     * @return the type name for the given scope and character sequence.
     */
    TypeName createTypeName(NameSpace scope, CharSequence name);

    /**
     * Creates a member name from the given character sequence and attribute type.
     *
     * @param  scope  the {@linkplain GenericName#scope() scope} of the member name to create,
     *                or {@code null} for a global namespace.
     * @param  name   the member name as a string or an international string.
     * @param  attributeType  the type of the data associated with the record member.
     * @return the member name for the given scope, character sequence and type name.
     *
     * @since 3.1
     */
    MemberName createMemberName(NameSpace scope, CharSequence name, TypeName attributeType);

    /**
     * Creates a local name from the given character sequence. The character sequence can be either
     * a {@link String} or an {@link InternationalString} instance. In the latter case, implementations
     * can use an arbitrary locale (typically {@link Locale#ROOT}) for the unlocalized string to be
     * returned by {@link LocalName#toString()}.
     *
     * @param  scope  the {@linkplain GenericName#scope() scope} of the local name to create,
     *                or {@code null} for a global namespace.
     * @param  name   the local name as a string or an international string.
     * @return the local name for the given scope and character sequence.
     */
    LocalName createLocalName(NameSpace scope, CharSequence name);

    /**
     * Creates a local or scoped name from an array of parsed names. The array elements can be either
     * {@link String} or {@link InternationalString} instances. In the latter case, implementations
     * can use an arbitrary locale (typically {@link Locale#ROOT}) for the unlocalized string to be
     * returned by {@link LocalName#toString()}.
     *
     * <p>If the length of the {@code parsedNames} array is 1, then this method returns an instance
     * of {@link LocalName}. If the length is 2 or more, then this method returns an instance of
     * {@link ScopedName}.</p>
     *
     * @param  scope        the {@linkplain GenericName#scope() scope} of the generic name to create,
     *                      or {@code null} for a global namespace.
     * @param  parsedNames  the local names as an array of strings or international strings.
     *                      This array must contains at least one element.
     * @return the generic name for the given parsed names.
     */
    GenericName createGenericName(NameSpace scope, CharSequence... parsedNames);

    /**
     * Constructs a generic name from a qualified name. This method splits the given name around a
     * separator inferred from the given scope, or an implementation-dependent default separator if
     * the given scope is null.
     *
     * <p>For example if the {@code scope} argument is the namespace {@code "urn:ogc:def"}
     * with {@code ":"} as the separator, and if the {@code name} argument is the string
     * {@code "crs:epsg:4326"}, then the result is a {@linkplain ScopedName scoped name}
     * having a {@linkplain GenericName#depth depth} of 3, which is the length of the list
     * of {@linkplain GenericName#getParsedNames parsed names} ({@code "crs"}, {@code "epsg"},
     * {@code "4326"}).</p>
     *
     * @param  scope  the {@linkplain GenericName#scope() scope} of the generic name to create,
     *                or {@code null} for a global namespace.
     * @param  name   the qualified name, as a sequence of names separated by a scope-dependent separator.
     * @return a name parsed from the given string.
     */
    GenericName parseGenericName(NameSpace scope, CharSequence name);
}
