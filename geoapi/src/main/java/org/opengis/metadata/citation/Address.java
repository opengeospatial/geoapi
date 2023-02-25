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

import java.util.Collection;
import java.util.Collections;
import org.opengis.util.InternationalString;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Location of the responsible individual or organization.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   1.0
 */
@Classifier(Stereotype.DATATYPE)
@UML(identifier="CI_Address", specification=ISO_19115)
public interface Address {
    /**
     * Address lines for the location (as described in ISO 11180, Annex A).
     * This method allows multiple values for describing a location on many lines,
     * not for enumerating many locations.
     *
     * @return address lines for the location, or an empty collection if none.
     *
     * @todo https://github.com/opengeospatial/geoapi/issues/34
     */
    @UML(identifier="deliveryPoint", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<? extends InternationalString> getDeliveryPoints() {
        return Collections.emptyList();
    }

    /**
     * The city of the location.
     * Returns {@code null} if unspecified.
     *
     * @return the city of the location, or {@code null}.
     */
    @UML(identifier="city", obligation=OPTIONAL, specification=ISO_19115)
    default InternationalString getCity() {
        return null;
    }

    /**
     * State, province of the location.
     * Returns {@code null} if unspecified.
     *
     * @return state, province of the location, or {@code null}.
     */
    @UML(identifier="administrativeArea", obligation=OPTIONAL, specification=ISO_19115)
    default InternationalString getAdministrativeArea() {
        return null;
    }

    /**
     * ZIP or other postal code.
     * Returns {@code null} if unspecified.
     *
     * @return ZIP or other postal code, or {@code null}.
     */
    @UML(identifier="postalCode", obligation=OPTIONAL, specification=ISO_19115)
    default String getPostalCode() {
        return null;
    }

    /**
     * Country of the physical address.
     * Returns {@code null} if unspecified.
     *
     * @return country of the physical address, or {@code null}.
     */
    @UML(identifier="country", obligation=OPTIONAL, specification=ISO_19115)
    default InternationalString getCountry() {
        return null;
    }

    /**
     * Address of the electronic mailbox of the responsible organization or individual.
     * Returns an empty collection if none.
     *
     * @return address of the electronic mailbox of the responsible organization or individual.
     */
    @UML(identifier="electronicMailAddress", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<String> getElectronicMailAddresses() {
        return Collections.emptySet();                          // Use Set instead of List for hash-safe final classes.
    }
}
