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

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.IntToDoubleFunction;
import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;
import org.opengis.geometry.DirectPosition;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests {@link CoordinateSetTest} bridge between package coordinate values and direct positions.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
public final class CoordinateSetTest implements CoordinateSet, CoordinateMetadata {
    /**
     * The stream to test, or {@code null} if none.
     */
    private Stream<DoubleBuffer> asDoubleBuffers;

    /**
     * The stream to test, or {@code null} if none.
     */
    private Stream<FloatBuffer> asFloatBuffers;

    /**
     * Number of dimensions for coordinate tuples.
     */
    private int dimension;

    /**
     * Index of the coordinate tuple being verified.
     */
    private int index;

    /**
     * The previous point during iteration.
     */
    private DirectPosition previous;

    /**
     * Creates a new test case.
     */
    public CoordinateSetTest() {
    }

    /**
     * Returns dummmy metadata.
     *
     * @return {@code this}.
     */
    @Override
    public CoordinateMetadata getCoordinateMetadata() {
        return this;
    }

    /**
     * Not needed for this test.
     *
     * @return {@code null}.
     */
    @Override
    public CoordinateReferenceSystem getCoordinateReferenceSystem() {
        return null;
    }

    /**
     * Returns the number of dimensions of the coordinate tuples to test.
     * Must be defined since {@link #getCoordinateReferenceSystem()} returns null.
     */
    @Override
    public int getDimension() {
        return dimension;
    }

    /**
     * Returns the stream used for the test, if any.
     */
    @Override
    public Optional<Stream<DoubleBuffer>> asDoubleBuffers() {
        return Optional.ofNullable(asDoubleBuffers);
    }

    /**
     * Returns the stream used for the test, if any.
     */
    @Override
    public Optional<Stream<FloatBuffer>> asFloatBuffers() {
        return Optional.ofNullable(asFloatBuffers);
    }

    /**
     * Returns an empty iterator.
     */
    @Override
    public Iterator<DirectPosition> iterator() {
        return Collections.emptyIterator();
    }

    /**
     * Tests the default path when there is no stream of buffers.
     */
    @Test
    public void testIterator() {
        assertTrue(stream().findAny().isEmpty());
    }

    /**
     * Tests with single-precision floating point values.
     */
    @Test
    public void testFloats() {
        dimension = 5;
        final var coordinates = new float[dimension * 200];
        for (int i=0; i<coordinates.length; i++) {
            // Pattern is "1XXY" where "XX" is point index and "Y" is dimension.
            coordinates[i] = 1000 + (i / dimension) * 10 + (i % dimension);
        }
        int lower, upper;
        final FloatBuffer[] buffers = {
            FloatBuffer.wrap(coordinates, lower = 0,     (upper =  34*dimension) - lower),
            FloatBuffer.wrap(coordinates, lower = upper, (upper = 113*dimension) - lower),
            FloatBuffer.wrap(coordinates, lower = upper, (upper = 178*dimension) - lower),
            FloatBuffer.wrap(coordinates, lower = upper, (coordinates.length)    - lower)
        };
        asFloatBuffers = Arrays.stream(buffers).map(FloatBuffer::duplicate);
        /*
         * At this point, the data have been initialized. Now test.
         */
        final Set<DirectPosition> positions = testStream((i) -> coordinates[i]);
        assertEquals(coordinates.length, index);
        assertEquals(positions.size(), coordinates.length / dimension);
        assertEquals("POINT(2990.0 2991.0 2992.0 2993.0 2994.0)", previous.toString());

        asFloatBuffers = Arrays.stream(buffers).map(FloatBuffer::duplicate);
        testParallelExecution(positions);
    }

    /**
     * Tests with double-precision floating point values.
     */
    @Test
    public void testDoubles() {
        dimension = 3;
        final var coordinates = new double[dimension * 100];
        for (int i=0; i<coordinates.length; i++) {
            // Pattern is "1XXY" where "XX" is point index and "Y" is dimension.
            coordinates[i] = 1000 + (i / dimension) * 10 + (i % dimension);
        }
        int lower, upper;
        final DoubleBuffer[] buffers = {
            DoubleBuffer.wrap(coordinates, lower = 0,     (upper = 13*dimension) - lower),
            DoubleBuffer.wrap(coordinates, lower = upper, (upper = 47*dimension) - lower),
            DoubleBuffer.wrap(coordinates, lower = upper, (upper = 79*dimension) - lower),
            DoubleBuffer.wrap(coordinates, lower = upper, (coordinates.length)   - lower)
        };
        asDoubleBuffers = Arrays.stream(buffers).map(DoubleBuffer::duplicate);
        /*
         * At this point, the data have been initialized. Now test.
         */
        final Set<DirectPosition> positions = testStream((i) -> coordinates[i]);
        assertEquals(coordinates.length, index);
        assertEquals(positions.size(), coordinates.length / dimension);
        assertEquals("POINT(1990.0 1991.0 1992.0)", previous.toString());

        asDoubleBuffers = Arrays.stream(buffers).map(DoubleBuffer::duplicate);
        testParallelExecution(positions);
    }

    /**
     * Compares {@link CoordinateSet#stream()} against expected values.
     *
     * @param  getter  provider of expected coordinate values for a given index.
     * @return all positions created by the stream.
     */
    private Set<DirectPosition> testStream(final IntToDoubleFunction getter) {
        final Set<DirectPosition> positions = new HashSet<>(250);
        stream().forEach((position) -> {
            @SuppressWarnings("LocalVariableHidesMemberVariable")
            final int dimension = CoordinateSetTest.this.dimension;
            assertEquals(dimension, position.getDimension());
            final double[] copy = position.getCoordinates();
            for (int i=0; i<dimension; i++) {
                final double expected = getter.applyAsDouble(index++);
                assertEquals(expected, position.getCoordinate(i));
                assertEquals(expected, copy[i]);
            }
            assertEquals(position, position);
            assertNotEquals(previous, position);
            if (previous != null) {
                assertNotEquals(previous.hashCode(), position.hashCode());
            }
            assertTrue(positions.add(position));
            previous = position;
        });
        return positions;
    }

    /**
     * Executes the the stream in parallel and compares with the expected positions.
     *
     * @param  positions  the expected positions.
     */
    private void testParallelExecution(final Set<DirectPosition> positions) {
        final Set<DirectPosition> c = Collections.newSetFromMap(new ConcurrentHashMap<>());
        assertTrue(c.addAll(positions));
        assertTrue(c.equals(positions));
        stream().parallel().forEach((position) -> assertTrue(c.remove(position), () -> "Not found: " + position));
        assertTrue(c.isEmpty(), () -> "Unexpected elements: " + c);
    }
}
