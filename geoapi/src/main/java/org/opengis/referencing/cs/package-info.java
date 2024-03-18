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
 * Coordinate Systems and their axes.
 * A {@linkplain org.opengis.referencing.cs.CoordinateSystem coordinate system} (<abbr>CS</abbr>) shall be
 * composed of a non-repeating sequence of {@linkplain org.opengis.referencing.cs.CoordinateSystemAxis axes}.
 * Each axis can have have a name, an abbreviation, a unit of measure, a direction and a range of values.
 * The number of axes is the dimension of the coordinate space. Axis order is significant.
 *
 * <p>One {@link org.opengis.referencing.cs.CoordinateSystem} instance may be used by multiple
 * {@link org.opengis.referencing.crs.CoordinateReferenceSystem} (CRS) instances.
 * The {@linkplain org.opengis.geometry.DirectPosition#getDimension() number of coordinates} in a coordinate tuple
 * shall be equal to the {@linkplain org.opengis.referencing.cs.CoordinateSystem#getDimension() number of coordinate axes}
 * in the coordinate system.
 * Coordinates in coordinate tuples shall be supplied in the order in which the coordinate system's axes are defined.</p>
 *
 * <h2>Coordinate system types</h2>
 * A coordinate system implies how coordinates are calculated from geometric elements such as distances and
 * angles and vice versa. These rules are not specified in details, but are implied by the coordinate system type.
 * Certain subtypes of coordinate system shall be used only with specific subtypes of coordinate reference system.
 * The restrictions are documented in the javadoc of each <abbr>CRS</abbr> subtype.
 *
 * <p>ISO 19111 defines some coordinate system <em>unions</em> in addition to the coordinate system <em>types</em>.
 * Each union enumerates the coordinate system types that can be associated to a <abbr>CRS</abbr> type.
 * GeoAPI does not define those unions because they have no direct equivalence in the Java language.</p>
 *
 * @departure constraint
 *   ISO 19111 defines {@code GeodeticCS} and {@code EngineeringCS} unions.
 *   However, the {@code union} construct found in some languages like C/C++ is not available in Java.
 *   For each union, a different approach has been applied and documented in the {@code org.opengis.referencing.cs}
 *   package.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   1.0
 */
package org.opengis.referencing.cs;
