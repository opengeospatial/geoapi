/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/

package org.opengis.go.display.event;

import java.awt.event.MouseEvent;

/**
 * The <code>GraphicMouseEvent</code> interface provides a common abstraction for
 * the various event objects pertaining to mouse events on a
 * <code>Graphic</code>.
 * 
 * @author  Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public interface GraphicMouseEvent extends GraphicEvent {
    
    /**
     * Flag for mouse clicked.
     */
    public static final int MOUSE_CLICKED = 0;    
    
    /**
     * Flag for mouse dwelled.
     */
    public static final int MOUSE_DWELLED = 1; 
    
    /**
     * Flag for mouse pressed.
     */   
    public static final int MOUSE_PRESSED = 2;   
    
    /**
     * Flag for mouse released.
     */ 
    public static final int MOUSE_RELEASED = 3;
    
    /**
     * Get the ID flag for this event. Returns MouseEvent.getID(), unless
     * the semantic is not supported by MouseEvent, e.g. MOUSE_DWELLED.
     */
    public int getID();
    
	/**
	 * Returns the MouseEvent that this event references.
	 * @return the MouseEvent referenced by this event.
	 */
	public MouseEvent getMouseEvent();
	
}
