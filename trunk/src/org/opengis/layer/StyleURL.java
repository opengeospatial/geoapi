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
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * The <code>StyleURL</code> interface offers more information about the
 * data or symbology underlying a particular Style. While the semantics
 * are not well-defined, as long as the results of an HTTP GET request
 * against the StyleURL are properly MIME-typed, Viewer Clients and
 * Cascading Map Servers can make use of this. A possible use could be
 * to allow a Map Server to provide legend information.
 * 
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @author Jesse Crossley (SYS Technologies)
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=5316">Implementation specification 1.3</A>
 * @since 1.1
 */
@UML (identifier="StyleURL", specification=ISO_19128)
public interface StyleURL extends AbstractURL {
}
