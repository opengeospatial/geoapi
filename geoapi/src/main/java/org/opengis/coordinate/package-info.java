/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2024 Open Geospatial Consortium, Inc.
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
 * Tuple of coordinate values.
 * A <dfn>coordinate</dfn> is one of <var>n</var> scalar values that define the position of a single point.
 * A <dfn>coordinate tuple</dfn> is an ordered list of coordinates that define the position of a single point.
 * The {@linkplain org.opengis.geometry.DirectPosition#getCoordinates() coordinates} within a coordinate tuple
 * are mutually independent, and the number of coordinates is equal to the tuple
 * {@linkplain org.opengis.geometry.DirectPosition#getDimension() dimension}.
 * A <dfn>coordinate set</dfn> is a collection of coordinate tuples referenced to the same
 * {@linkplain org.opengis.referencing.crs.CoordinateReferenceSystem coordinate reference system}.
 * A coordinate set does not necessarily describe a geometry.
 *
 * <p>Coordinate tuples can be represented by instances of {@link org.opengis.geometry.DirectPosition}
 * or by plain Java arrays of numbers.</p>
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
package org.opengis.coordinate;
