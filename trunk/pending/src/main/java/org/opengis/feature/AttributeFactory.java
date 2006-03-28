package org.opengis.feature;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.opengis.feature.simple.BooleanAttribute;
import org.opengis.feature.simple.NumericAttribute;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureCollection;
import org.opengis.feature.simple.SimpleFeatureCollectionType;
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
	 * @param value The value of the attribute, may be null depending on type.
	 * @param descriptor The attribute descriptor.
	 * @param id The id of the attribute, may be null depending on type.
	 * 
	 */
	Attribute createAttribute(Object value, AttributeDescriptor descriptor, String id);
	
	/**
	 * Creates a new boolean attribute.
	 * 
	 * @param value The boolean value of the attribute.
	 * @param descriptor The attribute descriptor.
	 * 
	 */
	BooleanAttribute createBooleanAttribute(Boolean value, AttributeDescriptor descriptor);
	
	/**
	 * Creates a new numberic attribute.
	 * 
	 * @param value The numeric value of the attribute.
	 * @param descriptor The attribute descriptor.
	 * 
	 */
	NumericAttribute createNumericAttribute(Number value, AttributeDescriptor descriptor);
	
	/**
	 * Creates a new text attribute.
	 * 
	 * @param value The text value of the attribute.
	 * @param descriptor The attribute descriptor.
	 * 
	 */
	TextAttribute createTextAttribute(CharSequence value, AttributeDescriptor descriptor);
	
	/**
	 * Creates a new temporal attribute.
	 * 
	 * @param value The date value of the attribute.
	 * @param descriptor Teh attribute descriptor.
	 * 
	 */
	TemporalAttribute createTemporalAttribute(Date value, AttributeDescriptor descriptor);
	
	/**
	 * Creates a new geometry attribute.
	 * 
	 * @param value The initial value of the attribute, may be null depending on 
	 * the type of the type of the attribute.
	 * @param desc The attribute descriptor.
	 * @param id The id of the attribute, may be null depending on the type.
	 * @param crs The coordinate reference system of the attribute, may be null.
	 *
	 * @throws IllegalArgumentException If desc.getType() does not return an 
	 * instanceof {@link GeometryType}.
	 */
	GeometryAttribute createGeometryAttribute(
		Object value, AttributeDescriptor desc, String id, CoordinateReferenceSystem crs
	);
	
	/**
	 * Creates a new complex attribute.
	 * 
	 * @param value The initial value of the attribute, may be null depending on 
	 * the type of the attribute.
	 * @param id The id of the attribute, may be null depending on the type.
	 * @param desc The attribute descriptor.
	 * 
	 * @throws IllegalArgumentException If desc.getType() does not return an 
	 * instanceof {@link ComplexType}.
	 */
	ComplexAttribute createComplexAttribute(
		Collection value, AttributeDescriptor desc, String id 
	);
	
	ComplexAttribute createComplexAttribute(
		Collection value, ComplexType type, String id	
	);
	
	/**
	 * Creates a new feature.
	 * 
	 * @param id The id of the feature, (fid), may be null depending on the type.
	 * @param desc The attribute descriptor. 
	 * @param value The initial value of the attribute, may be null depending on 
	 * the type of the feature.
	 * 
	 * @throws IllegalArgumentException If desc.getType() does not return an 
	 * instanceof {@link FeatureType}.
	 */
	Feature createFeature(Collection value, AttributeDescriptor desc, String id);
	
	Feature createFeature(Collection value, FeatureType type, String id);
	
	/**
	 * Creates a new simple feature.
	 * 
	 * @param value The initial value of the attribute, may be null depending on 
	 * the type of the feature.
	 * @param desc The attribute descriptor. 
	 * @param id The id of the feature, (fid), may be null depending on the type.
	 * 
	 * @throws IllegalArgumentException If desc.getType() does not return an 
	 * instanceof {@link SimpleFeatureType}.
	 */
	SimpleFeature createSimpleFeature(List value, AttributeDescriptor desc, String id);

	SimpleFeature createSimpleFeature(List value, SimpleFeatureType type, String id);
	
	/**
	 * Createsa a new feature collection.
	 * 
	 * @param value The initial value of the attribute, may be null depending on 
	 * the type of the feature.
	 * @param desc The attribute descriptor.
	 * @param id The id of the feature collection, may be null depending on the 
	 * type.
	 * 
	 * @throws IllegalArgumentException If desc.getType() does not return an 
	 * instanceof {@link FeatureCollectionType}.
	 */
	FeatureCollection createFeatureCollection(Collection value, AttributeDescriptor desc, String id);
	
	FeatureCollection createFeatureCollection(Collection value, FeatureCollectionType type, String id);
	
	/**
	 * Createsa a new simple feature collection.
	 * 
	 * @param value The initial value of the attribute, may be null depending on 
	 * the type of the feature.
	 * @param desc The attribute descriptor.
	 * @param id The id of the feature collection, may be null depending on the 
	 * type.
	 * 
	 * @throws IllegalArgumentException If desc.getType() does not return an 
	 * instanceof {@link org.opengis.feature.simple.SimpleFeatureCollectionType}.
	 */
	SimpleFeatureCollection createSimpleFeatureCollection(AttributeDescriptor desc, String id);
	
	SimpleFeatureCollection createSimpleFeatureCollection(SimpleFeatureCollectionType type, String id);
	
}

