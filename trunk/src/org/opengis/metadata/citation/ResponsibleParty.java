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

// J2SE direct dependencies
import java.util.Locale;


/**
 * Identification of, and means of communication with, person(s) and
 * organizations associated with the dataset.
 *
 * @UML datatype CI_ResponsibleParty
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public interface ResponsibleParty {
    /**
     * Name of the responsible person- surname, given name, title separated by a delimiter.
     * Only one of <code>individualName</code>, {@link #getOrganisationName organisationName}
     * and {@link #getPositionName positionName} should be provided.
     *
     * @UML conditional individualName
     */
    String getIndividualName();

    /**
     * Name of the responsible organization.
     * Only one of {@link #getIndividualName individualName}, </code>organisationName</code>
     * and {@link #getPositionName positionName} should be provided.
     *
     * @param  locale The desired locale for the name to be returned, or <code>null</code>
     *         for a name in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The name in the given locale.
     *         If no name is available in the given locale, then some default locale is used.
     * @UML conditional organisationName
     */
    String getOrganisationName(Locale locale);

    /**
     * Role or position of the responsible person
     * Only one of {@link #getIndividualName individualName},
     * {@link #getOrganisationName organisationName} and <code>getPositionName</code>
     * should be provided.
     *
     * @param  locale The desired locale for the name to be returned, or <code>null</code>
     *         for a name in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The name in the given locale.
     *         If no name is available in the given locale, then some default locale is used.
     * @UML conditional positionName
     */
    String getPositionName(Locale locale);

    /**
     * Address of the responsible party.
     *
     * @UML optional contactInfo
     */
    Contact getContactInfo();

    /**
     * Function performed by the responsible party.
     *
     * @UML mandatory role
     */
    RoleCode getRole();
}
