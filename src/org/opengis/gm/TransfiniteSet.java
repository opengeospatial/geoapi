/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.gm;

// OpenGIS direct dependencies
import org.opengis.gm.geometry.DirectPosition;


/**
 * A possibly infinite set; restricted only to values. For example, the integers and the real
 * numbers are transfinite sets. This is actually the usual definition of set in mathematics,
 * but programming languages restrict the term set to mean finite set.
 *
 * @UML type TransfiniteSet<DirectPosition>
 * @author ISO TS 19103
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @revisit This interface should uses generic type. It focus on {@link DirectPosition}
 *          for now because it is the only type used at this time.
 */
public interface TransfiniteSet {
    /**
     * Returns <code>true</code> if this <code>TransfiniteSet</code> contains another
     * <code>TransfiniteSet</code>. If the passed <code>TransfiniteSet</code> is a
     * {@link org.opengis.gm.primitive.Point}, then this operation is the equivalent
     * of a set-element test for the {@link DirectPosition} of that point within this
     * <code>TransfiniteSet</code>.
     * <br><br>
     * <strong>Note:</strinng> <code>contains</code> is strictly a set theoretic containment,
     * and has no dimensionality constraint. In a {@link org.opengis.gm.complex.Complex}, no
     * {@link org.opengis.gm.primitive.Primitive} will contain another unless a dimension is
     * skipped.
     *
     * @param  pointSet The set to be checked for containment in this set.
     * @return <code>true</code> if this set contains all of the elements of the specified set.
     */
    public boolean contains(TransfiniteSet pointSet);

    /**
     * Returns <code>true</code> if this <code>TransfiniteSet</code> contains a
     * single point given by a coordinate.
     */
    public boolean contains(DirectPosition point);
}
