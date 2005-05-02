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


/**
 * The <code>AbstractURL</code> interface is a simple starting point for most of 
 * the various URL elements used by {@code Layer}s and {@code Style}s.
 * 
 * 
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @author Jesse Crossley (SYS Technologies)
 */
public interface AbstractURL {

    /**
     * Provides the mime-type format of the expected response data.
     * @return the mime-type of the onlineResource's data
     */
    String getFormat();
    
    /**
     * Provides the {@code OnLineResource} this {@code AbstractURL} represents.  The
     * {@code OnLineResource} contains the actual {@code java.net.URI} to connect to.
     * @return the linkage
     */
    OnLineResource getOnlineResource();

}
