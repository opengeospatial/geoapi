/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2024 Open Geospatial Consortium, Inc.
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
package org.opengis.referencing;

import java.util.Locale;

import org.opengis.metadata.Identifier;
import org.opengis.metadata.extent.Extent;
import org.opengis.metadata.citation.Citation;
import org.opengis.util.Factory;
import org.opengis.util.InternationalString;
import org.opengis.util.GenericName;


/**
 * Base interface for all factories of identified objects.
 * Factories build up complex objects from simpler objects or values.
 * This factory allows applications to make
 * {@linkplain org.opengis.referencing.cs.CoordinateSystem coordinate systems},
 * {@linkplain org.opengis.referencing.datum.Datum datum} or
 * {@linkplain org.opengis.referencing.crs.CoordinateReferenceSystem coordinate reference systems}
 * that cannot be created by an {@linkplain AuthorityFactory authority factory}.
 * This factory is very flexible, whereas the authority factory is easier to use.
 *
 * <h2>Object properties</h2>
 * Most factory methods expect a {@code Map<String,?>} argument.
 * The table below lists the keys that {@code ObjectFactory} implementations shall accept,
 * together with the type of values associated to those keys. The <q>Alternative types</q> column
 * gives examples of types that factory implementations may accept as well for convenience.
 * A value for the {@code "name"} key is mandatory, while all other properties are optional.
 * Factory methods shall ignore unknown properties.
 *
 * <table class="ogc">
 *   <caption>Keys for standard properties</caption>
 *   <tr>
 *     <th>Key</th>
 *     <th>Value type</th>
 *     <th>Alternative types</th>
 *     <th>Value returned by</th>
 *   </tr><tr>
 *     <td>{@value org.opengis.referencing.IdentifiedObject#NAME_KEY}</td>
 *     <td>{@link Identifier}</td>
 *     <td>{@link String} (see alternatives below)</td>
 *     <td>{@link IdentifiedObject#getName()}</td>
 *   </tr><tr>
 *     <td>{@value org.opengis.referencing.IdentifiedObject#ALIAS_KEY}</td>
 *     <td><code>{@linkplain GenericName}[]</code></td>
 *     <td>{@link GenericName}, {@link String} or <code>{@linkplain String}[]</code></td>
 *     <td>{@link IdentifiedObject#getAlias()}</td>
 *   </tr><tr>
 *     <td>{@value org.opengis.referencing.IdentifiedObject#IDENTIFIERS_KEY}</td>
 *     <td><code>{@linkplain Identifier}[]</code></td>
 *     <td>{@link Identifier}</td>
 *     <td>{@link IdentifiedObject#getIdentifiers()}</td>
 *   </tr><tr>
 *     <td>{@value org.opengis.referencing.IdentifiedObject#DOMAINS_KEY}</td>
 *     <td><code>{@linkplain ObjectDomain}[]</code></td>
 *     <td>{@link ObjectDomain} (see alternatives below)</td>
 *     <td>{@link IdentifiedObject#getDomains()}</td>
 *   </tr><tr>
 *     <td>{@value org.opengis.referencing.IdentifiedObject#REMARKS_KEY}</td>
 *     <td>{@link InternationalString}</td>
 *     <td>{@link String} (see localization below)</td>
 *     <td>{@link IdentifiedObject#getRemarks()}</td>
 *   </tr>
 * </table>
 *
 * <div class="note"><b>Note:</b>
 * multi-values are arrays instead of collections in order to allow implementations to check the element
 * type by Java reflection. Such reflection cannot be performed on collections because of type erasure.</div>
 *
 * <p>Implementations may allow alternative ways to define the {@code "name"} and {@code "domains"} properties
 * for user convenience:</p>
 * <table class="ogc">
 *   <caption>Convenience keys (non-standard)</caption>
 *   <tr>
 *     <th>Key</th>
 *     <th>Value type</th>
 *     <th>Alternative types</th>
 *     <th>Value returned by</th>
 *   </tr><tr>
 *     <td>{@value org.opengis.metadata.Identifier#AUTHORITY_KEY}</td>
 *     <td>{@link Citation}</td>
 *     <td>{@link String}</td>
 *     <td>{@link Identifier#getAuthority()}</td>
 *   </tr><tr>
 *     <td>{@value org.opengis.metadata.Identifier#CODESPACE_KEY}</td>
 *     <td>{@link String}</td>
 *     <td></td>
 *     <td>{@link Identifier#getCodeSpace()}</td>
 *   </tr><tr>
 *     <td>{@value org.opengis.metadata.Identifier#VERSION_KEY}</td>
 *     <td>{@link String}</td>
 *     <td></td>
 *     <td>{@link Identifier#getVersion()}</td>
 *   </tr><tr>
 *     <td>{@value org.opengis.referencing.ObjectDomain#SCOPE_KEY}</td>
 *     <td>{@link InternationalString}</td>
 *     <td>{@link String} (see localization below)</td>
 *     <td>{@link ObjectDomain#getScope()}</td>
 *   </tr><tr>
 *     <td>{@value org.opengis.referencing.ObjectDomain#DOMAIN_OF_VALIDITY_KEY}</td>
 *     <td>{@link Extent}</td>
 *     <td></td>
 *     <td>{@link ObjectDomain#getDomainOfValidity()}</td>
 *   </tr>
 * </table>
 *
 * <h2>Localization</h2>
 * Localizable attributes like {@code "remarks"} can be specified either as a single {@code InternationalString},
 * or as one or many {@code String}s associated to keys suffixed by a language and country code.
 * For example, the {@code "remarks_fr"} key stands for remarks in {@linkplain Locale#FRENCH French} and the
 * {@code "remarks_fr_CA"} key stands for remarks in {@linkplain Locale#CANADA_FRENCH French Canadian}.
 *
 * @departure harmonization
 *   This interface is not part of any OGC specification. It is added for uniformity,
 *   in order to provide a common base class for all referencing factories producing
 *   {@code IdentifiedObject} instances.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.0
 */
public interface ObjectFactory extends Factory {
}
