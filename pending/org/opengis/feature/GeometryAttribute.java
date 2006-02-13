package org.opengis.feature;

import org.opengis.feature.type.GeometryType;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;

/**
 * Represent a Geometry as complex content.
 * <p>
 * List of information to make available through ComplexAttribute api:
 * <ul>
 * <li>srs
 * <li>bounds
 * </ul>
 * </p>
 * <p>
 * This class is cotten candy and does not add any new ability to our modeling.
 * </p>
 */
public interface GeometryAttribute extends Attribute {
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
	Envelope getBounds();

	/**
	 * GeometryType should be configued with a Geometry for getJavaType.
	 * <p>
	 * Q: If needed a set of well-known GeometryType can be constructed, may be
	 * needed to report CRS and Bounds constraints on data? A: It was needed
	 * when we switched over to Attribute
	 */
	GeometryType<? super Geometry> getType();

	/**
	 * Retrieve Geometry.
	 * <p>
	 * We may want to relax this to Object to allow for JTS or GeoAPI based
	 * objects for the first release.
	 */
	Geometry get();

	/**
	 * Set provided Geometry
	 * <p>
	 * We may want to relax this to Object to allow for JTS or GeoAPI based
	 * objects for the first release.
	 */
	void set(Geometry geom);
}