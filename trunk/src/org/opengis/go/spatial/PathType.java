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
import java.util.Collection;

// OpenGIS direct dependencies
import org.opengis.util.SimpleEnumerationType;


/**
 * Base class for objects that represent the various methods for computing a path between
 * two locations. Singleton instances of PathType will exist to represent for example
 * a path of constant bearing (rhumbline), or a great circle path.
 * Path type is an algorithmic sequence of interpolation and projection.
 * <ul>
 *   <li>For <b>rhumbline</b>, <b>great circle</b>, and <b>vector</b>, first
 *       <i>interpolation</i> is done on the verteces, which gives in-between points.
 *       These in-between points are then <i>projected</i> into the display space, 
 *       which converts them to display points.</li>
 *   <li>For <b>pixel-straight</b> and <b>spline</b>, the verteces are first <i>projected</i>
 *       into the display space as display points. These display points are <i>interpolated</i>,
 *       which generates in-between display points.</li>
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
 * <p>
 * 
 * @version 0.2
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 */
public abstract class PathType extends SimpleEnumerationType {
    /**
     * Creates a new PathType with the given value and name.
     *
     * @param values the list of values to add to.
     * @param name the short name for the enum.
     * @param description the description for the enum.
     */
    protected PathType(Collection values, String name, String description) {
        super(values, name, description);
    }
}
