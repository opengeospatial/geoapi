/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2024 Open Geospatial Consortium, Inc.
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

import java.util.List;
import java.util.Collection;
import java.util.Iterator;
import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Information required to enable contact with the responsible person and/or organization.
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Rémi Maréchal (Geomatys)
 * @version 3.1
 * @since   1.0
 */
@Classifier(Stereotype.DATATYPE)
@UML(identifier="CI_Contact", specification=ISO_19115)
public interface Contact {
    /**
     * Telephone numbers at which the organization or individual may be contacted.
     * Returns an empty collection if none.
     *
     * @return telephone numbers at which the organization or individual may be contacted.
     *
     * @since 3.1
     */
    @UML(identifier="phone", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends Telephone> getPhones() {
        return List.of();
    }

    /**
     * Telephone numbers at which the organization or individual may be contacted.
     * Returns {@code null} if none.
     *
     * @return telephone numbers at which the organization or individual may be contacted,
     *         or {@code null}.
     *
     * @deprecated As of ISO 19115:2014, replaced by {@link #getPhones()}.
     */
    @Deprecated(since="3.1")
    default Telephone getPhone() {
        Iterator<? extends Telephone> it = getPhones().iterator();
        return it.hasNext() ? it.next() : null;
    }

    /**
     * Physical and email addresses at which the organization or individual may be contacted.
     * Returns an empty collection if none.
     *
     * @return physical and email addresses at which the organization or individual may be contacted.
     *
     * @since 3.1
     */
    @UML(identifier="address", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends Address> getAddresses() {
        return List.of();
    }

    /**
     * Physical and email address at which the organization or individual may be contacted.
     * Returns {@code null} if none.
     *
     * @return physical and email address at which the organization or individual may be contacted,
     *         or {@code null}.
     *
     * @deprecated As of ISO 19115:2014, replaced by {@link #getAddresses()}.
     */
    @Deprecated(since="3.1")
    default Address getAddress() {
        Iterator<? extends Address> it = getAddresses().iterator();
        return it.hasNext() ? it.next() : null;
    }

    /**
     * On-line information that can be used to contact the individual or organization.
     * Returns an empty collection if none.
     *
     * @return on-line information that can be used to contact the individual or organization.
     *
     * @since 3.1
     */
    @UML(identifier="onlineResource", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends OnlineResource> getOnlineResources() {
        return List.of();
    }

    /**
     * On-line information that can be used to contact the individual or organization.
     * Returns {@code null} if none.
     *
     * @return on-line information that can be used to contact the individual or organization,
     *         or {@code null}.
     *
     * @deprecated As of ISO 19115:2014, replaced by {@link #getOnlineResources()}.
     */
    @Deprecated(since="3.1")
    default OnlineResource getOnlineResource() {
        Iterator<? extends OnlineResource> it = getOnlineResources().iterator();
        return it.hasNext() ? it.next() : null;
    }

    /**
     * Time period (including time zone) when individuals can contact the organization or individual.
     * Returns {@code null} if none.
     *
     * <div class="warning"><b>Upcoming API change — multiplicity</b><br>
     * As of ISO 19115:2014, this singleton has been replaced by a collection.
     * This change may be applied in GeoAPI 4.0.
     * </div>
     *
     * @return time period when individuals can contact the organization or individual.
     */
    @UML(identifier="hoursOfService", obligation=OPTIONAL, specification=ISO_19115)
    default InternationalString getHoursOfService() {
        return null;
    }

    /**
     * Supplemental instructions on how or when to contact the individual or organization.
     * Returns {@code null} if none.
     *
     * @return supplemental instructions on how or when to contact the individual or organization,
     *         or {@code null} if none.
     */
    @UML(identifier="contactInstructions", obligation=OPTIONAL, specification=ISO_19115)
    default InternationalString getContactInstructions() {
        return null;
    }

    /**
     * Type of the contact.
     * Returns {@code null} if none.
     *
     * @return type of the contact, or {@code null} if none.
     *
     * @since 3.1
     */
    @UML(identifier="contactType", obligation=OPTIONAL, specification=ISO_19115)
    default InternationalString getContactType() {
        return null;
    }
}
