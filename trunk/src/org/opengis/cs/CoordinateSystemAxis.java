/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.cs;

// J2SE direct dependencies and extensions
import java.util.Locale;
import javax.units.Unit;

// OpenGIS direct dependencies
import org.opengis.rs.Info;


/**
 * Definition of a coordinate system axis.
 * See <A HREF="package-summary.html#AxisNames">axis name constraints</A>.
 *
 * @UML abstract CS_CoordinateSystemAxis
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @see CoordinateSystem
 * @see Unit
 */
public interface CoordinateSystemAxis extends Info {
    /**
     * The abbreviation used for this coordinate system axes. This abbreviation is also
     * used to identify the ordinates in coordinate tuple. Examples are "<var>X</var>"
     * and "<var>Y</var>".
     *
     * @return The coordinate system axis abbreviation.
     * @UML mandatory axisAbbrev
     */
    public String getAbbreviation();

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
     * @param  locale The desired locale for the coordinate system axis direction to be returned,
     *         or <code>null</code> for a direction in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The coordinate system axis direction in the given locale. If no direction
     *         is available in the given locale, then some default locale is used.
     * @UML mandatory axisDirection
     */
    public String getDirection(Locale locale);

    /**
     * The unit of measure used for this coordinate system axis. The value of this
     * coordinate in a coordinate tuple shall be recorded using this unit of measure,
     * whenever those coordinates use a coordinate reference system that uses a
     * coordinate system that uses this axis.
     *
     * @return  The coordinate system axis unit.
     * @UML mandatory axisUnitID
     */
    public Unit getUnit();
}
