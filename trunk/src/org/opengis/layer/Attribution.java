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

// OpenGIS direct dependencies
import org.opengis.metadata.citation.OnLineResource;
import org.opengis.util.InternationalString;

// Annotations
import org.opengis.annotation.XmlElement;
import static org.opengis.annotation.Obligation.*;


/**
 * Provides a way to identify the source of geographic information used in a {@link Layer}
 * or collection of {@code Layer}s.
 * 
 * @author ISO 19128
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=5316">Implementation specification 1.3</A>
 * @since GeoAPI 1.1
 */
@XmlElement("Attribution") // 7.2.4.6.12 Attribution
public interface Attribution {
    /**
     * Provides the human-readable string naming the data provider.
     *
     * @return the human-readable name of the data provider.
     */
    @XmlElement("Title")
    InternationalString getTitle();
    
    /**
     * Provides the data provider's linkage.
     *
     * @return the linkage to the data provider.
     */
    @XmlElement("OnlineResource")
    OnLineResource getOnlineResource();
    
    /**
     * Provides linkage to a logo image.
     *
     * @return linkage to the logo image.
     */
    @XmlElement("LogoURL")
    LogoURL getLogoURL();
}
