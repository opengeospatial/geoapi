package org.opengis.feature.type;

import java.util.List;

import org.opengis.feature.Attribute;

/**
 * Type of content stored in an attribute.
 */
public interface AttributeType extends PropertyType {

	/**
	 * Indicates if the type is identified or not.
	 * <p>
	 * If this method returns <code>true</code>, then the corresponding 
	 * attribute must have a unique identifier.
	 * </p>
	 * 
	 * @return <code>true</code> if the attribute is identified, otherwise <code>false</code>.
	 * @see Attribute#getID()
	 */
	boolean isIdentified();

	/**
	 * Override of {@link PropertyType#getSuper()} which type narrows to 
	 * {@link AttributeType}.
	 * 
	 * @see PropertyType#getSuper()
	 */
	AttributeType getSuper();
	
	/**
	 * Operations that may be invoked against this type.
	 * 
	 * @return Collection<OperationDescriptor> that may be invoked on values of this type.
	 */
	List<Operation> getOperations();
}