/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.go.display.canvas.map2d;

// J2SE extensions
import javax.units.Unit;

// OpenGIS direct dependencies
import org.opengis.go.display.canvas.Canvas;
import org.opengis.go.display.canvas.CanvasController;
import org.opengis.spatialschema.geometry.Envelope;


/**
 * Setter methods for properties that define a rectangular, 2D {@link Canvas}.
 * 
 * @see Map2D
 * 
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @author Jesse Crossley (SYS Technologies)
 */
public interface Map2DController extends CanvasController {    
    /**
     * Sets the pixel width of the {@linkplain Canvas canvas}, provided this
     * {@code Map2DController} is the active controller.
     *
     * @param newPixelWidth the new pixel width.
     */
    void setPixelWidth(int newPixelWidth);
    
    /**
     * Sets the pixel height of the {@linkplain Canvas canvas}, provided this
     * {@code Map2DController} is the active controller.
     *
     * @param newPixelHeight the new pixel height.
     */
    void setPixelHeight(int newPixelHeight);
    
    /**
     * Sets the map width of the {@linkplain Canvas canvas}, provided this
     * {@code Map2DController} is the active controller.
     *
     * @param newMapWidth the new map width
     * @param widthUnit the {@linkplain Unit unit} of the {@code newMapWidth}.
     */
    void setMapWidth(double newMapWidth, Unit widthUnit);
    
    /**
     * Sets the scale of the {@linkplain Canvas canvas}, provided this
     * {@code Map2DController} is the active controller.
     *
     * @param newScale the new scale.
     */
    void setScale(double newScale);
    
    /**
     * Sets the envelope of the {@linkplain Canvas canvas}, provided this
     * {@code Map2DController} is the active controller.
     */
    void setEnvelope(Envelope newEnvelope);
}
