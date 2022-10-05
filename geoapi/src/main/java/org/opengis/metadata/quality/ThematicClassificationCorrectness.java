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
import org.opengis.referencing.operation.Matrix;

import static org.opengis.annotation.Specification.*;


/**
 * Comparison of the classes assigned to features or their attributes to a universe of discourse.
 * The assignment of an item to a certain class can either be correct or incorrect.
 * Depending on the item that is classified, several data quality measures exist.
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
 *     <th>{@linkplain Measure#getParameters() Parameters}</th>
 *     <th>{@linkplain Measure#getValueType() Value type}</th>
 *   </tr><tr>
 *     <td>60</td>
 *     <td>number of incorrectly classified features</td>
 *     <td>error count</td>
 *     <td></td>
 *     <td>Integer</td>
 *   </tr><tr>
 *     <td>61</td>
 *     <td>misclassification rate</td>
 *     <td>error rate</td>
 *     <td></td>
 *     <td>Real</td>
 *   </tr><tr>
 *     <td>62</td>
 *     <td>misclassification matrix</td>
 *     <td></td>
 *     <td></td>
 *     <td>{@link Matrix}</td>
 *   </tr><tr>
 *     <td>63</td>
 *     <td>relative misclassification matrix</td>
 *     <td></td>
 *     <td></td>
 *     <td>{@link Matrix}</td>
 *   </tr><tr>
 *     <td>64</td>
 *     <td>kappa coefficient</td>
 *     <td></td>
 *     <td>n (number of classes)</td>
 *     <td>{@link Matrix}</td>
 *   </tr>
 * </table>
 *
 * <p><b>Note:</b>
 * ISO 19157 declares the <i>misclassification matrix</i> value type as "Integer" or "Real"
 * associated with {@link ValueStructure#MATRIX}. For an object oriented language like Java,
 * a more natural approach is to use an object of specific type for the value.</p>
 *
 * <p>{@linkplain Measure#getDefinition() Definitions}:</p>
 * <ol start="60">
 *   <li>Number of incorrectly classified features</li>
 *   <li>Number of incorrectly classified features relative to the number of features that should be there.</li>
 *   <li>Matrix that indicates the number of items of class (i) classified as class (j).</li>
 *   <li>Matrix that indicates the number of items of class (i) classified as class (j) divided by the number of items of class (i).</li>
 *   <li>coefficient to quantify the proportion of agreement of assignments to classes by removing misclassifications.</li>
 * </ol>
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Alexis Gaillard (Geomatys)
 * @version 3.1
 * @since   2.0
 */
@UML(identifier="DQ_ThematicClassificationCorrectness", specification=ISO_19157)
public interface ThematicClassificationCorrectness extends ThematicAccuracy {
}
