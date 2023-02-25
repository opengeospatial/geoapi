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
package org.opengis.metadata.quality;

import javax.measure.Quantity;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;


/**
 * Closeness of the relative positions of features in the scope to their respective
 * relative positions accepted as or being true.
 *
 * <h2>Standardized values</h2>
 * In order to achieve well defined and comparable quality information, it is recommended to
 * report data quality using {@linkplain Measure quality measures} listed in ISO 19157 annex.
 * The following table provides a summary adapted to GeoAPI objects;
 * see ISO 19157 for more complete descriptions and formulas.
 * All identifiers should be in "ISO 19157" namespace.
 *
 * <table class="ogc">
 *   <caption>Standardized values derived from ISO 19157</caption>
 *   <tr>
 *     <th>{@linkplain MeasureReference#getMeasureIdentification() Identifier}</th>
 *     <th>{@linkplain MeasureReference#getNamesOfMeasure() Name of measure}</th>
 *     <th>{@linkplain Measure#getAliases() Aliases}</th>
 *     <th>{@linkplain Measure#getParameters() Parameters}</th>
 *     <th>{@linkplain Measure#getValueType() Value type}</th>
 *   </tr><tr>
 *     <td>52</td>
 *     <td>relative vertical error</td>
 *     <td>Rel LE90</td>
 *     <td>n (sample size)</td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td>53</td>
 *     <td>relative horizontal error</td>
 *     <td>Rel CE90</td>
 *     <td>n (sample size)</td>
 *     <td>{@link Quantity}</td>
 *   </tr>
 * </table>
 *
 * <p>{@linkplain Measure#getDefinition() Definitions}:</p>
 * <ol start="52">
 *   <li>Evaluation of the random errors of one relief feature to another in the same data set or on the same map/chart.</li>
 *   <li>Evaluation of the random errors in the horizontal position of one feature to another in the same data set or on the same map/chart.</li>
 * </ol>
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Alexis Gaillard (Geomatys)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="DQ_RelativeInternalPositionalAccuracy", specification=ISO_19157)
public interface RelativeInternalPositionalAccuracy extends PositionalAccuracy {
}
