package org.opengis.feature.simple;

import java.util.List;

import org.opengis.feature.Attribute;
import org.opengis.feature.FeatureBuilder;

public interface SimpleFeatureBuilder extends FeatureBuilder<Attribute,List<Attribute>,SimpleFeatureType,SimpleFeature> {

	/**
	 * Adds an attribute to the complex attribute to be created.
	 * <p>
	 * This method uses the number of attributes previously added to the 
	 * to locate the type of the attribute assuming that the attributes are 
	 * added in the same order as the attribute types are defined in the 
	 * associated {@link SimpleFeatureType}
	 * </p>
	 * <p>
	 * This method uses the type supplied in {@link #setType(T)} in order to 
	 * determine the attribute type.
	 * </p>
	 * 
	 * @param value
	 * 	The value of the attribute.
	 * 
	 * 
	 */
	void add(Object value);
	
	/**
	 * Adds an attribute to the complex attribute to be created.
	 * <p>
	 * This method uses the number of attributes previously added to the 
	 * to locate the type of the attribute assuming that the attributes are 
	 * added in the same order as the attribute types are defined in the 
	 * associated {@link SimpleFeatureType}
	 * </p>
	 * <p>
	 * This method uses the type supplied in {@link #setType(T)} in order to 
	 * determine the attribute type.
	 * </p>
	 * 
	 * @param id
	 * 	The id of the attribute.
	 * 
	 * @param value
	 * 	The value of the attribute.
	 * 
	 */
	void add(String id, Object value);
}
