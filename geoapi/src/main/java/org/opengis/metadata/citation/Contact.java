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

// OpenGIS direct dependencies
import org.opengis.util.InternationalString;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information required to enable contact with the responsible person and/or organization.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 */
@UML(identifier="CI_Contact", specification=ISO_19115)
public interface Contact {
    /**
     * Telephone numbers at which the organization or individual may be contacted.
     * Returns {@code null} if none.
     */
    @UML(identifier="phone", obligation=OPTIONAL, specification=ISO_19115)
    Telephone getPhone();

    /**
     * Physical and email address at which the organization or individual may be contacted.
     * Returns {@code null} if none.
     */
    @UML(identifier="address", obligation=OPTIONAL, specification=ISO_19115)
    Address getAddress();

    /**
     * On-line information that can be used to contact the individual or organization.
     * Returns {@code null} if none.
     */
    @UML(identifier="onLineResource", obligation=OPTIONAL, specification=ISO_19115)
    OnLineResource getOnLineResource();

    /**
     * Time period (including time zone) when individuals can contact the organization or
     * individual. Returns {@code null} if unspecified.
     */
    @UML(identifier="hoursOfService", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getHoursOfService();

    /**
     * Supplemental instructions on how or when to contact the individual or organization.
     * Returns {@code null} if none.
     */
    @UML(identifier="contactInstructions", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getContactInstructions();
}
