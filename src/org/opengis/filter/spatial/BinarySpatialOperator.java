package org.opengis.filter.spatial;

import org.opengis.spatialschema.geometry.Geometry;

/**
 * Abstract superclass for filter operators that perform some sort of spatial
 * comparison on two geometric objects: one which is a property of a feature and
 * one which is a fixed geometry object.
 */
public interface BinarySpatialOperator extends SpatialOperator {
    /**
     * Returns the constant Geometry object against which the feature's geometry
     * will be tested.  The nature of the test is dependent on the subclass.
     */
    public Geometry /* GM_Object */ getGeometry();
}
