/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.metadata.quality;

import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;


/**
 * Correctness of the explicitly encoded topological characteristics of the data set as
 * described by the scope.
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
