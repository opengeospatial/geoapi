/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2008 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.display.canvas;

import org.opengis.geometry.DirectPosition;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;


/**
 * Instances of this interface (and its sub-interfaces) describe the current
 * state of a <code>Canvas</code>.  The information contained by instances
 * of this interface should only describe the viewing area or volume of the
 * Canvas and should not contain any state information regarding the data
 * contained within it.
 * <p>
 * When an instance of this class is returned from <code>Canvas</code>
 * methods, a "snapshot" of the current state of the Canvas is taken
 * and the values will never change (even if the Canvas changes state).
 *
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
public interface CanvasState {
    
    /**
     * Returns the title of the <code>Canvas</code>.
     * @return canvas title
     */
    String getTitle();

    /**
     * Returns the position of the center point of this Canvas.
     * 
     * @return DirectPosition : center point
     */
    DirectPosition getCenter();
    
    /**
     * Returns the Coordinate Reference System associated with the display of
     * this <code>Canvas</code>. The display Coordinate Reference System
     * corresponds to the geometry of the display device (e.g. video monitor =
     * Cartesian, planetarium = Spherical).
     *
     * @return the display Coordinate Reference System
     */
    CoordinateReferenceSystem getDisplayCRS();

    /**
     * Returns the objective Coordinate Reference System (e.g. the projection of
     * a georeferenced Coordinate Reference System) for this <code>Canvas</code>.
     * This is the default objective Coordinate Reference System for this
     * <code>Canvas</code>.
     *
     * @return the objective Coordinate Reference System
     */
    CoordinateReferenceSystem getObjectiveCRS();

    /**
     * Returns the coordinate transformation object for this
     * <code>Canvas</code>. This allows the <code>Canvas</code> to resolve
     * conversions of coordinates between the objective and display Coordinate Reference Systems.
     * 
     * @return the coordinate transformation object
     */
    MathTransform getObjectiveToDisplayTransform();

    /**
     * Returns the coordinate transformation object for this
     * <code>Canvas</code>. This allows the <code>Canvas</code> to resolve
     * conversions of coordinates between the display and objective Coordinate Reference Systems.
     * 
     * @return the coordinate transformation object
     */
    MathTransform getDisplayToObjectiveTransform();


}
