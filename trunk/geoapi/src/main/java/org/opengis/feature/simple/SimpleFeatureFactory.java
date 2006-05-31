package org.opengis.feature.simple;

import java.util.Collection;

import org.opengis.feature.AttributeFactory;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureCollection;
import org.opengis.feature.simple.SimpleFeatureCollectionType;
import org.opengis.feature.simple.SimpleFeatureType;

/**
 * This interface denotes a factory capable of producing SimpleFeature.
 * <p>
 * This is an abstract factory describing how to create a set of classes
 * targeted towards a SimpleFeature implementation. The methods below define no
 * additional capability over Simp
 * </p>
 * 
 * @author Jody Garnett
 */
public interface SimpleFeatureFactory extends AttributeFactory {
	public SimpleFeature createSimpleFeature(SimpleFeatureType type, String id,
			Object attributes[]);

	public SimpleFeatureCollection createSimpleFeatureCollection(
			SimpleFeatureCollectionType type, String id, Collection contents);
}
