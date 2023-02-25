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

/**
 * {@linkplain org.opengis.referencing.ReferenceSystem Reference systems}. The following is adapted from
 * {@linkplain org.opengis.annotation.Specification#ISO_19111 OpenGIS&reg; Spatial Referencing by
 * Coordinates (Topic 2)} specification.
 *
 * <P ALIGN="justify">A reference system contains the metadata required to
 * interpret spatial location information unambiguously. The description of
 * an object's attributes can be done explicitly, by providing all defining
 * parameters, or by identifier, a reference to a recognised source that contains
 * a full description of the object.</P>
 *
 * <P ALIGN="justify">The {@link org.opengis.referencing.IdentifiedObject} interface contains
 * attributes common to several objects used in spatial referencing by coordinates. For example,
 * a {@linkplain org.opengis.referencing.datum.Datum datum} name might be "North American Datum
 * of 1983". This may have alternative names or aliases, for example the abbreviation "NAD83".
 * Object {@linkplain org.opengis.referencing.IdentifiedObject#getName primary names} have a data type
 * {@link org.opengis.metadata.Identifier} whilst {@linkplain org.opengis.referencing.IdentifiedObject#getAlias aliases}
 * have a data type {@link org.opengis.util.GenericName}.</P>
 *
 * <P ALIGN="justify">Another attribute is {@linkplain org.opengis.referencing.IdentifiedObject#getIdentifiers identifiers}.
 * This is a unique code used to reference an object in a given place. For example, an external geodetic register might
 * give the NAD83 datum a unique code of "6269". Identifiers have a data type of {@link org.opengis.metadata.Identifier}.
 * In addition to the use of an identifier as a reference to a definition in a remote register, it may also be included
 * in an object definition to allow remote users to refer to the object.</P>
 *
 * <P ALIGN="justify">Most interfaced objects are immutable. This means that implementations promise
 * not to change an object's internal state once they have handed out an interface pointer. Since
 * most interfaced objects are specified to be immutable, there do not need to be any constraints
 * on operation sequencing. This means that these interfaces can be used in parallel computing
 * environments (e.g. internet servers).</P>
 *
 * <H2>Well-Known Text format</H2>
 * <P ALIGN="justify">Many entities in this specification can be printed in a well-known text
 * format. This allows objects to be stored in databases (persistence), and transmitted between
 * interoperating computer programs. The <A HREF="doc-files/WKT.html">definition for WKT</A> is
 * shown using Extended Backus Naur Form (EBNF).</P>
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Ely Conn (Leica Geosystems Geospatial Imaging, LLC)
 * @version 3.0
 * @since   1.0
 */
package org.opengis.referencing;
