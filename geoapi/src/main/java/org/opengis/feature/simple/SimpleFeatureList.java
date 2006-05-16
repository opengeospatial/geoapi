package org.opengis.feature.simple;

import java.util.Collection;

import org.opengis.feature.Attribute;
import org.opengis.feature.FeatureList;
import org.opengis.feature.type.FeatureType;

public interface SimpleFeatureList<M extends FeatureType, T extends SimpleFeatureCollectionType<M>>
	extends SimpleFeatureCollection<M,T>, 
	FeatureList<Collection<Attribute>,M,T> {

}
