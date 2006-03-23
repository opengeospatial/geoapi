package org.opengis.feature.type;

import java.util.Collection;


public interface FeatureCollectionTypeBuilder 
	extends FeatureTypeBuilder<FeatureCollectionType,Collection> {
	
	/**
	 * Adds a feature type to the type member set of the created feature
	 * collection type.
	 * 
	 * @param type
	 */
	void addMemberType(FeatureType type);
}
