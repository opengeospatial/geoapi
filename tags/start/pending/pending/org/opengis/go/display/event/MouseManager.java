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
     * The window will pass mouse events to only this MouseHandler, until
     * the MouseHandler is changed or removed.
     * @param mouseHandler  the current mouse handler.
     */
    public void enableMouseHandler(MouseHandler mouseHandler);

    /**
     * Enable the given MouseHandler, and push it on the stack so that if
     * another MouseHandler gets enabled, this one will be reenabled when
     * that MouseHandler is removed.
     * @param mouseHandler  The MouseHandler to enable and push.
     * @see #enableMouseHandler
     */
    public void pushMouseHandler(MouseHandler mouseHandler);

    /**
     * Remove the given MouseHandler and reinstate the MouseHandler at the
     * top of the stack, if any.
     * @param mouseHandler  the MouseHandler to disable and remove.
     */
    public void removeMouseHandler(MouseHandler mouseHandler);

    /**
     * Replace a MouseHandler in the stack with another MouseHandler.
     * @param existingHandler the MouseHandler to be replaced.
     * @param replacementHandler the MouseHandler that is replacing the
     * existingHandler.
     * @return true if existingHandler was found and replaced
     * existingHandler.
     */
    public boolean replaceMouseHandler(MouseHandler existingHandler,
                                       MouseHandler replacementHandler);

    /**
     * @return the current MouseHandler or null if none.
     */
    public MouseHandler getCurrentMouseHandler();

    /**
     * @return an array of additional MouseHandlers to call for a given
     * MouseEvent, if the current mouse handler doesn't handle it. These
     * handlers will be called in ascending index order until the MouseEvent
     * isConsumed().
     */
    public MouseHandler[] getFallbackMouseHandlers();
}
