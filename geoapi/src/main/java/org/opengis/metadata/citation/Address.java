/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
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
     * <div class="warning"><b>Upcoming API change — internationalization</b><br>
     * The return type will be changed from {@code Collection<String>} to
     * {@code Collection<? extends InternationalString>} in GeoAPI 4.0.
     * </div>
     *
     * @return address lines for the location, or an empty collection if none.
     *
     * @todo https://github.com/opengeospatial/geoapi/issues/34
     */
    @UML(identifier="deliveryPoint", obligation=OPTIONAL, specification=ISO_19115)
    default Collection<String> getDeliveryPoints() {
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
