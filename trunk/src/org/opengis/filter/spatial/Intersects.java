package org.opengis.filter.spatial;

/**
 * "Concrete" subclass of <code>BinarySpatialOperator</code> that evaluates to
 * true if the feature's geometry anywhere intersects the geometry held by this
 * object.
 */
public interface Intersects extends BinarySpatialOperator {
}
