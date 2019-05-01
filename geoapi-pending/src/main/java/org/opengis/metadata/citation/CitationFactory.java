/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2005-2019 Open Geospatial Consortium, Inc.
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

import java.net.URI;
import java.util.Collection;
import org.opengis.util.InternationalString;
import org.opengis.annotation.Obligation;
import org.opengis.util.Factory;


/**
 * A factory for metadata from the citation package.
 * All factory methods accept null value for {@linkplain Obligation#OPTIONAL optional} arguments.
 * The value must be non-null for {@linkplain Obligation#MANDATORY mandatory} arguments.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as#01-111">ISO 19115</A>
 * @author  Jesse Crossley (SYS Technologies)
 * @since   GeoAPI 2.0
 */
public interface CitationFactory extends Factory {
    /**
     * Location of the responsible individual or organization.
     *
     * @param deliveryPoints          Address line for the location (as described in ISO 11180, Annex A).
     * @param city                    The city of the location.
     * @param administrativeArea      State, province of the location.
     * @param postalCode              ZIP or other postal code.
     * @param country                 Country of the physical address.
     * @param electronicMailAddresses Address of the electronic mailbox of the responsible organization or individual.
     * @return the address.
     */
    Address createAddress(
            Collection<String>  deliveryPoints,
            InternationalString city,
            InternationalString administrativeArea,
            String              postalCode,
            InternationalString country,
            Collection<String>  electronicMailAddresses);

    /**
     * Information required to enable contact with the responsible person and/or organization.
     *
     * @param phone               Telephone numbers at which the organization or individual may be contacted.
     * @param address             Physical and email address at which the organization or individual may be contacted.
     * @param onLineResource      On-line information that can be used to contact the individual or organization.
     * @param hoursOfService      Time period (including time zone) when individuals can contact the organization or individual.
     * @param contactInstructions Supplemental instructions on how or when to contact the individual or organization.
     * @return the contact.
     */
    Contact createContact(
            Telephone           phone,
            Address             address,
            OnlineResource      onLineResource,
            InternationalString hoursOfService,
            InternationalString contactInstructions);

    /**
     * Information about on-line sources from which the dataset, specification, or
     * community profile name and extended metadata elements can be obtained.
     *
     * @param linkage            Location (address) for on-line access.
     * @param protocol           Connection protocol to be used.
     * @param applicationProfile Name of an application profile that can be used with the online resource.
     * @param description        Detailed text description of what the online resource is/does.
     * @param function           Code for function performed by the online resource.
     * @return the online resource.
     */
    OnlineResource createOnLineResource(
            URI                 linkage,
            String              protocol,
            String              applicationProfile,
            InternationalString description,
            OnLineFunction      function);

    /**
     * Identification of, and means of communication with, person(s) and organizations associated with the dataset.
     * Only one of {@code individualName}, {@code organisationName} and {@code positionName} should be provided.
     *
     * @param individualName   Name of the responsible person- surname, given name, title separated by a delimiter.
     * @param organisationName Name of the responsible organization.
     * @param positionName     Role or position of the responsible person.
     * @param contactInfo      Address of the responsible party.
     * @param role             Function performed by the responsible party.
     * @return the responsible party.
     */
    ResponsibleParty createResponsibleParty(
            String              individualName,
            InternationalString organisationName,
            InternationalString positionName,
            Contact             contactInfo,
            Role                role);

    /**
     * Telephone numbers for contacting the responsible individual or organization.
     *
     * @param voice     Telephone number by which individuals can speak to the responsible organization or individual.
     * @param facsimile Telephone number of a facsimile machine for the responsible organization or individual.
     * @return the telephone.
     */
    Telephone createTelephone(
            String voice,
            String facsimile);
}
