/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.cd;

// OpenGIS direct dependencies
import org.opengis.rs.Identifier;


/**
 * A prime meridian defines the origin from which longitude values are determined.
 *  
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
     * @mandatory
     *
     * @rename  Omitted the "<code>meridian</code>" prefix.
     */
    public String getName();

    /**
     * Set of alternative identifications of this prime meridian. The first <code>ID</code>,
     * if any, is normally the primary identification code, and any others are aliases.
     *
     * @return  The datum identifiers, or an empty array if there is none.
     * @optional
     *
     * @rename  Omitted the "<code>meridian</code>" prefix.
     * @revisit Should we rename this method as <code>getIdentifiers()</code>?
     *          Note the proposed plural form.
     */
    public Identifier[] getID();

    /**
     * Longitude of the prime meridian measured from the Greenwich meridian, positive eastward.
     * The <code>greenwichLongitude</code> initial value is zero, and that value shall be used
     * when the {@linkplain #getName meridian name} value is “Greenwich”.
     *
     * @return The prime meridian Greenwich longitude.
     * @revisit In UML, the return type for this method is <code>Angle</code>.
     */
    public double getGreenwichLongitude();

    /**
     * Comments on the prime meridian, including data source information.
     *
     * @return The datum remarks, or <code>null</code> if not available.
     * @optional
     *
     * @revisit Should we ask for a (possibly null) {@link java.util.Locale} argument?
     */
    public String getRemarks();
}
