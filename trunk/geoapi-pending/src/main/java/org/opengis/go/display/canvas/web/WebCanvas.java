/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.go.display.canvas.web;

import java.awt.Graphics;

import org.opengis.go.display.canvas.Canvas;


/**
 * The <code>WebCanvas</code> interface extends the functionality of <code>Canvas</code> and adds a
 * specific method for rendering which is conspicuous in its absence from the <code>Canvas</code>
 * interface.
 * @author crossley
 */
public interface WebCanvas extends Canvas {
    /**
     * Renders this <code>WebCanvas</code>'s map image using the given <code>Graphics</code> object.
     * The immediate use case is for creating WMS (or other webapp) map images using the
     * <code>Graphics</code> from a <code>BufferedImage</code>, though any case with a need for
     * on-demand rendering could be satisfied with this method
     * @param g
     */
    void render(Graphics g);
}
