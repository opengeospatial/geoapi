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
import org.opengis.go.display.canvas.CanvasParameterAccessor;

import com.dautelle.units.Unit;

/**
 * Instances of this interface describe the current
 * state of a <code>Canvas</code> that has a XY (Cartesian plane) display area.
 * <p>
 * The information contained by instances
 * of this interface should only describe the viewing area or volume of the
 * canvas and should not contain any state information regarding the data 
 * contained within it.
 * <p>
 * When an instance of this class is returned from <code>Canvas</code>
 * methods, a "snapshot" of the current state of the canvas is taken
 * and the values will never change (even if the canvas changes state).
 *
 * @author Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public interface XYParameterAccessor extends CanvasParameterAccessor {
    
    /**
     * Returns the pixel width of the <code>Canvas</code>.
     */
    public int getPixelWidth();

    /**
     * Returns the pixel height of the <code>Canvas</code>.
     */
    public int getPixelHeight();

    /**
     * Returns the "width", in the given <code>Unit</code>, of the canvas.  In 
     * general, this should be computed by multiplying the number of given 
     * Units per pixel at the canvas center by the pixel width of the canvas.  
     * As such, it is only an approximate value.
     */
    public double getWidth(Unit unit);

    /**
     * Returns the current scale of the <code>Canvas</code>.  For 
     * consistency, canvas implementations are encouraged to use a formula 
     * similar to the following
     * to compute scale:
     * <P>
     *   <code>scale = ppu * width / pixelWidth</code>
     * <P>
     * where <code>width</code> is the same as the value returned by
     * <code>getWidth(Unit)</code>, <code>pixelWidth</code> is the same as the
     * value returned by <code>getPixelWidth()</code>, and <code>ppu</code>
     * is the display device's number of pixels per Unit.
     * <P>
     * For example, for a canvas whose scale is 1:1000, this function should
     * return "1000".
     */
    public double getScale();

    /**
     * Returns a bounding rectangle for the region that is currently displayed
     * on this canvas.  The returned value is not necessarily well-defined.
     */
    public BoundingRectangle getBoundingRectangle();

}