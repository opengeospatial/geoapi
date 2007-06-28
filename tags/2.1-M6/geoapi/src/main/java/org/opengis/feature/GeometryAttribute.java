package org.opengis.feature;

import org.opengis.feature.type.GeometryType;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.geometry.BoundingBox;

//Java 1.4 imports
//import org.opengis.feature.type.AttributeType;

/**
 * Represent a Geometry as an attribute content.
 * <p>
 * This class is cotton candy and does not add any new ability to our modeling.
 * We are using generics to avoid making the exact instance of the Geometry known
 * at this time, allowing the use of JTS for SFSQL use, or GeoAPI interfaces for ISO.
 * </p>
 */
public interface GeometryAttribute<G, T extends GeometryType<G>> extends Attribute<G,T> {
	/**
	 * The Coordinate Reference System of this geometry.
	 * <p>
	 * This may not be needed if we are using a GeoAPI Geometry (at which point
	 * this method should be considered a convience method).
	 * </p>
	 * <p>
	 * As it stands this method will help transition code over from JTS as
	 * GeoAPI Geometry implementations are made avaialble.
	 * </p>
	 */
	CoordinateReferenceSystem getCRS();

    /**
     * Sets the coordinate reference system for the attribute.
     */
    void setCRS(CoordinateReferenceSystem crs);
    
	/**
	 * The bounds of this geometry.
	 * <p>
	 * This may not be needed if we are using a GeoAPI Geometry (at which point
	 * this method should be considered a convience method).
	 * </p>
	 * <p>
	 * As it stands this method will help transition code over from JTS as
	 * GeoAPI Geometry implementations are made avaialble.
	 * </p>
	 */
	BoundingBox getBounds();
	
	/**
	 * Although this is tipically a derrived quantity of the contents, this
	 * value is often available in precomputed form from data providers.
	 * <p>
	 * This method allows a data provider to store the bounds information
	 * associated with the contents of this geometry attribute.
	 * @param bounds
	 */
	public void setBounds( BoundingBox bounds );

	/**
	 * GeometryType should be configued with a Geometry for getJavaType.
	 * <p>
	 * Q: If needed a set of well-known GeometryType can be constructed, may be
	 * needed to report CRS and Bounds constraints on data? A: It was needed
	 * when we switched over to Attribute
	 */
	T getType();

	/**
	 * Retrieve Geometry.
	 * <p>
	 * We may want to relax this to Object to allow for JTS or GeoAPI based
	 * objects for the first release.
	 */
	G get();

	/**
	 * Set provided Geometry
	 * <p>
	 * We may want to relax this to Object to allow for JTS or GeoAPI based
	 * objects for the first release.
	 */
	void set(G geom);
}