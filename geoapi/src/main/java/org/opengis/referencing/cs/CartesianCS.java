/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2019 Open Geospatial Consortium, Inc.
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
package org.opengis.referencing.cs;

import java.util.Map;
import org.opengis.annotation.UML;
import static org.opengis.annotation.Specification.*;


/**
 * A 2- or 3-dimensional coordinate system with orthogonal straight axes.
 * All axes shall have the same length unit of measure.
 *
 * <p>This type of CS can be used by coordinate reference systems of type
 * {@link org.opengis.referencing.crs.GeocentricCRS},
 * {@link org.opengis.referencing.crs.ProjectedCRS},
 * {@link org.opengis.referencing.crs.EngineeringCRS} or
 * {@link org.opengis.referencing.crs.ImageCRS}.
 * The following examples describe some possible set of axes for Cartesian CS used with the above-cited CRS:</p>
 *
 * <table class="ogc">
 *   <caption>Example 1: used with a Projected CRS</caption>
 *   <tr><th>Axis name</th> <th>Abbr.</th> <th>Direction</th> <th>Unit</th></tr>
 *   <tr><td>Easting</td> <td>E</td> <td>{@link AxisDirection#EAST}</td>  <td>metre</td></tr>
 *   <tr><td>Northing</td><td>N</td> <td>{@link AxisDirection#NORTH}</td> <td>metre</td></tr>
 * </table>
 *
 * <table class="ogc">
 *   <caption>Example 2: used with a Geocentric CRS</caption>
 *   <tr><th>Axis name</th> <th>Abbr.</th> <th>Direction</th> <th>Unit</th></tr>
 *   <tr><td>Geocentric X</td><td>X</td> <td>{@link AxisDirection#GEOCENTRIC_X}</td> <td>metre</td></tr>
 *   <tr><td>Geocentric Y</td><td>Y</td> <td>{@link AxisDirection#GEOCENTRIC_Y}</td> <td>metre</td></tr>
 *   <tr><td>Geocentric Z</td><td>Z</td> <td>{@link AxisDirection#GEOCENTRIC_Z}</td> <td>metre</td></tr>
 * </table>
 *
 * <table class="ogc">
 *   <caption>Example 3: used with an Engineering CRS for a station fixed to Earth</caption>
 *   <tr><th>Axis name</th> <th>Abbr.</th> <th>Direction</th> <th>Unit</th></tr>
 *   <tr><td>Site north</td><td>x</td> <td>{@link AxisDirection#SOUTH_EAST}</td> <td>metre</td></tr>
 *   <tr><td>Site east</td> <td>y</td> <td>{@link AxisDirection#SOUTH_WEST}</td> <td>metre</td></tr>
 * </table>
 *
 * <table class="ogc">
 *   <caption>Example 4: used with an Engineering CRS for a moving platform</caption>
 *   <tr><th>Axis name</th> <th>Abbr.</th> <th>Direction</th> <th>Unit</th></tr>
 *   <tr><td>Ahead</td><td>x</td> <td>{@code AxisDirection.valueOf("FORWARD")}</td>  <td>metre</td></tr>
 *   <tr><td>Right</td><td>y</td> <td>{@code AxisDirection.valueOf("STARBOARD")}</td> <td>metre</td></tr>
 *   <tr><td>Down</td> <td>z</td> <td>{@link AxisDirection#DOWN}</td>                 <td>metre</td></tr>
 * </table>
 *
 * <div class="note"><b>Note:</b>
 * The above example uses two axis directions that are not defined in ISO 19111,
 * but found in ISO 19162 as "{@code forward}" and "{@code starboard}".</div>
 *
 * @departure constraint
 *   ISO 19111 defines {@code CartesianCS} as a direct sub-type of {@code CoordinateSystem}.
 *   ISO also defines {@code ImageCS} as the union of {@code AffineCS} and {@code CartesianCS},
 *   for use by {@code ImageCRS}. Because the {@code union} construct found in some languages like
 *   C/C++ does not exist in Java, GeoAPI defines {@code CartesianCS} as a sub-type of {@code AffineCS}
 *   in order to achieve the same type safety.
 *   With this change, GeoAPI can use {@code AffineCS} directly without the need to define {@code ImageCS}.
 *   In this hierarchy, {@code CartesianCS} is considered a special case of {@code AffineCS} where all axes
 *   are perpendicular to each other.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   1.0
 *
 * @see CSAuthorityFactory#createCartesianCS(String)
 * @see CSFactory#createCartesianCS(Map, CoordinateSystemAxis, CoordinateSystemAxis)
 * @see CSFactory#createCartesianCS(Map, CoordinateSystemAxis, CoordinateSystemAxis, CoordinateSystemAxis)
 */
@UML(identifier="CS_CartesianCS", specification=ISO_19111)
public interface CartesianCS extends AffineCS {
}
