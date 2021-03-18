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
 * A 2- or 3-dimensional coordinate system in which position is specified by
 * geodetic latitude, geodetic longitude, and (in the 3D case) ellipsoidal height.
 *
 * <p>This type of CS can be used by coordinate reference systems of type
 * {@link org.opengis.referencing.crs.GeographicCRS}.
 * The following examples describe some possible set of axes for ellipsoidal CS used with the above-cited CRS:</p>
 *
 * <table class="ogc">
 *   <caption>Example 1: used with a two-dimensional Geographic CRS</caption>
 *   <tr><th>Axis name</th> <th>Abbr.</th> <th>Direction</th> <th>Unit</th></tr>
 *   <tr><td>Geodetic latitude</td> <td>φ</td> <td>{@link AxisDirection#NORTH}</td><td>degree</td></tr>
 *   <tr><td>Geodetic longitude</td><td>λ</td> <td>{@link AxisDirection#EAST}</td> <td>degree</td></tr>
 * </table>
 *
 * <table class="ogc">
 *   <caption>Example 2: used with a three-dimensional Geographic CRS</caption>
 *   <tr><th>Axis name</th> <th>Abbr.</th> <th>Direction</th> <th>Unit</th></tr>
 *   <tr><td>Geodetic latitude</td> <td>φ</td> <td>{@link AxisDirection#NORTH}</td><td>degree</td></tr>
 *   <tr><td>Geodetic longitude</td><td>λ</td> <td>{@link AxisDirection#EAST}</td> <td>degree</td></tr>
 *   <tr><td>Ellipsoidal height</td><td>h</td> <td>{@link AxisDirection#UP}</td>   <td>metre</td></tr>
 * </table>
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.1
 * @since   1.0
 *
 * @see CSAuthorityFactory#createEllipsoidalCS(String)
 * @see CSFactory#createEllipsoidalCS(Map, CoordinateSystemAxis, CoordinateSystemAxis)
 * @see CSFactory#createEllipsoidalCS(Map, CoordinateSystemAxis, CoordinateSystemAxis, CoordinateSystemAxis)
 */
@UML(identifier="CS_EllipsoidalCS", specification=ISO_19111)
public interface EllipsoidalCS extends CoordinateSystem {
}
