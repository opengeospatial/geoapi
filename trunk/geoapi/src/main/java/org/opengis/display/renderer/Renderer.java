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

import java.awt.RenderingHints;
import java.awt.image.RenderedImage;
import java.util.Collection;
import org.opengis.display.canvas.Canvas;
import org.opengis.display.primitive.Graphic;


/**
 * A renderer can generate images from graphics and a define canvas.
 *
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
public interface Renderer {
    /**
     * Sets the canvas to use, which defines the rendered area.
     *
     * @param canvas The canvas.
     */
    void setCanvas(Canvas canvas);

    /**
     * Returns the canvas used by this renderer.
     *
     * @return The canvas or {@code null} if undefined.
     */
    Canvas getCanvas();

    /**
     * Gets a snapshot image of the actual renderer content.
     *
     * @return RenderedImage A snapshot image.
     */
    RenderedImage getSnapShot();

    /**
     * Returns the collection of all graphics to render. Changes to this collection (addition,
     * removal) are reflected into the set of graphics to be rendered. Note that the returned
     * collection must notifies the {@linkplain RendererListener renderer listener} about any
     * addition or removal.
     *
     * @return Collection of all graphics, as a live collection.
     */
    Collection<Graphic> getGraphics();

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
