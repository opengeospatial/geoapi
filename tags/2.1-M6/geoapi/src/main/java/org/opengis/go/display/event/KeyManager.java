/**************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.go.display.event;


/**
 * Defines a common abstraction for classes that handle key events.
 * It prescribes a stack mechanism for managing <code>KeyHandler</code>s.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version $Revision: 658 $, $Date: 2006-02-23 12:09:34 +1100 (jeu., 23 févr. 2006) $
 */
public interface KeyManager extends EventManager {
    /**
     * Enables the given <code>KeyHandler</code>.  This <code>KeyManager</code>
     * then passes events only to the given <code>KeyHandler</code> until it is
     * either removed or another <code>KeyHandler</code> is enabled.
     * @param keyHandler  the new <code>KeyHandler</code> to enable.
     */
    void enableKeyHandler(KeyHandler keyHandler);

    /**
     * Enables the given <code>KeyHandler</code>, and pushes it on the stack so that if
     * another <code>KeyHandler</code> gets enabled, this one will be reenabled when
     * that <code>KeyHandler</code> is removed.
     * @param keyHandler  The <code>KeyHandler</code> to enable and push.
     * @see #enableKeyHandler
     */
    void pushKeyHandler(KeyHandler keyHandler);

    /**
     * Removes the given <code>KeyHandler</code> and reinstates the <code>KeyHandler</code> at the
     * top of the stack, if any.
     * @param keyHandler  the <code>KeyHandler</code> to disable and remove.
     */
    void removeKeyHandler(KeyHandler keyHandler);

    /**
     * Replaces a <code>KeyHandler</code> in the stack with another <code>KeyHandler</code>.
     * @param existingHandler the <code>KeyHandler</code> to be replaced.
     * @param replacementHandler the <code>KeyHandler</code> that is replacing
     *        the <code>existingHandler</code>.
     * @return <code>true</code> if <code>existingHandler</code> was found and
     *         replaced by <code>replacementHandler</code>.
     */
    boolean replaceKeyHandler(KeyHandler existingHandler, KeyHandler replacementHandler);

    /**
     * Returns the current <code>KeyHandler</code> or {@code null} if no
     *         handler is set.
     *
     * @return the current <code>KeyHandler</code> or {@code null}.
     */
    KeyHandler getCurrentKeyHandler();

    /**
     * Returns an array of additional <code>KeyHandler</code>s to call for a given
     * <code>KeyEvent</code>, if the current event handler doesn't handle it. These
     * handlers will be called in ascending index order until the <code>KeyEvent</code>
     * {@linkplain java.awt.event.InputEvent#isConsumed() is consumed}.
     *
     * @return The additional <code>KeyHandler</code>s.
     */
    KeyHandler[] getFallbackKeyHandlers();
}
