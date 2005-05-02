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


/**
 * The <code>Attribution</code> interface provides a way to identify the source
 * of geographic information used in a {@code Layer} or collection of {@code Layer}s.
 * 
 * @author ISO_19128 7.2.4.6.12 Attribution
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @author Jesse Crossley (SYS Technologies)
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
