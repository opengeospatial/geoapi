/*
 * $ Id $
 * $ Source $
 * Created on Apr 5, 2005
 */
package org.opengis.metadata;

import java.net.URI;
import java.util.Collection;

import org.opengis.metadata.citation.Address;
import org.opengis.metadata.citation.Contact;
import org.opengis.metadata.citation.OnLineFunction;
import org.opengis.metadata.citation.OnLineResource;
import org.opengis.metadata.citation.ResponsibleParty;
import org.opengis.metadata.citation.Role;
import org.opengis.metadata.citation.Telephone;
import org.opengis.util.InternationalString;


/**
 * The <code>MetadataFactory</code> class/interface...
 * 
 * @author SYS Technologies
 * @author crossley
 * @version $Revision $
 */
public interface MetadataFactory {

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
