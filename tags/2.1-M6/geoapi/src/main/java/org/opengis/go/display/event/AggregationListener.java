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

import java.util.EventListener;


/**
 * Allows an implementing object to listen to element addition, removal,
 * and ordering changes in an aggregating object.
 *  
 * @version $Revision: 658 $, $Date: 2006-02-23 12:09:34 +1100 (jeu., 23 févr. 2006) $
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 */
public interface AggregationListener extends EventListener {	
    /**
     * Invoked when any elements are added, removed, 
     * or reorderd (if supported) in the aggregating object.
     *
     * @param event The aggregation event.
     */
    void aggregationChanged(AggregationChangeEvent event);
}
