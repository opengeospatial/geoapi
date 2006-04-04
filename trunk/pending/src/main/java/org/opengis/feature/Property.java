package org.opengis.feature;

import org.opengis.feature.type.AttributeDescriptor;
import org.opengis.feature.type.AttributeType;
import org.opengis.feature.type.Name;
import org.opengis.feature.type.PropertyDescriptor;
import org.opengis.feature.type.PropertyType;


/**
 * Contains information as defined by an descriptor.
 * <p>
 * A Property is used to hold information in our data model, similar to the
 * way a Map.Entry holds values in a Map. Rathen then
 * simpliy defining a Name, we are using a slightly more rich data
 * structure called a ProperType.
 * </p>
 * There are two useful PropertyTypes:
 * <ul>
 * <li>AttributeType - containing data
 * <li>AssociationType - linking to other typed objects
 * </ul>
 * <p>
 * If this property is contained in another data structure you may
 * use the provided Descriptor. This descriptor will provide any
 * additional information (such as the name and multiplicity) needed.
 * </p>
 * @author Jody Garnett, Refractions Research
 */
public interface Property<T extends PropertyType> {

	/**
	 * Indicates the Descriptor for this content.
	 * <p>
	 * The attribute descriptor formally captures the name and multiplicity
	 * information and type information.
	 * </p>
	 * @return Descriptor, may be null if Property is not contained in a complex attribute.
	 */
	PropertyDescriptor getDescriptor();

	/**
	 * Name (from the descriptor) of this Property.
	 * 
	 * @return name of this property.
	 */
	Name name();
		
	/**
	 * Indicate the PropertyType, if we have a descriptor it will be in agreement.
	 * 
	 * @return PropertyType information descirbing content and use
	 */
	T getType();
 
}