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
package org.opengis.referencing.crs;

import java.util.Map;
import org.opengis.referencing.cs.CoordinateSystem;
import org.opengis.referencing.operation.Conversion;
import org.opengis.annotation.UML;
import static org.opengis.annotation.Specification.*;


/**
 * A coordinate reference system that is defined by its coordinate conversion from another CRS
 * but is not a projected CRS.
 * This category includes coordinate reference systems derived from a projected CRS.
 *
 * <p>A {@code DerivedCRS} instance may also implement one of the interfaces listed below,
 * provided that the conditions in the right column are meet:</p>
 *
 * <table class="ogc">
 *   <caption>Derived CRS types</caption>
 *   <tr><th>Type</th>                   <th>Conditions</th></tr>
 *   <tr><td>{@link GeodeticCRS}</td>    <td>Base CRS is also a {@code GeodeticCRS}.</td></tr>
 *   <tr><td>{@link VerticalCRS}</td>    <td>Base CRS is also a {@code VerticalCRS}.</td></tr>
 *   <tr><td>{@link TemporalCRS}</td>    <td>Base CRS is also a {@code TemporalCRS}.</td></tr>
 *   <tr><td>{@link EngineeringCRS}</td> <td>Base CRS is a {@code GeodeticCRS}, {@code ProjectedCRS} or {@code EngineeringCRS}.</td></tr>
 * </table>
 *
 * @departure integration
 *   ISO 19111 defines a {@code SC_DerivedCRSType} code list with the following values:
 *   {@code geodetic}, {@code vertical}, {@code engineering} and {@code image}.
 *   But ISO 19162 takes a slightly different approach without such code list.
 *   Instead, ISO 19162 writes the derived CRS using the WKT keyword of the corresponding CRS type
 *   ({@code “GeodCRS”}, {@code “VertCRS”}, {@code “TimeCRS”} or {@code “EngCRS”}).
 *   GeoAPI follows a similar path by <strong>not</strong> providing a {@code DerivedCRSType} code list.
 *   Instead, we recommend to implement the corresponding interface as documented in the above table.
 *   Then, Java expressions like {@code (baseCRS instanceof FooCRS)} provides the same capability
 *   than the code list with more flexibility. For example it allows to use a derived CRS of type “vertical”
 *   with API expecting an instance of {@code VerticalCRS}.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 *
 * @see CRSAuthorityFactory#createDerivedCRS(String)
 * @see CRSFactory#createDerivedCRS(Map, CoordinateReferenceSystem, Conversion, CoordinateSystem)
 */
@UML(identifier="SC_DerivedCRS", specification=ISO_19111)
public interface DerivedCRS extends GeneralDerivedCRS {
}
