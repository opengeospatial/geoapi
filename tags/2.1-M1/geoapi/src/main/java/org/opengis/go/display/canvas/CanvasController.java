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

import org.opengis.spatialschema.geometry.DirectPosition;

/**
 * The <code>CanvasController</code> interface serves as the token in the 
 * <code>CanvasController &lt;--&gt; Canvas</code> interaction.  When 
 * the <code>Canvas</code> enables a <code>CanvasHandler</code>, it calls
 * <code>handlerEnabled(CanvasController)</code> on the 
 * <code>CanvasHandler</code>.  The <code>CanvasController</code> may then be
 * used by the programmer to set some of the properties of the associated
 * <code>Canvas</code>.  The <code>CanvasController</code> will be active
 * until another <code>CanvasHandler</code> is enabled.
 * <p/>
 * The <code>CanvasController</code>'s methods (in an implementation-specific
 * interaction with the <code>Canvas</code>) should only set the 
 * <code>Canvas</code>'s properties <b>IFF</b> the 
 * <code>CanvasController</code> is recognized to be the active controller (the
 * controller given out by the canvas when the most recent handler was 
 * enabled).
 *
 * @author Open GIS Consortium, Inc.
 * @version $Revision: 658 $, $Date: 2006-02-23 12:09:34 +1100 (jeu., 23 févr. 2006) $
 */
public interface CanvasController {

    /**
     * Sets the position of the center pixel of the <code>Canvas</code> this
     * controller works for, if it is the active controller.
     * @see CanvasState#getCenter()
     */
    public void setCenter(DirectPosition newCenter);

    

}
