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
 * A 2- or 3-dimensional coordinate system with one distance measured from the origin and two angular coordinates.
 * In the two-dimensional case, the radius is omitted and may be implicitly an ellipsoid surface.
 * Not to be confused with an {@link EllipsoidalCS} based on an ellipsoid "degenerated" into a sphere.
 *
 * <p>This type of <abbr>CS</abbr> can be used by coordinate reference systems of type
 * {@link org.opengis.referencing.crs.GeodeticCRS} or
 * {@link org.opengis.referencing.crs.EngineeringCRS}, potentially in combination with
 * {@link org.opengis.referencing.crs.DerivedCRS}.
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
 *   <tr><td>Distance</td>  <td>r</td> <td>{@link AxisDirection#AWAY_FROM}</td>         <td>kilometre</td></tr>
 *   <tr><td>Longitude</td> <td>φ</td> <td>{@link AxisDirection#COUNTER_CLOCKWISE}</td> <td>degree</td></tr>
 *   <tr><td>Elevation</td> <td>Θ</td> <td>{@link AxisDirection#UP}</td>                <td>degree</td></tr>
 * </table>
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 * @since   1.0
 *
 * @see CSAuthorityFactory#createSphericalCS(String)
 * @see CSFactory#createSphericalCS(Map, CoordinateSystemAxis, CoordinateSystemAxis, CoordinateSystemAxis)
 */
@UML(identifier="SphericalCS", specification=ISO_19111)
public interface SphericalCS extends CoordinateSystem {
}
