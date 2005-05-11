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
 * Contains the linkage to an image of a map legend appropriate to the enclosing {@link Style}.
 * 
 * @author ISO 19128
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=5316">Implementation specification 1.3</A>
 * @since 1.1
 */
@XmlSchema ("http://schemas.opengis.net/wms/1.3.0/capabilities_1_3_0.xsd")
@UML (identifier="LegendURL", specification=ISO_19128) // 7.2.4.6.5 Style
public interface LegendURL extends AbstractSizedURL {
}
