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
 * Defines a common abstraction for classes that handle mouse events.
 * It prescribes a stack mechanism for managing <code>MouseHandler</code>s.
 * 
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version $Revision$, $Date$
 */
public interface MouseManager extends EventManager {

    /**
     * The window will pass mouse events to only this <code>MouseHandler</code>,
     * until the <code>MouseHandler</code> is changed or removed.
     *
     * @param mouseHandler  the current mouse handler.
     */
    public void enableMouseHandler(MouseHandler mouseHandler);

    /**
     * Enable the given <code>MouseHandler</code>, and push it on the stack
     * so that if another <code>MouseHandler</code> gets enabled, this one
     * will be reenabled when that <code>MouseHandler</code> is removed.
     *
     * @param mouseHandler  The <code>MouseHandler</code> to enable and push.
     * @see #enableMouseHandler
     */
    public void pushMouseHandler(MouseHandler mouseHandler);

    /**
     * Remove the given <code>MouseHandler</code> and reinstate the
     * <code>MouseHandler</code> at the top of the stack, if any.
     *
     * @param mouseHandler  the <code>MouseHandler</code> to disable and remove.
     */
    public void removeMouseHandler(MouseHandler mouseHandler);

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
    public boolean replaceMouseHandler(MouseHandler existingHandler,
        MouseHandler replacementHandler);

    /**
     * Returns the current <code>MouseHandler</code> or <code>null</code> if none.
     *
     * @return the current <code>MouseHandler</code> or <code>null</code> if none.
     */
    public MouseHandler getCurrentMouseHandler();

    /**
     * Returns an array of additional <code>MouseHandler</code>s to call for a given
     * <code>MouseEvent</code>, if the current mouse handler doesn't handle it. These
     * handlers will be called in ascending index order until the <code>MouseEvent</code>
     * {@linkplain java.awt.event.InputEvent#isConsumed() is consumed}.
     *
     * @return the array of additional <code>MouseHandler</code>s.
     */
    public MouseHandler[] getFallbackMouseHandlers();

}

