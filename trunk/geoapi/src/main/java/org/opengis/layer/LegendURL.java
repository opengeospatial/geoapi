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
 * Contains the linkage to an image of a map legend appropriate to the enclosing {@link Style}.
 *
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=5316">Implementation specification 1.3</A>
 * @author Jesse Crossley (SYS Technologies)
 * @since GeoAPI 2.0
 */
@XmlElement("LegendURL") // 7.2.4.6.5 Style
public interface LegendURL extends AbstractSizedURL {
}
