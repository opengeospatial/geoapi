/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.cs;

// OpenGIS direct dependencies
import org.opengis.rs.Identifier;
import org.opengis.cs.UnitOfMeasure;


/**
 * Definition of a coordinate system axis.
 *  
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @see CoordinateSystem
 * @see UnitOfMeasure
 */
public interface CoordinateSystemAxis {
    /**
     * Name of the coordinate system axis.
     *
     * @return The coordinate system axis name.
     * @mandatory
     *
     * @rename  Omitted the "<code>axis</code>" prefix.
     */
    public String getName();

    /**
     * Set of alternative identifications of the coordinate system axis. The first <code>ID</code>,
     * if any, is normally the primary identification code, and any others are aliases. 
     *
     * @return The coordinate system axis identifiers, or an empty string if there is none.
     * @optional
     *
     * @rename  Omitted the "<code>axis</code>" prefix.
     * @revisit Should we rename this method as <code>getIdentifiers()</code>?
     *          Note the proposed plural form.
     */
    public Identifier[] getID();

    /**
     * The abbreviation used for this coordinate system axes. This abbreviation is also used
     * to identify the ordinates in coordinate tuple. Examples are <var>X</var> and <var>Y</var>.
     *
     * @return The coordinate system axis abbreviation.
     * @mandatory
     *
     * @rename  Omitted the "<code>axis</code>" prefix.
     * @revisit Shoud we rename this method as <code>getAbbreviation()</code>?
     */
    public String getAbbrev();

    /**
     * Direction of this coordinate system axis (or in the case of Cartesian projected coordinates,
     * the direction of this coordinate system axis locally). Examples: north or south, east or
     * west, up or down. Within any set of coordinate system axes, only one of each pair of terms
     * can be used. For earth-fixed coordinate reference systems, this direction is often
     * approximate and intended to provide a human interpretable meaning to the axis. When a
     * geodetic datum is used, the precise directions of the axes may therefore vary slightly
     * from this approximate direction.
     *
     * Note that an {@link org.opengis.sc.EngineeringCRS} often requires specific descriptions
     * of the directions of its coordinate system axes.
     *
     * @return The coordinate system axis direction
     * @mandatory
     *
     * @rename  Omitted the "<code>axis</code>" prefix.
     */
    public String getDirection();

    /**
     * Identifier of the unit of measure used for this coordinate system axis. The value of this
     * coordinate in a coordinate tuple shall be recorded using this unit of measure, whenever
     * those coordinates use a coordinate reference system that uses a coordinate system that
     * uses this axis.
     *
     * @return  The coordinate system axis unit identifier.
     * @mandatory
     *
     * @rename  Omitted the "<code>axis</code>" prefix.
     *          Removed also the "<code>ID</code>" suffix, since this method returns a real unit
     *          object (not an ID).
     */
    public UnitOfMeasure getUnit();

    /**
     * Comments on or information about the coordinate system, including data source information.
     *
     * @return  The coordinate system remarks, or <code>null</code> if not available.
     * @optional
     *
     * @revisit Should we ask for a (possibly null) java.util.Locale argument?
     */
    public String getRemarks();
}
