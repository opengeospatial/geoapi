package org.opengis.filter.spatial;

/**
 * "Concrete" subclass of <code>BinarySpatialOperator</code> that evaluates to
 * true if the feature's geometry touches, but does not overlap with the
 * geometry held by this object.
 */
public interface Touches extends BinarySpatialOperator {
}
