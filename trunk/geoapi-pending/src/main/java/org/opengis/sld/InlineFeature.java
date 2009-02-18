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

import java.util.Collection;
import org.opengis.annotation.Extension;
import org.opengis.annotation.XmlElement;
import org.opengis.feature.Feature;


/**
 * Inline Features.
 * Features are stored under a GML format.
 * 
 * @version <A HREF="http://www.opengeospatial.org/standards/sld">Implementation specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@XmlElement("InlineFeature")
public interface InlineFeature extends Source{

    /**
     * Collection of features.
     * Caution : the return type of this method may change.
     */
    //TODO : replace the Collection<Feature> by something else when possible.
    @XmlElement("FeatureCollection")
    public Collection<Collection<Feature>> features();
    
    /**
     * calls the visit method of a SLDVisitor
     *
     * @param visitor the sld visitor
     */
    @Extension
    Object accept(SLDVisitor visitor, Object extraData);

}
