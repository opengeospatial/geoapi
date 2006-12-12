package org.opengis.service;

//Annotations
import static org.opengis.annotation.Obligation.MANDATORY;
import static org.opengis.annotation.Obligation.OPTIONAL;
import static org.opengis.annotation.Specification.ISO_19119;

import java.util.Collection;

import org.opengis.annotation.UML;
import org.opengis.metadata.MetadataEntity;

/**
 * The parameters that are required for an interface.
 *
 * @author Mauricio Pazos - Axios Engineering
 * @author Gabriel Roldan - Axios Engineering
 * 
 * @version $Id$ 
 */
@UML(identifier="SV_Parameter", specification=ISO_19119)
public interface Parameter extends MetadataEntity{
	
	/**
	 * The name, as used by the service for this parameter.
	 * 
	 * @return String 
	 */
    @UML(identifier="name", obligation=MANDATORY, specification=ISO_19119)
	String getName();

	/**
	 * Indication if the parameter is an input to the service, an output or both.
	 * 
	 * @return Direction
	 */
    @UML(identifier="direction", obligation=OPTIONAL, specification=ISO_19119)
	ParameterDirection getDirection();

	/**
	 * A narrative explanation of the role of the parameter.
	 * 
	 * @return String
	 */
    @UML(identifier="description", obligation=OPTIONAL, specification=ISO_19119)
	String getDescription();

	/**
	 * Indication if the parameter is required
	 * 
	 * @return String
	 */
    @UML(identifier="optionality", obligation=MANDATORY, specification=ISO_19119)
	Optionality getOptionality();
	
	/**
	 * Indication if more than one value of the parameter may be provided.
	 * 
	 * @return Boolean
	 */
    @UML(identifier="repeatability", obligation=MANDATORY, specification=ISO_19119)
	Boolean getRepeatability();
	
	
	/**
	 * value type
	 * 
	 * @return Collection of CodeList
	 */
    @UML(identifier="valueType", obligation=OPTIONAL, specification=ISO_19119)
	Collection getValueTypes();
}
