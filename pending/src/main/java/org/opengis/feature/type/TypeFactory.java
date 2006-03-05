package org.opengis.feature.type;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.opengis.feature.AttributeName;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.feature.type.AttributeDescriptor;
import org.opengis.feature.type.AttributeType;
import org.opengis.feature.xml.ChoiceType;
import org.opengis.filter.Filter;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

/**
 * Factory interface for the typing system.
 * 
 * @author Gabriel Roldan, Axios Engineering 
 * @author Justin Deoliveira, The Open Planning Project
 * 
 * TODO: add FeatureCollection factory methods
 */
public interface TypeFactory {

	/**
	 * Creates a new attribute type.
	 * 
	 * @param name The name of the type type.
	 * @param binding The class this type is bound to.
	 */
	AttributeType createType(String name, Class binding);

	/**
	 * Creates a new attribute type.
	 * 
	 * @param name The qualified name of hte type.
	 * @param binding The class this type is bound to.
	 */
	AttributeType createType(AttributeName name, Class binding);

	/**
	 * Creates a new attribute type.
	 * 
	 * @param name The qualified name of hte type.
	 * @param binding The class this type is bound to.
	 * @param identified Flag inidicating wether the type is identifiable.
	 * @param nillable Flag indicating wether the type is nillable.
	 * @param restrictions Addional constraints on the type.
	 */
	AttributeType createType(AttributeName name, Class binding, boolean identified,
			boolean nillable, Set<Filter> restrictions);

	/**
	 * Creates a new attribute type.
	 * 
	 * @param name The qualified name of hte type.
	 * @param binding The class this type is bound to.
	 * @param identified Flag inidicating wether the type is identifiable.
	 * @param nillable Flag indicating wether the type is nillable.
	 * @param restrictions Addional constraints on the type.
	 * @param superType The parent or super type.
	 */
	AttributeType createType(AttributeName name, Class binding, boolean identified,
			boolean nillable, Set<Filter> restrictions, AttributeType superType);

	/**
	 * Creates a new geometry type.
	 * 
	 * @param name The qualified name of the type.
	 * @param binding The class this type of bound to.
	 * @param nillable Flag indicating wether the type is nillable.
	 * @param crs The coordinate reference system of the type.
	 */
	GeometryType createGeometryType(AttributeName name, Class binding,
			boolean nillable, CoordinateReferenceSystem crs);

	/**
	 * Creates a new geometry type.
	 * 
	 * @param name The qualified name of the type.
	 * @param binding The class this type of bound to.
	 * @param identified Flag inidicating wether the type is identifiable.
	 * @param nillable Flag indicating wether the type is nillable.
	 * @param crs The coordinate reference system of the type.
	 * @param restrictions Set of additional restrictions on attributes of this
	 * type
	 * @param The parent or super type.
	 */
	GeometryType createGeometryType(AttributeName name, Class binding,
			boolean identified, boolean nillable,
			CoordinateReferenceSystem crs, Set<Filter> restrictions,
			GeometryType superType);

	/**
	 * Creates a new complex type.
	 *
	 * @param name The name of the type.
	 * @param schema Collection of attribute descriptors forming the schema or 
	 * content of the type.
	 */
	ComplexType createType(String name, Collection<AttributeDescriptor> schema);

	/**
	 * Creates a new complex type.
	 * 
	 * @param name The qualified name of the type.
	 * @param schema Collection of attribute descriptors forming the schema or 
	 * content of the type.
	 */
	ComplexType createType(AttributeName name, Collection<AttributeDescriptor> schema);

	/**
	 * Creates a new complex type.
	 * 
	 * @param name The qualified name of the type.
	 * @param schema Collection of attribute descriptors forming the schema or 
	 * content of the type
	 * @param identified Flag indicating if the type is identifiable.
	 * @param nillable Flag indicating if the type is nillable.
	 * @param restrictions A set of filter containing additional restrictions 
	 * on the type.
	 */
	ComplexType createType(AttributeName name, Collection<AttributeDescriptor> schema,
			boolean identified, boolean nillable, Set<Filter> restrictions);

	/**
	 * Creates a new complex type.
	 * 
	 * @param name The qualified name of the type.
	 * @param schema Collection of attribute descriptors forming the schema or 
	 * content of the type
	 * @param identified Flag indicating if the type is identifiable.
	 * @param nillable Flag indicating if the type is nillable.
	 * @param restrictions A set of filter containing additional restrictions 
	 * on the type.
	 * @param superType The super or parent type of the new type.
	 * @param isAbstract Flag indicating if the type is abstract/
	 */
	ComplexType createType(AttributeName name, Collection<AttributeDescriptor> schema, 
			boolean identified,boolean nillable, Set<Filter> restrictions, 
			ComplexType superType,boolean isAbstract);

	/**
	 * Creates a new feature type.
	 * 
	 * @param name Feature type name.
	 * @param schema Schema describing contents of the type.
	 * @param defaultGeometry The default geometry of the type.
	 */
	FeatureType createFeatureType(String name, Collection<AttributeDescriptor> schema,
			GeometryType defaultGeometry);

	/**
	 * Creates a new feature type.
	 * 
	 * @param name Qualified Feature type name.
	 * @param schema Schema describing contents of the type.
	 * @param defaultGeometry The default geometry of the type.
	 */
	FeatureType createFeatureType(AttributeName name, Collection<AttributeDescriptor> schema,
			GeometryType defaultGeometry);

	/**
	 * Creates a new feature type.
	 * 
	 * @param name Qualified Feature type name.
	 * @param schema Schema describing contents of the type.
	 * @param defaultGeometry The default geometry of the type.
	 * @param restrictions Any additional restrictions on attributes of the type.
	 * @param superType The parent type.
	 * @param isAbstract Flag indicating wether the type is abstract.
	 */
	FeatureType createFeatureType(AttributeName name, Collection<AttributeDescriptor> schema,
			GeometryType defaultGeometry, Set<Filter> restrictions,
			FeatureType superType, boolean isAbstract);

	/**
	 * Creates a simple feature type.
	 * 
	 * @param name Name of the type.
	 * @param types List of types representing the content of the type.
	 * @param defaultGeometry The default geometry type.
	 */
	SimpleFeatureType createFeatureType(String name, List<AttributeType> types,
			GeometryType defaultGeometry);

	/**
	 * Creates a simple feature type.
	 * 
	 * @param name Qualified name of the type.
	 * @param types List of types representing the content of the type.
	 * @param defaultGeometry The default geometry type.
	 */
	SimpleFeatureType createFeatureType(AttributeName name, List<AttributeType> types,
			GeometryType defaultGeometry);

	/**
	 * Creates a simple feature type.
	 * 
	 * @param name Name of the type.
	 * @param schema List of descriptors representing the content of the type.
	 * @param defaultGeometry The default geometry type.
	 * @param restrictions Set of filters restricting the contents of attributes
	 * of this type.
	 * @param superType Parent or super type from which this type inherits.
	 * @param isAbstract Flag indicating wether the type is abstract.
	 */
	SimpleFeatureType createFeatureType(AttributeName name, List<AttributeDescriptor> schema,
			GeometryType defaultGeometry, Set<Filter> restrictions,
			SimpleFeatureType superType, boolean isAbstract);

	
	/**
	 * Creates a new "empty" feature collection type.
	 * 
	 */
	FeatureCollectionType createFeatureCollectionType();

	/**
	 * Creates a new feature collection type containing a single member type.
	 *  
	 * @param membersType The type of members in the feature collection.
	 * 
	 */
	FeatureCollectionType createFeatureCollectionType(FeatureType membersType);

	/**
	 * Creates a FeatureCollectionType named <code>name</code> whose member
	 * Features can be of any FeatureType.
	 * 
	 * @param name
	 */
	FeatureCollectionType createFeatureCollectionType(AttributeName name);

	/**
	 * Creates a FeatureCollectionType named <code>name</code> whose member
	 * Features can be only of <code>membersType</code> FeatureType.
	 * 
	 * @param name
	 * @param membersType
	 */
	FeatureCollectionType createFeatureCollectionType(AttributeName name,
			FeatureType membersType);

	/**
	 * Creates a FeatureCollectionType named <code>name</code> whose member
	 * Features can be of any of the FeatureTypes in <code>membersTypes</code>.
	 * <p>
	 * All parametesr may be null, in which case sensible defaults will be
	 * applied.
	 * </p>
	 * 
	 * @param name
	 *            name of FeatureCollectionType. Required if
	 *            <code>schema != null</code>. Otherwise, if
	 *            <code>null</code> is passed,
	 *            <code>gml:FeatureCollection</code> will be used.
	 * @param membersTypes
	 *            list of allowable FeatureTypes that Feature members must
	 *            adhere to.
	 * @param schema
	 *            the schema for the Feature representation of the collection.
	 *            You will generally pass <code>null</code>, at least you
	 *            want to add attributes to the FeatureCollection itself.
	 * @param defaultGeom
	 *            only needed if adding attributes to the Feature aspect of the
	 *            collection. Use <code>null</code> if you don't add
	 *            GeometryTypes or are just adding one, in which case it will be
	 *            used as the default geometry.
	 * @param restrictions
	 *            restrictions applied to contained Features.
	 * @param superType
	 *            the FeatureCollectionType the created one inherits from.
	 * @param isAbstract
	 *            wether the created FeatureCollectionType is abstract.
	 */
	FeatureCollectionType createFeatureCollectionType(AttributeName name,
			Set<FeatureType> membersTypes, Collection<AttributeDescriptor> schema,
			GeometryType defaultGeom, Set<Filter> restrictions,
			FeatureCollectionType superType, boolean isAbstract);
	
}
