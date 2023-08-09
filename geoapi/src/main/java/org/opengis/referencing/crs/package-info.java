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

/**
 * {@linkplain org.opengis.referencing.crs.CoordinateReferenceSystem Coordinate reference systems}
 * ({@linkplain org.opengis.referencing.cs.CoordinateSystem coordinate systems} with a
 * {@linkplain org.opengis.referencing.datum.Datum datum}). The following is adapted from
 * {@linkplain org.opengis.annotation.Specification#ISO_19111 OpenGIS® Spatial Referencing by
 * Coordinates (Topic 2)} specification.
 *
 * <p>A coordinate reference system (CRS) consists of one coordinate system (CS) that is related to the earth
 * or platform through one datum. The coordinate system is composed of a set of coordinate axes with specified
 * units of measure. This concept implies the mathematical rules that define how coordinate values are calculated
 * from distances, angles and other geometric elements and vice versa.</p>
 *
 * <p>A datum specifies the relationship of a coordinate system to the object, thus ensuring that the abstract
 * mathematical concept “coordinate system” can be applied to the practical problem of describing positions of
 * features on or near the earth's surface by means of coordinates. The object will generally, but not necessarily,
 * be the earth; for certain coordinate reference systems, the object may be a moving platform.</p>
 *
 * <p>{@code CoordinateReferenceSystem} instances and their components shall be immutable.
 * For CRS defined on moving platforms such as cars, ships, aircraft and spacecraft,
 * transformation to an earth-fixed coordinate reference system may include a time element.
 * Time-variability of coordinate reference systems may be covered by creating different
 * {@code CoordinateReferenceSystem} instances, each with a different datum, for consecutive epochs.
 * The date of realization of the datum shall then be included in its definition.
 * Furthermore, it is recommended that the date of realization be included in the names of those datums
 * and coordinate reference systems.</p>
 *
 * <h2>Sub-types of coordinate reference system</h2>
 * <p>Geodetic survey practice usually divides coordinate reference systems into a number of sub-types.
 * The common classification criterion for sub-typing of coordinate reference systems can be described
 * as the way in which they deal with earth curvature. This has a direct effect on the portion of the
 * earth's surface that can be covered by that type of CRS with an acceptable degree of error.
 * Thus the following principal sub-types of coordinate reference system are distinguished:</p>
 *
 * <blockquote>
 * <p><b>Geocentric:</b>
 * Type of coordinate reference system that deals with the earth's curvature by taking the 3D spatial view,
 * which obviates the need to model the earth's curvature.
 * The origin of a geocentric CRS is at the approximate centre of mass of the earth.</p>
 *
 * <p><b>Geographic:</b>
 * Type of coordinate reference system based on an ellipsoidal approximation of the geoid.
 * This provides an accurate representation of the geometry of geographic features for a large portion
 * of the earth's surface. Geographic coordinate reference systems can be 2D or 3D.
 * A 2D Geographic CRS is used when positions of features are described on the surface of the reference ellipsoid;
 * a 3D Geographic CRS is used when positions are described on, above or below the reference ellipsoid.</p>
 *
 * <p><b>Projected:</b>
 * Type of coordinate reference system that is based on an approximation of the shape of the earth's surface by a plane.
 * The distortion that is inherent to the approximation is carefully controlled and known.
 * Distortion correction is commonly applied to calculated bearings and distances
 * to produce values that are a close match to actual field values.</p>
 *
 * <p><b>Engineering:</b>
 * Type of coordinate reference system that is that is used only in a contextually local sense.
 * This sub-type is used to model two broad categories of local coordinate reference systems:</p>
 * <ul>
 *   <li>earth-fixed systems, applied to engineering activities on or near the surface of the earth;</li>
 *   <li>coordinates on moving platforms such as road vehicles, vessels, aircraft or spacecraft.</li>
 * </ul>
 *
 * <p><b>Image:</b>
 * An Image CRS is an Engineering CRS applied to images. Image CRSs are treated as
 * a separate sub-type because a separate user community exists for images with its
 * own vocabulary. The definition of the associated Image Datum contains two data
 * attributes not relevant for other datums and coordinate reference systems.</p>
 *
 * <p><b>Vertical:</b>
 * Type of coordinate reference system used for the recording of heights or depths.
 * Vertical CRSs make use of the direction of gravity to define the concept of height or depth,
 * but its relationship with gravity may not be straightforward.
 * By implication ellipsoidal heights (<var>h</var>) cannot be captured in a vertical CRS.
 * Ellipsoidal heights cannot exist independently, but only as inseparable part of a 3D coordinate tuple
 * defined in a geographic 3D coordinate reference system.</p>
 *
 * <p><b>Temporal:</b>
 * Used for the recording of time in association with any of the listed spatial coordinate reference systems.
 * Any CRS can be associate with a temporal CRS to form a spatio-temporal compound CRS.
 * More than one temporal CRS may be included if these axes represent different time quantities.</p>
 * </blockquote>
 *
 * <p>In addition to the above principal sub-types, so called because they represent concepts generally known
 * in geodetic practice, two more sub-types have been defined to permit modelling of certain relationships
 * and constraints that exist between the principal sub-types.</p>
 *
 * <blockquote>
 * <p id="CompoundCRS"><b>Compound:</b>
 * The traditional separation of horizontal and vertical position has resulted in coordinate reference systems
 * that are horizontal (2D) in nature and vertical (1D). It is established practice to combine the horizontal
 * coordinates of a point with a height or depth from a different CRS. The coordinate reference system to which
 * these 3D coordinates are referenced combines the separate horizontal and vertical coordinate reference systems
 * of the horizontal and vertical coordinates. Such a CRS is called a <cite>compound</cite> CRS.
 * It consists of an ordered sequence of the two or more single coordinate reference systems.</p>
 *
 * <p id="DerivedCRS"><b>Derived:</b>
 * Some coordinate reference systems are defined by applying a coordinate conversion to another CRS.
 * Such a CRS is called a <cite>derived</cite> CRS and the coordinate reference system it was derived from
 * by applying the conversion is called the <cite>source</cite> or <cite>base</cite> CRS.
 * A coordinate conversion is an arithmetic operation with zero or more parameters that have defined values.
 * The base CRS and derived CRS have the same datum.</p>
 * </blockquote>
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   1.0
 */
package org.opengis.referencing.crs;
