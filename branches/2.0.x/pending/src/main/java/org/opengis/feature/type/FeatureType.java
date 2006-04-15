package org.opengis.feature.type;

import java.util.Collection;

import org.opengis.feature.Attribute;
import org.opengis.referencing.crs.CoordinateReferenceSystem;


/**
 * Describes a Feature, this is a step in the chain towards FeatureCollectionType.
 * <p>
 * This class provides no additional modeling power beyond ComplexType, it does however
 * come with additional restrictions that may be described at the Java level.
 * </p>
 * @author Jody Garnett
 *
 */
public interface FeatureType<C extends Collection<Attribute>> extends ComplexType<C> {
			
	/**
	 * Indicates which AttributeType is to be considered the default
	 * geometry.
	 * @return AttributeType used to locate the default Geometry
	 */
	AttributeDescriptor<GeometryType> getDefaultGeometry();
	//GeometryType getDefaultGeometry();
	
	/**
	 * The coordinate reference system of the Geometries
	 * attributes contained by this feature type.
	 * <p>
	 * Note: since this appears in the type system (CRS is XPathable) we can define
	 * restrictions as a Filter - these restrictions are inteneded to be applied to
	 * any contained geometry attributes.
	 * </p>
	 * <p>
	 * This value may be null, in which case you may need to check the GeomtryType
	 * CRS directly. When working with GML any associated FeatureCollection may also
	 * provide CRS information used for interreptation. It is responsibility of those
	 * parsing GML to make this interretation known.
	 * </p>
	 */
	public CoordinateReferenceSystem getCRS();
	
	/**
	 * Super may be a normal ComplexType.
	 */
///	ComplexType getSuper();
}