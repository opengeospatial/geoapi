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
import org.opengis.annotation.XmlParameter;
import org.opengis.metadata.citation.OnLineResource;

/**
 * The UseSLDLibrary element provides the ability of handling external SLD documents
 * to be used in library-mode even when using XML-encoded POST requests with a WMS.
 * (The library mode can be accessed with the HTTP-GET method by supplying an SLD
 * CGI parameter in addition to LAYERS and STYLES CGI parameters.) This addition
 * merely exercises pre-existing functionality in WMS, but it does add the wrinkle of
 * making SLD-library references iterative and (syntactically) recursive. Successive
 * definitions are applied “on top of” previous ones to determine the scoping of overlapping
 * style definitions.
 * 
 * @version <A HREF="http://www.opengeospatial.org/standards/sld">Implementation specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @since GeoAPI 2.2
 */
@XmlElement("UseSLDLibrary")
public interface SLDLibrary {

    /**
     * The OnlineResource must refer to an SLD document.
     */
    @XmlParameter("OnlineResource")
    OnLineResource getOnlineResource();

    /**
     * This is a convinient method for the one using this interface.
     * If we only provide the getOnlineResource method the user would be stuck
     * because he might not no how to parse correctly the given file.
     * This method must be implemented correctly and return the SLD object resulting
     * from the parsing of the OnlineResource.
     */
    StyledLayerDescriptor getSLD();
    
    /**
     * calls the visit method of a SLDVisitor
     *
     * @param visitor the sld visitor
     */
    @Extension
    Object accept(SLDVisitor visitor, Object extraData);
    
}
