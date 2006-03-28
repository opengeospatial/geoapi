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
	 * information for this attribute. If this attribute is not contained
	 * in a container, then the descriptor will be null.
	 * </p>
	 * @return Descriptor for this attribute, may be null.
	 */
	AttributeDescriptor<T> getDescriptor();

	/**
	 * AttributeName (from the descriptor) for this attribute.
	 * 
	 * @return name of this attrubute.
	 */
	AttributeName name();
	
	/**
	 * Determines if the attribute is allowed to have a <code>null</code> value.
	 * <p>
	 *	For those attributes which are contained within a complex type 
	 *	(ie. getDescriptor() != null), this method defers to the descriptor
	 * </p>
	 * 
	 * @see AttributeDescriptor#isNillable()
	 */
	boolean nillable();
	
	/**
	 * Indicate the AttributeType, if we have a descriptor it will be in agreement.
	 * 
	 * @return AttributeType information descirbing allowable content
	 */
	T getType();

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