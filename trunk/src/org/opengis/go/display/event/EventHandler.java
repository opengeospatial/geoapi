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

// J2SE direct dependencies
import java.awt.Component;
import java.util.EventListener;


/**
 * Common abstraction whose implementations can associate different key
 * handlers with a Component to switch key handling states easily.
 *
 * @version 0.2
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 *
 * @see EventManager
 */
public interface EventHandler extends EventListener {
    /**
     * Invoked when the handler is first enabled, or reenabled after
     * being temporarily disabled.
     *
     * @see #handlerDisabled
     */
    public void handlerEnabled();

    /**
     * Invoked when the handler is temporarily disabled.
     *
     * @see #handlerEnabled
     */
    public void handlerDisabled();

    /**
     * Invoked when the handler is permanently removed.
     *
     * @revisit According {@link EventManagerSupport} implementation, a handler
     *          is disabled before to be removed. The specification should said
     *          that.
     */
    public void handlerRemoved();
}
