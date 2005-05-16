/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
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
 * @author ISO 19128
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=5316">Implementation specification 1.3</A>
 * @since GeoAPI 1.1
 */
@XmlElement("StyleURL")
public interface StyleURL extends AbstractURL {
}
