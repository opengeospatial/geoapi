package org.opengis.feature;

import org.opengis.feature.type.AttributeType;


public interface Attribute {

	/**
	 * Indicate the AttributeType of this content.
	 * 
	 * @return AttributeType information descirbing allowable content
	 */
	AttributeType<?> getType();
	
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
	Object get();

	/**
	 * Set content to newZValue
	 * @param newValue
	 *            Must be of type indicated by type()
	 */
	void set(Object newValue)throws IllegalArgumentException;
}