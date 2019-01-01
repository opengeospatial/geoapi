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
 * A 3-dimensional coordinate system with one distance measured from the origin and two angular ordinates.
 * Not to be confused with an {@link EllipsoidalCS} based on an ellipsoid "degenerated" into a sphere.
 *
 * <p>This type of CS can be used by coordinate reference systems of type
 * {@link org.opengis.referencing.crs.GeocentricCRS} or
 * {@link org.opengis.referencing.crs.EngineeringCRS}.
 * The following examples describe some possible set of axes for spherical CS used with the above-cited CRS:</p>
 *
 * <table class="ogc">
 *   <caption>Example 1: used with a Geocentric CRS</caption>
 *   <tr><th>Axis name</th> <th>Abbr.</th> <th>Direction</th> <th>Unit</th></tr>
 *   <tr><td>Spherical latitude</td> <td>Θ</td> <td>{@link AxisDirection#NORTH}</td> <td>degree</td></tr>
 *   <tr><td>Spherical longitude</td><td>Ω</td> <td>{@link AxisDirection#EAST}</td>  <td>degree</td></tr>
 *   <tr><td>Geocentric radius</td>  <td>R</td> <td>{@link AxisDirection#UP}</td>    <td>metre</td></tr>
 * </table>
 *
 * <table class="ogc">
 *   <caption>Example 2: used with an Engineering CRS</caption>
 *   <tr><th>Axis name</th> <th>Abbr.</th> <th>Direction</th> <th>Unit</th></tr>
 *   <tr><td>Distance</td>  <td>r</td> <td>{@code AxisDirection.valueOf("AWAY_FROM")}</td>         <td>kilometre</td></tr>
 *   <tr><td>Longitude</td> <td>φ</td> <td>{@code AxisDirection.valueOf("COUNTER_CLOCKWISE")}</td> <td>degree</td></tr>
 *   <tr><td>Elevation</td> <td>Θ</td> <td>{@link AxisDirection#UP}</td>                           <td>degree</td></tr>
 * </table>
 *
 * <div class="note"><b>Note:</b>
 * The above example uses two axis directions that are not defined in ISO 19111,
 * but found in ISO 19162 as "{@code awayFrom}" and "{@code counterClockwise}".</div>
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   1.0
 *
 * @see CSAuthorityFactory#createSphericalCS(String)
 * @see CSFactory#createSphericalCS(Map, CoordinateSystemAxis, CoordinateSystemAxis, CoordinateSystemAxis)
 */
@UML(identifier="CS_SphericalCS", specification=ISO_19111)
public interface SphericalCS extends CoordinateSystem {
}
