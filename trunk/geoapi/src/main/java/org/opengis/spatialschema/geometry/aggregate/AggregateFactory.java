/*$************************************************************************************************
 **
 ** $Id: Aggregate.java 831 2006-05-01 00:27:23Z Desruisseaux $
 **
 ** $URL: https://geoapi.svn.sourceforge.net/svnroot/geoapi/trunk/geoapi/src/main/java/org/opengis/spatialschema/geometry/aggregate/Aggregate.java $
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
// This class was created by Sanjay Jena and Prof. Jackson Roehrig in order to complete
// missing parts of the GeoAPI and submit the results to GeoAPI afterwards as proposal.

package org.opengis.spatialschema.geometry.aggregate;

// J2SE direct dependencies
import java.util.Set;

// OpenGIS direct dependencies
import org.opengis.spatialschema.geometry.primitive.Point;
import org.opengis.spatialschema.geometry.primitive.Primitive;
import org.opengis.spatialschema.geometry.primitive.OrientableCurve;
import org.opengis.spatialschema.geometry.primitive.OrientableSurface;


/**
 * A factory of {@linkplain Aggregate aggregate} geometric objects.
 * All aggregates created through this interface will use the
 * {@linkplain #getCoordinateReferenceSystem factory's coordinate reference system}.
 * Creating aggregates in a different CRS may requires a different instance of
 * {@code AggregateFactory}.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-101.pdf">Abstract specification 5</A>
 * @author Sanjay Jena
 * @author Prof. Dr. Jackson Roehrig
 * @since GeoAPI 2.1
 *
 * @todo Need to check if ISO 19107 defines constructors for aggregates.
 */
public interface AggregateFactory {
    /**
     * Creates a {@linkplain MultiPrimitive multi primitive} by a set of {@linkplain Primitive primitives}.
     * The created {@code MultiPrimitive} will hold only references to the given primitive,
     * e.g. changes in the primitive instances will have consequences in the
     * geometric behaviour of the {@code MultiPrimitive} and vice versa.
     * 
     * @param points A set of primitives.
     * @return the resulting multi primitive.
     */
    public MultiPrimitive createMultiPrimitive(Set<Primitive> primitives);

    /**
     * Creates a {@linkplain MultiPoint multi point} by a set of {@linkplain Point points}.
     * The created {@code MultiPoint} will hold only references to the given points,
     * e.g. changes in the point instances will have consequences in the
     * geometric behaviour of the {@code MultiPoint} and vice versa.
     * 
     * @param points A set of points.
     * @return the resulting multi point.
     */
    public MultiPoint createMultiPoint(Set<Point> points);

    /**
     * Creates a {@linkplain MultiCurve multi curve} by a set of {@linkplain Curve curves}.
     * The created {@code MultiCurve} will hold only references to the given curves,
     * e.g. changes in the curve instances will have consequences in the
     * geometric behaviour of the {@code MultiCurve} and vice versa.
     * 
     * @param curves A set of curves.
     * @return the resulting multi curve.
     */
    public MultiCurve createMultiCurve(Set<OrientableCurve> curves);

    /**
     * Creates a {@linkplain MultiSurface multi surface} by a set of {@linkplain Surface surfaces}.
     * The created {@code MultiSurface} will hold only references to the given surfaces,
     * e.g. changes in the surface instances will have consequences in the
     * geometric behaviour of the {@code MultiSurface} and vice versa.
     * 
     * @param surfaces A set of surfaces.
     * @return the resulting multi surface.
     */
    public MultiSurface createMultiSurface(Set<OrientableSurface> surfaces);
}
