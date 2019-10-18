/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2003-2019 Open Geospatial Consortium, Inc.
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
 * which can not be mapped directly from Java, plus utilities.
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
 * <p>The job of a "name" in the context of ISO 19103 is to associate that name with an {@link java.lang.Object}.
 * A use of the namespace structure defined in 19103 is the translation of given names into specific storage formats.
 * XML has different naming rules than shapefiles, and both are different than netCDF.
 * Specialized names are mapped to data as below:</p>
 *
 * <ul>
 *   <li>{@link org.opengis.util.TypeName} is the name of a {@link org.opengis.util.RecordType}.</li>
 *   <li>{@link org.opengis.util.MemberName} is the name of an attribute in a {@link org.opengis.util.Record}
 *       or {@link org.opengis.util.RecordType}.</li>
 * </ul>
 *
 * The {@link org.opengis.util.GenericName} javadoc provides more details about name structure.
 *
 * <h2>Records and Schemas</h2>
 * <p>A {@link org.opengis.util.RecordType} is similar to a {@code struct} declaration in C/C++, a table definition
 * in SQL, a {@code RECORD} in Pascal, or an attribute-only {@code class} declaration in Java if it were stripped of
 * all notions of inheritance.
 * {@code RecordType}s can be organized into a named collection called {@link org.opengis.util.RecordSchema},
 * which is similar to a schema in SQL or a package in Java. Both records and schemas behave as dictionaries
 * for their members.</p>
 *
 * <p>A {@link org.opengis.util.Record} is an instance of {@code RecordType} containing the actual attribute values.
 * If we think about {@code RecordType} as equivalent to a simple feature (ISO 19109) or a Java {@code class},
 * then we can establish the following equivalence table:</p>
 *
 * <table class="ogc">
 *   <caption>Equivalences between records, features and Java constructs</caption>
 *   <tr>
 *     <th>ISO 19103</th>
 *     <th>ISO 19109</th>
 *     <th>Java equivalent</th>
 *   </tr><tr>
 *     <td>{@link org.opengis.util.Record}</td>
 *     <td>{@link org.opengis.feature.Feature}</td>
 *     <td>{@link java.lang.Object}</td>
 *   </tr><tr>
 *     <td>{@link org.opengis.util.Record#getRecordType()}</td>
 *     <td>{@link org.opengis.feature.Feature#getType()}</td>
 *     <td>{@link java.lang.Object#getClass()}</td>
 *   </tr><tr>
 *     <td>{@link org.opengis.util.RecordType}</td>
 *     <td>{@link org.opengis.feature.FeatureType}</td>
 *     <td>{@link java.lang.Class}</td>
 *   </tr><tr>
 *     <td>{@link org.opengis.util.RecordType#getTypeName()}</td>
 *     <td>{@link org.opengis.feature.FeatureType#getName()}</td>
 *     <td>{@link java.lang.Class#getName()}</td>
 *   </tr><tr>
 *     <td>{@link org.opengis.util.RecordType#getContainer()}</td>
 *     <td></td>
 *     <td>{@link java.lang.Class#getPackage()}</td>
 *   </tr><tr>
 *     <td>{@link org.opengis.util.RecordType#getMembers()}</td>
 *     <td>{@link org.opengis.feature.FeatureType#getProperties(boolean)}</td>
 *     <td>{@link java.lang.Class#getFields()}</td>
 *   </tr><tr>
 *     <td>{@link org.opengis.util.RecordType#locate RecordType.locate(MemberName)}</td>
 *     <td>{@link org.opengis.feature.FeatureType#getProperty getProperty(String)}</td>
 *     <td>{@link java.lang.Class#getField Class.getField(String)}</td>
 *   </tr><tr>
 *     <td>{@link org.opengis.util.RecordType#isInstance RecordType.isInstance(Record)}</td>
 *     <td></td>
 *     <td>{@link java.lang.Class#isInstance Class.isInstance(Object)}</td>
 *   </tr><tr>
 *     <td>{@link org.opengis.util.RecordSchema}</td>
 *     <td></td>
 *     <td>{@link java.lang.Package}</td>
 *   </tr>
 * </table>
 *
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @author  Jesse Crossley (SYS Technologies)
 * @author  Bryce Nordgren (USDA)
 * @version 3.0
 * @since   1.0
 */
package org.opengis.util;
