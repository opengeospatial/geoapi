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

import java.awt.event.MouseEvent;


/**
 * Provides a common abstraction for the various event objects pertaining
 * to mouse events on a {@link org.opengis.go.display.primitive.Graphic}.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 *
 * @todo This interface wraps a {@link MouseEvent}, which is not used by some toolkit like SWT.
 */
public interface GraphicMouseEvent extends GraphicEvent {
    /**
     * Flag for mouse clicked.
     */
    int MOUSE_CLICKED = 0;

    /**
     * Flag for mouse dwelled.
     */
    int MOUSE_DWELLED = 1;

    /**
     * Flag for mouse pressed.
     */
    int MOUSE_PRESSED = 2;

    /**
     * Flag for mouse released.
     */
    int MOUSE_RELEASED = 3;

    /**
     * Get the ID flag for this event. Returns {@link MouseEvent#getID()},
     * unless the semantic is not supported by <code>MouseEvent</code>,
     * e.g. {@link #MOUSE_DWELLED}.
     *
     * @todo This specification is incompatible with {@link MouseEvent} constants!
     *       This is because {@link #MOUSE_CLICKED} value is different from
     *       {@link MouseEvent#MOUSE_CLICKED} value, etc.
     */
    int getID();

    /**
     * Returns the <code>MouseEvent</code> that this event references.
     *
     * @return the <code>MouseEvent</code> referenced by this event.
     */
    MouseEvent getMouseEvent();
}
