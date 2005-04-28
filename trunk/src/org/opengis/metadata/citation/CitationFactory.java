/*
 * $ Id $
 * $ Source $
 * Created on Apr 5, 2005
 */
package org.opengis.metadata.citation;

import java.net.URI;
import java.util.Collection;

import org.opengis.util.InternationalString;


/**
 * The <code>CitationFactory</code> class/interface...
 * 
 * @author Open GIS Consortium, Inc.
 * @version $Revision$, $Date$
 * @version $Revision $
 */
public interface CitationFactory {

    //**  Citation  **
    
    Address createAddress(
            Collection/*<String>*/ deliveryPoints,
            InternationalString city,
            InternationalString administrativeArea,
            String postalCode, 
            InternationalString country,
            Collection/*<String>*/ electronicMailAddresses);
    
    Contact createContact(
            Telephone phone, 
            Address address, 
            OnLineResource onLineResource,
            InternationalString hoursOfService,
            InternationalString contactInstructions);
    
    OnLineResource createOnLineResource(
            URI linkage, 
            String protocol, 
            String applicationProfile, 
            InternationalString description, 
            OnLineFunction function);
    
    ResponsibleParty createResponsibleParty(
            String individualName,
            InternationalString organisationName,
            InternationalString oositionName,
            Contact contactInfo,
            Role role);
    
    Telephone createTelephone(
            String voice,
            String facsimile);
}
