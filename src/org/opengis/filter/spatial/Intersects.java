package org.opengis.filter.spatial;

/**
 * "Concrete" subclass of <code>BinarySpatialOperator</code> that evaluates to
 * true if the two geometric operands intersect.  This is the opposite of the
 * <code>Disjoint</code> operator.
 */
public interface Intersects extends BinarySpatialOperator {
}
