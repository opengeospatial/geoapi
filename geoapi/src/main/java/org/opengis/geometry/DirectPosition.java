/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2003-2024 Open Geospatial Consortium, Inc.
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

import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.annotation.UML;
import org.opengis.annotation.Classifier;
import org.opengis.annotation.Stereotype;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Holds the coordinates for a position within some coordinate reference system.
 * Since {@code DirectPosition}s, as data types, will often be included in larger objects
 * (such as {@linkplain org.opengis.geometry.Geometry geometries}) that have references to
 * {@linkplain CoordinateReferenceSystem coordinate reference system} (CRS),
 * the {@link #getCoordinateReferenceSystem()} method may return {@code null} if this particular
 * {@code DirectPosition} is included in a larger object with such a reference to a <abbr>CRS</abbr>.
 * In this case, the coordinate reference system is implicitly assumed to take on the value
 * of the containing object's <abbr>CRS</abbr>.
 *
 * <h2>Optional operation</h2>
 * A direct position can optionally be modifiable. If this {@code DirectPosition} is unmodifiable,
 * then all setter methods will throw {@link UnsupportedOperationException}.
 *
 * @departure easeOfUse
 *   The ISO specification defines this interface in the {@code coordinate} sub-package.
 *   GeoAPI moved this interface into the {@code org.opengis.geometry} root package for
 *   convenience, because it is extensively used.
 *
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 4.0
 * @since   1.0
 */
@Classifier(Stereotype.DATATYPE)
@UML(identifier="DirectPosition", specification=ISO_19107)
public interface DirectPosition {
    /**
     * The coordinate reference system (CRS) in which the coordinate tuple is given.
     * May be {@code null} if this particular {@code DirectPosition} is included in a larger object
     * with such a reference to a {@linkplain CoordinateReferenceSystem coordinate reference system}.
     * In this case, the coordinate reference system is implicitly assumed to take on the value
     * of the containing object's <abbr>CRS</abbr>.
     *
     * <h4>Default implementation</h4>
     * The default implementation returns {@code null}. Implementations should override
     * this method if the <abbr>CRS</abbr> is known or can be taken from the containing object.
     *
     * @return the coordinate reference system (CRS), or {@code null}.
     */
    @UML(identifier="coordinateReferenceSystem", obligation=MANDATORY, specification=ISO_19107)
    default CoordinateReferenceSystem getCoordinateReferenceSystem() {
        return null;
    }

    /**
     * The length of coordinate sequence (the number of entries). This is determined by the
     * {@linkplain #getCoordinateReferenceSystem() coordinate reference system}.
     *
     * @return the dimensionality of this position.
     */
    @UML(identifier="dimension", obligation=MANDATORY, specification=ISO_19107)
    int getDimension();

    /**
     * A <b>copy</b> of the coordinates stored as an array of double values.
     * Changes to the returned array will not affect this {@code DirectPosition}.
     * The array length shall be equal to the {@linkplain #getDimension() dimension}.
     *
     * <h4>Default implementation</h4>
     * The default implementation invokes {@link #getCoordinate(int)} for all indices
     * from 0 inclusive to {@link #getDimension()} exclusive, and stores the values
     * in a newly created array.
     *
     * @return a copy of the coordinates. Changes in the returned array will not be reflected back
     *         in this {@code DirectPosition} object.
     *
     * @since 3.1
     */
    @UML(identifier="coordinate", obligation=MANDATORY, specification=ISO_19107)
    default double[] getCoordinates() {
        final double[] coordinates = new double[getDimension()];
        for (int i=0; i<coordinates.length; i++) {
            coordinates[i] = getCoordinate(i);
        }
        return coordinates;
    }

    /**
     * Returns the coordinate at the specified dimension.
     *
     * @param  dimension  the dimension in the range 0 to {@linkplain #getDimension dimension}−1.
     * @return the coordinate at the specified dimension.
     * @throws IndexOutOfBoundsException if the given index is negative or is equal or greater
     *         than the {@linkplain #getDimension() number of dimensions}.
     *
     * @since 3.1
     */
    double getCoordinate(int dimension);

    /**
     * Sets the coordinate value along the specified dimension.
     * This is an optional operation.
     *
     * <h4>Default implementation</h4>
     * The default implementation throws {@code UnsupportedOperationException}.
     * Implementations need to override this method if this direct position is mutable.
     *
     * @param  dimension  the dimension for the coordinate of interest.
     * @param  value      the coordinate value of interest.
     * @throws IndexOutOfBoundsException if the given index is negative or is equal or greater
     *         than the {@linkplain #getDimension() position dimension}.
     * @throws UnsupportedOperationException if this direct position is immutable.
     *
     * @since 3.1
     */
    default void setCoordinate(int dimension, double value) {
        throw new UnsupportedOperationException();
    }

    /**
     * Compares this direct position with the specified object for equality.
     * Two direct positions are considered equal if the following conditions are met:
     *
     * <ul>
     *   <li>{@code object} is non-null and is an instance of {@code DirectPosition}.</li>
     *   <li>Both direct positions have the same {@linkplain #getDimension() number of dimensions}.</li>
     *   <li>Both direct positions have the same or equal {@linkplain #getCoordinateReferenceSystem()
     *       coordinate reference system}.</li>
     *   <li>For all dimension <var>i</var>, the {@linkplain #getCoordinates coordinate value} of both
     *       direct positions at that dimension are equals in the sense of {@link Double#equals(Object)}.</li>
     * </ul>
     *
     * In other words, this method shall be equivalent to the following code:
     *
     * {@snippet lang="java" :
     * public boolean equals(Object obj) {
     *     return (obj instanceof DirectPosition other)
     *             && Arrays.equals(getCoordinates(), other.getCoordinates())
     *             && Objects.equals(getCoordinateReferenceSystem(), other.getCoordinateReferenceSystem());
     * }
     * }
     *
     * @param  object  the object to compare with this direct position for equality.
     * @return {@code true} if the given object is equal to this direct position.
     */
    @Override
    boolean equals(Object object);

    /**
     * Returns a hash code value for this direct position.
     * This method shall be equivalent to the following code:
     *
     * {@snippet lang="java" :
     * public int hashCode() {
     *     return Arrays.hashCode(getCoordinates()) + Objects.hashCode(getCoordinateReferenceSystem());
     * }
     * }
     *
     * @return a hash code value for this direct position.
     */
    @Override
    int hashCode();
}
