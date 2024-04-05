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
 * A {@linkplain org.opengis.referencing.crs.CoordinateReferenceSystem Coordinate Reference System}
 * (<abbr>CRS</abbr>) generally consists of one {@linkplain org.opengis.referencing.cs Coordinate System}
 * (a set of {@linkplain org.opengis.referencing.cs.CoordinateSystemAxis axes} with implied mathematical rules
 * for calculating distances and angles from coordinates) that is related to the Earth, another celestial body
 * or a platform through one {@linkplain org.opengis.referencing.datum datum} or datum ensemble.
 *
 * <p>{@code CoordinateReferenceSystem} instances and their components shall be immutable.
 * For <abbr>CRS</abbr> defined on moving platforms such as cars, ships, aircraft and spacecraft,
 * transformation to a planet-fixed coordinate reference system may include a time element.
 * For a dynamic <abbr>CRS</abbr>, locations on or near the surface of the planet will move
 * within the <abbr>CRS</abbr> due to crustal motion or deformation, therefor data needs a
 * {@linkplain org.opengis.coordinate.CoordinateMetadata#getCoordinateEpoch() coordinate epoch}
 * in addition of the <abbr>CRS</abbr>. In both cases, time-variability is handled by
 * coordinate operations rather than changes in the <abbr>CRS</abbr> definition.</p>
 *
 * <h2>Compound <abbr>CRS</abbr></h2>
 * The traditional separation of horizontal, vertical and temporal position has resulted in different interfaces
 * for the horizontal (2D), vertical (1D) and temporal (1D) components. It is established practice to combine the
 * horizontal coordinates of a point with a height or depth from a different <abbr>CRS</abbr>, and sometime a time.
 * The <abbr>CRS</abbr> to which these 3D or 4D coordinates are referenced is called a <dfn>compound</dfn>
 * coordinate reference system.
 *
 * <h2>Derived <abbr>CRS</abbr></h2>
 * Some coordinate reference systems are defined by applying a coordinate conversion to another <abbr>CRS</abbr>.
 * Such a <abbr>CRS</abbr> is called a <dfn>derived</dfn> coordinate reference system and the <abbr>CRS</abbr>
 * it was derived from by applying the conversion is called the <dfn>base</dfn> <abbr>CRS</abbr>.
 * A coordinate conversion is an arithmetic operation with zero or more parameters that have defined values.
 * The base <abbr>CRS</abbr> and derived <abbr>CRS</abbr> have the same datum or datum ensemble.
 * Projected <abbr>CRS</abbr>s are special cases of derived <abbr>CRS</abbr>s.
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 4.0
 * @since   1.0
 */
package org.opengis.referencing.crs;
