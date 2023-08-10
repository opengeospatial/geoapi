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
 * A 2- or 3-dimensional coordinate system in which position is specified by
 * geodetic latitude, geodetic longitude, and (in the 3D case) ellipsoidal height.
 *
 * <p>This type of CS can be used by coordinate reference systems of type
 * {@link org.opengis.referencing.crs.GeographicCRS}.
 * The following examples describe some possible set of axes for ellipsoidal CS used with the above-cited CRS:</p>
 *
 * <table class="ogc">
 *   <caption>Example 1: used with a two-dimensional Geographic CRS</caption>
 *   <tr><th>Axis name</th> <th>Abbr.</th> <th>Direction</th> <th>Unit</th></tr>
 *   <tr><td>Geodetic latitude</td> <td>φ</td> <td>{@link AxisDirection#NORTH}</td><td>degree</td></tr>
 *   <tr><td>Geodetic longitude</td><td>λ</td> <td>{@link AxisDirection#EAST}</td> <td>degree</td></tr>
 * </table>
 *
 * <table class="ogc">
 *   <caption>Example 2: used with a three-dimensional Geographic CRS</caption>
 *   <tr><th>Axis name</th> <th>Abbr.</th> <th>Direction</th> <th>Unit</th></tr>
 *   <tr><td>Geodetic latitude</td> <td>φ</td> <td>{@link AxisDirection#NORTH}</td><td>degree</td></tr>
 *   <tr><td>Geodetic longitude</td><td>λ</td> <td>{@link AxisDirection#EAST}</td> <td>degree</td></tr>
 *   <tr><td>Ellipsoidal height</td><td>h</td> <td>{@link AxisDirection#UP}</td>   <td>metre</td></tr>
 * </table>
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   1.0
 *
 * @see CSAuthorityFactory#createEllipsoidalCS(String)
 * @see CSFactory#createEllipsoidalCS(Map, CoordinateSystemAxis, CoordinateSystemAxis)
 * @see CSFactory#createEllipsoidalCS(Map, CoordinateSystemAxis, CoordinateSystemAxis, CoordinateSystemAxis)
 */
@UML(identifier="CS_EllipsoidalCS", specification=ISO_19111, version=2007)
public interface EllipsoidalCS extends CoordinateSystem {
}
