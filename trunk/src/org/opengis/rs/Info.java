/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.rs;

// J2SE direct dependencies
import java.util.Locale;

// OpenGIS direct dependencies
import org.opengis.rs.Identifier;


/**
 * A base interface for metadata applicable to reference system objects.
 * This specification does not dictate what the contents of these items
 * should be. However, the following guidelines are suggested:
 *
 * <UL>
 *   <LI> When {@link org.opengis.sc.CRSAuthorityFactory} is used to create an object, the
 *        {@linkplain Identifier#getAuthority authority} and {@linkplain Identifier#getCode
 *        authority code} values should be set to the authority name of the factory object,
 *        and the authority code supplied by the client, respectively. The other values may
 *        or may not be set. If the authority is EPSG, the implementer may consider using the
 *        corresponding metadata values in the EPSG tables.</LI>
 *
 *    <LI>When {@link org.opengis.sc.CRSFactory} creates an object, the {@linkplain #getName name}
 *        should be set to the value supplied by the client. All of the other metadata items may
 *        be left empty.</LI>
 * </UL>
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
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
     * @UML mandatory <prefix>Name
     *
     * @rename  Omitted the
     *          "{@link org.opengis.rs.ReferenceSystem srs}",
     *          "{@link org.opengis.cs.CoordinateSystem cs}",
     *          "{@link org.opengis.cs.CoordinateSystemAxis axis}",
     *          "{@link org.opengis.cd.Datum datum}",
     *          "{@link org.opengis.cd.PrimeMeridian meridian}",
     *          "{@link org.opengis.cd.Ellipsoid ellipsoid}",
     *          "{@link org.opengis.cc.CoordinateOperation coordinateOperation}",
     *          "{@link org.opengis.cc.OperationMethod method}",
     *          "{@link org.opengis.cc.OperationParameter parameter}" and
     *          "{@link org.opengis.cc.OperationParameterGroup group}"
     *          prefix, which stands as an abbreviation for the enclosing class.
     */
    public String getName(Locale locale);

    /**
     * Set of alternative identifications of this object. The first identifier, if
     * any, is normally the primary identification code, and any others are aliases.
     *
     * @return This object identifiers, or an empty array if there is none.
     * @UML optional <prefix>ID
     *
     * @rename  Omitted the same prefix than {@link #getName}. Also replaced
     *          "<code>ID</code>" by "<code>Identifiers</code>" in order to
     *          1) use the return type class name and 2) use the plural form.
     */
    public Identifier[] getIdentifiers();

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
    public String getRemarks(Locale locale);
}
