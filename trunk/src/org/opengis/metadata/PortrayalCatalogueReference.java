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
import org.opengis.metadata.citation.Citation;


/**
 * Information identifying the portrayal catalogue used.
 *
 * @UML datatype MD_PortrayalCatalogueReference
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public interface PortrayalCatalogueReference {
    /**
     * Bibliographic reference to the portrayal catalogue cited.
     *
     * @UML mandatory portrayalCatalogueCitation
     */
    Citation[] getPortrayalCatalogueCitations();
}
