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
 * Points to a list of the features represented in a given {@link Layer}.
 * The enclosed Format element indicates the file format MIME type of the feature list.
 *
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=5316">Implementation specification 1.3</A>
 * @author Jesse Crossley (SYS Technologies)
 * @since GeoAPI 2.0
 */
@XmlElement("FeatureListURL") // 7.2.4.6.14 FeatureListURL
public interface FeatureListURL extends AbstractURL {
}
