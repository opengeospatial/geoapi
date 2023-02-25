/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
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
package org.opengis.geometry.coordinate;

import java.util.List;
import java.util.Set;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.Envelope;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.geometry.MismatchedReferenceSystemException;
import org.opengis.geometry.primitive.Surface;
import org.opengis.geometry.primitive.SurfaceBoundary;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A factory of {@linkplain org.opengis.geometry.Geometry geometries}.
 * All geometries created through this interface will use the
 * {@linkplain #getCoordinateReferenceSystem factory's coordinate reference system}.
 * Creating geometries in a different CRS may requires a different instance of
 * {@code GeometryFactory}.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 */
public interface GeometryFactory {
    /**
     * Returns the coordinate reference system in use for all
     * {@linkplain org.opengis.geometry.Geometry geometries}
     * to be created through this interface.
     */
    CoordinateReferenceSystem getCoordinateReferenceSystem();

    /**
     * Creates a new Envelope with the given corners.
     *
     * @param lowerCorner A coordinate position consisting of all the maximal ordinates for each
     *                    dimension for all points within the envelope.
     * @param upperCorner A coordinate position consisting of all the minimal ordinates for each
     *                    dimension for all points within the envelope.
     *
     * @throws MismatchedReferenceSystemException if the coordinate positions don't use
     *         compatible {@linkplain CoordinateReferenceSystem coordinate reference system}.
     * @throws MismatchedDimensionException if the coordinate position don't have compatible dimension.
     */
    Envelope createEnvelope(DirectPosition lowerCorner, DirectPosition upperCorner)
            throws MismatchedReferenceSystemException, MismatchedDimensionException;

    /**
     * Takes two positions and creates the appropriate line segment joining them.
     *
     * @param startPoint The {@linkplain LineSegment#getStartPoint start point}.
     * @param   endPoint The {@linkplain LineSegment#getEndPoint end point}.
     *
     * @throws MismatchedReferenceSystemException if geometric objects given in argument don't
     *         use compatible {@linkplain CoordinateReferenceSystem coordinate reference system}.
     * @throws MismatchedDimensionException if geometric objects given in argument don't have
     *         the expected dimension.
     */
    @UML(identifier="GM_LineSegment(GM_Position[2])", obligation=MANDATORY, specification=ISO_19107)
    LineSegment createLineSegment(Position startPoint, Position endPoint)
            throws MismatchedReferenceSystemException, MismatchedDimensionException;

    /**
     * Takes two or more positions and creates the appropriate line string joining them.
     *
     * @throws MismatchedReferenceSystemException if geometric objects given in argument don't
     *         use compatible {@linkplain CoordinateReferenceSystem coordinate reference system}.
     * @throws MismatchedDimensionException if geometric objects given in argument don't have
     *         the expected dimension.
     */
    @UML(identifier="GM_LineString(GM_Position[2..n])", obligation=MANDATORY, specification=ISO_19107)
    LineString createLineString(List<Position> points)
            throws MismatchedReferenceSystemException, MismatchedDimensionException;

    /**
     * Takes two positions and creates the appropriate geodesic joining them.
     *
     * @param startPoint The {@linkplain Geodesic#getStartPoint start point}.
     * @param   endPoint The {@linkplain Geodesic#getEndPoint end point}.
     *
     * @throws MismatchedReferenceSystemException if geometric objects given in argument don't
     *         use compatible {@linkplain CoordinateReferenceSystem coordinate reference system}.
     * @throws MismatchedDimensionException if geometric objects given in argument don't have
     *         the expected dimension.
     */
    Geodesic createGeodesic(Position startPoint, Position endPoint)
            throws MismatchedReferenceSystemException, MismatchedDimensionException;

    /**
     * Takes two or more positions, interpolates using a geodesic defined from
     * the geoid (or {@linkplain org.opengis.referencing.datum.Ellipsoid ellipsoid}) of the
     * {@linkplain org.opengis.referencing.crs.CoordinateReferenceSystem coordinate reference system}
     * being used, and creates the appropriate geodesic string joining them.
     *
     * @throws MismatchedReferenceSystemException if geometric objects given in argument don't
     *         use compatible {@linkplain CoordinateReferenceSystem coordinate reference system}.
     * @throws MismatchedDimensionException if geometric objects given in argument don't have
     *         the expected dimension.
     */
    @UML(identifier="GM_GeodesicString(GM_Position[2..n])", obligation=MANDATORY, specification=ISO_19107)
    GeodesicString createGeodesicString(List<Position> points)
            throws MismatchedReferenceSystemException, MismatchedDimensionException;

    /**
     * Takes three positions and constructs the corresponding arc.
     *
     * @param startPoint The {@linkplain Arc#getStartPoint start point}.
     * @param   midPoint Some point on the arc neither at the start or end.
     * @param   endPoint The {@linkplain Arc#getEndPoint end point}.
     *
     * @throws MismatchedReferenceSystemException if geometric objects given in argument don't
     *         use compatible {@linkplain CoordinateReferenceSystem coordinate reference system}.
     * @throws MismatchedDimensionException if geometric objects given in argument don't have
     *         the expected dimension.
     */
    @UML(identifier="GM_Arc(GM_Position[3])", obligation=MANDATORY, specification=ISO_19107)
    Arc createArc(Position startPoint, Position midPoint, Position endPoint)
            throws MismatchedReferenceSystemException, MismatchedDimensionException;

    /**
     * Takes two positions and the offset of the midpoint of the arc from the midpoint of
     * the chord, given by a distance and direction, and constructs the corresponding arc.
     * The midpoint of the resulting arc is given by:
     *
     * <blockquote><pre>midPoint = ((startPoint + endPoint)/2.0) + bulge×normal</pre></blockquote>
     *
     * In 2D coordinate reference systems, the bulge can be given a sign and the normal can be
     * assumed to be the perpendicular to the line segment between the start and end point of
     * the arc (the chord of the arc), pointing left. For example if the two points are
     * <var>P₀</var> = (<var>x₀</var>,&nbsp;<var>y₀</var>) and
     * <var>P₁</var> = (<var>x₁</var>,&nbsp;<var>y₁</var>), and
     * the bulge is <var>b</var>, then the vector in the direction of <var>P₁</var>
     * from <var>P₀</var> is:
     *
     * <blockquote>
     * <b><var>u</var></b> = (<var>u₀</var>,&nbsp;<var>u₁</var>) =
     * (<var>x₁</var>-<var>x₀</var>,
     *  <var>y₁</var>-<var>y₀</var>) /
     * {@link Math#sqrt sqrt}((<var>x₁</var>-<var>x₀</var>)² +
     *                        (<var>y₁</var>-<var>y₀</var>)²)
     * </blockquote>
     *
     * To complete a right-handed local coordinate system {<b>u</b>,<b>v</b>}, the two vectors
     * must have a vector dot product of zero and a vector cross product of 1. By inspection,
     * the leftward normal to complete the pair is:
     *
     * <blockquote>
     * <b><var>v</var></b> = (<var>v₀</var>,&nbsp;<var>v₁</var>) =
     *                      (-<var>u₁</var>,&nbsp;<var>u₀</var>)
     * </blockquote>
     *
     * The midpoint of the arc, which is the midpoint of the chord offset by the bulge, becomes:
     *
     * <blockquote>
     * <var>m</var> = (<var>P₀</var> + <var>P₁</var>)/2
     *              + <var>b</var>×<b><var>v</var></b>
     * </blockquote>
     *
     * This is leftward if <var>b</var>&nbsp;&gt;&nbsp;0
     *    and rightward if <var>b</var>&nbsp;&lt;&nbsp;0.
     *
     * @param startPoint The {@linkplain Arc#getStartPoint start point}.
     * @param   endPoint The {@linkplain Arc#getEndPoint end point}.
     * @param      bulge The distance of the midpoint of the arc from the midpoint of the chord.
     * @param     normal A direction normal to the chord.
     *
     * @throws MismatchedReferenceSystemException if geometric objects given in argument don't
     *         use compatible {@linkplain CoordinateReferenceSystem coordinate reference system}.
     * @throws MismatchedDimensionException if geometric objects given in argument don't have
     *         the expected dimension.
     */
    @UML(identifier="GM_Arc(GM_Position[2],Real,Vector)", obligation=MANDATORY, specification=ISO_19107)
    Arc createArc(Position startPoint, Position endPoint, double bulge, double[] normal)
            throws MismatchedReferenceSystemException, MismatchedDimensionException;

    /**
     * Takes a sequence of {@linkplain Position positions} and constructs a sequence of
     * 3-point arcs jointing them. By the nature of an arc string, the sequence must have
     * an odd number of positions.
     *
     * @throws MismatchedReferenceSystemException if geometric objects given in argument don't
     *         use compatible {@linkplain CoordinateReferenceSystem coordinate reference system}.
     * @throws MismatchedDimensionException if geometric objects given in argument don't have
     *         the expected dimension.
     */
    @UML(identifier="GM_ArcString(GM_Position[3, 5, 7...])", obligation=MANDATORY, specification=ISO_19107)
    ArcString createArcString(List<Position> points)
            throws MismatchedReferenceSystemException, MismatchedDimensionException;

    /**
     * Equivalents to the {@linkplain #createArc(Position,Position,double,double[]) second
     * constructor of arc}, except the bulge representation is maintained. The midpoint of
     * the resulting arc is given by:
     *
     * <blockquote><pre>midPoint = ((startPoint + endPoint)/2.0) + bulge×normal</pre></blockquote>
     *
     * @param startPoint The {@linkplain ArcByBulge#getStartPoint start point}.
     * @param   endPoint The {@linkplain ArcByBulge#getEndPoint end point}.
     * @param      bulge The distance of the midpoint of the arc from the midpoint of the chord.
     * @param     normal A direction normal to the chord.
     *
     * @throws MismatchedReferenceSystemException if geometric objects given in argument don't
     *         use compatible {@linkplain CoordinateReferenceSystem coordinate reference system}.
     * @throws MismatchedDimensionException if geometric objects given in argument don't have
     *         the expected dimension.
     */
    @UML(identifier="GM_ArcByBulge(GM_Position[2],Real,Vector)", obligation=MANDATORY, specification=ISO_19107)
    ArcByBulge createArcByBulge(Position startPoint, Position endPoint, double bulge, double[] normal)
            throws MismatchedReferenceSystemException, MismatchedDimensionException;

    /**
     * Equivalent to the {@linkplain #createArc(Position,Position,double,double[]) second
     * constructor of arc}, except the bulge representation is maintained internal to the
     * object. The midpoints of the resulting arc is given by:
     *
     * <blockquote><code>
     * midPoint[<var>n</var>] = ((points[<var>n</var>] + points[<var>n</var>+1])/2.0) + (bulge * normal)
     * </code></blockquote>
     *
     * @param  points The points to use as {@linkplain Arc#getStartPoint start} and
     *                {@linkplain Arc#getEndPoint end points} for each arc. This list size
     *                shall be equal to the {@code bulge} array length plus 1.
     * @param  bulges The distances of the midpoint of the arc from the midpoint of the chord.
     * @param normals The directions normal to the chord. This list size shall be the same than
     *                the {@code bulge} array length.
     *
     * @throws MismatchedReferenceSystemException if geometric objects given in argument don't
     *         use compatible {@linkplain CoordinateReferenceSystem coordinate reference system}.
     * @throws MismatchedDimensionException if geometric objects given in argument don't have
     *         the expected dimension.
     */
    @UML(identifier="GM_ArcStringByBulge(GM_Position[2..n],Real[1..n],Vector[1..n])", obligation=MANDATORY, specification=ISO_19107)
    ArcStringByBulge createArcStringByBulge(List<Position> points, double[] bulges,
                                            List<double[]> normals)
            throws MismatchedReferenceSystemException, MismatchedDimensionException;

    /**
     * Constructs a B-spline curve. If the {@code knotSpec} is {@code null}, then the
     * {@link KnotType} is uniform and the knots are evenly spaced, and except for the
     * first and last have multiplicity = 1. At the ends the knots are of multiplicity =
     * {@code degree}+1. If the {@code knotType} is uniform they need not be specified.
     *
     * <div class="note"><b>Note:</b>
     * if the B-spline curve is uniform and degree = 1, the B-spline
     * is equivalent to a polyline ({@link LineString}). If the {@code knotType} is
     * {@linkplain KnotType#PIECEWISE_BEZIER piecewise Bezier}, then the knots are
     * defaulted so that they are evenly spaced, and except for the first and last
     * have multiplicity equal to degree. At the ends the knots are of multiplicity =
     * {@code degree}+1.
     * </div>
     *
     * @param degree The algebraic degree of the basis functions.
     * @param points An array of points that are used in the interpolation in this spline curve.
     * @param knots  The sequence of distinct knots used to define the spline basis functions.
     * @param knotSpec The type of knot distribution used in defining this spline.
     *
     * @throws MismatchedReferenceSystemException if geometric objects given in argument don't
     *         use compatible {@linkplain CoordinateReferenceSystem coordinate reference system}.
     * @throws MismatchedDimensionException if geometric objects given in argument don't have
     *         the expected dimension.
     */
    @UML(identifier="GM_BSplineCurve(Integer,GM_PointArray,Sequence<GM_Knot>,GM_KnotType)", obligation=MANDATORY, specification=ISO_19107)
    BSplineCurve createBSplineCurve(int degree, PointArray points, List<Knot> knots, KnotType knotSpec)
            throws MismatchedReferenceSystemException, MismatchedDimensionException;

    /**
     * Constructs a B-spline surface. If the {@code knotSpec} is not present, then the
     * {@code knotType} is uniform and the knots are evenly spaced, and, except for the
     * first and last, have multiplicity = 1. At the ends the knots are of multiplicity
     * = degree+1. If the {@code knotType} is uniform they need not be specified.
     *
     * @param points Arrays of points that are used in the interpolation in this spline surface.
     * @param degree The algebraic degree of the basis functions for the first and second parameter,
     *               as an array of length 1 or 2. If only one value is given, then the two degrees
     *               are equal.
     * @param knots    The sequence of knots as an array of length 2, or {@code null} if unspecified.
     * @param knotSpec The type of knot distribution used in defining this spline, or
     *                 {@code null} if unspecified.
     *
     * @throws MismatchedReferenceSystemException if geometric objects given in argument don't
     *         use compatible {@linkplain CoordinateReferenceSystem coordinate reference system}.
     * @throws MismatchedDimensionException if geometric objects given in argument don't have
     *         the expected dimension.
     */
    @UML(identifier="GM_BSplineSurface(Sequence<GM_PointArray>,Integer,Sequence<GM_Knot>,GM_KnotType)", obligation=MANDATORY, specification=ISO_19107)
    BSplineSurface createBSplineSurface(List<PointArray> points, int[] degree, List<Knot>[] knots, KnotType knotSpec)
            throws MismatchedReferenceSystemException, MismatchedDimensionException;

    /**
     * Creates a polygon directly from a set of boundary curves (organized into a
     * surface boundary) which shall be defined using coplanar {@linkplain Position positions}
     * as control points.
     *
     * <div class="note"><b>Note:</b>
     * the meaning of exterior in the surface boundary is consistent
     * with the plane of the constructed planar polygon.
     * </div>
     *
     * @param boundary The surface boundary.
     *
     * @throws MismatchedReferenceSystemException if geometric objects given in argument don't
     *         use compatible {@linkplain CoordinateReferenceSystem coordinate reference system}.
     * @throws MismatchedDimensionException if geometric objects given in argument don't have
     *         the expected dimension.
     */
    @UML(identifier="GM_Polygon(GM_SurfaceBondary)", obligation=MANDATORY, specification=ISO_19107)
    Polygon createPolygon(SurfaceBoundary boundary)
            throws MismatchedReferenceSystemException, MismatchedDimensionException;

    /**
     * Creates a polygon lying on a spanning surface. There is no restriction of the types of
     * interpolation used by the composite curves used in the {@linkplain SurfaceBoundary
     * surface boundary}, but they must all be lie on the
     * {@linkplain Polygon#getSpanningSurface spanning surface} for the process to succeed.
     *
     * <div class="note"><b>Note:</b>
     * it is important that the boundary components be oriented properly
     * for this to work. It is often the case that in bounded manifolds, such as the sphere,
     * there is an ambiguity unless the orientation is properly used.
     * </div>
     *
     * @param boundary The surface boundary.
     * @param spanSurface The spanning surface.
     *
     * @throws MismatchedReferenceSystemException if geometric objects given in argument don't
     *         use compatible {@linkplain CoordinateReferenceSystem coordinate reference system}.
     * @throws MismatchedDimensionException if geometric objects given in argument don't have
     *         the expected dimension.
     */
    @UML(identifier="GM_Polygon(GM_SurfaceBondary,GM_Surface)", obligation=MANDATORY, specification=ISO_19107)
    Polygon createPolygon(SurfaceBoundary boundary, Surface spanSurface)
            throws MismatchedReferenceSystemException, MismatchedDimensionException;

    /**
     * Constructs a restricted Delaunay network from triangle corners (posts),
     * breaklines, stoplines, and maximum length of a triangle side.
     *
     * @param  post The corners of the triangles in the TIN.
     * @param  stopLines lines where the local continuity or regularity of the surface is questionable.
     * @param  breakLines lines of a critical nature to the shape of the surface.
     * @param  maxLength Maximal length for retention.
     *
     * @throws MismatchedReferenceSystemException if geometric objects given in argument don't
     *         use compatible {@linkplain CoordinateReferenceSystem coordinate reference system}.
     * @throws MismatchedDimensionException if geometric objects given in argument don't have
     *         the expected dimension.
     */
    @UML(identifier="GM_Tin(Set<GM_Position>,Set<GM_LineString>,Set<GM_LineString>,Number)", obligation=MANDATORY, specification=ISO_19107)
    Tin createTin(Set<Position> post, Set<LineString> stopLines,
                  Set<LineString> breakLines, double maxLength)
            throws MismatchedReferenceSystemException, MismatchedDimensionException;

    /**
     * Constructs polyhedral surface from the facet polygons.
     *
     * @param tiles The facet polygons. Must contains at least one polygon.
     *
     * @throws MismatchedReferenceSystemException if geometric objects given in argument don't
     *         use compatible {@linkplain CoordinateReferenceSystem coordinate reference system}.
     * @throws MismatchedDimensionException if geometric objects given in argument don't have
     *         the expected dimension.
     */
    @UML(identifier="GM_PolyhedralSurface(GM_Polygon)", obligation=MANDATORY, specification=ISO_19107)
    PolyhedralSurface createPolyhedralSurface(List<Polygon> tiles)
            throws MismatchedReferenceSystemException, MismatchedDimensionException;
}
