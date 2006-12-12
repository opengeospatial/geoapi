package org.opengis.service;

//Annotations
import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Specification.ISO_19119;

import java.util.Collection;

import org.opengis.annotation.UML;
import org.opengis.metadata.citation.ResponsibleParty;
import org.opengis.util.InternationalString;

/**
 * Describes an organization that provides services.
 * 
 * @author Mauricio Pazos - Axios Engineering
 * @author Gabriel Roldan - Axios Engineering
 */
@UML(identifier="SV_ServiceProvider", specification=ISO_19119)
public interface ServiceProvider {
	
	/**
	 * A unique identifier for this organization
	 * 
	 * @return String 
     * @todo return type should be {@link InternationalString}
	 */
    @UML(identifier="providerName", obligation=MANDATORY, specification=ISO_19119)
    String getProviderName();

	/**
	 * Information for contacting the service provider
	 * 
	 * @return ResponsibleParty 
	 */
    @UML(identifier="serviceContact", obligation=MANDATORY, specification=ISO_19119)
	ResponsibleParty getServiceContact();
	
	
	/**
	 * Services provided by this service provider
	 * 
	 * @return Collection of ServiceIdentification
	 */
    @UML(identifier="services", obligation=MANDATORY, specification=ISO_19119)
	Collection <ServiceIdentification> getServices();

	
}
