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

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


/**
 * <code>MouseHandler</code> defines a common abstraction whose implementations
 * can associate different mouse handlers with a Component to switch mouse
 * handling states easily.
 *
 * @author  Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public interface MouseHandler extends EventHandler, MouseListener, MouseMotionListener {

}
