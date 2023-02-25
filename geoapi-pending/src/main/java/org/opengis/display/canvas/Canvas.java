/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2023 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
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
