package org.opengis.go.display.canvas.map2d;

import javax.units.Unit;

import org.opengis.go.display.canvas.Canvas;
import org.opengis.go.geometry.BoundingRectangle;
import org.opengis.spatialschema.geometry.DirectPosition;


/**
 * The <code>Map2D</code> interface extends the <code>Canvas</code> interface in order to clearly
 * define a group of mutators for 2 dimensional maps.  <b>These mutators are not intended to be
 * called directly!</b>  Rather, these methods are intended to work with the 
 * <code>Map2DController</code>
 * 
 * @see Map2DController
 * @see Map2DState
 * 
 * @author crossley
 *
 */
public interface Map2D extends Canvas {

    /**
     * Sets the position of the center pixel of this <code>Map2D</code>, if the 
     * <code>controller</code> is the active controller.
     * @param controller The token identifier
     * @param directPosition The new position for the center pixel
     */
    void setCenter(Map2DController controller, DirectPosition directPosition);
    
    /**
     * Sets the pixel width of this <code>Map2D</code>, if the <code>controller</code> is the active
     * controller.
     * @param controller The token identifier
     * @param newPixelWidth The new pixel width
     */
    void setPixelWidth(Map2DController controller, int newPixelWidth);
    
    /**
     * Sets the pixel height of this <code>Map2D</code>, if the <code>controller</code> is the
     * active controller.
     * @param controller The token identifier
     * @param newPixelHeight The new pixel height
     */
    void setPixelHeight(Map2DController controller, int newPixelHeight);
    
    /**
     * Sets the map width of this <code>Map2D</code>, in terms of <code>widthUnit</code>, if the 
     * <code>controller</code> is the active controller.
     * @param controller The token identifier
     * @param newMapWidth The new map width
     * @param widthUnit The new map width's unit
     */
    void setMapWidth(Map2DController controller, double newMapWidth, Unit widthUnit);
    
    /**
     * Sets the map scale of this <code>Map2D</code>, if the <code>controller</code> is the active
     * controller.
     * @param controller The token identifier
     * @param newScale The new map scale
     */
    void setScale(Map2DController controller, double newScale);
    
    /**
     * Sets the bounds of this <code>Map2D</code>, if the <code>controller</code> is the active
     * controller.
     * @param controller The token identifier
     * @param newBoundingRectangle The new map bounds
     */
    void setBoundingRectangle(Map2DController controller, BoundingRectangle newBoundingRectangle);
}
