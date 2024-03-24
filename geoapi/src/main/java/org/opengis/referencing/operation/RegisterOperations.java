/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2009-2023 Open Geospatial Consortium, Inc.
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

import java.util.Set;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;
import org.opengis.referencing.crs.CoordinateReferenceSystem;


/**
 *
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@UML(identifier="RegisterOperations", specification=ISO_19111)
public interface RegisterOperations {
    /**
     * Finds or infers coordinate operations between the given pair of <abbr>CRS</abbr>s.
     *
     * <h4>Implementation considerations</h4>
     * Coordinate transformation services should be able to automatically derive coordinate operations
     * that are not stored explicitly in any permanent data store, in other words determine their own
     * concatenated or inverse operations. The reason is that it is practically impossible to store
     * all possible pairs of coordinate reference systems in explicitly defined coordinate operations.
     *
     * <p>Coordinate transformation services should also be able to derive or infer the inverse of any
     * coordinate operation (from <var>B</var> to <var>A</var>) from its complementary forward operation
     * (<var>A</var> to <var>B</var>). Geodetic datasets may record
     * only one of the two operations that may exist between any two coordinate reference systems.
     * The inverse operation is then inferred by the application software logic from the stored operation.
     * In some cases, the algorithm for the inverse operation is the same as the forward algorithm,
     * and only the signs of the parameter values reversed.
     * In some other cases, the forward and inverse methods imply two algorithms, but the parameters values are the same.
     * The latter situation generally applies to map projections.</p>
     *
     * <p>The implementation should apply meaningful constraints and validations to this process.
     * For example, it may be mathematically possible to derive a concatenated coordinate operation
     * that will transform North American Datum 1983 coordinates to Australian National Datum,
     * but in a practical sense that operation would be meaningless.
     * The operation can be determined as invalid with a comparison of the two areas of validity
     * and the conclusion that there is no overlap between these.</p>
     *
     * @param  source  the source <abbr>CRS</abbr>.
     * @param  target  the target <abbr>CRS</abbr>.
     * @return coordinate operations found or inferred between the given pair <abbr>CRS</abbr>s. May be an empty set.
     */
    Set<CoordinateOperation> findCoordinateOperation(CoordinateReferenceSystem source, CoordinateReferenceSystem target);
}
