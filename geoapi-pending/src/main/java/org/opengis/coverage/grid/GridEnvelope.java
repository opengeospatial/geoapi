/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2005-2023 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
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
     * @throws IndexOutOfBoundsException if the given index is negative or is equal or greater
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
     * @throws IndexOutOfBoundsException if the given index is negative or is equal or greater
     *         than the {@linkplain #getDimension() grid dimension}.
     */
    long getHigh(int dimension) throws IndexOutOfBoundsException;

    /**
     * Returns the number of integer grid coordinates along the specified dimension.
     * This is equal to:
     *
     * <blockquote><code>
     * {@linkplain #getHigh getHigh}(dimension) - {@linkplain #getLow getLow}(dimension) + 1
     * </code></blockquote>
     *
     * @param  dimension  the dimension for which to obtain the size.
     * @return the number of integer grid coordinates along the given dimension.
     * @throws IndexOutOfBoundsException if the given index is negative or is equal or greater
     *         than the {@linkplain #getDimension() grid dimension}.
     * @throws ArithmeticException if the size is too large for the {@code long} primitive type.
     *
     * @see #getLow(int)
     * @see #getHigh(int)
     */
    long getSize(int dimension) throws IndexOutOfBoundsException;
}
