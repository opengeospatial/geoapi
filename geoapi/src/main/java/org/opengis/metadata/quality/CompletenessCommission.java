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
 * Excess data present in the data set.
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
 *     <td>1</td>
 *     <td>excess item</td>
 *     <td>error indicator</td>
 *     <td>Boolean</td>
 *     <td>{@code true} indicates that the item is in excess</td>
 *   </tr><tr>
 *     <td>2</td>
 *     <td>number of excess items</td>
 *     <td>error count</td>
 *     <td>Integer</td>
 *     <td></td>
 *   </tr><tr>
 *     <td>3</td>
 *     <td>rate of excess items</td>
 *     <td>error rate</td>
 *     <td>Real</td>
 *     <td></td>
 *   </tr><tr>
 *     <td>4</td>
 *     <td>number of duplicate feature instances</td>
 *     <td>error count</td>
 *     <td>Integer</td>
 *     <td></td>
 *   </tr>
 * </table>
 *
 * <p>{@linkplain Measure#getDefinition() Definitions}:</p>
 * <ol start="1">
 *   <li>Indication that an item is incorrectly present in the data.</li>
 *   <li>Number of items within the data set or sample that should not have been present.</li>
 *   <li>Number of excess items in the data set or sample in relation to the number of items that should have been present.</li>
 *   <li>Total number of exact duplications of feature instances within the data.</li>
 * </ol>
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Alexis Gaillard (Geomatys)
 * @version 3.1
 *
 * @see CompletenessOmission
 *
 * @since 2.0
 *
 * @todo Renamed in 19157:2022: {@code Commission}.
 */
@UML(identifier="DQ_CompletenessCommission", specification=ISO_19157)
public interface CompletenessCommission extends Completeness {
}
