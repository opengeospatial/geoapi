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
import org.opengis.style.Style;


/**
 * Since a layer is defined as a collection of potentially mixed-type features, the UserLayer
 * element must provide the means to identify the features to be used. 
 * 
 * @version <A HREF="http://www.opengeospatial.org/standards/sld">Implementation specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@XmlElement("UserLayer")
public interface UserLayer extends Layer{

    /**
     * All features to be rendered are assumed to be fetched from a Web Feature Server (WFS) or a Web
     * Coverage Service (WCS, in which case the term “features” is used loosely). Alternatively
     * they can be supplied in-line in the SLD document. This alternative is only recommended
     * for small numbers of features of transient nature.
     * 
     * @return InlineFeature or RemoteOWS
     */
    @XmlElement("InlineFeature,RemoteOWS")
    public Source getSource();

    /**
     * Constraints to apply on the features.
     * 
     * @return LayerFeatureConstraints or LayerCoverageConstraints
     */
    @XmlElement("LayerFeatureConstraints,LayerCoverageConstraints")
    public Constraints getConstraints();

    /**
     * Styles to apply on the features.
     */
    @XmlElement("UserStyle")
    public List<? extends Style> styles();
    
    /**
     * calls the visit method of a SLDVisitor
     *
     * @param visitor the sld visitor
     */
    @Extension
    Object accept(SLDVisitor visitor, Object extraData);

}
