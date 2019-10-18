/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2019 Open Geospatial Consortium, Inc.
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

import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.Obligation.MANDATORY;


/**
 * A {@linkplain LocalName local name} that references a {@linkplain RecordType record type}
 * in a {@linkplain RecordSchema record schema}.
 * Can also be used as the name of a <cite>feature type</cite>.
 *
 * <p>{@code TypeName}s are returned by the following methods:</p>
 * <ul>
 *   <li>{@link RecordType#getTypeName()} for the name of the type definition.</li>
 *   <li>{@link MemberName#getAttributeType()} as a reference to the type definition of the attribute.</li>
 * </ul>
 *
 * <h2>Comparison with the Java language</h2>
 * A {@code TypeName} is similar to the name of a java {@link Class}.
 * By contrast, a {@link MemberName} is similar to the name of a field in a Java class.
 * <p>
 * It is sometime useful to establish a mapping between {@code TypeName} and {@code Class}.
 * Such mapping is left to implementors, but the following table can be used as an example.
 * This example uses UML identifiers, arbitrarily prefixed by the {@code "OGC"} namespace
 * (this is <strong>not</strong> a standard practice, and should not be used for types not found in OGC standards).
 * The definition identifiers in OGC namespace are also shown for information purpose.
 * Those identifiers would be a more standard alternative, but cover only a small subset of types
 * and sometime do not provide an exact match. Available OGC identifiers can be browsed on
 * <a href="http://schemas.opengis.net/definitions/">http://schemas.opengis.net/definitions/</a>.
 * </p>
 *
 * <table class="ogc">
 *   <caption>Examples of mapping from Java classes to type names</caption>
 *   <tr><th>Java class</th>                            <th>Name example</th>                  <th>Definition identifier in OGC namespace</th></tr>
 *   <tr><td>{@link java.lang.String}</td>              <td>"{@code OGC:CharacterString}"</td> <td>urn:ogc:def:dataType:OGC::string</td></tr>
 *   <tr><td>{@link java.util.Date}</td>                <td>"{@code OGC:DateTime}"</td>        <td></td></tr>
 *   <tr><td>{@link java.lang.Double}</td>              <td>"{@code OGC:Real}"</td>            <td></td></tr>
 *   <tr><td>{@link java.lang.Integer}</td>             <td>"{@code OGC:Integer}"</td>         <td>urn:ogc:def:dataType:OGC::nonNegativeInteger</td></tr>
 *   <tr><td>{@link java.lang.Boolean}</td>             <td>"{@code OGC:Boolean}"</td>         <td>urn:ogc:def:dataType:OGC::boolean</td></tr>
 *   <tr><td>{@link org.opengis.metadata.Metadata}</td> <td>"{@code OGC:MD_Metadata}"</td>     <td></td></tr>
 * </table>
 *
 * @author  Bryce Nordgren (USDA)
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   2.1
 *
 * @see NameFactory#createTypeName(NameSpace, CharSequence)
 */
@UML(identifier="TypeName", specification=ISO_19103)
public interface TypeName extends LocalName {
    /**
     * Returns the local name of the type as a {@code String}.
     * Type names typically use a {@code '.'} or {@code ':'} navigation separator, so that their
     * {@linkplain #toFullyQualifiedName() fully qualified name} is of the form {@code "[package].[class]"}
     * or {@code "[schema]:[type]"}.
     *
     * @return the local name of the type.
     */
    @Override
    @UML(identifier="aName", obligation=MANDATORY, specification=ISO_19103)
    String toString();
}
