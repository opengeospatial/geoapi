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
package org.opengis.referencing.crs;

import java.util.Map;
import org.opengis.referencing.cs.CoordinateSystem;
import org.opengis.referencing.operation.Conversion;
import org.opengis.annotation.UML;
import static org.opengis.annotation.Specification.*;


/**
 * A coordinate reference system that is defined by its coordinate conversion from another CRS
 * but is not a projected CRS.
 * This category includes coordinate reference systems derived from a projected CRS.
 *
 * <p>A {@code DerivedCRS} instance may also implement one of the interfaces listed below,
 * provided that the conditions in the right column are meet:</p>
 *
 * <table class="ogc">
 *   <caption>Derived CRS types</caption>
 *   <tr><th>Type</th>                   <th>Conditions</th></tr>
 *   <tr><td>{@link GeodeticCRS}</td>    <td>Base CRS is also a {@code GeodeticCRS}.</td></tr>
 *   <tr><td>{@link VerticalCRS}</td>    <td>Base CRS is also a {@code VerticalCRS}.</td></tr>
 *   <tr><td>{@link TemporalCRS}</td>    <td>Base CRS is also a {@code TemporalCRS}.</td></tr>
 *   <tr><td>{@link EngineeringCRS}</td> <td>Base CRS is a {@code GeodeticCRS}, {@code ProjectedCRS} or {@code EngineeringCRS}.</td></tr>
 * </table>
 *
 * @departure integration
 *   ISO 19111 defines a {@code SC_DerivedCRSType} code list with the following values:
 *   {@code geodetic}, {@code vertical}, {@code engineering} and {@code image}.
 *   But ISO 19162 takes a slightly different approach without such code list.
 *   Instead, ISO 19162 writes the derived CRS using the WKT keyword of the corresponding CRS type
 *   ({@code “GeodCRS”}, {@code “VertCRS”}, {@code “TimeCRS”} or {@code “EngCRS”}).
 *   GeoAPI follows a similar path by <strong>not</strong> providing a {@code DerivedCRSType} code list.
 *   Instead, we recommend to implement the corresponding interface as documented in the above table.
 *   Then, Java expressions like {@code (baseCRS instanceof FooCRS)} provides the same capability
 *   than the code list with more flexibility. For example, it allows to use a derived CRS of type “vertical”
 *   with API expecting an instance of {@code VerticalCRS}.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 *
 * @see CRSAuthorityFactory#createDerivedCRS(String)
 * @see CRSFactory#createDerivedCRS(Map, CoordinateReferenceSystem, Conversion, CoordinateSystem)
 */
@UML(identifier="SC_DerivedCRS", specification=ISO_19111)
public interface DerivedCRS extends GeneralDerivedCRS {
}
