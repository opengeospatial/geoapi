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

import java.awt.event.KeyEvent;

/**
 * The <code>GraphicKeyEvent</code> interface provides a common abstraction for
 * the various event objects pertaining to key events on a
 * <code>Graphic</code>.
 * 
 * @author  Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public interface GraphicKeyEvent extends GraphicEvent {
	/**
	 * Returns the KeyEvent that this event references.
	 * @return the KeyEvent referenced by this event.
	 */
	public KeyEvent getKeyEvent();
}
