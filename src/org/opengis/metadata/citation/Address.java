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

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * Location of the responsible individual or organization.
 *
 * @author ISO 19115
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-111.pdf">Abstract specification 5.0</A>
 */
///@UML (identifier="CI_Address")
public interface Address {
    /**
     * Address line for the location (as described in ISO 11180, Annex A).
     * Returns an empty array if none.
     */
/// @UML (identifier="deliveryPoint", obligation=OPTIONAL)
    Set/*<String>*/ getDeliveryPoints();

    /**
     * Returns the city of the location
     * Returns <code>null</code> if unspecified.
     */
/// @UML (identifier="city", obligation=OPTIONAL)
    InternationalString getCity();

    /**
     * State, province of the location.
     * Returns <code>null</code> if unspecified.
     */
/// @UML (identifier="administrativeArea", obligation=OPTIONAL)
    InternationalString getAdministrativeArea();

    /**
     * ZIP or other postal code.
     * Returns <code>null</code> if unspecified.
     */
/// @UML (identifier="postalCode", obligation=OPTIONAL)
    String getPostalCode();

    /**
     * Country of the physical address.
     * Returns <code>null</code> if unspecified.
     */
/// @UML (identifier="country", obligation=OPTIONAL)
    InternationalString getCountry();

    /**
     * Address of the electronic mailbox of the responsible organization or individual.
     * Returns an empty array if none.
     */
/// @UML (identifier="electronicMailAddress", obligation=OPTIONAL)
    Set/*<String>*/ getElectronicMailAddresses();
}
