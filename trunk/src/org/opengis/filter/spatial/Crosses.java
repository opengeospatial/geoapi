package org.opengis.filter.spatial;

/**
 * "Concrete" subclass of <code>BinarySpatialOperator</code> that evaluates to
 * true if the feature's geometry has lines that cross the lines of the geometry
 * held by this object.
 */
public interface Crosses extends BinarySpatialOperator {
}
