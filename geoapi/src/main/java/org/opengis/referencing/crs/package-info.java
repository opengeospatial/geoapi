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
 * Reference systems by coordinates.
 * A {@linkplain org.opengis.referencing.crs.CoordinateReferenceSystem Coordinate Reference System} (CRS)
 * consists of one {@linkplain org.opengis.referencing.cs Coordinate System}
 * (a set of {@linkplain org.opengis.referencing.cs.CoordinateSystemAxis axes} with implied mathematical rules
 * for calculating distances and angles from coordinates) that is related to the Earth, another celestial body
 * or a platform through one {@linkplain org.opengis.referencing.datum datum}.
 *
 * <p>{@code CoordinateReferenceSystem} instances and their components shall be immutable.
 * For <abbr>CRS</abbr> defined on moving platforms such as cars, ships, aircraft and spacecraft,
 * transformation to an earth-fixed coordinate reference system may include a time element.
 * Time-variability of coordinate reference systems may be covered by creating different
 * {@code CoordinateReferenceSystem} instances, each with a different datum, for consecutive epochs.
 * The date of realization of the datum shall then be included in its definition.
 * Furthermore, it is recommended that the date of realization be included in the names of those datums
 * and coordinate reference systems.</p>
 *
 * <h2>Sub-types of coordinate reference system</h2>
 * Geodetic survey practice usually divides coordinate reference systems into a number of sub-types.
 * The common classification criterion for sub-typing of coordinate reference systems can be described
 * as the way in which they deal with earth curvature. This has a direct effect on the portion of the
 * earth's surface that can be covered by that type of <abbr>CRS</abbr> with an acceptable degree of error.
 *
 * <h3>Compound <abbr>CRS</abbr></h3>
 * The traditional separation of horizontal and vertical position has resulted in coordinate reference systems
 * that are horizontal (2D) in nature and vertical (1D). It is established practice to combine the horizontal
 * coordinates of a point with a height or depth from a different <abbr>CRS</abbr>. The coordinate reference system
 * to which these 3D coordinates are referenced combines the separate horizontal and vertical coordinate reference
 * systems of the horizontal and vertical coordinates. Such a  <abbr>CRS</abbr> is called a <dfn>compound</dfn>
 * <abbr>CRS</abbr>. It consists of an ordered sequence of the two or more single coordinate reference systems.
 *
 * <h3>Derived <abbr>CRS</abbr></h3>
 * Some coordinate reference systems are defined by applying a coordinate conversion to another <abbr>CRS</abbr>.
 * Such a <abbr>CRS</abbr> is called a <dfn>derived</dfn> <abbr>CRS</abbr> and the coordinate reference system it
 * was derived from by applying the conversion is called the <dfn>source</dfn> or <dfn>base</dfn> <abbr>CRS</abbr>.
 * A coordinate conversion is an arithmetic operation with zero or more parameters that have defined values.
 * The base <abbr>CRS</abbr> and derived <abbr>CRS</abbr> have the same datum.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   1.0
 */
package org.opengis.referencing.crs;
