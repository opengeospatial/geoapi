package org.opengis.feature;

import java.util.Collection;

import org.opengis.feature.type.AttributeType;
import org.opengis.feature.type.ComplexType;

public interface ComplexAttributeBuilder<C extends Collection<Attribute>, T extends ComplexType<C>, A extends ComplexAttribute<C,T>> {

	//
	// Injection
	//
	// Used to inject dependencies we need during construction time.
	//
	/**
	 * Returns the underlying attribute factory.
	 */
	AttributeFactory getAttributeFactory();
	
	/**
	 * Sets the underlying attribute factory.
	 */
	void setAttributeFactory(AttributeFactory attributeFactory);
	
	// State
	/**
	 * This namespace will be used when constructing AttributeName
	 */
	void setNamespaceURI(String uri );
	
	/**
	 * This namespace will be used when constructing AttributeName
	 * 
	 * @return namespace will be used when constructing AttributeName
	 */
	String getNamespaceURI();
	
	/**
	 * Sets the type with which to build attributes contained within the 
	 * complex attribute being built.
	 * <p>
	 * This method should be called before any calls to {@link #attribute(String, B)}.
	 * </p>
	 * 
	 * @param type The type that the built attribute will implement.
	 */
	void setType(T type);
	
	/**
	 * Adds an attribute to the complex attribute to be created.
	 * <br>
	 * <p>
	 * This method uses the type supplied in {@link #setType(T)} in order to 
	 * determine the attribute type.
	 * </p>
	 * 
	 * @param name
	 * 	The name of the attribute.
	 * @param value
	 * 	The value of the attribute.
	 * 
	 */
	<B> Attribute<B,AttributeType<B>> attribute(String name, B value);
	
	/**
	 * Builds the complex attribute.
	 */
	A build();
	
}
