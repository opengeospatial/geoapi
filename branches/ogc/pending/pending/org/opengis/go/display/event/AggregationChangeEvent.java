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

/**
 * The <code>AggregationChangeEvent</code> interface provides a mechanism for notification
 * of additions, removals, and reorderings of elements in an aggregation.
 * 
 * @author  Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 */
public interface AggregationChangeEvent {
	
	/**
	 * Flag for one or more elements added to the aggregation.
	 */
	public static final int ELEMENT_ADDED = 0;
    
	/**
	 * Flag for one or more elements removed from the aggregation.
	 */
	public static final int ELEMENT_REMOVED = 1;
    
	/**
	 * Flag for the case of the element order changing within the aggregation.
	 */
	public static final int ELEMENTS_REORDERD = 2;
	
	/**
	 * Get the ID flag for this event.
	 */
	public int getID();
    
}
