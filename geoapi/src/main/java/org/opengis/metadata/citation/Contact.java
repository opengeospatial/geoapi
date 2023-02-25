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

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information required to enable contact with the responsible person and/or organization.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 *
 * @navassoc 1 - - Telephone
 * @navassoc 1 - - Address
 * @navassoc 1 - - OnlineResource
 */
@UML(identifier="CI_Contact", specification=ISO_19115)
public interface Contact {
    /**
     * Telephone numbers at which the organization or individual may be contacted.
     * Returns {@code null} if none.
     *
     * @return Telephone numbers at which the organization or individual may be contacted,
     *         or {@code null}.
     */
    @UML(identifier="phone", obligation=OPTIONAL, specification=ISO_19115)
    Telephone getPhone();

    /**
     * Physical and email address at which the organization or individual may be contacted.
     * Returns {@code null} if none.
     *
     * @return Physical and email address at which the organization or individual may be contacted,
     *         or {@code null}.
     */
    @UML(identifier="address", obligation=OPTIONAL, specification=ISO_19115)
    Address getAddress();

    /**
     * On-line information that can be used to contact the individual or organization.
     * Returns {@code null} if none.
     *
     * @return On-line information that can be used to contact the individual or organization,
     *         or {@code null}.
     */
    @UML(identifier="onlineResource", obligation=OPTIONAL, specification=ISO_19115)
    OnlineResource getOnlineResource();

    /**
     * Time period (including time zone) when individuals can contact the organization or
     * individual. Returns {@code null} if unspecified.
     *
     * @return Time period when individuals can contact the organization or individual,
     *         or {@code null}.
     */
    @UML(identifier="hoursOfService", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getHoursOfService();

    /**
     * Supplemental instructions on how or when to contact the individual or organization.
     * Returns {@code null} if none.
     *
     * @return Supplemental instructions on how or when to contact the individual or organization,
     *         or {@code null}.
     */
    @UML(identifier="contactInstructions", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getContactInstructions();
}
