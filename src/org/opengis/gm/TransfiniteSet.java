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
     * <strong>NOTE:</strong> <code>contains</code> is strictly a set theoretic containment,
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
     *
     * @param  point The point to be checked for containment in this set.
     * @return <code>true</code> if this set contains the specified point.
     */
    public boolean contains(DirectPosition point);

    /**
     * Returns <code>true</code> if this <code>TransfiniteSet</code> intersects another
     * <code>TransfiniteSet</code>. Withing a {@link org.opengis.gm.complex.Complex},
     * the {@link org.opengis.gm.primitive.Primitive}s do not intersect one another.
     * In general, topologically structured data uses shared geometric objects to
     * capture intersection information.
     * <br><br>
     * <strong>NOTE:</strong> This intersect is strictly a set theoretic common containment of
     * {@link DirectPosition}s. Two {@link org.opengis.gm.primitive.Curve}s do not intersect if
     * they share a common end point because {@link org.opengis.gm.primitive.Primitive}s are
     * considered to be open (do not contain their boundary). If two 
     * {@link org.opengis.gm.complex.CompositeCurve}s share a common end point, then they
     * intersect because {@link org.opengis.gm.complex.Complex}es are considered to be closed
     * (contain their boundary).
     *
     * @param  pointSet The set to be checked for intersection with this set.
     * @return <code>true</code> if this set intersects some of the elements of the specified set.
     */
    public boolean intersects(TransfiniteSet pointSet);

    /**
     * Returns <code>true</code> if this <code>TransfiniteSet</code> is equal to another
     * <code>TransfiniteSet</code>. Two different instances of <code>TransfiniteSet</code>
     * are equal if they return the same boolean value for the operation
     * {@link #contains(DirectPosition) contains} for every tested {@link DirectPosition}
     * within the valid range of the coordinate reference system associated to the object.
     * <br><br>
     * <strong>NOTE:</strong> Since an infinite set of direct positions cannot be tested,
     * the internal implementation of equal must test for equivalence between two, possibly
     * quite different, representations. This test may be limited to the resolution of the
     * coordinate system or the accuracy of the data. Implementations may define a tolerance
     * that returns <code>true</code> if the two <code>TransfiniteSet</code> have the same
     * dimension and each direct position in this <code>TransfiniteSet</code> is within a
     * tolerance distance of a direct position in the passed <code>TransfiniteSet</code> and
     * vice versa.
     *
     * @param pointSet The set to test for equality.
     * @return <code>true</code> if the two set are equals.
     */
    public boolean equals(TransfiniteSet pointSet);

    /**
     * Returns the set theoretic union of this <code>TransfiniteSet</code> and the passed
     * <code>TransfiniteSet</code>.
     *
     * @param pointSet The second set.
     * @return The union of both sets.
     */
    public TransfiniteSet union(TransfiniteSet pointSet);

    /**
     * Returns the set theoretic intersection of this <code>TransfiniteSet</code> and the passed
     * <code>TransfiniteSet</code>.
     *
     * @param pointSet The second set.
     * @return The intersection of both sets.
     */
    public TransfiniteSet intersection(TransfiniteSet pointSet);

    /**
     * Returns the set theoretic difference of this <code>TransfiniteSet</code> and the passed
     * <code>TransfiniteSet</code>.
     *
     * @param pointSet The second set.
     * @return The difference between both sets.
     */
    public TransfiniteSet difference(TransfiniteSet pointSet);

    /**
     * Returns the set theoretic symmetric difference of this <code>TransfiniteSet</code> and the
     * passed <code>TransfiniteSet</code>.
     *
     * @param pointSet The second set.
     * @return The symmetric difference between both sets.
     */
    public TransfiniteSet symmetricDifference(TransfiniteSet pointSet);
}
