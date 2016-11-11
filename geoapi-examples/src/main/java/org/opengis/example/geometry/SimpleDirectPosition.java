/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.geometry;

import java.util.Arrays;
import java.util.Objects;
import java.io.Serializable;

import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;


/**
 * A {@link DirectPosition} implementation which store ordinate values in a {@code double[]} array.
 * This implementation can store an optional reference to an existing
 * {@linkplain CoordinateReferenceSystem Coordinate Reference System}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class SimpleDirectPosition implements DirectPosition, Serializable {
    /**
     * For cross-version compatibility.
     */
    private static final long serialVersionUID = 5075669833975740957L;

    /**
     * The ordinate values. The length of this array is the {@linkplain #getDimension() dimension}
     * of this direct position.
     *
     * <p>This array is public for allowing more efficient ordinates operations, for example using
     * the {@link java.util.Arrays} methods. However we encourage to use only the methods from
     * the {@link DirectPosition} interface in most cases.</p>
     *
     * @see #getCoordinate()
     */
    public final double[] ordinates;

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
     * All ordinate values are initialized to zero.
     *
     * @param  dimension  the dimension.
     */
    public SimpleDirectPosition(final int dimension) {
        ordinates = new double[dimension];
    }

    /**
     * Creates a new direct position associated to the given coordinate reference system.
     * The {@linkplain #getDimension() dimension} of the new direct position is the
     * dimension of the given CRS. All ordinate values are initialized to zero.
     *
     * @param crs  the coordinate reference system to associate to the new direct position.
     */
    public SimpleDirectPosition(final CoordinateReferenceSystem crs) {
        ordinates = new double[crs.getCoordinateSystem().getDimension()];
        this.crs = crs;
    }

    /**
     * Creates a new direct position initialized to the given ordinate values.
     * If the given CRS is non-null, then its dimension shall be equal to the
     * length of the given {@code ordinates} array.
     *
     * <p>This constructor assigns the given array directly (without clone) to the
     * {@link #ordinates} field, because that field is public anyway. Defensive
     * copy would not protect the state of this object.</p>
     *
     * @param  crs        the coordinate reference system, or {@code null}.
     * @param  ordinates  the ordinate values. This array is <strong>not</strong> cloned.
     * @throws MismatchedDimensionException if the given CRS is not null and its dimension
     *         is not equals to the length of the {@code ordinates} array.
     */
    public SimpleDirectPosition(final CoordinateReferenceSystem crs, final double... ordinates)
            throws MismatchedDimensionException
    {
        Objects.requireNonNull(ordinates);
        if (crs != null && crs.getCoordinateSystem().getDimension() != ordinates.length) {
            throw new MismatchedDimensionException();
        }
        this.ordinates = ordinates;
        this.crs = crs;
    }

    /**
     * Creates a new direct position initialized to the CRS and ordinate values
     * of the given direct position. This is a copy constructor.
     *
     * @param position  the direct position from which to copy the CRS and ordinate values.
     */
    public SimpleDirectPosition(final DirectPosition position) {
        ordinates = position.getCoordinate();           // Array shall be a copy according DirectPosition contract.
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
     * The length of {@linkplain #ordinates coordinate sequence} (the number of entries).
     *
     * @return the length of the {@link #ordinates} array.
     */
    @Override
    public int getDimension() {
        return ordinates.length;
    }

    /**
     * Returns a <em>copy</em> of the {@linkplain #ordinates} array.
     *
     * @return a clone of the {@link #ordinates} array.
     */
    @Override
    public double[] getCoordinate() {
        return ordinates.clone();
    }

    /**
     * Returns the ordinate at the specified dimension.
     *
     * @param  dimension  the dimension in the range 0 to {@linkplain #getDimension dimension}-1.
     * @return the value in the {@link #ordinates} array at the given index.
     * @throws IndexOutOfBoundsException if the given index is negative or is equals or greater
     *         than the {@linkplain #getDimension() position dimension}.
     */
    @Override
    public double getOrdinate(final int dimension) throws IndexOutOfBoundsException {
        return ordinates[dimension];
    }

    /**
     * Sets the ordinate value along the specified dimension.
     *
     * @param  dimension  the dimension for the ordinate of interest.
     * @param  value      the ordinate value of interest.
     * @throws IndexOutOfBoundsException if the given index is negative or is equals or greater
     *         than the {@linkplain #getDimension() position dimension}.
     */
    @Override
    public void setOrdinate(final int dimension, final double value) throws IndexOutOfBoundsException {
        ordinates[dimension] = value;
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
            return Arrays.equals(ordinates, other.getCoordinate()) &&
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
        return Arrays.hashCode(ordinates) + Objects.hashCode(crs);
    }

    /**
     * Returns a string representation of this direct position in <cite>Well-Known Text</cite> (WKT) format.
     *
     * @return the <cite>Well-Known Text</cite> representation of this direct position.
     *
     * @see <a href="http://en.wikipedia.org/wiki/Well-known_text">Well-known text on Wikipedia</a>
     */
    @Override
    public String toString() {
        final StringBuilder buffer = new StringBuilder("POINT");
        char separator = '(';
        for (final double ordinate : ordinates) {
            buffer.append(separator).append(ordinate);
            separator = ' ';
        }
        return buffer.append(')').toString();
    }
}
