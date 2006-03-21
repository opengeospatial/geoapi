package org.opengis.feature.type;

import java.util.Collection;


public interface FeatureCollectionType extends FeatureType {	
	/**
	 * FeatureTypes allowable as members of this collection.
	 * <p>
	 * Please note that all the normal AttributeTypes defined by this FeatureCollection
	 * (such as bounds) are considered to be derivative from the member features
	 * </p>
	 */
	Collection<FeatureType> getMemberTypes();
}