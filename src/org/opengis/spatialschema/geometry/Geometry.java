/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.spatialschema.geometry;

import java.util.Set;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.IncompatibleOperationException;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;
import org.opengis.spatialschema.geometry.complex.Complex;


/**
 * Root class of the geometric object taxonomy. <code>Geometry</code> supports interfaces common
 * to all geographically referenced geometric objects. <code>Geometry</code> instances are sets
 * of direct positions in a particular coordinate reference system. A <code>Geometry</code> can
 * be regarded as an infinite set of points that satisfies the set operation interfaces for a set
 * of direct positions, {@link TransfiniteSet TransfiniteSet&lt;DirectPosition&gt;}.
 *
 * @UML type GM_Object
 * @rename Renamed as <code>Geometry</code> in order to avoid ambiguity with
 *         {@link java.lang.Object}.
 *
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface Geometry extends TransfiniteSet {
    /**
     * Returns the coordinate reference system used in {@linkplain DirectPosition direct position}
     * coordinates. If <code>null</code>, then this <code>Geometry</code> uses the coordinate reference
     * system from another <code>Geometry</code> in which it is contained.
     *
     * The most common example where the coordinate reference system is <code>null</code> is the elements
     * and subcomplexes of a maximal {@linkplain Complex complex}. The {@linkplain Complex complex} can
     * carry the {@linkplain CoordinateReferenceSystem coordinate reference system} for all
     * {@linkplain org.opengis.spatialschema.geometry.primitive.Primitive primitive} elements
     * and for all {@link Complex} subcomplexes.
     * <br><br>
     * This association is only navigable from <code>Geometry</code> to {@linkplain CoordinateReferenceSystem
     * coordinate reference system}. This means that the coordinate reference system objects in a data set do
     * not keep a list of <code>Geometry</code>s that use them.
     *
     * @return The coordinate reference system used in {@linkplain DirectPosition direct position}
     *         coordinates.
     * @UML association CRS
     *
     * @see #getCoordinateDimension
     */
    public CoordinateReferenceSystem getCoordinateReferenceSystem();

    /**
     * Returns a region in the coordinate reference system that contains this <code>Geometry</code>.
     * The default shall be to return an instance of an appropriate <code>Geometry</code> subclass
     * that represents the same spatial set returned from {@link #getEnvelope}. The most common
     * use of <code>mbRegion</code> will be to support indexing methods that use extents other
     * than minimum bounding rectangles (MBR or envelopes). This does not restrict the returned
     * <code>Geometry</code> from being a non-vector geometric representation, although those
     * types are not defined within this specification.
     *
     * @return The minimum bounding region.
     * @UML operation mbRegion
     *
     * @see #getEnvelope
     * @see #getBoundary
     */
    public Geometry getMbRegion();

    /**
     * Returns a point value that is guaranteed to be on this <code>Geometry</code>. The default
     * logic may be to use the {@linkplain DirectPosition direct position} of the point returned by
     * {@link #getCentroid} if that point is on the object. Another use of representative point may
     * be for the placement of labels in systems based on graphic presentation.
     *
     * @return The representative point.
     * @UML operation representativePoint
     *
     * @see #getCentroid
     */
    public DirectPosition getRepresentativePoint();

    /**
     * Returns a finite set of <code>Geometry</code>s containing all of the direct positions on the
     * boundary of this <code>Geometry</code>. These object collections shall have further internal
     * structure where appropriate. The finite set of <code>Geometry</code>s returned shall be in
     * the same coordinate reference system as this <code>Geometry</code>. If the <code>Geometry</code>
     * is in a {@linkplain Complex complex}, then the boundary <code>Geometry</code>s returned shall be
     * in the same {@linkplain Complex complex}. If the <code>Geometry</code> is not in any
     * {@linkplain Complex complex}, then the boundary <code>Geometry</code>s returned may have been
     * constructed in response to the operation. The elements of a boundary shall be smaller in
     * dimension than the original element.
     *
     * @return The sets of positions on the boundary.
     * @UML operation boundary
     *
     * @see #getMbRegion
     * @see #getClosure
     * @see #getBuffer
     * @see #getDistance
     */
    public Boundary getBoundary();

    /**
     * Returns a finite set of <code>Geometry</code>s containing all of the points on the boundary of
     * this <code>Geometry</code> and this object (the union of the object and its boundary). These
     * object collections shall have further internal structure where appropriate. The finite set
     * of <code>Geometry</code>s returned shall be in the same coordinate reference system as this
     * <code>Geometry</code>. If the <code>Geometry</code> is in a {@linkplain Complex complex}, then the boundary
     * <code>Geometry</code>s returned shall be in the same {@linkplain Complex complex}. If the
     * <code>Geometry</code> is not in any {@linkplain Complex complex}, then the boundary
     * <code>Geometry</code>s returned may have been constructed in response to the operation.
     *
     * @return The sets of points on the union of this object and its boundary.
     * @UML operation closure
     *
     * @see #getBoundary
     */
    public Complex getClosure();

    /**
     * Returns <code>true</code> if this <code>Geometry</code> has no interior point of
     * self-intersection or selftangency. In mathematical formalisms, this means that
     * every point in the interior of the object must have a metric neighborhood whose
     * intersection with the object is isomorphic to an <var>n</var>-sphere, where <var>n</var>
     * is the dimension of this <code>Geometry</code>.
     * <br><br>
     * Since most coordinate geometries are represented, either directly or indirectly by functions
     * from regions in Euclidean space of their topological dimension, the easiest test for
     * simplicity to require that a function exist that is one-to-one and bicontinuous
     * (continuous in both directions). Such a function is a topological isomorphism. This test
     * does not work for "closed" objects (that is, objects for which {@link #isCycle} returns
     * <code>true</code>).
     *
     * @return <code>true</code> if this object has no interior point of self-intersection or
     *         selftangency.
     * @UML operation isSimple
     *
     * @see #isCycle
     */
    public boolean isSimple();

    /**
     * Returns <code>true</code> if this <code>Geometry</code> has an empty boundary after topological
     * simplification (removal of overlaps between components in non-structured aggregates, such as
     * subclasses of {@link org.opengis.spatialschema.geometry.aggregate.Aggregate}). This condition is alternatively
     * referred to as being "closed" as in a "closed curve." This creates some confusion since there
     * are two distinct and incompatible definitions for the word "closed". The use of the word cycle
     * is rarer (generally restricted to the field of algebraic topology), but leads to less confusion.
     * Essentially, an object is a cycle if it is isomorphic to a geometric object that is the
     * boundary of a region in some Euclidean space. Thus a curve is a cycle if it is isomorphic
     * to a circle (has the same start and end point). A surface is a cycle if it isomorphic to the
     * surface of a sphere, or some torus. A solid, with finite size, in a space of dimension 3 is
     * never a cycle.
     *
     * @return <code>true</code> if this <code>Geometry</code> has an empty boundary after
     *         topological simplification.
     * @UML operation isCycle
     *
     * @see #isSimple
     */
    public boolean isCycle();

    /**
     * Returns the distance between this <code>Geometry</code> and another <code>Geometry</code>.
     * This distance is defined to be the greatest lower bound of the set of distances between
     * all pairs of points that include one each from each of the two <code>Geometry</code>s. A
     * "distance" value shall be a positive number associated to a distance unit such as meter
     * or standard foot. If necessary, the second geometric object shall be transformed into
     * the same coordinate reference system as the first before the distance is calculated.
     * <br><br>
     * If the geometric objects overlap, or touch, then their distance apart shall be zero.
     * Some current implementations use a "negative" distance for such cases, but the approach
     * is neither consistent between implementations, nor theoretically viable.
     * <br><br>
     * <strong>NOTE:</strong> The role of the reference system in distance calculations is
     * important. Generally, there are at least three types of distances that may be defined
     * between points (and therefore between geometric objects): map distance, geodesic distance,
     * and terrain distance.
     * <ul>
     *   <li>Map distance is the distance between the points as defined by their positions in a
     *       coordinate projection (such as on a map when scale is taken into account). Map distance
     *       is usually accurate for small areas where scale functions have well-behaved derivatives.</li>
     *   <li>Geodesic distance is the length of the shortest curve between those two points along the
     *       surface of the earth model being used by the coordinate reference system. Geodesic
     *       distance behaves well for wide areas of coverage, and takes the earth's curvature
     *       into account. It is especially handy for air and sea navigation, although care should
     *       be taken to distinguish between rhumb line (curves of constant bearing) and geodesic
     *       curve distance.</li>
     *   <li>Terrain distance takes into account the local vertical displacements (hypsography).
     *       Terrain distance can be based either on a geodesic distance or a map distance.</li>
     * </ul>
     *
     * @param  geometry The other object.
     * @return The distance between the two objects.
     * @unitof Distance
     * @UML operation distance
     *
     * @see #getBoundary
     * @see #getBuffer
     * @see org.opengis.referencing.cs.CoordinateSystem#getAxis
     */
    public double getDistance(Geometry geometry);

    /**
     * Returns the inherent dimension of this <code>Geometry</code>, which shall be less than or
     * equal to the {@linkplain #getCoordinateDimension coordinate dimension}. The dimension of
     * a collection of geometric objects shall be the largest dimension of any of its pieces.
     * Points are 0-dimensional, curves are 1-dimensional, surfaces are 2-dimensional, and solids
     * are 3-dimensional. Locally, the dimension of a geometric object at a point is the dimension
     * of a local neighborhood of the point - that is the dimension of any coordinate neighborhood
     * of the point. Dimension is unambiguously defined only for {@linkplain DirectPosition direct
     * positions} interior to this <code>Geometry</code>. If the passed {@linkplain DirectPosition
     * direct position} is <code>null</code>, then the operation shall return the largest possible
     * dimension for any {@linkplain DirectPosition direct position} in this <code>Geometry</code>.
     *
     * @param point The point where to evaluate the dimension, or <code>null</code>.
     * @return The inherent dimension.
     * @UML operation dimension
     *
     * @see #getCoordinateDimension
     */
    public int getDimension(DirectPosition point);

    /**
     * Returns the dimension of the coordinates that define this <code>Geometry</code>, which must
     * be the same as the coordinate dimension of the coordinate reference system for this
     * <code>Geometry</code>.
     *
     * @return The coordinate dimension.
     * @UML operation coordinateDimension
     *
     * @see #getDimension
     * @see #getCoordinateReferenceSystem
     */
    public int getCoordinateDimension();

    /**
     * Returns the set of maximal complexes within which this <code>Geometry</code> is contained.
     * As a set of primitives, a {@linkplain Complex complex} may be contained as a set in another
     * larger {@linkplain Complex complex}, referred to as a "super complex" of the original.
     * A {@linkplain Complex complex} is maximal if there is no such larger super complex.
     *
     * @return The set of maximal complexes within which this <code>Geometry</code> is contained.
     * @UML operation maximalComplex
     */
    public Set getMaximalComplex();

    /**
     * Returns a new <code>Geometry</code> that is the coordinate transformation of this
     * <code>Geometry</code> into the passed coordinate reference system within the accuracy
     * of the transformation.
     *
     * @param  newCRS The new coordinate reference system.
     * @return The transformed <code>Geometry</code>.
     * @throws TransformException if the transformation failed.
     * @UML operation transform
     */
    public Geometry transform(CoordinateReferenceSystem newCRS) throws TransformException;

    /**
     * Returns a new <code>Geometry</code> that is the coordinate transformation of this
     * <code>Geometry</code> into the passed coordinate reference system, using the
     * specified transform. It is the user responsability to ensure that the supplied
     * transform is appropriate for this geometry.
     *
     * @param  newCRS The new coordinate reference system.
     * @param  transform The transform from the existing coordinate reference system
     *         to the new coordinate reference system.
     * @throws TransformException if the transformation failed.
     * @return The transformed <code>Geometry</code>.
     */
    public Geometry transform(CoordinateReferenceSystem newCRS, MathTransform transform) throws TransformException;

    /**
     * Returns the minimum bounding box for this <code>Geometry</code>. This shall be the
     * coordinate region spanning the minimum and maximum value for each ordinate taken on by
     * {@linkplain DirectPosition direct positions} in this <code>Geometry</code>. The simplest
     * representation for an envelope consists of two {@linkplain DirectPosition direct positions},
     * the first one containing all the minimums for each ordinate, and second one containing all
     * the maximums. However, there are cases for which these two positions would be outside the
     * domain of validity of the object's coordinate reference system.
     *
     * @return The envelope.
     * @UML operation envelope
     *
     * @see #getMbRegion
     */
    public Envelope getEnvelope();

    /**
     * Returns the mathematical centroid for this <code>Geometry</code>. The result is not guaranteed
     * to be on the object. For heterogeneous collections of primitives, the centroid only takes
     * into account those of the largest dimension. For example, when calculating the centroid of
     * surfaces, an average is taken weighted by area. Since curves have no area they do not
     * contribute to the average.
     *
     * @return The centroid.
     * @UML operation centroid
     *
     * @see #getRepresentativePoint
     */
    public DirectPosition getCentroid();

    /**
     * Returns a <code>Geometry</code> that represents the convex hull of this <code>Geometry</code>.
     * Convexity requires the use of "lines" or "curves of shortest length" and the use of different
     * coordinate systems may result in different versions of the convex hull of an object. Each
     * implementation shall decide on an appropriate solution to this ambiguity. For two reasonable
     * coordinate systems, a convex hull of an object in one will be very closely approximated by
     * the transformed image of the convex hull of the same object in the other.
     *
     * @return The convex hull.
     * @UML operation convexHull
     */
    public Geometry getConvexHull();

    /**
     * Returns a <code>Geometry</code> containing all points whose distance from this
     * <code>Geometry</code> is less than or equal to the distance passed as a parameter.
     * The <code>Geometry</code> returned is in the same reference system as this original
     * <code>Geometry</code>. The dimension of the returned <code>Geometry</code> is normally
     * the same as the coordinate dimension - a collection of
     * {@linkplain org.opengis.spatialschema.geometry.primitive.Surface surfaces} in 2D space and a collection of
     * {@linkplain org.opengis.spatialschema.geometry.primitive.Solid solids} in 3D space, but this may be application
     * defined.
     *
     * @param distance The distance.
     * @return A geometry containing all points whose distance from this <code>Geometry</code>
     *         is less than or equal to the specified distance.
     * @unitof Distance (for the argument)
     * @UML operation buffer
     *
     * @see #getBoundary
     * @see #getDistance
     * @see org.opengis.referencing.cs.CoordinateSystem#getAxis
     */
    public Geometry getBuffer(double distance);

    /**
     * Releases any resources that may have been allocated for this object.
     * It is an error to reference a geometry after its <code>dispose</code>
     * method has been called.
     */
    public void dispose();
}
