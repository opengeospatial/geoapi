/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.simple;

import java.util.Arrays;
import java.io.Serializable;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;


/**
 * A {@link DirectPosition} which stores ordinate values in an {@code double[]} array.
 * This implementation can store an optional reference to an existing
 * {@linkplain CoordinateReferenceSystem Coordinate Reference System}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class SimplePosition implements DirectPosition, Serializable {
    /**
     * For cross-version compatibility.
     */
    private static final long serialVersionUID = 3857593689493894398L;

    /**
     * The coordinate reference system, or {@code null} if none.
     */
    protected final CoordinateReferenceSystem crs;

    /**
     * The ordinates values.
     */
    protected final double[] ordinates;

    /**
     * Creates a new direct position of the given dimension without CRS.
     * All ordinate values are initialized to 0.
     *
     * @param dimension The number of dimensions.
     */
    public SimplePosition(final int dimension) {
        crs = null;
        ordinates = new double[dimension];
    }

    /**
     * Creates a new direct position associated to the given CRS.
     * All ordinate values are initialized to 0.
     *
     * @param crs The coordinate reference system.
     */
    public SimplePosition(final CoordinateReferenceSystem crs) {
        this.crs = crs;
        ordinates = new double[crs.getCoordinateSystem().getDimension()];
    }

    /**
     * Creates a new direct position associated to the given CRS and initialized
     * to the given ordinate values.
     *
     * @param  crs The coordinate reference system, or {@code null} if none.
     * @param  ordinates The ordinate values.
     * @throws MismatchedDimensionException If the given CRS is not null and its dimension is
     *         not equals to the length of the given ordinates array.
     */
    public SimplePosition(final CoordinateReferenceSystem crs, final double... ordinates)
            throws MismatchedDimensionException
    {
        if (crs != null && crs.getCoordinateSystem().getDimension() != ordinates.length) {
            throw new MismatchedDimensionException();
        }
        this.crs = crs;
        this.ordinates = ordinates.clone();
    }

    /**
     * Returns the Coordinate Reference System associated to this position.
     * This is the CRS argument given at construction time.
     */
    @Override
    public CoordinateReferenceSystem getCoordinateReferenceSystem() {
        return crs;
    }

    /**
     * Returns the length of coordinate sequence (the number of entries).
     */
    @Override
    public int getDimension() {
        return ordinates.length;
    }

    /**
     * Returns a <b>copy</b> of the ordinates presented as an array of double values.
     */
    @Override
    public double[] getCoordinate() {
        return ordinates.clone();
    }

    /**
     * Returns the ordinate at the specified dimension.
     */
    @Override
    public double getOrdinate(final int dimension) throws IndexOutOfBoundsException {
        return ordinates[dimension];
    }

    /**
     * Sets the ordinate value along the specified dimension.
     */
    @Override
    public void setOrdinate(final int dimension, final double value) throws IndexOutOfBoundsException {
        ordinates[dimension] = value;
    }

    /**
     * Returns {@code this}, since this {@code SimplePosition} object is the direct position.
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
            if (Objects.equals(other.getCoordinateReferenceSystem(), crs)) {
                return Arrays.equals(ordinates, other.getCoordinate());
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
            code += crs.hashCode();
        }
        return code;
    }
}
