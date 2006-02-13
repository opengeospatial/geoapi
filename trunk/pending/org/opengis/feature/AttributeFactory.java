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

import com.vividsolutions.jts.geom.Geometry;

/**
 * Plays the role of making actual instances of types in this puzzle.
 * <p>
 * </p>
 * 
 * @author Gabriel Roldan, Axios Engineering
 *
 */
public interface AttributeFactory {


	/**
	 * Creates an attribute to hold values of the prescribed <code>type</code>
	 * @param type
	 * @return
	 */
	public Attribute create(AttributeType type, String id);

	/**
	 * 
	 * @param type
	 * @param value
	 * @return
	 */
	public Attribute create(AttributeType type, String id, Object value);
	
	/**
	 * 
	 * @param type
	 * @return
	 */
	public GeometryAttribute create(GeometryType type, String id);
	
	/**
	 * 
	 * @param type
	 * @param crs
	 * @return
	 */
	public GeometryAttribute create(GeometryType type, String id, CoordinateReferenceSystem crs);
	
	/**
	 * 
	 * @param type
	 * @param value
	 * @return
	 */
	public GeometryAttribute create(GeometryType type, String id, Geometry value);
	
	/**
	 * 
	 * @param type
	 * @param crs
	 * @param value
	 * @return
	 */
	public GeometryAttribute create(GeometryType type, String id, CoordinateReferenceSystem crs, Geometry value);
	
	/**
	 * 
	 * @param type
	 * @return
	 */
	public SimpleFeature create(SimpleFeatureType type, String id);
	
	/**
	 * 
	 * @param type
	 * @param values
	 * @return
	 */
	public SimpleFeature create(SimpleFeatureType type, String id, List<? extends Object>values);

	/**
	 * 
	 * @param type
	 * @param values
	 * @return
	 */
	public SimpleFeature create(SimpleFeatureType type, String id, Object []values);

	/**
	 * 
	 * @param type
	 * @return
	 */
	public ComplexAttribute create(ComplexType type, String id);

	/**
	 * 
	 * @param type
	 * @return
	 */
	public Feature create(FeatureType type, String id);
	
	/**
	 * 
	 * @param type
	 * @return
	 */
	public FeatureCollection create(FeatureCollectionType type, String id);
}
