/**************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.go.spatial;


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
 * @version $Revision: 658 $, $Date: 2006-02-23 12:09:34 +1100 (jeu., 23 févr. 2006) $
 */
public final class VectorPathType extends PathType {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -5126616538488631415L;

    /**
     * Number of enum of this type created up to date.
     */
    private static int count = 0;

    /**
     * The path that is the Euclidean shortest distance path.
     */
    public static final VectorPathType VECTOR =
        new VectorPathType("VECTOR", "The path that is the Euclidean shortest distance path.");

    /**
     * Creates a new {@code VectorPathType} with the given name.
     *
     * @param name the short name for the enum.
     * @param description the description for the enum.
     */
    public VectorPathType(String name, String description) {
        super(name, description);
        synchronized (VALUES) {
            count++;
        }
    }

    /**
     * Returns the list of {@code VectorPathType}s.
     */
    public static /*{VectorPathType}*/ PathType[] values() {
        synchronized (VALUES) {
            return values(VectorPathType.class, count);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    @Override
    public /*{VectorPathType}*/ org.opengis.util.CodeList[] family() {
        return values();
    }
}
