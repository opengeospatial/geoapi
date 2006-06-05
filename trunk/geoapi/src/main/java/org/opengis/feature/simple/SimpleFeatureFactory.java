package org.opengis.feature.simple;

import org.opengis.feature.AttributeFactory;

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
	/**
	 * Creates a new simple feature.
	 * 
	 * @param type Type of SimpleFeature to be created
	 * @param id The id of the feature, (fid), may be null depending on the type.
	 * @param values Values order dicated by provided <code>type</code>
	 * 
	 * @throws IllegalArgumentException If desc.getType() does not return an 
	 * instanceof {@link SimpleFeatureType}.
	 */
	public SimpleFeature createSimpleFeature(SimpleFeatureType type, String id,
			Object values[]);

	/**
	 * Createsa a new simple feature collection.
	 * 
	 * @param type Type of SimpleFeatureCollection to be created
	 * @param id The id of the feature collection
	 * 
	 * @throws IllegalArgumentException If desc.getType() does not return an 
	 * instanceof {@link org.opengis.feature.simple.SimpleFeatureCollectionType}.
	 */
	public SimpleFeatureCollection createSimpleFeatureCollection(
			SimpleFeatureCollectionType type, String id);
		
}
