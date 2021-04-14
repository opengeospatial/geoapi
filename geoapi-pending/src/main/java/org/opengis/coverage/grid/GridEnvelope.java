/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2005-2021 Open Geospatial Consortium, Inc.
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
package org.opengis.coverage.grid;

import java.awt.Rectangle;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Provides the {@linkplain GridCoordinates grid coordinate} values for the diametrically opposed
 * corners of the {@linkplain Grid grid}.
 *
 * <p>Remark that both corners are inclusive. Thus the number of elements in the direction of the first
 * axis is <code>{@linkplain #getHigh(int) getHigh}(0) - {@linkplain #getLow(int) getLow}(0) + 1</code>.
 * This is the opposite of Java2D usage where maximal values in {@link Rectangle} (as computed by
 * {@linkplain Rectangle#getMaxX getMaxX()} and {@linkplain Rectangle#getMinY getMaxY()}) are
 * exclusive.</p>
 *
 * @version ISO 19123:2004
 * @author  Wim Koolhoven
 * @author  Martin Schouwenburg
 * @since   GeoAPI 2.1
 *
 * @see org.opengis.geometry.Envelope
 */
@UML(identifier="CV_GridEnvelope", specification=ISO_19123)
public interface GridEnvelope {
    /**
     * Returns the number of dimensions. It shall be equal to the number of dimensions
     * of {@linkplain #getLow low} and {@linkplain #getHigh high} grid coordinates.
     *
     * @return the number of dimensions.
     *
     * @since GeoAPI 2.2
     */
    int getDimension();

    /**
     * Returns the minimal coordinate values for all grid points within the {@linkplain Grid grid}.
     *
     * @return the minimal coordinate values for all grid points, inclusive.
     */
    @UML(identifier="low", obligation=MANDATORY, specification=ISO_19123)
    GridCoordinates getLow();

    /**
     * Returns the maximal coordinate values for all grid points within the {@linkplain Grid grid}.
     *
     * @return the maximal coordinate values for all grid points, <strong>inclusive</strong>.
     */
    @UML(identifier="high", obligation=MANDATORY, specification=ISO_19123)
    GridCoordinates getHigh();

    /**
     * Returns the valid minimum inclusive grid coordinate along the specified dimension. This is a
     * shortcut for the following without the cost of creating a temporary {@link GridCoordinates} object:
     *
     * <blockquote><code>
     * {@linkplain #getLow}.{@linkplain GridCoordinates#getCoordinateValue getCoordinateValue}(dimension)
     * </code></blockquote>
     *
     * @param  dimension  the dimension for which to obtain the coordinate value.
     * @return the low coordinate value at the given dimension, inclusive.
     * @throws IndexOutOfBoundsException if the given index is negative or is equals or greater
     *         than the {@linkplain #getDimension() grid dimension}.
     */
    long getLow(int dimension) throws IndexOutOfBoundsException;

    /**
     * Returns the valid maximum inclusive grid coordinate along the specified dimension. This is a
     * shortcut for the following without the cost of creating a temporary {@link GridCoordinates} object:
     *
     * <blockquote><code>
     * {@linkplain #getHigh}.{@linkplain GridCoordinates#getCoordinateValue getCoordinateValue}(dimension)
     * </code></blockquote>
     *
     * @param  dimension  the dimension for which to obtain the coordinate value.
     * @return the high coordinate value at the given dimension, <strong>inclusive</strong>.
     * @throws IndexOutOfBoundsException if the given index is negative or is equals or greater
     *         than the {@linkplain #getDimension() grid dimension}.
     */
    long getHigh(int dimension) throws IndexOutOfBoundsException;

    /**
     * Returns the number of integer grid coordinates along the specified dimension.
     * This is equals to:
     *
     * <blockquote><code>
     * {@linkplain #getHigh getHigh}(dimension) - {@linkplain #getLow getLow}(dimension) + 1
     * </code></blockquote>
     *
     * @param  dimension  the dimension for which to obtain the size.
     * @return the number of integer grid coordinates along the given dimension.
     * @throws IndexOutOfBoundsException if the given index is negative or is equals or greater
     *         than the {@linkplain #getDimension() grid dimension}.
     * @throws ArithmeticException if the size is too large for the {@code long} primitive type.
     *
     * @see #getLow(int)
     * @see #getHigh(int)
     */
    long getSize(int dimension) throws IndexOutOfBoundsException;
}
