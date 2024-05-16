/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2007-2023 Open Geospatial Consortium, Inc.
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

import java.util.List;
import org.opengis.coordinate.MismatchedDimensionException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.geometry.coordinate.PointArray;
import org.opengis.geometry.coordinate.Position;


/**
 * A factory for managing {@linkplain DirectPosition direct position} creation.
 * <p>
 * This factory will be created for a known {@linkplain CoordinateReferenceSystem
 * Coordinate Reference System}.
 * </p>
 *
 * @author Jody Garnett
 * @since GeoAPI 2.1
 */
public interface PositionFactory {
    /**
     * Returns the coordinate reference system in use for all
     * {@linkplain DirectPosition direct positions} to be created
     * through this interface.
     */
    CoordinateReferenceSystem getCoordinateReferenceSystem();

    /**
     * Creates a direct position at the specified location specified by coordinates.
     * <p>
     * Implementations have the option of taking ownership of the provided
     * coordinate array. You should not attempt to reuse this array after it
     * has been provided to this factory method.
     *
     * @param coordinates array of coordinates used for this {@code DirectPosition}.
     * @throws MismatchedDimensionException if the coordinates array length doesn't match
     *         the {@linkplain #getCoordinateReferenceSystem coordinate reference system}
     *         dimension.
     */
    DirectPosition createDirectPosition(double[] coordinates)
            throws MismatchedDimensionException;

    /**
     * Constructs a position from another position by copying the coordinate values of the
     * position. There will be no further reference to the position instance.
     *
     * @param position A position.
     * @return the position which defines the coordinates for the other position.
     */
    Position createPosition(Position position);

    /**
     * Creates a (possibly optimized) list for positions. The list is initially
     * empty. New direct positions can be stored using the {@link List#add} method.
     */
    PointArray createPointArray();

    /**
     * Creates a list for positions initialised from the specified values.
     * <p>
     * Implementations have the option of taking ownership of the provided
     * coordinate array. You should not attempt to reuse this array after it
     * has been provided to this factory method.
     *
     * @param coordinates The coordinates to assign to the list of positions.
     * @param start       The first valid value in the {@code coordinates} array.
     * @param length      The number of valid values in the {@code coordinates} array.
     * @return            The list of positions.
     */
    PointArray createPointArray(double[] coordinates, int start, int length);

    /**
     * Creates a list for positions initialized from the specified values.
     * <p>
     * Implementations have the option of taking ownership of the provided
     * coordinate array. You should not attempt to reuse this array after it
     * has been provided to this factory method.
     *
     * @param coordinates The coordinates to assign to the list of positions.
     * @param start       The first valid value in the {@code coordinates} array.
     * @param length      The number of valid values in the {@code coordinates} array.
     * @return            The list of positions.
     */
    PointArray createPointArray(float[] coordinates, int start, int length);
}
