/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2024 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.opengis.test.referencing;

import java.util.Arrays;
import java.awt.geom.Point2D;
import java.lang.reflect.Array;
import org.opengis.geometry.DirectPosition;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import static org.junit.jupiter.api.Assertions.*;


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
     * The coordinates.
     */
    protected final double[] coordinates;

    /**
     * {@code true} to freeze this position. If {@code true}, then any attempts to invoke
     * a {@link #setCoordinate(int, double)} method will cause a JUnit test failure.
     *
     * <p>Note that setting this field to {@code true} does not prevent {@link TransformTestCase}
     * to write directly in the {@link #coordinates} array. But since this class is package-private,
     * the code writing directly in the coordinates array should know what they are doing.</p>
     */
    boolean unmodifiable;

    /**
     * Creates a new direct position of the given dimension.
     *
     * @param  dimension  the dimension.
     */
    public SimpleDirectPosition(final int dimension) {
        coordinates = new double[dimension];
    }

    /**
     * Creates a new direct position initialized to the given coordinate values.
     *
     * @param coordinates  the coordinate values. This array is <strong>not</strong> cloned.
     *
     * @since 3.1
     */
    public SimpleDirectPosition(final double[] coordinates) {
        assertNotNull(coordinates, "Array of coordinate values shall not be null.");
        this.coordinates = coordinates;
    }

    /**
     * Creates a new two-dimensional direct position initialized to the given point.
     *
     * @param  point  the initial coordinate values.
     */
    public SimpleDirectPosition(final Point2D point) {
        coordinates = new double[] {
            point.getX(),
            point.getY()
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDimension() {
        return coordinates.length;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double[] getCoordinates() {
        return coordinates.clone();
    }

    /**
     * Sets all coordinate values. The array length must be equal to the number of dimensions.
     *
     * @param  coordinates  the coordinate values for a single point.
     *
     * @since 3.1
     */
    public void setCoordinate(final double... coordinates) {
        assertFalse(unmodifiable, "This DirectPosition shall not be modified.");
        assertEquals(this.coordinates.length, coordinates.length, "Unexpected number of dimensions.");
        System.arraycopy(coordinates, 0, this.coordinates, 0, coordinates.length);
    }

    /**
     * Sets all coordinate values starting at the given offset in the given array.
     * This method is for internal usage by {@link TransformTestCase} only.
     *
     * @param coordinates  the {@code float[]} or {@code double[]} array.
     * @param offset       index of the first element to copy from the given array.
     * @param useDouble    if {@code false}, cast the values to floats.
     */
    final void setCoordinate(final Object coordinates, int offset, final boolean useDouble) {
        assertFalse(unmodifiable, "This DirectPosition shall not be modified.");
        final int dimension = this.coordinates.length;
        for (int i=0; i<dimension; i++) {
            double coordinate = Array.getDouble(coordinates, offset++);
            if (!useDouble) {
                coordinate = (float) coordinate;
            }
            this.coordinates[i] = coordinate;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getCoordinate(int dimension) throws IndexOutOfBoundsException {
        return coordinates[dimension];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCoordinate(int dimension, double value) throws IndexOutOfBoundsException {
        assertFalse(unmodifiable, "This DirectPosition shall not be modified.");
        coordinates[dimension] = value;
    }

    /**
     * Returns {@code true} if this direct position is equal to the given object.
     */
    @Override
    public boolean equals(final Object object) {
        if (object instanceof DirectPosition) {
            final DirectPosition other = (DirectPosition) object;
            if (other.getCoordinateReferenceSystem() == null) {
                return Arrays.equals(coordinates, other.getCoordinates());
            }
        }
        return false;
    }

    /**
     * Returns a hash code value for this direct position.
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(coordinates);
    }

    /**
     * Returns the <i>Well Known Test</i> (WKT) representation of this direct position.
     */
    @Override
    public String toString() {
        boolean castToFloats = true;
        for (final double coordinate : coordinates) {
            if (Double.doubleToLongBits(coordinate) != Double.doubleToLongBits((float) coordinate)) {
                castToFloats = false;
                break;
            }
        }
        final StringBuilder buffer = new StringBuilder("POINT(");
        for (int i=0; i<coordinates.length; i++) {
            if (i != 0) {
                buffer.append(' ');
            }
            final double coordinate = coordinates[i];
            if (castToFloats) {
                buffer.append((float) coordinate);
            } else {
                buffer.append(coordinate);
            }
        }
        return buffer.append(')').toString();
    }
}
