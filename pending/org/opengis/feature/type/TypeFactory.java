package org.opengis.feature.type;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.namespace.QName;

import org.geotools.filter.Filter;
import org.opengis.feature.schema.Descriptor;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import com.vividsolutions.jts.geom.Geometry;

/**
 * Factory interface for the typing system.
 * 
 * @author Gabriel Roldan, Axios Engineering TODO: add FeatureCollection factory
 *         methods
 */
public interface TypeFactory {

	/**
	 * 
	 * @param name
	 * @param binding
	 * @return
	 */
	AttributeType createType(String name, Class binding);

	/**
	 * 
	 * @param name
	 * @param binding
	 * @return
	 */
	AttributeType createType(QName name, Class binding);

	/**
	 * 
	 * @param name
	 * @param binding
	 * @param identified
	 * @param nillable
	 * @param restrictions
	 * @return
	 */
	AttributeType createType(QName name, Class binding, boolean identified,
			boolean nillable, Set<Filter> restrictions);

	/**
	 * 
	 * @param name
	 * @param binding
	 * @param identified
	 * @param nillable
	 * @param restrictions
	 * @param superType
	 * @return
	 */
	AttributeType createType(QName name, Class binding, boolean identified,
			boolean nillable, Set<Filter> restrictions, AttributeType superType);

	GeometryType createGeometryType(QName name, Class binding,
			boolean nillable, CoordinateReferenceSystem crs);

	GeometryType createGeometryType(QName name, Class binding,
			boolean identified, boolean nillable,
			CoordinateReferenceSystem crs, Set<Filter> restrictions,
			GeometryType superType);

	/**
	 * 
	 * @param name
	 * @param schema
	 * @return
	 */
	ComplexType createType(String name, Descriptor schema);

	/**
	 * 
	 * @param name
	 * @param schema
	 * @return
	 */
	ComplexType createType(QName name, Descriptor schema);

	/**
	 * 
	 * @param name
	 * @param schema
	 * @param identified
	 * @param nillable
	 * @param restrictions
	 * @return
	 */
	ComplexType createType(QName name, Descriptor schema, boolean identified,
			boolean nillable, Set<Filter> restrictions);

	/**
	 * 
	 * @param name
	 * @param schema
	 * @param identified
	 * @param nillable
	 * @param restrictions
	 * @param superType
	 * @param isAbstract
	 * @return
	 */
	ComplexType createType(QName name, Descriptor schema, boolean identified,
			boolean nillable, Set<Filter> restrictions, ComplexType superType,
			boolean isAbstract);

	/**
	 * 
	 * @param name
	 * @param schema
	 * @param defaultGeometry
	 * @return
	 */
	FeatureType createFeatureType(String name, Descriptor schema,
			GeometryType defaultGeometry);

	/**
	 * 
	 * @param name
	 * @param schema
	 * @param defaultGeometry
	 * @return
	 */
	FeatureType createFeatureType(QName name, Descriptor schema,
			GeometryType defaultGeometry);

	/**
	 * 
	 * @param name
	 * @param schema
	 * @param defaultGeometry
	 * @param restrictions
	 * @param superType
	 * @param isAbstract
	 * @return
	 */
	FeatureType createFeatureType(QName name, Descriptor schema,
			GeometryType defaultGeometry, Set<Filter> restrictions,
			FeatureType superType, boolean isAbstract);

	SimpleFeatureType createFeatureType(String name, List<AttributeType> types,
			GeometryType defaultGeometry);

	/**
	 * 
	 * @param name
	 * @param types
	 * @param defaultGeometry
	 * @return
	 */
	SimpleFeatureType createFeatureType(QName name, List<AttributeType> types,
			GeometryType defaultGeometry);

	SimpleFeatureType createFeatureType(QName name, List<AttributeType> types,
			GeometryType defaultGeometry, Set<Filter> restrictions,
			SimpleFeatureType superType, boolean isAbstract);

	public FeatureCollectionType createFeatureCollectionType();

	public FeatureCollectionType createFeatureCollectionType(
			FeatureType membersType);

	/**
	 * Creates a FeatureCollectionType named <code>name</code> whose member
	 * Features can be of any FeatureType.
	 * 
	 * @param name
	 * @return
	 */
	public FeatureCollectionType createFeatureCollectionType(QName name);

	/**
	 * Creates a FeatureCollectionType named <code>name</code> whose member
	 * Features can be only of <code>membersType</code> FeatureType.
	 * 
	 * @param name
	 * @param membersType
	 * @return
	 */
	public FeatureCollectionType createFeatureCollectionType(QName name,
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
	 * @return
	 */
	public FeatureCollectionType createFeatureCollectionType(QName name,
			Set<FeatureType> membersTypes, Descriptor schema,
			GeometryType defaultGeom, Set<Filter> restrictions,
			FeatureCollectionType<?> superType, boolean isAbstract);
}