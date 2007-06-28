package org.opengis.feature.xml;

import java.util.List;

import org.opengis.feature.Attribute;
import org.opengis.feature.type.ComplexType;

/**
 * Indicates a ComplexType that indicates a required ordering of attributes.
 * <p>
 * You may mark your sequence as "inline" as dictated by the needs of your XML Schema.
 * </p>
 * 
 * @author Jody Garnett, Refractions Research
 */
public interface SequenceType<L extends List<Attribute>> extends ComplexType<Attribute,L> {
	
	/** Indicates required ordering for Attributes */
    ///	List<AttributeDescriptor> getAttributes();
}