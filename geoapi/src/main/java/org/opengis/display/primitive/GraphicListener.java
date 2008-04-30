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
package org.opengis.display.primitive;

import java.util.EventListener;


/**
 * Allows a program to listen to a {@link org.opengis.go.display.primitive.Graphic}
 * for state events.
 * 
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
public interface GraphicListener extends EventListener {
    
    /**
     * Invoked when a <code>Graphic</code> changes in any way.
     * 
     * @param event 
     */
    void graphicChanged(GraphicEvent event);
    
}
