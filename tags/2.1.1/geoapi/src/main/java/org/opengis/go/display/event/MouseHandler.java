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

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


/**
 * Defines a common abstraction whose implementations can associate
 * different mouse handlers with a Component to switch mouse handling
 * states easily.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 */

public interface MouseHandler extends EventHandler, MouseListener, MouseMotionListener {

}
