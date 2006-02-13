package org.opengis.feature.type;

import org.opengis.referencing.crs.CoordinateReferenceSystem;
import com.vividsolutions.jts.geom.Geometry;

/**
 * Represents (explicitly) the binding of an AttributeType to Geometry information.
 * 
 * @author Jody Garnett
 */
public interface GeometryType<T extends Geometry> extends AttributeType<T> {
	
	/** Explicitly bound to Geometry */
	public Class<T> getBinding();
	
	/**
	 * If there is a superclass it better also be a Geometry
	 */
	public AttributeType<? super T> getSuper();
		
	/**
	 * The coordinate reference system of the Geometries
	 * contained by attributes of this type.
	 * <p>
	 * Note: when using GeoAPI Geometry (where CRS is XPathable) we can define
	 * this restriction as a Facet (indeed we can allow for a discrete set of allowable
	 * CRS).
	 * </p>
	 * @return
	 */
	public CoordinateReferenceSystem getCRS();
	
}