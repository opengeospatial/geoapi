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

import java.util.EventListener;

/**
 * <code>EventHandler</code> defines a common abstraction whose implementations
 * can associate different key handlers with a Component to switch key
 * handling states easily.
 *
 * @author  Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 * @see EventManager
 */
public interface EventHandler extends EventListener {

    /**
     * Called when this <code>EventHandler</code> is first enabled, or reenabled after
     * being temporarily disabled.
     * @see #handlerDisabled
     */
    public abstract void handlerEnabled();

    /**
     * Called when this <code>EventHandler</code> is temporarily disabled.
     * @see #handlerEnabled
     */
    public abstract void handlerDisabled();

    /**
     * Called when this <code>EventHandler</code> is permanently removed.
     */
    public abstract void handlerRemoved();

}