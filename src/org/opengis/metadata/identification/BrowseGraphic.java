/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.identification;

// J2SE direct dependencies
import java.net.URL;

// OpenGIS direct dependencies
import org.opengis.util.InternationalString;


/**
 * Graphic that provides an illustration of the dataset (should include a legend for the graphic).
 *
 * @UML datatype MD_BrowseGraphic
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public interface BrowseGraphic {
    /**
     * Name of the file that contains a graphic that provides an illustration of the dataset.
     *
     * @UML mandatory fileName
     */
    URL getFileName();

    /**
     * Text description of the illustration.
     *
     * @UML optional fileDescription
     */
    InternationalString getFileDescription();

    /**
     * Format in which the illustration is encoded.
     * Examples: CGM, EPS, GIF, JPEG, PBM, PS, TIFF, XWD.
     *
     * @UML optional fileType
     */
    String getFileType();
}
