package org.opengis.feature;

import java.util.Collection;

import org.opengis.feature.type.FeatureCollectionType;
import org.opengis.feature.type.FeatureType;

public interface FeatureCollectionBuilder<C extends Collection<Attribute>, M extends FeatureType, T extends FeatureCollectionType<C,M>, A extends FeatureCollection<C,M,T>> 
	extends FeatureBuilder<C,T,A> {

	
}