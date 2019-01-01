/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2007-2019 Open Geospatial Consortium, Inc.
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

import java.util.List;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.geometry.coordinate.PointArray;
import org.opengis.geometry.coordinate.Position;


/**
 * A factory for managing {@linkplain DirectPosition direct position} creation.
 * <p>
 * This factory will be created for a known {@linkplain CoordinateReferenceSystem
 * Coordinate Reference System} and {@linkplain Precision precision} model.
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
     * The Precision used used by {@linkplain DirectPosition direct positions}
     * created via this factory.
     * <p>
     * The Precision is used to inform topological operations of the number of
     * significant digits maintained by the {@link DirectPosition} instances. This
     * information both helps operations stop when the correct level of detail is
     * reached, and ensure the result will be valid when rounded to the required
     * precision.
     */
    Precision getPrecision();

    /**
     * Creates a direct position at the specified location specified by coordinates.
     * <p>
     * Implementations have the option of taking ownership of the provided
     * coordinate array. You should not attempt to reuse this array after it
     * has been provided to this factory method.
     *
     * @param coordinates Array of ordinates used for this DirectPosition
     * @throws MismatchedDimensionException if the coordinates array length doesn't match
     *         the {@linkplain #getCoordinateReferenceSystem coordinate reference system}
     *         dimension.
     */
    DirectPosition createDirectPosition(double[] coordinates)
            throws MismatchedDimensionException;

    /**
     * Constructs a position from an other position by copying the coordinate values of the
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
