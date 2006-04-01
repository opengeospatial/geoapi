package org.opengis.feature.type;

import org.opengis.feature.AttributeName;

/**
 * Indicating an implementation of a opperation type for a specific type.
 * <p>
 * This class carries the ComplexType specific information requried
 * for using an opperation. Name, type and evaulation are defined.
 * <p>
 * Please see the description of OpperationType for more guidelines on capturing
 * opperations for use with your model.
 * </p>
 * @author Jody Garnett, Refractions Research
 */
public interface OpperationDescriptor<B, T extends AttributeType<B>, O extends OpperationType> extends PropertyDescriptor {
	
	
	/** Indicates the type of this attribute */
	O getType();

	/**
	 * Indicates Name of defined opperation in a ComplexType, this method may 
	 * never return a null value.
	 */
	Name getName();
	
	Object call( Attribute<T> value ); 
}