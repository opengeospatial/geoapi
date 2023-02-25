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
 * Correctness of the explicitly encoded topological characteristics of the data set.
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
 *     <th>{@linkplain Measure#getAliases() Aliases}</th>
 *     <th>{@linkplain Measure#getBasicMeasure() Basic measure}</th>
 *     <th>{@linkplain Measure#getParameters() Parameters}</th>
 *     <th>{@linkplain Measure#getValueType() Value type}</th>
 *   </tr><tr>
 *     <td>21</td>
 *     <td>number of faulty point-curve connections</td>
 *     <td>extraneous nodes</td>
 *     <td>error count</td>
 *     <td></td>
 *     <td>Integer</td>
 *   </tr><tr>
 *     <td>22</td>
 *     <td>rate of faulty point-curve connections</td>
 *     <td></td>
 *     <td>error rate</td>
 *     <td></td>
 *     <td>Real</td>
 *   </tr><tr>
 *     <td>23</td>
 *     <td>number of missing connections due to undershoots</td>
 *     <td>undershoots</td>
 *     <td>error count</td>
 *     <td>search distance from the end of a dangling line</td>
 *     <td>Integer</td>
 *   </tr><tr>
 *     <td>24</td>
 *     <td>number of missing connections due to overshoots</td>
 *     <td>overshoots</td>
 *     <td>error count</td>
 *     <td>search tolerance of minimum allowable length in the data set</td>
 *     <td>Integer</td>
 *   </tr><tr>
 *     <td>25</td>
 *     <td>number of invalid slivers</td>
 *     <td>slivers</td>
 *     <td>error count</td>
 *     <td>maximum sliver area size, thickness quotient</td>
 *     <td>Integer</td>
 *   </tr><tr>
 *     <td>26</td>
 *     <td>number of invalid self-intersect errors</td>
 *     <td>loops</td>
 *     <td>error count</td>
 *     <td></td>
 *     <td>Integer</td>
 *   </tr><tr>
 *     <td>27</td>
 *     <td>number of invalid self-overlap errors</td>
 *     <td>kickbacks</td>
 *     <td>error count</td>
 *     <td></td>
 *     <td>Integer</td>
 *   </tr>
 * </table>
 *
 * <p>{@linkplain Measure#getDefinition() Definitions}:</p>
 * <ol start="21">
 *   <li>Number of faulty point-curve connections in the data set.</li>
 *   <li>Number of faulty link node connections in relation to the number of supposed link node connections.</li>
 *   <li>Count of items in the data set, within the parameter tolerance, that are mismatched due to undershoots.</li>
 *   <li>Count of items in the data set, within the parameter tolerance, that are mismatched due to overshoots.</li>
 *   <li>Count of all items in the data set that are invalid sliver surfaces.</li>
 *   <li>Count of all items in the data that illegally intersect with themselves.</li>
 *   <li>Count of all items in the data that illegally self overlap.</li>
 * </ol>
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Alexis Gaillard (Geomatys)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="DQ_TopologicalConsistency", specification=ISO_19157)
public interface TopologicalConsistency extends LogicalConsistency {
}
