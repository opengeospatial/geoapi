/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2003-2024 Open Geospatial Consortium, Inc.
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
import java.time.temporal.Temporal;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Change of coordinate values within one <abbr>CRS</abbr> due to the motion of the point between two coordinate epochs.
 * The motion is typically due to tectonic plate movement or deformation.
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 * @since   3.1
 */
@UML(identifier="PointMotionOperation", specification=ISO_19111)
public interface PointMotionOperation extends SingleOperation {
    /**
     * Returns the <abbr>CRS</abbr> from which coordinates are changed.
     * This attribute is mandatory in all point motion operations.
     * It shall be the same as the {@linkplain #getTargetCRS() target <abbr>CRS</abbr>}.
     *
     * @return the <abbr>CRS</abbr> from which coordinates are changed. Shall not be {@code null}.
     */
    @Override
    @UML(identifier="sourceCRS", obligation=MANDATORY, specification=ISO_19111)
    CoordinateReferenceSystem getSourceCRS();

    /**
     * Returns the <abbr>CRS</abbr> to which coordinates are changed.
     * This attribute is mandatory in all point motion operations.
     * It shall be the same as the {@linkplain #getSourceCRS() source <abbr>CRS</abbr>}.
     *
     * <p>The default implementation returns {@link #getSourceCRS()}.</p>
     *
     * @return the <abbr>CRS</abbr> to which coordinates are changed. Shall not be {@code null}.
     */
    @Override
    @UML(identifier="targetCRS", obligation=MANDATORY, specification=ISO_19111)
    default CoordinateReferenceSystem getTargetCRS() {
        return getSourceCRS();
    }

    /**
     * Returns the date at which source coordinate tuples are valid.
     * This is mandatory for point motion operations.
     *
     * @return epoch at which source coordinate tuples are valid. Shall not be empty.
     */
    @Override
    @UML(identifier="sourceCoordinateEpoch", obligation=MANDATORY, specification=ISO_19111)
    Optional<Temporal> getSourceEpoch();

    /**
     * Returns the date at which target coordinate tuples are valid.
     * This is mandatory for point motion operations.
     *
     * @return epoch at which target coordinate tuples are valid. Shall not be empty.
     */
    @Override
    @UML(identifier="targetCoordinateEpoch", obligation=MANDATORY, specification=ISO_19111)
    Optional<Temporal> getTargetEpoch();

    /**
     * Returns the version of this point motion operation.
     * The version is an identification of the instantiation due to the stochastic nature of the parameters.
     * This attribute is mandatory in all point motion operations.
     *
     * @return version of the point motion operation. Shall not be empty.
     */
    @Override
    @UML(identifier="operationVersion", obligation=MANDATORY, specification=ISO_19111)
    Optional<String> getOperationVersion();
}
