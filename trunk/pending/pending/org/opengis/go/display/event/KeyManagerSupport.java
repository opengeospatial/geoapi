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
     * The window will pass key events to only this KeyHandler, until
     * the KeyHandler is changed or removed.
     * @param keyHandler  the new key handler.
     */
    public synchronized void enableKeyHandler(KeyHandler keyHandler) {
        super.enableEventHandler(keyHandler);
    }

    /**
     * Enable the given KeyHandler, and push it on the stack so that if
     * another KeyHandler gets enabled, this one will be reenabled when
     * that KeyHandler is removed.
     * @param keyHandler  The KeyHandler to enable and push.
     */
    public synchronized void pushKeyHandler(KeyHandler keyHandler) {
        super.pushEventHandler(keyHandler);
    }

    /**
     * Remove the given KeyHandler and reinstate the KeyHandler at the
     * top of the stack, if any.
     * @param keyHandler  the KeyHandler to disable and remove.
     */
    public synchronized void removeKeyHandler(KeyHandler keyHandler) {
        super.removeEventHandler(keyHandler);
    }

    /**
     * Replace a KeyHandler in the stack with another KeyHandler.
     * @param existingHandler the KeyHandler to be replaced.
     * @param replacementHandler the KeyHandler that is replacing the.
     * @return true if existingHandler was found and replaced
     * existingHandler.
     */
    public synchronized boolean replaceKeyHandler(KeyHandler existingHandler, KeyHandler replacementHandler) {
        return super.replaceEventHandler(existingHandler, replacementHandler);
    }

    /**
     * @return the current KeyHandler or null if none
     */
    public KeyHandler getCurrentKeyHandler() {
        return (KeyHandler)currentEventHandler;
    }

    /**
     * @return an array of additional KeyHandlers to call for a given
     * KeyEvent, if the current key handler doesn't handle it. These
     * handlers will be called in ascending index order until the KeyEvent
     * isConsumed().
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

                for (int i = 0; (i < fallback.length) &&!e.isConsumed();
                        i++) {

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
     * Calls keyPressed on the current key handler, and then as long as
     * the event is not consumed, continues passing the event to the
     * keyPressed method of any fallback key handlers.
     * @param e the KeyEvent to process.
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

                for (int i = 0; (i < fallback.length) &&!e.isConsumed(); i++) {

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
     * Calls keyReleased on the current key handler, and then as long as
     * the event is not consumed, continues passing the event to the
     * keyReleased method of any fallback key handlers.
     * @param e the KeyEvent to process.
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

                for (int i = 0; (i < fallback.length) &&!e.isConsumed(); i++) {
                    fallback[i].keyReleased(e);
                }
            }
        }
    }
}
