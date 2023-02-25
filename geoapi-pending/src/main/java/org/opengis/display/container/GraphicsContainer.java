/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2009-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.display.container;

import java.util.Collection;
import org.opengis.display.canvas.Canvas;
import org.opengis.display.primitive.Graphic;
import org.opengis.geometry.Envelope;


/**
 * Holds a collection of {@linkplain Graphic graphics} to be drawn in a {@linkplain Canvas canvas}.
 * The GraphicsContainer implementation typically depends on the canvas implementation. For example, an AWT
 * canvas may be associated to a GraphicsContainer using a {@link java.awt.Graphics2D} handler for drawing,
 * while a SWT canvas may be associated to another GraphicsContainer implementation using a different
 * drawing toolkit.
 * <p>
 * Graphics can be {@linkplain Collection#add added} or {@linkplain Collection#remove removed} with
 * method invocations on the collection returned by {@link #graphics}, which is a "live" collection.
 * Note that a GraphicsContainer instance may restrict the acceptable graphic implementations.
 *
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since  GeoAPI 2.2
 */
public interface GraphicsContainer<G extends Graphic> {

    /**
     * Returns the canvas where this GraphicsContainer will drawn the {@linkplain Graphic graphics}.
     *
     * @return the canvas where to drawn.
     */
    Canvas getCanvas();

    Envelope getGraphicsEnvelope();

    /**
     * Returns the collection of all graphics. Changes to this collection (addition,
     * removal) are reflected into the set of graphics to be rendered. Note that the returned
     * collection must notifies the {@linkplain ContainerListener GraphicsContainer listener} about any
     * addition or removal.
     *
     * <p>When new graphics are {@linkplain Collection#add added}, implementations shall respect
     * the <var>z</var>-order. When two
     * added graphics have the same <var>z</var>-order, the most recently added one should be
     * on top.</p>
     *
     * @return Collection of all graphics, as a live collection.
     */
    Collection<G> graphics();

    /**
     * Adds a listener to be notified when a graphic is added or removed.
     *
     * @param listener The listener to add.
     */
    void addContainerListener(ContainerListener listener);

    /**
     * Removes a listener.
     *
     * @param listener The listener to remove.
     */
    void removeContainerListener(ContainerListener listener);

    /**
     * Provides a hint that a GraphicsContainer is no longer needed. Implementations may use this method to
     * release resources, if needed. Implementations may also implement this method to return the
     * GraphicsContainer to a GraphicsContainer pool.
     * <p>
     * It is an error to reference a {@link GraphicsContainer} in any way after its dispose method has been
     * called.
     */
    void dispose();
}
