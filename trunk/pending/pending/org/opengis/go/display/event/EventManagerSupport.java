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
 * delegate for EventHandler management.
 *
 * @author  Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public abstract class EventManagerSupport implements EventManager {

    //*************************************************************************
    //  Fields
    //*************************************************************************
    
    /**
     * EventHandler for handling events in a window.
     */
    protected transient EventHandler currentEventHandler;

    /**
     * Holds event handlers to revert to.
     */
    protected transient Stack eventStack;
    
    //*************************************************************************
    //  implement the EventManager interface
    //*************************************************************************
    
    /**
     * The window will pass event events to only this EventHandler, until
     * the EventHandler is changed or removed.
     * @param eventHandler  the new event handler.
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
     * Enable the given EventHandler, and push it on the stack so that if
     * another EventHandler gets enabled, this one will be reenabled when
     * that EventHandler is removed.
     * @param eventHandler  The EventHandler to enable and push.
     */
    protected synchronized void pushEventHandler(EventHandler eventHandler) {
        enableEventHandler(eventHandler);

        if (eventStack == null) {
            eventStack = new Stack();
        }
        eventStack.push(eventHandler);
    }

    /**
     * Remove the given EventHandler and reinstate the EventHandler at the
     * top of the stack, if any.
     * @param eventHandler  the EventHandler to disable and remove.
     */
    protected synchronized void removeEventHandler(EventHandler eventHandler) {
        if (eventStack != null) {
            eventStack.remove(eventHandler);    // if it's on the stack
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
     * Replace a EventHandler in the stack with another EventHandler.
     * @param existingHandler the EventHandler to be replaced.
     * @param replacementHandler the EventHandler that is replacing the.
     * @return true if existingHandler was found and replaced
     * existingHandler.
     */
    protected synchronized boolean replaceEventHandler(EventHandler existingHandler, EventHandler replacementHandler) {
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
     * Return the current EventHandler or null if none.
     * @return the current EventHandler or null if none
     */
    protected EventHandler getCurrentEventHandler() {
        return currentEventHandler;
    }

    /**
     * Returns an array of additional EventHandlers to call for a given
     * Event, if the current event handler doesn't handle it. These
     * handlers will be called in ascending index order until the Event
     * isConsumed().
     * @return an array of additional EventHandlers
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