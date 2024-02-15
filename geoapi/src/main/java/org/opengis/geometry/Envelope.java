/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.geometry;

import java.awt.geom.Rectangle2D; // Used in @see javadoc tags
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A minimum bounding box or rectangle. Regardless of dimension, an {@code Envelope} can
 * be represented without ambiguity as two direct positions (coordinate points). To encode an
 * {@code Envelope}, it is sufficient to encode these two points. This is consistent with
 * all of the data types in this specification, their state is represented by their publicly
 * accessible attributes.
 * 
 * @departure easeOfUse
 *   This interface was moved into the <code>org.opengis.geometry</code> package for convenience.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 *
 * @see org.opengis.coverage.grid.GridEnvelope
 *
 * @navassoc 1 - - CoordinateReferenceSystem
 * @navassoc 2 - - DirectPosition
 */
@UML(identifier="GM_Envelope", specification=ISO_19107)
public interface Envelope {
    /**
     * Returns the envelope coordinate reference system, or {@code null} if unknown.
     * If non-null, it shall be the same as {@linkplain #getLowerCorner lower corner}
     * and {@linkplain #getUpperCorner upper corner} CRS.
     *
     * @return The envelope CRS, or {@code null} if unknown.
     *
     * @departure easeOfUse
     *   ISO does not define this method - the CRS or the dimension can be obtained only through
     *   one of the corner <code>DirectPosition</code> objects. GeoAPI adds this method for
     *   convenience as a more direct way of obtaining the information and to free the user from
     *   the need to choose an arbitrary corner (very defensive code might feel the need to get
     *   the value from both corners to check they were the same).
     *
     * @since 2.1
     */
    CoordinateReferenceSystem getCoordinateReferenceSystem();

    /**
     * The length of coordinate sequence (the number of entries) in this envelope. Mandatory
     * even when the {@linkplain #getCoordinateReferenceSystem coordinate reference system}
     * is unknown.
     *
     * @return The dimensionality of this envelope.
     *
     * @departure easeOfUse
     *   ISO does not define this method - the CRS or the dimension can be obtained only through
     *   one of the corner <code>DirectPosition</code> objects. GeoAPI adds this method for
     *   convenience as a more direct way of obtaining the information and to free the user from
     *   the need to choose an arbitrary corner (very defensive code might feel the need to get
     *   the value from both corners to check they were the same).
     *
     * @since 2.0
     */
    int getDimension();

    /**
     * A coordinate position consisting of all the minimal ordinates for each
     * dimension for all points within the {@code Envelope}.
     *
     * @return The lower corner.
     */
    @UML(identifier="lowerCorner", obligation=MANDATORY, specification=ISO_19107)
    DirectPosition getLowerCorner();

    /**
     * A coordinate position consisting of all the maximal ordinates for each
     * dimension for all points within the {@code Envelope}.
     *
     * @return The upper corner.
     */
    @UML(identifier="upperCorner", obligation=MANDATORY, specification=ISO_19107)
    DirectPosition getUpperCorner();

    /**
     * Returns the minimal ordinate along the specified dimension. This is a shortcut for
     * the following without the cost of creating a temporary {@link DirectPosition} object:
     *
     * <blockquote><code>
     * {@linkplain #getLowerCorner}.{@linkplain DirectPosition#getOrdinate getOrdinate}(dimension)
     * </code></blockquote>
     *
     * @param  dimension The dimension for which to obtain the ordinate value.
     * @return The minimal ordinate at the given dimension.
     * @throws IndexOutOfBoundsException If the given index is negative or is equal or greater
     *         than the {@linkplain #getDimension envelope dimension}.
     *
     * @departure easeOfUse
     *   This method is not part of ISO specification. GeoAPI adds this method for convenience and
     *   efficiency, since some implementations might store the minimum and maximum ordinate values
     *   directly in the <code>Envelope</code> itself rather than in a contained 
     *   <code>DirectPosition</code> corner.
     *
     * @see Rectangle2D#getMinX()
     * @see Rectangle2D#getMinY()
     *
     * @since 2.0
     */
    double getMinimum(int dimension) throws IndexOutOfBoundsException;

    /**
     * Returns the maximal ordinate along the specified dimension. This is a shortcut for
     * the following without the cost of creating a temporary {@link DirectPosition} object:
     *
     * <blockquote><code>
     * {@linkplain #getUpperCorner}.{@linkplain DirectPosition#getOrdinate getOrdinate}(dimension)
     * </code></blockquote>
     *
     * @param  dimension The dimension for which to obtain the ordinate value.
     * @return The maximal ordinate at the given dimension.
     * @throws IndexOutOfBoundsException If the given index is negative or is equal or greater
     *         than the {@linkplain #getDimension envelope dimension}.
     *
     * @departure easeOfUse
     *   This method is not part of ISO specification. GeoAPI adds this method for convenience and
     *   efficiency, since some implementations might store the minimum and maximum ordinate values
     *   directly in the <code>Envelope</code> itself rather than in a contained 
     *   <code>DirectPosition</code> corner.
     *
     * @see Rectangle2D#getMaxX()
     * @see Rectangle2D#getMaxY()
     *
     * @since 2.0
     */
    double getMaximum(int dimension) throws IndexOutOfBoundsException;

    /**
     * Returns the median ordinate along the specified dimension. The result shall be equals
     * (minus rounding error) to:
     *
     * <blockquote><code>
     * ({@linkplain #getMinimum getMinimum}(dimension) + {@linkplain #getMaximum getMaximum}(dimension)) / 2
     * </code></blockquote>
     *
     * @param  dimension The dimension for which to obtain the ordinate value.
     * @return The median ordinate at the given dimension.
     * @throws IndexOutOfBoundsException If the given index is negative or is equal or greater
     *         than the {@linkplain #getDimension envelope dimension}.
     *
     * @departure easeOfUse
     *   This method is not part of ISO specification. GeoAPI adds this method for convenience and
     *   efficiency, since some implementations might store the minimum and maximum ordinate values
     *   directly in the <code>Envelope</code> itself rather than in a contained 
     *   <code>DirectPosition</code> corner.
     *
     * @see Rectangle2D#getCenterX()
     * @see Rectangle2D#getCenterY()
     *
     * @since 2.2
     */
    double getMedian(int dimension) throws IndexOutOfBoundsException;

    /**
     * Returns the envelope span (typically width or height) along the specified dimension.
     * The result shall be equals (minus rounding error) to:
     *
     * <blockquote><code>
     * {@linkplain #getMaximum getMaximum}(dimension) - {@linkplain #getMinimum getMinimum}(dimension)
     * </code></blockquote>
     *
     * @param  dimension The dimension for which to obtain the ordinate value.
     * @return The span (typically width or height) at the given dimension.
     * @throws IndexOutOfBoundsException If the given index is negative or is equal or greater
     *         than the {@linkplain #getDimension envelope dimension}.
     *
     * @departure easeOfUse
     *   This method is not part of ISO specification. GeoAPI adds this method for convenience and
     *   efficiency, since some implementations might store the minimum and maximum ordinate values
     *   directly in the <code>Envelope</code> itself rather than in a contained 
     *   <code>DirectPosition</code> corner.
     *
     * @see Rectangle2D#getWidth()
     * @see Rectangle2D#getHeight()
     *
     * @since 2.2
     */
    double getSpan(int dimension) throws IndexOutOfBoundsException;
}
