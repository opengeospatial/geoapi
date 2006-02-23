package org.opengis.go.display.canvas.map2d;

import javax.units.Unit;

import org.opengis.go.display.canvas.CanvasState;
import org.opengis.spatialschema.geometry.Envelope;


/**
 * The <code>Map2DState</code> explicitly names the properties necessary 
 * to define a rectangular, 2D canvas.
 * 
 * @see Map2D
 *  
 * @author crossley
 */
public interface Map2DState extends CanvasState {

    /**
     * Gets the pixel width of the <code>Canvas</code> represented by this 
     * <code>Map2DState</code>.
     * @return the pixel width
     */             
    int getPixelWidth();
    
    /**
     * Gets the pixel height of the <code>Canvas</code> represented by this
     * <code>Map2DState</code>. 
     * @return the pixel height
     */
    int getPixelHeight();
    
    /**
     * Gets the map width of the <code>Canvas</code> represented by this
     * <code>Map2DState</code>, in terms of the given <code>unit</code>.
     * @param unit the <code>Unit</code> for the return value
     * @return the width in terms of the given <code>unit</code>
     */
    double getMapWidth(Unit unit);
    
    /**
     * Gets the scale of the <code>Canvas</code> represented by this 
     * <code>Map2DState</code>.
     * @return the scale
     */
    double getScale();
    
    /**
     * Gets the <code>Envelope</code> of the <code>Canvas</code>
     * represented by this <code>Map2DState</code>.
     * @return the envelope
     */
    Envelope getEnvelope();
    
}
