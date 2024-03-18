/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2024 Open Geospatial Consortium, Inc.
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

/**
 * Base interfaces for reference systems by coordinates or by identifiers.
 * A reference system contains the metadata required to interpret spatial location information unambiguously.
 * Referencing can be done by coordinates with interfaces in the {@code crs} sub-package,
 * or by identifiers with interfaces in the {@code gazetteer} sub-package.
 * The {@link org.opengis.referencing.IdentifiedObject} interface in this package
 * contains attributes common to several objects used in referencing by coordinates or by identifiers.
 * For example, a reference frame {@linkplain org.opengis.referencing.IdentifiedObject#getName() primary name}
 * might be <q>North American Datum of 1983</q>. That reference frame may have alternative names or
 * {@linkplain org.opengis.referencing.IdentifiedObject#getAlias() aliases},
 * for example, the abbreviation <q>NAD83</q>.
 *
 * <p>Another attribute is {@linkplain org.opengis.referencing.IdentifiedObject#getIdentifiers() identifiers}.
 * This is a unique code used to reference an object in a given place.
 * For example, an external geodetic register might give the NAD83 reference frame a unique code of <q>6269</q>.
 * Objects can be obtained from codes using sub-interfaces of {@link org.opengis.referencing.AuthorityFactory}.
 * Identifiers have a data type of {@link org.opengis.metadata.Identifier}.</p>
 *
 * <h2>Well-Known Text format</h2>
 * Many entities can be printed in a
 * {@linkplain org.opengis.annotation.Specification#ISO_19162 Well-Known Text (WKT)} format
 * by invoking the {@link org.opengis.referencing.IdentifiedObject#toWKT()} method.
 * This allows objects to be stored in databases (persistence),
 * and transmitted between inter-operating computer programs.
 *
 * <h2>Immutability</h2>
 * Most interfaced objects are immutable. This means that implementations should not
 * change an object's internal state once they have handed out an interface pointer.
 * Since most interfaced objects are specified to be immutable,
 * there do not need to be any constraints on operation sequencing.
 * This means that these interfaces can be used in parallel computing environments (e.g. Internet servers).
 *
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @author  Ely Conn (Leica Geosystems Geospatial Imaging, LLC)
 * @version 3.1
 * @since   1.0
 */
package org.opengis.referencing;
