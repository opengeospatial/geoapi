/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The GDAL wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementors can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.wrapper.gdal;

import javax.measure.Unit;
import org.gdal.osr.SpatialReference;
import org.gdal.osr.osrConstants;
import org.opengis.referencing.cs.AxisDirection;
import org.opengis.referencing.cs.CoordinateSystemAxis;
import org.opengis.referencing.cs.RangeMeaning;


/**
 * A single axis of the GDAL spatial reference system object.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class Axis extends ReferencingObject implements CoordinateSystemAxis {
    /**
     * Axis orientation has one of {@link osrConstants} constant.
     */
    private final int orientation;

    /**
     * Creates a new axis for the given GDAL reference system.
     *
     * @param  srs         the GDAL spatial reference system information.
     * @param  kind       the kind of CRS for which to create an axis: {@code "GEOGCS"}, {@code "PROJCS"}, <var>etc.</var>
     * @param  dimension  the dimension for which to create an axis, starting at 0.
     */
    Axis(final SpatialReference srs, final String kind, final int dimension) {
        super(srs.GetAxisName(kind, dimension));
        orientation = srs.GetAxisOrientation(kind, dimension);
    }

    /**
     * Returns the direction of this coordinate system axis as indicated by GDAL {@code GetAxisOrientation(…)} method.
     */
    @Override
    public AxisDirection getDirection() {
        switch (orientation) {
            default:                     return AxisDirection.OTHER;
            case osrConstants.OAO_North: return AxisDirection.NORTH;
            case osrConstants.OAO_South: return AxisDirection.SOUTH;
            case osrConstants.OAO_East:  return AxisDirection.EAST;
            case osrConstants.OAO_West:  return AxisDirection.WEST;
            case osrConstants.OAO_Up:    return AxisDirection.UP;
            case osrConstants.OAO_Down:  return AxisDirection.DOWN;
        }
    }

    @Override public String       getAbbreviation() {return null;}                          // TODO
    @Override public Unit<?>      getUnit()         {return null;}                          // TODO
    @Override public double       getMinimumValue() {return Double.NEGATIVE_INFINITY;}
    @Override public double       getMaximumValue() {return Double.POSITIVE_INFINITY;}
    @Override public RangeMeaning getRangeMeaning() {return null;}
    @Override public String       toWKT()           {return "AXIS[\"" + getName() + "\", " + getDirection().name() + ']';}
}
