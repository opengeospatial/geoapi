/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.citation;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * Telephone numbers for contacting the responsible individual or organization.
 *
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
///@UML (identifier="CI_Telephone")
public interface Telephone {
    /**
     * Telephone number by which individuals can speak to the responsible organization or individual.
     */
/// @UML (identifier="voice", obligation=OPTIONAL)
    String getVoice();

    /**
     * Telephone number of a facsimile machine for the responsible organization or individual.
     */
/// @UML (identifier="facsimile", obligation=OPTIONAL)
    String getFacsimile();
}
