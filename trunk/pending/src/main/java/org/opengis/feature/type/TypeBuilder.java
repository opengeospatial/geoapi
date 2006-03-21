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
 */
public interface TypeBuilder {

	/**
	 * Returns the underlying type factory.
	 */
	TypeFactory getTypeFactory();
	
	/**
	 * Sets the underyling type factory.
	 */
	void setTypeFactory(TypeFactory factory);
	
	// naming 
	/**
	 * Creates a non qualified type name.
	 * (ie. {@link TypeName#getNamespaceURI() = null}).
	 * 
	 * @param local The local name.
	 */
	TypeName name(String local);
	
	/**
	 * Creates a qualified type name.
	 * 
	 * @param namespace The namespace.
	 * @param local The local name.
	 * 
	 */
	TypeName name(String namespace, String local);
	
	// descriptors
	/**
	 * Creates an attribute descriptor.
	 * <br>
	 * Equivalent to calling:
	 * <pre>
	 * 	<code>descriptor(type,name,1,1)</code>
	 * </pre>
	 *
	 * @param type The type of the attribute.
	 * @param name The name of the attribute.
	 * 
	 */
	AttributeDescriptor descriptor(AttributeType type, TypeName name);
	
	/**
	 * 
	 * @param type The type of the attribute.
	 * @param name The name of the attribute.
	 * @param min The minimum number of occurencces of the attribute.
	 * @param max The maximum number of occurencces of the attribute.
	 * @return
	 */
	AttributeDescriptor descriptor(AttributeType type, TypeName name, int min, int max);
	
	// type creation
	
	/**
	 * Creates a new attribute type.
	 * <br>
	 * <p>
	 * Equivalent to calling:
	 * 	<pre>
	 * 	<code>attributeType(name,binding,Collections.emptySet())</code>
	 * 	</pre>
	 * </p>
	 * @param name The name of the type.
	 * @param binding The class with which the type is bound to.
	 * 
	 */
	AttributeType attributeType(TypeName name, Class binding);
	
	/**
	 * Creates a new attribute type.
	 * <br>
	 * <p>
	 * Equivalent to calling:
	 * 	<pre>
	 * 	<code>attributeType(name,binding,restrictions,null)</code>
	 * 	</pre>
	 * </p>
	 * @param name The name of the type.
	 * @param binding The class with which the type is bound to.
	 * @param restrictions Additional restrictions to be placed on attributes 
	 * of the type. Specifies as instances of {@link Filter}.
	 * 
	 */
	AttributeType attributeType(
		TypeName name, Class binding, Set<Filter> restrictions
	);
	
	/**
	 * Creates a new attribute type.
	 * <br>
	 * <p>
	 * Equivalent to calling:
	 * 	<pre>
	 * 	<code>attributeType(name,binding,flags,restrictions,TypeFlag.DEFAULT)</code>
	 * 	</pre>
	 * </p>
	 * 
	 * @param name The name of the type.
	 * @param binding The class with which the type is bound to.
	 * @param restrictions Additional restrictions to be placed on attributes 
	 * of the type. Specified as instances of {@link Filter}.
	 * @param superType The super type of this type.
	 */
	AttributeType attributeType(
		TypeName name, Class binding, Set<Filter> restrictions, 
		AttributeType superType
	);
	
	/**
	 * Creates a new attribute type.
	 * 
	 * @param name The name of the type.
	 * @param binding The class with which the type is bound to.
	 * @param restrictions Additional restrictions to be placed on attributes 
	 * of the type. Specified as instances of {@link Filter}.
	 * @param superType The super type of this type.
	 * @param flags Flags to be set on the type.
	 */
	AttributeType attributeType(
		TypeName name, Class binding, Set<Filter> restrictions, 
		AttributeType superType, TypeFlag[] flags
	);
	
	/**
	 * Creates a new complex type.
	 * <br>
	 *	<p>
	 *	Equivalent to calling:
	 *	<pre>
	 *	  <code>complexType(name,schema,Collections.emptySet())</code>
	 *  </pre>
	 *	</p>
	 * @param name The name of the type.
	 * @param schema The collection of attributes that make up the complex type.
	 */
	ComplexType complexType(
		TypeName name, Collection<AttributeDescriptor> schema
	);
	
	/**
	 * Creates a new complex type.
	 * <br>
	 *	<p>
	 *	Equivalent to calling:
	 *	<pre>
	 *	  <code>complexType(name,schema,restrictions,null)</code>
	 *  </pre>
	 *	</p>
	 * @param name The name of the type.
	 * @param schema The collection of attributes that make up the complex type.
	 * @param restrictions Additional restrictions to be placed on attributes
	 */
	ComplexType complexType(
		TypeName name, Collection<AttributeDescriptor> schema, 
		Set<Filter> restrictions
	);
	
	/**
	 * Creates a new complex type.
	 * <br>
	 *	<p>
	 *	Equivalent to calling:
	 *	<pre>
	 *	  <code>complexType(name,schema,restrictions,null,TypeFlag.DEFAULT)</code>
	 *  </pre>
	 *	</p>
	 * @param name The name of the type.
	 * @param schema The collection of attributes that make up the complex type.
	 * @param restrictions Additional restrictions to be placed on attributes 
	 * of the type. Specified as instances of {@link Filter}.
	 * 
	 */
	ComplexType complexType(
		TypeName name, Collection<AttributeDescriptor> schema,
		Set<Filter> restrictions, ComplexType superType 
	);
	
	
	/**
	 * Creates a new complex type.
	 * 
	 * @param name The name of the type.
	 * @param schema The collection of attributes that make up the complex type.
	 * 
	 * @param restrictions Additional restrictions to be placed on attributes 
	 * of the type. Specified as instances of {@link Filter}.
	 * @param superType The super type of this type,may be null.
	 * @param flags Flags to be set on the type.
	 */
	ComplexType complexType(
		TypeName name, Collection<AttributeDescriptor> schema, 
		Set<Filter> restrictions, ComplexType superType, TypeFlag[] flags
	);
	
	/**
	 * Creates a new geometry type.
	 * * <br>
	 *	<p>
	 *	Equivalent to calling:
	 *	<pre>
	 *	  <code>geometryType(name,binding,crs,TypeFlag.DEFAULT)</code>
	 *  </pre>
	 *	</p>
	 * @param name The name of the type.
	 * @param binding The class with which the type is bound to.
	 * @param crs The coordinate reference system of the type.
	 */
	GeometryType geometryType(
		TypeName name, Class binding, CoordinateReferenceSystem crs
	);
	
	/**
	 * Creates a new geometry type.
	 * * <br>
	 *	<p>
	 *	Equivalent to calling:
	 *	<pre>
	 *	  <code>geometryType(name,binding,crs,restrictions,null)</code>
	 *  </pre>
	 *	</p>
	 * @param name The name of the type.
	 * @param binding The class with which the type is bound to.
	 * @param crs The coordinate reference system of the type.
	 * @param restrictions Additional restrictions to be placed on attributes 
	 * of the type. Specified as instances of {@link Filter}.
	 */
	GeometryType geometryType(
		TypeName name, Class binding, CoordinateReferenceSystem crs,
		Set<Filter> restrictions
	);

	/**
	 * Creates a new geometry type.
	 * <br>
	 * <p>
	 * Equivalent to calling:
	 * <pre>
	 * 	<code>
	 * 	geometryType(name,binding,flags,crs,restrictions,superType,TypeFlag.DEFAULT)
	 * 	</code>
	 * </pre>
	 * </p>
	 * @param name The name of the type.
	 * @param binding The class with which the type is bound to.
	 * @param crs The coordinate reference system of the type.
	 * @param restrictions Additional restrictions to be placed on attributes 
	 * of the type. Specified as instances of {@link Filter}.
	 * @param superType The parent type, may be null.
	 */
	GeometryType geometryType(
		TypeName name, Class binding, CoordinateReferenceSystem crs,
		Set<Filter> restrictions, GeometryType superType
	);
	
	/**
	 * Creates a new geometry type.
	 * 
	 * @param name The name of the type.
	 * @param binding The class with which the type is bound to.
	 * @param crs The coordinate reference system of the type.
	 * @param restrictions Additional restrictions to be placed on attributes 
	 * of the type. Specified as instances of {@link Filter}.
	 * @param superType The parent type, may be null.
	 * @param flags Flags to be set on the type.
	 */
	GeometryType geometryType(
		TypeName name, Class binding, CoordinateReferenceSystem crs, 
		Set<Filter> restrictions, GeometryType superType, TypeFlag[] flags
	);
	
	/**
	 * Creates a new feature type.
	 * <p>
	 * Equivalent to calling:
	 * <pre>
	 * 	<code>feaureType(name,schema,null)</code>
	 * </pre>
	 * </p>
	 * @param name The name of the type.
	 * @param schema The collection of attributes that make up the type. 
	 */
	FeatureType featureType(
		TypeName name, Collection<AttributeDescriptor> schema
	);

	/**
	 * Creates a new feature type.
	 * <p>
	 * Equivalent to calling:
	 * <pre>
	 * 	<code>feaureType(name,schema,defaultGeometry,Collections.emptySet())</code>
	 * </pre>
	 * </p>
	 * @param name The name of the type.
	 * @param schema The collection of attributes that make up the type. 
	 * @param defaultGeometry The default geometry type of the feautre type.
	 */
	FeatureType featureType(
		TypeName name, Collection<AttributeDescriptor> schema, 
		GeometryType defaultGeometry
	);
	
	/**
	 * Creates a new feature type.
	 * <p>
	 * Equivalent to calling:
	 * <pre>
	 * 	<code>feaureType(name,schema,defaultGeometry,restrictions,null)</code>
	 * </pre>
	 * </p>
	 * @param name The name of the type.
	 * @param schema The collection of attributes that make up the type. 
	 * @param defaultGeometry The default geometry type of the feautre type.
	 * @param restrictions Additional restrictions to be placed on attributes 
	 * of the type. Specified as instances of {@link Filter}.
	 */
	FeatureType featureType(
		TypeName name, Collection<AttributeDescriptor> schema,
		GeometryType defaultGeometry, Set<Filter> restrictions
	);
	
	/**
	 * Creates a new feature type.
	 * <p>
	 * Equivalent to calling:
	 * <pre>
	 * 	<code>feaureType(name,schema,defaultGeometry,restrictions,null,null)</code>
	 * </pre>
	 * </p>
	 * @param name The name of the type.
	 * @param schema The collection of attributes that make up the type. 
	 * @param defaultGeometry The default geometry type of the feautre type.
	 * @param restrictions Additional restrictions to be placed on attributes 
	 * of the type. Specified as instances of {@link Filter}.
	 * @param superType The parent type, may be null.
	 */
	FeatureType featureType(
		TypeName name, Collection<AttributeDescriptor> schema,
		GeometryType defaultGeometry, Set<Filter> restrictions, 
		FeatureType superType
	);
	
	/**
	 * Creates a new feature type.
	 *
	 * @param name The name of the type.
	 * @param schema The collection of attributes that make up the type. 
	 * @param defaultGeometry The default geometry type of the feautre type.
	 * @param restrictions Additional restrictions to be placed on attributes 
	 * of the type. Specified as instances of {@link Filter}.
	 * @param superType The parent type, may be null.
	 * @param flags Flags to be set on the type.
	 */
	FeatureType featureType(
		TypeName name, Collection<AttributeDescriptor> schema,
		GeometryType defaultGeometry, Set<Filter> restrictions, 
		FeatureType superType, TypeFlag[] flags
	);
	
	/**
	 * Creates a new simple feature type.
	 * <p>
	 * Equivalent to calling:
	 * <pre>
	 * 	<code>simpleFeatureType(name,schema,null)</code>
	 * </pre>
	 * </p>
	 * @param name The name of the type.
	 * @param schema The ordered list of attribute types making up this type.
	 * @param flags Flags to be set on the type.
	 */
	SimpleFeatureType simpleFeatureType(
		TypeName name, List<AttributeType> schema
	);
	
	/**
	 * Creates a new simple feature type.
	 * <p>
	 * Equivalent to calling:
	 * <pre>
	 * 	<code>simpleFeatureType(name,schema,defaultGeometry,Collections.emptySet())</code>
	 * </pre>
	 * </p>
	 * @param name The name of the type.
	 * @param schema The ordered list of attribute types making up this type.
	 * @param defaultGeometry The default geometry type.
	 */
	SimpleFeatureType simpleFeatureType(
		TypeName name, List<AttributeType> schema, GeometryType defaultGeometry
	);

	/**
	 * Creates a new simple feature type.
	 * <p>
	 * Equivalent to calling:
	 * <pre>
	 * 	<code>simpleFeatureType(name,schema,defaultGeometry,restrictions,null)</code>
	 * </pre>
	 * </p>
	 * @param name The name of the type.
	 * @param schema The ordered list of attribute types making up this type.
	 * @param defaultGeometry The default geometry type.
	 * @param restrictions Additional restrictions to be placed on attributes 
	 * of the type. Specified as instances of {@link Filter}.
	 */
	SimpleFeatureType simpleFeatureType(
		TypeName name, List<AttributeType> schema, 
		GeometryType defaultGeometry,  Set<Filter> restrictions
	);

	/**
	 * Creates a new simple feature type.
	 * <br>
	 * Equivalent to calling:
	 *	<pre>
	 *	<code>simpleFeatureType(name,schema,defaultGeometry,resitrctions,superType,null)</code>
	 *	</pre>
	 * @param name The name of the type.
	 * @param schema The ordered list of attribute types making up this type.
	 * @param defaultGeometry The default geometry type.
	 * @param restrictions Additional restrictions to be placed on attributes 
	 * of the type. Specified as instances of {@link Filter}.
	 * @param superType The parent type, may be null.
	 */
	SimpleFeatureType simpleFeatureType(
		TypeName name, List<AttributeType> schema,
		GeometryType defaultGeometry, Set<Filter> restrictions, 
		SimpleFeatureType superType
	);
	
	/**
	 * Creates a new simple feature type.
	 *
	 * @param name The name of the type.
	 * @param schema The ordered list of attribute types making up this type.
	 * @param defaultGeometry The default geometry type.
	 * @param restrictions Additional restrictions to be placed on attributes 
	 * of the type. Specified as instances of {@link Filter}.
	 * @param superType The parent type, may be null.
	 * @param flags Flags to be set on the type.
	 */
	SimpleFeatureType simpleFeatureType(
		TypeName name, List<AttributeType> schema, 
		GeometryType defaultGeometry, Set<Filter> restrictions, 
		SimpleFeatureType superType, TypeFlag[] flags
	);
	
	/**
	 * Creates a new feature collcetion type.
	 * <br>
	 * <p>
	 * Same as calling:
	 * <pre>
	 * 	<code>
	 * 		featureCollectionType(
	 * 			name,memeberTypes,Collections.EMPTY_LIST, null, 
	 *          Collections.EMPTY_SET,null,null
	 *      );
	 *  </code>
	 * </pre>
	 * </p>
	 * @param name The name of the type.
	 * @param memberTypes The set of feature types in which members of the 
	 * collection may implement.
	 * 
	 */
	FeatureCollectionType featureCollectionType(
		TypeName name, Set<FeatureType> memberTypes
	);
	
	/**
	 * Creates a new feature collection type.
	 * 
	 * @param name The name of the type.
	 * @param membersTypes The set of feature types in which members of the
	 * collection may implement.
	 * @param schema The collection of attribute types making up this type.
	 * @param defaultGeom The default geometry attribute of the type.
	 * @param restrictions Additional restrictions to be placed on attributes 
	 * of the type. Specified as instances of {@link Filter}.
	 * @param superType The parent type, may be null.
	 * @param flags Flags to be set on the type.
	 */
	FeatureCollectionType featureCollectionType(
		TypeName name, Set<FeatureType> membersTypes, 
		Collection<AttributeDescriptor> schema, GeometryType defaultGeom, 
		Set<Filter> restrictions, FeatureType superType, TypeFlag[] flags
	);
	
	/**
	 * Creates a new simple feature collection type.
	 * <br>
	 * <p>
	 * Equivalent to calling:
	 * <pre>
	 * 	<code>simpleFeatureCollectionType(name,memberType,Collections.EMPTY_SET)</code>
	 * </pre>
	 * 	
	 * </p>
	 * 
	 * @param name The name of the type.
	 * @param memberType The type of members of a collection of this type.
	 */
	SimpleFeatureCollectionType simpleFeatureCollectionType(
		TypeName name, FeatureType memberType
	);
	
	/**
	 * Creates a new simple feature collection type.
	 * 
	 * @param name The name of the type.
	 * @param memberType The type of members of a collection of this type.
	 * @param restrictions Additional restrictions to be placed on attributes 
	 * of the type. Specified as instances of {@link Filter}.
	 * 
	 */
	SimpleFeatureCollectionType simpleFeatureCollectionType(
		TypeName name, FeatureType memberType, Set<Filter> restrictions
	);
	
	// Subtypeing convenience methods
	
	/**
	 * Performs the inheritance of an type from its super type.
	 * <br>
	 * <p>
	 * Calling this method is the equivalent to calling: 
	 * <pre>
	 * 	<code>inherit(type,type.getSuperType())</code>
	 * </pre>
	 * </p>
	 * 
	 * @see #inheirit(AttributeType, AttributeType)
	 */
	void inheirit(AttributeType type);
	
	/**
	 * Performs the inheritance of an instance from another type.
	 * <br>
	 * <p>
	 * After this method is called, <i>type</i> should reflect any properties
	 * inheirited from its parent. For instance, a complex type should contain 
	 * the attributes of the parent.
	 * </p>
	 */
	void inheirit(AttributeType type, AttributeType parent);
	
	// other
	
	/**
	 * Returns an array of the flags which have been set for a pre-existing type.
	 */
	TypeFlag[] flags(AttributeType type);
	
	
}
