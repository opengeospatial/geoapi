/*$************************************************************************************************
 **
 ** $Id: Identifier.java  $
 **
 ** $URL: https://geoapi.svn.sourceforge.net/svnroot/geoapi/trunk/geoapi/src/main/java/org/opengis/metadata/reference/Identifier.java $
 **
 ** Copyright (C) 2004-2007 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.reference;

import static org.opengis.annotation.Obligation.OPTIONAL;
import static org.opengis.annotation.Specification.ISO_19115;

import org.opengis.annotation.UML;

/**
 * Identifier used for reference systems.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Ely Conn (Leica Geosystems Geospatial Imaging, LLC)
 * @since GeoAPI 2.1
 */
@UML(identifier="RS_Identifier", specification=ISO_19115)
public interface Identifier extends org.opengis.metadata.Identifier {
    /**
     * Name or identifier of the person or organization responsible for namespace.
     */
    @UML(identifier="codeSpace", obligation=OPTIONAL, specification=ISO_19115)
    String getCodeSpace();

    /**
     * Version identifier for the namespace.
     */
    @UML(identifier="version", obligation=OPTIONAL, specification=ISO_19115)
    String getVersion();
}
