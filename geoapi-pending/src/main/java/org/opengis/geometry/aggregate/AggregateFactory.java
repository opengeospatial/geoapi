/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2006-2019 Open Geospatial Consortium, Inc.
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
// This class was created by Sanjay Jena and Prof. Jackson Roehrig in order to complete
// missing parts of the GeoAPI and submit the results to GeoAPI afterwards as proposal.

package org.opengis.geometry.aggregate;

import java.util.Set;
import org.opengis.geometry.primitive.Curve;
import org.opengis.geometry.primitive.Point;
import org.opengis.geometry.primitive.Primitive;
import org.opengis.geometry.primitive.OrientableCurve;
import org.opengis.geometry.primitive.OrientableSurface;
import org.opengis.geometry.primitive.Surface;


/**
 * A factory of {@linkplain Aggregate aggregate} geometric objects.
 * All aggregates created through this interface will use the
 * factory's coordinate reference system.
 * Creating aggregates in a different CRS may requires a different instance of
 * {@code AggregateFactory}.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
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
     * @param primitives A set of primitives.
     * @return the resulting multi primitive.
     */
    MultiPrimitive createMultiPrimitive(Set<Primitive> primitives);

    /**
     * Creates a {@linkplain MultiPoint multi point} by a set of {@linkplain Point points}.
     * The created {@code MultiPoint} will hold only references to the given points,
     * e.g. changes in the point instances will have consequences in the
     * geometric behaviour of the {@code MultiPoint} and vice versa.
     *
     * @param points A set of points.
     * @return the resulting multi point.
     */
    MultiPoint createMultiPoint(Set<Point> points);

    /**
     * Creates a {@linkplain MultiCurve multi curve} by a set of {@linkplain Curve curves}.
     * The created {@code MultiCurve} will hold only references to the given curves,
     * e.g. changes in the curve instances will have consequences in the
     * geometric behaviour of the {@code MultiCurve} and vice versa.
     *
     * @param curves A set of curves.
     * @return the resulting multi curve.
     */
    MultiCurve createMultiCurve(Set<OrientableCurve> curves);

    /**
     * Creates a {@linkplain MultiSurface multi-surface} by a set of {@linkplain Surface surfaces}.
     * The created {@code MultiSurface} will hold only references to the given surfaces,
     * e.g. changes in the surface instances will have consequences in the
     * geometric behaviour of the {@code MultiSurface} and vice versa.
     *
     * @param surfaces A set of surfaces.
     * @return the resulting multi surface.
     */
    MultiSurface createMultiSurface(Set<OrientableSurface> surfaces);
}
