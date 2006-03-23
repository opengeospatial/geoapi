package org.opengis.feature.type;

import java.util.Collection;

/**
 * Type builder for creating complex types.
 * 
 * @author Justin Deoliveira, The Open Planning Project, jdeolive@openplans.org
 *
 * @param <T> The attribute type which must be an extension of ComplexType.
 * @param <B> The class the type is bound to which must a Collection.
 */
public interface ComplexTypeBuilder<T extends ComplexType,B extends Collection> 
	extends TypeBuilder<T,B> {

	/**
	 * Adds an attribute descriptor to the type.
	 * 
	 * @param name The name of the attribute.
	 * @param type The type of the attribute.
	 */
	void add(String name, AttributeType type);
	
	/**
	 * Adds an attribute descriptor to the type.
	 * 
	 * @param name The name of the attribute.
	 * @param type The type of the attribute.
	 * @param minOccurs Minimum number of occurences of the attribute.
	 * @param maxOccurs Maximum number of occurences of the attribute.
	 */
	void add(String name, AttributeType type, int minOccurs, int maxOccurs);
}
