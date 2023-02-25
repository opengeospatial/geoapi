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
package org.opengis.referencing.cs;

import java.util.Map;
import org.opengis.annotation.UML;
import static org.opengis.annotation.Specification.*;


/**
 * A 1-dimensional coordinate system used to record the heights or depths of points.
 * Such a coordinate system is usually dependent on the Earth's gravity field, perhaps
 * loosely as when atmospheric pressure is the basis for the vertical coordinate system axis.
 * An exact definition is deliberately not provided as the complexities of the subject fall
 * outside the scope of the ISO 19111 specification.
 *
 * <p>This type of CS can be used by coordinate reference systems of type
 * {@link org.opengis.referencing.crs.VerticalCRS}.
 * The following examples describe some possible axes for vertical CS used with the above-cited CRS:</p>
 *
 * <table class="ogc">
 *   <caption>Example 1: positive values above sea level</caption>
 *   <tr><th>Axis name</th> <th>Abbr.</th> <th>Direction</th> <th>Unit</th></tr>
 *   <tr><td>Gravity-related height</td> <td>H</td> <td>{@link AxisDirection#UP}</td> <td>metre</td></tr>
 * </table>
 *
 * <table class="ogc">
 *   <caption>Example 2: positive values below sea level</caption>
 *   <tr><th>Axis name</th> <th>Abbr.</th> <th>Direction</th> <th>Unit</th></tr>
 *   <tr><td>Depth</td> <td>D</td> <td>{@link AxisDirection#DOWN}</td> <td>metre</td></tr>
 * </table>
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   1.0
 *
 * @see CSAuthorityFactory#createVerticalCS(String)
 * @see CSFactory#createVerticalCS(Map, CoordinateSystemAxis)
 */
@UML(identifier="CS_VerticalCS", specification=ISO_19111)
public interface VerticalCS extends CoordinateSystem {
}
