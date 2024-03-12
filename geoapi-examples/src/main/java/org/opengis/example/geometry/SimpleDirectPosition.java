/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.geometry;

import java.util.Arrays;
import java.util.Objects;

import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;


/**
 * A {@link DirectPosition} implementation which store coordinate values in a {@code double[]} array.
 * This implementation can store an optional reference to an existing
 * {@linkplain CoordinateReferenceSystem Coordinate Reference System}.
 */
public class SimpleDirectPosition implements DirectPosition {
    /**
     * The coordinate values. The length of this array is the {@linkplain #getDimension() dimension}
     * of this direct position.
     *
     * <p>This array is public for allowing more efficient coordinates operations, for example using
     * the {@link java.util.Arrays} methods. However, we encourage to use only the methods from
     * the {@link DirectPosition} interface in most cases.</p>
     *
     * @see #getCoordinate()
     */
    public final double[] coordinates;

    /**
     * The coordinate reference system associated to this direct position,
     * or {@code null} if unspecified.
     *
     * <p>The {@code SimpleDirectPosition} class does not provide any setter for this field,
     * since uncontrolled modifications of geometry CRS is often undesirable.
     * The decision to allow modifications or not is left to subclasses.</p>
     *
     * @see #getCoordinateReferenceSystem()
     */
    protected CoordinateReferenceSystem crs;

    /**
     * Creates a new direct position of the given dimension.
     * The Coordinate Reference System is left unspecified.
     * All coordinate values are initialized to zero.
     *
     * @param  dimension  the dimension.
     */
    public SimpleDirectPosition(final int dimension) {
        coordinates = new double[dimension];
    }

    /**
     * Creates a new direct position associated to the given coordinate reference system.
     * The {@linkplain #getDimension() dimension} of the new direct position is the
     * dimension of the given CRS. All coordinate values are initialized to zero.
     *
     * @param crs  the coordinate reference system to associate to the new direct position.
     */
    public SimpleDirectPosition(final CoordinateReferenceSystem crs) {
        coordinates = new double[crs.getCoordinateSystem().getDimension()];
        this.crs = crs;
    }

    /**
     * Creates a new direct position initialized to the given coordinate values.
     * If the given CRS is non-null, then its dimension shall be equal to the
     * length of the given {@code coordinates} array.
     *
     * <p>This constructor assigns the given array directly (without clone) to the
     * {@link #coordinates} field, because that field is public anyway. Defensive
     * copy would not protect the state of this object.</p>
     *
     * @param  crs        the coordinate reference system, or {@code null}.
     * @param  coordinates  the coordinate values. This array is <strong>not</strong> cloned.
     * @throws MismatchedDimensionException if the given CRS is not null and its dimension
     *         is not equals to the length of the {@code coordinates} array.
     */
    public SimpleDirectPosition(final CoordinateReferenceSystem crs, final double... coordinates)
            throws MismatchedDimensionException
    {
        this.coordinates = Objects.requireNonNull(coordinates);
        this.crs = crs;
        if (crs != null && crs.getCoordinateSystem().getDimension() != coordinates.length) {
            throw new MismatchedDimensionException();
        }
    }

    /**
     * Creates a new direct position initialized to the CRS and coordinate values
     * of the given direct position. This is a copy constructor.
     *
     * @param position  the direct position from which to copy the CRS and coordinate values.
     */
    public SimpleDirectPosition(final DirectPosition position) {
        coordinates = position.getCoordinate();           // Array shall be a copy according DirectPosition contract.
        crs = position.getCoordinateReferenceSystem();
    }

    /**
     * Returns the coordinate reference system in which the coordinate is given.
     * May be {@code null} if no CRS were specified at construction time. In such case,
     * this particular direct position is assumed included in a larger object with such
     * a reference to a {@linkplain CoordinateReferenceSystem coordinate reference system}.
     *
     * @return the coordinate reference system, or {@code null}.
     */
    @Override
    public CoordinateReferenceSystem getCoordinateReferenceSystem() {
        return crs;
    }

    /**
     * The length of {@linkplain #coordinates coordinate sequence} (the number of entries).
     *
     * @return the length of the {@link #coordinates} array.
     */
    @Override
    public int getDimension() {
        return coordinates.length;
    }

    /**
     * Returns a <em>copy</em> of the {@linkplain #coordinates} array.
     *
     * @return a clone of the {@link #coordinates} array.
     */
    @Override
    public double[] getCoordinate() {
        return coordinates.clone();
    }

    /**
     * Returns the coordinate at the specified dimension.
     *
     * @param  dimension  the dimension in the range 0 to {@linkplain #getDimension dimension}-1.
     * @return the value in the {@link #coordinates} array at the given index.
     * @throws IndexOutOfBoundsException if the given index is negative or is equal or greater
     *         than the {@linkplain #getDimension() position dimension}.
     */
    @Override
    public double getOrdinate(final int dimension) throws IndexOutOfBoundsException {
        return coordinates[dimension];
    }

    /**
     * Sets the coordinate value along the specified dimension.
     *
     * @param  dimension  the dimension for the coordinate of interest.
     * @param  value      the coordinate value of interest.
     * @throws IndexOutOfBoundsException if the given index is negative or is equal or greater
     *         than the {@linkplain #getDimension() position dimension}.
     */
    @Override
    public void setOrdinate(final int dimension, final double value) throws IndexOutOfBoundsException {
        coordinates[dimension] = value;
    }

    /**
     * Unconditionally returns {@code this}, since this object is already a direct position.
     */
    @Override
    public DirectPosition getDirectPosition() {
        return this;
    }

    /**
     * Returns {@code true} if the specified object is also a {@code DirectPosition}
     * with equal coordinate and equal CRS.
     *
     * This method performs the comparison as documented in the {@link DirectPosition#equals(Object)}
     * Javadoc. In particular, the given object is not required to be of the same implementation class.
     * Consequently, it should be possible to mix different {@code DirectPosition} implementations in
     * the same hash map.
     */
    @Override
    public boolean equals(final Object object) {
        if (object instanceof DirectPosition) {
            final DirectPosition other = (DirectPosition) object;
            return Arrays.equals(coordinates, other.getCoordinate()) &&
                   Objects.equals(crs, other.getCoordinateReferenceSystem());
        }
        return false;
    }

    /**
     * Returns a hash code value for this direct position.
     * This method returns a value compliant with the contract documented in the
     * {@link DirectPosition#hashCode()} javadoc. Consequently, it should be possible
     * to mix different {@code DirectPosition} implementations in the same hash map.
     *
     * @return a hash code value for this position.
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(coordinates) + Objects.hashCode(crs);
    }

    /**
     * Returns a string representation of this direct position in <i>Well-Known Text</i> (WKT) format.
     *
     * @return the Well-Known Text representation of this direct position.
     *
     * @see <a href="https://en.wikipedia.org/wiki/Well-known_text_representation_of_geometry">Well-known text on Wikipedia</a>
     */
    @Override
    public String toString() {
        final StringBuilder buffer = new StringBuilder("POINT");
        char separator = '(';
        for (final double coordinate : coordinates) {
            buffer.append(separator).append(coordinate);
            separator = ' ';
        }
        return buffer.append(')').toString();
    }
}
