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
package org.opengis.layer;

// Annotations
import org.opengis.annotation.XmlElement;


/**
 * Offers more information about the data or symbology underlying a particular {@link Style}.
 * While the semantics are not well-defined, as long as the results of an HTTP GET request
 * against the {@code StyleURL} are properly MIME-typed, Viewer Clients and Cascading Map Servers
 * can make use of this. A possible use could be to allow a Map Server to provide legend information.
 * 
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=5316">Implementation specification 1.3</A>
 * @author Jesse Crossley (SYS Technologies)
 * @since GeoAPI 2.0
 */
@XmlElement("StyleURL")
public interface StyleURL extends AbstractURL {
}
