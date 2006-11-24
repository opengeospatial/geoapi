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
package org.opengis.filter.identity;

import org.opengis.annotation.XmlElement;
import org.opengis.feature.Feature;
import org.opengis.filter.identity.Identifier;


/**
 * Feature identifier.
 * <p>
 * Features are identified as strings. 
 * </p>
 * 
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Implementation specification 1.0</A>
 * @author Chris Dillard (SYS Technologies)
 * @author Justin Deoliveira, The Open Planning Project
 * @since GeoAPI 2.0
 */
@XmlElement("FeatureId")
public interface FeatureId extends Identifier<String,Feature> {
    
	/**
	 * The identifier value, which is a string.
	 */
    @XmlElement("fid")
    String getID();
    
    /**
     * Evaluates the identifer value against the given feature.
     * 
     * @param feature The feature to be tested.
     * 
     * @return <code>true</code> if a match, otherwise <code>false</code>
     */
    boolean matches( Feature feature );
    
}
