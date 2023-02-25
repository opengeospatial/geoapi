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

import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Holds the set of grid coordinates that specifies the location of the
 * {@linkplain GridPoint grid point} within the {@linkplain Grid grid}.
 *
 * @version ISO 19123:2004
 * @author  Martin Schouwenburg
 * @author  Wim Koolhoven
 * @author  Martin Desruisseaux (IRD)
 * @since   GeoAPI 2.1
 */
@UML(identifier="CV_GridCoordinates", specification=ISO_19123)
public interface GridCoordinates {
    /**
     * Returns the number of dimensions. This method is equivalent to
     * <code>{@linkplain #getCoordinateValues()}.length</code>. It is
     * provided for efficiency.
     *
     * @return the number of dimensions.
     */
    int getDimension();

    /**
     * Returns one integer value for each dimension of the grid. The ordering of these coordinate
     * values shall be the same as that of the elements of {@link Grid#getAxisNames}. The value of
     * a single coordinate shall be the number of offsets from the origin of the grid in the direction
     * of a specific axis.
     *
     * @return a copy of the coordinates. Changes in the returned array will not be reflected
     *         back in this {@code GridCoordinates} object.
     */
    @UML(identifier="coordValues", obligation=MANDATORY, specification=ISO_19123)
    long[] getCoordinateValues();

    /**
     * Returns the coordinate value at the specified dimension. This method is equivalent to
     * <code>{@linkplain #getCoordinateValues()}[<var>i</var>]</code>. It is provided for
     * efficiency.
     *
     * @param  dimension  the dimension for which to obtain the coordinate value.
     * @return the coordinate value at the given dimension.
     * @throws IndexOutOfBoundsException if the given index is negative or is equal
     *         or greater than the {@linkplain #getDimension grid dimension}.
     */
    long getCoordinateValue(int dimension) throws IndexOutOfBoundsException;

    /**
     * Sets the coordinate value at the specified dimension (optional operation).
     *
     * @param  dimension  the dimension for which to set the coordinate value.
     * @param  value  the new value.
     * @throws IndexOutOfBoundsException if the given index is negative or is equal
     *         or greater than the {@linkplain #getDimension grid dimension}.
     * @throws UnsupportedOperationException if this grid coordinates is not modifiable.
     */
    void setCoordinateValue(int dimension, long value)
            throws IndexOutOfBoundsException, UnsupportedOperationException;
}
