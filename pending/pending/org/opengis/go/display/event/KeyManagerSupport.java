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

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The <code>KeyManagerSupport</code> class is a helper or
 * delegate for KeyHandler management.
 *
 * @author  Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public class KeyManagerSupport extends EventManagerSupport implements KeyManager, KeyListener {

    //*************************************************************************
    //  implement the KeyManager interface
    //*************************************************************************

    /**
     * Enables the given <code>KeyHandler</code>.  This <code>KeyManager</code>
     * then passes events only to the given <code>KeyHandler</code> until it is
     * either removed or another <code>KeyHandler</code> is enabled.
     * @param keyHandler  the new <code>KeyHandler</code> to enable.
     */
    public synchronized void enableKeyHandler(KeyHandler keyHandler) {
        super.enableEventHandler(keyHandler);
    }

    /**
     * Enables the given <code>KeyHandler</code>, and pushes it on the stack so that if
     * another <code>KeyHandler</code> gets enabled, this one will be reenabled when
     * that <code>KeyHandler</code> is removed.
     * @param keyHandler  The <code>KeyHandler</code> to enable and push.
     */
    public synchronized void pushKeyHandler(KeyHandler keyHandler) {
        super.pushEventHandler(keyHandler);
    }

    /**
     * Removes the given <code>KeyHandler</code> and reinstates the <code>KeyHandler</code> at the
     * top of the stack, if any.
     * @param keyHandler  the <code>KeyHandler</code> to disable and remove.
     */
    public synchronized void removeKeyHandler(KeyHandler keyHandler) {
        super.removeEventHandler(keyHandler);
    }

    /**
     * Replaces a <code>KeyHandler</code> in the stack with another <code>KeyHandler</code>.
     * @param existingHandler the <code>KeyHandler</code> to be replaced.
     * @param replacementHandler the <code>KeyHandler</code> that is replacing the.
     * @return true if existingHandler was found and replaced
     * by replacementHandler.
     */
    public synchronized boolean replaceKeyHandler(
        KeyHandler existingHandler,
        KeyHandler replacementHandler) {
        return super.replaceEventHandler(existingHandler, replacementHandler);
    }

    /**
     * Returns the current <code>KeyHandler</code> or null if none.
     * @return the current <code>KeyHandler</code> or null if none
     */
    public KeyHandler getCurrentKeyHandler() {
        return (KeyHandler)currentEventHandler;
    }

    /**
     * Returns an array of additional <code>KeyHandler</code>s to call for a given
     * <code>KeyEvent</code>, if the current event handler doesn't handle it. These
     * handlers will be called in ascending index order until the <code>KeyEvent</code>
     * isConsumed().
     * @return an array of additional <code>KeyHandler</code>s
     */
    public synchronized KeyHandler[] getFallbackKeyHandlers() {
        KeyHandler[] fallbackHandlers = new KeyHandler[0];
        if (eventStack != null || !eventStack.isEmpty()) {
            int size = eventStack.size();
            if (eventStack.get(0) == currentEventHandler) {
                size -= 1;
            }
            fallbackHandlers = new KeyHandler[size];
            int j = 0;
            for (int i = size - 1; i >= 0; i--, j++) {
                fallbackHandlers[j] = (KeyHandler)eventStack.get(i);
            }
        }
        return fallbackHandlers;
    }

    //*************************************************************************
    //  implement the KeyListener interface
    //*************************************************************************

    public void keyTyped(KeyEvent e) {
        // System.err.println("manager got keyPressed! during...");
        // new Exception().printStackTrace();
        KeyHandler keyHandler;
        synchronized (this) {
            keyHandler = (KeyHandler)currentEventHandler;
        }
        if (keyHandler != null) {
            keyHandler.keyTyped(e);
            if (!e.isConsumed()) {
                KeyHandler[] fallback = getFallbackKeyHandlers();
                for (int i = 0;(i < fallback.length) && !e.isConsumed(); i++) {
                    // EXPLAIN(CCP): Even though the array returned by the
                    // method getFallbackKeyHandlers() does NOT include the
                    // current key hander, we still have to check here,
                    // because sometimes the call to keyHandler.
                    //    keyTyped(e) above actually causes a NEW handler
                    // to become current.  Thus, since we've already stored off
                    // the OLD current handler into a local variable, we need
                    // to check it as well.
                    if (keyHandler != fallback[i]) {
                        fallback[i].keyTyped(e);
                    }
                }
            }
        }
    }

    /**
     * Calls keyPressed on the current <code>KeyHandler</code>, and then as long as
     * the event is not consumed, continues passing the event to the
     * keyPressed method of any fallback <code>KeyHandler</code>s.
     * @param e the <code>KeyEvent</code> to process.
     */
    public void keyPressed(KeyEvent e) {
        // System.err.println("manager got keyPressed! during...");
        // new Exception().printStackTrace();
        KeyHandler keyHandler;
        synchronized (this) {
            keyHandler = (KeyHandler)currentEventHandler;
        }
        if (keyHandler != null) {
            keyHandler.keyPressed(e);
            if (!e.isConsumed()) {
                KeyHandler[] fallback = getFallbackKeyHandlers();
                for (int i = 0;(i < fallback.length) && !e.isConsumed(); i++) {
                    // EXPLAIN(CCP): Even though the array returned by the
                    // method getFallbackKeyHandlers() does NOT include the
                    // current key hander, we still have to check here,
                    // because sometimes the call to keyHandler.
                    //    keyPressed(e) above actually causes a NEW handler
                    // to become current.  Thus, since we've already stored off
                    // the OLD current handler into a local variable, we need
                    // to check it as well.
                    if (keyHandler != fallback[i]) {
                        fallback[i].keyPressed(e);
                    }
                }
            }
        }
    }

    /**
     * Calls keyReleased on the current <code>KeyHandler</code>, and then as long as
     * the event is not consumed, continues passing the event to the
     * keyReleased method of any fallback <code>KeyHandler</code>s.
     * @param e the <code>KeyEvent</code> to process.
     */
    public void keyReleased(KeyEvent e) {
        // System.err.println("manager got keyReleased!");
        KeyHandler keyHandler;
        synchronized (this) {
            keyHandler = (KeyHandler)currentEventHandler;
        }
        if (keyHandler != null) {
            keyHandler.keyReleased(e);
            if (!e.isConsumed()) {
                KeyHandler[] fallback = getFallbackKeyHandlers();
                for (int i = 0;(i < fallback.length) && !e.isConsumed(); i++) {
                    fallback[i].keyReleased(e);
                }
            }
        }
    }
}
