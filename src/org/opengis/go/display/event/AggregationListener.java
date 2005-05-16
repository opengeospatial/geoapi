/**************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.go.display.event;

// J2SE direct dependencies
import java.util.EventListener;

/**
 * Allows an implementing object to listen to element addition, removal,
 * and ordering changes in an aggregating object.
 *  
 * @version $Revision$, $Date$
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 */
public interface AggregationListener extends EventListener {
	
	/**
     * Invoked when any elements are added, removed, 
	 * or reorderd (if supported) in the aggregating object.
     *
     * @param event The aggregation event.
	 */
    public void aggregationChanged(AggregationChangeEvent event);
}

