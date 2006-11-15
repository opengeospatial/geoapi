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

// J2SE direct dependencies
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Defines a common abstraction whose implementations can associate
 * different mouse handlers with a Component to switch mouse handling
 * states easily.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version $Revision: 658 $, $Date: 2006-02-23 12:09:34 +1100 (jeu., 23 févr. 2006) $
 */

public interface MouseHandler extends EventHandler, MouseListener, MouseMotionListener {

}

