/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.rs;

// OpenGIS direct dependencies
import org.opengis.ex.Extent;


/**
 * Description of a spatial and temporal reference system used by a dataset.
 *  
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @see org.opengis.sc.CoordinateReferenceSystem
 */
public interface ReferenceSystem {
    /**
     * The name by which this reference system is uniquely identified.
     *
     * @return The reference system name.
     * @mandatory
     *
     * @rename  Omitted the "<code>srs</code>" prefix,
     *          which stands as an abbreviation for this enclosing class.
     *
     * @revisit Should we ask for a (possibly null) {@link java.util.Locale} argument?
     */
    public String getName();

    /**
     * Set of alternative identifications of this reference system. The first <code>ID</code>,
     * if any, is normally the primary identification code, and any others are aliases.
     *
     * @return Coordinate reference system identifiers, or an empty array if there is none.
     * @optional
     *
     * @rename  Omitted the "<code>srs</code>" prefix,
     *          which stands as an abbreviation for this enclosing class.
     * @revisit Should we rename this method as <code>getIdentifiers()</code>?
     *          Note the proposed plural form.
     */
    public Identifier[] getID();

    /**
     * Area for which the (coordinate) reference system is valid.
     *
     * @return Coordinate reference system valid area, or <code>null</code> if not available.
     * @optional
     */
    public Extent getValidArea();

    /**
     * Description of domain of usage, or limitations of usage, for which this
     * CRS object is valid.
     *
     * @return Coordinate reference system scope, or <code>null</code> if not available.
     * @optional
     *
     * @revisit Should we ask for a (possibly null) {@link java.util.Locale} argument?
     */
    public String getScope();

    /**
     * Comments on or information about this (coordinate) reference system,
     * including data source information.
     *
     * @return Coordinate reference system remarks, or <code>null</code> if not available.
     * @optional
     *
     * @revisit Should we ask for a (possibly null) {@link java.util.Locale} argument?
     */
    public String getRemarks();
}
