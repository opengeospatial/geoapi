package org.opengis.go.display.canvas.map2d;

import javax.units.Unit;

import org.opengis.go.display.canvas.CanvasController;
import org.opengis.spatialschema.geometry.Envelope;


/**
 * The <code>Map2DController</code> offers setter methods for properties
 * that define a rectangular, 2D <code>Canvas</code>.
 * 
 * @see Map2D
 * 
 * @author crossley
 */
public interface Map2DController extends CanvasController {
    
    /**
     * Sets the pixel width of the <code>Canvas</code>, provided this
     * <code>Map2DController</code> is the active controller.
     * @param newPixelWidth the new pixel width
     */
    void setPixelWidth(int newPixelWidth);
    
    /**
     * Sets the pixel height of the <code>Canvas</code>, provided this
     * <code>Map2DController</code> is the active controller.
     * @param newPixelHeight the new pixel height
     */
    void setPixelHeight(int newPixelHeight);
    
    /**
     * Sets the map width of the <code>Canvas</code>, provided this
     * <code>Map2DController</code> is the active controller.
     * @param newMapWidth the new map width
     * @param widthUnit the <code>Unit</code> of the newMapWidth
     */
    void setMapWidth(double newMapWidth, Unit widthUnit);
    
    /**
     * Sets the scale of the <code>Canvas</code>, provided this
     * <code>Map2DController</code> is the active controller.
     * @param newScale the new scale
     */
    void setScale(double newScale);
    
    /**
     * Sets the envelope of the <code>Canvas</code>, provided this
     * <code>Map2DController</code> is the active controller.
     * @param newBounds
     */
    void setEnvelope(Envelope newEnvelope);

}
