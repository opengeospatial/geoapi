/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.geometry;

import java.util.Arrays;
import java.util.Objects;
import org.opengis.geometry.Envelope;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.MismatchedDimensionException;
import org.opengis.geometry.MismatchedReferenceSystemException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;


/**
 * An unmodifiable {@link Envelope} implementation defined by two corners.
 * This implementation can store an optional reference to an existing
 * {@linkplain CoordinateReferenceSystem Coordinate Reference System}.
 *
 * <p>This simple implementation does not support envelopes crossing the anti-meridian.
 * Consequently, lower coordinate values shall not be greater than corresponding upper
 * coordinate values.</p>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public class SimpleEnvelope implements Envelope {
    /**
     * Coordinate values of lower and upper corners. The length of this array is twice the
     * number of dimensions. The first half contains the lower corner, while the second
     * half contains the upper corner.
     */
    private final double[] coordinates;

    /**
     * The coordinate reference system associated to this envelope, or {@code null} if unspecified.
     *
     * @see #getCoordinateReferenceSystem()
     */
    private final CoordinateReferenceSystem crs;

    /**
     * Constructs an envelope defined by two direct positions.
     * The CRS of the envelope will be the CRS of the given direct positions, which shall be the equal.
     *
     * @param  lowerCorner  the limits in the direction of decreasing coordinate values for each dimension.
     * @param  upperCorner  the limits in the direction of increasing coordinate values for each dimension.
     * @throws MismatchedReferenceSystemException if the CRS of the two position are not equal.
     * @throws MismatchedDimensionException if the two positions do not have the same dimension.
     * @throws IllegalArgumentException if an coordinate value in the lower corner is greater than
     *         the corresponding coordinate value in the upper corner.
     */
    public SimpleEnvelope(final DirectPosition lowerCorner, final DirectPosition upperCorner)
            throws MismatchedDimensionException, MismatchedReferenceSystemException
    {
        crs = lowerCorner.getCoordinateReferenceSystem();                           // May be null.
        if (!Objects.equals(crs, upperCorner.getCoordinateReferenceSystem())) {
            throw new MismatchedReferenceSystemException();
        }
        final int dimension = lowerCorner.getDimension();
        if (dimension != upperCorner.getDimension()) {
            throw new MismatchedDimensionException();
        }
        coordinates = new double[dimension * 2];
        setCorners(coordinates, lowerCorner, upperCorner);
    }

    /**
     * Constructs a new envelope with the same data as the specified envelope.
     * This is a copy constructor.
     *
     * @param  envelope  the envelope to copy.
     * @throws IllegalArgumentException if an coordinate value in the lower corner is greater than
     *         the corresponding coordinate value in the upper corner.
     */
    public SimpleEnvelope(final Envelope envelope) {
        crs = envelope.getCoordinateReferenceSystem();
        coordinates = new double[envelope.getDimension() * 2];
        setCorners(coordinates, envelope.getLowerCorner(), envelope.getUpperCorner());
    }

    /**
     * Sets the coordinate values to the given corners. This method does not verify the corners CRS
     * neither their dimensions; they must have been checked by the caller. However, this method
     * verifies that the lower values are not greater than upper values, because this simple class
     * does not support envelopes crossing the anti-meridian.
     *
     * @param  coordinates  the array where to set the coordinate values.
     * @param  lowerCorner  first corner from which to get the coordinates.
     * @param  upperCorner  second corner from which to get the coordinates.
     */
    private static void setCorners(final double[] coordinates,
                                   final DirectPosition lowerCorner,
                                   final DirectPosition upperCorner)
    {
        final int dimension = coordinates.length/2;
        for (int i=0; i<dimension; i++) {
            final double lower = lowerCorner.getOrdinate(i);
            final double upper = upperCorner.getOrdinate(i);
            if (lower > upper) {
                throw new IllegalArgumentException("Coordinate value in the lower corner at dimension "
                        + i + ", which is " + lower + ", cannot be greater than the corresponding "
                        + "coordinate value in the upper corner, which is " + upper + '.');
            }
            coordinates[i            ] = lower;
            coordinates[i + dimension] = upper;
        }
    }

    /**
     * Returns the length of coordinate sequence (the number of entries) in this envelope.
     *
     * @return the dimensionality of this envelope.
     */
    @Override
    public final int getDimension() {
        return coordinates.length / 2;
    }

    /**
     * Returns the envelope coordinate reference system, or {@code null} if unknown.
     *
     * @return the envelope CRS, or {@code null} if unknown.
     */
    @Override
    public CoordinateReferenceSystem getCoordinateReferenceSystem() {
        return crs;
    }

    /**
     * The limits in the direction of decreasing coordinate values for each dimension.
     * This is typically a coordinate position consisting of all the minimal coordinates
     * for each dimension for all points within the {@code Envelope}.
     *
     * <p>This method returns a copy of the lower corner.
     * Changes in the returned position will not be reflected in this envelope.</p>
     *
     * @return the lower corner, typically (but not necessarily) containing minimal coordinate values.
     */
    @Override
    public DirectPosition getLowerCorner() {
        final int dimension = coordinates.length / 2;
        final SimpleDirectPosition position = new SimpleDirectPosition(dimension);
        System.arraycopy(coordinates, 0, position.coordinates, 0, dimension);
        position.crs = crs;
        return position;
    }

    /**
     * The limits in the direction of increasing coordinate values for each dimension.
     * This is typically a coordinate position consisting of all the maximal coordinates
     * for each dimension for all points within the {@code Envelope}.
     *
     * <p>This method returns a copy of the upper corner.
     * Changes in the returned position will not be reflected in this envelope.</p>
     *
     * @return the upper corner, typically (but not necessarily) containing maximal coordinate values.
     */
    @Override
    public DirectPosition getUpperCorner() {
        final int dimension = coordinates.length / 2;
        final SimpleDirectPosition position = new SimpleDirectPosition(dimension);
        System.arraycopy(coordinates, dimension, position.coordinates, 0, dimension);
        position.crs = crs;
        return position;
    }

    /**
     * Ensures that the given dimension is equal or greater than zero and lower than the
     * number of dimensions in this envelope.
     *
     * @param  dimension  the dimension to check.
     * @throws IndexOutOfBoundsException if the given dimension is negative or not lower
     *         than the number of dimensions in this envelope.
     */
    private void ensureValidDimension(final int dimension) throws IndexOutOfBoundsException {
        if (dimension < 0 || dimension >= coordinates.length/2) {
            throw new IndexOutOfBoundsException("Dimension " + dimension + " is out of bounds.");
        }
    }

    /**
     * Returns the minimal coordinate value for the specified dimension.
     * Since this simple class does not support envelopes crossing the anti-meridian,
     * this method is equivalent to the following code:
     *
     * {@snippet lang="java" :
     * return getLowerCorner().getOrdinate(dimension);
     * }
     *
     * @param  dimension  the dimension for which to obtain the coordinate value.
     * @return the minimal coordinate at the given dimension.
     * @throws IndexOutOfBoundsException if the given index is negative or is equal or greater
     *         than the {@linkplain #getDimension() envelope dimension}.
     */
    @Override
    public double getMinimum(final int dimension) throws IndexOutOfBoundsException {
        ensureValidDimension(dimension);
        return coordinates[dimension];
    }

    /**
     * Returns the maximal coordinate value for the specified dimension.
     * Since this simple class does not support envelopes crossing the anti-meridian,
     * this method is equivalent to the following code:
     *
     * {@snippet lang="java" :
     * return getUpperCorner().getOrdinate(dimension);
     * }
     *
     * @param  dimension  the dimension for which to obtain the coordinate value.
     * @return the maximal coordinate at the given dimension.
     * @throws IndexOutOfBoundsException if the given index is negative or is equal or greater
     *         than the {@linkplain #getDimension() envelope dimension}.
     */
    @Override
    public double getMaximum(int dimension) throws IndexOutOfBoundsException {
        ensureValidDimension(dimension);
        return coordinates[dimension + coordinates.length/2];
    }

    /**
     * Returns the median coordinate along the specified dimension.
     * Since this simple class does not support envelopes crossing the anti-meridian,
     * this method is equivalent to the following code:
     *
     * {@snippet lang="java" :
     * return 0.5*(getMinimum(dimension) + getMaximum(dimension));
     * }
     */
    @Override
    public double getMedian(int dimension) throws IndexOutOfBoundsException {
        ensureValidDimension(dimension);
        return 0.5*(coordinates[dimension] + coordinates[dimension + coordinates.length/2]);
    }

    /**
     * Returns the envelope span (typically width or height) along the specified dimension.
     * Since this simple class does not support envelopes crossing the anti-meridian,
     * this method is equivalent to the following code:
     *
     * {@snippet lang="java" :
     * return getMaximum(dimension) - getMinimum(dimension);
     * }
     */
    @Override
    public double getSpan(int dimension) throws IndexOutOfBoundsException {
        ensureValidDimension(dimension);
        return coordinates[dimension + coordinates.length/2] - coordinates[dimension];
    }

    /**
     * Compares this envelope with the specified object for equality.
     * Since the {@code equals(Object)} and {@code hashCode()} methods are not documented
     * in the {@code Envelope} interface, this method returns {@code false} if the given
     * object is not an instance of the same {@code SimpleEnvelope} class. We do that in
     * order to preserve consistency with {@link #hashCode()}.
     *
     * @param  object  the object to compare with this envelope, or {@code null}.
     * @return {@code true} if the given object is an instance of the same {@code SimpleEnvelope}
     *         class, and have equal coordinate values and equal CRS.
     */
    @Override
    public boolean equals(final Object object) {
        if (object != null && object.getClass() == getClass()) {
            final SimpleEnvelope that = (SimpleEnvelope) object;
            return Arrays.equals(coordinates, that.coordinates) &&
                   Objects.equals(crs, that.crs);
        }
        return false;
    }

    /**
     * Returns an arbitrary hash code value for this envelope.
     *
     * @return a hash code value.
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(coordinates) + Objects.hashCode(crs);
    }

    /**
     * Formats this envelope in the <i>Well-Known Text</i> (WKT) format.
     * The output is of the form "{@code BOX}<var>n</var>{@code D(}{@linkplain #getLowerCorner()
     * lower corner}{@code ,}{@linkplain #getUpperCorner() upper corner}{@code )}"
     * where <var>n</var> is the {@linkplain #getDimension() number of dimensions}.
     *
     * @return this envelope as a {@code BOX2D} or {@code BOX3D} (most typical dimensions) in WKT format.
     */
    @Override
    public String toString() {
        final int dimension = getDimension();
        final StringBuilder  buffer = new StringBuilder(64).append("BOX").append(dimension).append('D');
        char separator = '(';
        for (int i=0; i<dimension; i++) {
            buffer.append(separator).append(getMinimum(i));
            separator = ' ';
        }
        buffer.append(',');
        for (int i=0; i<dimension; i++) {
            buffer.append(' ').append(getMaximum(i));
        }
        return buffer.append(')').toString();
    }
}
