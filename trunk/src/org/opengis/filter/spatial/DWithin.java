package org.opengis.filter.spatial;

/**
 * "Concrete" subclass of <code>DistanceBufferOperator</code> that evaluates as
 * true when any part of a feature's geometry lies within the given distance
 * from this object's constant geometry.
 */
public interface DWithin extends DistanceBufferOperator {
}
