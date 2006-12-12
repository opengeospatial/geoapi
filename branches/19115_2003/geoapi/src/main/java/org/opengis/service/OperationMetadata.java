package org.opengis.service;

//Annotations
import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Obligation.OPTIONAL;
import static org.opengis.annotation.Specification.ISO_19119;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.opengis.annotation.UML;
import org.opengis.metadata.MetadataEntity;
import org.opengis.metadata.citation.OnLineResource;

/**
 *  Provides descriptive data for a Service instance sufficient to allow a client to 
 *  invoke the service
 *
 * @author Mauricio Pazos - Axios Engineering
 * @author Gabriel Roldan - Axios Engineering
 */
@UML(identifier="SV_OperationMetadata", specification=ISO_19119)
public interface OperationMetadata extends MetadataEntity{

	/**
	 * A unique identifier for this interface.
	 * 
	 * @return String
	 */
    @UML(identifier="operationName", obligation=MANDATORY, specification=ISO_19119)
	String getOperationName();

	/**
	 * Distributed Computing Platforms on which the operation has been
	 * implemented
	 * 
	 * @return Collection of DCPList
	 */
    @UML(identifier="DCP", obligation=MANDATORY, specification=ISO_19119)
	Collection <DCPList> getDcp();

	/**
	 * Free text description of the intent of the operation and the results of
	 * the operation.
	 * 
	 * @return String
	 */
    @UML(identifier="operationDescription", obligation=OPTIONAL, specification=ISO_19119)
	String	getOperationDescription();

	/**
	 * The name used to invoke this interface within the context of the DCP.
	 * 
	 * @return String
	 */
    @UML(identifier="invocationName", obligation=OPTIONAL, specification=ISO_19119)
	String getInvocationName();
	
	/**
	 * The sequence of parameters that are required for this interface.
	 *
	 * @return Set sequnce of parameters
	 */
    @UML(identifier="parameters", obligation=OPTIONAL, specification=ISO_19119)
	Set<Parameter> getParameters();
	
	/**
	 * Handle for accessing the service interface.
	 * 
	 * @return Collection of OnLineResource
	 */
    @UML(identifier="connectPoints", obligation=MANDATORY, specification=ISO_19119)
	Collection <OnLineResource> getConnectPoint();

	/**
	 * List of operations that must be completed immediately before current
	 * operation is invoked, structured as a list for capturing alternate
	 * predecessor paths and sets for capturing parallel predecessor paths.
	 * 
	 * @return Set of OperationName (as String)
	 */
    @UML(identifier="dependsOn", obligation=OPTIONAL, specification=ISO_19119)
	List<String> getDependsOn(); 
}