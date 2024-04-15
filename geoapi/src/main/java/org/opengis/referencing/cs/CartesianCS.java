/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2003-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.referencing.cs;

import java.util.Map;
import org.opengis.annotation.UML;
import static org.opengis.annotation.Specification.*;


/**
 * A 2- or 3-dimensional coordinate system in Euclidean space with orthogonal straight axes.
 * All axes shall have the same length unit of measure.
 *
 * <p>This type of <abbr>CS</abbr> can be used by coordinate reference systems of type
 * {@link org.opengis.referencing.crs.GeodeticCRS},
 * {@link org.opengis.referencing.crs.ProjectedCRS} or
 * {@link org.opengis.referencing.crs.EngineeringCRS}.
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
 *   <tr><td>Ahead</td><td>x</td> <td>{@link AxisDirection#FORWARD}</td>   <td>metre</td></tr>
 *   <tr><td>Right</td><td>y</td> <td>{@link AxisDirection#STARBOARD}</td> <td>metre</td></tr>
 *   <tr><td>Down</td> <td>z</td> <td>{@link AxisDirection#DOWN}</td>      <td>metre</td></tr>
 * </table>
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 * @since   1.0
 *
 * @see CSAuthorityFactory#createCartesianCS(String)
 * @see CSFactory#createCartesianCS(Map, CoordinateSystemAxis, CoordinateSystemAxis)
 * @see CSFactory#createCartesianCS(Map, CoordinateSystemAxis, CoordinateSystemAxis, CoordinateSystemAxis)
 */
@UML(identifier="CartesianCS", specification=ISO_19111)
public interface CartesianCS extends AffineCS {
}
