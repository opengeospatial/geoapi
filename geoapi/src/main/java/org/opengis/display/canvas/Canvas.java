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
 * Defines a common abstraction for implementations that manage the display. Canvas can be extended
 * for cartesian display or any kind. A {@code Canvas} with an XY (Cartesian) display field should
 * support the following properties:
 * <p>
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
     * Returns a copy of the current state of this {@code Canvas}. The object returned will
     * implement <code>CanvasState</code> or one of its subinterfaces, depending on the type
     * of canvas.
     *
     * @return A snapshot of current canvas state.
     */
    CanvasState getState();

    /**
     * Returns {@code true} if the given coordinate is visible on this {@code Canvas}.
     *
     * @param coordinate The coordinate to test for visibility.
     * @return {@code true} if the given coordinate is visible on this canvas.
     */
    boolean isVisible(DirectPosition coordinate);

    /**
     * Returns the controller that allows the programmer to modify the
     * {@code Canvas}'s properties.
     *
     * @return The controller for this canvas.
     */
    CanvasController getController();

    /**
     * Sets a rendering hint for implementation or platform specific rendering
     * information.
     *
     * @param hintKey The key of the hint.
     * @param hint The rendering hint.
     */
    void setRenderingHint(RenderingHints.Key hintKey, Object hint);

    /**
     * Returns the rendering hint associated with the hint name.
     *
     * @param hintKey the key of the hint.
     * @return the rendering hint.
     */
    Object getRenderingHint(RenderingHints.Key hintKey);

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
