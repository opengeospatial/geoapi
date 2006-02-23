package org.opengis.feature;

import org.opengis.feature.type.AttributeDescriptor;
import org.opengis.feature.type.AttributeType;


/**
 * Contains information of an indicated type.
 * 
 * @author Jody Garnett, Refractions Research
 */
public interface Attribute<T> {

	/**
	 * Indicates the AttirbuteDescriptor for this content.
	 * <p>
	 * The attribute descriptor formally captures the name and multiplicity
	 * and type associated with this attirbute.
	 * </p>
	 * @return Descriptor for this attribute.
	 */
	AttributeDescriptor getDescriptor();

	/**
	 * AttributeName (from the descriptor) for this attribute.
	 * 
	 * @return name of this attrubute.
	 */
	AttributeName name();
	
	/**
	 * Indicate the AttributeType (from the descriptor) of this content.
	 * 
	 * @return AttributeType information descirbing allowable content
	 */
	AttributeType<T> type();

    /**
     * Unique, inmutable identification for domain object being modeled.
     *  
     * @return Unique ID, may not be null if getType().isIdentifiable() is true
     */
    String getID();
   
	/**
	 * Access to the content of this attribtue.
	 * 
	 * @return Value of the type indicated by type()
	 */
	T get();

	/**
	 * Set content to newZValue
	 * @param newValue
	 *            Must be of type indicated by type()
	 */
	void set(T newValue)throws IllegalArgumentException;
}