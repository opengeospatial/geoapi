package org.opengis.feature.xml;

import java.util.List;

import org.opengis.feature.Attribute;
import org.opengis.feature.ComplexAttribute;
import org.opengis.feature.type.ComplexType;

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
///	SequenceType getType();
	
    /**
     * Attributes are to be maintained in a sequence, the order of this
     * sequence to to be in agreement with that described by the SequenceType.
     */
	List<Attribute> get();
}