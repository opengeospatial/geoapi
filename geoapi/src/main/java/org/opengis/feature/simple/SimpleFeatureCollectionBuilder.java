package org.opengis.feature.simple;

import java.util.Collection;

import org.opengis.feature.Attribute;
import org.opengis.feature.FeatureCollectionBuilder;
import org.opengis.feature.type.FeatureType;

public interface SimpleFeatureCollectionBuilder<M extends FeatureType, T extends SimpleFeatureCollectionType<M>> 
	extends FeatureCollectionBuilder<Collection<Attribute>,M,T,SimpleFeatureCollection<M,T>> {

	
	
}
