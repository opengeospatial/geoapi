/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2013 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */

/**
 * {@linkplain org.opengis.referencing.cs.CoordinateSystem Coordinate systems} and their
 * {@linkplain org.opengis.referencing.cs.CoordinateSystemAxis axis}. The following is adapted from
 * {@linkplain org.opengis.annotation.Specification#ISO_19111 OpenGIS® Spatial Referencing by
 * Coordinates (Topic 2)} specification.
 *
 * <h3>Coordinate system</h3>
 * <p>The coordinates of points are recorded in a coordinate
 * system. A coordinate system is the set of coordinate system axes that spans
 * the coordinate space. This concept implies the set of mathematical rules that
 * determine how coordinates are associated with invariant quantities such as
 * angles and distances. In other words, a coordinate system implies how coordinates
 * are calculated from geometric elements such as distances and angles and vice
 * versa. The calculus required to derive angles and distances from point coordinates
 * and vice versa in a map plane is simple Euclidean 2D arithmetic. To do the same
 * on the surface of an ellipsoid (curved 2D space) involves more complex ellipsoidal
 * calculus. These rules cannot be specified in detail, but are implied in the
 * geometric properties of the coordinate space.</p>
 *
 * {@note The word "distances" is used loosely in the above description. Strictly speaking
 *        distances are not invariant quantities, as they are expressed in the unit of measure
 *        defined for the coordinate system; ratios of distances are invariant.}
 *
 * <p>One {@linkplain org.opengis.referencing.cs.CoordinateSystem coordinate system}
 * may be used by multiple {@linkplain org.opengis.referencing.crs.CoordinateReferenceSystem coordinate
 * reference systems}. A coordinate system is composed of an ordered set of coordinate
 * system axes, the number of axes being equal to the dimension of the space of which
 * it describes the geometry. Its axes can be spatial, temporal, or mixed. Coordinates
 * in coordinate tuples shall be supplied in the same order as the coordinate axes are
 * defined.</p>
 *
 * <p>The dimension of the coordinate space, the names, the units
 * of measure, the directions and sequence of the axes are all part of the Coordinate
 * System definition. The number of coordinates in a tuple and consequently the number
 * of coordinate axes in a coordinate system shall be equal to the number of coordinate
 * axes in the coordinate system. It is therefore not permitted to supply a coordinate
 * tuple with two heights of different definition in the same coordinate tuple.</p>
 *
 * <p>Coordinate systems are divided into subtypes by the geometric
 * properties of the coordinate space spanned and the geometric properties of the axes
 * themselves (straight or curved; perpendicular or not). Certain subtypes of coordinate
 * system can only be used with specific subtypes of coordinate reference system.</p>
 *
 * <h3>Coordinate system axis</h3>
 * <p>A coordinate system is composed of an ordered set of coordinate
 * system axes. Each of its axes is completely characterised by a unique combination
 * of axis name, axis abbreviation, axis direction and axis unit of measure.</p>
 *
 * <p>The concept of coordinate axis requires some clarification.
 * Consider an arbitrary <var>x</var>, <var>y</var>, <var>z</var> coordinate system.
 * The <var>x</var>-axis may be defined as the locus of points with
 * <var>y</var>&nbsp;=&nbsp;<var>z</var>&nbsp;=&nbsp;0. This is easily enough
 * understood if the <var>x</var>, <var>y</var>, <var>z</var> coordinate system is a
 * Cartesian system and the space it describes is Euclidean. It becomes a bit more
 * difficult to understand in the case of a strongly curved space, such as the surface
 * of an ellipsoid, its geometry described by an ellipsoidal coordinate system (2D or 3D).
 * Applying the same definition by analogy to the curvilinear latitude and longitude
 * coordinates the latitude axis would be the equator and the longitude axis would be
 * the prime meridian, which is not a satisfactory definition.</p>
 *
 * <p>Bearing in mind that the order of the coordinates in a coordinate
 * tuple must be the same as the defined order of the coordinate axes, the "<var>i</var>-th"
 * coordinate axis of a coordinate system is defined as the locus of points for which all
 * coordinates with sequence number not equal to "<var>i</var>", have a constant value
 * locally (whereby <var>i</var> = 1…<var>n</var>, and <var>n</var> is the dimension
 * of the coordinate space).</p>
 *
 * <p>It will be evident that the addition of the word "locally" in this
 * definition apparently adds an element of ambiguity and this is intentional. However,
 * the definition of the coordinate parameter associated with any axis must be unique.
 * The coordinate axis itself should not be interpreted as a unique mathematical object,
 * the associated coordinate parameter should. For example, geodetic latitude is defined
 * as the "Angle from the equatorial plane to the perpendicular to the ellipsoid through
 * a given point, northwards usually treated as positive". However, when used in an ellipsoidal
 * coordinate system the geodetic latitude axis will be described as pointing "north". In two
 * different points on the ellipsoid the direction "north" will be a spatially different direction,
 * but the concept of latitude is the same.</p>
 *
 * @departure constraint
 *   ISO 19111 defines <code>GeodeticCS</code>, <code>EngineeringCS</code> and <code>ImageCS</code>
 *   unions for type safety, which ensures, for example, that a <code>GeodeticCRS</code> only be
 *   associated to a <code>CartesianCS</code>, an <code>EllipsoidalCS</code> or a <code>SphericalCS</code>.
 *   However the <code>union</code> construct found in some languages like C/C++ is not available
 *   in Java. In the particular case of <code>ImageCS</code>, the same type-safety objective can
 *   be obtained through a slight change in the interface hierarchy (see the departure documented
 *   in <code>CartesianCS</code>). For the other two unions (<code>GeodeticCS</code> and
 *   <code>EngineeringCS</code>), no workaround is proposed.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 */
package org.opengis.referencing.cs;
