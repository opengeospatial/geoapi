/**************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.go.display.event;

// J2SE direct dependencies
import java.util.EventListener;

/**
 * Allows a program to listen to a {@link org.opengis.go.display.primitive.Graphic}
 * for certain mouse and state events.
 *
 * Note that a <code>Graphic</code>
 * is <i>editable</i> when it can be modified by a user via the display GUI
 * (generally by clicking on graphic editing handles).
 * 
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version $Revision: 659 $, $Date: 2006-02-23 14:07:07 +1100 (jeu., 23 févr. 2006) $
 */
public interface GraphicListener extends EventListener {

    /**
     * Invoked when the mouse has been clicked on a <code>Graphic</code>.
     */
    public void mouseClicked(GraphicMouseEvent ge);

    /**
     * Invoked when a mouse button has been pressed on a <code>Graphic</code>.
     */
    public void mousePressed(GraphicMouseEvent ge);

    /**
     * Invoked when a mouse button has been released on a <code>Graphic</code>.
     */
    public void mouseReleased(GraphicMouseEvent ge);

    /**
     * Invoked when the mouse dwells on a <code>Graphic</code>.  Dwelling 
     * occurs after a <code>mouseEntered</code> event transpires and only
     * after <code>mouseMoved</code> events have ceased for an arbitrary
     * length of time.  
     */
    public void mouseDwelled(GraphicMouseEvent ge);

    /**
     * Invoked when a <code>Graphic</code> is selected, either programmatically
     * or through a mouse gesture.
     */
    public void graphicSelected(GraphicChangeEvent ge);

    /**
     * Invoked when a <code>Graphic</code> is deselected, either 
     * programmatically or through a mouse gesture.
     */
    public void graphicDeselected(GraphicChangeEvent ge);

    /**
     * Invoked when a <code>Graphic</code> is disposed.
     */
    public void graphicDisposed(GraphicChangeEvent ge);

    /**
     * Invoked when a <code>Graphic</code> is put into an editable state.
     */
    public void graphicEditableStart(GraphicChangeEvent ge);

    /**
     * Invoked when a <code>Graphic</code> is edited by a gui user.
     */
    public void graphicEditableChanged(GraphicChangeEvent ge);

    /**
     * Invoked when a <code>Graphic</code> is no longer in an editable state.
     */
    public void graphicEditableEnd(GraphicChangeEvent ge);

    /**
     * Invoked when a <code>Graphic</code> changes in any way, other
     * than editing.
     */
    public void graphicChanged(GraphicChangeEvent ge);
}
