package org.opengis.feature;

import java.util.Collection;
import java.util.Date;

import org.opengis.feature.simple.BooleanAttribute;
import org.opengis.feature.simple.NumericAttribute;
import org.opengis.feature.simple.TemporalAttribute;
import org.opengis.feature.simple.TextAttribute;
import org.opengis.feature.type.AssociationDescriptor;
import org.opengis.feature.type.AssociationType;
import org.opengis.feature.type.AttributeDescriptor;
import org.opengis.feature.type.AttributeType;
import org.opengis.feature.type.ComplexType;
import org.opengis.feature.type.FeatureCollectionType;
import org.opengis.feature.type.FeatureType;
import org.opengis.feature.type.GeometryType;
import org.opengis.feature.type.Name;
import org.opengis.referencing.crs.CRSFactory;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.spatialschema.geometry.geometry.GeometryFactory;

/**
 * Plays the role of making actual instances of types in this puzzle.
 * 
 * @author Gabriel Roldan, Axios Engineering
 * @author Justin Deoliveira, The Open Planning Project
 *
 */
public interface AttributeFactory {

    /**
     * @return The CRS factory used to create CRS info for created attributes.
     */
    CRSFactory getCRSFactory();
    
    /**
     * Sets the CRS factory used to create CRS info for created attributes.
     */
    void setCRSFactory(CRSFactory crsFactory);
    
    /**
     * @return The factory used to create geometric data.
     */
    GeometryFactory getGeometryFactory();
    
    /**
     * Sets the factory used to create geometric data.  
     */
    void setGeometryFactory(GeometryFactory geometryFactory);
    
    /**
     * Creates an association descriptor.
     * 
     * @param type The type.
     * @param name The name.
     * @param minOccurs The minimum number of occurences of the association.
     * @param maxOccurs The maximum number of occurences of hte association.
     * 
     */
    AssociationDescriptor createAssociationDescriptor(
        AssociationType type, Name name, int minOccurs, int maxOccurs      
    );
    
    /**
     * Creates an association.
     * 
     * @param value The value of the association, an attribute.
     * @param descriptor The descriptor.
     */
    Association createAssociation(Attribute value, AssociationDescriptor descriptor);
    
	/**
	 * Creates an attribute descriptor.
	 * 
	 * @param type The type.
	 * @param name The name.
	 * @param minOccurs The minimum number of occurences of an attribute.
	 * @param maxOccurs The maximum number of occurences of an attribute.
	 * @param nillable Wether the attribute may have a null value.
	 */
	AttributeDescriptor createAttributeDescriptor(
		AttributeType type, Name name, int minOccurs, int maxOccurs, 
		boolean nillable
	);
	
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

}

