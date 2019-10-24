/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 *
 *    The netCDF wrappers are provided as code examples, in the hope to facilitate
 *    GeoAPI implementations backed by other libraries. Implementers can take this
 *    source code and use it for any purpose, commercial or non-commercial, copyrighted
 *    or open-source, with no legal obligation to acknowledge the borrowing/copying
 *    in any way.
 */
package org.opengis.wrapper.netcdf;

import java.util.Arrays;
import java.io.Serializable;
import org.opengis.geometry.DirectPosition;
import org.opengis.referencing.crs.CoordinateReferenceSystem;


/**
 * A trivial implementation of {@link DirectPosition}.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class SimpleDirectPosition implements DirectPosition, Serializable {
    /**
     * For cross-version compatibility.
     */
    private static final long serialVersionUID = -6629745469199237817L;

    /**
     * The ordinates.
     */
    protected final double[] ordinates;

    /**
     * Creates a new direct position of the given dimension.
     *
     * @param  dimension  the dimension.
     */
    public SimpleDirectPosition(final int dimension) {
        ordinates = new double[dimension];
    }

    /**
     * Returns always {@code null}, since it is allowed by the specification
     * and {@link TransformTestCase} doesn't want to test the handling of CRS.
     */
    @Override
    public CoordinateReferenceSystem getCoordinateReferenceSystem() {
        return null;
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
            if (other.getCoordinateReferenceSystem() == null) {
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
        return Arrays.hashCode(ordinates);
    }

    /**
     * Returns a string representation of this direct position in <cite>Well-Known Text</cite> (WKT) format.
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
