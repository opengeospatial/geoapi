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
import java.util.Set;

// OpenGIS direct dependencies
import org.opengis.util.InternationalString;


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
    Set/*<String>*/ getDeliveryPoints();

    /**
     * Returns the city of the location
     * Returns <code>null</code> if unspecified.
     *
     * @UML optional city
     */
    InternationalString getCity();

    /**
     * State, province of the location.
     * Returns <code>null</code> if unspecified.
     *
     * @UML optional administrativeArea
     */
    InternationalString getAdministrativeArea();

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
     * @UML optional country
     */
    InternationalString getCountry();

    /**
     * Address of the electronic mailbox of the responsible organization or individual.
     * Returns an empty array if none.
     *
     * @UML optional electronicMailAddress
     */
    Set/*<String>*/ getElectronicMailAddresses();
}
