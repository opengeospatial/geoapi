/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/

package org.opengis.go.spatial;

import org.opengis.go.util.NoSuchEnumerationException;
import org.opengis.go.util.SimpleEnumerationType;

/**
 * This class serves to contain two constants: 
 * 
 * <ol>
 *     <li>PIXEL_STRAIGHT, that indicates
 * that a path between two points should be drawn on the screen 
 * as a straight line, regardless of the location or orientation 
 * of the vertices.</li>
 *     <li>CONTINUOUS_SPLINE, that indicates a spline is to be 
 * used to connect points along a path.</li>
 * </ol>
 * These should be used in situations where accuracy is unimportant or the
 * appearance of the path is intended to remain the same, regardless of the
 * current viewport of the map.  Note that this applies to both
 * two dimensional and three dimensional displays; in a three dimensional
 * display, the path may cross other objects, but will always appear straight.
 * 
 * @author Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public class UnprojectedPathType extends PathType {
    /**
     * Creates a new UnprojectedPathType with the given value and name.
     * @param value the int value for the enum.
     * @param name the short name for the enum.
     * @param description the description for the enum.
     */
    protected UnprojectedPathType(int value, String name, String description) {
        super(value, name, description);
    }

    public static final UnprojectedPathType PIXEL_STRAIGHT =
        new UnprojectedPathType(0, "PIXEL_STRAIGHT",
        "The path that is drawn as screen-straight, regardless of any current projection.");

    public static final UnprojectedPathType CONTINUOUS_SPLINE = 
        new UnprojectedPathType(1, "CONTINUOUS_SPLINE",
        "The path that connects its points via a continuous (although not necessarily smooth) spline.");

    /** Enumeration value of the <code>PIXEL_STRAIGHT<\code> constant. */
    public static final int PIXEL_STRAIGHT_VALUE = PIXEL_STRAIGHT.getValue();
    
    /** Enumeration value of the <code>CONTINUOUS_SPLINE</code> constant. */
    public static final int CONTINUOUS_SPLINE_VALUE = CONTINUOUS_SPLINE.getValue();
    
    /**
     * A list containing all the enumerators so that the list can be
     * "walked" and also to do reverse lookups (id to object).
     */
    private static final UnprojectedPathType[] enumList = {
        PIXEL_STRAIGHT,
        CONTINUOUS_SPLINE
    };
    
    /**
     * Method to lookup an enumerator by value.
     * @param value The value to match the object for.
     * @throws NoSuchEnumerationException If there is no object for the
     *         given value.
     */
    public SimpleEnumerationType getByValue(int value)
                    throws NoSuchEnumerationException {
        UnprojectedPathType enum = null;
        for (int i = 0; i < enumList.length && enum == null; i++) {
            if (enumList[i].getValue() == value) {
                enum = enumList[i];
            }
        }

        if (enum == null) {
            throw new NoSuchEnumerationException(value);
        }
        return enum;
    }

    /**
     * Gets the list of all UnprojectedPathTypes. Useful when making comboboxes
     * like:
     * <code><pre>
     * JComboBox comboBox = new JComboBox(UnprojectedPathType.getArray());
     * </pre></code><p>
     * <b>IMPORTANT SAFETY TIP:</b><br>
     * Modifying the array returned is a Bad Thing. Don't do it.
     */
    public static UnprojectedPathType[] getArray() {
        return enumList;
    }

}
