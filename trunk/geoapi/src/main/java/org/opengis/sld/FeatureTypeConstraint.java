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
package org.opengis.sld;

import java.util.List;
import org.opengis.annotation.Extension;
import org.opengis.annotation.XmlElement;
import org.opengis.feature.type.Name;
import org.opengis.filter.Filter;

/**
 * A FeatureTypeConstraint element is used to identify a feature type by a well-known
 * name, using the FeatureTypeName element. Any positive number of
 * FeatureTypeConstraints may be used to define the features of a layer, though all
 * FeatureTypeConstraints in a UserLayer must originate from the same WFS source.
 * 
 * @version <A HREF="http://www.opengeospatial.org/standards/sld">Implementation specification 1.1.0</A>
 * 
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
public interface FeatureTypeConstraint extends Constraint{

    /**
     * FeatureType name.
     */
    @XmlElement("FeatureTypeName")
    public Name getFeatureTypeName();

    /**
     * Filter to apply on feature collection.
     */
    @XmlElement("Filter")
    public Filter getFilter();

    @XmlElement("Extent")
    public List<Extent> getExtent();
    
    /**
     * calls the visit method of a SLDVisitor
     *
     * @param visitor the sld visitor
     */
    @Extension
    Object accept(SLDVisitor visitor, Object extraData);
    
}
