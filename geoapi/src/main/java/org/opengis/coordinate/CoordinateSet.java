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

import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.opengis.geometry.DirectPosition;
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
     */
    default int getDimension() {
        // All methods invoked below are for attributes declared as mandatory. Values shall not be null.
        return getCoordinateMetadata().getCoordinateReferenceSystem().getCoordinateSystem().getDimension();
    }

    /**
     * Returns the positions described by coordinate tuples.
     *
     * @return position described by coordinate tuples.
     */
    @Override
    @UML(identifier="coordinateTuple", obligation=MANDATORY, specification=ISO_19111)
    Iterator<DirectPosition> iterator();

    /**
     * Returns a stream of coordinate tuples.
     * Whether the stream is sequential or parallel is implementation dependent.
     * The default implementation creates a sequential stream.
     *
     * @return a sequential or parallel stream of coordinate tuples.
     */
    default Stream<DirectPosition> stream() {
        return StreamSupport.stream(spliterator(), false);
    }
}
