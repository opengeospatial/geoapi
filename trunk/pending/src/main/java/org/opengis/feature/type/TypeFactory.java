package org.opengis.feature.type;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.opengis.feature.simple.SimpleFeatureCollectionType;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.Filter;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.util.InternationalString;

/**
 * Factory interface for the typing system.
 * 
 * @author Gabriel Roldan, Axios Engineering 
 * @author Justin Deoliveira, The Open Planning Project
 * 
 */
public interface TypeFactory {

	AssociationType createAssociationType(
		TypeName name, Class association, boolean isIdentifiable, 
		boolean isAbstract, Set<Filter> restrictions, AssociationType superType,
		InternationalString description
	);
	
	AttributeType createAttributeType(
		TypeName name, Class binding, boolean isIdentifiable, 
		boolean isAbstract, Set<Filter> restrictions, AttributeType superType,
		InternationalString description
	);
	
	ComplexType createComplexType(
		TypeName name, Collection<AttributeDescriptor> schema, 
		boolean isIdentifiable, boolean isAbstract, 
		Set<Filter> restrictions, ComplexType superType,
		InternationalString description
	);
	
	GeometryType createGeometryType(
		TypeName name, Class binding, CoordinateReferenceSystem crs,
		boolean isIdentifiable, boolean isAbstract,  
		Set<Filter> restrictions, AttributeType superType,
		InternationalString description
	);

	FeatureType createFeatureType(
		TypeName name, Collection<AttributeDescriptor> schema,
		AttributeDescriptor<GeometryType> defaultGeometry, CoordinateReferenceSystem crs,
		boolean isAbstract, Set<Filter> restrictions, ComplexType superType,
		InternationalString description
	);

	SimpleFeatureType createSimpleFeatureType(
		TypeName name,List<AttributeDescriptor> schema,
		AttributeDescriptor<GeometryType> defaultGeometry, CoordinateReferenceSystem crs, 
		boolean isAbstract, Set<Filter> restrictions, SimpleFeatureType superType,
		InternationalString description
	);
	
	FeatureCollectionType createFeatureCollectionType(
		TypeName name, Set<FeatureType> membersTypes, 
		Collection<AttributeDescriptor> schema, 
		AttributeDescriptor<GeometryType> defaultGeom, 
		CoordinateReferenceSystem crs, boolean isAbstract, 
		Set<Filter> restrictions, ComplexType superType,
		InternationalString description
	);
	
	SimpleFeatureCollectionType createSimpleFeatureCollectionType(
		TypeName name, FeatureType memberType, CoordinateReferenceSystem crs, 
		Set<Filter> restrictions, InternationalString description
	);

}
