package org.opengis.feature.type;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.opengis.feature.AttributeName;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.filter.Filter;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

/**
 * Interface providing high level convenience methods used to build type 
 * objects.
 * <p>
 * This when possible this interface should be usd to construct types as 
 * opposed to {@link org.opengis.feature.type.TypeFactory}
 * </p>
 * 
 * @author Justin Deoliveira, The Open Planning Project, jdeolive@openplans.org
 *
 */
public interface TypeBuilder {

	// Flags

	/** 
	 * Flag indicating wether a type is identifiable.
	 */
	int IDENTIFIABLE = 0x01;
	
	/** 
	 * Flag indicating wether a type is nillable. 
	 */
	int NILLABLE = 0x02;
	
	/** 
	 * Flag indicating wether a type is abstract .
	 */
	int ABSTRACT = 0x04;
	
	/**
	 * Flag indicating no flags should be set.
	 */
	int NONE = 0x00;
	
	/**
	 * Flag indicating "default" flags should be set.
	 * <p>
	 *  Default flags set include:
	 *  <ul>
	 *  	<li>NILLABLE</li>
	 *  </ul>
	 * 
	 * </p>
	 */
	int DEFAULT = NILLABLE;
	
	/**
	 * Returns the underlying type factory.
	 */
	TypeFactory getTypeFactory();
	
	/**
	 * Sets the underyling type factory.
	 */
	void setTypeFactory(TypeFactory factory);
	
	// Naming convenience methods 
	/**
	 * Creates a non qualified attribute name.
	 * (ie. {@link AttributeName#getNamespaceURI() = null}).
	 * 
	 * @param local The local name.
	 */
	AttributeName name(String local);
	
	/**
	 * Creates a qualified attribute name.
	 * 
	 * @param namespace The namespace.
	 * @param local The local name.
	 * 
	 */
	AttributeName name(String namespace, String local);
	
	// Creation methods
	/**
	 * Creates a new attribute type.
	 * <br>
	 * <p>
	 * Equivalent to calling:
	 * 	<pre>
	 * 	<code>attributeType(name,binding,flags,Collections.emptySet(),data)</code>
	 * 	</pre>
	 * </p>
	 * @param name The name of the type.
	 * @param binding The class with which the type is bound to.
	 * @param flags Flags to be set on the type.
	 * @param data Additional data used in construction of the type. This value
	 * may be set to null.
	 * 
	 */
	AttributeType attributeType(
		AttributeName name, Class binding, int flags, Object data
	);
	
	/**
	 * Creates a new attribute type.
	 * <br>
	 * <p>
	 * Equivalent to calling:
	 * 	<pre>
	 * 	<code>attributeType(name,binding,flags,restrictions,null,data)</code>
	 * 	</pre>
	 * </p>
	 * 
	 * @param name The name of the type.
	 * @param binding The class with which the type is bound to.
	 * @param flags Flags to be set on the type.
	 * @param restrictions Additional restrictions to be placed on attributes 
	 * of the type. Specifies as instances of {@link Filter}.
	 * @param data Additional data used in construction of the type,may be null.
	 */
	AttributeType attributeType(
		AttributeName name, Class binding, int flags, Set restrictions,
		Object data
	);
	
	/**
	 * Creates a new attribute type.
	 * 
	 * @param name The name of the type.
	 * @param binding The class with which the type is bound to.
	 * @param flags Flags to be set on the type.
	 * @param restrictions Additional restrictions to be placed on attributes 
	 * of the type. Specified as instances of {@link Filter}.
	 * @param superType The super type of this type.
	 * @param data Additional data used in construction of the type,may be null.
	 */
	AttributeType attributeType(
		AttributeName name, Class binding, int flags, Set restrictions, 
		AttributeType superType, Object data
	);
	
	/**
	 * Creates a new complex type.
	 * <br>
	 *	<p>
	 *	Equivalent to calling:
	 *	<pre>
	 *	  <code>complexType(name,schema,flags,Collections.emptySet(),data)</code>
	 *  </pre>
	 *	</p>
	 * @param name The name of the type.
	 * @param schema The collection of attributes that make up the complex type.
	 * @param flags Flags to be set on the type.
	 * @param data Additional data used in construction of the type,may be null.
	 */
	ComplexType complexType(
		AttributeName name, Collection/*<AttributeDescriptor>*/ schema, int flags,
		Object data
	);
	
	/**
	 * Creates a new complex type.
	 * <br>
	 *	<p>
	 *	Equivalent to calling:
	 *	<pre>
	 *	  <code>complexType(name,schema,flags,restrictions,null,data)</code>
	 *  </pre>
	 *	</p>
	 * @param name The name of the type.
	 * @param schema The collection of attributes that make up the complex type.
	 * @param flags Flags to be set on the type.
	 * @param restrictions Additional restrictions to be placed on attributes 
	 * of the type. Specified as instances of {@link Filter}.
	 * @param data Additional data used in construction of the type,may be null.
	 */
	ComplexType complexType(
		AttributeName name, Collection/*<AttributeDescriptor>*/ binding,
		int flags, Set restrictions, Object data
	);
	
	/**
	 * Creates a new complex type.
	 * 
	 * @param name The name of the type.
	 * @param schema The collection of attributes that make up the complex type.
	 * @param flags Flags to be set on the type.
	 * @param restrictions Additional restrictions to be placed on attributes 
	 * of the type. Specified as instances of {@link Filter}.
	 * @param superType The super type of this type,may be null.
	 * @param data Additional data used in construction of the type,may be null.
	 */
	ComplexType complexType(
		AttributeName name, Collection/*<AttributeDescriptor>*/ binding, 
		int flags, Set restrictions, ComplexType superType,
		Object data
	);
	
	/**
	 * Creates a new geometry type.
	 * * <br>
	 *	<p>
	 *	Equivalent to calling:
	 *	<pre>
	 *	  <code>geometryType(name,binding,flags,crs,Collections.emptySet(),data)</code>
	 *  </pre>
	 *	</p>
	 * @param name The name of the type.
	 * @param binding The class with which the type is bound to.
	 * @param flags Flags to be set on the type.
	 * @param crs The coordinate reference system of the type.
	 * @param data Additional data used in construction of the type,may be null.
	 */
	GeometryType geometryType(
		AttributeName name, Class binding, int flags, 
		CoordinateReferenceSystem crs, Object data
	);

	/**
	 * Creates a new geometry type.
	 * <br>
	 * <p>
	 * Equivalent to calling:
	 * <pre>
	 * 	<code>
	 * 	geometryType(name,binding,flags,crs,restrictions,null,data)
	 * 	</code>
	 * </pre>
	 * </p>
	 * @param name The name of the type.
	 * @param binding The class with which the type is bound to.
	 * @param flags Flags to be set on the type.
	 * @param crs The coordinate reference system of the type.
	 * @param restrictions Additional restrictions to be placed on attributes 
	 * of the type. Specified as instances of {@link Filter}.
	 * @param data Additional data used in construction of the type,may be null.
	 */
	GeometryType geometryType(
		AttributeName name, Class binding, int flags, 
		CoordinateReferenceSystem crs, Set restrictions, Object data
	);
	
	/**
	 * Creates a new geometry type.
	 * 
	 * @param name The name of the type.
	 * @param binding The class with which the type is bound to.
	 * @param flags Flags to be set on the type.
	 * @param crs The coordinate reference system of the type.
	 * @param restrictions Additional restrictions to be placed on attributes 
	 * of the type. Specified as instances of {@link Filter}.
	 * @param superType The parent type, may be null.
	 * @param data Additional data used in construction of the type,may be null.
	 */
	GeometryType geometryType(
		AttributeName name, Class binding,  int flags, 
		CoordinateReferenceSystem crs, Set restrictions, 
		AttributeType superType, Object data
	);
	
	/**
	 * Creates a new feature type.
	 * <p>
	 * Equivalent to calling:
	 * <pre>
	 * 	<code>feaureType(name,schema,flags,Collections.emptySet(),data)</code>
	 * </pre>
	 * </p>
	 * @param name The name of the type.
	 * @param schema The collection of attributes that make up the type. 
	 * @param flags Flags to be set on the type.
	 * @param data Additional data used in construction of the type,may be null.
	 */
	FeatureType featureType(
		AttributeName name, Collection/*<AttributeDescriptor>*/ schema, int flags, 
		Object data
	);

	/**
	 * Creates a new feature type.
	 * <p>
	 * Equivalent to calling:
	 * <pre>
	 * 	<code>feaureType(name,schema,flags,restrictions,null,data)</code>
	 * </pre>
	 * </p>
	 * @param name The name of the type.
	 * @param schema The collection of attributes that make up the type. 
	 * @param flags Flags to be set on the type.
	 * @param restrictions Additional restrictions to be placed on attributes 
	 * of the type. Specified as instances of {@link Filter}.
	 * @param data Additional data used in construction of the type,may be null.
	 */
	FeatureType featureType(
		AttributeName name, Collection/*<AttributeDescriptor>*/ schema, int flags,
		Set restrictions, Object data
	);
	
	/**
	 * Creates a new feature type.
	 * <p>
	 * Equivalent to calling:
	 * <pre>
	 * 	<code>feaureType(name,schema,flags,restrictions,null,data)</code>
	 * </pre>
	 * </p>
	 * @param name The name of the type.
	 * @param schema The collection of attributes that make up the type. 
	 * @param flags Flags to be set on the type.
	 * @param restrictions Additional restrictions to be placed on attributes 
	 * of the type. Specified as instances of {@link Filter}.
	 * @param superType The parent type, may be null.
	 * @param data Additional data used in construction of the type,may be null.
	 */
	FeatureType featureType(
		AttributeName name, Collection/*<AttributeDescriptor>*/ schema, int flags,
		Set restrictions, ComplexType superType, Object data
	);
	
	/**
	 * Creates a new simple feature type.
	 * <p>
	 * Equivalent to calling:
	 * <pre>
	 * 	<code>simpleFeatureType(name,schema,flags,null,data)</code>
	 * </pre>
	 * </p>
	 * @param name The name of the type.
	 * @param schema The ordered list of attribute types making up this type.
	 * @param flags Flags to be set on the type.
	 * @param data Additional data used in construction of the type,may be null.
	 */
	SimpleFeatureType simpleFeatureType(
		AttributeName name, List/*<AttributeType>*/ schema, int flags, Object data
	);

	/**
	 * Creates a new simple feature type.
	 * <p>
	 * Equivalent to calling:
	 * <pre>
	 * 	<code>simpleFeatureType(name,schema,flags,defaultGeometry,Collections.emptySet(),data)</code>
	 * </pre>
	 * </p>
	 * @param name The name of the type.
	 * @param schema The ordered list of attribute types making up this type.
	 * @param flags Flags to be set on the type.
	 * @param defaultGeometry The default geometry type.
	 * @param data Additional data used in construction of the type,may be null.
	 */
	SimpleFeatureType simpleFeatureType(
		AttributeName name, List/*<AttributeDescriptor>*/ schema, int flags,
		GeometryType defaultGeometry, Object data
	);

	/**
	 * Creates a new simple feature type.
	 *
	 * @param name The name of the type.
	 * @param schema The ordered list of attribute types making up this type.
	 * @param flags Flags to be set on the type.
	 * @param defaultGeometry The default geometry type.
	 * @param restrictions Additional restrictions to be placed on attributes 
	 * of the type. Specified as instances of {@link Filter}.
	 * @param data Additional data used in construction of the type,may be null.
	 */
	SimpleFeatureType simpleFeatureType(
		AttributeName name, List/*<AttributeDescriptor>*/ schema, int flags,
		GeometryType defaultGeometry, Set restrictions, Object data
	);
	

	FeatureCollectionType featureCollectionType(
		AttributeName name, Set membersTypes, int flags,
		Collection/*<AttributeDescriptor>*/ schema, GeometryType defaultGeom, 
		Set restrictions, FeatureCollectionType superType
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
	
}
