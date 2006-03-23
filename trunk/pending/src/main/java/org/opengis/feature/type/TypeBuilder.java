package org.opengis.feature.type;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.opengis.feature.simple.SimpleFeatureCollectionType;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.Filter;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

/**
 * Interface providing high level convenience methods used to build type 
 * objects.
 * <br>
 * <p>
 * This when possible this interface should be usd to construct types as 
 * opposed to {@link org.opengis.feature.type.TypeFactory}
 * </p>
 * <p>
 * Type buliders used flags defined in {@link org.opengis.feature.type.TypeFlag}.
 * Using {@link org.opengis.feature.type.TypeFlag#DEFAULT} results in the 
 * following:
 * <ul>
 * 	<li>{@link org.opengis.feature.type.TypeFlag#DEFAULT}
 * </ul>
 *	</p>
 *
 * @author Justin Deoliveira, The Open Planning Project, jdeolive@openplans.org
 *
 * @param <B> The class the created attribute type is bound to.
 * @param <T> The class of attribute type created by the builder.
 */
public interface TypeBuilder<T extends AttributeType,B> {

	/**
	 * Returns the underlying type factory.
	 */
	TypeFactory getTypeFactory();
	
	/**
	 * Sets the underyling type factory.
	 */
	void setTypeFactory(TypeFactory factory);
	
	// State
	//
	// These methods capture state used in helping produce
	// objects.
	
	/**
	 * Sets the namespace to be used when constructing type and attribute names.
	 */
	void setNamespaceURI(String uri);
	
	/**
	 * Returns The namespace to be used when constructing type and attribute 
	 * names.
	 */
	String getNamespaceURI();
	
	/**
	 * Sets the name of the type.
	 */
	void setName(String name);
	
	/**
	 * Returns the name of the type.
	 */
	String getName();
	
	/**
	 * Sets the class the type is bound to.
	 */
	<C extends B> void setBinding(Class<C> binding);
	
	/**
	 * Returns the class the type is bound to.
	 */
	<C extends B> C getBinding();
	
	/**
	 * Sets the abstract property of created types.
	 * 
	 * @see AttributeType#isAbstract()
	 */
	void setAbstract(boolean isAbstract);
	
	/**
	 * Returns the abstract property of created types.
	 * 
	 * @see AttributeType#isAbstract()
	 */
	boolean isAbstract();
	
	/**
	 * Sets the nillable property of created types.
	 * 
	 * @see AttributeType#isNillable()
	 */
	void setNillable(boolean isNillable);

	/**
	 * Returns the abstract property of created types.
	 * 
	 * @see AttributeType#isNillable()
	 */
	boolean isNillable();
	
	/**
	 * Sets additional restrictions on created types.
	 * 
	 * @see AttributeType#getRestrictions()
	 */
	void setRestrictions(Set<Filter> restrictions);
	
	/**
	 * Returns additional restrictions on created types.
	 * 
	 * @see AttributeType#getRestrictions()
	 */
	Set<Filter> getRestrictions();
	
	/**
	 * Sets the super type of created types.
	 *
	 * @see AttributeType#getSuper()
	 */
	<E extends T> void setSuper(E superType);
	
	/**
	 * Sets the super type of created types.
	 *
	 * @see AttributeType#getSuper()
	 */
	<E extends T> E getSuper();
	
	// descriptors
//	/**
//	 * Creates an attribute descriptor.
//	 * <br>
//	 * Equivalent to calling:
//	 * <pre>
//	 * 	<code>descriptor(type,name,1,1)</code>
//	 * </pre>
//	 *
//	 * @param type The type of the attribute.
//	 * @param name The name of the attribute.
//	 */
//	AttributeDescriptor descriptor(AttributeType type, String name);
//	
//	/**
//	 * 
//	 * @param type The type of the attribute.
//	 * @param name The name of the attribute.
//	 * @param min The minimum number of occurencces of the attribute.
//	 * @param max The maximum number of occurencces of the attribute.
//	 * @return
//	 */
//	AttributeDescriptor descriptor(AttributeType type, String name, int min, int max);
	
	// type creation
	
	/**
	 * Builds the new type.
	 */
	T build();
	
//	/**
//	 * Creates a new complex type.
//	 * 
//	 * @param name The name of the type.
//	 * @param schema The collection of attributes that make up the complex type.
//	 */
//	ComplexType complexType(String name, Collection<AttributeDescriptor> schema);
//	
//	/**
//	 * Creates a new geometry type.
//	 *
//	 * @param name The name of the type.
//	 * @param binding The class with which the type is bound to.
//	 * @param crs The coordinate reference system of the type.
//	 */
//	GeometryType geometryType(
//		String name, Class binding, CoordinateReferenceSystem crs
//	);
//	
//	/**
//	 * Creates a new feature type.
//	 *
//	 * @param name The name of the type.
//	 * @param schema The collection of attributes that make up the type. 
//	 * @param defaultGeometry The default geometry type of the feature type.
//	 */
//	FeatureType featureType(
//		String name, Collection<AttributeDescriptor> schema, 
//		GeometryType defaultGeometry
//	);
//	
//	/**
//	 * Creates a new simple feature type.
//	 *
//	 * @param name The name of the type.
//	 * @param schema The ordered list of attribute types making up this type.
//	 * @param defaultGeometry The default geometry type.
//	 */
//	SimpleFeatureType simpleFeatureType(
//		String name, List<AttributeType> schema, GeometryType defaultGeometry
//	);
//
//	/**
//	 * Creates a new feature collection type.
//	 * 
//	 * @param name The name of the type.
//	 * @param membersTypes The set of feature types in which members of the
//	 * collection may implement.
//	 * @param schema The collection of attribute types making up this type.
//	 * @param defaultGeom The default geometry attribute of the type.
//	 */
//	FeatureCollectionType featureCollectionType(
//		String name, Set<FeatureType> membersTypes, 
//		Collection<AttributeDescriptor> schema, GeometryType defaultGeom 
//	);
//	
//	/**
//	 * Creates a new simple feature collection type.
//	 * 
//	 * @param name The name of the type.
//	 * @param memberType The type of members of a collection of this type.
//	 */
//	SimpleFeatureCollectionType simpleFeatureCollectionType(
//		String name, FeatureType memberType
//	);
//	
//	// Subtypeing convenience methods
//	
//	/**
//	 * Performs the inheritance of an type from its super type.
//	 * <br>
//	 * <p>
//	 * Calling this method is the equivalent to calling: 
//	 * <pre>
//	 * 	<code>inherit(type,type.getSuperType())</code>
//	 * </pre>
//	 * </p>
//	 * 
//	 * @see #inheirit(AttributeType, AttributeType)
//	 */
//	void inheirit(AttributeType type);
//	
//	/**
//	 * Performs the inheritance of an instance from another type.
//	 * <br>
//	 * <p>
//	 * After this method is called, <i>type</i> should reflect any properties
//	 * inheirited from its parent. For instance, a complex type should contain 
//	 * the attributes of the parent.
//	 * </p>
//	 */
//	void inheirit(AttributeType type, AttributeType parent);
	
}
