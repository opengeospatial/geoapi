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
package org.opengis.geometry;

import java.awt.geom.Rectangle2D;                           // For javadoc
import org.opengis.referencing.cs.AxisDirection;            // For javadoc
import org.opengis.metadata.extent.GeographicBoundingBox;   // For javadoc
import org.opengis.referencing.crs.GeographicCRS;           // For javadoc
import org.opengis.referencing.crs.CRSAuthorityFactory;     // For javadoc
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.TransformException;


/**
 * Represents a two-dimensional {@linkplain Envelope envelope}.
 * This interface combines the ideas of {@link GeographicBoundingBox} with
 * those of {@link Envelope}. It provides convenience methods to assist
 * in accessing the formal properties of this object. Those methods
 * (for example {@link #getMinX()}) match common usage in existing libraries
 * like {@linkplain Rectangle2D Java2D}.
 * <p>
 * This object contains no additional information beyond that provided
 * by {@link Envelope}.
 *
 * @author Jody Garnett (Refractions Research)
 * @author Martin Desruisseaux (Geomatys)
 * @since GeoAPI 2.1
 *
 * @deprecated See <a href="https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-231">GEO-231</a>.
 */
@Deprecated
public interface BoundingBox extends Envelope {
    /**
     * Sets this bounding box to be the same as the specified box.
     *
     * @param bounds The new bounds.
     */
    void setBounds(BoundingBox bounds);

    /**
     * Provides the minimum ordinate along the first axis.
     * This is equivalent to <code>{@linkplain #getMinimum getMinimum}(0)</code>.
     * There is no guarantee that this axis is oriented toward
     * {@linkplain AxisDirection#EAST East}.
     *
     * @return the minimum ordinate along the first axis.
     */
    double getMinX();

    /**
     * Provides the maximum ordinate along the first axis.
     * This is equivalent to <code>{@linkplain #getMaximum getMaximum}(0)</code>.
     * There is no guarantee that this axis is oriented toward
     * {@linkplain AxisDirection#EAST East}.
     *
     * @return the maximum ordinate along the first axis.
     */
    double getMaxX();

    /**
     * Provides the minimum ordinate along the second axis.
     * This is equivalent to <code>{@linkplain #getMinimum getMinimum}(1)</code>.
     * There is no guarantee that this axis is oriented toward
     * {@linkplain AxisDirection#NORTH North}.
     *
     * @return the minimum ordinate along the second axis.
     */
    double getMinY();

    /**
     * Provides the maximum ordinate along the second axis.
     * This is equivalent to <code>{@linkplain #getMaximum getMaximum}(1)</code>.
     * There is no guarantee that this axis is oriented toward
     * {@linkplain AxisDirection#NORTH North}.
     *
     * @return the maximum ordinate along the second axis.
     */
    double getMaxY();

    /**
     * Provides the difference between {@linkplain #getMinX minimum} and
     * {@linkplain #getMaxX maximum} ordinate along the first axis.
     * This is equivalent to <code>{@linkplain #getSpan getSpan}(0)</code>.
     * There is no guarantee that this axis is oriented toward
     * {@linkplain AxisDirection#EAST East}.
     *
     * @return the span along the first axis.
     */
    double getWidth();

    /**
     * Provides the difference between {@linkplain #getMinX minimum} and
     * {@linkplain #getMaxX maximum} ordinate along the second axis.
     * This is equivalent to <code>{@linkplain #getSpan getSpan}(1)</code>.
     * There is no guarantee that this axis is oriented toward
     * {@linkplain AxisDirection#NORTH North}.
     *
     * @return the span along the second axis.
     */
    double getHeight();

    /**
     * Returns {@code true} if {@linkplain #getSpan spans} along all dimension are zero
     * or negative.
     *
     * @return {@code true} if this bounding box is empty.
     */
    boolean isEmpty();

    /**
     * Includes the provided bounding box, expanding as necessary.
     *
     * @param bounds The bounds to add to this geographic bounding box.
     */
    void include(BoundingBox bounds);

    /**
     * Includes the provided coordinates, expanding as necessary. Note that there is no
     * guarantee that the (<var>x</var>, <var>x</var>) values are oriented toward
     * ({@linkplain AxisDirection#EAST East}, {@linkplain AxisDirection#NORTH North}),
     * since it depends on the {@linkplain #getCoordinateReferenceSystem envelope CRS}.
     *
     * @param x The first ordinate value.
     * @param y The second ordinate value.
     */
    void include(double x, double y);

    /**
     * Returns {@code true} if the interior of this bounds intersects the interior
     * of the provided bounds.
     *
     * @param  bounds The bounds to test for intersection.
     * @return {@code true} if the two bounds intersect.
     */
    boolean intersects(BoundingBox bounds);

    /**
     * Returns {@code true} if the provided bounds are contained by this bounding box.
     *
     * @param  bounds The bounds to test for inclusion.
     * @return {@code true} if the given bounds is inside this bounds.
     */
    boolean contains(BoundingBox bounds);

    /**
     * Returns {@code true} if the provided location is contained by this bounding box.
     *
     * @param  location The direct position to test for inclusion.
     * @return {@code true} if the given position is inside this bounds.
     */
    boolean contains(DirectPosition location);

    /**
     * Returns {@code true} if the provided location is contained by this bounding box.
     * Note that there is no guarantee that the (<var>x</var>, <var>x</var>) values are
     * oriented toward ({@linkplain AxisDirection#EAST East}, {@linkplain AxisDirection#NORTH North}),
     * since it depends on the {@linkplain #getCoordinateReferenceSystem envelope CRS}.
     *
     * @param x The first ordinate value.
     * @param y The second ordinate value.
     * @return {@code true} if the given position is inside this bounds.
     */
    boolean contains(double x, double y);

    /**
     * Transforms this box to the specified CRS and returns a new bounding box for the
     * transformed shape. This method provides a convenient (while not always efficient)
     * way to get {@linkplain #getMinimum minimum} and {@linkplain #getMaximum maximum}
     * ordinate values toward some specific axis directions, typically
     * {@linkplain AxisDirection#EAST East} and {@linkplain AxisDirection#NORTH North}.
     * <p>
     * <b>Example:</b> if {@code box} is a bounding box using a {@linkplain GeographicCRS
     * geographic CRS} with WGS84 datum, then one can write:
     *
     * <blockquote><pre>
     * GeographicCRS targetCRS   = crsAuthorityFactory.{@linkplain CRSAuthorityFactory#createGeographicCRS createGeographicCRS}("EPSG:4326");
     * BoundingBox   targetBox   = box.toBounds(targetCRS);
     * double        minEasting  = targetBox.getMinY();
     * double        minNorthing = targetBox.getMinX();
     * </pre></blockquote>
     *
     * Be aware that {@code "EPSG:4326"} has (<var>latitude</var>, <var>longitude</var>)
     * axis order, thus the inversion of <var>X</var> and <var>Y</var> in the above code.
     *
     * <p>Sophisticated applications will typically provide more efficient way to perform
     * similar transformations in their context. For example a canvas may store
     * precomputed "objective to display" transforms.</p>
     *
     * @param  targetCRS The target CRS for the bounding box to be returned.
     * @return a new bounding box wich includes the shape of this box transformed
     *         to the specified target CRS.
     * @throws TransformException if no transformation path has been found from
     *         {@linkplain #getCoordinateReferenceSystem this box CRS} to the specified
     *         target CRS, or if the transformation failed for an other reason.
     */
    BoundingBox toBounds(CoordinateReferenceSystem targetCRS) throws TransformException;
}
