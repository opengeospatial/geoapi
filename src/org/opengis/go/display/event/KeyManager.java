/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.go.display.event;


/**
 * Defines a common abstraction for classes that handle key events.
 * It prescribes a stack mechanism for managing <code>KeyHandler</code>s.
 * 
 * @version 0.2
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 */
public interface KeyManager extends EventManager {
    /**
     * The window will pass key events to only this <code>KeyHandler</code>,
     * until the <code>KeyHandler</code> is changed or removed.
     *
     * @param keyHandler  the current key handler.
     */
    public void enableKeyHandler(KeyHandler keyHandler);

    /**
     * Enable the given <code>KeyHandler</code>, and push it on the stack so
     * that if another <code>KeyHandler</code> gets enabled, this one will be
     * reenabled when that <code>KeyHandler</code> is removed.
     *
     * @param keyHandler  The <code>KeyHandler</code> to enable and push.
     * @see #enableKeyHandler
     */
    public void pushKeyHandler(KeyHandler keyHandler);

    /**
     * Remove the given <code>KeyHandler</code> and reinstate the
     * <code>KeyHandler</code> at the top of the stack, if any.
     *
     * @param keyHandler  the <code>KeyHandler</code> to disable and remove.
     */
    public void removeKeyHandler(KeyHandler keyHandler);

    /**
     * Replace a <code>KeyHandler</code> in the stack with another
     * <code>KeyHandler</code>.
     *
     * @param existingHandler the <code>KeyHandler</code> to be replaced.
     * @param replacementHandler the <code>KeyHandler</code> that is replacing
     *        the <code>existingHandler</code>.
     * @return <code>true</code> if <code>existingHandler</code> was found and
     *         replaced by <code>replacementHandler</code>.
     */
    public boolean replaceKeyHandler(KeyHandler existingHandler,
                                     KeyHandler replacementHandler);

    /**
     * Returns the current <code>KeyHandler</code> or <code>null</code> if no
     *         handler is set.
     *
     * @return the current <code>KeyHandler</code> or <code>null</code>.
     */
    public KeyHandler getCurrentKeyHandler();

    /**
     * Returns an array of additional <code>KeyHandler</code>s to call for a given
     * <code>KeyEvent</code>, if the current key handler doesn't handle it. These
     * handlers will be called in ascending index order until the <code>KeyEvent</code>
     * {@linkplain java.awt.event.InputEvent#isConsumed() is consumed}.
     *
     * @return The additional <code>KeyHandler</code>s.
     */
    public KeyHandler[] getFallbackKeyHandlers();
}
