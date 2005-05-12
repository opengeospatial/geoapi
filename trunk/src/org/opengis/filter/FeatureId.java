/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.filter;

// J2SE dependencies
import java.util.Set;

// Annotations
import org.opengis.annotation.XmlElement;
import static org.opengis.annotation.Obligation.*;


/**
 * Instances of this interface represent a filter that passes only for features
 * that have one of the IDs given to this object.
 *
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/02-059.pdf">Implementation specification 1.0</A>
 * @since GeoAPI 1.1
 */
@XmlElement(name="FeatureId")
public interface FeatureId extends Filter {
    /**
     * Returns a {@linkplain Set} containing the IDs of {@linkplain org.opengis.feature.Feature features}
     * that will pass this filter.
     */
    @XmlElement(name="fid", obligation=MANDATORY)
    Set<String> getIDs();
}
