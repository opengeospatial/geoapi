/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.referencing;

// OpenGIS dependencies
import org.opengis.metadata.citation.Citation;


/**
 * Base interface for all factories of {@link Info} objects. Factories build up complex
 * objects from simpler objects or values. <code>Factory</code> allows applications to
 * make {@linkplain org.opengis.referencing.cs.CoordinateSystem coordinate systems},
 * {@linkplain org.opengis.referencing.datum.Datum} or
 * {@linkplain org.opengis.referencing.crs.CoordinateReferenceSystem coordinate reference systems}
 * that cannot be created by an {@link AuthorityFactory}. This factory is very flexible,
 * whereas the authority factory is easier to use.
 * <br><br>
 * <H3>Object properties</H3>
 * <P>Most factory methods expect a {@link java.util.Map} argument. The map is often (but is not required
 * to be) a {@link java.util.Properties} instance. The map shall contains at least a <code>"name"</code>
 * property. In the common case where the name is the only property, the map may be constructed with
 * <code>Collections.{@linkplain java.util.Collections#singletonMap singletonMap}("name",
 * <var>theName</var>)</code> where <var>theName</var> is an arbitrary name as free text.
 * Additionally, implementations are encouraged to recognize at least the properties listed
 * in the following table. More implementation-specific properties may be added as well.
 * In any case, unknown properties will be ignored.</P>
 *
 * <table border='1'>
 *   <tr bgcolor="#CCCCFF" class="TableHeadingColor">
 *     <th nowrap>Property name</th>
 *     <th nowrap>Value type</th>
 *     <th nowrap>Value given to</th>
 *   </tr>
 *   <tr>
 *     <td nowrap>&nbsp;<code>"name"</code>&nbsp;</td>
 *     <td nowrap>&nbsp;{@link String}&nbsp;</td>
 *     <td nowrap>&nbsp;{@link Info#getName}</td>
 *   </tr>
 *   <tr>
 *     <td nowrap>&nbsp;<code>"remarks"</code>&nbsp;</td>
 *     <td nowrap>&nbsp;{@link String}&nbsp;</td>
 *     <td nowrap>&nbsp;{@link Info#getRemarks}</td>
 *   </tr>
 *   <tr>
 *     <td nowrap>&nbsp;<code>"authority"</code>&nbsp;</td>
 *     <td nowrap>&nbsp;{@link String} or {@link org.opengis.metadata.citation.Citation}&nbsp;</td>
 *     <td nowrap>&nbsp;{@link Identifier#getAuthority} on the first identifier</td>
 *   </tr>
 *   <tr>
 *     <td nowrap>&nbsp;<code>"code"</code>&nbsp;</td>
 *     <td nowrap>&nbsp;{@link String}&nbsp;</td>
 *     <td nowrap>&nbsp;{@link Identifier#getCode} on the first identifier</td>
 *   </tr>
 *   <tr>
 *     <td nowrap>&nbsp;<code>"codeSpace"</code>&nbsp;</td>
 *     <td nowrap>&nbsp;{@link String}&nbsp;</td>
 *     <td nowrap>&nbsp;{@link Identifier#getCodeSpace} on the first identifier</td>
 *   </tr>
 *   <tr>
 *     <td nowrap>&nbsp;<code>"version"</code>&nbsp;</td>
 *     <td nowrap>&nbsp;{@link String}&nbsp;</td>
 *     <td nowrap>&nbsp;{@link Identifier#getVersion} on the first identifier</td>
 *   </tr>
 *   <tr>
 *     <td nowrap>&nbsp;<code>"identifiers"</code>&nbsp;</td>
 *     <td nowrap>&nbsp;<code>{@linkplain Identifier}</code>[]&nbsp;</td>
 *     <td nowrap>&nbsp;{@link Info#getIdentifiers}</td>
 *   </tr>
 * </table>
 *
 * <P>The <code>"name"</code> property is mandatory. All others are optional. Additionally, all
 * localizable attributes like <code>"name"</code> and <code>"remarks"</code> may have a language
 * and country code suffix. For example the <code>"remarks_fr"</code> property stands for remarks
 * in {@linkplain java.util.Locale#FRENCH French} and the <code>"remarks_fr_CA"</code> property
 * stands for remarks in {@linkplain java.util.Locale#CANADA_FRENCH French Canadian}.</P>
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-009.pdf">Implementation specification 1.0</A>
 */
public interface Factory {
    /**
     * Returns the vendor responsible for creating this factory implementation. Many implementations
     * may be available for the same factory interface. Implementations are usually managed by a
     * {@linkplain javax.imageio.spi.ServiceRegistry service registry}.
     *
     * @return The vendor for this factory implementation.
     */
    Citation getVendor();
}
