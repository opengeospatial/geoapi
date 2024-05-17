/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2024 Open Geospatial Consortium, Inc.
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
package org.opengis.coordinate;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;
import java.util.Arrays;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;
import org.opengis.geometry.DirectPosition;
import org.opengis.referencing.crs.CoordinateReferenceSystem;


/**
 * Produces {@code DirectPosition} views on a buffer of floating point values.
 * This is used for producing a stream of direct positions.
 *
 * @author  Martin Desruisseaux (Geomatys)
 */
abstract class BufferToPoint implements Spliterator<DirectPosition> {
    /**
     * The coordinate reference system of the {@code DirectPosition} instances.
     * Should not be {@code null} when {@code BufferToPoint} is instantiated from
     * a {@link CoordinateSet}, but this class is nevertheless tolerant to null.
     */
    protected final CoordinateReferenceSystem crs;

    /**
     * Number of dimensions of coordinate tuples.
     */
    protected final int dimension;

    /**
     * Creates a new instance for the given number of dimensions.
     *
     * @param crs        the coordinate reference system of the {@code DirectPosition} instances.
     * @param dimension  number of dimensions of coordinate tuples.
     */
    protected BufferToPoint(final CoordinateReferenceSystem crs, final int dimension) {
        this.crs = crs;
        this.dimension = dimension;
    }

    /**
     * Creates a new instance with a duplicated buffer.
     * This method is invoked for partitioning the iteration.
     */
    protected abstract BufferToPoint duplicate();

    /**
     * Returns the buffer of floating point values.
     */
    protected abstract Buffer buffer();

    /**
     * Creates a new position for the coordinate tuple starting at the given offset.
     */
    protected abstract DirectPosition position(int offset);

    /**
     * Declares that the number of elements is known, all elements are non-null and there is a defined encounter order.
     * Furthermore, if the buffer of floating point values is read-only, then the elements are immutable.
     */
    @Override
    public final int characteristics() {
        return buffer().isReadOnly()
                ? (ORDERED | SIZED | SUBSIZED | NONNULL | IMMUTABLE)
                : (ORDERED | SIZED | SUBSIZED | NONNULL);
    }

    /**
     * Returns the number of coordinate tuples.
     */
    @Override
    public final long estimateSize() {
        return buffer().remaining() / dimension;
    }

    /**
     * Returns a new instance covering a prefix of the points,
     * then updates this instance for covering the remaining points.
     * If the iteration cannot be partitioned, returns {@code null}.
     */
    @Override
    public final Spliterator<DirectPosition> trySplit() {
        final Buffer buffer = buffer();
        int splitAt = buffer.remaining() / (2 * dimension);
        if (splitAt >= 1) {
            splitAt = splitAt * dimension + buffer.position();
            final BufferToPoint prefix = duplicate();
            prefix.buffer().limit(splitAt);
            buffer.position(splitAt);
            return prefix;
        }
        return null;
    }

    /**
     * If a remaining element exists, performs the given action on it.
     */
    @Override
    public final boolean tryAdvance(final Consumer<? super DirectPosition> action) {
        final Buffer buffer = buffer();
        int lower = buffer.position();
        int upper = lower + dimension;
        if (upper <= buffer.limit()) {
            action.accept(position(lower));
            buffer.position(upper);
            return true;
        }
        return false;
    }

    /**
     * Performs the given action for each remaining element sequentially.
     */
    @Override
    public final void forEachRemaining(final Consumer<? super DirectPosition> action) {
        final Buffer buffer = buffer();
        int lower = buffer.position();
        int upper = lower;
        while ((upper += dimension) <= buffer.limit()) {
            action.accept(position(lower));
            lower = upper;
        }
        buffer.position(lower);
    }

    /**
     * Base class of direct positions that are views over a buffer content.
     */
    static abstract class Position implements DirectPosition {
        /**
         * Creates a new direct position.
         */
        protected Position() {
        }

        /**
         * Appends a coordinate value in the given buffer with only
         * the number of fraction digits needed for the value type.
         */
        protected abstract void appendTo(StringBuilder sb, int i);

        /**
         * Returns a string representation of this position.
         */
        @Override public final String toString() {
            final var sb = new StringBuilder("POINT(");
            final int dimension = getDimension();
            for (int i=0; i<dimension; i++) {
                if (i != 0) sb.append(' ');
                appendTo(sb, i);
            }
            return sb.append(')').toString();
        }

        /**
         * Returns a hash code value for this direct position.
         * The contract is defined by {@link DirectPosition#hashCode()}.
         */
        @Override public final int hashCode() {
            return Arrays.hashCode(getCoordinates()) + Objects.hashCode(getCoordinateReferenceSystem());
        }

        /**
         * Compares the given object with this position for equality.
         * The contract is defined by {@link DirectPosition#equals(Object)}.
         */
        @Override public final boolean equals(final Object obj) {
            if (obj instanceof DirectPosition) {
                var other = (DirectPosition) obj;
                return Arrays.equals(getCoordinates(), other.getCoordinates()) &&
                        Objects.equals(getCoordinateReferenceSystem(), other.getCoordinateReferenceSystem());
            }
            return false;
        }
    }

    /**
     * Wrapper for a buffer of single-precision floating point values.
     */
    static final class Floats extends BufferToPoint {
        /** The buffer of packed floating point values. */
        private final FloatBuffer buffer;

        /** Creates a new instance for the given number of dimensions and buffer. */
        Floats(CoordinateReferenceSystem crs, int dimension, FloatBuffer buffer) {
            super(crs, dimension);
            this.buffer = buffer;
        }

        /** Creates a new instance with a duplicated buffer. */
        @Override protected BufferToPoint duplicate() {
            return new Floats(crs, dimension, buffer.duplicate());
        }

        /** Returns the buffer of floating point values. */
        @Override protected Buffer buffer() {
            return buffer;
        }

        /** Creates a new position for the coordinate tuple starting at the given offset. */
        @Override protected DirectPosition position(final int offset) {
            return new Position() {
                /** Returns the <abbr>CRS</abrr> of this direct position. */
                @Override public CoordinateReferenceSystem getCoordinateReferenceSystem() {
                    return crs;
                }

                /** Returns the number of dimensions of this direct position. */
                @Override public int getDimension() {
                    return dimension;
                }

                /** Appends a coordinate value in the given buffer. */
                @Override protected void appendTo(final StringBuilder sb, final int i) {
                    sb.append(buffer.get(offset + i));
                }

                /** Returns a copy of the coordinates stored as an array of double values. */
                @Override public double[] getCoordinates() {
                    final double[] coordinates = new double[dimension];
                    for (int i=0; i<coordinates.length; i++) {
                        coordinates[i] = buffer.get(offset + i);
                    }
                    return coordinates;
                }

                /** Returns the coordinate value for the given dimension. */
                @Override public double getCoordinate(final int i) {
                    if (i >= 0 && i < dimension) {
                        return buffer.get(offset + i);
                    } else {
                        throw new IndexOutOfBoundsException(i);
                    }
                }

                /** Sets the coordinate value for the given dimension if this buffer is not read-only. */
                @Override public void setCoordinate(final int i, final double value) {
                    if (i >= 0 && i < dimension) {
                        buffer.put(offset + i, (float) value);
                    } else {
                        throw new IndexOutOfBoundsException(i);
                    }
                }
            };
        }
    }

    /**
     * Wrapper for a buffer of double-precision floating point values.
     */
    static final class Doubles extends BufferToPoint {
        /** The buffer of packed floating point values. */
        private final DoubleBuffer buffer;

        /** Creates a new instance for the given number of dimensions and buffer. */
        Doubles(CoordinateReferenceSystem crs, int dimension, DoubleBuffer buffer) {
            super(crs, dimension);
            this.buffer = buffer;
        }

        /** Creates a new instance with a duplicated buffer. */
        @Override protected BufferToPoint duplicate() {
            return new Doubles(crs, dimension, buffer.duplicate());
        }

        /** Returns the buffer of floating point values. */
        @Override protected Buffer buffer() {
            return buffer;
        }

        /** Creates a new position for the coordinate tuple starting at the given offset. */
        @Override protected DirectPosition position(final int offset) {
            return new Position() {
                /** Returns the <abbr>CRS</abrr> of this direct position. */
                @Override public CoordinateReferenceSystem getCoordinateReferenceSystem() {
                    return crs;
                }

                /** Returns the number of dimensions of this direct position. */
                @Override public int getDimension() {
                    return dimension;
                }

                /** Appends a coordinate value in the given buffer. */
                @Override protected void appendTo(final StringBuilder sb, final int i) {
                    sb.append(buffer.get(offset + i));
                }

                /** Returns a copy of the coordinates stored as an array of double values. */
                @Override public double[] getCoordinates() {
                    final double[] coordinates = new double[dimension];
                    // TODO: use `buffer.get(int, double[], int, int)` instead on JDK13.
                    for (int i=0; i<coordinates.length; i++) {
                        coordinates[i] = buffer.get(offset + i);
                    }
                    return coordinates;
                }

                /** Returns the coordinate value for the given dimension. */
                @Override public double getCoordinate(final int i) {
                    if (i >= 0 && i < dimension) {
                        return buffer.get(offset + i);
                    } else {
                        throw new IndexOutOfBoundsException(i);
                    }
                }

                /** Sets the coordinate value for the given dimension if this buffer is not read-only. */
                @Override public void setCoordinate(final int i, final double value) {
                    if (i >= 0 && i < dimension) {
                        buffer.put(offset + i, value);
                    } else {
                        throw new IndexOutOfBoundsException(i);
                    }
                }
            };
        }
    }
}
