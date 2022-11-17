/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2021 Open Geospatial Consortium, Inc.
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
 * Degree of adherence of a data set to a specific set of requirements.
 * Usability evaluation may be based on specific user requirements that
 * cannot be described using the other quality elements.
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
 *     <td>101</td>
 *     <td>data product specification passed</td>
 *     <td>correctness indicator</td>
 *     <td>Boolean</td>
 *     <td>{@code true} if all the requirements in the referred data product specification are fulfilled.</td>
 *   </tr><tr>
 *     <td>102</td>
 *     <td>data product specification fail count</td>
 *     <td>error count</td>
 *     <td>Integer</td>
 *     <td></td>
 *   </tr><tr>
 *     <td>103</td>
 *     <td>data product specification pass count</td>
 *     <td>correct items count</td>
 *     <td>Integer</td>
 *     <td></td>
 *   </tr><tr>
 *     <td>104</td>
 *     <td>data product specification fail rate</td>
 *     <td>error rate</td>
 *     <td>Real</td>
 *     <td></td>
 *   </tr><tr>
 *     <td>105</td>
 *     <td>data product specification pass rate</td>
 *     <td>correct items rate</td>
 *     <td>Real</td>
 *     <td></td>
 *   </tr>
 * </table>
 *
 * <p>{@linkplain Measure#getDefinition() Definitions}:</p>
 * <ol start="101">
 *   <li>Indication that all requirements in the referred data product specification are fulfilled.</li>
 *   <li>Number of data product specification requirements that are not fulfilled by the current product/data set.</li>
 *   <li>Number of data product specification requirements that are fulfilled by the current product/data set.</li>
 *   <li>Number of the data product specification requirements that are not fulfilled by the current product/data set
 *       in relation to the total number of data product specification requirements.</li>
 *   <li>Number of the data product specification requirements that are fulfilled by the current product/data set
 *       in relation to the total number of data product specification requirements.</li>
 * </ol>
 *
 * @author  Alexis Gaillard (Geomatys)
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   3.1
 */
@UML(identifier="DQ_UsabilityElement", specification=ISO_19157)
public interface UsabilityElement extends Element {
}
