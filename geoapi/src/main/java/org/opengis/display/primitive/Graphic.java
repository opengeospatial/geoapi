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



/**
 * <code>Graphic</code> defines the root abstraction of a graphic object
 * taxonomy, specifying the methods common to a lightweight set of graphic objects.
 * 
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
public interface Graphic {

    /**
     * Returns the visible value.
     * @return the visible value.
     */
    boolean isVisible();

    /**
     * Sets the visible value.
     * @param visible the visible value.
     */
    void setVisible(boolean visible);
    
    /**
     * Adds the given <code>GraphicListener</code> to this <code>Graphic</code>'s list of
     * listeners.  
     *
     * @param listener the <code>GraphicListener</code> to add.
     */
    void addGraphicListener(GraphicListener listener);

    /**
     * Removes the given <code>GraphicListener</code> from this <code>Graphic</code>'s list of
     * listeners.
     *
     * @param listener the <code>GraphicListener</code> to remove.
     */
    void removeGraphicListener(GraphicListener listener);

    /**
     * Method that can be called when an object is no longer needed.
     * Implementations may use this method to release resources, if needed.
     * Implementations may also implement this method to return an object
     * to an object pool.  It is an error to reference a <code>Graphic</code> in any
     * way after its dispose method has been called.
     */
    void dispose();
        
}

