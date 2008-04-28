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


/**
 * Listen to the renderer. Methods are called when graphics are added or removed from
 * the renderer.
 * 
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
public interface RendererListener {
            
    /**
     * Called when graphic objects are added.
     * 
     * @param event 
     */
    void graphicsAdded(RendererEvent event);
    
    /**
     * Called when graphics objects are removed.
     * 
     * @param event 
     */
    void graphicRemoved(RendererEvent event);
    
    /**
     * Called when graphic objects are updated.
     * 
     * @param event 
     */
    void graphicChanged(RendererEvent event);
    
}
