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
 * The <code>FeatureListURL</code> interface points to a list of the features
 * represented in a given {@code Layer}.  The enclosed Format element indicates
 * the file format MIME type of the feature list.
 * 
 * @author ISO_19128 7.2.4.6.14 FeatureListURL
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @author Jesse Crossley (SYS Technologies)
 * @version <A HREF="http://portal.opengeospatial.org/files/?artifact_id=5316">Implementation specification 1.3</A>
 * @since 1.1
 */
public interface FeatureListURL extends AbstractURL {
}
