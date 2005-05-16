/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.go.display;

import java.awt.Container;
import java.util.Properties;

import org.opengis.go.display.canvas.Canvas;
import org.opengis.go.display.primitive.Graphic;
import org.opengis.go.display.style.GraphicStyle;

/**
 * <code>DisplayFactory</code> defines a common abstraction for creating
 * the different kinds of display objects.
 *
 * @author Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public interface DisplayFactory {

    /**
     * Creates a new <code>Graphic</code>.
     * @param implementsGraphic The <code>Class</code> of a Graphic
     *      primitive interface (such as <code>GraphicArc.class</code>).
     *      Throws an <code>IllegalArgumentException</code> if the
     *      <code>implementsGraphic</code> does not match any Graphic
     *      primitive interface that this factory supports.
     * @return the newly created <code>Graphic</code>.
     */
    public Graphic createGraphic(Class implementsGraphic);

    /**
     * Creates a new <code>GraphicStyle</code>.  Implementations of
     * DisplayFactory are expected to support all four subclasses of GraphicStyle.
     * @param implementsGraphicStyle The <code>Class</code> of a GraphicStyle
     *      interface (such as <code>TextSymbolizer.class</code>).
     *      Throws an <code>IllegalArgumentException</code> if the
     *      <code>implementsGraphicStyle</code> does not match any GraphicStyle
     *      interface.
     * @return the newly created <code>GraphicStyle</code>.
     */
    public GraphicStyle createGraphicStyle(Class implementsGraphicStyle);

    /**
     * Returns an object that represents the capabilities of this
     * Display factory and its associated canvas.
     */
    public DisplayCapabilities getCapabilities();
    
    /**
     * Creates a new <code>Canvas</code> object that is embedded in the given 
     * <code>Container</code>.
     * <p>
     * If the GO-1 implementation has multiple types of <code>Canvas</code> implementations,
     * then the <code>Canvas</code> object that best fits the given <code>Properties</code>
     * is the object returned. The criteria for "best fit" are left up to the particular GO-1
     * implementation.
     * <p>
     * The <code>Canvas</code> rendering takes up the full extent of the <code>Container</code> 
     * display space. It is assumed that the <code>Container</code> will be embedded by a user 
     * program in another user interface.
     * 
     * @param canvasProperties <code>Properties</code> that can be used to determine
     *    which <code>Canvas</code> implementation to use.
     * @param container the <code>java.awt.Container</code> to place the 
     *     <code>Canvas</code> in.
     * @return the newly-created Canvas.
     */
    public Canvas createCanvas(Properties canvasProperties, Container container);
    
    /**
     * Creates a new <code>Canvas</code> object that is returned embedded in a window, as 
     * determined by the GO-1 implementation. For example, it could create a new top level 
     * frame window. The implementor may include all of its standard user interface components.
     * <p>
     * If the GO-1 implementation has multiple types of <code>Canvas</code> implementations,
     * then the <code>Canvas</code> object that best fits the given <code>Properties</code>
     * is the object returned. The criteria for "best fit" are left up to the particular GO-1
     * implementation.
     * 
     * @param canvasProperties <code>Properties</code> that can be used to determine
     *    which <code>Canvas</code> implementation to use.
     */
    public Canvas createCanvas(Properties canvasProperties);
    
    /**
     * Gets an existing <code>Canvas</code> object by UID, or null if no 
     * <code>Canvas</code> exists for the given UID.
     */
    public Canvas getCanvas(String uid);
}
