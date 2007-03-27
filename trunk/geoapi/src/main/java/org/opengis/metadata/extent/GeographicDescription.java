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
package org.opengis.metadata.extent;

// OpenGIS direct dependencies
import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Specification.ISO_19115;

import org.opengis.annotation.UML;
import org.opengis.metadata.Identifier;


/**
 * Description of the geographic area using identifiers.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 */
@UML(identifier="EX_GeographicDescription", specification=ISO_19115)
public interface GeographicDescription extends GeographicExtent {
    /**
     * Returns the identifier used to represent a geographic area.
     */
    @UML(identifier="geographicIdentifier", obligation=MANDATORY, specification=ISO_19115)
    Identifier getGeographicIdentifier();
}
