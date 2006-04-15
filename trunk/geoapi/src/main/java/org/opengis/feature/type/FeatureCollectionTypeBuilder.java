package org.opengis.feature.type;

import java.util.Collection;
import java.util.Set;

import org.opengis.feature.Attribute;


public interface FeatureCollectionTypeBuilder<C extends Collection<Attribute>, M extends FeatureType,T extends FeatureCollectionType<C,M>>
	extends FeatureTypeBuilder<C,T> {

	/**
	 * Adds a FeatureType association to the collection.
	 * <p>
	 * <ul>
	 * 	<li>Name is based on getNamespaceURI() + <code>name</code>
	 * </ul>
	 * </p>
	 * <p>
	 * The FeatureType constraints the allowable Feature members of a feature 
	 * collection. In other words, added features must implement one of the 
	 * types in association set.
	 * </p>
	 * 
	 * 
	 */
	void addMemberType(String name, AssociationType<M> memberType);
	
	/**
	 * Adds a FeatureType association to the collection.
	 * <p>
	 * <ul>
	 * 	<li>Name is based on <code>namespaceURI</code> + <code>name</code>
	 * </ul>
	 * </p>
	 * <p>
	 * The FeatureType constraints the allowable Feature members of a feature 
	 * collection. In other words, added features must implement one of the 
	 * types in association set.
	 * </p>
	 */
	void addMemberType(String namespaceURI, String name, AssociationType<M> memberType);
	
	/**
	 * Adds a FeatureType association to the collection.
	 * <p>
	 * The FeatureType constraints the allowable Feature members of a feature 
	 * collection. In other words, added features must implement one of the 
	 * types in association set.
	 * </p>
	 */
	void addMemberType(Name name, AssociationType<M> memberType);
	
}
