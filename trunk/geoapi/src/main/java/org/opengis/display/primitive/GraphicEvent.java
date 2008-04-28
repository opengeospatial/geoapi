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

import java.util.EventObject;
import org.opengis.go.display.primitive.Graphic;


/**
 * Provides a common abstraction for various events.
 *
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
public abstract class GraphicEvent extends EventObject{

    
    public GraphicEvent(Graphic gra){
        super(gra);
    }

    @Override
    public Graphic getSource() {
        return (Graphic) super.getSource();
    }
    
}
