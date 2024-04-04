/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    This file is hereby placed into the Public Domain.
 *    This means anyone is free to do whatever they wish with this file.
 */
package org.opengis.example.referencing;

import java.util.Arrays;
import java.util.Objects;
import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Stream;
import java.time.temporal.Temporal;
import org.opengis.geometry.DirectPosition;
import org.opengis.coordinate.CoordinateSet;
import org.opengis.coordinate.CoordinateMetadata;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.CoordinateOperation;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;


/**
 * The result of transforming coordinate tuples using the math transform of a given coordinate operation.
 * This class is used for the default implementation of {@link SimpleTransform#transform(CoordinateSet)}.
 * This default implementation is okay for small set but should not be used for large ones.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
final class TransformedCoordinateSet implements CoordinateSet, CoordinateMetadata {
    /**
     * The <abbr>CRS</abbr> of the transformed coordinate tuples.
     *
     * @see #getCoordinateReferenceSystem()
     */
    private final CoordinateReferenceSystem crs;

    /**
     * Date at which coordinate tuples are valid, or {@code null} if the CRS is not dynamic.
     *
     * @see #getCoordinateEpoch()
     */
    private final Temporal epoch;

    /**
     * The transformed coordinate tuples.
     *
     * @see #iterator()
     * @see #stream()
     */
    private final DirectPosition[] positions;

    /**
     * Creates a new transformed coordinate set.
     *
     * @param  operation  the coordinate operation to apply.
     * @param  data       the coordinate tuples to transform.
     * @throws TransformException if the data cannot be changed.
     */
    TransformedCoordinateSet(final CoordinateOperation operation, final CoordinateSet data) throws TransformException {
        final CoordinateMetadata md = data.getCoordinateMetadata();
        crs = Objects.requireNonNull(operation.getTargetCRS(), "Missing target CRS.");
        if (!crs.equals(md.getCoordinateReferenceSystem())) {
            throw new TransformException("Unexpected data CRS.");
        }
        epoch = operation.getTargetEpoch().orElse(null);
        if (!Objects.equals(epoch, md.getCoordinateEpoch())) {
            throw new TransformException("Missing or unexpected data epoch.");
        }
        final MathTransform mt = operation.getMathTransform();
        try {
            positions = data.stream().sequential().map((p) -> {
                try {
                    return mt.transform(p, null);
                } catch (TransformException e) {
                    throw new RuntimeException(e);      // Checked exception not allowed here.
                }
            }).toArray(DirectPosition[]::new);
        } catch (RuntimeException e) {
            final Throwable cause = e.getCause();
            if (cause instanceof TransformException) {
                throw (TransformException) cause;       // The checked exception that we couldn't throw in above lambda.
            }
            throw e;
        }
    }

    /**
     * Returns the coordinate metadata to which this coordinate set is referenced.
     */
    @Override
    public CoordinateMetadata getCoordinateMetadata() {
        return this;
    }

    /**
     * Returns the <abbr>CRS</abbr> in which the coordinate tuples are given.
     */
    @Override
    public CoordinateReferenceSystem getCoordinateReferenceSystem() {
        return crs;
    }

    /**
     * Returns the date at which coordinate tuples referenced to a dynamic <abbr>CRS</abbr> are valid.
     */
    @Override
    public Optional<Temporal> getCoordinateEpoch() {
        return Optional.ofNullable(epoch);
    }

    /**
     * Returns the transformed positions described by coordinate tuples.
     */
    @Override
    public Iterator<DirectPosition> iterator() {
        return Arrays.asList(positions).iterator();
    }

    /**
     * Returns a stream of transformed coordinate tuples.
     */
    @Override
    public Stream<DirectPosition> stream() {
        return Arrays.stream(positions);
    }
}
