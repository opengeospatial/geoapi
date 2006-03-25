package org.opengis.feature.type;

import java.util.Collection;
import java.util.List;

import org.opengis.feature.Attribute;


public interface FeatureCollectionTypeBuilder<C extends Collection<Attribute>, M extends FeatureType,T extends FeatureCollectionType<C,M>>
	extends FeatureTypeBuilder<C,T> {
	
	/**
	 * List of memeber types to be used as children for the created FeatureCollection.
	 */
	List<M> getMemberTypes();	
}
