/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2023 Open Geospatial Consortium, Inc.
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

/**
 * Coordinate operations (relationship between any two <abbr>CRS</abbr>).
 * If the relationship between any two coordinate reference systems (<abbr>CRS</abbr>) is known,
 * coordinates in one <abbr>CRS</abbr> can be transformed or converted to another <abbr>CRS</abbr>.
 * If the <abbr>CRS</abbr> is dynamic, coordinates also may be referenced to a different coordinate epoch,
 * or to both a different <abbr>CRS</abbr> and different coordinate epoch.
 *
 * <p>The algorithm used to execute a
 * {@linkplain org.opengis.referencing.operation.SingleOperation single coordinate operation}
 * is defined in the {@linkplain org.opengis.referencing.operation.OperationMethod operation method}.
 * Each operation method can use a number of {@linkplain org.opengis.parameter.ParameterValue parameters},
 * and each single coordinate operation assigns value to these parameters.</p>
 *
 * <p>A coordinate operation (transformation or conversion) can be time-varying,
 * and must be time-varying if either the source or target <abbr>CRS</abbr> is time varying.
 * When the coordinate operation is time-varying, the operation method used will also be time-varying,
 * and some of the parameters used by that operation method will involve time.
 * For example, some of the parameters may have time, velocity, and/or acceleration values and units.</p>
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 4.0
 * @since   1.0
 */
package org.opengis.referencing.operation;
