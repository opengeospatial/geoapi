package org.opengis.filter.spatial;

/**
 * "Concrete" subclass of <code>BinarySpatialOperator</code> that evaluates to
 * true if the interior of the first geometry somewhere overlaps the
 * interior of the second geometry.
 */
public interface Overlaps extends BinarySpatialOperator {
}
