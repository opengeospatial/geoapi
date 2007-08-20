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

import java.util.EventListener;


/**
 * Common abstraction whose implementations can associate different key
 * handlers with a Component to switch key handling states easily.
 *
 * @version $Revision: 658 $, $Date: 2006-02-23 12:09:34 +1100 (jeu., 23 févr. 2006) $
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @see EventManager
 */
public interface EventHandler extends EventListener {
    /**
     * Invoked when the handler is first enabled, or reenabled after
     * being temporarily disabled.
     *
     * @see #handlerDisabled
     */
    void handlerEnabled();

    /**
     * Invoked when the handler is temporarily disabled.
     *
     * @see #handlerEnabled
     */
    void handlerDisabled();

    /**
     * Invoked when the handler is permanently removed.
     *
     * @todo According <code>EventManagerSupport</code> implementation, a handler
     *       is disabled before to be removed. The specification should said that.
     */
    void handlerRemoved();
}
