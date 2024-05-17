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

import java.util.Optional;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;
import org.opengis.geometry.DirectPosition;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A collection of coordinate tuples referenced to the same coordinate reference system (<abbr>CRS</abbr>).
 * If the <abbr>CRS</abbr> is dynamic, the {@code CoordinateSet} metadata contains also a coordinate epoch.
 * Operations on the geometry of the tuples within the coordinate set are valid only if all
 * tuples are referenced to the same coordinate epoch.
 *
 * <h2>Coordinate operations</h2>
 * Coordinate sets referenced to one <abbr>CRS</abbr> may be referenced to another <abbr>CRS</abbr> through the
 * application of a {@linkplain org.opengis.referencing.operation.CoordinateOperation coordinate operation}.
 * If the <abbr>CRS</abbr> is dynamic, the {@code CoordinateSet} may be converted to another coordinate epoch
 * through a point motion coordinate operation that includes time evolution.
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 *
 * @see org.opengis.referencing.operation.CoordinateOperation#transform(CoordinateSet)
 *
 * @since 3.1
 */
@UML(identifier="CoordinateSet", specification=ISO_19111)
public interface CoordinateSet extends Iterable<DirectPosition> {
    /**
     * Coordinate metadata to which this coordinate set is referenced. Coordinate metadata includes a
     * {@linkplain org.opengis.referencing.crs.CoordinateReferenceSystem coordinate reference system}
     * (<abbr>CRS</abbr>) and, if the <abbr>CRS</abbr> is dynamic, a coordinate epoch.
     *
     * @return coordinate metadata to which this coordinate set is referenced.
     */
    @UML(identifier="coordinateMetadata", obligation=MANDATORY, specification=ISO_19111)
    CoordinateMetadata getCoordinateMetadata();

    /**
     * Returns the number of dimensions of coordinate tuples. This is determined by the
     * {@linkplain CoordinateMetadata#getCoordinateReferenceSystem() coordinate reference system}.
     *
     * @return the number of dimensions of coordinate tuples.
     *
     * @departure easeOfUse
     *   This shortcut has been added because this is a frequently used information.
     */
    default int getDimension() {
        // All methods invoked below are for attributes declared as mandatory. Values shall not be null.
        return getCoordinateMetadata().getCoordinateReferenceSystem().getCoordinateSystem().getDimension();
    }

    /**
     * Returns an iterator over the positions described by coordinate tuples.
     * The positions shall be in a well-defined encounter order.
     * For each element, the following constraints shall be met:
     *
     * <ul>
     *   <li>{@link DirectPosition#getDimension()} is equal to {@link #getDimension()}.</li>
     *   <li>{@link DirectPosition#getCoordinateReferenceSystem()} is null or equal to
     *       {@link CoordinateMetadata#getCoordinateReferenceSystem()}.</li>
     * </ul>
     *
     * Invoking this method is equivalent to invoking {@code stream().iterator()}.
     *
     * @return a new iterator over the positions described by coordinate tuples.
     */
    @Override
    @UML(identifier="coordinateTuple", obligation=MANDATORY, specification=ISO_19111)
    Iterator<DirectPosition> iterator();

    /**
     * Returns a sequential stream of coordinate tuples.
     * The positions shall be in a well-defined encounter order
     * (i.e., positions are {@linkplain Spliterator#ORDERED ordered}).
     *
     * <h4>Default implementation</h4>
     * If {@link #asDoubleBuffers()} or {@link #asFloatBuffers()} (in that preference order) returns
     * a non-empty value, then the default method returns a stream of views over the buffers content.
     * Otherwise, the default method returns a stream backed by {@link #iterator()}.
     *
     * @return a sequential stream of coordinate tuples.
     *
     * @departure integration
     *   Added for allowing developers to process coordinate tuples efficiently in Java environments.
     *   The use of Java streams makes parallel processing easier.
     */
    default Stream<DirectPosition> stream() {
        final Optional<Stream<DoubleBuffer>> asDoubleBuffers = asDoubleBuffers();
        if (asDoubleBuffers.isPresent()) {
            final var src = asDoubleBuffers.get();
            final int dim = getDimension();
            final var crs = getCoordinateMetadata().getCoordinateReferenceSystem();
            return src.flatMap((buffer) -> StreamSupport.stream(new BufferToPoint.Doubles(crs, dim, buffer), src.isParallel()));
        }
        final Optional<Stream<FloatBuffer>> asFloatBuffers = asFloatBuffers();
        if (asFloatBuffers.isPresent()) {
            final var src = asFloatBuffers.get();
            final int dim = getDimension();
            final var crs = getCoordinateMetadata().getCoordinateReferenceSystem();
            return src.flatMap((buffer) -> StreamSupport.stream(new BufferToPoint.Floats(crs, dim, buffer), src.isParallel()));
        }
        return StreamSupport.stream(spliterator(), false);
    }

    /**
     * If the coordinates are packed in sequences of double-precision floating point values,
     * returns views over those sequences. For example, if the number of dimensions is 3,
     * then the coordinates can be packed in this order:
     *
     * (<var>x₀</var>,<var>y₀</var>,<var>z₀</var>,
     *  <var>x₁</var>,<var>y₁</var>,<var>z₁</var> …).
     *
     * <p>The coordinates may be packed in a single buffer, or may be partitioned in many buffers.
     * For each buffer, the number of coordinate tuples is {@link DoubleBuffer#remaining()} / {@link #getDimension()}.
     * Each buffer may have a different number of coordinate tuples. It shall be safe to modify the position, limit or
     * mark of buffer instances (see example below). Each {@link DoubleBuffer} instance should be a <em>view</em> over
     * an underlying coordinate array, those arrays should not be copied.</p>
     *
     * <h4>Examples</h4>
     * For an implementation with all coordinates packed in a single array of {@code double} primitive values:
     *
     * {@snippet lang="java" :
     * private final double[] coordinates = ...;
     *
     * public Optional<Stream<DoubleBuffer>> asDoubleBuffers() {
     *     return Optional.of(Stream.of(DoubleBuffer.wrap(coordinates)));
     * }
     * }
     *
     * For an implementation with all coordinates partitioned in an array of {@link DoubleBuffer} wrappers.
     * Note the call to {@link DoubleBuffer#duplicate()} for protecting the internal buffers from changes:
     *
     * {@snippet lang="java" :
     * private final DoubleBuffer[] buffers = ...;
     *
     * public Optional<Stream<DoubleBuffer>> asDoubleBuffers() {
     *     return Optional.of(Arrays.stream(buffers).map(DoubleBuffer::duplicate));
     * }
     * }
     *
     * <h4><abbr>API</abbr> notes</h4>
     * The use of {@link DoubleBuffer} makes possible to handle coordinate values not only from a Java array,
     * but also from {@linkplain DoubleBuffer#slice() a sub-array}, from {@linkplain java.nio.MappedByteBuffer
     * a region of a file} or from the {@link java.lang.foreign.MemorySegment#asByteBuffer() memory of a native
     * application}.
     *
     * <p>Empty {@code Optional} and empty {@code Stream} are not synonymous.
     * An empty optional means that this {@code CoordinateSet} is not backed by sequences of double-precision values,
     * or that those values are not packed in the way described above, or are not accessible for any reason.
     * An empty stream means that the coordinates are accessible by this method, but there is none
     * (i.e., this {@code CoordinateSet} is empty).</p>
     *
     * <p>At most one of {@code asDoubleBuffers()} and {@link #asFloatBuffers()} should return a non-empty value,
     * because a {@code CoordinateSet} may be backed by single- or double-precision floating point values,
     * but usually not both in same time.</p>
     *
     * @departure integration
     *   Added for allowing developers to access coordinate tuples efficiently in Java environments,
     *   including the case where the coordinate values are in the memory of a native application.
     *
     * @return a view over the sequences of coordinates as double-precision floating point values.
     *
     * @see #stream()
     * @see MathTransform#transform(DoubleBuffer, DoubleBuffer)
     */
    default Optional<Stream<DoubleBuffer>> asDoubleBuffers() {
        return Optional.empty();
    }

    /**
     * If the coordinates are packed in sequences of single-precision floating point values, returns views
     * over those sequences. This method contract is the same as {@link #asDoubleBuffers()}, with only the
     * {@code float} type instead of {@code double}.
     *
     * <p>At most one of {@code asFloatBuffers()} and {@link #asDoubleBuffers()} should return a non-empty value,
     * because a {@code CoordinateSet} may be backed by single- or double-precision floating point values,
     * but usually not both in same time.</p>
     *
     * @departure integration
     *   Added for allowing developers to access coordinate tuples efficiently in Java environments,
     *   including the case where the coordinate values are in the memory of a native application.
     *
     * @return a view over the sequences of coordinates as single-precision floating point values.
     *
     * @see #stream()
     */
    default Optional<Stream<FloatBuffer>> asFloatBuffers() {
        return Optional.empty();
    }
}
