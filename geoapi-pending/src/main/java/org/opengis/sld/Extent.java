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

import org.opengis.annotation.Extension;
import org.opengis.annotation.XmlElement;

/**
 * When used in a UserLayer, the Extent reference defines what features are to be included
 * in the layer and when used in a NamedLayer, it filters the features that are part of the
 * named layer.
 * 
 * @version <A HREF="http://www.opengeospatial.org/standards/sld">Implementation specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
public interface Extent {

    @XmlElement("Name")
    String getName();

    @XmlElement("Value")
    String getValue();
    
    /**
     * calls the visit method of a SLDVisitor
     *
     * @param visitor the sld visitor
     */
    @Extension
    Object accept(SLDVisitor visitor, Object extraData);
    
}
