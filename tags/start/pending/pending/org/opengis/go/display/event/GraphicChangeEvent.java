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

/**
 * The <code>GraphicChangeEvent</code> interface provides a common abstraction for
 * the various event objects pertaining to change events on a
 * <code>Graphic</code>.
 * 
 * @author  Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public interface GraphicChangeEvent extends GraphicEvent {
    
    /**
     * Flag for changed to a <code>Graphic</code> by a user via the GUI controls.
     */
    public static final int EDITABLE_CHANGED = 0;
    
    /**
     * Flag for exiting editable mode.
     */
    public static final int EDITABLE_END = 1;
    
    /**
     * Flag for entering editable mode.
     */
    public static final int EDITABLE_START = 2;
    
    /**
     * Flag for graphic changed by non-edit mechanism.
     */
    public static final int GRAPHIC_CHANGED = 3;    
    
    /**
     * Flag for graphic deselected.
     */
    public static final int GRAPHIC_DESELECTED = 4; 
    
    /**
     * Flag for graphic disposed.
     */   
    public static final int GRAPHIC_DISPOSED = 5;   
    
    /**
     * Flag for graphic selected.
     */ 
    public static final int GRAPHIC_SELECTED = 6;
    
	/**
	 * Get the ID flag for this event.
	 */
	public int getID();
	
}
