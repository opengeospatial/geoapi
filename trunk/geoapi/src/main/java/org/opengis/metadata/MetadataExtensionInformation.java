/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata;

// J2SE direct dependencies
import static org.opengis.annotation.Obligation.OPTIONAL;
import static org.opengis.annotation.Specification.ISO_19115;

import java.util.Collection;

import org.opengis.annotation.UML;
import org.opengis.metadata.citation.OnLineResource;


/**
 * Information describing metadata extensions.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="MD_MetadataExtensionInformation", specification=ISO_19115)
public interface MetadataExtensionInformation {
    /**
     * Information about on-line sources containing the community profile name and
     * the extended metadata elements. Information for all new metadata elements.
     */
    @UML(identifier="extensionOnLineResource", obligation=OPTIONAL, specification=ISO_19115)
    OnLineResource getExtensionOnLineResource();

    /**
     * Provides information about a new metadata element, not found in ISO 19115, which is
     * required to describe geographic data.
     */
    @UML(identifier="extendedElementInformation", obligation=OPTIONAL, specification=ISO_19115)
    Collection<ExtendedElementInformation> getExtendedElementInformation();
}
