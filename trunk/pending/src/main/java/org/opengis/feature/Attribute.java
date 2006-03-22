package org.opengis.feature;

import org.opengis.feature.type.AttributeDescriptor;
import org.opengis.feature.type.AttributeType;


/**
 * Contains information as defined by an AttributeDescriptor.
 * <p>
 * The AttributeDescriptor defines both the name and the type (T) of
 * this attribute. The type define the java class (B) to which
 * this attribute is bound.
 * </p>
 * @author Jody Garnett, Refractions Research
 */
public interface Attribute<B, T extends AttributeType<B>> {

	/**
	 * Indicates the AttirbuteDescriptor for this content.
	 * <p>
	 * The attribute descriptor formally captures the name and multiplicity
	 * and type associated with this attirbute.
	 * </p>
	 * @return Descriptor for this attribute.
	 */
	AttributeDescriptor<T> getDescriptor();

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
	T type();

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
	B get();

	/**
	 * Set content to newZValue
	 * @param newValue
	 *            Must be of type indicated by type()
	 */
	void set(B newValue)throws IllegalArgumentException;
}