/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2023 Open Geospatial Consortium, Inc.
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

import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A pass-through operation specifies that a subset of a coordinate tuple is subject to a specific
 * coordinate operation.
 *
 * <div class="warning"><b>Upcoming API change</b><br>
 * This interface is conformant to ISO 19111:2003. But the ISO 19111:2007 revision changed the parent
 * interface from {@code SingleOperation} to {@link CoordinateOperation}. This change may be applied
 * in GeoAPI 4.0.
 * </div>
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 */
@UML(identifier="CC_PassThroughOperation", specification=ISO_19111)
public interface PassThroughOperation extends SingleOperation {
    /**
     * Returns the operation to apply on the subset of a coordinate tuple.
     *
     * <div class="warning"><b>Upcoming API change</b><br>
     * This method is conformant to ISO 19111:2003. But the ISO 19111:2007 revision changed the type from
     * {@code SingleOperation} to {@link CoordinateOperation}. This change may be applied in GeoAPI 4.0.
     * This is necessary for supporting usage of {@code PassThroughOperation} with {@link ConcatenatedOperation}.
     * </div>
     *
     * @return the operation to apply on the subset of a coordinate tuple.
     */
    @UML(identifier="coordOperation", obligation=MANDATORY, specification=ISO_19111)
    SingleOperation getOperation();

    /**
     * Returns the ordered sequence of positive integers defining the positions in a source
     * coordinate tuple of the coordinates affected by this pass-through operation.
     *
     * @return Zero-based indices of the modified source coordinates.
     */
    @UML(identifier="modifiedCoordinate", obligation=MANDATORY, specification=ISO_19111)
    int[] getModifiedCoordinates();
}
