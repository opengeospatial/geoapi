package org.opengis.feature.simple;

import java.util.Collection;

import org.opengis.feature.Attribute;
import org.opengis.feature.FeatureList;

public interface SimpleFeatureList<M extends SimpleFeatureType, T extends SimpleFeatureCollectionType<M>>
	extends SimpleFeatureCollection<M,T>,
	FeatureList<Attribute,Collection<Attribute>,M,T,SimpleFeature> {

}
