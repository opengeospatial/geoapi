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


/**
 * The one method in this interface is called by a <code>Canvas</code> when
 * its state has changed.
 * 
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
public interface CanvasListener {
    
    /**
     * This method is called by a <code>Canvas</code> when its state has
     * changed.
     * @param event describe the changes.
     */
    void canvasChanged(CanvasEvent event);
    
}
