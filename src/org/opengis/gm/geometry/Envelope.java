/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.gm.geometry;

// OpenGIS direct dependencies
import org.opengis.gm.primitive.Primitive;


/**
 * GM_Envelope is often referred to as a minimum bounding box or rectangle. Regardless
 * of dimension, a GM_Envelope can be represented without ambiguity as two direct positions
 * (coordinate points). To encode a GM_Envelope, it is sufficient to encode these two
 * points. This is consistent with all of the data types in this standard, their state
 * is represented by their publicly accessible attributes. 
 *  
 * @author GeoAPI
 * @version 1.0
 */
public interface Envelope {
    /**
     * A coordinate consisting of all maximal values of the ordinates of all points within
     * the GM_Envelope. The "upperCorner" of a GM_Envelope is a coordinate position consisting
     * of all the maximal ordinates for each dimension for all points within the GM_Envelope.
     * GM_Envelope::upperCorner : DirectPosition 
     */
//    public DirectPosition upperCorner;

    /**
     * A coordinate consisting of all minimal values of the ordinates of all points within
     * the GM_Envelope. The "lowerCorner" of a GM_Envelope is a coordinate position consisting
     * of all the minimal ordinates for each dimension for all points within the GM_Envelope.
     * GM_Envelope::lowerCorner : DirectPosition 
     */
//    public DirectPosition lowerCorner;

    /**
     * Returns this envelope as a primitive. <code>Envelope</code> will often be used
     * in query operations, and therefore must have a cast operation that returns a
     * {@link org.opengis.gm.Geometry}. The actual return of the operation depends upon
     * the dimension of the coordinate reference system and the extent of the envelope.
     * In a 2D system, the primitive returned will be a {@link org.opengis.gm.primitive.Surface}
     * (if the envelope does not collapse to a point or line). In 3D systems, the usual return is
     * a {@link org.opengis.gm.primitive.Solid}.
     * <br><br>
     * <strong>EXAMPLE:</strong> In the case where the <code>Envelope</code> is totally contained
     * in the domain of validity of its {@link org.opengis.sc.CRS} (coordinate reference system)
     * object, its associated {@link Primitive} is the convex hull of the various permutations of
     * the coordinates in the corners. For example, suppose that a particular envelope in 2D is
     * defined as:
     *
     * <blockquote><pre>
     * lowerCorner = (x1, y1)
     * upperCorner = (x2, y2)</pre></blockquote>
     *
     * (we ignore the CRS below, assuming that it is a global variable), then we can take the
     * various permutations of the ordinate values to create a list of polygon corners:
     *
     * <blockquote><pre>
     * {@link org.opengis.gm.aggregate.MultiPoint} = { (x1, y1), (x1, y2), (x2, y1), (x2, y2) }</pre></blockquote>
     *
     * If we then apply the {@link org.opengis.gm.Geometry#getConvexHull convex hull} function
     * to the multi_point, we get a polygon as a {@link org.opengis.gm.primitive.Surface}.
     * The extent of a polygon in 2D is totally defined by its
     * {@link org.opengis.gm.Geometry#getBoundary boundary} (internal surface
     * patches are planar and do not need interior control points) which gives
     * us a data type to represent {@link org.opengis.gm.primitive.Surface} in 2D:
     *
     * <blockquote><pre>
     * {@link org.opengis.gm.primitive.Ring} = {
     *     {@link org.opengis.gm.geometry.LineString} = { (x1, y1), (x1, y2), (x2, y2), (x2, y1), (x1, y1)}
     * }</pre></blockquote>
     *
     * So that the {@link org.opengis.gm.primitive.SurfaceBoundary} record contains the above-cited
     * exterior ring, and an empty set of interior rings (convex sets have no "interior" holes).
     *
     * @return This envelope as a primitive.
     * @UML constructor Primitive(Envelope)
     */
    public Primitive toPrimitive();
}
