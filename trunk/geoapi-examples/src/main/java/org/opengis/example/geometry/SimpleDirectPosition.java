/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.geometry;

import java.util.Arrays;
import java.io.Serializable;

import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;


/**
 * A trivial implementation of {@link DirectPosition} which stores ordinate values in a
 * {@code double[]} array. This implementation can store an optional reference to an existing
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
     * <p>
     * This array is public for allowing more efficient ordinates operations, for example using
     * the {@link java.util.Arrays} methods. However we encourage to use only the methods from
     * the {@link DirectPosition} interface in most cases.
     *
     * @see #getCoordinate()
     */
    public final double[] ordinates;

    /**
     * The coordinate reference system associated to this direct position,
     * or {@code null} if unspecified.
     *
     * @see #getCoordinateReferenceSystem()
     */
    private final CoordinateReferenceSystem crs;

    /**
     * Creates a new direct position of the given dimension.
     * The Coordinate Reference System is left unspecified.
     * All ordinate values are initialized to zero.
     *
     * @param dimension The dimension.
     */
    public SimpleDirectPosition(final int dimension) {
        ordinates = new double[dimension];
        crs = null;
    }

    /**
     * Creates a new direct position associated to the given coordinate reference system.
     * The {@linkplain #getDimension() dimension} of the new direct position is the
     * dimension of the given CRS. All ordinate values are initialized to zero.
     *
     * @param crs The coordinate reference system to associate to the new direct position.
     */
    public SimpleDirectPosition(final CoordinateReferenceSystem crs) {
        ordinates = new double[crs.getCoordinateSystem().getDimension()];
        this.crs = crs;
    }

    /**
     * Creates a new direct position initialized to the given ordinate values.
     * If the given CRS is non-null, then its dimension must be equals to the
     * length of the given {@code ordinates} array.
     *
     * @param  crs The coordinate reference system, or {@code null}.
     * @param  ordinates The ordinate values.
     * @throws MismatchedDimensionException If the given CRS is not null and its dimension
     *         is not equals to the length of the {@code ordinates} array.
     *
     * @since 3.1
     */
    public SimpleDirectPosition(final CoordinateReferenceSystem crs, final double... ordinates)
            throws MismatchedDimensionException
    {
        if (crs != null && crs.getCoordinateSystem().getDimension() != ordinates.length) {
            throw new MismatchedDimensionException();
        }
        this.ordinates = ordinates.clone();
        this.crs = crs;
    }

    /**
     * Returns the coordinate reference system in which the coordinate is given.
     * May be {@code null} if no CRS were specified at construction time. In such case,
     * this particular direct position is assumed included in a larger object with such
     * a reference to a {@linkplain CoordinateReferenceSystem coordinate reference system}.
     *
     * @return The coordinate reference system, or {@code null}.
     */
    @Override
    public CoordinateReferenceSystem getCoordinateReferenceSystem() {
        return crs;
    }

    /**
     * The length of {@linkplain #ordinates coordinate sequence} (the number of entries).
     *
     * @return The length of the {@link #ordinates} array.
     */
    @Override
    public int getDimension() {
        return ordinates.length;
    }

    /**
     * Returns a <em>copy</em> of the {@linkplain #ordinates} array.
     *
     * @return A clone of the {@link #ordinates} array.
     */
    @Override
    public double[] getCoordinate() {
        return ordinates.clone();
    }

    /**
     * Returns the ordinate at the specified dimension.
     *
     * @param  dimension The dimension in the range 0 to {@linkplain #getDimension dimension}-1.
     * @return The value in the {@link #ordinates} array at the given index.
     * @throws IndexOutOfBoundsException If the given index is negative or is equals or greater
     *         than the {@linkplain #getDimension() position dimension}.
     */
    @Override
    public double getOrdinate(final int dimension) throws IndexOutOfBoundsException {
        return ordinates[dimension];
    }

    /**
     * Sets the ordinate value along the specified dimension.
     *
     * @param  dimension the dimension for the ordinate of interest.
     * @param  value the ordinate value of interest.
     * @throws IndexOutOfBoundsException If the given index is negative or is equals or greater
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
     * Returns {@code true} if this direct position is equals to the given object.
     */
    @Override
    public boolean equals(final Object object) {
        if (object instanceof DirectPosition) {
            final DirectPosition other = (DirectPosition) object;
            if (Arrays.equals(ordinates, other.getCoordinate())) {
                // Note: below this point, JDK7 developers can use Objects.equals(...) instead.
                final CoordinateReferenceSystem otherCRS = other.getCoordinateReferenceSystem();
                if (otherCRS == crs || (otherCRS != null && otherCRS.equals(crs))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns a hash code value for this direct position.
     */
    @Override
    public int hashCode() {
        int code = Arrays.hashCode(ordinates);
        if (crs != null) {
            code += 31 * crs.hashCode();
        }
        return code;
    }

    /**
     * Returns a string representation of this direct position in <cite>Well Known Text</cite>
     * format.
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
