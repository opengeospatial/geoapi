/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.metadata.citation;

// J2SE direct dependencies
import java.util.Locale;


/**
 * Location of the responsible individual or organization.
 *
 * @UML datatype CI_Address
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
public interface Address {
    /**
     * Address line for the location (as described in ISO 11180, Annex A).
     * Returns an empty array if none.
     *
     * @UML optional deliveryPoint
     */
    String[] getDeliveryPoints();

    /**
     * Returns the city of the location
     * Returns <code>null</code> if unspecified.
     *
     * @param  locale The desired locale for the name to be returned, or <code>null</code>
     *         for a name in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The name in the given locale.
     *         If no name is available in the given locale, then some default locale is used.
     * @UML optional city
     */
    String getCity(Locale locale);

    /**
     * State, province of the location.
     * Returns <code>null</code> if unspecified.
     *
     * @param  locale The desired locale for the name to be returned, or <code>null</code>
     *         for a name in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The name in the given locale.
     *         If no name is available in the given locale, then some default locale is used.
     * @UML optional administrativeArea
     */
    String getAdministrativeArea(Locale locale);

    /**
     * ZIP or other postal code.
     * Returns <code>null</code> if unspecified.
     *
     * @UML optional postalCode
     */
    String getPostalCode();

    /**
     * Country of the physical address.
     * Returns <code>null</code> if unspecified.
     *
     * @param  locale The desired locale for the name to be returned, or <code>null</code>
     *         for a name in some default locale (may or may not be the
     *         {@linkplain Locale#getDefault() system default}).
     * @return The name in the given locale.
     *         If no name is available in the given locale, then some default locale is used.
     * @UML optional country
     */
    String getCountry(Locale locale);

    /**
     * Address of the electronic mailbox of the responsible organization or individual.
     * Returns an empty array if none.
     *
     * @UML optional electronicMailAddress
     */
    String[] getElectronicMailAddresses();
}
