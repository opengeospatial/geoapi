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
 * A trivial implementation of {@link DirectPosition} backed by an array of {@code double} values.
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
     * The ordinate values. The length of this array is the
     * {@linkplain #getDimension() dimension} of this direct position.
     */
    protected final double[] ordinates;

    /**
     * The coordinate reference system associated to this direct position,
     * or {@code null} if unspecified.
     */
    protected final CoordinateReferenceSystem crs;

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
     * {@inheritDoc}
     */
    @Override
    public CoordinateReferenceSystem getCoordinateReferenceSystem() {
        return crs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDimension() {
        return ordinates.length;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double[] getCoordinate() {
        return ordinates.clone();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getOrdinate(int dimension) throws IndexOutOfBoundsException {
        return ordinates[dimension];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setOrdinate(int dimension, double value) throws IndexOutOfBoundsException {
        ordinates[dimension] = value;
    }

    /**
     * {@inheritDoc}
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
