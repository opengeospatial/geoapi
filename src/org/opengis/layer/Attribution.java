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
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * The <code>Attribution</code> interface provides a way to identify the source
 * of geographic information used in a {@code Layer} or collection of {@code Layer}s.
 * 
 * @author ISO 19128 section 7.2.4.6.12 Attribution
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @author Jesse Crossley (SYS Technologies)
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=5316">Implementation specification 1.3</A>
 * @since 1.1
 */
public interface Attribution {
    /**
     * Provides the human-readable string naming the data provider.
     * @return the human-readable name of the data provider
     */
    InternationalString getTitle();
    
    /**
     * Provides the data provider's linkage.
     * @return the linkage to the data provider
     */
    OnLineResource getOnlineResource();
    
    /**
     * Provides linkage to a logo image.
     * @return linkage to the logo image
     */
    LogoURL getLogoURL();
}
