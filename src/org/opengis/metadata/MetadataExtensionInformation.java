/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata;

// OpenGIS direct dependencies
import org.opengis.metadata.citation.OnLineResource;


/**
 * Information describing metadata extensions.
 *
 * @UML datatype MD_MetadataExtensionInformation
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public interface MetadataExtensionInformation {
    /**
     * Information about on-line sources containing the community profile name and
     * the extended metadata elements. Information for all new metadata elements.
     *
     * @UML optional extensionOnLineResource
     */
    OnLineResource getExtensionOnLineResource();

    /**
     * Provides information about a new metadata element, not found in ISO 19115, which is
     * required to describe geographic data.
     *
     * @UML optional extendedElementInformation
     */
    ExtendedElementInformation[] getExtendedElementInformation();
}
