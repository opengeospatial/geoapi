package org.opengis.filter.spatial;

import org.opengis.spatialschema.geometry.Geometry;

/**
 * Abstract superclass for spatial operators that check that one shape satisfies
 * some relation to a buffer around another shape.  This could be used to find,
 * for example, all the geometries that come within 10 meters of a river.
 */
public interface DistanceBufferOperator extends SpatialOperator {
    /**
     * Returns the constant Geometry around which a distance buffer is computed
     * and used for comparison against features' geometries.
     */
    public Geometry /* GM_Object */ getGeometry();

    /**
     * Returns the buffer distance around the geometry that will be used when
     * comparing features' geometries.
     */
    public double getDistance();

    /**
     * Gets the units of measure that can be used to interpret the distance
     * value held by this object.  An implementation may throw an exception if
     * these units differ from the units of the coordinate system of its
     * geometry or the feature's geometry.
     */
    public String getDistanceUnits();
}
