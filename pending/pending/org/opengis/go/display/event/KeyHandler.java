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

import java.awt.event.KeyListener;


/**
 * <code>KeyHandler</code> defines a common abstraction whose implementations
 * can associate different key handlers with a Component to switch key
 * handling states easily.
 *
 * @author  Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public interface KeyHandler extends EventHandler, KeyListener {

}
