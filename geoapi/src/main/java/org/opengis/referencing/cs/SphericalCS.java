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
package org.opengis.referencing.cs;

import java.util.Map;
import org.opengis.annotation.UML;
import static org.opengis.annotation.Specification.*;


/**
 * A 3-dimensional coordinate system with one distance measured from the origin and two angular coordinates.
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
 * the above example uses two axis directions that are not defined in ISO 19111,
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
