package org.opengis.feature.xml;

import java.util.Collection;
import java.util.List;

import org.opengis.feature.Attribute;
import org.opengis.feature.type.AssociationDescriptor;
import org.opengis.feature.type.AttributeDescriptor;
import org.opengis.feature.type.ComplexType;
import org.opengis.feature.type.StructuralDescriptor;

// Java 1.4 
//import java.util.Collection;
/**
 * Indicates a ComplexType that indicates a required ordering of attributes.
 * <p>
 * You may mark your sequence as "inline" as dictated by the needs of your XML Schema.
 * </p>
 * 
 * @author Jody Garnett, Refractions Research
 */
public interface SequenceType extends ComplexType {
	
	List<StructuralDescriptor> getProperties();
	
	List<AssociationDescriptor> associations();
	
	/** Indicates required ordering for Attributes */
    List<AttributeDescriptor> attributes();
}