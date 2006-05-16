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

	/**
	 * Creates a new attribute descriptor.
	 * 
	 * @param type
	 * 	The type of the described attribute.
	 * @param name
	 * 	The name of the described attribute. 
	 * @param minOccurs
	 * 	The minimum number of occurences of the described attribute.
	 * @param maxOccurs
	 * 	The maximum number of occurences of the described attribute.
	 * @param isNillable
	 * 	Flag indicating if the described attribute may have a null value.
	 * 
	 */
	<T extends AttributeType>  
	AttributeDescriptor<T> createAttributeDescriptor(
		T type, Name name, int minOccurs, int maxOccurs, boolean isNillable
	);
	
	/**
	 * Creates a new association descriptor.
	 * 
	 * @param type
	 * 	The type of the described association.
	 * @param name
	 * 	The name of the described association.
	 * @param minOccurs
	 * 	The minimum number of occurences of the described association.
	 * @param maxOCcurs
	 * 	The maximum number of occurences of the described association.
	 * 
	 */
	<B extends AttributeType, A extends AssociationType<B>> 
	AssociationDescriptor<A> createAssociationDescriptor(
		A type, Name name, int minOccurs, int maxOCcurs
	);
	
	/**
	 * Creates a new operation descriptor.
	 * 
	 * @param type 
	 * 	The type of the described operation.
	 * @param isImplemented
	 * 	Flag indicating if the described operation is implemented or not.
	 */
	<B, T extends AttributeType<B>, O extends OperationType<B,T>>
	OperationDescriptor<B,T,O> createOperationDescriptor(
		O type, boolean isImplemented
	);
	
	AssociationType createAssociationType(
		TypeName name, AttributeType referenceType,boolean isIdentifiable,
		boolean isAbstract, Set<Filter> restrictions, AssociationType superType,
		InternationalString description
	);
	
	AttributeType createAttributeType(
		TypeName name, Class binding, boolean isIdentifiable, boolean isAbstract, 
		Set<Filter> restrictions, AttributeType superType, InternationalString description
	);
	
	ComplexType createComplexType(
		TypeName name, Collection<StructuralDescriptor> schema, boolean isIdentifiable, 
		boolean isAbstract, Set<Filter> restrictions, AttributeType superType,
		InternationalString description
	);
	
	GeometryType createGeometryType(
		TypeName name, Class binding, CoordinateReferenceSystem crs, boolean isIdentifiable, 
		boolean isAbstract, Set<Filter> restrictions, AttributeType superType,
		InternationalString description
	);

	FeatureType createFeatureType(
		TypeName name, Collection<StructuralDescriptor> schema,
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
		TypeName name,  Collection<StructuralDescriptor> schema, 
		AttributeDescriptor<GeometryType> defaultGeom, CoordinateReferenceSystem crs, 
		boolean isAbstract, Set<Filter> restrictions, ComplexType superType,
		InternationalString description
	);
	
	SimpleFeatureCollectionType createSimpleFeatureCollectionType(
		TypeName name, AssociationDescriptor member, Set<Filter> restrictions, 
		InternationalString description
	);

}
