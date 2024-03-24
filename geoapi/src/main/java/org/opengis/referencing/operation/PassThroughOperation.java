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
@UML(identifier="CC_PassThroughOperation", specification=ISO_19111, version=2007)
public interface PassThroughOperation extends CoordinateOperation {
    /**
     * Returns the operation to apply on the subset of a coordinate tuple.
     *
     * @return the operation to apply on the subset of a coordinate tuple.
     */
    @UML(identifier="coordOperation", obligation=MANDATORY, specification=ISO_19111)
    CoordinateOperation getOperation();

    /**
     * Returns the ordered sequence of positive integers defining the positions in a source
     * coordinate tuple of the coordinates affected by this pass-through operation.
     *
     * @return Zero-based indices of the modified source coordinates.
     */
    @UML(identifier="modifiedCoordinate", obligation=MANDATORY, specification=ISO_19111)
    int[] getModifiedCoordinates();
}
