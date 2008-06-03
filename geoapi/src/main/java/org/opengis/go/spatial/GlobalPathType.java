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
 * The instances of this class represent methods of calculating a path
 * between two locations. The in-between points of the path satisfy two
 * conditions:
 * <OL>
 * <LI>The in-between points are the same regardless of the way the current
 *     path is displayed (i.e. the path is independent of map projection,
 *     current viewport, etc.)</LI>
 * <LI>The in-between points are claculated along a surface that the points
 *     are projected onto (such as the surface of the earth).</LI>
 * </OL>
 * The second condition implies that altitude is not taken into account
 * when calculating paths of type GlobalPathType.  Hence paths of this type
 * are well suited for navigation of surface ships or vehicles.
 *
 * @author Open GIS Consortium, Inc.
 */
public final class GlobalPathType extends PathType {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -3807198607057483081L;

    /**
     * Number of enum of this type created up to date.
     */
    private static int count = 0;

    /**
     * The path that is the shortest distance path over the WGS84 ellipsoid.
     */
    public static final GlobalPathType GREAT_CIRCLE_ELLIPSOIDAL =
        new GlobalPathType("GREAT_CIRCLE_ELLIPSOIDAL",
            "The path that is the shortest distance path over the WGS84 ellipsoid.");

    /**
     * The path that is the shortest distance path over the sphere whose radius is the
     * equatorial radius of the WGS84 ellipsoid.
     */
    public static final GlobalPathType GREAT_CIRCLE_SPHERICAL =
        new GlobalPathType("GREAT_CIRCLE_SPHERICAL",
            "The path that is the shortest distance path over the sphere whose radius is the equatorial radius of the WGS84 ellipsoid.");

    /**
     * The path that is the path of constant bearing over the WGS84 ellipsoid.
     */
    public static final GlobalPathType RHUMBLINE_ELLIPSOIDAL =
        new GlobalPathType("RHUMBLINE_ELLIPSOIDAL",
            "The path that is the path of constant bearing over the WGS84 ellipsoid.");

    /**
     * The path that is the path of constant bearing over the sphere whose radius is the
     * equatorial radius of the WGS84 ellipsoid.
     */
    public static final GlobalPathType RHUMBLINE_SPHERICAL =
        new GlobalPathType("RHUMBLINE_SPHERICAL",
            "The path that is the path of constant bearing over the sphere whose radius is the equatorial radius of the WGS84 ellipsoid.");

    /**
     * Creates a new {@code GlobalPathType} with the given name.
     *
     * @param name the short name for the enum.
     * @param description the description for the enum.
     */
    public GlobalPathType(String name, String description) {
        super(name, description);
        synchronized (VALUES) {
            count++;
        }
    }

    /**
     * Returns the list of {@code GlobalPathType}s.
     *
     * @return The list of codes declared in the current JVM.
     */
    public static GlobalPathType[] values() {
        synchronized (VALUES) {
            return values(GlobalPathType.class, count);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    @Override
    public GlobalPathType[] family() {
        return values();
    }
}
