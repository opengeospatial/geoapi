/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/

package org.opengis.go.display.typical.canvas;

import org.opengis.go.geometry.BoundingRectangle;
import org.opengis.go.display.canvas.CanvasParameterMutator;

import com.dautelle.units.Unit;
/**
 * Instances of this interface are used to modify the current
 * display state of a <code>Canvas</code> that has a XY (Cartesian plane) display area.
 * <p>
 * These methods (in an implementation-specific
 * interaction with the <code>Canvas</code>) should only set the 
 * <code>Canvas</code>'s properties <b>IFF</b> the 
 * <code>CanvasController</code> is recognized to be the active controller (the
 * controller given out by the canvas when the most recent handler was 
 * enabled).
 * 
 * @author Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public interface XYParameterMutator extends CanvasParameterMutator {
    /**
     * Sets the pixel width of the <code>Canvas</code> this controller works
     * for, if it is the active controller.
     */
    public void setPixelWidth(int newWidth);
    
    /**
     * Sets the pixel height of the <code>Canvas</code> this controller works
     * for, if it is the active controller.
     */
    public void setPixelHeight(int newHeight);
    
    /**
     * Sets the "width", in the given <code>Unit</code>, of the 
     * <code>Canvas</code> this controller works for, if it is the active
     * controller.  
     * In general, the width should be computed by multiplying the number of
     * <code>Unit</code>s per pixel at the canvas's center by the pixel width
     * of the canvas.  As such, it is only an approximate value.
     */
    public void setWidth(double newWidth, Unit unit);
    
    /**
     * Sets the current scale of the <code>Canvas</code> this controller
     * works for, if it is the active controller.
     * For consistency, implementors are encouraged to use a formula similar to
     * the following to compute scale:
     * <p>
     * <code>scale = ppu * width / pixelWidth;</code>
     * <p>
     * where <code>width</code> is the same as the value set by 
     * <code>setWidth(double, Unit)</code>, <code>pixelWidth</code> is the same
     * as the value set by <code>setPixelWidth(int)</code>, and 
     * <code>ppu</code> is the display device's number of pixels per 
     * <code>Unit</code>.
     */
    public void setScale(double newScale);
    
    /**
     * Sets a bounding rectangle for the region that is currently displayed on
     * the <code>Canvas</code> this controller is working for, if it is the 
     * active controller.
     */
    public void setBoundingRectangle(BoundingRectangle newBounds);
    
}
