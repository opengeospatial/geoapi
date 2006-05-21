package org.opengis.feature;

import java.util.Collection;

import org.opengis.feature.type.FeatureCollectionType;
import org.opengis.feature.type.FeatureType;

public interface FeatureCollectionBuilder<E extends Property, C extends Collection<E>, M extends FeatureType, T extends FeatureCollectionType<E,C,M>, A extends FeatureCollection<E,C,M,T>> 
	extends FeatureBuilder<E,C,T,A> {

	
}