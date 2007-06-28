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
package org.opengis.metadata.citation;

import java.util.Collection;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Telephone numbers for contacting the responsible individual or organization.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as#01-111">ISO 19115</A>
 * @author Martin Desruisseaux (IRD)
 * @author Cory Horner (Refractions Research)
 * @since GeoAPI 1.0
 */
@UML(identifier="CI_Telephone", specification=ISO_19115)
public interface Telephone {
    /**
     * Telephone number by which individuals can speak to the responsible organization or individual.
     *
     * @deprecated Replaced by {@link #getVoices}.
     */
    @UML(identifier="voice", obligation=OPTIONAL, specification=ISO_19115)
    String getVoice();

    /**
     * Telephone numbers by which individuals can speak to the responsible organization or individual.
     *
     * @since GeoAPI 2.1
     */
    @UML(identifier="voice", obligation=OPTIONAL, specification=ISO_19115)
    Collection<String> getVoices();

    /**
     * Telephone number of a facsimile machine for the responsible organization or individual.
     *
     * @deprecated Replaced by {@link #getFacsimiles}.
     */
    @UML(identifier="facsimile", obligation=OPTIONAL, specification=ISO_19115)
    String getFacsimile();

    /**
     * Telephone numbers of a facsimile machine for the responsible organization or individual.
     *
     * @since GeoAPI 2.1
     */
    @UML(identifier="facsimile", obligation=OPTIONAL, specification=ISO_19115)
    Collection<String> getFacsimiles();
}
