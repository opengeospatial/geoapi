/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2003-2023 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.opengis.geometry;

import java.util.Set;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.TransformException;
import org.opengis.geometry.complex.Complex;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Root class of the geometric object taxonomy. {@code Geometry} supports interfaces common
 * to all geographically referenced geometric objects. {@code Geometry} instances are sets
 * of direct positions in a particular coordinate reference system. A {@code Geometry} can
 * be regarded as an infinite set of points that satisfies the set operation interfaces for a set
 * of direct positions, {@link TransfiniteSet TransfiniteSet&lt;DirectPosition&gt;}.
 *
 * @departure rename
 *   Renamed <code>GM_Object</code> as <code>Geometry</code> in order to avoid ambiguity with
 *   <code>java.lang.Object</code>.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 */
@UML(identifier="GM_Object", specification=ISO_19107)
public interface Geometry extends TransfiniteSet {
    /**
     * Returns the coordinate reference system used in {@linkplain DirectPosition direct position}
     * coordinates. If {@code null}, then this {@code Geometry} uses the coordinate reference
     * system from another {@code Geometry} in which it is contained.
     *
     * The most common example where the coordinate reference system is {@code null} is the elements
     * and subcomplexes of a maximal {@linkplain Complex complex}. The {@linkplain Complex complex} can
     * carry the {@linkplain CoordinateReferenceSystem coordinate reference system} for all
     * {@linkplain org.opengis.geometry.primitive.Primitive primitive} elements
     * and for all {@link Complex} subcomplexes.
     * <p>
     * This association is only navigable from {@code Geometry} to {@linkplain CoordinateReferenceSystem
     * coordinate reference system}. This means that the coordinate reference system objects in a data set do
     * not keep a list of {@code Geometry}s that use them.
     *
     * @return the coordinate reference system used in {@linkplain DirectPosition direct position}
     *         coordinates.
     *
     * @see #getCoordinateDimension
     */
    @UML(identifier="CRS", obligation=MANDATORY, specification=ISO_19107)
    CoordinateReferenceSystem getCoordinateReferenceSystem();

    /**
     * Returns a region in the coordinate reference system that contains this {@code Geometry}.
     * The default shall be to return an instance of an appropriate {@code Geometry} subclass
     * that represents the same spatial set returned from {@link #getEnvelope}. The most common
     * use of {@code mbRegion} will be to support indexing methods that use extents other
     * than minimum bounding rectangles (MBR or envelopes). This does not restrict the returned
     * {@code Geometry} from being a non-vector geometric representation, although those
     * types are not defined within this specification.
     *
     * @return the minimum bounding region.
     *
     * @see #getEnvelope
     * @see #getBoundary
     */
    @UML(identifier="mbRegion", obligation=MANDATORY, specification=ISO_19107)
    Geometry getMbRegion();

    /**
     * Returns a point value that is guaranteed to be on this {@code Geometry}. The default
     * logic may be to use the {@linkplain DirectPosition direct position} of the point returned by
     * {@link #getCentroid} if that point is on the object. Another use of representative point may
     * be for the placement of labels in systems based on graphic presentation.
     *
     * @return the representative point.
     *
     * @see #getCentroid
     */
    @UML(identifier="representativePoint", obligation=MANDATORY, specification=ISO_19107)
    DirectPosition getRepresentativePoint();

    /**
     * Returns a finite set of {@code Geometry}s containing all of the direct positions on the
     * boundary of this {@code Geometry}. These object collections shall have further internal
     * structure where appropriate. The finite set of {@code Geometry}s returned shall be in
     * the same coordinate reference system as this {@code Geometry}. If the {@code Geometry}
     * is in a {@linkplain Complex complex}, then the boundary {@code Geometry}s returned shall be
     * in the same {@linkplain Complex complex}. If the {@code Geometry} is not in any
     * {@linkplain Complex complex}, then the boundary {@code Geometry}s returned may have been
     * constructed in response to the operation. The elements of a boundary shall be smaller in
     * dimension than the original element.
     *
     * @return the sets of positions on the boundary.
     *
     * @see #getMbRegion
     * @see #getClosure
     * @see #getBuffer
     */
    @UML(identifier="boundary", obligation=MANDATORY, specification=ISO_19107)
    Boundary getBoundary();

    /**
     * Returns a finite set of {@code Geometry}s containing all of the points on the boundary of
     * this {@code Geometry} and this object (the union of the object and its boundary). These
     * object collections shall have further internal structure where appropriate. The finite set
     * of {@code Geometry}s returned shall be in the same coordinate reference system as this
     * {@code Geometry}. If the {@code Geometry} is in a {@linkplain Complex complex}, then the boundary
     * {@code Geometry}s returned shall be in the same {@linkplain Complex complex}. If the
     * {@code Geometry} is not in any {@linkplain Complex complex}, then the boundary
     * {@code Geometry}s returned may have been constructed in response to the operation.
     *
     * @return the sets of points on the union of this object and its boundary.
     *
     * @see #getBoundary
     */
    @UML(identifier="closure", obligation=MANDATORY, specification=ISO_19107)
    Complex getClosure();

    /**
     * Returns {@code true} if this {@code Geometry} has no interior point of
     * self-intersection or selftangency. In mathematical formalisms, this means that
     * every point in the interior of the object must have a metric neighborhood whose
     * intersection with the object is isomorphic to an <var>n</var>-sphere, where <var>n</var>
     * is the dimension of this {@code Geometry}.
     * <p>
     * Since most coordinate geometries are represented, either directly or indirectly by functions
     * from regions in Euclidean space of their topological dimension, the easiest test for
     * simplicity to require that a function exist that is one-to-one and bicontinuous
     * (continuous in both directions). Such a function is a topological isomorphism. This test
     * does not work for "closed" objects (that is, objects for which {@link #isCycle} returns
     * {@code true}).
     *
     * @return {@code true} if this object has no interior point of self-intersection or
     *         selftangency.
     *
     * @see #isCycle
     */
    @UML(identifier="isSimple", obligation=MANDATORY, specification=ISO_19107)
    boolean isSimple();

    /**
     * Returns {@code true} if this {@code Geometry} has an empty boundary after topological
     * simplification (removal of overlaps between components in non-structured aggregates, such as
     * subclasses of {@link org.opengis.geometry.aggregate.Aggregate}). This condition is alternatively
     * referred to as being "closed" as in a "closed curve." This creates some confusion since there
     * are two distinct and incompatible definitions for the word "closed". The use of the word cycle
     * is rarer (generally restricted to the field of algebraic topology), but leads to less confusion.
     * Essentially, an object is a cycle if it is isomorphic to a geometric object that is the
     * boundary of a region in some Euclidean space. Thus a curve is a cycle if it is isomorphic
     * to a circle (has the same start and end point). A surface is a cycle if it isomorphic to the
     * surface of a sphere, or some torus. A solid, with finite size, in a space of dimension 3 is
     * never a cycle.
     *
     * @return {@code true} if this {@code Geometry} has an empty boundary after
     *         topological simplification.
     *
     * @see #isSimple
     */
    @UML(identifier="isCycle", obligation=MANDATORY, specification=ISO_19107)
    boolean isCycle();

    /**
     * Returns the distance between this {@code Geometry} and another {@code Geometry}.
     * This distance is defined to be the greatest lower bound of the set of distances between
     * all pairs of points that include one each from each of the two {@code Geometry}s. A
     * "distance" value shall be a positive number associated to a distance unit such as meter
     * or standard foot. If necessary, the second geometric object shall be transformed into
     * the same coordinate reference system as the first before the distance is calculated.
     *
     * <p>If the geometric objects overlap, or touch, then their distance apart shall be zero.
     * Some current implementations use a "negative" distance for such cases, but the approach
     * is neither consistent between implementations, nor theoretically viable.</p>
     *
     * <div class="note"><b>Note:</b>
     * the role of the reference system in distance calculations is
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
     * </div>
     *
     * @param  geometry The other object.
     * @return the distance between the two objects.
     * @unitof Distance
     * @since GeoAPI 2.1
     *
     * @see #getBoundary
     * @see #getBuffer
     * @see org.opengis.referencing.cs.CoordinateSystem#getAxis
     */
    @UML(identifier="distance", obligation=MANDATORY, specification=ISO_19107)
    double distance(Geometry geometry);

    /**
     * Returns the inherent dimension of this {@code Geometry}, which shall be less than or
     * equal to the {@linkplain #getCoordinateDimension coordinate dimension}. The dimension of
     * a collection of geometric objects shall be the largest dimension of any of its pieces.
     * Points are 0-dimensional, curves are 1-dimensional, surfaces are 2-dimensional, and solids
     * are 3-dimensional. Locally, the dimension of a geometric object at a point is the dimension
     * of a local neighborhood of the point - that is the dimension of any coordinate neighborhood
     * of the point. Dimension is unambiguously defined only for {@linkplain DirectPosition direct
     * positions} interior to this {@code Geometry}. If the passed {@linkplain DirectPosition
     * direct position} is {@code null}, then the operation shall return the largest possible
     * dimension for any {@linkplain DirectPosition direct position} in this {@code Geometry}.
     *
     * @param point The point where to evaluate the dimension, or {@code null}.
     * @return the inherent dimension.
     *
     * @see #getCoordinateDimension
     */
    @UML(identifier="dimension", obligation=MANDATORY, specification=ISO_19107)
    int getDimension(DirectPosition point);

    /**
     * Returns the dimension of the coordinates that define this {@code Geometry}, which must
     * be the same as the coordinate dimension of the coordinate reference system for this
     * {@code Geometry}.
     *
     * @return the coordinate dimension.
     *
     * @see #getDimension
     * @see #getCoordinateReferenceSystem
     */
    @UML(identifier="coordinateDimension", obligation=MANDATORY, specification=ISO_19107)
    int getCoordinateDimension();

    /**
     * Returns the set of maximal complexes within which this {@code Geometry} is contained.
     * As a set of primitives, a {@linkplain Complex complex} may be contained as a set in another
     * larger {@linkplain Complex complex}, referred to as a "super complex" of the original.
     * A {@linkplain Complex complex} is maximal if there is no such larger super complex.
     *
     * @return the set of maximal complexes within which this {@code Geometry} is contained.
     */
    @UML(identifier="maximalComplex", obligation=MANDATORY, specification=ISO_19107)
    Set<? extends Complex> getMaximalComplex();

    /**
     * Returns a new {@code Geometry} that is the coordinate transformation of this
     * {@code Geometry} into the passed coordinate reference system within the accuracy
     * of the transformation.
     *
     * @param  newCRS The new coordinate reference system.
     * @return the transformed {@code Geometry}.
     * @throws TransformException if the transformation failed.
     */
    @UML(identifier="transform", obligation=MANDATORY, specification=ISO_19107)
    Geometry transform(CoordinateReferenceSystem newCRS) throws TransformException;

    /**
     * Returns the minimum bounding box for this {@code Geometry}.
     * This shall be the region spanning the minimum and maximum value for each coordinate taken
     * on by {@linkplain DirectPosition direct positions} in this {@code Geometry}. The simplest
     * representation for an envelope consists of two {@linkplain DirectPosition direct positions},
     * the first one containing all the minimums for each coordinate, and second one containing all
     * the maximums. However, there are cases for which these two positions would be outside the
     * domain of validity of the object's coordinate reference system.
     *
     * @return the envelope.
     *
     * @see #getMbRegion
     */
    @UML(identifier="envelope", obligation=MANDATORY, specification=ISO_19107)
    Envelope getEnvelope();

    /**
     * Returns the mathematical centroid for this {@code Geometry}. The result is not guaranteed
     * to be on the object. For heterogeneous collections of primitives, the centroid only takes
     * into account those of the largest dimension. For example, when calculating the centroid of
     * surfaces, an average is taken weighted by area. Since curves have no area they do not
     * contribute to the average.
     *
     * @return the centroid.
     *
     * @see #getRepresentativePoint
     */
    @UML(identifier="centroid", obligation=MANDATORY, specification=ISO_19107)
    DirectPosition getCentroid();

    /**
     * Returns a {@code Geometry} that represents the convex hull of this {@code Geometry}.
     * Convexity requires the use of "lines" or "curves of shortest length" and the use of different
     * coordinate systems may result in different versions of the convex hull of an object. Each
     * implementation shall decide on an appropriate solution to this ambiguity. For two reasonable
     * coordinate systems, a convex hull of an object in one will be very closely approximated by
     * the transformed image of the convex hull of the same object in the other.
     *
     * @return the convex hull.
     */
    @UML(identifier="convexHull", obligation=MANDATORY, specification=ISO_19107)
    Geometry getConvexHull();

    /**
     * Returns a {@code Geometry} containing all points whose distance from this
     * {@code Geometry} is less than or equal to the distance passed as a parameter.
     * The {@code Geometry} returned is in the same reference system as this original
     * {@code Geometry}. The dimension of the returned {@code Geometry} is normally
     * the same as the coordinate dimension - a collection of
     * {@linkplain org.opengis.geometry.primitive.Surface surfaces} in 2D space and a collection of
     * {@linkplain org.opengis.geometry.primitive.Solid solids} in 3D space, but this may be application
     * defined.
     *
     * @param distance The distance.
     * @return a geometry containing all points whose distance from this {@code Geometry}
     *         is less than or equal to the specified distance.
     * @unitof Distance (for the argument)
     *
     * @see #getBoundary
     * @see org.opengis.referencing.cs.CoordinateSystem#getAxis
     */
    @UML(identifier="buffer", obligation=MANDATORY, specification=ISO_19107)
    Geometry getBuffer(double distance);
}
