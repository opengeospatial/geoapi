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


/**
 * Telephone numbers for contacting the responsible individual or organization.
 *
 * @UML datatype CI_Telephone
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public interface Telephone {
    /**
     * Telephone number by which individuals can speak to the responsible organization or individual.
     *
     * @UML optional voice
     */
    String getVoice();

    /**
     * Telephone number of a facsimile machine for the responsible organization or individual.
     *
     * @UML optional facsimile
     */
    String getFacsimile();
}
