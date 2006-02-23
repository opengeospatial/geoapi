/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
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
 * @version $Revision$, $Date$
 */
public class UnprojectedPathType extends PathType {

    /**
     * Creates a new UnprojectedPathType with the given value and name.
     * @param name the short name for the enum.
     * @param description the description for the enum.
     */
    protected UnprojectedPathType(String name, String description) {
        super(name, description);
    }
    
    public static final UnprojectedPathType PIXEL_STRAIGHT =
        new UnprojectedPathType("PIXEL_STRAIGHT",
            "The path that is drawn as screen-straight, regardless of any current projection.");
            
    public static final UnprojectedPathType CONTINUOUS_SPLINE =
        new UnprojectedPathType("CONTINUOUS_SPLINE",
            "The path that connects its points via a continuous (although not necessarily smooth) spline.");
    
    /** Enumeration value of the <code>PIXEL_STRAIGHT<\code> constant. */
   // public static final int PIXEL_STRAIGHT_VALUE = PIXEL_STRAIGHT.getValue();
    
    /** Enumeration value of the <code>CONTINUOUS_SPLINE</code> constant. */
   // public static final int CONTINUOUS_SPLINE_VALUE = CONTINUOUS_SPLINE.getValue();
    

}
