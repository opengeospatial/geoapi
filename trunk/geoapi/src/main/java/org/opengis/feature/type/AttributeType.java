package org.opengis.feature.type;

import org.opengis.feature.Attribute;

/**
 * The type of an Attribute.
 * <p>
 * An AttributeType is an extension of {@link PropertyType} which defines some 
 * additional information: 
 * <ul>
 *  <li>if the attribute is identified or not
 * </ul>
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
	//Collection<OperationDescriptor> getOperations();
}