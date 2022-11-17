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

import javax.measure.Quantity;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;


/**
 * Accuracy of quantitative attributes.
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
 *     <th>{@linkplain Measure#getBasicMeasure() Basic measure}</th>
 *     <th>{@linkplain Measure#getValueType() Value type}</th>
 *   </tr><tr>
 *     <td>68</td>
 *     <td>attribute value uncertainty at 68.3% significance level</td>
 *     <td>LE68.3 or LE68.3(r)</td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td>69</td>
 *     <td>attribute value uncertainty at 50% significance level</td>
 *     <td>LE50 or LE50(r)</td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td>70</td>
 *     <td>attribute value uncertainty at 90% significance level</td>
 *     <td>LE90 or LE90(r)</td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td>71</td>
 *     <td>attribute value uncertainty at 95% significance level</td>
 *     <td>LE95 or LE95(r)</td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td>72</td>
 *     <td>attribute value uncertainty at 99% significance level</td>
 *     <td>LE99 or LE99(r)</td>
 *     <td>{@link Quantity}</td>
 *   </tr><tr>
 *     <td>73</td>
 *     <td>attribute value uncertainty at 99.8% significance level</td>
 *     <td>LE99.8 or LE99.8(r)</td>
 *     <td>{@link Quantity}</td>
 *   </tr>
 * </table>
 *
 * <p>{@linkplain Measure#getDefinition() Definitions}:</p>
 * <ol start="68">
 *   <li>Half length of the interval defined by an upper and a lower limit, in which the true value lies with probability 68.3%.</li>
 *   <li>Half length of the interval defined by an upper and a lower limit, in which the true value lies with probability 50%.</li>
 *   <li>Half length of the interval defined by an upper and a lower limit, in which the true value lies with probability 90%.</li>
 *   <li>Half length of the interval defined by an upper and a lower limit, in which the true value lies with probability 95%.</li>
 *   <li>Half length of the interval defined by an upper and a lower limit, in which the true value lies with probability 99%.</li>
 *   <li>Half length of the interval defined by an upper and a lower limit, in which the true value lies with probability 99.8%.</li>
 * </ol>
 *
 * @author  Martin Desruisseaux (IRD)
 * @author  Alexis Gaillard (Geomatys)
 * @version 3.1
 *
 * @see NonQuantitativeAttributeCorrectness
 *
 * @since 2.0
 */
@UML(identifier="DQ_QuantitativeAttributeAccuracy", specification=ISO_19157)
public interface QuantitativeAttributeAccuracy extends ThematicAccuracy {
}
