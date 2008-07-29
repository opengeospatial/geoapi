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
import org.opengis.metadata.citation.OnLineResource;

/**
 * OGC Web Service. Information about the remove serveur.
 * 
 * @version <A HREF="http://www.opengeospatial.org/standards/sld">Implementation specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@XmlElement("RemoteOWS")
public interface RemoteOWS extends Source {

    /**
     * The provided service type name.
     * @return WFS or WCS
     */
    @XmlElement("Service")
    public String getService();

    /**
     * Online resource of the service.
     */
    @XmlElement("OnlineResource")
    public OnLineResource getOnlineResource();
    
    /**
     * calls the visit method of a SLDVisitor
     *
     * @param visitor the sld visitor
     */
    @Extension
    Object accept(SLDVisitor visitor, Object extraData);
    
}
