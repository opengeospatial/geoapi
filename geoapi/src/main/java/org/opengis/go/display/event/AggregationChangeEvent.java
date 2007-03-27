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

import org.opengis.go.display.primitive.Graphic;


/**
 * Provides a mechanism for notification of additions, removals, and
 * reorderings of elements in an aggregation.
 * 
 * @version $Revision: 658 $, $Date: 2006-02-23 12:09:34 +1100 (jeu., 23 févr. 2006) $
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 */
public interface AggregationChangeEvent {
    /**
     * Flag for one or more elements added to the aggregation.
     */
    int ELEMENT_ADDED = 0;

    /**
     * Flag for one or more elements removed from the aggregation.
     */
    int ELEMENT_REMOVED = 1;

    /**
     * Flag for the case of the element order changing within the aggregation.
     */
    int ELEMENTS_REORDERED = 2;

    /**
     * Get the ID flag for this event.
     *
     * @return The event type. One of the {@link #ELEMENT_ADDED},
     *         {@link #ELEMENT_REMOVED} or {@link #ELEMENTS_REORDERED}
     *         constants.
     */
    int getID();
    
    /**
     * Gets the <code>Graphic</code> elements pertinent to the event.
     * @return an array of <code>Graphic</code>s that were affected by the event,
     *         or an empty array.
     */
    Graphic[] getElements();
}
