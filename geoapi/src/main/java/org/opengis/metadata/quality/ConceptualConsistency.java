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
 * Adherence to rules of the conceptual schema.
 *
 * <div class="note"><b>Example:</b>
 * Invalid placement of an airport inside a lake.</div>
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
 *     <th>{@linkplain Measure#getAliases() Aliases}</th>
 *     <th>{@linkplain Measure#getBasicMeasure() Basic measure}</th>
 *     <th>{@linkplain Measure#getValueType() Value type}</th>
 *     <th>Remarks</th>
 *   </tr><tr>
 *     <td>8</td>
 *     <td>conceptual schema non-compliance</td>
 *     <td></td>
 *     <td>error indicator</td>
 *     <td>Boolean</td>
 *     <td>{@code true} indicates that an item is not compliant</td>
 *   </tr><tr>
 *     <td>9</td>
 *     <td>conceptual schema non-compliance</td>
 *     <td></td>
 *     <td>correctness indicator</td>
 *     <td>Integer</td>
 *     <td>{@code true} indicates that an item is in compliance</td>
 *   </tr><tr>
 *     <td>10</td>
 *     <td>number of items not compliant with the rules of the conceptual schema</td>
 *     <td></td>
 *     <td>error count</td>
 *     <td>Integer</td>
 *     <td></td>
 *   </tr><tr>
 *     <td>11</td>
 *     <td>number of invalid overlaps of surfaces</td>
 *     <td>overlapping surfaces</td>
 *     <td>error count</td>
 *     <td>Integer</td>
 *     <td></td>
 *   </tr><tr>
 *     <td>12</td>
 *     <td>non-compliance rate with respect to the rules of the conceptual schema</td>
 *     <td></td>
 *     <td>error rate</td>
 *     <td>Real</td>
 *     <td></td>
 *   </tr><tr>
 *     <td>13</td>
 *     <td>compliance rate with the rules of the conceptual schema</td>
 *     <td></td>
 *     <td>correct items rate</td>
 *     <td>Real</td>
 *     <td></td>
 *   </tr>
 * </table>
 *
 * <p>{@linkplain Measure#getDefinition() Definitions}:</p>
 * <ol start="8">
 *   <li>Indication that an item is not compliant to the rules of the relevant conceptual schema.</li>
 *   <li>Indication that an item complies with the rules of the relevant conceptual.</li>
 *   <li>Count of all items in the data set that are not compliant with the rules of the conceptual schema.</li>
 *   <li>Total number of erroneous overlaps within the data.</li>
 *   <li>Number of items in the data set that are not compliant with the rules of the conceptual schema
 *       in relation to the total number of these items supposed to be in the data set.</li>
 *   <li>Number of items in the data set in compliance with the rules of the conceptual schema
 *       in relation to the total number of items.</li>
 * </ol>
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Alexis Gaillard (Geomatys)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="DQ_ConceptualConsistency", specification=ISO_19157)
public interface ConceptualConsistency extends LogicalConsistency {
}
