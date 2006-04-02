package org.opengis.feature.type;

import org.opengis.feature.AttributeName;

/**
 * Indicating a named entry for a perscribed AttributeType.
 * <p>
 * This class carries the ComplexType specific information required
 * for useing a contained attribute. Name, type and multiplicity are defined.
 * <p>
 * If in the future the nature of the containment relationship needs further
 * definition you may expected additional information to be gathered here.
 * </p>
 * 
 * @author Jody Garnett, Refractions Research
 */
public interface AttributeDescriptor<T extends AttributeType> extends PropertyDescriptor {
	
	/** Captures cadinality */
	public int getMinOccurs();
	
	/** Captures cadinality */
	public int getMaxOccurs();
	
	/**
	 * True attribute is allowed to be null.
	 * 
	 * @return true if value may be null, false if value must be present
	 */
	public boolean isNillable();
	
	/** Indicates the type of this attribute */
	T getType();

	
	/**
	 * Indicates Name of defined attribute in a ComplexType, this method may 
	 * never return a null value.
	 */
	AttributeName getName();
}