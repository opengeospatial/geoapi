package org.opengis.feature.type;

import java.util.Collection;

import org.opengis.referencing.crs.CoordinateReferenceSystem;

/**
 * Type builder for features.
 * 
 * @author Justin Deoliveira, The Open Planning Project, jdeolive@openplans.org
 *
 * @param <T>
 * @param <B>
 */
public interface FeatureTypeBuilder<T extends FeatureType,B extends Collection>
	extends ComplexTypeBuilder<T,B> {
	
	/**
	 * Sets the coordinate reference system of the created type.
	 */
	void setCRS(CoordinateReferenceSystem crs);
	
	/**
	 * Returns the coordinate reference system of the created type.
	 */
	CoordinateReferenceSystem getCRS();
	
	/**
	 * Sets the default geometry attribute of the created type.
	 */
	void setDefaultGeometry(GeometryType type);
	
	/**
	 * Returns the default geometry attribute of the created type.
	 */
	GeometryType getDefaultGeometry();
}
