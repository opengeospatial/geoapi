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
     * Sets the Canvas to use, canvas define the rendered area.
     *
     * @param canvas
     */
    void setCanvas(Canvas canvas);

    /**
     * Returns the Canvas used by the renderer.
     *
     * @return canvas or null if no canvas
     */
    Canvas getCanvas();

    /**
     * Get a snapshot image of the actual mapcontext used with
     * the canvas.
     *
     * @return RenderedImage
     */
    RenderedImage getSnapShot();

    /**
     * Add a single graphic object.
     *
     * @param graphic
     */
    void add(Graphic graphic);

    /**
     * Add a collection of graphic objects.
     *
     * @param graphics
     */
    void add(Collection<Graphic> graphics);

    /**
     * Remove a specific Graphic Object.
     *
     * @param graphic
     */
    void remove(Graphic graphic);
    
    /**
     * Remove a collection of Graphic Object.
     *
     * @param graphics
     */
    void remove(Collection<Graphic> graphics);

    /**
     * Removes all Graphics objects.
     */
    void removeAll();

    /**
     * Returns the list of all graphics that may be rendered.
     *
     * @return collection of all graphics
     */
    Collection<Graphic> getGraphics();

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
     * Add a RendererListener
     * @param listener : RendererListener to add
     */
    void addRendererListener(RendererListener listener);

    /**
     * Remove a RendererListener
     * @param listener : RendererListener to remove
     */
    void removeRendererListener(RendererListener listener);

    /**
     * Must be called once the renderer is not used anymore, to free
     * the used resources.
     */
    void dispose();
    
}
