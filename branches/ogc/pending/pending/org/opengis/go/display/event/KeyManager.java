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

/**
 * The <code>KeyManager</code> defines a common abstraction for classes that
 * handle key events. It prescribes a stack mechanism for managing
 * <code>KeyHandler</code>s.
 *
 * @author  Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public interface KeyManager extends EventManager {

    /**
     * The window will pass key events to only this KeyHandler, until
     * the KeyHandler is changed or removed.
     * @param keyHandler  the current key handler.
     */
    public void enableKeyHandler(KeyHandler keyHandler);

    /**
     * Enable the given KeyHandler, and push it on the stack so that if
     * another KeyHandler gets enabled, this one will be reenabled when
     * that KeyHandler is removed.
     * @param keyHandler  The KeyHandler to enable and push.
     * @see #enableKeyHandler
     */
    public void pushKeyHandler(KeyHandler keyHandler);

    /**
     * Remove the given KeyHandler and reinstate the KeyHandler at the
     * top of the stack, if any.
     * @param keyHandler  the KeyHandler to disable and remove.
     */
    public void removeKeyHandler(KeyHandler keyHandler);

    /**
     * Replace a KeyHandler in the stack with another KeyHandler.
     * @param existingHandler the KeyHandler to be replaced.
     * @param replacementHandler the KeyHandler that is replacing the
     * existingHandler.
     * @return true if existingHandler was found and replaced
     * existingHandler.
     */
    public boolean replaceKeyHandler(KeyHandler existingHandler,
                                       KeyHandler replacementHandler);

    /**
     * Returns the current KeyHandler or null if no handler is set.
     * @return the current KeyHandler or null.
     */
    public KeyHandler getCurrentKeyHandler();

    /**
     * @return an array of additional KeyHandlers to call for a given
     * KeyEvent, if the current key handler doesn't handle it. These
     * handlers will be called in ascending index order until the KeyEvent
     * isConsumed().
     */
    public KeyHandler[] getFallbackKeyHandlers();
}
