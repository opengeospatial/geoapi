/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.referencing;

// J2SE direct dependencies
import java.util.Locale;


/**
 * A base interface for metadata applicable to reference system objects.
 * This specification does not dictate what the contents of these items
 * should be. However, the following guidelines are suggested:
 *
 * <UL>
 *   <LI> When {@link org.opengis.referencing.crs.CRSAuthorityFactory} is used to create an object, the
 *        {@linkplain Identifier#getAuthority authority} and {@linkplain Identifier#getCode
 *        authority code} values should be set to the authority name of the factory object,
 *        and the authority code supplied by the client, respectively. The other values may
 *        or may not be set. If the authority is EPSG, the implementer may consider using the
 *        corresponding metadata values in the EPSG tables.</LI>
 *
 *    <LI>When {@link org.opengis.referencing.crs.CRSFactory} creates an object, the {@linkplain #getName name}
 *        should be set to the value supplied by the client. All of the other metadata items may
 *        be left empty.</LI>
 * </UL>
 *
 * @UML abstract CS_Info
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-009.pdf">Implementation specification 1.0</A>
 */
public interface Info {
    /**
     * The name by which this object is identified. 
     *
     * @param  locale The desired locale for the name to be returned, or <code>null</code>
     *         for a name in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The object name in the given locale.
     *         If no name is available in the given locale, then some default locale is used.
     * @UML mandatory &lt;prefix&gt;Name
     *
     * @rename  Omitted the
     *          "{@link org.opengis.referencing.ReferenceSystem srs}",
     *          "{@link org.opengis.referencing.cs.CoordinateSystem cs}",
     *          "{@link org.opengis.referencing.cs.CoordinateSystemAxis axis}",
     *          "{@link org.opengis.referencing.datum.Datum datum}",
     *          "{@link org.opengis.referencing.datum.PrimeMeridian meridian}",
     *          "{@link org.opengis.referencing.datum.Ellipsoid ellipsoid}",
     *          "{@link org.opengis.referencing.operation.CoordinateOperation coordinateOperation}",
     *          "{@link org.opengis.referencing.operation.OperationMethod method}",
     *          "{@link org.opengis.parameter.OperationParameter parameter}" and
     *          "{@link org.opengis.parameter.OperationParameterGroup group}"
     *          prefix, which stands as an abbreviation for the enclosing class.
     */
    String getName(Locale locale);

    /**
     * Set of alternative identifications of this object. The first identifier, if
     * any, is normally the primary identification code, and any others are aliases.
     *
     * @return This object identifiers, or an empty array if there is none.
     * @UML optional &lt;prefix&gt;ID
     *
     * @rename  Omitted the same prefix than {@link #getName}. Also replaced
     *          "<code>ID</code>" by "<code>Identifiers</code>" in order to
     *          1) use the return type class name and 2) use the plural form.
     */
    Identifier[] getIdentifiers();

    /**
     * Comments on or information about this object, including data source information.
     *
     * @param  locale The desired locale for the remarks to be returned, or <code>null</code>
     *         for remarks in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The remarks in the given locale, or <code>null</code> if none.
     *         If no remark is available in the given locale, then some default locale is used.
     * @UML optional remarks
     */
    String getRemarks(Locale locale);
}
