package org.opengis.filter.spatial;

/**
 * "Concrete" subclass of <code>BinarySpatialOperator</code> that evaluates to
 * true if the interior of the feature's geometry somewhere overlaps the
 * interior of the geometry held by this object.
 */
public interface Overlaps extends BinarySpatialOperator {
}
