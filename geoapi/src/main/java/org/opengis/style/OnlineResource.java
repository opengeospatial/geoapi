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
package org.opengis.style;

import java.net.URI;
import org.opengis.annotation.XmlElement;

/**
 * Represents an online resource by an URI.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/symbol">Symbology Encoding Implementation Specification 1.1.0</A>
 * @author Open Geospatial Consortium
 * @author Johann Sorel (Geomatys)
 * @author Chris Dillard (SYS Technologies)
 * @since GeoAPI 2.2
 */
@XmlElement("OnlineResource")
public interface OnlineResource {

    /**
     * URI to acces the online resource
     * @return URI
     */
    @XmlElement("xlink:simpleLink")
    URI getURI();

}
