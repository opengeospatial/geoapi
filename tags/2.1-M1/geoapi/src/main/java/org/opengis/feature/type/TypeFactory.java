package org.opengis.feature.type;

import java.util.Collection;
import java.util.Set;

import org.opengis.filter.Filter;
import org.opengis.filter.FilterFactory;
import org.opengis.referencing.crs.CRSFactory;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.util.InternationalString;

/**
 * Factory interface for the typing system.
 * <p>
 * This interface supports setter dependency injection:
 * <ul>
 * <li>CRSFactory - used for spatial content creation
 * <li>FilterFactory - used for type restrictions
 * </ul>
 * Implementors are encouraged to allow constructor injection:
 * <table width=80% border=1 bgcolor=%BBBBBB><tr><td><pre><code>class MyTypeFactory {
 *    CRSFactory crsFactory;
 *    FilterFactory filterFactory;
 *    public MyTypeFactory( CRSFactory crsFactory, FilterFactory filterFactory){
 *       this.crsFactory = crsFactory;
 *       this.filterFactory = filterFactory;
 *    }
 *    public void setCRSFactory( CRSFactory factory ){
 *       this.crsFactory = factory;
 *    }
 *    public void setFilterFactory( FilterFactory factory ){
 *       this.filterFactory = factory;
 *    }
 *    ...
 *  }</code></pre></td></tr></table>
 * </p>
 * @author Gabriel Roldan, Axios Engineering 
 * @author Justin Deoliveira, The Open Planning Project
 */
public interface TypeFactory {
    /**
     * @return The CRS factory used to create CRS info for created types.
     */
    CRSFactory getCRSFactory();
    
    /**
     * Sets the CRS factory used to create CRS info for created types.
     */
    void setCRSFactory(CRSFactory crsFactory);
    
    /**
     * @return The Filter Facotry used to create type restrictions.
     */
    FilterFactory getFilterFactory();
    
    /**
     * Sets the filter factory used to create type restrictions.
     */
    void setFilterFactory(FilterFactory filterFactory);
    
    /**
     * Create a Schema to hold instances of Types created by this factory.
     * 
     * @param namespaceURI
     * @return Schema
     */
    Schema createSchema(String namespaceURI );
    
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
		boolean isAbstract, Set<Filter> restrictions, AttributeType superType,
		InternationalString description
	);
	
	FeatureCollectionType createFeatureCollectionType(
		TypeName name,  Collection<StructuralDescriptor> schema, Collection<AssociationDescriptor> members,
		AttributeDescriptor<GeometryType> defaultGeom, CoordinateReferenceSystem crs, 
		boolean isAbstract, Set<Filter> restrictions, AttributeType superType,
		InternationalString description
	);
   
}
