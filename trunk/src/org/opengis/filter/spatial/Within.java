package org.opengis.filter.spatial;

/**
 * "Concrete" subclass of <code>BinarySpatialOperator</code> that evaluates to
 * true if the feature's geometry is completely contained by the constant
 * geometry held by this object.
 */
public interface Within extends BinarySpatialOperator {
}
