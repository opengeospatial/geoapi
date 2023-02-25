/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
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
 * {@linkplain org.opengis.referencing.cs.CoordinateSystem Coordinate systems} and their
 * {@linkplain org.opengis.referencing.cs.CoordinateSystemAxis axis}. The following is adapted from
 * {@linkplain org.opengis.annotation.Specification#ISO_19111 OpenGIS® Spatial Referencing by
 * Coordinates (Topic 2)} specification.
 *
 * <p>A coordinate system shall be composed of a non-repeating sequence of coordinate system axes.
 * One {@link org.opengis.referencing.cs.CoordinateSystem} (CS) instance may be used by multiple
 * {@linkplain org.opengis.referencing.crs.CoordinateReferenceSystem} (CRS) instances.
 * The dimension of the coordinate space, the names, the units of measure, the directions
 * and sequence of the axes shall be part of the coordinate system definition.
 * The number of axes shall be equal to the dimension of the space of which it describes the geometry.
 * It is therefore not permitted to supply a coordinate tuple with two heights of different definition.</p>
 *
 * <p>The {@linkplain org.opengis.geometry.DirectPosition#getDimension() number of coordinates} in a coordinate tuple
 * shall be equal to the {@linkplain org.opengis.referencing.cs.CoordinateSystem#getDimension() number of coordinate
 * axes} in the coordinate system. Coordinates in coordinate tuples shall be supplied in the order in which
 * the coordinate system's axes are defined.</p>
 *
 * <p>A coordinate system implies how coordinates are calculated from geometric elements such as distances
 * and angles and vice versa. The calculus required to derive angles and distances from point coordinates
 * and vice versa in a map plane is simple Euclidean 2D arithmetic. To do the same on the surface of an
 * ellipsoid (curved 2D space) involves more complex ellipsoidal calculus. These rules cannot be specified
 * in detail, but are implied in the geometric properties of the coordinate space.</p>
 *
 * <h2>Coordinate system types and unions</h2>
 * <p>Coordinate systems are divided into subtypes by the geometric properties of the coordinate space spanned
 * and the geometric properties of the axes themselves (straight or curved; perpendicular or not).
 * Certain subtypes of coordinate system shall be used only with specific subtypes of coordinate reference system.
 * The restrictions are documented in the javadoc of each CRS subtype.</p>
 *
 * <p>ISO 19111 defines three coordinate system <em>unions</em> in addition to the coordinate system <em>types</em>.
 * Each union enumerates the coordinate system types that can be associated to a CRS type.
 * However, the {@code union} construct found in some languages like C/C++ is not available in Java.
 * GeoAPI workarounds this limitation in different ways:</p>
 *
 * <table class="ogc">
 *   <caption>Representations of Coordinate System unions</caption>
 *   <tr>
 *     <td>Union</td>
 *     <td>Types in the union</td>
 *     <td>GeoAPI approach</td>
 *   </tr>
 *   <tr>
 *     <td>{@code GeodeticCS}</td>
 *     <td>{@link org.opengis.referencing.cs.CartesianCS},
 *         {@link org.opengis.referencing.cs.EllipsoidalCS},
 *         {@link org.opengis.referencing.cs.SphericalCS}</td>
 *     <td>Provides a {@link org.opengis.referencing.crs.GeographicCRS} type for the {@code EllipsoidalCS} case.
 *         Provides distinct {@link org.opengis.referencing.crs.CRSFactory} methods for the {@code CartesianCS}
 *         and {@code SphericalCS} cases.</td>
 *   </tr>
 *   <tr>
 *     <td>{@code EngineeringCS}</td>
 *     <td>{@link org.opengis.referencing.cs.AffineCS},
 *         {@link org.opengis.referencing.cs.CartesianCS},
 *         {@link org.opengis.referencing.cs.CylindricalCS},
 *         {@link org.opengis.referencing.cs.LinearCS},
 *         {@link org.opengis.referencing.cs.PolarCS},
 *         {@link org.opengis.referencing.cs.SphericalCS},
 *         {@link org.opengis.referencing.cs.UserDefinedCS}.</td>
 *     <td>No workaround in the API. Verified by the conformance tests.</td>
 *   </tr>
 *   <tr>
 *     <td>{@code ImageCS}</td>
 *     <td>{@link org.opengis.referencing.cs.AffineCS},
 *         {@link org.opengis.referencing.cs.CartesianCS}</td>
 *     <td>Defines {@code CartesianCS} as a special case of {@code AffineCS}.</td>
 *   </tr>
 * </table>
 *
 * @departure constraint
 *   ISO 19111 defines {@code GeodeticCS}, {@code EngineeringCS} and {@code ImageCS} unions.
 *   However, the {@code union} construct found in some languages like C/C++ is not available in Java.
 *   For each union, a different approach has been applied and documented in the {@code org.opengis.referencing.cs}
 *   package. In the particular case of {@code ImageCS}, the same type-safety objective can be obtained
 *   through a slight change in the interface hierarchy.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   1.0
 */
package org.opengis.referencing.cs;
