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
 * This class serves to contain two constants: 
 * <ol>
 *   <li>
 *     PIXEL_STRAIGHT, that indicates that a path between two points should
 *     be drawn on the screen as a straight line, regardless of the location 
 *     or orientation of the vertices.
 *   </li>
 *   <li>
 *     CONTINUOUS_SPLINE, that indicates a spline is to be used to connect 
 *     points along a path.
 *   </li>
 * </ol>
 * These should be used in situations where accuracy is unimportant or the
 * appearance of the path is intended to remain the same, regardless of the
 * current viewport of the map.  Note that this applies to both
 * two dimensional and three dimensional displays; in a three dimensional
 * display, the path may cross other objects, but will always appear straight.
 * 
 * @author Open GIS Consortium, Inc.
 * @version $Revision: 658 $, $Date: 2006-02-23 12:09:34 +1100 (jeu., 23 févr. 2006) $
 */
public final class UnprojectedPathType extends PathType {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 5983938773013515674L;

    /**
     * Number of enum of this type created up to date.
     */
    private static int count = 0;

    /**
     * The path that is drawn as screen-straight, regardless of any current projection.
     */
    public static final UnprojectedPathType PIXEL_STRAIGHT =
        new UnprojectedPathType("PIXEL_STRAIGHT",
            "The path that is drawn as screen-straight, regardless of any current projection.");

    /**
     * The path that connects its points via a continuous (although not necessarily smooth) spline.
     */
    public static final UnprojectedPathType CONTINUOUS_SPLINE =
        new UnprojectedPathType("CONTINUOUS_SPLINE",
            "The path that connects its points via a continuous (although not necessarily smooth) spline.");

    /**
     * Creates a new {@code UnprojectedPathType} with the given name.
     *
     * @param name the short name for the enum.
     * @param description the description for the enum.
     */
    public UnprojectedPathType(String name, String description) {
        super(name, description);
        synchronized (VALUES) {
            count++;
        }
    }

    /**
     * Returns the list of {@code UnprojectedPathType}s.
     */
    public static /*{UnprojectedPathType}*/ PathType[] values() {
        synchronized (VALUES) {
            return values(UnprojectedPathType.class, count);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    @Override
    public /*{UnprojectedPathType}*/ org.opengis.util.CodeList[] family() {
        return values();
    }
}
