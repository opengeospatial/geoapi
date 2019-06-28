/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2019 Open Geospatial Consortium, Inc.
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

/**
 * Base interfaces for {@linkplain org.opengis.referencing.ReferenceSystem reference systems}. The following
 * is adapted from {@linkplain org.opengis.annotation.Specification#ISO_19111 OpenGIS® Spatial Referencing by
 * Coordinates (Topic 2)} specification.
 *
 * <p>A reference system contains the metadata required to interpret spatial location information unambiguously.
 * The description of an object's attributes can be done</p>
 *
 * <ul>
 *   <li>explicitly, by providing all defining parameters, or</li>
 *   <li>by identifier, a reference to a recognised source that contains a full description of the object.</li>
 * </ul>
 *
 * The {@link org.opengis.referencing.IdentifiedObject} interface contains attributes common to several objects
 * used in spatial referencing by coordinates. For example, a {@linkplain org.opengis.referencing.datum.Datum datum}
 * {@linkplain org.opengis.referencing.IdentifiedObject#getName() primary names} might be “<cite>North American Datum
 * of 1983</cite>”. This may have alternative names or {@linkplain org.opengis.referencing.IdentifiedObject#getAlias()
 * aliases}, for example the abbreviation “NAD83”.
 *
 * <p>Another attribute is {@linkplain org.opengis.referencing.IdentifiedObject#getIdentifiers() identifiers}.
 * This is a unique code used to reference an object in a given place. For example, an external geodetic register might
 * give the NAD83 datum a unique code of “6269”. Identifiers have a data type of {@link org.opengis.metadata.Identifier}.
 * In addition to the use of an identifier as a reference to a definition in a remote register, it may also be included
 * in an object definition to allow remote users to refer to the object.</p>
 *
 * <p>Most interfaced objects are immutable. This means that implementations promise
 * not to change an object's internal state once they have handed out an interface pointer. Since
 * most interfaced objects are specified to be immutable, there do not need to be any constraints
 * on operation sequencing. This means that these interfaces can be used in parallel computing
 * environments (e.g. Internet servers).</p>
 *
 * <h2>Well-Known Text format</h2>
 * <p>Many entities in this specification can be printed in a well-known text format.
 * This allows objects to be stored in databases (persistence), and transmitted between
 * interoperating computer programs. Well-Known Texts (WKT) may come in two formats:
 *
 * <ul>
 *   <li>The current standard, WKT 2, is defined by ISO 19162.</li>
 *   <li>The legacy format, WKT 1, is shown using Extended Backus Naur Form (EBNF)
 *       <a href="doc-files/WKT.html">here</a>.</li>
 * </ul>
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Ely Conn (Leica Geosystems Geospatial Imaging, LLC)
 * @version 3.1
 * @since   1.0
 */
package org.opengis.referencing;
