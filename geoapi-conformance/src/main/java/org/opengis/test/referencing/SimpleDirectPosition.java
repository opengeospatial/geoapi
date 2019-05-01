/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2008-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.test.referencing;

import java.util.Arrays;
import java.awt.geom.Point2D;
import java.lang.reflect.Array;
import org.opengis.geometry.DirectPosition;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import static org.junit.Assert.*;


/**
 * A trivial implementation of {@link DirectPosition} for internal usage by
 * {@link TransformTestCase}. Not public because strictly reserved to tests.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   2.2
 */
final class SimpleDirectPosition implements DirectPosition {
    /**
     * The ordinates.
     */
    protected final double[] ordinates;

    /**
     * {@code true} to freeze this position. If {@code true}, then any attempts to invoke
     * a {@link #setOrdinate(int, double)} method will cause a JUnit test failure.
     *
     * <p>Note that setting this field to {@code true} does not prevent {@link TransformTestCase}
     * to write directly in the {@link #ordinates} array. But since this class is package-private,
     * the code writing directly in the ordinates array should know what they are doing.</p>
     */
    boolean unmodifiable;

    /**
     * Creates a new direct position of the given dimension.
     *
     * @param  dimension  the dimension.
     */
    public SimpleDirectPosition(final int dimension) {
        ordinates = new double[dimension];
    }

    /**
     * Creates a new direct position initialized to the given ordinate values.
     *
     * @param ordinates  the ordinate values. This array is <strong>not</strong> cloned.
     *
     * @since 3.1
     */
    public SimpleDirectPosition(final double[] ordinates) {
        assertNotNull("Array of ordinate values shall not be null.", ordinates);
        this.ordinates = ordinates;
    }

    /**
     * Creates a new two-dimensional direct position initialized to the given point.
     */
    public SimpleDirectPosition(final Point2D point) {
        ordinates = new double[] {
            point.getX(),
            point.getY()
        };
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
     * Sets all ordinate values. The array length must be equal to the number of dimensions.
     *
     * @since 3.1
     */
    public void setCoordinate(final double... ordinates) {
        assertFalse("This DirectPosition shall not be modified.", unmodifiable);
        assertEquals("Unexpected dimension.", this.ordinates.length, ordinates.length);
        System.arraycopy(ordinates, 0, this.ordinates, 0, ordinates.length);
    }

    /**
     * Sets all ordinate values starting at the given offset in the given array.
     * This method is for internal usage by {@link TransformTestCase} only.
     *
     * @param array      the {@code float[]} or {@code double[]} array.
     * @param offset     index of the first element to copy from the given array.
     * @param useDouble  if {@code false}, cast the values to floats.
     */
    final void setCoordinate(final Object ordinates, int offset, final boolean useDouble) {
        assertFalse("This DirectPosition shall not be modified.", unmodifiable);
        final int dimension = this.ordinates.length;
        for (int i=0; i<dimension; i++) {
            double ordinate = Array.getDouble(ordinates, offset++);
            if (!useDouble) {
                ordinate = (float) ordinate;
            }
            this.ordinates[i] = ordinate;
        }
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
        assertFalse("This DirectPosition shall not be modified.", unmodifiable);
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
     * Returns the <cite>Well Known Test</cite> (WKT) representation of this direct position.
     */
    @Override
    public String toString() {
        boolean castToFloats = true;
        for (final double ordinate : ordinates) {
            if (Double.doubleToLongBits(ordinate) != Double.doubleToLongBits((float) ordinate)) {
                castToFloats = false;
                break;
            }
        }
        final StringBuilder buffer = new StringBuilder("POINT(");
        for (int i=0; i<ordinates.length; i++) {
            if (i != 0) {
                buffer.append(' ');
            }
            final double ordinate = ordinates[i];
            if (castToFloats) {
                buffer.append((float) ordinate);
            } else {
                buffer.append(ordinate);
            }
        }
        return buffer.append(')').toString();
    }
}
