/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.cs;

// OpenGIS direct dependencies
import org.opengis.rs.Identifier;


/**
 * The set of coordinate system axes that spans a given coordinate space. A coordinate system (CS)
 * is derived from a set of (mathematical) rules for specifying how coordinates in a given space
 * are to be assigned to points. The coordinate values in a coordinate tuple shall be recorded in
 * the order in which the coordinate system axes associations are recorded, whenever those
 * coordinates use a coordinate reference system that uses this coordinate system.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @see org.opengis.cs.CoordinateSystemAxis
 * @see org.opengis.cs.UnitOfMeasure
 * @see org.opengis.cd.Datum
 * @see org.opengis.sc.CoordinateReferenceSystem
 */
public interface CoordinateSystem {
    /**
     * The name by which this coordinate system is identified. 
     *
     * @return The coordinate system name.
     * @mandatory
     *
     * @rename  Omitted the "<code>srs</code>" prefix,
     *          which stands as an abbreviation for this enclosing class.
     *
     * @revisit Should we ask for a (possibly null) java.util.Locale argument?
     */
    public String getName();

    /**
     * Set of alternative identifications of the coordinate system. The first <code>ID</code>,
     * if any, is normally the primary identification code, and any others are aliases.
     *
     * @return Coordinate system identifiers, or an empty string if there is none.
     * @optional
     *
     * @rename  Omitted the "<code>srs</code>" prefix,
     *          which stands as an abbreviation for this enclosing class.
     * @revisit Should we rename this method as <code>getIdentifiers()</code>?
     *          Note the proposed plural form.
     */
    public Identifier[] getID();

    /**
     * Returns the ordered set of axis for this coordinate system. Each coordinate system
     * must have at least one axis.
     *
     * @return The ordered set of axis.
     * @association
     */
    public CoordinateSystemAxis[] getAxis();

    /**
     * Comments on or information about the coordinate system, including data source information.
     *
     * @return The coordinate system remarks, or <code>null</code> if not available.
     * @optional
     *
     * @revisit Should we ask for a (possibly null) java.util.Locale argument?
     */
    public String getRemarks();
}
