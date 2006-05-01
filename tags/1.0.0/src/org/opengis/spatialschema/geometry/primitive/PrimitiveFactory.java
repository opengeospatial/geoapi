/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.spatialschema.geometry.primitive;

// J2SE direct dependencies
import java.util.List;

// OpenGIS direct dependencies
import org.opengis.spatialschema.geometry.Envelope;
import org.opengis.spatialschema.geometry.DirectPosition;
import org.opengis.spatialschema.geometry.geometry.Position;
import org.opengis.referencing.crs.CoordinateReferenceSystem;


/**
 * A factory of {@linkplain Primitive primitive} geometric objects.
 * All primitives created through this interface will use the
 * {@linkplain #getCoordinateReferenceSystem factory's coordinate reference system}.
 * Creating primitives in a different CRS may requires a different instance of
 * <code>PrimitiveFactory</code>.
 *
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @revisit Should we extend {@link org.opengis.referencing.Factory}?
 */
public interface PrimitiveFactory {
    /**
     * Returns the coordinate reference system in use for all {@linkplain Primitive primitive}
     * geometric objects to be created through this interface.
     */
    public CoordinateReferenceSystem getCoordinateReferenceSystem();

    /**
     * Returns an envelope as a primitive. An {@linkplain Envelope envelope} will often be
     * used in query operations, and therefore must have a cast operation that returns a
     * {@linkplain org.opengis.spatialschema.geometry.Geometry geometry}. The actual return of the operation depends
     * upon the dimension of the {@linkplain org.opengis.referencing.crs.CoordinateReferenceSystem coordinate
     * reference system} and the extent of the {@linkplain Envelope envelope}. In a 2D system,
     * the primitive returned will be a {@linkplain Surface surface} (if the envelope does not
     * collapse to a point or line). In 3D systems, the usual return is a {@linkplain Solid solid}.
     * <br><br>
     * <strong>EXAMPLE:</strong> In the case where the {@linkplain Envelope envelope} is totally
     * contained in the domain of validity of its {@linkplain org.opengis.referencing.crs.CoordinateReferenceSystem
     * coordinate reference system} object, its associated {@linkplain Primitive primitive} is the convex
     * hull of the various permutations of the coordinates in the corners. For example, suppose
     * that a particular envelope in 2D is defined as:
     *
     * <blockquote><pre>
     * lowerCorner = (x1, y1)
     * upperCorner = (x2, y2)</pre></blockquote>
     *
     * (we ignore the CRS below, assuming that it is a global variable), then we can take the
     * various permutations of the ordinate values to create a list of polygon corners:
     *
     * <blockquote><pre>
     * {@link org.opengis.spatialschema.geometry.aggregate.MultiPoint} = { (x1, y1), (x1, y2), (x2, y1), (x2, y2) }</pre></blockquote>
     *
     * If we then apply the {@linkplain org.opengis.spatialschema.geometry.Geometry#getConvexHull convex hull}
     * function to the multi point, we get a polygon as a {@linkplain Surface surface}.
     * The extent of a polygon in 2D is totally defined by its
     * {@linkplain org.opengis.spatialschema.geometry.Geometry#getBoundary boundary} (internal surface
     * patches are planar and do not need interior control points) which gives
     * us a data type to represent {@linkplain Surface surface} in 2D:
     *
     * <blockquote><pre>
     * {@link org.opengis.spatialschema.geometry.primitive.Ring} = {
     *     {@link org.opengis.spatialschema.geometry.geometry.LineString} = { (x1, y1), (x1, y2), (x2, y2), (x2, y1), (x1, y1)}
     * }</pre></blockquote>
     *
     * So that the {@linkplain SurfaceBoundary surface boundary} record contains the above-cited
     * exterior ring, and an empty set of interior rings (convex sets have no "interior" holes).
     *
     * @UML constructor GM_Primitive(GM_Envelope)
     */
    public Primitive createPrimitive(Envelope envelope);

    /**
     * Create a direct position at the specified location specified by coordinates.
     */
    public DirectPosition createDirectPosition(double[] coordinates);

    /**
     * Creates a point at the specified location specified by coordinates.
     */
    public Point createPoint(double[] coordinates);

    /**
     * Creates a point at the specified position.
     *
     * @UML constructor GM_Point(GM_Position)
     */
    public Point createPoint(Position position);

    /**
     * Takes a list of {@linkplain CurveSegment curve segments} with the appropriate
     * end-to-start relationships and creates a {@linkplain Curve curve}.
     *
     * @UML constructor GM_Curve(GM_CurveSegment[1..n])
     */
    public Curve createCurve(List/*<CurveSegment>*/ segments);

    /**
     * Takes a list of {@linkplain SurfacePatch surface patches} with the appropriate
     * side-toside relationships and creates a {@linkplain Surface surface}.
     *
     * @UML constructor GM_Surface(GM_SurfacePatch[1..n])
     */
    public Surface createSurface(List/*<SurfacePatch>*/ surfaces);

    /**
     * Constructs a {@linkplain Surface surface} by indicating its boundary as a collection
     * of {@linkplain Curve curves} organized into the specified {@linkplain SurfaceBoundary
     * surface boundary}. This method is guaranteed to work always in 2D coordinate spaces,
     * In 3D coordinate spaces, this method shall require all of the defining boundary
     * {@linkplain Curve curve} instances to be coplanar (lie in a single plane) which will
     * define the surface interior.
     *
     * @UML constructor GM_Surface(GM_SurfaceBoundary)
     */
    public Surface createSurface(SurfaceBoundary boundary);

    /**
     * Constructs a {@linkplain Solid solid} by indicating its boundary as a collection of
     * {@linkplain Shell shells} organized into a {@linkplain SolidBoundary solid boundary}.
     * Since this specification is limited to 3-dimensional coordinate reference systems,
     * any solid is definable by its boundary.
     *
     * @UML constructor GM_Solid(GM_SolidBoundary)
     */
    public Solid createSolid(SolidBoundary boundary);
}
