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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import org.opengis.util.CodeList;
import org.opengis.util.SimpleEnumerationType;


/**
 * This class serves as the base class for objects that represent the
 * various methods for computing a path between two locations.
 * Singleton instances of PathType will exist to represent for example
 * a path of constant bearing (rhumbline), or a great circle path.
 * <p>
 * Path type is an algorithmic sequence of interpolation and projection.
 * <ul>
 * <li>For <b>rhumbline</b>, <b>great circle</b>, and <b>vector</b>, first
 * <i>interpolation</i> is done on the verteces, which gives in-between points.
 * These in-between points are then <i>projected</i> into the display space,
 * which converts them to display points.</li>
 * <li>For <b>pixel-straight</b> and <b>spline</b>, the verteces are first <i>projected</i>
 * into the display space as display points. These display points are <i>interpolated</i>,
 * which generates in-between display points.</li>
 * </ul>
 * For each path type, an implementations will iteratively apply the respective algorithms
 * until the appropriate display resolution is reached.
 * <p>
 * <center>
 * <table border=1>
 * <b> <tr><td>Path Type</td> <td>Interpolation Method</td> </tr></b>
 * <tr><td>rhumbline</td> <td>constant bearing</td></tr>
 * <tr><td>great circle</td> <td>geodesic</td></tr>
 * <tr><td>vector</td> <td>linear in worldspace (interpolation before projection)</td></tr>
 * <tr><td>pixel-straight</td> <td>linear in display space (interpolation after projection)</td></tr>
 * <tr><td>spline</td> <td>cubic in display space (interpolation after projection)</td></tr>
 * </table>
 * </center>
 *
 * @author Open GIS Consortium, Inc.
 */
public class PathType extends SimpleEnumerationType<PathType> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -5930548969548155096L;

    /**
     * The list of enumeration available in this virtual machine.
     * <strong>Must be declared first!</strong>.
     * <p>
     * This field is not private because it is used as synchronization lock by
     * implementation of {@code values()} method in subclasses only. The content
     * should not be modified by any class in this package.
     */
    static final List<PathType> VALUES = new ArrayList<PathType>(7);

    /**
     * Creates a new {@code PathType} with the given name.
     *
     * @param name the short name for the enum.
     * @param description the description for the enum.
     */
    protected PathType(String name, String description) {
        super(VALUES, name, description);
    }

    /**
     * Returns the list of {@code PathType}s.
     *
     * @return The list of codes declared in the current JVM.
     */
    public static PathType[] values() {
        synchronized (VALUES) {
            return VALUES.toArray(new PathType[VALUES.size()]);
        }
    }

    /**
     * Returns the list of {@code PathType}s of the specified subclasses.
     * For implementation of {@code values()} method in subclasses only.
     */
    @SuppressWarnings("unchecked")
    static <T extends PathType> T[] values(final Class<T> type, final int count) {
        assert Thread.holdsLock(VALUES);
        final T[] codes = (T[]) Array.newInstance(type, count);
        int index = 0;
        for (final PathType candidate : VALUES) {
            if (type.isInstance(candidate)) {
                codes[index++] = (T) candidate;
            }
        }
        assert index == codes.length;
        return codes;
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public PathType[] family() {
        return values();
    }
}
