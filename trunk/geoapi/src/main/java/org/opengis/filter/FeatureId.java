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

// J2SE dependencies
import java.util.Set;

import org.opengis.annotation.XmlElement;


/**
 * Instances of this interface represent a filter that passes only the IDs given to this object.
 * <p>
 * This application of this filter for Features is well understood. For other identifiable Objects
 * such as Geometry or Records we may have to provide more thought.
 * </p>
 * 
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Implementation specification 1.0</A>
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.0
 */
@XmlElement("FeatureId")
public interface FeatureId extends Filter {
    /**
     * Returns a {@linkplain Set} containing the IDs of that will pass this filter.
     */
    @XmlElement("fid")
    Set<String> getIDs();
    
    /**
     * {@linkplain Set} containing the IDs of that will pass this filter
     * 
     *  @param ids Set of IDs that will passs
     */
    void setIDs( Set<String> ids );
}
