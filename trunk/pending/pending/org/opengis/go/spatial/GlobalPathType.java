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

/**
 * The instances of this class represent methods of calculating a path
 * between two locations. The in-between points of the path satisfy two
 * conditions:
 * <OL>
 * <LI>The in-between points are the same regardless of the way the current
 *     path is displayed (i.e. the path is independent of map projection,
 *     current viewport, etc.)
 * <LI>The in-between points are claculated along a surface that the points
 *     are projected onto (such as the surface of the earth).
 * </OL>
 * The second condition implies that altitude is not taken into account
 * when calculating paths of type GlobalPathType.  Hence paths of this type
 * are well suited for navigation of surface ships or vehicles.\
 * 
 * @author Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public class GlobalPathType extends PathType {
    /**
     * Creates a new GlobalPathType with the given value and name.
     * @param value the int value for the enum.
     * @param name the short name for the enum.
     * @param description the description for the enum.
     */
    protected GlobalPathType(int value, String name, String description) {
        super(value, name, description);
    }

    public static final GlobalPathType GREAT_CIRCLE_ELLIPSOIDAL =
        new GlobalPathType(0, "GREAT_CIRCLE_ELLIPSOIDAL",
        "The path that is the shortest distance path over the WGS84 ellipsoid.");

    public static final GlobalPathType GREAT_CIRCLE_SPHERICAL =
        new GlobalPathType(1, "GREAT_CIRCLE_SPHERICAL",
        "The path that is the shortest distance path over the sphere whose radius is the equatorial radius of the WGS84 ellipsoid.");

    public static final GlobalPathType RHUMBLINE_ELLIPSOIDAL =
        new GlobalPathType(2, "RHUMBLINE_ELLIPSOIDAL",
        "The path that is the path of constant bearing over the WGS84 ellipsoid.");

    public static final GlobalPathType RHUMBLINE_SPHERICAL =
        new GlobalPathType(3, "RHUMBLINE_SPHERICAL",
        "The path that is the path of constant bearing over the sphere whose radius is the equatorial radius of the WGS84 ellipsoid.");

    /**
     * Enumeration value of the <code>GREAT_CIRCLE_ELLIPSOIDAL</code>
     * constant.
     */
    public static final int GREAT_CIRCLE_ELLIPSOIDAL_VALUE =
        GREAT_CIRCLE_ELLIPSOIDAL.getValue();

    /**
     * Enumeration value of the <code>GREAT_CIRCLE_SPHERICAL</code>
     * constant.
     */
    public static final int GREAT_CIRCLE_SPHERICAL_VALUE =
        GREAT_CIRCLE_SPHERICAL.getValue();

    /**
     * Enumeration value of the <code>RHUMBLINE_ELLIPSOIDAL</code>
     * constant.
     */
    public static final int RHUMBLINE_ELLIPSOIDAL_VALUE =
        RHUMBLINE_ELLIPSOIDAL.getValue();

    /**
     * Enumeration value of the <code>RHUMBLINE_SPHERICAL</code>
     * constant.
     */
    public static final int RHUMBLINE_SPHERICAL_VALUE =
        RHUMBLINE_SPHERICAL.getValue();

    /**
     * A list containing all the enumerators so that the list can be
     * "walked" and also to do reverse lookups (id to object).
     */
    private static final GlobalPathType[] enumList = {
        GREAT_CIRCLE_ELLIPSOIDAL,
        GREAT_CIRCLE_SPHERICAL,
        RHUMBLINE_ELLIPSOIDAL,
        RHUMBLINE_SPHERICAL
    };
    
    /**
     * Method to lookup an enumerator by value.
     * @param value The value to match the object for.
     * @throws NoSuchEnumerationException If there is no object for the
     *         given value.
     */
    public static GlobalPathType getByValue(int value)
                    throws NoSuchEnumerationException {
        GlobalPathType enum = null;
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
     * Gets the list of all GlobalPathTypes. Useful when making comboboxes
     * like:
     * <code><pre>
     * JComboBox comboBox = new JComboBox(GlobalPathType.getArray());
     * </pre></code><p>
     * <b>IMPORTANT SAFETY TIP:</b><br>
     * Modifying the array returned is a Bad Thing. Don't do it.
     */
    public static GlobalPathType[] getArray() {
        return enumList;
    }
}
