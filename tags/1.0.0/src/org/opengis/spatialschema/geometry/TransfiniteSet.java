/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.spatialschema.geometry;


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
     * {@linkplain org.opengis.spatialschema.geometry.primitive.Point point}, then this operation is the
     * equivalent of a set-element test for the {@linkplain DirectPosition direct position}
     * of that point within this <code>TransfiniteSet</code>.
     *
     * <blockquote><font size=2>
     * <strong>NOTE:</strong> <code>contains</code> is strictly a set theoretic containment,
     * and has no dimensionality constraint. In a {@linkplain org.opengis.spatialschema.geometry.complex.Complex
     * complex}, no {@linkplain org.opengis.spatialschema.geometry.primitive.Primitive primitive} will contain
     * another unless a dimension is skipped.
     * </font></blockquote>
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
     * <code>TransfiniteSet</code>. Withing a {@linkplain org.opengis.spatialschema.geometry.complex.Complex complex},
     * the {@linkplain org.opengis.spatialschema.geometry.primitive.Primitive primitives} do not intersect one another.
     * In general, topologically structured data uses shared geometric objects to
     * capture intersection information.
     *
     * <blockquote><font size=2>
     * <strong>NOTE:</strong> This intersect is strictly a set theoretic common containment of
     * {@linkplain DirectPosition direct positions}.
     * Two {@linkplain org.opengis.spatialschema.geometry.primitive.Curve curves} do not intersect if they share a common
     * end point because {@linkplain org.opengis.spatialschema.geometry.primitive.Primitive primitives} are considered to be
     * open (do not contain their boundary).
     * If two {@linkplain org.opengis.spatialschema.geometry.complex.CompositeCurve composite curves} share a common end point,
     * then they intersect because {@linkplain org.opengis.spatialschema.geometry.complex.Complex complexes} are considered to
     * be closed (contain their boundary).
     * </font></blockquote>
     *
     * @param  pointSet The set to be checked for intersection with this set.
     * @return <code>true</code> if this set intersects some of the elements of the specified set.
     */
    public boolean intersects(TransfiniteSet pointSet);

    /**
     * Returns <code>true</code> if this <code>TransfiniteSet</code> is equal to another
     * <code>TransfiniteSet</code>. Two different instances of <code>TransfiniteSet</code>
     * are equal if they return the same boolean value for the operation
     * {@link #contains(DirectPosition) contains} for every tested {@linkplain DirectPosition
     * direct position} within the valid range of the coordinate reference system associated
     * to the object.
     *
     * <blockquote><font size=2>
     * <strong>NOTE:</strong> Since an infinite set of direct positions cannot be tested,
     * the internal implementation of equal must test for equivalence between two, possibly
     * quite different, representations. This test may be limited to the resolution of the
     * coordinate system or the accuracy of the data. Implementations may define a tolerance
     * that returns <code>true</code> if the two <code>TransfiniteSet</code> have the same
     * dimension and each direct position in this <code>TransfiniteSet</code> is within a
     * tolerance distance of a direct position in the passed <code>TransfiniteSet</code> and
     * vice versa.
     * </font></blockquote>
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
