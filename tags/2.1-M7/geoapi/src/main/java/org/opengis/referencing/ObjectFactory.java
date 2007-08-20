/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.referencing;

import org.opengis.metadata.Identifier;
import org.opengis.metadata.citation.Citation;
import org.opengis.util.InternationalString;
import org.opengis.util.GenericName;
import org.opengis.annotation.Extension;


/**
 * Base interface for all factories of {@linkplain IdentifiedObject identified objects}. Factories
 * build up complex objects from simpler objects or values. This factory allows applications
 * to make {@linkplain org.opengis.referencing.cs.CoordinateSystem coordinate systems},
 * {@linkplain org.opengis.referencing.datum.Datum datum} or
 * {@linkplain org.opengis.referencing.crs.CoordinateReferenceSystem coordinate reference systems}
 * that cannot be created by an {@linkplain AuthorityFactory authority factory}. This factory is
 * very flexible, whereas the authority factory is easier to use.
 * <p>
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
 *     <td nowrap>&nbsp;{@value org.opengis.referencing.IdentifiedObject#NAME_KEY}&nbsp;</td>
 *     <td nowrap>&nbsp;{@link org.opengis.referencing.ReferenceIdentifier} or {@link String}&nbsp;</td>
 *     <td nowrap>&nbsp;{@link IdentifiedObject#getName}</td>
 *   </tr>
 *   <tr>
 *     <td nowrap>&nbsp;{@value org.opengis.referencing.IdentifiedObject#ALIAS_KEY}&nbsp;</td>
 *     <td nowrap>&nbsp;{@link String}, <code>{@linkplain String}[]</code>,
 *     {@link GenericName} or <code>{@linkplain GenericName}[]</code>&nbsp;</td>
 *     <td nowrap>&nbsp;{@link IdentifiedObject#getAlias}</td>
 *   </tr>
 *   <tr>
 *     <td nowrap>&nbsp;{@value org.opengis.metadata.Identifier#AUTHORITY_KEY}&nbsp;</td>
 *     <td nowrap>&nbsp;{@link String} or {@link Citation}&nbsp;</td>
 *     <td nowrap>&nbsp;{@link Identifier#getAuthority} on the {@linkplain IdentifiedObject#getName name}</td>
 *   </tr>
 *   <tr>
 *     <td nowrap>&nbsp;{@value ReferenceIdentifier#CODESPACE_KEY}&nbsp;</td>
 *     <td nowrap>&nbsp;{@link String}&nbsp;</td>
 *     <td nowrap>&nbsp;{@link ReferenceIdentifier#getCodeSpace} on the {@linkplain IdentifiedObject#getName name}</td>
 *   </tr>
 *   <tr>
 *     <td nowrap>&nbsp;{@value ReferenceIdentifier#VERSION_KEY}&nbsp;</td>
 *     <td nowrap>&nbsp;{@link String}&nbsp;</td>
 *     <td nowrap>&nbsp;{@link ReferenceIdentifier#getVersion} on the {@linkplain IdentifiedObject#getName name}</td>
 *   </tr>
 *   <tr>
 *     <td nowrap>&nbsp;{@value org.opengis.referencing.IdentifiedObject#IDENTIFIERS_KEY}&nbsp;</td>
 *     <td nowrap>&nbsp;{@link Identifier} or <code>{@linkplain Identifier}[]</code>&nbsp;</td>
 *     <td nowrap>&nbsp;{@link IdentifiedObject#getIdentifiers}</td>
 *   </tr>
 *   <tr>
 *     <td nowrap>&nbsp;{@value org.opengis.referencing.IdentifiedObject#REMARKS_KEY}&nbsp;</td>
 *     <td nowrap>&nbsp;{@link String} or {@link InternationalString}&nbsp;</td>
 *     <td nowrap>&nbsp;{@link IdentifiedObject#getRemarks}</td>
 *   </tr>
 * </table>
 *
 * <P>The <code>"name"</code> property is mandatory. All others are optional. Additionally, all
 * localizable attributes like <code>"remarks"</code> may have a language and country code suffix.
 * For example the <code>"remarks_fr"</code> property stands for remarks in
 * {@linkplain java.util.Locale#FRENCH French} and the <code>"remarks_fr_CA"</code> property
 * stands for remarks in {@linkplain java.util.Locale#CANADA_FRENCH French Canadian}.</P>
 *
 * @version <A HREF="http://www.opengis.org/docs/01-009.pdf">Implementation specification 1.0</A>
 * @author Open Geospatial Consortium
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@Extension
public interface ObjectFactory extends Factory {
}
