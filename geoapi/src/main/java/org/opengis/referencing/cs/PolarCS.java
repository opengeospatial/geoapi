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
 * A 2-dimensional polar coordinate system.
 * Position is specified by the distance from the origin and the angle
 * between the line from the origin to a point and a reference direction.
 *
 * <p>This type of <abbr>CS</abbr> can be used by coordinate reference systems of type
 * {@link org.opengis.referencing.crs.EngineeringCRS}, potentially in combination with
 * {@link org.opengis.referencing.crs.DerivedCRS}.
 * The following examples describe some possible set of axes for polar CS used with the above-cited CRS:</p>
 *
 * <table class="ogc">
 *   <caption>Example: used with an Engineering CRS</caption>
 *   <tr><th>Axis name</th> <th>Abbr.</th> <th>Direction</th> <th>Unit</th></tr>
 *   <tr><td>Distance</td> <td>r</td> <td>{@link AxisDirection#AWAY_FROM}</td> <td>metre</td></tr>
 *   <tr><td>Bearing</td>  <td>Θ</td> <td>{@link AxisDirection#CLOCKWISE}</td> <td>degree</td></tr>
 * </table>
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 * @since   1.0
 *
 * @see CylindricalCS
 * @see CSAuthorityFactory#createPolarCS(String)
 * @see CSFactory#createPolarCS(Map, CoordinateSystemAxis, CoordinateSystemAxis)
 */
@UML(identifier="PolarCS", specification=ISO_19111)
public interface PolarCS extends CoordinateSystem {
    /**
     * Returns the number of dimensions, which is 2 for this type of coordinate system.
     *
     * @return always 2.
     */
    @Override
    default int getDimension() {
        return 2;
    }
}
