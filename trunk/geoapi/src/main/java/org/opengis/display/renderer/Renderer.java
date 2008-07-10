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
package org.opengis.display.renderer;

import java.util.Collection;
import java.awt.RenderingHints;
import org.opengis.display.canvas.Canvas;
import org.opengis.display.primitive.Graphic;


/**
 * Holds a collection of {@linkplain Graphic graphics} to be drawn in a {@linkplain Canvas canvas}.
 * The renderer implementation typically depends on the canvas implementation. For example an AWT
 * canvas may be associated to a renderer using a {@link java.awt.Graphics2D} handler for drawing,
 * while a SWT canvas may be associated to an other renderer implementation using a different
 * drawing toolkit.
 * <p>
 * Graphics can be {@linkplain Collection#add added} or {@linkplain Collection#remove removed} with
 * method invocations on the collection returned by {@link #graphics}, which is a "live" collection.
 * Note that a renderer instance may restrict the acceptable graphic implementations.
 *
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since  GeoAPI 2.2
 */
public interface Renderer {

    /**
     * Returns the canvas where this renderer will drawn the {@linkplain Graphic graphics}.
     *
     * @return The canvas where to drawn.
     */
    Canvas getCanvas();

    /**
     * Returns the collection of all graphics to render. Changes to this collection (addition,
     * removal) are reflected into the set of graphics to be rendered. Note that the returned
     * collection must notifies the {@linkplain RendererListener renderer listener} about any
     * addition or removal.
     * <p>
     * When new graphics are {@linkplain Collection#add added}, implementations shall respect
     * the <var>z</var>-order retrieved by calling {@link Graphic#getZOrderHint()}. When two
     * added graphics have the same <var>z</var>-order, the most recently added one should be
     * on top.
     *
     * @return Collection of all graphics, as a live collection.
     */
    Collection<Graphic> graphics();

    /**
     * Sets a rendering hint for implementation or platform specific rendering information.
     *
     * @param hintKey The key of the hint.
     * @param hint The rendering hint.
     */
    void setRenderingHint(RenderingHints.Key hintKey, Object hint);

    /**
     * Returns the rendering hint associated with the hint key.
     *
     * @param hintKey The key of the hint.
     * @return The rendering hint.
     */
    Object getRenderingHint(RenderingHints.Key hintKey);

    /**
     * Adds a listener to be notified when a graphic is added or removed.
     *
     * @param listener The listener to add.
     */
    void addRendererListener(RendererListener listener);

    /**
     * Removes a listener.
     *
     * @param listener The listener to remove.
     */
    void removeRendererListener(RendererListener listener);

    /**
     * Provides a hint that a renderer is no longer needed. Implementations may use this method to
     * release resources, if needed. Implementations may also implement this method to return the
     * renderer to a renderer pool.
     * <p>
     * It is an error to reference a {@link Renderer} in any way after its dispose method has been
     * called.
     */
    void dispose();
}
