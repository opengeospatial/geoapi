package org.opengis.feature.type;


/**
 * Containment information for a complex attribute.
 * <p>
 * ComplexType specific information such as Name, type and multiplicity are defined.
 * <p>
 * If in the future the nature of the containment relationship needs further
 * definition you may expected additional information to be gathered here.
 * </p>
 * 
 * @author Jody Garnett, Refractions Research
 */
public interface StructuralDescriptor extends PropertyDescriptor {
	
	/** Captures cadinality */
	int getMinOccurs();
	
	/** Captures cadinality */
	int getMaxOccurs();
	
	/**
	 * Indicates Name of defined attribute in a ComplexType, this method may 
	 * never return a null value.
	 */
	Name getName();
}