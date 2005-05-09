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

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * The <code>AuthorityURL</code> interface encloses an {@code OnLineResource}
 * which states the linkage of a document defining the meaning of the 
 * {@code Identifier} values which reference this {@code AuthorityURL}.
 * 
 * @author ISO 19128 section 7.2.4.6.13 Identifier and AuthorityURL
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @author Jesse Crossley (SYS Technologies)
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=5316">Implementation specification 1.3</A>
 * @since 1.1
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
