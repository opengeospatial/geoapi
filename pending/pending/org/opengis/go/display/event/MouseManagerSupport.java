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

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * The <code>MouseManagerSupport</code> class is a helper or
 * delegate for <code>MouseHandler</code> management.
 *
 * @author  Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public class MouseManagerSupport
    extends EventManagerSupport
    implements MouseManager, MouseListener, MouseMotionListener {

    //*************************************************************************
    //  implement the MouseManager interface
    //*************************************************************************

    /**
     * Enables the given <code>MouseHandler</code>.  This <code>MouseManager</code>
     * then passes events only to the given <code>MouseHandler</code> until it is
     * either removed or another <code>MouseHandler</code> is enabled.
     * @param mouseHandler  the new <code>MouseHandler</code> to enable.
     */
    public synchronized void enableMouseHandler(MouseHandler mouseHandler) {
        super.enableEventHandler(mouseHandler);
    }

    /**
     * Enables the given <code>MouseHandler</code>, and pushes it on the stack so that if
     * another <code>MouseHandler</code> gets enabled, this one will be reenabled when
     * that <code>MouseHandler</code> is removed.
     * @param mouseHandler  The <code>MouseHandler</code> to enable and push.
     */
    public synchronized void pushMouseHandler(MouseHandler mouseHandler) {
        super.pushEventHandler(mouseHandler);
    }

    /**
     * Removes the given <code>MouseHandler</code> and reinstate the <code>MouseHandler</code> at the
     * top of the stack, if any.
     * @param mouseHandler  the <code>MouseHandler</code> to disable and remove.
     */
    public synchronized void removeMouseHandler(MouseHandler mouseHandler) {
        super.removeEventHandler(mouseHandler);
    }

    /**
     * Replaces a <code>MouseHandler</code> in the stack with another <code>MouseHandler</code>.
     * @param existingHandler the <code>MouseHandler</code> to be replaced.
     * @param replacementHandler the <code>MouseHandler</code> that is replacing the.
     * @return true if existingHandler was found and replaced by replacementHandler.
     */
    public synchronized boolean replaceMouseHandler(
        MouseHandler existingHandler,
        MouseHandler replacementHandler) {
        return super.replaceEventHandler(existingHandler, replacementHandler);
    }

    /**
     * Returns the current <code>MouseHandler</code> or null if none.
     * @return the current <code>MouseHandler</code> or null if none.
     */
    public MouseHandler getCurrentMouseHandler() {
        return (MouseHandler)currentEventHandler;
    }

    /**
     * Returns an array of additional <code>MouseHandler</code>s to call for a given
     * <code>MouseEvent</code>, if the current <code>MouseHandler</code> doesn't handle it. These
     * handlers will be called in ascending index order until the <code>MouseEvent</code>
     * isConsumed().
     * @return an array of additional <code>MouseHandler</code>s
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
     * Calls mousePressed on the current <code>MouseHandler</code>, and then as long as
     * the event is not consumed, continues passing the event to the
     * mousePressed method of any fallback <code>MouseHandler</code>s.
     * @param e the <code>MouseEvent</code> to process.
     */
    public void mousePressed(MouseEvent e) {
        // System.err.println("manager got mousePressed! during...");
        // new Exception().printStackTrace();
        MouseHandler mouseHandler;
        synchronized (this) {
            mouseHandler = (MouseHandler)currentEventHandler;
        }
        if (mouseHandler != null) {
            mouseHandler.mousePressed(e);
            if (!e.isConsumed()) {
                MouseHandler[] fallback = getFallbackMouseHandlers();
                for (int i = 0;(i < fallback.length) && !e.isConsumed(); i++) {
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
     * Calls mouseReleased on the current <code>MouseHandler</code>, and then as long as
     * the event is not consumed, continues passing the event to the
     * mouseReleased method of any fallback <code>MouseHandler</code>s.
     * @param e the <code>MouseEvent</code> to process.
     */
    public void mouseReleased(MouseEvent e) {
        // System.err.println("manager got mouseReleased!");
        MouseHandler mouseHandler;
        synchronized (this) {
            mouseHandler = (MouseHandler)currentEventHandler;
        }
        if (mouseHandler != null) {
            mouseHandler.mouseReleased(e);
            if (!e.isConsumed()) {
                MouseHandler[] fallback = getFallbackMouseHandlers();
                for (int i = 0;(i < fallback.length) && !e.isConsumed(); i++) {
                    fallback[i].mouseReleased(e);
                }
            }
        }
    }

    /**
     * Calls mouseClicked on the current <code>MouseHandler</code>, and then as long as
     * the event is not consumed, continues passing the event to the
     * mouseClicked method of any fallback <code>MouseHandler</code>s.
     * @param e the <code>MouseEvent</code> to process.
     */
    public void mouseClicked(MouseEvent e) {
        // System.err.println("manager got mouseClicked!");
        MouseHandler mouseHandler;
        synchronized (this) {
            mouseHandler = (MouseHandler)currentEventHandler;
        }
        if (mouseHandler != null) {
            mouseHandler.mouseClicked(e);
            if (!e.isConsumed()) {
                MouseHandler[] fallback = getFallbackMouseHandlers();
                for (int i = 0;(i < fallback.length) && !e.isConsumed(); i++) {
                    fallback[i].mouseClicked(e);
                }
            }
        }
    }

    //*************************************************************************
    //  implement the MouseMotionListener interface
    //*************************************************************************

    /**
     * Calls mouseEntered on the current <code>MouseHandler</code>, and then as long as
     * the event is not consumed, continues passing the event to the 
     * mouseEntered method of any fallback <code>MouseHandler</code>s.
     * @param e the <code>MouseEvent</code> to process.
     */
    public void mouseEntered(MouseEvent e) {
        MouseHandler mouseHandler;
        synchronized (this) {
            mouseHandler = (MouseHandler)currentEventHandler;
        }
        if (mouseHandler != null) {
            mouseHandler.mouseEntered(e);
            if (!e.isConsumed()) {
                MouseHandler[] fallback = getFallbackMouseHandlers();
                for (int i = 0;(i < fallback.length) && !e.isConsumed(); i++) {
                    fallback[i].mouseEntered(e);
                }
            }
        }
    }

    /**
     * Calls mouseExited on the current <code>MouseHandler</code>, and then as long as
     * the event is not consumed, continues passing the event to the mouseExited
     * method of any fallback <code>MouseHandler</code>s.
     * @param e the <code>MouseEvent</code> to process
     */
    public void mouseExited(MouseEvent e) {
        MouseHandler mouseHandler;
        synchronized (this) {
            mouseHandler = (MouseHandler)currentEventHandler;
        }
        if (mouseHandler != null) {
            mouseHandler.mouseExited(e);
            if (!e.isConsumed()) {
                MouseHandler[] fallback = getFallbackMouseHandlers();
                for (int i = 0;(i < fallback.length) && !e.isConsumed(); i++) {
                    fallback[i].mouseExited(e);
                }
            }
        }
    }

    /**
     * Calls mouseMoved on the current <code>MouseHandler</code>, and then as long as
     * the event is not consumed, continues passing the event to the mouseMoved
     * method of any fallback <code>MouseHandler</code>s.
     * @param e the <code>MouseEvent</code> to process
     */
    public void mouseMoved(MouseEvent e) {
        MouseHandler mouseHandler;
        synchronized (this) {
            mouseHandler = (MouseHandler)currentEventHandler;
        }
        if (mouseHandler != null) {
            mouseHandler.mouseMoved(e);
            if (!e.isConsumed()) {
                MouseHandler[] fallback = getFallbackMouseHandlers();
                for (int i = 0;(i < fallback.length) && !e.isConsumed(); i++) {
                    fallback[i].mouseMoved(e);
                }
            }
        }
    }

    /**
     * Calls mouseDragged on the current <code>MouseHandler</code>, and then as long as
     * the event is not consumed, continues passing the event to the
     * mouseDragged method of any fallback <code>MouseHandler</code>s.
     * @param e the <code>MouseEvent</code> to process.
     */
    public void mouseDragged(MouseEvent e) {
        MouseHandler mouseHandler;
        synchronized (this) {
            mouseHandler = (MouseHandler)currentEventHandler;
        }
        if (mouseHandler != null) {
            mouseHandler.mouseDragged(e);
            if (!e.isConsumed()) {
                MouseHandler[] fallback = getFallbackMouseHandlers();
                for (int i = 0;(i < fallback.length) && !e.isConsumed(); i++) {
                    fallback[i].mouseDragged(e);
                }
            }
        }
    }

}
