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
package org.opengis.display.container;

import java.util.EventListener;
import org.opengis.display.primitive.Graphic;


/**
 * Listener notified when {@linkplain Graphic graphics} are added or removed from a
 * {@linkplain GraphicsContainer container}.
 *
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
public interface ContainerListener extends EventListener {
    /**
     * Called when graphic objects are added.
     *
     * @param event The event containing the collection of graphics added.
     */
    void graphicsAdded(ContainerEvent event);

    /**
     * Called when graphics objects are removed.
     *
     * @param event The event containing the collection of graphics removed.
     */
    void graphicsRemoved(ContainerEvent event);

    /**
     * Called when graphic objects are updated.
     *
     * @param event The event containing the collection of graphics updated.
     */
    void graphicsChanged(ContainerEvent event);
    
    /**
     * Called when graphic objects need to be repainted, but no property changed.
     * Exemple : a blinking or animated graphic.
     * 
     * @param event The event containing the collection of graphics updated.
     */
    void graphicsDisplayChanged(ContainerEvent event);
    
}
