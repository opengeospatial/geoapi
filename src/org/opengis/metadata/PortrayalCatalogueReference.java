/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata;

// J2SE direct dependencies
import java.util.Collection;

// OpenGIS direct dependencies
import org.opengis.metadata.citation.Citation;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information identifying the portrayal catalogue used.
 *
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @since GeoAPI 1.1
 */
@UML(identifier="MD_PortrayalCatalogueReference", specification=ISO_19115)
public interface PortrayalCatalogueReference {
    /**
     * Bibliographic reference to the portrayal catalogue cited.
     */
    @UML(identifier="portrayalCatalogueCitation", obligation=MANDATORY, specification=ISO_19115)
    Collection<Citation> getPortrayalCatalogueCitations();
}
