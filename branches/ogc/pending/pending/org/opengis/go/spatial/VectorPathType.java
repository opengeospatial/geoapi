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
 * This class serves to contain one static constant, VECTOR, indicating that
 * the in-between points of a path are points on the Euclidean shortest
 * distance line between the vertices.  This line should be computed in
 * whatever "real-world" space the points live.  So for example, if the
 * points are LatLonAlts, then the shortest distance line should be computed
 * in a coordinate system such as Earth Centered Earth Fixed (ECEF).
 * <P>
 * As an example of the use of VECTOR, consider two buildings some distance
 * apart, each with a person standing on top.  If one of the people were to
 * fire a laser at the other, the beam would follow a straight path that does
 * not bend with the surface of the earth.  This straight path is what is
 * meant by VECTOR.
 * 
 * @author Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public class VectorPathType extends PathType {
    /**
     * Creates a new VectorPathType with the given value and name.
     * @param value the int value for the enum.
     * @param name the short name for the enum.
     * @param description the description for the enum.
     */
    protected VectorPathType(int value, String name, String description) {
        super(value, name, description);
    }

    public static final VectorPathType VECTOR =
        new VectorPathType(0, "VECTOR",
        "The path that is the Euclidean shortest distance path.");

    /** Enumeration value of the <code>VECTOR<\code> constant. */
    public static final int VECTOR_VALUE = VECTOR.getValue();

    /**
     * A list containing all the enumerators so that the list can be
     * "walked" and also to do reverse lookups (id to object).
     */
    private static final VectorPathType[] enumList = {
        VECTOR
    };
    
    /**
     * Method to lookup an enumerator by value.
     * @param value The value to match the object for.
     * @throws NoSuchEnumerationException If there is no object for the
     *         given value.
     */
    public static VectorPathType getByValue(int value)
                    throws NoSuchEnumerationException {
        VectorPathType enum = null;
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
     * Gets the list of all VectorPathTypes. Useful when making comboboxes
     * like:
     * <code><pre>
     * JComboBox comboBox = new JComboBox(VectorPathType.getArray());
     * </pre></code><p>
     * <b>IMPORTANT SAFETY TIP:</b><br>
     * Modifying the array returned is a Bad Thing. Don't do it.
     */
    public static VectorPathType[] getArray() {
        return enumList;
    }
    
}
