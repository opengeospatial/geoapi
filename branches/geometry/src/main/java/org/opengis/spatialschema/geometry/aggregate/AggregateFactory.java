// This class was created by Sanjay Jena and Prof. Jackson Roehrig in order to complete missing parts of the GeoAPI and submit the results to GeoAPI afterwards as proposal.

package org.opengis.spatialschema.geometry.aggregate;

import java.util.Set;

import org.opengis.spatialschema.geometry.primitive.OrientableCurve;
import org.opengis.spatialschema.geometry.primitive.OrientableSurface;
import org.opengis.spatialschema.geometry.primitive.Point;
import org.opengis.spatialschema.geometry.primitive.Primitive;

/**
 * A factory of {@linkplain Aggregate aggregate} geometric objects.
 * All aggregates created through this interface will use the
 * {@linkplain #getCoordinateReferenceSystem factory's coordinate reference system}.
 * Creating complexes in a different CRS may requires a different instance of
 * {@code AggregateFactory}.
 *
 * @version <A HREF="http://www.opengis.org/docs/01-101.pdf">Abstract specification 5</A>
 * @author Sanjay Jena and Prof. Dr. Jackson Roehrig
 */
public interface AggregateFactory {
	
	/**
	 * Creates a {@linkplain MultiPrimitive} by a set of {@linkplain Primitive}s.
	 * The created MultiPrimitive will hold only references to the given primitive,
	 * e.g. changes in the primitive instances will have consequences in the
	 * geometric behaviour of the MultiPrimitive and vice versa.
	 * 
	 * @param points A set of primitives
	 * @return the resulting MultiPrimitive
	 */
	public MultiPrimitive createMultiPrimitive(Set<Primitive> primitives);
	
	/**
	 * Creates a {@linkplain MultiPoint} by a set of {@linkplain Point}s.
	 * The created MultiPoint will hold only references to the given points,
	 * e.g. changes in the point instances will have consequences in the
	 * geometric behaviour of the MultiPoint and vice versa.
	 * 
	 * @param points A set of points
	 * @return the resulting MultiPoint
	 */
	public MultiPoint createMultiPoint(Set<Point> points);

	/**
	 * Creates a {@linkplain MultiCurve} by a set of {@linkplain Curve}s.
	 * The created MultiCurve will hold only references to the given curves,
	 * e.g. changes in the curve instances will have consequences in the
	 * geometric behaviour of the MultiCurve and vice versa.
	 * 
	 * @param curves A set of curves
	 * @return the resulting MultiCurve
	 */
	public MultiCurve createMultiCurve(Set<OrientableCurve> curves);

	/**
	 * Creates a {@linkplain MultiSurface} by a set of {@linkplain Surface}s.
	 * The created MultiSurface will hold only references to the given surfaces,
	 * e.g. changes in the surface instances will have consequences in the
	 * geometric behaviour of the MultiSurface and vice versa.
	 * 
	 * @param surfaces A set of surfaces
	 * @return the resulting MultiSurface
	 */
	public MultiSurface createMultiSurface(Set<OrientableSurface> surfaces);

}
