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
    public CanvasState getState();
    
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
    public CanvasController getController();

    /**
     * Sets a rendering hint for implementation or platform specific rendering
     * information.
     *
     * @param hintName the name of the hint.
     * @param hint the rendering hint.
     */
    public void setHint(String hintName, Object hint);

    /**
     * Returns the rendering hint associated with the hint name.
     *
     * @param hintName the name of the hint.
     * @return the rendering hint.
     */
    public Object getHint(String hintName);

    /**
     * Add a <code>CanvasListener</code> on this canvas.
     * 
     * @param listener
     */
    public void addCanvasListener(CanvasListener listener);

    /**
     * Remove <code>CanvasListener</code> listener.
     * 
     * @param listener
     */
    public void removeCanvasListener(CanvasListener listener);
    
}
