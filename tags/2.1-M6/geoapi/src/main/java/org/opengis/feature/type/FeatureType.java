package org.opengis.feature.type;

import java.util.Collection;

import org.opengis.feature.Property;
import org.opengis.referencing.crs.CoordinateReferenceSystem;


/**
 * Describes the contents of a Feature, basically a ComplexType with at least a
 * Geometry and CRS.
 * <p>
 * This class provides no additional modeling power beyond ComplexType. It does
 * formally includes both a Geometry and a CRS these items are available to
 * you to place restrictions against.
 * </p>
 * <p>
 * You should be aware that the GML definition of AbstractFeatureType includes
 * bounds, crs name and description as "optional" attributes - as such these
 * ideas are very common and have been included in the Feature API.
 * </p>
 * @author Jody Garnett
 */
public interface FeatureType<E extends Property,C extends Collection<E>> extends ComplexType<E,C> {
			
	/**
	 * Indicates which AttributeType is to be considered the default
	 * geometry.
	 * @return AttributeType used to locate the default Geometry
	 */
	AttributeDescriptor<GeometryType> getDefaultGeometry();
	
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