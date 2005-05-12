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
import org.opengis.annotation.XmlElement;
import static org.opengis.annotation.Obligation.*;


/**
 * Offers detailed, standardized metadata about the data corresponding to a particular {@link Layer}.
 * 
 * @author ISO 19128
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=5316">Implementation specification 1.3</A>
 * @since GeoAPI 1.1
 */
@XmlElement("MetadataURL") // 7.2.4.6.11 MetadataURL
public interface MetadataURL extends AbstractURL {
    /**
     * Provides the standard to which the metadata compiles.
     * The two currently defined values are:
     * <ul>
     * <li>'ISO19115:2003' - refers to ISO 19115:2003</li>
     * <li>'FGDC:1998' - refers to FGDC-STD-001-1998</li>
     * <ul>
     * An information community may define meanings for other values.
     */
    @XmlElement("type")
    String getType();
}
