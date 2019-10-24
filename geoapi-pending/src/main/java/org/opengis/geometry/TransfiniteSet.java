/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2019 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.geometry;

import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;


/**
 * A possibly infinite set; restricted only to values. For example, the integers and the real
 * numbers are transfinite sets. This is actually the usual definition of set in mathematics,
 * but programming languages restrict the term set to mean finite set.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 */
@UML(identifier="TransfiniteSet", specification=ISO_19107)
public interface TransfiniteSet {
    /**
     * Returns {@code true} if this {@code TransfiniteSet} contains another
     * {@code TransfiniteSet}. If the passed {@code TransfiniteSet} is a
     * {@linkplain org.opengis.geometry.primitive.Point point}, then this operation is the
     * equivalent of a set-element test for the {@linkplain DirectPosition direct position}
     * of that point within this {@code TransfiniteSet}.
     *
     * <div class="note"><b>Note:</b>
     * {@code contains} is strictly a set theoretic containment,
     * and has no dimensionality constraint. In a {@linkplain org.opengis.geometry.complex.Complex
     * complex}, no {@linkplain org.opengis.geometry.primitive.Primitive primitive} will contain
     * another unless a dimension is skipped.
     * </div>
     *
     * @param  pointSet The set to be checked for containment in this set.
     * @return {@code true} if this set contains all of the elements of the specified set.
     */
    boolean contains(TransfiniteSet pointSet);

    /**
     * Returns {@code true} if this {@code TransfiniteSet} contains a
     * single point given by a coordinate.
     *
     * @param  point The point to be checked for containment in this set.
     * @return {@code true} if this set contains the specified point.
     */
    boolean contains(DirectPosition point);

    /**
     * Returns {@code true} if this {@code TransfiniteSet} intersects another
     * {@code TransfiniteSet}. Withing a {@linkplain org.opengis.geometry.complex.Complex complex},
     * the {@linkplain org.opengis.geometry.primitive.Primitive primitives} do not intersect one another.
     * In general, topologically structured data uses shared geometric objects to
     * capture intersection information.
     *
     * <div class="note"><b>Note:</b>
     * this intersect is strictly a set theoretic common containment of
     * {@linkplain DirectPosition direct positions}.
     * Two {@linkplain org.opengis.geometry.primitive.Curve curves} do not intersect if they share a common
     * end point because {@linkplain org.opengis.geometry.primitive.Primitive primitives} are considered to be
     * open (do not contain their boundary).
     * If two {@linkplain org.opengis.geometry.complex.CompositeCurve composite curves} share a common end point,
     * then they intersect because {@linkplain org.opengis.geometry.complex.Complex complexes} are considered to
     * be closed (contain their boundary).
     * </div>
     *
     * @param  pointSet The set to be checked for intersection with this set.
     * @return {@code true} if this set intersects some of the elements of the specified set.
     */
    boolean intersects(TransfiniteSet pointSet);

    /**
     * Returns {@code true} if this {@code TransfiniteSet} is equal to another
     * {@code TransfiniteSet}. Two different instances of {@code TransfiniteSet}
     * are equal if they return the same boolean value for the operation
     * {@link #contains(DirectPosition) contains} for every tested {@linkplain DirectPosition
     * direct position} within the valid range of the coordinate reference system associated
     * to the object.
     *
     * <div class="note"><b>Note:</b>
     * since an infinite set of direct positions cannot be tested,
     * the internal implementation of equal must test for equivalence between two, possibly
     * quite different, representations. This test may be limited to the resolution of the
     * coordinate system or the accuracy of the data. Implementations may define a tolerance
     * that returns {@code true} if the two {@code TransfiniteSet} have the same
     * dimension and each direct position in this {@code TransfiniteSet} is within a
     * tolerance distance of a direct position in the passed {@code TransfiniteSet} and
     * vice versa.
     * </div>
     *
     * @param pointSet The set to test for equality.
     * @return {@code true} if the two set are equals.
     */
    boolean equals(TransfiniteSet pointSet);

    /**
     * Returns the set theoretic union of this {@code TransfiniteSet} and the passed
     * {@code TransfiniteSet}.
     *
     * @param pointSet The second set.
     * @return the union of both sets.
     */
    TransfiniteSet union(TransfiniteSet pointSet);

    /**
     * Returns the set theoretic intersection of this {@code TransfiniteSet} and the passed
     * {@code TransfiniteSet}.
     *
     * @param pointSet The second set.
     * @return the intersection of both sets.
     */
    TransfiniteSet intersection(TransfiniteSet pointSet);

    /**
     * Returns the set theoretic difference of this {@code TransfiniteSet} and the passed
     * {@code TransfiniteSet}.
     *
     * @param pointSet The second set.
     * @return the difference between both sets.
     */
    TransfiniteSet difference(TransfiniteSet pointSet);

    /**
     * Returns the set theoretic symmetric difference of this {@code TransfiniteSet} and the
     * passed {@code TransfiniteSet}.
     *
     * @param pointSet The second set.
     * @return the symmetric difference between both sets.
     */
    TransfiniteSet symmetricDifference(TransfiniteSet pointSet);
}
