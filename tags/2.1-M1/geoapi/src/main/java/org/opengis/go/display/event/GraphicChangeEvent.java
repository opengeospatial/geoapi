/**************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.go.display.event;

/**
 * Provides a common abstraction for the various event objects pertaining
 * to change events on a {@link org.opengis.go.display.primitive.Graphic}.
 * 
 * @version $Revision: 658 $, $Date: 2006-02-23 12:09:34 +1100 (jeu., 23 févr. 2006) $
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 */
public interface GraphicChangeEvent extends GraphicEvent {
    
    /**
     * Flag for changes to a <code>Graphic</code> by a user via the GUI controls.
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
     *
     * @return The event type. One of the constants declared in this interface.
	 */
	public int getID();
	
}
