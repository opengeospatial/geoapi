/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2023 Open Geospatial Consortium, Inc.
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
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


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
    @UML(identifier="role", obligation=MANDATORY, specification=ISO_19115)
    Role getRole();
}
