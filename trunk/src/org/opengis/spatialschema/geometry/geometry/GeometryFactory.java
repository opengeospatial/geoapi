/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.spatialschema.geometry.geometry;

// J2SE direct dependencies
import java.util.List;

// OpenGIS direct dependencies
import org.opengis.spatialschema.geometry.DirectPosition;
import org.opengis.spatialschema.geometry.MismatchedDimensionException;
import org.opengis.spatialschema.geometry.MismatchedReferenceSystemException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * A factory of {@linkplain org.opengis.spatialschema.geometry.Geometry geometries}.
 * All geometries created through this interface will use the
 * {@linkplain #getCoordinateReferenceSystem factory's coordinate reference system}.
 * Creating geometries in a different CRS may requires a different instance of
 * <code>GeometryFactory</code>.
 *
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface GeometryFactory {
    /**
     * Returns the coordinate reference system in use for all
     * {@linkplain org.opengis.spatialschema.geometry.Geometry geometries}
     * to be created through this interface.
     */
    CoordinateReferenceSystem getCoordinateReferenceSystem();

    /**
     * Create a direct position at the specified location specified by coordinates.
     */
    DirectPosition createDirectPosition(double[] coordinates);

    /**
     * Takes two positions and creates the appropriate line segment joining them.
     *
     * @param startPoint The {@linkplain LineSegment#getStartPoint start point}.
     * @param   endPoint The {@linkplain LineSegment#getEndPoint end point}.
     *
     * @throws MismatchedReferenceSystemException If geometric objects given in argument don't
     *         use compatible {@linkplain CoordinateReferenceSystem coordinate reference system}.
     * @throws MismatchedDimensionException If geometric objects given in argument don't have
     *         the expected dimension.
     */
/// @UML (identifier="GM_LineSegment(GM_Position[2])", obligation=MANDATORY)
    LineSegment createLineSegment(Position startPoint, Position endPoint)
            throws MismatchedReferenceSystemException, MismatchedDimensionException;

    /**
     * Takes two or more positions and creates the appropriate line string joining them.
     *
     * @throws MismatchedReferenceSystemException If geometric objects given in argument don't
     *         use compatible {@linkplain CoordinateReferenceSystem coordinate reference system}.
     * @throws MismatchedDimensionException If geometric objects given in argument don't have
     *         the expected dimension.
     */
/// @UML (identifier="GM_LineString(GM_Position[2..n])", obligation=MANDATORY)
    LineString createLineString(List/*<Position>*/ points)
            throws MismatchedReferenceSystemException, MismatchedDimensionException;

    /**
     * Takes two positions and creates the appropriate geodesic joining them.
     *
     * @param startPoint The {@linkplain Geodesic#getStartPoint start point}.
     * @param   endPoint The {@linkplain Geodesic#getEndPoint end point}.
     *
     * @throws MismatchedReferenceSystemException If geometric objects given in argument don't
     *         use compatible {@linkplain CoordinateReferenceSystem coordinate reference system}.
     * @throws MismatchedDimensionException If geometric objects given in argument don't have
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
     * @throws MismatchedReferenceSystemException If geometric objects given in argument don't
     *         use compatible {@linkplain CoordinateReferenceSystem coordinate reference system}.
     * @throws MismatchedDimensionException If geometric objects given in argument don't have
     *         the expected dimension.
     */
/// @UML (identifier="GM_GeodesicString(GM_Position[2..n])", obligation=MANDATORY)
    GeodesicString createGeodesicString(List/*<Position>*/ points)
            throws MismatchedReferenceSystemException, MismatchedDimensionException;

    /**
     * Takes three positions and constructs the corresponding arc.
     *
     * @param startPoint The {@linkplain Arc#getStartPoint start point}.
     * @param   midPoint Some point on the arc neither at the start or end.
     * @param   endPoint The {@linkplain Arc#getEndPoint end point}.
     *
     * @throws MismatchedReferenceSystemException If geometric objects given in argument don't
     *         use compatible {@linkplain CoordinateReferenceSystem coordinate reference system}.
     * @throws MismatchedDimensionException If geometric objects given in argument don't have
     *         the expected dimension.
     */
/// @UML (identifier="GM_Arc(GM_Position[3])", obligation=MANDATORY)
    Arc createArc(Position startPoint, Position midPoint, Position endPoint)
            throws MismatchedReferenceSystemException, MismatchedDimensionException;

    /**
     * Takes two positions and the offset of the midpoint of the arc from the midpoint of
     * the chord, given by a distance and direction, and constructs the corresponding arc.
     * The midpoint of the resulting arc is given by:
     *
     * <blockquote><pre>midPoint = ((startPoint + endPoint)/2.0) + bulge&times;normal</pre></blockquote>
     *
     * In 2D coordinate reference systems, the bulge can be given a sign and the normal can be
     * assumed to be the perpendicular to the line segment between the start and end point of
     * the arc (the chord of the arc), pointing left. For example if the two points are
     * <var>P</var><sub>0</sub> = (<var>x</var><sub>0</sub>,&nbsp;<var>y</var><sub>0</sub>) and
     * <var>P</var><sub>1</sub> = (<var>x</var><sub>1</sub>,&nbsp;<var>y</var><sub>1</sub>), and
     * the bulge is <var>b</var>, then the vector in the direction of <var>P</var><sub>1</sub>
     * from <var>P</var><sub>0</sub> is:
     *
     * <blockquote>
     * <b><var>u</var></b> = (<var>u</var><sub>0</sub>,&nbsp;<var>u</var><sub>1</sub>) = 
     * (<var>x</var><sub>1</sub>-<var>x</var><sub>0</sub>,
     *  <var>y</var><sub>1</sub>-<var>y</var><sub>0</sub>) /
     * {@link Math#sqrt sqrt}((<var>x</var><sub>1</sub>-<var>x</var><sub>0</sub>)<sup>2</sup> +
     *                        (<var>y</var><sub>1</sub>-<var>y</var><sub>0</sub>)<sup>2</sup>)
     * </blockquote>
     *
     * To complete a right-handed local coordinate system {<b>u</b>,<b>v</b>}, the two vectors
     * must have a vector dot product of zero and a vector cross product of 1. By inspection,
     * the leftward normal to complete the pair is:
     *
     * <blockquote>
     * <b><var>v</var></b> = (<var>v</var><sub>0</sub>,&nbsp;<var>v</var><sub>1</sub>) = 
     *                      (-<var>u</var><sub>1</sub>,&nbsp;<var>u</var><sub>0</sub>)
     * </blockquote>
     *
     * The midpoint of the arc, which is the midpoint of the chord offset by the bulge, becomes:
     *
     * <blockquote>
     * <var>m</var> = (<var>P</var><sub>0</sub> + <var>P</var><sub>1</sub>)/2
     *              + <var>b</var>&times;<b><var>v</var></b>
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
     * @throws MismatchedReferenceSystemException If geometric objects given in argument don't
     *         use compatible {@linkplain CoordinateReferenceSystem coordinate reference system}.
     * @throws MismatchedDimensionException If geometric objects given in argument don't have
     *         the expected dimension.
     */
/// @UML (identifier="GM_Arc(GM_Position[2],Real,Vector)", obligation=MANDATORY)
    Arc createArc(Position startPoint, Position endPoint, double bulge, double[] normal)
            throws MismatchedReferenceSystemException, MismatchedDimensionException;

    /**
     * Takes a sequence of {@linkplain Position positions} and constructs a sequence of
     * 3-point arcs jointing them. By the nature of an arc string, the sequence must have
     * an odd number of positions.
     *
     * @throws MismatchedReferenceSystemException If geometric objects given in argument don't
     *         use compatible {@linkplain CoordinateReferenceSystem coordinate reference system}.
     * @throws MismatchedDimensionException If geometric objects given in argument don't have
     *         the expected dimension.
     */
/// @UML (identifier="GM_ArcString(GM_Position[3, 5, 7...])", obligation=MANDATORY)
    ArcString createArcString(List/*<Position>*/ points)
            throws MismatchedReferenceSystemException, MismatchedDimensionException;

    /**
     * Equivalents to the {@linkplain #createArc(Position,Position,double,double[]) second
     * constructor of arc}, except the bulge representation is maintained. The midpoint of
     * the resulting arc is given by:
     *
     * <blockquote><pre>midPoint = ((startPoint + endPoint)/2.0) + bulge&times;normal</pre></blockquote>
     *
     * @param startPoint The {@linkplain ArcByBulge#getStartPoint start point}.
     * @param   endPoint The {@linkplain ArcByBulge#getEndPoint end point}.
     * @param      bulge The distance of the midpoint of the arc from the midpoint of the chord.
     * @param     normal A direction normal to the chord.
     *
     * @throws MismatchedReferenceSystemException If geometric objects given in argument don't
     *         use compatible {@linkplain CoordinateReferenceSystem coordinate reference system}.
     * @throws MismatchedDimensionException If geometric objects given in argument don't have
     *         the expected dimension.
     */
/// @UML (identifier="GM_ArcByBulge(GM_Position[2],Real,Vector)", obligation=MANDATORY)
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
     *                must be equals to the <code>bulge</code> array length plus 1.
     * @param  bulges The distances of the midpoint of the arc from the midpoint of the chord.
     * @param normals The directions normal to the chord. This list size must be the same than
     *                the <code>bulge</code> array length.
     *
     * @throws MismatchedReferenceSystemException If geometric objects given in argument don't
     *         use compatible {@linkplain CoordinateReferenceSystem coordinate reference system}.
     * @throws MismatchedDimensionException If geometric objects given in argument don't have
     *         the expected dimension.
     */
/// @UML (identifier="GM_ArcStringByBulge(GM_Position[2..n],Real[1..n],Vector[1..n])", obligation=MANDATORY)
    ArcStringByBulge createArcStringByBulge(List/*<Position>*/ points, double[] bulges,
                                            List/*<double[]>*/ normals)
            throws MismatchedReferenceSystemException, MismatchedDimensionException;
}
