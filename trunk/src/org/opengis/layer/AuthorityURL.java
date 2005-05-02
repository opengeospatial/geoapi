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
 * The <code>AuthorityURL</code> interface encloses an {@code OnLineResource}
 * which states the linkage of a document defining the meaning of the 
 * {@code Identifier} values which reference this {@code AuthorityURL}.
 * 
 * @author ISO_19128 7.2.4.6.13 Identifier and AuthorityURL
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @author Jesse Crossley (SYS Technologies)
 */
public interface AuthorityURL {

    /**
     * Provides the name of this {@code AuthorityURL} which may be referenced
     * by one or more {@code Identifier}s
     * @return this {@code AuthorityURL}'s name
     */
    String getName();
    
    /**
     * Provides the linkage to the document defining the meaning of the values
     * used by {@code Identifier}s which reference this {@code AuthorityURL}.
     * @return the linkage to the {@code Identifier} value defining document
     */
    OnLineResource getOnlineResource();
}
