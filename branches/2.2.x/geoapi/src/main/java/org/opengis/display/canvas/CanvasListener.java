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
package org.opengis.display.canvas;

import java.util.EventListener;


/**
 * Listener notified when a {@linkplain Canvas canvas} state changed.
 *
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since  GeoAPI 2.2
 */
public interface CanvasListener extends EventListener {
    /**
     * Invoked by a {@linkplain Canvas canvas} when its state has changed.
     *
     * @param event A description of the change.
     */
    void canvasChanged(CanvasEvent event);
}
