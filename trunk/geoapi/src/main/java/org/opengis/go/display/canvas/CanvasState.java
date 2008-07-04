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
package org.opengis.go.display.canvas;

import org.opengis.geometry.DirectPosition;


/**
 * Instances of this interface (and its sub-interfaces) describe the current
 * state of a <code>Canvas</code>.  The information contained by instances
 * of this interface should only describe the viewing area or volume of the
 * Canvas and should not contain any state information regarding the data
 * contained within it.
 * <p>
 * When an instance of this class is returned from <code>Canvas</code>
 * methods, a "snapshot" of the current state of the Canvas is taken
 * and the values will never change (even if the Canvas changes state).
 *
 * @author Open GIS Consortium, Inc.
 *
 * @deprecated Moved to {@link org.opengis.display.canvas}.
 */
@Deprecated
public interface CanvasState {
    /**
     * Returns the title of the <code>Canvas</code>.
     */
    String getTitle();

    /**
     * Returns the position of the center pixel of this Canvas.
     */
    DirectPosition getCenter();

    /**
     * Returns a copy of this state object. The implementation should also clone
     * the object returned by <code>getCanvasParameterAccessor()</code>.
     *
     * @deprecated This is left to implementor.
     */
    @Deprecated
    CanvasState clone() throws CloneNotSupportedException;

    /**
     * Determines if the given object is the same type of Canvas state object
     * and has values equal to this one. As part of the test, the implementation
     * should also test equivalency of the object returned by
     * <code>getCanvasParameterAccessor()</code>.
     *
     * @deprecated This is left to implementor.
     */
    ///@Override
    @Deprecated
    boolean equals(Object object);
}
