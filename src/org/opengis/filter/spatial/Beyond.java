package org.opengis.filter.spatial;

/**
 * "Concrete" subclass of <code>DistanceBufferOperator</code> that evaluates as
 * true when all of a feature's geometry lies beyond (i.e. is more distant) than
 * the given distance from this object's constant geometry.
 */
public interface Beyond extends DistanceBufferOperator {
}
