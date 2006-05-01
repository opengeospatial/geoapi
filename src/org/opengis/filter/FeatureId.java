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

// Annotations
import org.opengis.annotation.XmlElement;


/**
 * Instances of this interface represent a filter that passes only for features
 * that have one of the IDs given to this object.
 *
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Implementation specification 1.0</A>
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.0
 */
@XmlElement("FeatureId")
public interface FeatureId extends Filter {
    /**
     * Returns a {@linkplain Set} containing the IDs of {@linkplain org.opengis.feature.Feature features}
     * that will pass this filter.
     */
    @XmlElement("fid")
    Set<String> getIDs();
}
