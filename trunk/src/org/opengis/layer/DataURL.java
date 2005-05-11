/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.layer;

// Annotations
import org.opengis.annotation.UML;
import org.opengis.annotation.XmlSchema;
import static org.opengis.annotation.Specification.*;


/**
 * Offers a link to the underlying data represented by a particular {@link Layer}.
 * The enclosed Format element indicates the file format MIME type of the data file.
 * 
 * @author ISO 19128
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=5316">Implementation specification 1.3</A>
 * @since 1.1
 */
@XmlSchema ("http://schemas.opengis.net/wms/1.3.0/capabilities_1_3_0.xsd")
@UML (identifier="DataURL", specification=ISO_19128) // 7.2.4.6.15 DataURL
public interface DataURL extends AbstractURL {
}
