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

import java.util.Stack;

/**
 * The <code>EventManagerSupport</code> abstraction of a helper or
 * delegate for <code>EventHandler</code> management.
 *
 * @author  Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public abstract class EventManagerSupport implements EventManager {

    //*************************************************************************
    //  Fields
    //*************************************************************************

    /**
     * <code>EventHandler</code> for handling events in a window.
     */
    protected transient EventHandler currentEventHandler;

    /**
     * Holds <code>EventHandler</code>s to revert to.
     */
    protected transient Stack eventStack;

    //*************************************************************************
    //  implement the EventManager interface
    //*************************************************************************

    /**
     * Enables the given <code>EventHandler</code>.  This <code>EventManager</code>
     * then passes events only to the given <code>EventHandler</code> until it is
     * either removed or another <code>EventHandler</code> is enabled.
     * @param eventHandler  the new <code>EventHandler</code> to enable.
     */
    protected synchronized void enableEventHandler(EventHandler eventHandler) {
        if (currentEventHandler != null) {
            currentEventHandler.handlerDisabled();
            if (eventStack == null || eventStack.peek() != currentEventHandler) {
                currentEventHandler.handlerRemoved();
            }
        }
        currentEventHandler = eventHandler;
        eventHandler.handlerEnabled();
    }

    /**
     * Enables the given <code>EventHandler</code>, and pushes it on the stack so that if
     * another <code>EventHandler</code> gets enabled, this one will be reenabled when
     * that <code>EventHandler</code> is removed.
     * @param eventHandler  The <code>EventHandler</code> to enable and push.
     */
    protected synchronized void pushEventHandler(EventHandler eventHandler) {
        enableEventHandler(eventHandler);
        if (eventStack == null) {
            eventStack = new Stack();
        }
        eventStack.push(eventHandler);
    }

    /**
     * Removes the given <code>EventHandler</code> and reinstates the <code>EventHandler</code> at the
     * top of the stack, if any.
     * @param eventHandler  the <code>EventHandler</code> to disable and remove.
     */
    protected synchronized void removeEventHandler(EventHandler eventHandler) {
        if (eventStack != null) {
            eventStack.remove(eventHandler); // if it's on the stack
        }
        if (currentEventHandler == eventHandler) {
            currentEventHandler.handlerDisabled();
            currentEventHandler.handlerRemoved();
            if ((eventStack == null) || eventStack.isEmpty()) {
                currentEventHandler = null;
            } else {
                currentEventHandler = (EventHandler)eventStack.peek();
                currentEventHandler.handlerEnabled();
            }
        }
    }

    /**
     * Replaces an <code>EventHandler</code> in the stack with another <code>EventHandler</code>.
     * @param existingHandler the <code>EventHandler</code> to be replaced.
     * @param replacementHandler the <code>EventHandler</code> that is replacing the.
     * @return true if existingHandler was found and replaced
     * by replacementHandler.
     */
    protected synchronized boolean replaceEventHandler(
        EventHandler existingHandler,
        EventHandler replacementHandler) {

        boolean replaced = false;
        if (existingHandler != null && replacementHandler != null) {
            if (eventStack != null && eventStack.contains(existingHandler)) {
                int position = eventStack.indexOf(existingHandler);
                if (position != -1) {
                    eventStack.set(position, replacementHandler);
                    replaced = true;
                }
            }
            if (currentEventHandler == existingHandler) {
                currentEventHandler.handlerDisabled();
                currentEventHandler.handlerRemoved();
                currentEventHandler = replacementHandler;
                currentEventHandler.handlerEnabled();
                replaced = true;
            }
        }
        return replaced;
    }

    /**
     * Returns the current <code>EventHandler</code> or null if none.
     * @return the current <code>EventHandler</code> or null if none
     */
    protected EventHandler getCurrentEventHandler() {
        return currentEventHandler;
    }

    /**
     * Returns an array of additional <code>EventHandler</code>s to call for a given
     * <code>Event</code>, if the current event handler doesn't handle it. These
     * handlers will be called in ascending index order until the <code>Event</code>
     * isConsumed().
     * @return an array of additional <code>EventHandler</code>s
     */
    protected synchronized EventHandler[] getFallbackEventHandlers() {
        EventHandler[] fallbackHandlers = new EventHandler[0];
        if (eventStack != null && !eventStack.isEmpty()) {
            int size = eventStack.size();
            if (eventStack.peek() == currentEventHandler) {
                size -= 1;
            }
            fallbackHandlers = new EventHandler[size];
            int j = 0;
            for (int i = size - 1; i >= 0; i--, j++) {
                fallbackHandlers[j] = (EventHandler)eventStack.elementAt(i);
            }
        }
        return fallbackHandlers;
    }
}