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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


/**
 * A helper or delegate for {@link MouseHandler} management.
 *
 * @version 0.2
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 */
public class MouseManagerSupport extends EventManagerSupport
                              implements MouseManager, MouseListener, MouseMotionListener
{
    /**
     * Construct a default mouse manager.
     */
    public MouseManagerSupport() {
    }

    //*************************************************************************
    //  implement the MouseManager interface
    //*************************************************************************
    
    /**
     * The window will pass mouse events to only this <code>MouseHandler</code>,
     * until the <code>MouseHandler</code> is changed or removed.
     *
     * @param mouseHandler  the current mouse handler.
     */
    public void enableMouseHandler(MouseHandler mouseHandler) {
        super.enableEventHandler(mouseHandler);
    }

    /**
     * Enable the given <code>MouseHandler</code>, and push it on the stack
     * so that if another <code>MouseHandler</code> gets enabled, this one
     * will be reenabled when that <code>MouseHandler</code> is removed.
     *
     * @param mouseHandler  The <code>MouseHandler</code> to enable and push.
     * @see #enableMouseHandler
     */
    public void pushMouseHandler(MouseHandler mouseHandler) {
        super.pushEventHandler(mouseHandler);
    }

    /**
     * Remove the given <code>MouseHandler</code> and reinstate the
     * <code>MouseHandler</code> at the top of the stack, if any.
     *
     * @param mouseHandler  the <code>MouseHandler</code> to disable and remove.
     */
    public synchronized void removeMouseHandler(MouseHandler mouseHandler) {
        super.removeEventHandler(mouseHandler);
    }

    /**
     * Replace a <code>MouseHandler</code> in the stack with another
     * <code>MouseHandler</code>.
     *
     * @param existingHandler the <code>MouseHandler</code> to be replaced.
     * @param replacementHandler the <code>MouseHandler</code> that is replacing
     *        the <code>existingHandler</code>.
     * @return <code>true</code> if <code>existingHandler</code> was found and replaced
     *         by <code>replacementHandler</code>.
     */
    public boolean replaceMouseHandler(MouseHandler existingHandler, MouseHandler replacementHandler) {
        return super.replaceEventHandler(existingHandler, replacementHandler);
    }

    /**
     * Returns the current <code>MouseHandler</code> or <code>null</code> if none.
     *
     * @return the current <code>MouseHandler</code> or <code>null</code> if none.
     */
    public MouseHandler getCurrentMouseHandler() {
        return (MouseHandler)currentEventHandler;
    }

    /**
     * Returns an array of additional <code>MouseHandler</code>s to call for a given
     * <code>MouseEvent</code>, if the current mouse handler doesn't handle it. These
     * handlers will be called in ascending index order until the <code>MouseEvent</code>
     * {@linkplain java.awt.event.InputEvent#isConsumed() is consumed}.
     *
     * @return the array of additional <code>MouseHandler</code>s.
     *
     * @revisit This code duplicate {@link EventManagerSupport#getFallbackEventHandlers()}
     *          except for the array type (could be handle with a {@link Class} argument).
     */
    public synchronized MouseHandler[] getFallbackMouseHandlers() {
        MouseHandler[] fallbackHandlers = new MouseHandler[0];
        if (eventStack != null && !eventStack.isEmpty()) {
            int size = eventStack.size();
            if (eventStack.peek() == currentEventHandler) {
                size -= 1;
            }
            fallbackHandlers = new MouseHandler[size];
            int j = 0;
            for (int i = size - 1; i >= 0; i--, j++) {
                fallbackHandlers[j] = (MouseHandler)eventStack.elementAt(i);
            }
        }
        return fallbackHandlers;
    }

    //*************************************************************************
    //  implement the MouseListener interface
    //*************************************************************************
    
    /**
     * Calls {@link MouseHandler#mousePressed mousePressed} on the current mouse
     * handler, and then as long as the event is not consumed, continues passing
     * the event to the <code>mousePressed</code> method of any fallback key handlers.
     *
     * @param e the <code>MouseEvent</code> to process.
     */
    public void mousePressed(final MouseEvent e) {
        if (TRACE) {
            System.err.println("manager got mousePressed! during...");
            new Exception().printStackTrace();
        }
        final MouseHandler mouseHandler;

        synchronized (this) {
            mouseHandler = (MouseHandler)currentEventHandler;
        }
        if (mouseHandler != null) {
            mouseHandler.mousePressed(e);

            if (!e.isConsumed()) {
                MouseHandler[] fallback = getFallbackMouseHandlers();

                for (int i = 0; (i < fallback.length) && !e.isConsumed(); i++) {

                    // EXPLAIN(CCP): Even though the array returned by the
                    // method getFallbackMouseHandlers() does NOT include the
                    // current mouse hander, we still have to check here,
                    // because sometimes the call to mouseHandler.
                    //    mousePressed(e) above actually causes a NEW handler
                    // to become current.  Thus, since we've already stored off
                    // the OLD current handler into a local variable, we need
                    // to check it as well.
                    if (mouseHandler != fallback[i]) {
                        fallback[i].mousePressed(e);
                    }
                }
            }
        }
    }

    /**
     * Calls {@link MouseHandler#mouseReleased mouseReleased} on the current mouse
     * handler, and then as long as the event is not consumed, continues passing
     * the event to the <code>mouseReleased</code> method of any fallback key handlers.
     *
     * @param e the <code>MouseEvent</code> to process.
     *
     * @revisit Why this implementation do not performs the same safety check than
     *          other implementations? (<code>if (keyHandler != fallback[i])</code>)
     */
    public void mouseReleased(final MouseEvent e) {
        if (TRACE) {
            System.err.println("manager got mouseReleased!");
        }
        final MouseHandler mouseHandler;

        synchronized (this) {
            mouseHandler = (MouseHandler)currentEventHandler;
        }
        if (mouseHandler != null) {
            mouseHandler.mouseReleased(e);
            if (!e.isConsumed()) {
                MouseHandler[] fallback = getFallbackMouseHandlers();
                for (int i = 0; (i < fallback.length) && !e.isConsumed(); i++) {
                    fallback[i].mouseReleased(e);
                }
            }
        }
    }

    /**
     * Calls {@link MouseHandler#mouseClicked mouseClicked} on the current mouse
     * handler, and then as long as the event is not consumed, continues passing
     * the event to the <code>mouseClicked</code> method of any fallback key handlers.
     *
     * @param e the <code>MouseEvent</code> to process.
     *
     * @revisit Why this implementation do not performs the same safety check than
     *          other implementations? (<code>if (keyHandler != fallback[i])</code>)
     */
    public void mouseClicked(final MouseEvent e) {
        if (TRACE) {
            System.err.println("manager got mouseClicked!");
        }
        final MouseHandler mouseHandler;

        synchronized (this) {
            mouseHandler = (MouseHandler)currentEventHandler;
        }
        if (mouseHandler != null) {
            mouseHandler.mouseClicked(e);
            if (!e.isConsumed()) {
                MouseHandler[] fallback = getFallbackMouseHandlers();
                for (int i = 0; (i < fallback.length) && !e.isConsumed(); i++) {
                    fallback[i].mouseClicked(e);
                }
            }
        }
    }
    
    //*************************************************************************
    //  implement the MouseMotionListener interface
    //*************************************************************************

    /**
     * Calls {@link MouseHandler#mouseEntered mouseEntered} on the current mouse
     * handler, and then as long as the event is not consumed, continues passing
     * the event to the <code>mouseEntered</code> method of any fallback key handlers.
     *
     * @param e the <code>MouseEvent</code> to process.
     *
     * @revisit Why this implementation do not performs the same safety check than
     *          other implementations? (<code>if (keyHandler != fallback[i])</code>)
     */
    public void mouseEntered(final MouseEvent e) {
        final MouseHandler mouseHandler;

        synchronized (this) {
            mouseHandler = (MouseHandler)currentEventHandler;
        }
        if (mouseHandler != null) {
            mouseHandler.mouseEntered(e);
            if (!e.isConsumed()) {
                MouseHandler[] fallback = getFallbackMouseHandlers();
                for (int i = 0; (i < fallback.length) && !e.isConsumed(); i++) {
                    fallback[i].mouseEntered(e);
                }
            }
        }
    }

    /**
     * Calls {@link MouseHandler#mouseExited mouseExited} on the current mouse
     * handler, and then as long as the event is not consumed, continues passing
     * the event to the <code>mouseExited</code> method of any fallback key handlers.
     *
     * @param e the <code>MouseEvent</code> to process.
     *
     * @revisit Why this implementation do not performs the same safety check than
     *          other implementations? (<code>if (keyHandler != fallback[i])</code>)
     */
    public void mouseExited(final MouseEvent e) {
        final MouseHandler mouseHandler;

        synchronized (this) {
            mouseHandler = (MouseHandler)currentEventHandler;
        }
        if (mouseHandler != null) {
            mouseHandler.mouseExited(e);
            if (!e.isConsumed()) {
                MouseHandler[] fallback = getFallbackMouseHandlers();
                for (int i = 0; (i < fallback.length) && !e.isConsumed(); i++) {
                    fallback[i].mouseExited(e);
                }
            }
        }
    }

    /**
     * Calls {@link MouseHandler#mouseMoved mouseMoved} on the current mouse
     * handler, and then as long as the event is not consumed, continues passing
     * the event to the <code>mouseMoved</code> method of any fallback key handlers.
     *
     * @param e the <code>MouseEvent</code> to process.
     *
     * @revisit Why this implementation do not performs the same safety check than
     *          other implementations? (<code>if (keyHandler != fallback[i])</code>)
     */
    public void mouseMoved(final MouseEvent e) {
        final MouseHandler mouseHandler;

        synchronized (this) {
            mouseHandler = (MouseHandler)currentEventHandler;
        }
        if (mouseHandler != null) {
            mouseHandler.mouseMoved(e);
            if (!e.isConsumed()) {
                MouseHandler[] fallback = getFallbackMouseHandlers();
                for (int i = 0; (i < fallback.length) && !e.isConsumed(); i++) {
                    fallback[i].mouseMoved(e);
                }
            }
        }
    }

    /**
     * Calls {@link MouseHandler#mouseDragged mouseDragged} on the current mouse
     * handler, and then as long as the event is not consumed, continues passing
     * the event to the <code>mouseDragged</code> method of any fallback key handlers.
     *
     * @param e the <code>MouseEvent</code> to process.
     *
     * @revisit Why this implementation do not performs the same safety check than
     *          other implementations? (<code>if (keyHandler != fallback[i])</code>)
     */
    public void mouseDragged(final MouseEvent e) {
        final MouseHandler mouseHandler;

        synchronized (this) {
            mouseHandler = (MouseHandler)currentEventHandler;
        }
        if (mouseHandler != null) {
            mouseHandler.mouseDragged(e);
            if (!e.isConsumed()) {
                MouseHandler[] fallback = getFallbackMouseHandlers();
                for (int i = 0; (i < fallback.length) && !e.isConsumed(); i++) {
                    fallback[i].mouseDragged(e);
                }
            }
        }
    }
}
