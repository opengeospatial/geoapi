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

/**
 * The one method in this interface is called by a <code>Canvas</code> when
 * its state has changed.
 *
 * @author Open GIS Consortium, Inc.
 * @version $Revision: 660 $, $Date: 2006-02-23 14:35:47 +1100 (jeu., 23 févr. 2006) $
 */
public interface CanvasListener {

    /**
     * This method is called by a <code>Canvas</code> when its state has
     * changed.
     * @param canvas The Canvas whose state has changed
     * @param newState The state of the canvas (for convenience)
     */
    public void canvasChanged(Canvas canvas, CanvasState newState);

}
