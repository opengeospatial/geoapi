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
import java.util.EventObject;
import org.opengis.display.primitive.Graphic;


/**
 * RendererEvent send to listener when the Graphics changes.
 * 
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
public abstract class RendererEvent extends EventObject{
    
    public RendererEvent(Renderer source) {
        super(source);
    }

    /**
     * Get the affected Graphics of this event
     * 
     * @param graphics
     */
    public abstract void getGraphics(Collection<Graphic> graphics);

    @Override
    public Renderer getSource() {
        return (Renderer) super.getSource();
    }
    
}
