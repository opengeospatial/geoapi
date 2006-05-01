/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2001 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.referencing.cs;

// J2SE directdependencies
import java.util.List;
import java.util.ArrayList;

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
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List VALUES = new ArrayList(13);
    
    // NOTE: The following enum values are from the OpenGIS specification.
    //       IF THOSE VALUES CHANGE, THEN inverse() AND absolute() MUST BE
    //       UPDATED.

    /**
     * Unknown or unspecified axis orientation.
     * This can be used for
     * {@linkplain org.opengis.referencing.crs.EngineeringCRS engineering} or
     * {@linkplain org.opengis.referencing.crs.DerivedCRS derived}
     * coordinate reference systems.
     *
     * @UML conditional CS_AO_OTHER
     */
    public static final AxisDirection OTHER = new AxisDirection("OTHER"); // 0

    /**
     * Increasing ordinates values go North.
     * This is usually used for Grid Y coordinates and Latitude.
     *
     * @UML conditional CS_AO_NORTH
     */
    public static final AxisDirection NORTH = new AxisDirection("NORTH"); // 1

    /**
     * Increasing ordinates values go South.
     * This is rarely used.
     *
     * @UML conditional CS_AO_SOUTH
     */
    public static final AxisDirection SOUTH = new AxisDirection("SOUTH"); // 2

    /**
     * Increasing ordinates values go East.
     * This is usually used for Grid X coordinates and Longitude.
     *
     * @UML conditional CS_AO_EAST
     */
    public static final AxisDirection EAST = new AxisDirection("EAST"); // 3

    /**
     * Increasing ordinates values go West.
     * This is usually used for Grid X coordinates and Longitude.
     *
     * @UML conditional CS_AO_WEST
     */
    public static final AxisDirection WEST = new AxisDirection("WEST"); // 4

    /**
     * Increasing ordinates values go up, usually on a vertical axe.
     * This is used for {@linkplain org.opengis.referencing.crs.VerticalCRS vertical}
     * coordinate reference systems.
     *
     * @UML conditional CS_AO_UP
     */
    public static final AxisDirection UP = new AxisDirection("UP"); // 5

    /**
     * Increasing ordinates values go down, usually on a vertical axe.
     * This is used for {@linkplain org.opengis.referencing.crs.VerticalCRS vertical}
     * coordinate reference systems.
     *
     * @UML conditional CS_AO_DOWN
     */
    public static final AxisDirection DOWN = new AxisDirection("DOWN"); // 6

    /**
     * Increasing ordinates values go right, usually on a horizontal plane.
     * This is used for rendering coordinate reference systems.
     */
    public static final AxisDirection RIGHT = new AxisDirection("RIGHT"); // 7

    /**
     * Increasing ordinates values go left, usually on a horizontal plane.
     * This is used for rendering coordinate reference systems.
     */
    public static final AxisDirection LEFT = new AxisDirection("LEFT"); // 8

    /**
     * Increasing ordinates values go top, usually on a horizontal plane.
     * This is used for rendering coordinate reference systems.
     */
    public static final AxisDirection TOP = new AxisDirection("TOP"); // 9

    /**
     * Increasing ordinates values go bottom, usually on a horizontal plane.
     * This is used for rendering coordinate reference systems.
     */
    public static final AxisDirection BOTTOM = new AxisDirection("BOTTOM"); // 10

    /**
     * Increasing ordinates values go future.
     * This is used for {@linkplain org.opengis.referencing.crs.TemporalCRS temporal}
     * coordinate reference systems.
     */
    public static final AxisDirection FUTURE = new AxisDirection("FUTURE"); // 11

    /**
     * Increasing ordinates values go past.
     * This is used for {@linkplain org.opengis.referencing.crs.TemporalCRS temporal}
     * coordinate reference systems.
     */
    public static final AxisDirection PAST = new AxisDirection("PAST"); // 12

    /**
     * A copy of {@link #VALUES} protected from change.
     */
    private static final AxisDirection[] STANDARD_VALUES = values();

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    public AxisDirection(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of <code>AxisDirection</code>s.
     */
    public static AxisDirection[] values() {
        synchronized (VALUES) {
            return (AxisDirection[]) VALUES.toArray(new AxisDirection[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{AxisDirection}*/ CodeList[] family() {
        return values();
    }

    /**
     * Returns the opposite direction of this axis. The opposite direction of
     * {@linkplain #NORTH North} is {@linkplain #SOUTH South}, and the opposite
     * direction of {@linkplain #SOUTH South} is {@linkplain #NORTH North}. The
     * same applies to {@linkplain #EAST East}-{@linkplain #WEST West},
     * {@linkplain #UP Up}-{@linkplain #DOWN Down} and
     * {@linkplain #FUTURE Future}-{@linkplain #PAST Past}.
     * {@linkplain #OTHER Other} axis directions are returned unchanged.
     */
    public AxisDirection inverse() {
        final int value = ordinal()-1;
        if (value>=0 && value<STANDARD_VALUES.length-1) {
            return STANDARD_VALUES[(value ^ 1)+1];
        } else {
            return this;
        }
    }

    /**
     * Returns the "absolute" direction of this axis.
     * This "absolute" operation is similar to the <code>Math.abs(int)</code>
     * method in that "negative" directions ({@link #SOUTH}, {@link #WEST},
     * {@link #DOWN}, {@link #PAST} are changed for their "positive" counterparts
     * ({@link #NORTH}, {@link #EAST}, {@link #UP}, {@link #FUTURE})
     * More specifically, the following conversion table is applied.
     * <br>&nbsp;
     * <table align="center" cellpadding="3" border="1" bgcolor="F4F8FF">
     *   <tr bgcolor="#B9DCFF">
     *     <th>&nbsp;&nbsp;Direction&nbsp;&nbsp;</th>
     *     <th>&nbsp;&nbsp;Absolute value&nbsp;&nbsp;</th>
     *   </tr>
     *   <tr align="center"><td>NORTH</td> <td>NORTH</td> </tr>
     *   <tr align="center"><td>SOUTH</td> <td>NORTH</td> </tr>
     *   <tr align="center"><td>EAST</td>  <td>EAST</td>  </tr>
     *   <tr align="center"><td>WEST</td>  <td>EAST</td>  </tr>
     *   <tr align="center"><td>UP</td>    <td>UP</td>    </tr>
     *   <tr align="center"><td>DOWN</td>  <td>UP</td>    </tr>
     *   <tr align="center"><td>RIGHT</td> <td>RIGHT</td> </tr>
     *   <tr align="center"><td>LEFT</td>  <td>RIGHT</td> </tr>
     *   <tr align="center"><td>TOP</td>   <td>TOP</td>   </tr>
     *   <tr align="center"><td>BOTTOM</td><td>TOP</td>   </tr>
     *   <tr align="center"><td>FUTURE</td><td>FUTURE</td></tr>
     *   <tr align="center"><td>PAST</td>  <td>FUTURE</td></tr>
     *   <tr align="center"><td>OTHER</td> <td>OTHER</td> </tr>
     * </table>
     */
    public AxisDirection absolute() {
        final int value = ordinal()-1;
        if (value>=0 && value<STANDARD_VALUES.length-1) {
            return STANDARD_VALUES[(value & ~1)+1];
        } else {
            return this;
        }
    }
}
