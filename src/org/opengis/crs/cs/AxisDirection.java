/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2001 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.crs.cs;

// OpenGIS direct dependencies
import org.opengis.util.CodeList;


/**
 * Orientation of axis. 
 * Some coordinate systems use non-standard orientations.  For example,
 * the first axis in South African grids usually points West, instead of
 * East. This information is obviously relevant for algorithms converting
 * South African grid coordinates into Lat/Long.
 *
 * @UML codelist CS_AxisOrientationEnum
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-009.pdf">Implementation specification 1.0</A>
 */
public final class AxisDirection extends CodeList {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -4405275475770755714L;

    /**
     * Unknown or unspecified axis orientation.
     * This can be used for
     * {@linkplain org.opengis.crs.crs.EngineeringCRS engineering} or
     * {@linkplain org.opengis.crs.crs.DerivedCRS derived}
     * coordinate reference systems.
     *
     * @UML conditional CS_AO_OTHER
     */
    public static final AxisDirection OTHER = new AxisDirection("OTHER", 0);

    /**
     * Increasing ordinates values go North.
     * This is usually used for Grid Y coordinates and Latitude.
     *
     * @UML conditional CS_AO_NORTH
     */
    public static final AxisDirection NORTH = new AxisDirection("NORTH", 1);

    /**
     * Increasing ordinates values go South.
     * This is rarely used.
     *
     * @UML conditional CS_AO_SOUTH
     */
    public static final AxisDirection SOUTH = new AxisDirection("SOUTH", 2);

    /**
     * Increasing ordinates values go East.
     * This is usually used for Grid X coordinates and Longitude.
     *
     * @UML conditional CS_AO_EAST
     */
    public static final AxisDirection EAST = new AxisDirection("EAST", 3);

    /**
     * Increasing ordinates values go West.
     * This is usually used for Grid X coordinates and Longitude.
     *
     * @UML conditional CS_AO_WEST
     */
    public static final AxisDirection WEST = new AxisDirection("WEST", 4);

    /**
     * Increasing ordinates values go up.
     * This is used for {@linkplain org.opengis.crs.crs.VerticalCRS vertical}
     * coordinate reference systems.
     *
     * @UML conditional CS_AO_UP
     */
    public static final AxisDirection UP = new AxisDirection("UP", 5);

    /**
     * Increasing ordinates values go down.
     * This is used for {@linkplain org.opengis.crs.crs.VerticalCRS vertical}
     * coordinate reference systems.
     *
     * @UML conditional CS_AO_DOWN
     */
    public static final AxisDirection DOWN = new AxisDirection("DOWN", 6);

    /**
     * Increasing ordinates values go future.
     * This is used for {@linkplain org.opengis.crs.crs.TemporalCRS temporal}
     * coordinate reference systems.
     */
    public static final AxisDirection FUTURE = new AxisDirection("FUTURE", 7);

    /**
     * Increasing ordinates values go past.
     * This is used for {@linkplain org.opengis.crs.crs.TemporalCRS temporal}
     * coordinate reference systems.
     */
    public static final AxisDirection PAST = new AxisDirection("PAST", 8);

    /**
     * List of all enumeration of this type.
     */
    private static final AxisDirection[] VALUES = new AxisDirection[] {
            OTHER, NORTH, SOUTH, EAST, WEST, UP, DOWN, FUTURE, PAST };

    /**
     * Constructs an enum with the given name.
     */
    private AxisDirection(final String name, final int ordinal) {
        super(name, ordinal);
    }

    /**
     * Returns the list of <code>AxisDirection</code>s.
     */
    public static AxisDirection[] values() {
        return (AxisDirection[]) VALUES.clone();
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{AxisDirection}*/ CodeList[] family() {
        return values();
    }
}
