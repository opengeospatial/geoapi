/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.cd;

// J2SE direct dependencies
import java.util.Locale;

// OpenGIS direct dependencies
import org.opengis.rs.Identifier;


/**
 * A prime meridian defines the origin from which longitude values are determined.
 *
 * @UML abstract CD_PrimeMeridian
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface PrimeMeridian {
    /**
     * The name by which this prime meridian is identified. The <code>name</code> initial value is
     * “Greenwich”, and that value shall be used when the {@linkplain #getGreenwichLongitude
     * greenwich longitude} value is zero.
     *
     * @return The prime meridian name.
     * @UML mandatory meridianName
     *
     * @rename  Omitted the "<code>meridian</code>" prefix.
     */
    public String getName();

    /**
     * Set of alternative identifications of this prime meridian. The first identifier,
     * if any, is normally the primary identification code, and any others are aliases.
     *
     * @return  The datum identifiers, or an empty array if there is none.
     * @UML optional meridianID
     *
     * @rename  Omitted the "<code>meridian</code>" prefix.
     *          Replaced "<code>ID</code>" by "<code>Identifiers</code>" in order to
     *          1) use the return type class name and 2) use the plural form.
     */
    public Identifier[] getIdentifiers();

    /**
     * Longitude of the prime meridian measured from the Greenwich meridian, positive eastward.
     * The <code>greenwichLongitude</code> initial value is zero, and that value shall be used
     * when the {@linkplain #getName meridian name} value is “Greenwich”.
     *
     * @return The prime meridian Greenwich longitude.
     * @UML conditional greenwichLongitude
     *
     * @revisit In UML, the return type for this method is <code>Angle</code>.
     */
    public double getGreenwichLongitude();

    /**
     * Comments on the prime meridian, including data source information.
     *
     * @param  locale The desired locale for the remarks to be returned,
     *         or <code>null</code> for a non-localized string (or a default default locale).
     * @return The datum remarks, or <code>null</code> if not available.
     * @UML optional remarks
     */
    public String getRemarks(Locale locale);
}
