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
import java.util.Stack;

/**
 * Abstraction of a helper or delegate for {@link EventHandler} management.
 *
 * @version 0.2
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 */
public abstract class EventManagerSupport implements EventManager {

    //*************************************************************************
    //  Fields
    //*************************************************************************
    /**
     * Set to <code>true</code> and recompile for tracing events to {@link System#err}.
     */
    static final boolean TRACE = false;

    /**
     * An immutable, empty array of event handler for {@link #getFallbackEventHandlers}.
     */
    private static final EventHandler[] EMPTY_ARRAY = new EventHandler[0];

    /**
     * EventHandler for handling events in a window.
     *
     * @revisit Should this field be private?
     */
    protected transient EventHandler currentEventHandler;

    /**
     * Holds event handlers to revert to.
     *
     * @revisit Should this field be private? Should it be a {@link java.util.LinkedList} instead?
     *          It would be more efficient on handler removal, and is not synchronized (which is
     *          not needed since all methods in this implementation are already synchronized). We
     *          would need to replace calls to {@link java.util.Vector#elementAt} by uses of
     *          {@link java.util.Iterator}.
     */
    protected transient Stack eventStack;

    /**
     * Construct a default event manager support.
     */
    protected EventManagerSupport() {
    }

    //*************************************************************************
    //  implement the EventManager interface
    //*************************************************************************

    /**
     * The window will pass events to only the specified
     * <code>EventHandler</code>, until the <code>EventHandler</code> is
     * changed or removed.
     *
     * @param eventHandler  the new event handler.
     *
     * @revisit Should check for null argument before the other work is performed.
     *          Maybe should also optimize <code>eventHandler == currentEventHandler</code>
     *          as a no-operation.
     */
    protected synchronized void enableEventHandler(final EventHandler eventHandler) {
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
     * Enable the given <code>EventHandler</code>, and push it on the stack so that
     * if another <code>EventHandler</code> gets enabled, this one will be reenabled
     * when that <code>EventHandler</code> is removed.
     *
     * @param eventHandler  The EventHandler to enable and push.
     *
     * @revisit What if the same handler is pushed more than once? This method will work
     *          correctly, but current implementation of {@link #removeEventHandler} and
     *          {@link #replaceEventHandler} will not.
     */
    protected synchronized void pushEventHandler(final EventHandler eventHandler) {
        enableEventHandler(eventHandler);
        if (eventStack == null) {
            eventStack = new Stack();
        }
        eventStack.push(eventHandler);
    }

    /**
     * Remove the given <code>EventHandler</code> and reinstate the
     * <code>EventHandler</code> at the top of the stack, if any.
     *
     * @param eventHandler  the EventHandler to disable and remove.
     */
    protected synchronized void removeEventHandler(final EventHandler eventHandler) {
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
     * Replace a <code>EventHandler</code> in the stack with another <code>EventHandler</code>.
     *
     * @param existingHandler the <code>EventHandler</code> to be replaced.
     * @param  replacementHandler the <code>EventHandler</code> that is replacing the old one.
     * @return <code>true</code> if <code>existingHandler</code> was found and replaced
     *         by <code>replacementHandler</code>.
     *
     * @revisit Current implementation do nothing is any argument is <code>null</code>.
     *          May be okay for <code>existingHandler</code>, but it is more questionable
     *          for <code>replacementHandler</code>. For example one could expect that
     *          invoking <code>replaceEventHandler(existingHandler, null)</code> would be
     *          equivalents to <code>removeEventHandler(existingHandler)</code>.
     *          <br><br>
     *          Maybe should also optimize <code>eventHandler == currentEventHandler</code>
     *          as a no-operation.
     */
    protected synchronized boolean replaceEventHandler(final EventHandler existingHandler,
                                                       final EventHandler replacementHandler) {
        boolean replaced = false;
        if (existingHandler != null && replacementHandler != null) {
            if (eventStack != null && eventStack.contains(existingHandler)) {
                int position = eventStack.indexOf(existingHandler);
                if (position >= 0) {
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
     * Return the current <code>EventHandler</code> or <code>null</code> if none.
     *
     * @return the current <code>EventHandler</code> or <code>null</code> if none.
     */
    protected EventHandler getCurrentEventHandler() {
        return currentEventHandler;
    }

    /**
     * Returns an array of additional <code>EventHandler</code>s to call for a given
     * event, if the current event handler doesn't handle it. These handlers will
     * be called in ascending index order until the event
     * {@linkplain java.awt.event.InputEvent#isConsumed is consumed}.
     *
     * @return an array of additional <code>EventHandler</code>s.
     */
    protected synchronized EventHandler[] getFallbackEventHandlers() {
        EventHandler[] fallbackHandlers = EMPTY_ARRAY;
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
