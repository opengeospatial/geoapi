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

import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;


/**
 * Adherence of values to the value domains.
 *
 * <h2>Standardized values</h2>
 * In order to achieve well defined and comparable quality information, it is recommended to
 * report data quality using {@linkplain Measure quality measures} listed in ISO 19157 annex.
 * The following table provides a summary; see ISO 19157 for more complete descriptions and examples.
 * All identifiers should be in "ISO 19157" namespace.
 *
 * <table class="ogc">
 *   <caption>Standardized values derived from ISO 19157</caption>
 *   <tr>
 *     <th>{@linkplain MeasureReference#getMeasureIdentification() Identifier}</th>
 *     <th>{@linkplain MeasureReference#getNamesOfMeasure() Name of measure}</th>
 *     <th>{@linkplain Measure#getBasicMeasure() Basic measure}</th>
 *     <th>{@linkplain Measure#getValueType() Value type}</th>
 *     <th>Remarks</th>
 *   </tr><tr>
 *     <td>14</td>
 *     <td>value domain non-conformance</td>
 *     <td>error indicator</td>
 *     <td>Boolean</td>
 *     <td>{@code true} indicates that an item is not in conformance with its value domain</td>
 *   </tr><tr>
 *     <td>15</td>
 *     <td>value domain conformance</td>
 *     <td>correctness indicator</td>
 *     <td>Boolean</td>
 *     <td>{@code true} indicates that an item is in compliance with its value domain</td>
 *   </tr><tr>
 *     <td>16</td>
 *     <td>number of items not in conformance with their value domain</td>
 *     <td>error count</td>
 *     <td>Integer</td>
 *     <td></td>
 *   </tr><tr>
 *     <td>17</td>
 *     <td>value domain conformance rate</td>
 *     <td>correct items rate</td>
 *     <td>Real</td>
 *     <td></td>
 *   </tr><tr>
 *     <td>18</td>
 *     <td>value domain non-conformance rate</td>
 *     <td>error rate</td>
 *     <td>Real</td>
 *     <td></td>
 *   </tr>
 * </table>
 *
 * <p>{@linkplain Measure#getDefinition() Definitions}:</p>
 * <ol start="14">
 *   <li>Indication of if an item is not in conformance with its value domain.</li>
 *   <li>Indication that an item is conforming to its value domain.</li>
 *   <li>Count of all items in the data set that are not in conformance with their value domain.</li>
 *   <li>Number of items in the data set that are in conformance with their value domain
 *       in relation to the total number of items in the data set.</li>
 *   <li>Number of items in the data set that are not in conformance with their value domain
 *       in relation to the total number of items in the data set.</li>
 * </ol>
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Alexis Gaillard (Geomatys)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="DQ_DomainConsistency", specification=ISO_19157)
public interface DomainConsistency extends LogicalConsistency {
}
