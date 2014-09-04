/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2008-2014 Open Geospatial Consortium, Inc.
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
package org.opengis.test.metadata;

import org.opengis.metadata.*;
import org.opengis.metadata.citation.*;
import org.opengis.util.InternationalString;
import org.opengis.test.ValidatorContainer;


/**
 * Validates {@link Citation} and related objects from the
 * {@code org.opengis.metadata.citation} package.
 *
 * <p>This class is provided for users wanting to override the validation methods. When the default
 * behavior is sufficient, the {@link org.opengis.test.Validators} static methods provide a more
 * convenient way to validate various kinds of objects.</p>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   2.2
 */
public class CitationValidator extends MetadataValidator {
    /**
     * Creates a new validator instance.
     *
     * @param container The set of validators to use for validating other kinds of objects
     *                  (see {@linkplain #container field javadoc}).
     */
    public CitationValidator(final ValidatorContainer container) {
        super(container, "org.opengis.metadata.citation");
    }

    /**
     * Validates the given citation.
     *
     * @param object The object to validate, or {@code null}.
     */
    public void validate(final Citation object) {
        if (object == null) {
            return;
        }
        validateMandatory(object.getTitle());
        validateOptional (object.getEdition());
        for (final InternationalString e : toArray(InternationalString.class, object.getAlternateTitles())) {
            container.validate(e);
        }
        for (final Identifier e : toArray(Identifier.class, object.getIdentifiers())) {
            container.validate(e);
        }
        container.validate(object.getOtherCitationDetails());
        for (final Responsibility e : toArray(Responsibility.class, object.getCitedResponsibleParties())) {
            validate(e);
        }
    }

    /**
     * Validates the given responsible party.
     *
     * @param object The object to validate, or {@code null}.
     *
     * @since 3.1
     */
    public void validate(final Responsibility object) {
        if (object == null) {
            return;
        }
        mandatory("Responsibility: shall have a role.", object.getRole());
        for (final Party e : toArray(Party.class, object.getParties())) {
            validate(e);
        }
    }

    /**
     * Validates the given party.
     *
     * @param object The object to validate, or {@code null}.
     *
     * @since 3.1
     */
    public void validate(final Party object) {
        if (object == null) {
            return;
        }
        boolean isMandatory = true;
        if (object instanceof Individual) {
            isMandatory &= isNullOrEmpty(((Individual) object).getPositionName());
        }
        if (object instanceof Organisation) {
            isMandatory &= isNullOrEmpty(((Organisation) object).getLogo());
        }
        if (isMandatory) {
            mandatory("Party: shall have a name.", object.getName());
        }
        for (final Contact e : toArray(Contact.class, object.getContactInfo())) {
            validate(e);
        }
    }

    /**
     * Validates the given contact information.
     *
     * @param object The object to validate, or {@code null}.
     *
     * @since 3.1
     */
    public void validate(final Contact object) {
        if (object == null) {
            return;
        }
        for (final Telephone e : toArray(Telephone.class, object.getPhones())) {
            validate(e);
        }
        for (final Address e : toArray(Address.class, object.getAddresses())) {
            validate(e);
        }
        for (final OnlineResource e : toArray(OnlineResource.class, object.getOnlineResources())) {
            validate(e);
        }
        validateOptional(object.getHoursOfService());
        validateOptional(object.getContactInstructions());
    }

    /**
     * Validates the given telephone information.
     *
     * @param object The object to validate, or {@code null}.
     *
     * @since 3.1
     */
    public void validate(final Telephone object) {
        if (object == null) {
            return;
        }
        mandatory("Telephone: shall have a number.", object.getNumber());
    }

    /**
     * Validates the given address.
     *
     * @param object The object to validate, or {@code null}.
     *
     * @since 3.1
     */
    public void validate(final Address object) {
        if (object == null) {
            return;
        }
        validate(object.getDeliveryPoints());
        validateOptional(object.getCity());
        validateOptional(object.getAdministrativeArea());
        validateOptional(object.getCountry());
        validate(object.getElectronicMailAddresses());
    }

    /**
     * Validates the given online resource.
     *
     * @param object The object to validate, or {@code null}.
     *
     * @since 3.1
     */
    public void validate(final OnlineResource object) {
        if (object == null) {
            return;
        }
        mandatory("OnlineResource: shall have a linkage.", object.getLinkage());
        validateOptional(object.getDescription());
    }
}
