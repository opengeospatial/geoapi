/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.go.display.event;

// J2SE dependencies
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * A helper or delegate for {@link KeyHandler} management.
 * 
 * @version 0.2
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 */
public class KeyManagerSupport extends EventManagerSupport implements KeyManager, KeyListener {

    /**
     * Construct a default key manager.
     */
    public KeyManagerSupport() {
    }

    //*************************************************************************
    //  implement the KeyManager interface
    //*************************************************************************

    /**
     * The window will pass key events to only this <code>KeyHandler</code>,
     * until the <code>KeyHandler</code> is changed or removed.
     *
     * @param keyHandler  the new key handler.
     */
    public void enableKeyHandler(KeyHandler keyHandler) {
        super.enableEventHandler(keyHandler);
    }

    /**
     * Enable the given <code>KeyHandler</code>, and push it on the stack so
     * that if another <code>KeyHandler</code> gets enabled, this one will be
     * reenabled when that <code>KeyHandler</code> is removed.
     *
     * @param keyHandler  The <code>KeyHandler</code> to enable and push.
     */
    public void pushKeyHandler(KeyHandler keyHandler) {
        super.pushEventHandler(keyHandler);
    }

    /**
     * Remove the given <code>KeyHandler</code> and reinstate the
     * <code>KeyHandler</code> at the top of the stack, if any.
     *
     * @param keyHandler  the <code>KeyHandler</code> to disable and remove.
     */
    public void removeKeyHandler(KeyHandler keyHandler) {
        super.removeEventHandler(keyHandler);
    }

    /**
     * Replace a <code>KeyHandler</code> in the stack with another <code>KeyHandler</code>.
     *
     * @param existingHandler the <code>KeyHandler</code> to be replaced.
     * @param replacementHandler the <code>KeyHandler</code> that is replacing
     *        the <code>existingHandler</code>.
     *
     * @return <code>true</code> if <code>existingHandler</code> was found and
     *         replaced by <code>replacementHandler</code>.
     */
    public synchronized boolean replaceKeyHandler(KeyHandler existingHandler,
                                                  KeyHandler replacementHandler) {
        return super.replaceEventHandler(existingHandler, replacementHandler);
    }

    /**
     * Returns the current <code>KeyHandler</code> or <code>null</code> if none.
     *
     * @return the current <code>KeyHandler</code> or <code>null</code> if none.
     */
    public KeyHandler getCurrentKeyHandler() {
        return (KeyHandler)currentEventHandler;
    }

    /**
     * Returns an array of additional <code>KeyHandler</code>s to call for a given
     * <code>KeyEvent</code>, if the current key handler doesn't handle it. These
     * handlers will be called in ascending index order until the <code>KeyEvent</code>
     * {@linkplain java.awt.event.InputEvent#isConsumed() is consumed}.
     *
     * @return The additional <code>KeyHandler</code>s.
     *
     * @revisit This code duplicate {@link EventManagerSupport#getFallbackEventHandlers()}
     *          except for the array type (could be handle with a {@link Class} argument)
     *          and the <code>eventStack.peek()</code> which has been replaced by
     *          <code>eventStack.get(0)</code> (why?).
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
    
    /**
     * Calls {@link KeyHandler#keyTyped keyTyped} on the current key handler,
     * and then as long as the event is not consumed, continues passing the event
     * to the <code>keyTyped</code> method of any fallback key handlers.
     *
     * @param e the <code>KeyEvent</code> to process.
     */
    public void keyTyped(final KeyEvent e) {

        // System.err.println("manager got keyPressed! during...");
        // new Exception().printStackTrace();
        final KeyHandler keyHandler;

        synchronized (this) {
            keyHandler = (KeyHandler)currentEventHandler;
        }

        if (keyHandler != null) {
            keyHandler.keyTyped(e);

            if (!e.isConsumed()) {
                KeyHandler[] fallback = getFallbackKeyHandlers();

                for (int i = 0; (i < fallback.length) && !e.isConsumed(); i++) {

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
     * Calls {@link KeyHandler#keyPressed keyPressed} on the current key handler,
     * and then as long as the event is not consumed, continues passing the event
     * to the <code>keyPressed</code> method of any fallback key handlers.
     *
     * @param e the <code>KeyEvent</code> to process.
     */
    public void keyPressed(final KeyEvent e) {

        // System.err.println("manager got keyPressed! during...");
        // new Exception().printStackTrace();
        final KeyHandler keyHandler;

        synchronized (this) {
            keyHandler = (KeyHandler)currentEventHandler;
        }

        if (keyHandler != null) {
            keyHandler.keyPressed(e);

            if (!e.isConsumed()) {
                KeyHandler[] fallback = getFallbackKeyHandlers();

                for (int i = 0; (i < fallback.length) && !e.isConsumed(); i++) {

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
     * Calls {@link KeyHandler#keyReleased keyReleased} on the current key handler,
     * and then as long as the event is not consumed, continues passing the event
     * to the <code>keyReleased</code> method of any fallback key handlers.
     *
     * @param e the <code>KeyEvent</code> to process.
     *
     * @revisit Why this implementation do not performs the same safety check than
     *          other implementations? (<code>if (keyHandler != fallback[i])</code>)
     */
    public void keyReleased(final KeyEvent e) {

        // System.err.println("manager got keyReleased!");
        final KeyHandler keyHandler;

        synchronized (this) {
            keyHandler = (KeyHandler)currentEventHandler;
        }

        if (keyHandler != null) {
            keyHandler.keyReleased(e);

            if (!e.isConsumed()) {
                KeyHandler[] fallback = getFallbackKeyHandlers();

                for (int i = 0; (i < fallback.length) && !e.isConsumed(); i++) {
                    fallback[i].keyReleased(e);
                }
            }
        }
    }
}
