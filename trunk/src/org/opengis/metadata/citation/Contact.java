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


/**
 * Information required to enable contact with the responsible person and/or organization.
 *
 * @UML datatype CI_Contact
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public interface Contact {
    /**
     * Telephone numbers at which the organization or individual may be contacted.
     * Returns <code>null</code> if none.
     *
     * @UML optional phone
     */
    Telephone getPhone();

    /**
     * Physical and email address at which the organization or individual may be contacted.
     * Returns <code>null</code> if none.
     *
     * @UML optional address
     */
    Address getAddress();

    /**
     * On-line information that can be used to contact the individual or organization.
     * Returns <code>null</code> if none.
     *
     * @UML optional address
     */
    OnLineResource getOnLineResource();

    /**
     * Time period (including time zone) when individuals can contact the organization or
     * individual. Returns <code>null</code> if unspecified.
     *
     * @UML optional hoursOfService
     */
    InternationalString getHoursOfService();

    /**
     * Supplemental instructions on how or when to contact the individual or organization.
     * Returns <code>null</code> if none.
     *
     * @UML optional contactInstructions
     */
    InternationalString getContactInstructions();
}
