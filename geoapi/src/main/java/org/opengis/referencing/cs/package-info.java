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
 * {@link org.opengis.referencing.crs.CoordinateReferenceSystem} (<abbr>CRS</abbr>) instances.
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
 * <p>Coordinate system <em>types</em> should not be confused with coordinate system <em>unions</em>.
 * The latter look similar to <abbr>CS</abbr> types, but do not imply a specific set of mathematical rules.
 * A <abbr>CS</abbr> union is only an enumeration of the <abbr>CS</abbr> types that can be associated to a
 * given <abbr>CRS</abbr> type. For example, ISO 19111 defines {@code GeodeticCS} as the enumeration of the
 * types of <abbr>CS</abbr> that can be associated to a {@code GeodeticCRS}.
 * Since unions are not available in Java (they are available in some other languages such as C++),
 * GeoAPI replaces them by Javadoc. All <abbr>CS</abbr> defined in this packages are types.</p>
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 * @since   1.0
 */
package org.opengis.referencing.cs;
