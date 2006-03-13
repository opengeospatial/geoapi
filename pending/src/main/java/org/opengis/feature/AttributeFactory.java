package org.opengis.feature;

import java.util.List;

import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.feature.type.AttributeType;
import org.opengis.feature.type.ComplexType;
import org.opengis.feature.type.FeatureCollectionType;
import org.opengis.feature.type.FeatureType;
import org.opengis.feature.type.GeometryType;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.spatialschema.geometry.Geometry;

/**
 * Plays the role of making actual instances of types in this puzzle.
 * 
 * @author Gabriel Roldan, Axios Engineering
 * @author Justin Deoliveira, The Open Planning Project
 *
 */
public interface AttributeFactory {

	/**
	 * Creates a new attribute.
	 * 
	 * @param type The type of the attribute.
	 * @param id The id of the attribute, may be null depending on the type.
	 * @param value The initial value of the attribute, may be null depending on 
	 * the type of the type of the attribute.
	 * 
	 */
	Attribute create(AttributeType type, String id, Object value);
	
	/**
	 * Creates a new geometry attribute.
	 * 
	 * @param type The type of the attribute. 
	 * @param id The id of the attribute, may be null depending on the type.
	 * @param crs The coordinate reference system of the attribute, may be null.
	 * @param value The initial value of the attribute, may be null depending on 
	 * the type of the type of the attribute.
	 */
	GeometryAttribute create(GeometryType type, String id, CoordinateReferenceSystem crs, Geometry value);
	
	/**
	 * Creates a new complex attribute.
	 * 
	 * @param type The type of the attribute.
	 * @param id The id of the attribute, may be null depending on the type.
	 * @param value The initial value of the attribute, may be null depending on 
	 * the type of the attribute.
	 */
	ComplexAttribute create(ComplexType type, String id, List value);

	/**
	 * Creates a new feature.
	 * 
	 * @param type The type of the feature. 
	 * @param id The id of the feature, (fid), may be null depending on the type.
	 * @param value The initial value of the attribute, may be null depending on 
	 * the type of the feature.
	 */
	Feature create(FeatureType type, String id, List value);
	
	/**
	 * Creates a new simple feature.
	 * 
	 * @param type The type of the feature. 
	 * @param id The id of the feature, (fid), may be null depending on the type.
	 * @param value The initial value of the attribute, may be null depending on 
	 * the type of the feature.
	 */
	SimpleFeature create(SimpleFeatureType type, String id, List value);

	/**
	 * Createsa a new feature collection.
	 * 
	 * @param type The type of the feature collection.
	 * @param id The id of the feature collection, may be null depending on the 
	 * type.
	 */
	FeatureCollection create(FeatureCollectionType type, String id);
}
