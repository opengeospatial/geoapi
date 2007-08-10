package org.opengis.feature.xml;

import java.util.List;
//import java.util.Collection;

import org.opengis.feature.Association;
import org.opengis.feature.Attribute;
import org.opengis.feature.ComplexAttribute;
import org.opengis.feature.Property;

//Java 1.4 imports
//import org.opengis.feature.type.AttributeType;

/**
 * Represents a ComplexAttribute in which attribtues are perscribed an order.
 * <p>
 * I cannot yet think of a way to descirbe this limitation using a constraint.
 * On the bright side ComplexAttribute does not perscribe an order so this subclass
 * is free to impose one with out impact.
 * </p>
 * <p>
 * You may mark your Sequence "inline" as required to match your XML schema.
 * </p>
 * @author Jody Garnett, Refractions Research
 */
public interface Sequence extends ComplexAttribute {
    
	/**
	 * Indicates that this ComplexType is to maintain its attributes
	 * in the perscribed order.
	 */
    SequenceType getType();
	
    /**
     * Attributes are to be maintained in a sequence, the order of this
     * sequence to to be in agreement with that described by the SequenceType.
     * @return List<Property> List of properties (attribtues or associations) in order expected.
     */
	List<Property> getValue();
	
	/**
	 * List of properties (the order should agree with the SequenceType).
	 * @param values Ordered List<Property> of attributes or associations 
	 */
	void setValue(List<Property> values);
	
	/**
	 * Ordered view of associations.
	 */
	List<Association> associations();

	/**
	 * Ordered view of attributes.
	 */
	List<Attribute> attributes();
}