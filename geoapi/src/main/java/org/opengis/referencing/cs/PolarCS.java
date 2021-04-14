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
package org.opengis.referencing.cs;

import java.util.Map;
import org.opengis.annotation.UML;
import static org.opengis.annotation.Specification.*;


/**
 * A 2-dimensional coordinate system in which position is specified by the distance from the
 * origin and the angle between the line from the origin to a point and a reference direction.
 *
 * <p>This type of CS can be used by coordinate reference systems of type
 * {@link org.opengis.referencing.crs.EngineeringCRS}.
 * The following examples describe some possible set of axes for polar CS used with the above-cited CRS:</p>
 *
 * <table class="ogc">
 *   <caption>Example: used with an Engineering CRS</caption>
 *   <tr><th>Axis name</th> <th>Abbr.</th> <th>Direction</th> <th>Unit</th></tr>
 *   <tr><td>Distance</td> <td>r</td> <td>{@code AxisDirection.valueOf("AWAY_FROM")}</td> <td>metre</td></tr>
 *   <tr><td>Bearing</td>  <td>Θ</td> <td>{@code AxisDirection.valueOf("CLOCKWISE")}</td> <td>degree</td></tr>
 * </table>
 *
 * <div class="note"><b>Note:</b>
 * the above example uses two axis directions that are not defined in ISO 19111,
 * but found in ISO 19162 as "{@code awayFrom}" and "{@code clockwise}".</div>
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   1.0
 *
 * @see CylindricalCS
 * @see CSAuthorityFactory#createPolarCS(String)
 * @see CSFactory#createPolarCS(Map, CoordinateSystemAxis, CoordinateSystemAxis)
 */
@UML(identifier="CS_PolarCS", specification=ISO_19111)
public interface PolarCS extends CoordinateSystem {
}
