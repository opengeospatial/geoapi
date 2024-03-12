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
 * A 2-dimensional coordinate system in which position is specified by the distance from the
 * origin and the angle between the line from the origin to a point and a reference direction.
 *
 * <p>This type of CS can be used by coordinate reference systems of type
 * {@link org.opengis.referencing.crs.EngineeringCRS}.
 * The following examples describe some possible set of axes for polar CS used with the above-cited CRS:</p>
 *
 * <table class="ogc">
 *   <caption>Example: used with an Engineering CRS</caption>
 *   <tr><th>Axis name</th> <th>Abbr.</th> <th>Direction</th> <th>Unit</th></tr>
 *   <tr><td>Distance</td> <td>r</td> <td>{@code AxisDirection.valueOf("AWAY_FROM")}</td> <td>metre</td></tr>
 *   <tr><td>Bearing</td>  <td>Θ</td> <td>{@code AxisDirection.valueOf("CLOCKWISE")}</td> <td>degree</td></tr>
 * </table>
 *
 * <div class="note"><b>Note:</b>
 * the above example uses two axis directions that are not defined in ISO 19111,
 * but found in ISO 19162 as "{@code awayFrom}" and "{@code clockwise}".</div>
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   1.0
 *
 * @see CylindricalCS
 * @see CSAuthorityFactory#createPolarCS(String)
 * @see CSFactory#createPolarCS(Map, CoordinateSystemAxis, CoordinateSystemAxis)
 */
@UML(identifier="CS_PolarCS", specification=ISO_19111, version=2007)
public interface PolarCS extends CoordinateSystem {
}
