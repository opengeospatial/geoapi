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
import org.opengis.metadata.Identifier;
import org.opengis.metadata.citation.OnLineResource;

// Annotations
import org.opengis.annotation.XmlElement;
import static org.opengis.annotation.Obligation.*;


/**
 * Encloses an {@link OnLineResource} which states the linkage of a document defining
 * the meaning of the {@link Identifier} values which reference this {@code AuthorityURL}.
 * 
 * @author ISO 19128
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=5316">Implementation specification 1.3</A>
 * @since GeoAPI 1.1
 */
@XmlElement(name="AuthorityURL") // 7.2.4.6.13 Identifier and AuthorityURL
public interface AuthorityURL {
    /**
     * Provides the name of this {@code AuthorityURL} which may be referenced
     * by one or more {@link Identifier}s.
     *
     * @return this {@code AuthorityURL}'s name.
     */
    @XmlElement(name="Name", obligation=MANDATORY)
    String getName();
    
    /**
     * Provides the linkage to the document defining the meaning of the values
     * used by {@link Identifier}s which reference this {@code AuthorityURL}.
     *
     * @return the linkage to the {@code Identifier} value defining document.
     */
    @XmlElement(name="OnlineResource", obligation=MANDATORY)
    OnLineResource getOnlineResource();
}
