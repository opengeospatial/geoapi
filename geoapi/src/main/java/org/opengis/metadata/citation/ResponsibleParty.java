/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2019 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.metadata.citation;

import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;
import org.opengis.annotation.Profile;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.ComplianceLevel.*;


/**
 * Identification of, and means of communication with, person(s) and
 * organizations associated with the dataset.
 * At least one of {@link #getIndividualName() individual name}, {@linkplain #getOrganisationName()
 * organisation name} and {@linkplain #getPositionName() position name} shall be provided.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   1.0
 *
 * @deprecated As of ISO 19115:2014, the {@code ResponsibleParty} type has been replaced by {@link Responsibility}
 *             to allow more flexible associations of individuals, organisations, and roles.
 */
@Deprecated
@Classifier(Stereotype.DATATYPE)
@UML(identifier="CI_ResponsibleParty", specification=ISO_19115, version=2003)
public interface ResponsibleParty extends Responsibility {
    /**
     * Name of the responsible person- surname, given name, title separated by a delimiter.
     *
     * @return name, surname, given name and title of the responsible person, or {@code null}.
     *
     * @condition Mandatory if the {@linkplain #getOrganisationName() organisation name} and
     *            {@linkplain #getPositionName() position name} are not documented.
     *
     * @deprecated As of ISO 19115:2014, replaced by {@code getName()} in {@link Individual}.
     */
    @Deprecated
    @Profile(level=CORE)
    @UML(identifier="individualName", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    String getIndividualName();

    /**
     * Name of the responsible organization.
     *
     * @return name of the responsible organization, or {@code null}.
     *
     * @condition Mandatory if the {@linkplain #getIndividualName() individual name} and
     *            {@linkplain #getPositionName() position name} are not documented.
     *
     * @deprecated As of ISO 19115:2014, replaced by {@code getName()} in {@link Organisation}.
     */
    @Deprecated
    @Profile(level=CORE)
    @UML(identifier="organisationName", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    InternationalString getOrganisationName();

    /**
     * Role or position of the responsible person.
     *
     * @return role or position of the responsible person, or {@code null}
     *
     * @condition Mandatory if the {@linkplain #getIndividualName() individual name} and
     *            {@linkplain #getOrganisationName() organisation name} are not documented.
     *
     * @deprecated As of ISO 19115:2014, replaced by {@link Individual#getPositionName()}.
     */
    @Deprecated
    @Profile(level=CORE)
    @UML(identifier="positionName", obligation=CONDITIONAL, specification=ISO_19115, version=2003)
    InternationalString getPositionName();

    /**
     * Address of the responsible party.
     *
     * @return address of the responsible party, or {@code null}.
     *
     * @deprecated As of ISO 19115:2014, replaced by {@link Party#getContactInfo()}.
     */
    @Deprecated
    @UML(identifier="contactInfo", obligation=OPTIONAL, specification=ISO_19115, version=2003)
    Contact getContactInfo();

    /**
     * Function performed by the responsible party.
     *
     * @return function performed by the responsible party.
     */
    // No explicit @Deprecation because the inherited Responsibility.getRole() method is not deprecated.
    @Override
    @Profile(level=CORE)
    @UML(identifier="role", obligation=MANDATORY, specification=ISO_19115)
    Role getRole();
}
