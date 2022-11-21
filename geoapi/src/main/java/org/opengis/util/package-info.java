/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2003-2021 Open Geospatial Consortium, Inc.
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
 * A set of base types from {@linkplain org.opengis.annotation.Specification#ISO_19103 ISO 19103}
 * which cannot be mapped directly from Java, plus utilities.
 * Type hierarchy is as below:
 *
 * <table class="ogc">
 * <caption>Package overview</caption>
 * <tr>
 *   <th>Records</th>
 *   <th class="sep">Names</th>
 *   <th class="sep">Factories</th>
 *   <th class="sep">Other</th>
 * </tr><tr><td style="width: 25%; white-space: nowrap">
 * <pre> ISO 19103 object
 *  ├─ {@linkplain org.opengis.util.RecordSchema}
 *  ├─ {@linkplain org.opengis.util.Type}
 *  │   └─ {@linkplain org.opengis.util.RecordType}
 *  └─ {@linkplain org.opengis.util.Record}</pre>
 * </td><td class="sep" style="width: 25%; white-space: nowrap">
 * <pre> ISO 19103 object
 *  └─ {@linkplain org.opengis.util.GenericName}
 *      ├─ {@linkplain org.opengis.util.ScopedName}
 *      └─ {@linkplain org.opengis.util.LocalName}
 *          ├─ {@linkplain org.opengis.util.TypeName}
 *          └─ {@linkplain org.opengis.util.MemberName}</pre>
 * </td><td class="sep" style="width: 25%; white-space: nowrap">
 * <pre> GeoAPI object
 *  └─ {@linkplain org.opengis.util.Factory}
 *      ├─ {@linkplain org.opengis.util.NameFactory}
 *      ├─ {@linkplain org.opengis.referencing.ObjectFactory}
 *      └─ {@linkplain org.opengis.referencing.AuthorityFactory}</pre>
 * </td><td class="sep" style="width: 25%; white-space: nowrap">
 * <pre> GeoAPI object
 *  └─ {@linkplain org.opengis.util.InternationalString}</pre>
 * </td></tr></table>
 *
 * <h2>Names and Namespaces</h2>
 * <p>The job of a "name" is to associate that name with an {@link java.lang.Object} within the context of a
 * {@link org.opengis.util.NameSpace}. For example, {@link org.opengis.util.GenericName} instances could be
 * keys in a {@link java.util.HashMap}, in which case the namespace is materialized by the {@code HashMap}.
 * Names are often used in the context of reading data from various formats such as XML, shapefiles or netCDF,
 * which have different constraints for names in their namespaces.
 * The {@link org.opengis.util.GenericName} javadoc provides more details about name structure.</p>
 *
 * <h2>Records and Schemas</h2>
 * <p>{@link org.opengis.util.Record} and {@link org.opengis.util.RecordType} are lookup mechanisms
 * that associate field names to <var>values</var> and <var>value types</var> respectively.
 * Field names are locally mapped, and field types are most often primitives.
 * Because the {@code RecordType} describes the structure of a set of records,
 * it is essentially a metaclass for that set of records viewed as a class.
 * A {@code Record} is an instance of {@code RecordType} containing the actual field values.</p>
 *
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @author  Jesse Crossley (SYS Technologies)
 * @author  Bryce Nordgren (USDA)
 * @version 3.1
 * @since   1.0
 */
package org.opengis.util;
