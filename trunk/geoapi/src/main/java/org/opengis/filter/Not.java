/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.filter;

// Annotations
import org.opengis.annotation.XmlElement;


/**
 * Reverses the logical value of an expression.
 *
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Implementation specification 1.0</A>
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.0
 */
@XmlElement("Not")
public interface Not extends Filter {
    /**
     * The filter to reverse.
     */
    Filter getFilter();
    
    /**
     * Filter to reverse.
     * 
     * @param filter Logical value to reverse
     */
    void setFilter( Filter filter );
}
