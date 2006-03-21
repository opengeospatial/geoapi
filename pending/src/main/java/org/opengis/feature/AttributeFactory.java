package org.opengis.feature;

import java.util.Collection;
import java.util.List;

import org.opengis.feature.simple.BooleanAttribute;
import org.opengis.feature.simple.NumericAttribute;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureCollection;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.feature.simple.TemporalAttribute;
import org.opengis.feature.simple.TextAttribute;
import org.opengis.feature.type.AttributeDescriptor;
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
	 * @param descriptor The attribute descriptor.
	 * @param id The id of the attribute, may be null depending on type.
	 * @param value The value of the attribute, may be null depending on type.
	 */
	Attribute createAttribute(AttributeDescriptor descriptor, String id , Object value);
	
	/**
	 * Creates a new boolean attribute.
	 * 
	 * @param descriptor The attribute descriptor.
	 * @param value The boolean value of the attribute.
	 */
	BooleanAttribute createBooleanAttribute(AttributeDescriptor descriptor, Boolean value);
	
	/**
	 * Creates a new numberic attribute.
	 * 
	 * @param descriptor The attribute descriptor.
	 * @param value The numeric value of the attribute.
	 */
	NumericAttribute createNumericAttribute(AttributeDescriptor descriptor, Number value);
	
	/**
	 * Creates a new text attribute.
	 * 
	 * @param descriptor The attribute descriptor.
	 * @param value The text value of the attribute.
	 */
	TextAttribute createTextAttribute(AttributeDescriptor descriptor, CharSequence value);
	
	/**
	 * Creates a new temporal attribute.
	 * 
	 * @param descriptor Teh attribute descriptor.
	 * @param value The calendar value of the attribute.
	 */
	TemporalAttribute createTemporalAttribute(AttributeDescriptor descriptor, String value);
	
	/**
	 * Creates a new geometry attribute.
	 * 
	 * @param desc The attribute descriptor.
	 * @param id The id of the attribute, may be null depending on the type.
	 * @param crs The coordinate reference system of the attribute, may be null.
	 * @param value The initial value of the attribute, may be null depending on 
	 * the type of the type of the attribute.
	 * 
	 * @throws IllegalArgumentException If desc.getType() does not return an 
	 * instanceof {@link GeometryType}.
	 */
	GeometryAttribute createGeometryAttribute(
		AttributeDescriptor desc, String id, CoordinateReferenceSystem crs, Object value
	);
	
	/**
	 * Creates a new complex attribute.
	 * 
	 * @param desc The attribute descriptor.
	 * @param id The id of the attribute, may be null depending on the type.
	 * @param value The initial value of the attribute, may be null depending on 
	 * the type of the attribute.
	 * 
	 * @throws IllegalArgumentException If desc.getType() does not return an 
	 * instanceof {@link ComplexType}.
	 */
	ComplexAttribute createComplexAttribute(
		AttributeDescriptor desc, String id, Collection value
	);

	/**
	 * Creates a new feature.
	 * 
	 * @param desc The attribute descriptor. 
	 * @param id The id of the feature, (fid), may be null depending on the type.
	 * @param value The initial value of the attribute, may be null depending on 
	 * the type of the feature.
	 * 
	 * @throws IllegalArgumentException If desc.getType() does not return an 
	 * instanceof {@link FeatureType}.
	 */
	Feature createFeature(AttributeDescriptor desc, String id, Collection value);
	
	/**
	 * Creates a new simple feature.
	 * 
	 * @param desc The attribute descriptor. 
	 * @param id The id of the feature, (fid), may be null depending on the type.
	 * @param value The initial value of the attribute, may be null depending on 
	 * the type of the feature.
	 * 
	 * @throws IllegalArgumentException If desc.getType() does not return an 
	 * instanceof {@link SimpleFeatureType}.
	 */
	SimpleFeature createSimpleFeature(AttributeDescriptor desc, String id, List value);

	/**
	 * Createsa a new feature collection.
	 * 
	 * @param desc The attribute descriptor.
	 * @param id The id of the feature collection, may be null depending on the 
	 * type.
	 * 
	 * @throws IllegalArgumentException If desc.getType() does not return an 
	 * instanceof {@link FeatureCollectionType}.
	 */
	FeatureCollection createFeatureCollection(AttributeDescriptor desc, String id);

	/**
	 * Createsa a new simple feature collection.
	 * 
	 * @param desc The attribute descriptor.
	 * @param id The id of the feature collection, may be null depending on the 
	 * type.
	 * 
	 * @throws IllegalArgumentException If desc.getType() does not return an 
	 * instanceof {@link org.opengis.feature.simple.SimpleFeatureCollectionType}.
	 */
	SimpleFeatureCollection createSimpleFeatureCollection(AttributeDescriptor desc, String id);
	
	
}

