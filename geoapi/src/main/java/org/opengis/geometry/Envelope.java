/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2019 Open Geospatial Consortium, Inc.
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

import java.awt.geom.Rectangle2D;                                   // Used in @see javadoc tags

import org.opengis.referencing.cs.RangeMeaning;                     // For javadoc
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A minimum bounding box or rectangle. Regardless of dimension, an {@code Envelope} can
 * be represented without ambiguity as two direct positions (coordinate points). To encode an
 * {@code Envelope}, it is sufficient to encode these two points. This is consistent with
 * all of the data types in this specification, their state is represented by their publicly
 * accessible attributes.
 *
 * <h2>Spanning the anti-meridian of a geographic CRS</h2>
 * The <cite>Web Coverage Service</cite> (WCS) 1.1 specification uses an extended interpretation
 * of the bounding box definition. In a WCS 1.1 data structure, the {@linkplain #getUpperCorner()
 * upper corner} defines the edges region in the directions of <em>increasing</em> coordinate
 * values in the envelope CRS, while the {@linkplain #getLowerCorner() lower corner} defines the
 * edges region in the direction of <em>decreasing</em> coordinate values. They are usually the
 * algebraic maximum and minimum coordinates respectively, but not always. For example, an envelope
 * crossing the anti-meridian could have an upper corner longitude less than the lower corner longitude.
 * Whether an envelope supports the extended bounding box interpretation or not is
 * implementation-dependent. If supported, the extended interpretation is applicable only to
 * axes having a {@link RangeMeaning#WRAPAROUND WRAPAROUND} range meaning, which is usually
 * the longitude axis.
 *
 * @departure easeOfUse
 *   The ISO specification defines this interface in the {@code coordinate} sub-package.
 *   GeoAPI moved this interface into the {@code org.opengis.geometry} root package for
 *   convenience, because it is extensively used.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   1.0
 *
 * @see org.opengis.coverage.grid.GridEnvelope
 */
@Classifier(Stereotype.DATATYPE)
@UML(identifier="GM_Envelope", specification=ISO_19107)
public interface Envelope {
    /**
     * Returns the envelope coordinate reference system, or {@code null} if unknown.
     * If non-null, it shall be the same as {@linkplain #getLowerCorner() lower corner}
     * and {@linkplain #getUpperCorner() upper corner} CRS.
     *
     * @return the envelope CRS, or {@code null} if unknown.
     *
     * @departure easeOfUse
     *   ISO does not define this method - the CRS or the dimension can be obtained only through
     *   one of the corner {@code DirectPosition} objects. GeoAPI adds this method for convenience
     *   as a more direct way of obtaining the information and to free the user from the need to
     *   choose an arbitrary corner (very defensive code might feel the need to get the value from
     *   both corners to check they were the same).
     */
    CoordinateReferenceSystem getCoordinateReferenceSystem();

    /**
     * The length of coordinate sequence (the number of entries) in this envelope. Mandatory
     * even when the {@linkplain #getCoordinateReferenceSystem() coordinate reference system}
     * is unknown.
     *
     * @return the dimensionality of this envelope.
     *
     * @departure easeOfUse
     *   ISO does not define this method - the CRS or the dimension can be obtained only through
     *   one of the corner {@code DirectPosition} objects. GeoAPI adds this method for convenience
     *   as a more direct way of obtaining the information and to free the user from the need to
     *   choose an arbitrary corner (very defensive code might feel the need to get the value from
     *   both corners to check they were the same).
     */
    int getDimension();

    /**
     * The limits in the direction of decreasing coordinate values for each dimension.
     * This is typically a coordinate position consisting of all the minimal coordinates
     * for each dimension for all points within the {@code Envelope}.
     *
     * <h4>Spanning the anti-meridian of a geographic CRS</h4>
     * The <cite>Web Coverage Service</cite> (WCS) 1.1 specification uses an extended interpretation
     * of the bounding box definition. In a WCS 1.1 data structure, the lower corner defines the
     * edges region in the directions of <em>decreasing</em> coordinate values in the envelope CRS.
     * This is usually the algebraic {@linkplain #getMinimum(int) minimum} coordinates, but not
     * always. For example, an envelope crossing the anti-meridian could have a lower corner
     * longitude greater than the {@linkplain #getUpperCorner() upper corner} longitude.
     * Whether this envelope supports the extended bounding box interpretation or not is
     * implementation-dependent. If supported, the extended interpretation is applicable only to
     * axes having a {@link RangeMeaning#WRAPAROUND WRAPAROUND} range meaning - usually the
     * longitude axis. On typical map representations, the {@code getLowerCorner()} method
     * name still "visually" appropriate since the <cite>lower</cite> corner still toward the
     * bottom of the map even if the left corner became the right corner.
     *
     * @return the lower corner, typically (but not necessarily) containing minimal coordinate values.
     */
    @UML(identifier="lowerCorner", obligation=MANDATORY, specification=ISO_19107)
    DirectPosition getLowerCorner();

    /**
     * The limits in the direction of increasing coordinate values for each dimension.
     * This is typically a coordinate position consisting of all the maximal coordinates
     * for each dimension for all points within the {@code Envelope}.
     *
     * <h4>Spanning the anti-meridian of a geographic CRS</h4>
     * The <cite>Web Coverage Service</cite> (WCS) 1.1 specification uses an extended interpretation
     * of the bounding box definition. In a WCS 1.1 data structure, the upper corner defines the
     * edges region in the directions of <em>increasing</em> coordinate values in the envelope CRS.
     * This is usually the algebraic {@linkplain #getMaximum(int) maximum} coordinates, but not
     * always. For example, an envelope crossing the anti-meridian could have an upper corner
     * longitude less than the {@linkplain #getLowerCorner() lower corner} longitude.
     * Whether this envelope supports the extended bounding box interpretation or not is
     * implementation-dependent. If supported, the extended interpretation is applicable only to
     * axes having a {@link RangeMeaning#WRAPAROUND WRAPAROUND} range meaning - usually the
     * longitude axis. On typical map representations, the {@code getUpperCorner()} method
     * name still "visually" appropriate since the <cite>upper</cite> corner still toward the
     * top of the map even if the right corner became the left corner.
     *
     * @return the upper corner, typically (but not necessarily) containing maximal coordinate values.
     */
    @UML(identifier="upperCorner", obligation=MANDATORY, specification=ISO_19107)
    DirectPosition getUpperCorner();

    /**
     * Returns the minimal coordinate value for the specified dimension. In the typical case of
     * non-empty envelopes <em>not</em> spanning the anti-meridian, this method is a shortcut
     * for the following code without the cost of creating a temporary {@link DirectPosition}
     * object:
     *
     * <blockquote><code>
     * minimum = {@linkplain #getLowerCorner()}.{@linkplain DirectPosition#getOrdinate(int) getOrdinate}(dimension);
     * </code></blockquote>
     *
     * <h4>Spanning the anti-meridian of a geographic CRS</h4>
     * If the axis range meaning is {@link RangeMeaning#WRAPAROUND WRAPAROUND} and this envelope
     * supports the {@linkplain #getLowerCorner() lower} and {@linkplain #getUpperCorner() upper}
     * corners extended interpretation, then <var>lower</var> may possibly be greater than
     * <var>upper</var>. In such case, implementations shall select some value such that
     * <var>minimum</var> &lt; <var>maximum</var> (ignoring NaN). It may be the
     * {@linkplain org.opengis.referencing.cs.CoordinateSystemAxis#getMinimumValue() axis minimum value},
     * {@linkplain Double#NEGATIVE_INFINITY negative infinity}, {@linkplain Double#NaN NaN} or other
     * value, at implementor choice.
     *
     * @param  dimension  the dimension for which to obtain the coordinate value.
     * @return the minimal coordinate at the given dimension.
     * @throws IndexOutOfBoundsException if the given index is negative or is equals or greater
     *         than the {@linkplain #getDimension() envelope dimension}.
     *
     * @departure easeOfUse
     *   This method is not part of ISO specification. GeoAPI adds this method for convenience and efficiency,
     *   since some implementations might store the minimum and maximum coordinate values directly in the
     *   {@code Envelope} itself rather than in a contained {@code DirectPosition} corner.
     *
     * @see Rectangle2D#getMinX()
     * @see Rectangle2D#getMinY()
     */
    double getMinimum(int dimension) throws IndexOutOfBoundsException;

    /**
     * Returns the maximal coordinate value for the specified dimension. In the typical case of
     * non-empty envelopes <em>not</em> spanning the anti-meridian, this method is a shortcut
     * for the following code without the cost of creating a temporary {@link DirectPosition}
     * object:
     *
     * <blockquote><code>
     * maximum = {@linkplain #getUpperCorner()}.{@linkplain DirectPosition#getOrdinate(int) getOrdinate}(dimension);
     * </code></blockquote>
     *
     * <h4>Spanning the anti-meridian of a geographic CRS</h4>
     * If the axis range meaning is {@link RangeMeaning#WRAPAROUND WRAPAROUND} and this envelope
     * supports the {@linkplain #getLowerCorner() lower} and {@linkplain #getUpperCorner() upper}
     * corners extended interpretation, then <var>upper</var> may possibly be less than
     * <var>lower</var>. In such case, implementations shall select some value such that
     * <var>maximum</var> &gt; <var>minimum</var> (ignoring NaN). It may be the
     * {@linkplain org.opengis.referencing.cs.CoordinateSystemAxis#getMaximumValue() axis maximum value},
     * {@linkplain Double#POSITIVE_INFINITY positive infinity}, {@linkplain Double#NaN NaN} or other
     * value, at implementor choice.
     *
     * @param  dimension  the dimension for which to obtain the coordinate value.
     * @return the maximal coordinate at the given dimension.
     * @throws IndexOutOfBoundsException if the given index is negative or is equals or greater
     *         than the {@linkplain #getDimension() envelope dimension}.
     *
     * @departure easeOfUse
     *   This method is not part of ISO specification. GeoAPI adds this method for convenience and efficiency,
     *   since some implementations might store the minimum and maximum coordinate values directly in the
     *   {@code Envelope} itself rather than in a contained {@code DirectPosition} corner.
     *
     * @see Rectangle2D#getMaxX()
     * @see Rectangle2D#getMaxY()
     */
    double getMaximum(int dimension) throws IndexOutOfBoundsException;

    /**
     * Returns the median coordinate along the specified dimension.
     * In most cases, the result is equals (minus rounding error) to:
     *
     * <blockquote><code>
     * median = ({@linkplain #getMinimum(int) getMinimum}(dimension) + {@linkplain #getMaximum(int) getMaximum}(dimension)) / 2;
     * </code></blockquote>
     *
     * <h4>Spanning the anti-meridian of a geographic CRS</h4>
     * If this envelope supports the {@linkplain #getLowerCorner() lower} and
     * {@linkplain #getUpperCorner() upper} corners extended interpretation, and if the axis
     * range meaning is {@link RangeMeaning#WRAPAROUND WRAPAROUND}, then a special cases occurs
     * when <var>upper</var> &lt; <var>lower</var>. In such cases, the coordinate values from the
     * lower and upper corner may be used instead than the minimum and maximum values, with the
     * periodicity (360° for longitudes) added to the upper value before to perform the median calculation.
     * Implementations are free to use variants of the above algorithm. For example some
     * libraries may add different multiples of the periodicity in order to ensure that the
     * median value is inside the axis range.
     *
     * @param  dimension  the dimension for which to obtain the coordinate value.
     * @return the median coordinate at the given dimension.
     * @throws IndexOutOfBoundsException if the given index is negative or is equals or greater
     *         than the {@linkplain #getDimension() envelope dimension}.
     *
     * @departure easeOfUse
     *   This method is not part of ISO specification. GeoAPI adds this method for convenience and efficiency,
     *   since some implementations might store the minimum and maximum coordinate values directly in the
     *   {@code Envelope} itself rather than in a contained {@code DirectPosition} corner.
     *
     * @see Rectangle2D#getCenterX()
     * @see Rectangle2D#getCenterY()
     */
    double getMedian(int dimension) throws IndexOutOfBoundsException;

    /**
     * Returns the envelope span (typically width or height) along the specified dimension.
     * In most cases, the result is equals (minus rounding error) to:
     *
     * <blockquote><code>
     * span = {@linkplain #getMaximum(int) getMaximum}(dimension) - {@linkplain #getMinimum(int) getMinimum}(dimension);
     * </code></blockquote>
     *
     * <h4>Spanning the anti-meridian of a geographic CRS</h4>
     * If this envelope supports the {@linkplain #getLowerCorner() lower} and
     * {@linkplain #getUpperCorner() upper} corners extended interpretation, and if the axis
     * range meaning is {@link RangeMeaning#WRAPAROUND WRAPAROUND}, then a special cases occurs
     * when <var>upper</var> &lt; <var>lower</var>. In such cases, the coordinate values from the
     * lower and upper corner may be used instead than the minimum and maximum values, with the
     * periodicity (360° for longitudes) added to the upper value before to perform the span calculation.
     * Implementations are free to use variants of the above algorithm. For example some
     * libraries may add different multiples of the periodicity.
     *
     * @param  dimension  the dimension for which to obtain the span.
     * @return the span (typically width or height) at the given dimension.
     * @throws IndexOutOfBoundsException if the given index is negative or is equals or greater
     *         than the {@linkplain #getDimension() envelope dimension}.
     *
     * @departure easeOfUse
     *   This method is not part of ISO specification. GeoAPI adds this method for convenience and efficiency,
     *   since some implementations might store the span values directly in the {@code Envelope} itself rather
     *   than calculating it from the corners.
     *
     * @see Rectangle2D#getWidth()
     * @see Rectangle2D#getHeight()
     */
    double getSpan(int dimension) throws IndexOutOfBoundsException;
}
