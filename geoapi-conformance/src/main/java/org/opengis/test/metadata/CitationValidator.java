/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2024 Open Geospatial Consortium, Inc.
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
package org.opengis.test.metadata;

import java.util.Date;
import org.opengis.metadata.*;
import org.opengis.metadata.citation.*;
import org.opengis.util.InternationalString;
import org.opengis.test.ValidatorContainer;

import static org.junit.jupiter.api.Assertions.fail;


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
     * @param container  the set of validators to use for validating other kinds of objects
     *                   (see {@linkplain #container field javadoc}).
     */
    public CitationValidator(final ValidatorContainer container) {
        super(container, "org.opengis.metadata.citation");
    }

    /**
     * Validates the given citation.
     *
     * @param  object  the object to validate, or {@code null}.
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
        validate(toArray(CitationDate.class, object.getDates()));
        for (final Responsibility e : toArray(Responsibility.class, object.getCitedResponsibleParties())) {
            validate(e);
        }
    }

    /**
     * Validates citation dates. If more than one dates is given, then this method will check
     * for the following constraints:
     *
     * <ul>
     *   <li>{@link DateType#CREATION} shall be before or equals to all other type of dates, ignoring user-defined codes.</li>
     *   <li>{@link DateType#LAST_UPDATE} shall be before or equals to {@link DateType#NEXT_UPDATE}.</li>
     *   <li>{@link DateType#VALIDITY_BEGINS} shall be before or equals to {@link DateType#VALIDITY_EXPIRES}.</li>
     * </ul>
     *
     * Those constraints are verified in their iteration order. It is possible for example to have more than one
     * (<var>validity begins</var>, <var>validity expires</var>) pair.
     *
     * @param dates  the citation dates to validate.
     *
     * @since 3.1
     */
    public void validate(final CitationDate... dates) {
        if (dates == null) {
            return;
        }
        Date creation       = null;
        Date lastUpdate     = null;
        Date validityBegins = null;
        final int lastOrdinal = DateType.DISTRIBUTION.ordinal();
        for (final CitationDate date : dates) {
            if (date != null) {
                final DateType type = date.getDateType();
                final Date     time = date.getDate();
                mandatory("CitationDate: shall have a date type.", type);
                mandatory("CitationDate: shall have a timestamp.", time);
                if (type != null && time != null) {
                    if (type.equals(DateType.CREATION)) {
                        creation = time;
                    } else if (type.equals(DateType.LAST_UPDATE)) {
                        lastUpdate = time;
                    } else if (type.equals(DateType.NEXT_UPDATE)) {
                        assertOrdered(DateType.LAST_UPDATE, lastUpdate, DateType.NEXT_UPDATE, time);
                    } else if (type.equals(DateType.VALIDITY_BEGINS)) {
                        validityBegins = time;
                    } else if (type.equals(DateType.VALIDITY_EXPIRES)) {
                        assertOrdered(DateType.VALIDITY_BEGINS, validityBegins, DateType.VALIDITY_EXPIRES, time);
                    }
                    if (type.ordinal() <= lastOrdinal) {
                        assertOrdered(DateType.CREATION, creation, type, time);
                    }
                }
            }
        }
    }

    /**
     * Asserts that the date {@code d2} is equal or after {@code d1}.
     *
     * @param  t1  type of the first date.
     * @param  d1  the first date to compare.
     * @param  t2  type of the second date.
     * @param  d2  the second date to compare.
     */
    private static void assertOrdered(final DateType t1, final Date d1, final DateType t2, final Date d2) {
        if (d1 != null && d2.before(d1)) {
            fail("The ‘" + t2.identifier() + "’ date (" + d2 + ") shall be equal or after "
               + "the ‘" + t1.identifier() + "’ date (" + d1 + ").");
        }
    }

    /**
     * Validates the given responsible party.
     *
     * @param  object  the object to validate, or {@code null}.
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
     * @param  object  the object to validate, or {@code null}.
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
     * @param  object  the object to validate, or {@code null}.
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
     * @param  object  the object to validate, or {@code null}.
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
     * @param  object  the object to validate, or {@code null}.
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
     * @param  object  the object to validate, or {@code null}.
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
