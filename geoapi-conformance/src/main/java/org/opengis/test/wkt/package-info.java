/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2015-2024 Open Geospatial Consortium, Inc.
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

/**
 * Tests <i>Well-Known Text</i> (WKT) parsing.
 * Each {@code *ParserTest} class in this package tests a factory method implemented by the product to test.
 * The factory method is given various WKT strings provided by the international standard defining the WKT format.
 * The tests verify that the objects returned by the factory method have the expected values.
 *
 * <p>The methods tested by this package and the corresponding international standards are:</p>
 *
 * <table class="ogc">
 *   <caption>Methods for parsing WKT</caption>
 *   <tr>
 *     <th>Parsing method</th>
 *     <th>Standard defining the WKT format</th>
 *   </tr><tr>
 *     <td>{@link org.opengis.referencing.crs.CRSFactory#createFromWKT(String)}</td>
 *     <td><a href="http://docs.opengeospatial.org/is/12-063r5/12-063r5.html">OGC 12-063r5 —
 *         Well-known text representation of coordinate reference systems</a></td>
 *   </tr>
 * </table>
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
package org.opengis.test.wkt;
