/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2003-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.referencing.operation;

import java.util.Optional;
import java.util.Collection;
import java.time.temporal.Temporal;
import org.opengis.metadata.quality.PositionalAccuracy;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.ObjectDomain;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * An operation specifying a subset of a coordinate tuple subjected to a specific coordinate operation.
 * Coordinates in the coordinate tuple other than the subset remain unchanged.
 * For example, it may be required to transform only the horizontal or only the vertical component of
 * a compound three-dimensional coordinate reference system (<abbr>CRS</abbr>).
 * This operation specifies what subset of a coordinate tuple is subject to a requested operation.
 * It takes the form of referencing another coordinate operation and specifying a sequence of numbers
 * defining the positions in the coordinate tuple of the coordinates affected by that operation.
 * The order of the coordinates in a coordinate tuple shall agree with the order of the coordinate system
 * axes as defined for the associated coordinate system.
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 4.0
 * @since   1.0
 */
@UML(identifier="PassThroughOperation", specification=ISO_19111)
public interface PassThroughOperation extends CoordinateOperation {
    /**
     * Returns the operation to apply on the subset of a coordinate tuple.
     *
     * @return the operation to apply on the subset of a coordinate tuple.
     */
    @UML(identifier="coordOperation", obligation=MANDATORY, specification=ISO_19111)
    CoordinateOperation getOperation();

    /**
     * Returns the positions in a source coordinate tuple of the coordinates affected by this pass-through operation.
     * Values 0 identifies the first source coordinate, value 1 identifies the second source coordinate, <i>etc.</i>
     * This is an ordered sequence: coordinates will be given to the {@linkplain #getOperation() operation}
     * in the same order as the indices returned by this method.
     *
     * @return zero-based indices of the modified source coordinates.
     */
    @UML(identifier="modifiedCoordinate", obligation=MANDATORY, specification=ISO_19111)
    int[] getModifiedCoordinates();

    /**
     * Returns the <abbr>CRS</abbr> to be used for interpolations in a grid.
     * By default, this is the interpolation <abbr>CRS</abbr> of the {@linkplain #getOperation() operation}.
     *
     * @since 3.1
     */
    @Override
    @UML(identifier="interpolationCRS", obligation=OPTIONAL, specification=ISO_19111)
    default Optional<CoordinateReferenceSystem> getInterpolationCRS() {
        return getOperation().getInterpolationCRS();
    }

    /**
     * Returns the date at which source coordinate tuples are valid.
     * By default, this is the source epoch of the {@linkplain #getOperation() operation}.
     *
     * @since 3.1
     */
    @Override
    @UML(identifier="sourceCoordinateEpoch", obligation=CONDITIONAL, specification=ISO_19111)
    default Optional<Temporal> getSourceEpoch() {
        return getOperation().getSourceEpoch();
    }

    /**
     * Returns the date at which target coordinate tuples are valid.
     * By default, this is the target epoch of the {@linkplain #getOperation() operation}.
     *
     * @since 3.1
     */
    @Override
    @UML(identifier="targetCoordinateEpoch", obligation=CONDITIONAL, specification=ISO_19111)
    default Optional<Temporal> getTargetEpoch() {
        return getOperation().getTargetEpoch();
    }

    /**
     * Returns the usage of this <abbr>CRS</abbr>-related object.
     * By default, this is the domain of the {@linkplain #getOperation() operation}.
     *
     * @since 3.1
     */
    @Override
    @UML(identifier="ObjectUsage.domain", obligation=OPTIONAL, specification=ISO_19111)
    default Collection<ObjectDomain> getDomains() {
        return getOperation().getDomains();
    }

    /**
     * Returns estimate(s) of the impact of this operation on point accuracy.
     * By default, this is the accuracy of the {@linkplain #getOperation() operation}.
     */
    @Override
    @UML(identifier="coordinateOperationAccuracy", obligation=OPTIONAL, specification=ISO_19111)
    default Collection<PositionalAccuracy> getCoordinateOperationAccuracy() {
        return getOperation().getCoordinateOperationAccuracy();
    }
}
