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

// J2SE direct dependencies
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;

// OpenGIS direct dependencies
import org.opengis.util.NoSuchEnumerationException;


/**
 * Methods of calculating a path between two locations.
 * The in-between points of the path satisfy two
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
 * @version 0.2
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 *
 * @revisit Localize descriptions.
 */
public class GlobalPathType extends PathType {
    /**
     * The list of enumeration available in this virtual machine.
     * <strong>Must be declared first!</strong>.
     */
    private static final List mutableValues = new ArrayList();

    /**
     * An immutable view of {@link #mutableValues} to be returned by {@link #values()}.
     */
    private static final List values = Collections.unmodifiableList(mutableValues);

    /**
     * The path that is the shortest distance path over the WGS84 ellipsoid.
     */
    public static final GlobalPathType GREAT_CIRCLE_ELLIPSOIDAL =
        new GlobalPathType("GREAT_CIRCLE_ELLIPSOIDAL",
        "The path that is the shortest distance path over the WGS84 ellipsoid.");

    /**
     * The path that is the shortest distance path over the sphere whose radius
     * is the equatorial radius of the WGS84 ellipsoid.
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
     * Creates a new <code>GlobalPathType</code> with the given name.
     *
     * @param name the short name for the enum.
     * @param description the description for the enum.
     */
    protected GlobalPathType(String name, String description) {
        super(mutableValues, name, description);
    }

    /**
     * Lookup an enumerator by value.
     *
     * @param value The value to match the object for.
     * @throws NoSuchEnumerationException If there is no object for the given value.
     */
    public static GlobalPathType valueOf(int value) throws NoSuchEnumerationException {
        for (final Iterator it=values.iterator(); it.hasNext();) {
            final GlobalPathType e = (GlobalPathType) it.next();
            if (e.ordinal() == value) {
                return e;
            }
        }
        throw new NoSuchEnumerationException(value);
    }

    /**
     * Returns the list of <code>GlobalPathType</code>s.
     * Useful when making comboboxes like:
     * <pre>
     * JComboBox comboBox = new JComboBox(GlobalPathType.values().toArray());
     * </pre>
     */
    public static List values() {
        return values;
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public List family() {
        return values;
    }
}
