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

import java.util.Collection;
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
    Collection<? extends Telephone> getPhones();

    /**
     * Telephone numbers at which the organization or individual may be contacted.
     * Returns {@code null} if none.
     *
     * @return telephone numbers at which the organization or individual may be contacted,
     *         or {@code null}.
     *
     * @deprecated As of ISO 19115:2014, replaced by {@link #getPhones()}.
     */
    @Deprecated
    Telephone getPhone();

    /**
     * Physical and email addresses at which the organization or individual may be contacted.
     * Returns an empty collection if none.
     *
     * @return physical and email addresses at which the organization or individual may be contacted.
     *
     * @since 3.1
     */
    @UML(identifier="address", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends Address> getAddresses();

    /**
     * Physical and email address at which the organization or individual may be contacted.
     * Returns {@code null} if none.
     *
     * @return physical and email address at which the organization or individual may be contacted,
     *         or {@code null}.
     *
     * @deprecated As of ISO 19115:2014, replaced by {@link #getAddresses()}.
     */
    @Deprecated
    Address getAddress();

    /**
     * On-line information that can be used to contact the individual or organization.
     * Returns an empty collection if none.
     *
     * @return on-line information that can be used to contact the individual or organization.
     *
     * @since 3.1
     */
    @UML(identifier="onlineResource", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends OnlineResource> getOnlineResources();

    /**
     * On-line information that can be used to contact the individual or organization.
     * Returns {@code null} if none.
     *
     * @return on-line information that can be used to contact the individual or organization,
     *         or {@code null}.
     *
     * @deprecated As of ISO 19115:2014, replaced by {@link #getOnlineResources()}.
     */
    @Deprecated
    OnlineResource getOnlineResource();

    /**
     * Time period (including time zone) when individuals can contact the organization or individual.
     * Returns an empty collection if none.
     *
     * @return time period when individuals can contact the organization or individual.
     */
    @UML(identifier="hoursOfService", obligation=OPTIONAL, specification=ISO_19115)
    Collection<? extends InternationalString> getHoursOfService();

    /**
     * Supplemental instructions on how or when to contact the individual or organization.
     * Returns {@code null} if none.
     *
     * @return supplemental instructions on how or when to contact the individual or organization,
     *         or {@code null} if none.
     */
    @UML(identifier="contactInstructions", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getContactInstructions();

    /**
     * Type of the contact.
     * Returns {@code null} if none.
     *
     * @return type of the contact, or {@code null} if none.
     *
     * @since 3.1
     */
    @UML(identifier="contactType", obligation=OPTIONAL, specification=ISO_19115)
    InternationalString getContactType();
}
