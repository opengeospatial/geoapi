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
 * The <code>MouseManager</code> defines a common abstraction for classes that
 * handle mouse events. It prescribes a stack mechanism for managing
 * <code>MouseHandler</code>s.
 * 
 * @author  Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public interface MouseManager extends EventManager {

    /**
     * Enables the given <code>MouseHandler</code>.  This <code>MouseManager</code>
     * then passes events only to the given <code>MouseHandler</code> until it is
     * either removed or another <code>MouseHandler</code> is enabled.
     * @param mouseHandler  the new <code>MouseHandler</code> to enable.
     */
    public void enableMouseHandler(MouseHandler mouseHandler);

    /**
     * Enables the given <code>MouseHandler</code>, and pushes it on the stack so that if
     * another <code>MouseHandler</code> gets enabled, this one will be reenabled when
     * that <code>MouseHandler</code> is removed.
     * @param mouseHandler  The <code>MouseHandler</code> to enable and push.
     * @see #enableMouseHandler
     */
    public void pushMouseHandler(MouseHandler mouseHandler);

    /**
     * Removes the given <code>MouseHandler</code> and reinstate the <code>MouseHandler</code> at the
     * top of the stack, if any.
     * @param mouseHandler  the <code>MouseHandler</code> to disable and remove.
     */
    public void removeMouseHandler(MouseHandler mouseHandler);

    /**
     * Replaces a <code>MouseHandler</code> in the stack with another <code>MouseHandler</code>.
     * @param existingHandler the <code>MouseHandler</code> to be replaced.
     * @param replacementHandler the <code>MouseHandler</code> that is replacing the.
     * @return true if existingHandler was found and replaced by replacementHandler.
     */
    public boolean replaceMouseHandler(
        MouseHandler existingHandler,
        MouseHandler replacementHandler);

    /**
     * Returns the current <code>MouseHandler</code> or null if none.
     * @return the current <code>MouseHandler</code> or null if none.
     */
    public MouseHandler getCurrentMouseHandler();

    /**
     * Returns an array of additional <code>MouseHandler</code>s to call for a given
     * <code>MouseEvent</code>, if the current <code>MouseHandler</code> doesn't handle it. These
     * handlers will be called in ascending index order until the <code>MouseEvent</code>
     * isConsumed().
     * @return an array of additional <code>MouseHandler</code>s
     */
    public MouseHandler[] getFallbackMouseHandlers();

}
