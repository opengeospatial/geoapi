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

import java.util.EventListener;
import org.opengis.display.primitive.Graphic;


/**
 * Listener notified when {@linkplain Graphic graphics} are added or removed from a
 * {@linkplain Renderer renderer}.
 *
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
public interface RendererListener extends EventListener {
    /**
     * Called when graphic objects are added.
     *
     * @param event The event containing the collection of graphics added.
     */
    void graphicsAdded(RendererEvent event);

    /**
     * Called when graphics objects are removed.
     *
     * @param event The event containing the collection of graphics removed.
     */
    void graphicsRemoved(RendererEvent event);

    /**
     * Called when graphic objects are updated.
     *
     * @param event The event containing the collection of graphics updated.
     */
    void graphicsChanged(RendererEvent event);
}
