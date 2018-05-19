/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The netCDF wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementors can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.wrapper.netcdf;

import java.util.Arrays;
import java.io.Serializable;

import org.opengis.coverage.grid.Grid;
import org.opengis.coverage.grid.GridPoint;
import org.opengis.coverage.grid.GridCoordinates;


/**
 * Holds the set of grid coordinates that specifies the location of the
 * {@linkplain GridPoint grid point} within the {@linkplain Grid grid}.
 *
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class SimpleGridCoordinates implements GridCoordinates, Serializable {
    /**
     * For cross-version compatibility.
     */
    private static final long serialVersionUID = -3963745583373126645L;

    /**
     * The grid coordinates.
     */
    protected final int[] coordinates;

    /**
     * Creates a grid coordinates initialized to the specified values.
     *
     * @param coordinates The grid coordinates to copy.
     */
    public SimpleGridCoordinates(final int[] coordinates) {
        this.coordinates = coordinates.clone();
    }

    /**
     * Returns the number of dimensions. This method is equivalent to
     * <code>{@linkplain #getCoordinateValues()}.length</code>. It is
     * provided for efficiency.
     */
    @Override
    public int getDimension() {
        return coordinates.length;
    }

    /**
     * Returns one integer value for each dimension of the grid. The ordering of these coordinate
     * values shall be the same as that of the elements of {@link Grid#getAxisNames}. The value of
     * a single coordinate shall be the number of offsets from the origin of the grid in the
     * direction of a specific axis.
     *
     * @return a copy of the coordinates. Changes in the returned array will not be reflected
     *         back in this {@code SimpleGridCoordinates} object.
     */
    @Override
    public int[] getCoordinateValues() {
        return coordinates.clone();
    }

    /**
     * Returns the coordinate value at the specified dimension. This method is equivalent to
     * <code>{@linkplain #getCoordinateValues()}[<var>i</var>]</code>. It is provided for
     * efficiency.
     *
     * @param  dimension  the dimension from 0 inclusive to {@link #getDimension} exclusive.
     * @return the value at the requested dimension.
     * @throws ArrayIndexOutOfBoundsException if the specified dimension is out of bounds.
     */
    @Override
    public int getCoordinateValue(final int dimension) throws ArrayIndexOutOfBoundsException {
        return coordinates[dimension];
    }

    /**
     * Sets the coordinate value at the specified dimension (optional operation).
     *
     * @param  dimension  the index of the value to set.
     * @param  value  the new value.
     * @throws ArrayIndexOutOfBoundsException if the specified dimension is out of bounds.
     */
    @Override
    public void setCoordinateValue(final int dimension, final int value) throws ArrayIndexOutOfBoundsException {
        coordinates[dimension] = value;
    }

    /**
     * Compares this grid coordinates with the specified object for equality.
     *
     * @param  object  the object to compares with this grid coordinates.
     * @return {@code true} if the given object is equal to this grid coordinates.
     */
    @Override
    public boolean equals(final Object object) {
        if (object instanceof SimpleGridCoordinates) {
            return Arrays.equals(coordinates, ((SimpleGridCoordinates) object).coordinates);
        }
        return false;
    }

    /**
     * Returns a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(coordinates) ^ (int) serialVersionUID;
    }

    /**
     * Returns a string representation of this grid coordinates.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + Arrays.toString(coordinates);
    }
}
