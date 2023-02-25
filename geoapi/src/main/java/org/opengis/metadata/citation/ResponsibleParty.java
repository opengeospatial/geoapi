/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2011 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.opengis.metadata.citation;

import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;
import org.opengis.annotation.Profile;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;
import static org.opengis.annotation.ComplianceLevel.*;


/**
 * Identification of, and means of communication with, person(s) and
 * organizations associated with the dataset.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 *
 * @navassoc 1 - - Role
 */
@UML(identifier="CI_ResponsibleParty", specification=ISO_19115)
public interface ResponsibleParty {
    /**
     * Name of the responsible person- surname, given name, title separated by a delimiter.
     * Only one of {@code individualName}, {@link #getOrganisationName organisationName}
     * and {@link #getPositionName positionName} shall be provided.
     *
     * @return Name, surname, given name and title of the responsible person, or {@code null}.
     *
     * @condition {@linkplain #getOrganisationName Organisation name} and
     *            {@linkplain #getPositionName position name} not documented.
     */
    @Profile(level=CORE)
    @UML(identifier="individualName", obligation=CONDITIONAL, specification=ISO_19115)
    String getIndividualName();

    /**
     * Name of the responsible organization.
     * Only one of {@link #getIndividualName individualName}, {@code organisationName}
     * and {@link #getPositionName positionName} shall be provided.
     *
     * @return Name of the responsible organization, or {@code null}.
     *
     * @condition {@linkplain #getIndividualName Individual name} and
     *            {@linkplain #getPositionName position name} not documented.
     */
    @Profile(level=CORE)
    @UML(identifier="organisationName", obligation=CONDITIONAL, specification=ISO_19115)
    InternationalString getOrganisationName();

    /**
     * Role or position of the responsible person.
     * Only one of {@link #getIndividualName individualName}, {@link #getOrganisationName organisationName}
     * and {@code positionName} shall be provided.
     *
     * @return Role or position of the responsible person, or {@code null}
     *
     * @condition {@linkplain #getIndividualName Individual name} and
     *            {@linkplain #getOrganisationName organisation name} not documented.
     */
    @Profile(level=CORE)
    @UML(identifier="positionName", obligation=CONDITIONAL, specification=ISO_19115)
    InternationalString getPositionName();

    /**
     * Address of the responsible party.
     *
     * @return Address of the responsible party., or {@code null}.
     */
    @UML(identifier="contactInfo", obligation=OPTIONAL, specification=ISO_19115)
    Contact getContactInfo();

    /**
     * Function performed by the responsible party.
     *
     * @return Function performed by the responsible party.
     */
    @Profile(level=CORE)
    @UML(identifier="role", obligation=MANDATORY, specification=ISO_19115)
    Role getRole();
}
