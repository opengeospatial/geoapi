package org.opengis.feature.xml;

import java.util.List;

import org.opengis.feature.Attribute;
import org.opengis.feature.ComplexAttribute;

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
public interface Sequence<L extends List<Attribute>, T extends SequenceType<L>> 
	extends ComplexAttribute<Attribute,L,T> {
    
	/**
	 * Indicates that this ComplexType is to maintain its attributes
	 * in the perscribed order.
	 */
    T getType();
	
    /**
     * Attributes are to be maintained in a sequence, the order of this
     * sequence to to be in agreement with that described by the SequenceType.
     */
	L get();
}