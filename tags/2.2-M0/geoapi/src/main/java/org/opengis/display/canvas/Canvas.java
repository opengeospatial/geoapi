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
package org.opengis.display.canvas;

import java.awt.RenderingHints;
import org.opengis.geometry.DirectPosition;


/**
 * <p><code>Canvas</code> defines a common abstraction for implementations that
 * manage the display. Canvas can be extended for cartesian display or any kind.
 * the canvas define the renderered Area by using different attributs.
 * </p>
 * A <code>Canvas</code> with an XY (Cartesian) display field should support
 * the following properties:
 * <ul>
 *   <li>pixelWidth</li>
 *   <li>pixelHeight</li>
 *   <li>center</li>
 *   <li>width</li>
 *   <li>scale</li>
 *   <li>envelope</li>
 * </ul>
 *
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
public interface Canvas {

    /**
     * Returns a copy of the current state of this <code>Canvas</code>. The
     * object returned will implement <code>CanvasState</code> or one of its
     * subinterfaces, depending on the type of Canvas.
     * 
     * @return immutable CanvasState
     */
    CanvasState getState();
    
    /**
     * Returns true if the given coordinate is visible on this
     * <code>Canvas</code>.
     * @param coordinate
     * @return true if the given coordinate is visible
     */
    boolean isVisible(DirectPosition coordinate);

    /**
     * Returns the controller object with all setters methods.
     * 
     * @return CanvasController
     */
    CanvasController getController();

    /**
     * Sets a rendering hint for implementation or platform specific rendering
     * information.
     *
     * @param hintKey the key of the hint.
     * @param hint the rendering hint.
     */
    void setHint(RenderingHints.Key hintKey, Object hint);

    /**
     * Returns the rendering hint associated with the hint name.
     *
     * @param hintKey the key of the hint.
     * @return the rendering hint.
     */
    Object getHint(RenderingHints.Key hintKey);

    /**
     * Add a <code>CanvasListener</code> on this canvas.
     * 
     * @param listener
     */
    void addCanvasListener(CanvasListener listener);

    /**
     * Remove <code>CanvasListener</code> listener.
     * 
     * @param listener
     */
    void removeCanvasListener(CanvasListener listener);
    
}
