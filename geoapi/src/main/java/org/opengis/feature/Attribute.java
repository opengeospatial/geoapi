package org.opengis.feature;

import org.opengis.feature.type.AttributeDescriptor;
import org.opengis.feature.type.AttributeType;


/**
 * Contains information defining an attirbute (ie aggregation).
 * <p>
 * An Attribute is used to hold a value in our data model, similar
 * to the way a Map.Entry holds values in a Map. Rather then use a Name
 * we use an AttributeType providing additional information such as the
 * java class this Attribtue is "bound" to. Validaiton is provied by way
 * of constraints implemented using Filter.
 * </p>
 * <p>
 * If this Attribute is contained in another data sturcutre you may
 * use the provided Descriptor. This descriptor will provided any additional
 * information (such as name and multiplicity) needed.
 * </p>
 * @author Jody Garnett, Refractions Research
 */
public interface Attribute<B, T extends AttributeType<B>> 
	extends Property<T> {

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