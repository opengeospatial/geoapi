/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source: /cvsroot/geoapi/src/org/opengis/metadata/citation/Telephone.java,v $
 **
 ** Copyright (C) 2004-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.citation;

// Annotations
import java.util.Collection;

import org.opengis.annotation.UML;
import org.opengis.metadata.MetadataEntity;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Telephone numbers for contacting the responsible individual or organization.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 */
@UML(identifier="CI_Telephone", specification=ISO_19115)
public interface Telephone extends MetadataEntity{
    /**
     * Telephone number by which individuals can speak to the responsible organization or individual.
     */
    @UML(identifier="voice", obligation=OPTIONAL, specification=ISO_19115)
    Collection<String> getVoice();

    /**
     * Telephone number of a facsimile machine for the responsible organization or individual.
     */
    @UML(identifier="facsimile", obligation=OPTIONAL, specification=ISO_19115)
    Collection<String> getFacsimile();
}
