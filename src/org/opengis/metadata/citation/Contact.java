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

// OpenGIS direct dependencies
import org.opengis.util.InternationalString;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * Information required to enable contact with the responsible person and/or organization.
 *
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
///@UML (identifier="CI_Contact")
public interface Contact {
    /**
     * Telephone numbers at which the organization or individual may be contacted.
     * Returns <code>null</code> if none.
     */
/// @UML (identifier="phone", obligation=OPTIONAL)
    Telephone getPhone();

    /**
     * Physical and email address at which the organization or individual may be contacted.
     * Returns <code>null</code> if none.
     */
/// @UML (identifier="address", obligation=OPTIONAL)
    Address getAddress();

    /**
     * On-line information that can be used to contact the individual or organization.
     * Returns <code>null</code> if none.
     */
/// @UML (identifier="onLineResource", obligation=OPTIONAL)
    OnLineResource getOnLineResource();

    /**
     * Time period (including time zone) when individuals can contact the organization or
     * individual. Returns <code>null</code> if unspecified.
     */
/// @UML (identifier="hoursOfService", obligation=OPTIONAL)
    InternationalString getHoursOfService();

    /**
     * Supplemental instructions on how or when to contact the individual or organization.
     * Returns <code>null</code> if none.
     */
/// @UML (identifier="contactInstructions", obligation=OPTIONAL)
    InternationalString getContactInstructions();
}
