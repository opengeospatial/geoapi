package org.opengis.feature.type;

import java.util.Collection;
import java.util.Set;

import org.opengis.feature.Attribute;


public interface FeatureCollectionTypeBuilder<C extends Collection<Attribute>, M extends FeatureType,T extends FeatureCollectionType<C,M>>
	extends FeatureTypeBuilder<C,T> {

	/**
	 * Adss a  memeber type to be used as children for the created 
	 * FeatureCollection.
	 */
	void addMemberType(M memberType);
	
	/**
	 * Sets memeber types to be used as children for the created 
	 * FeatureCollection.
	 */
	void setMemberTypes(Set<M> memeberTypes);
	
	/**
	 * List of memeber types to be used as children for the created 
	 * FeatureCollection.
	 */
	Set<M> getMemberTypes();	
}
