/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.cs;

// J2SE direct dependencies
import java.util.Locale;

// OpenGIS direct dependencies
import org.opengis.rs.Identifier;
import org.opengis.cs.UnitOfMeasure;


/**
 * Definition of a coordinate system axis.
 *
 * @UML abstract CS_CoordinateSystemAxis
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @see CoordinateSystem
 * @see UnitOfMeasure
 */
public interface CoordinateSystemAxis {
    /**
     * Name of the coordinate system axis.
     * See <A HREF="package-summary.html#AxisNames">axis name constraints</A>.
     *
     * @return The coordinate system axis name.
     * @UML mandatory axisName
     *
     * @rename  Omitted the "<code>axis</code>" prefix.
     */
    public String getName();

    /**
     * Set of alternative identifications of the coordinate system axis. The first identifier,
     * if any, is normally the primary identification code, and any others are aliases. 
     *
     * @return The coordinate system axis identifiers, or an empty array if there is none.
     * @UML optional axisID
     *
     * @rename  Omitted the "<code>axis</code>" prefix.
     *          Replaced "<code>ID</code>" by "<code>Identifiers</code>" in order to
     *          1) use the return type class name and 2) use the plural form.
     */
    public Identifier[] getIdentifiers();

    /**
     * The abbreviation used for this coordinate system axes. This abbreviation is also used
     * to identify the ordinates in coordinate tuple. Examples are <var>X</var> and <var>Y</var>.
     *
     * @return The coordinate system axis abbreviation.
     * @UML mandatory axisAbbrev
     *
     * @rename  Omitted the "<code>axis</code>" prefix.
     * @revisit Shoud we rename this method as <code>getAbbreviation()</code>?
     */
    public String getAbbrev();

    /**
     * Direction of this coordinate system axis. In the case of Cartesian projected coordinates,
     * this is the direction of this coordinate system axis locally. Examples: north or south, east
     * or west, up or down. Within any set of coordinate system axes, only one of each pair of terms
     * can be used. For earth-fixed coordinate reference systems, this direction is often
     * approximate and intended to provide a human interpretable meaning to the axis. When a
     * geodetic datum is used, the precise directions of the axes may therefore vary slightly
     * from this approximate direction.
     *
     * Note that an {@link org.opengis.sc.EngineeringCRS} often requires specific descriptions
     * of the directions of its coordinate system axes.
     *
     * @return The coordinate system axis direction
     * @UML mandatory axisDirection
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
     * @UML mandatory axisUnitID
     *
     * @rename  Omitted the "<code>axis</code>" prefix.
     *          Removed also the "<code>ID</code>" suffix, since this method returns a real unit
     *          object (not an ID).
     */
    public UnitOfMeasure getUnit();

    /**
     * Comments on or information about the coordinate system, including data source information.
     *
     * @param  locale The desired locale for the remarks to be returned,
     *         or <code>null</code> for a non-localized string (or a default default locale).
     * @return The coordinate system remarks, or <code>null</code> if not available.
     * @UML optional remarks
     */
    public String getRemarks(Locale locale);
}
