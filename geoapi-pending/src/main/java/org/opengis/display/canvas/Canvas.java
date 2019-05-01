/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2019 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
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
 * @since  GeoAPI 2.2
 */
public interface Canvas {
    /**
     * Returns a copy of the current state of this {@code Canvas}. The object returned will
     * implement {@link CanvasState} or one of its subinterfaces, depending on the type
     * of canvas.
     *
     * @return a snapshot of current canvas state.
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
     * @return the controller for this canvas.
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
     * @param hintKey The key of the hint.
     * @return the rendering hint.
     */
    Object getRenderingHint(RenderingHints.Key hintKey);

    /**
     * Adds the given listener that will be notified when the state of this
     * {@code Canvas} has changed.
     *
     * @param listener The listener to add.
     */
    void addCanvasListener(CanvasListener listener);

    /**
     * Removes the given listener.
     *
     * @param listener The listener to remove.
     */
    void removeCanvasListener(CanvasListener listener);
}
