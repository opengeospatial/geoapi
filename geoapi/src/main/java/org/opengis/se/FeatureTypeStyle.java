/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2008 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.se;

import org.opengis.annotation.XmlElement;

/**
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.2
 */
@XmlElement("FeatureTypeStyle")
public interface FeatureTypeStyle extends Style {

    /**
     * Returns the name of the feature type that this style is meant to act
     * upon.  This may return null if a style can operate on many different
     * feature types.
     * @return the name of the feature type that this style is meant 
     * to act upon.
     */
    @XmlElement("FeatureTypeName")
    String getFeatureTypeName();

    /**
     * Sets the name of the feature type that this style is meant to act upon.
     * See {@link #getFeatureTypeName} for details.
     * @param featureTypeName : new name of the feature type that this 
     * style is meant to act upon
     */
    @XmlElement("FeatureTypeName")
    void setFeatureTypeName(String featureTypeName);
}
