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
import org.opengis.util.InternationalString;


/**
 * The <code>CanvasController</code> holds all Setters
 * for the Canvas. Thoses methods are separate from the
 * getters (in the <code>CanvaState</code>) because a state
 * is an immutable object.
 *
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
public interface CanvasController {
        
    /**
     * Sets the canvas title.
     * 
     * @param title
     */
    void setTitle(InternationalString title);
    
    /**
     * Sets the objective Coordinate Reference System (e.g. the projection of a
     * georeferenced Coordinate Reference System) for this <code>Canvas</code>.
     * This is the default objective Coordinate Reference System for this
     * <code>Canvas</code>.
     *
     * @param crs the objective Coordinate Reference System
     */
    void setObjectiveCRS(CoordinateReferenceSystem crs);
    
    /**
     * Set the center point of the Canvas.
     * 
     * @param newCenter
     */
    void setCenter(DirectPosition newCenter);
    
}
