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
package org.opengis.metadata.identification;

// J2SE direct dependencies
import java.net.URI;

// OpenGIS direct dependencies
import org.opengis.util.InternationalString;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Graphic that provides an illustration of the dataset (should include a legend for the graphic).
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="MD_BrowseGraphic", specification=ISO_19115)
public interface BrowseGraphic {
    /**
     * Name of the file that contains a graphic that provides an illustration of the dataset.
     */
    @UML(identifier="fileName", obligation=MANDATORY, specification=ISO_19115)
    URI getFileName();

    /**
     * Text description of the illustration.
     */
    @UML(identifier="fileDescription", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getFileDescription();

    /**
     * Format in which the illustration is encoded.
     * Examples: CGM, EPS, GIF, JPEG, PBM, PS, TIFF, XWD.
     */
    @UML(identifier="fileType", obligation=OPTIONAL, specification=ISO_19115)
    String getFileType();
}
