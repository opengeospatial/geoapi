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

import java.util.EventListener;



/**
 * The <code>AggregationListener</code> allows an implementing object to listen to
 * element addition, removal, and ordering changes in an aggregating object.
 *  
 * @author  Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public interface AggregationListener extends EventListener {
	
	/**
	 * This method is called when any elements are added, removed, 
	 * or reorderd (if supported) in the aggregating object.
	 */
	void aggregationChanged(AggregationChangeEvent event);

}
