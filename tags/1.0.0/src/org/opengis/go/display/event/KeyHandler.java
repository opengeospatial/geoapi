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

// J2SE direct dependencies
import java.awt.event.KeyListener;

/**
 * Defines a common abstraction whose implementations can associate different
 * key handlers with a Component to switch key handling states easily.
 *
 * @version $Revision$, $Date$
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 */

public interface KeyHandler extends EventHandler, KeyListener {

}


