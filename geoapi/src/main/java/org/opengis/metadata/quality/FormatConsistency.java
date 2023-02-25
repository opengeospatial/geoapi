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
package org.opengis.metadata.quality;

import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;


/**
 * Degree to which data are stored in accordance with the physical structure of the data set.
 * The data set is described by the {@linkplain DataQuality#getScope() scope}.
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
 *     <td>119</td>
 *     <td>physical structure conflicts</td>
 *     <td>error indicator</td>
 *     <td>Boolean</td>
 *     <td>{@code true} indicates physical structure conflict</td>
 *   </tr><tr>
 *     <td>19</td>
 *     <td>number of physical structure conflicts</td>
 *     <td>error count</td>
 *     <td>Integer</td>
 *     <td></td>
 *   </tr><tr>
 *     <td>20</td>
 *     <td>physical structure conflict rate</td>
 *     <td>correct items rate</td>
 *     <td>Real</td>
 *     <td></td>
 *   </tr>
 * </table>
 *
 * <p>{@linkplain Measure#getDefinition() Definitions}:</p>
 * <ol start="119">
 *   <li>Indication that items are stored in conflict with the physical structure of the data set.</li>
 *   <li value="19">Count of all items in the data set that are stored in conflict with the physical structure of the data set.</li>
 *   <li>Number of items in the data set that are stored in conflict with the physical structure of the data set
 *       divided by the total number of items.</li>
 * </ol>
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Cory Horner (Refractions Research)
 * @author  Alexis Gaillard (Geomatys)
 * @version 3.1
 * @since   2.1
 */
@UML(identifier="DQ_FormatConsistency", specification=ISO_19157)
public interface FormatConsistency extends LogicalConsistency {
}
