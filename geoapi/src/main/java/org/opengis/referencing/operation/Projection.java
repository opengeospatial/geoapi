/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2011 Open Geospatial Consortium, Inc.
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
package org.opengis.referencing.operation;


/**
 * A {@linkplain org.opengis.referencing.operation.Conversion conversion} transforming
 * (<var>longitude</var>,<var>latitude</var>) coordinates to cartesian coordinates
 * (<var>x</var>,<var>y</var>). Although some map projections can be represented as a
 * geometric process, in general a map projection is a set of formulae that converts geodetic
 * latitude and longitude to plane (map) coordinates. Height plays no role in this process,
 * which is entirely two-dimensional. The same map projection can be applied to many
 * {@linkplain org.opengis.referencing.crs.GeographicCRS geographic CRSs}, resulting in many
 * {@linkplain org.opengis.referencing.crs.ProjectedCRS projected CRSs} each of which is related
 * to the same {@linkplain org.opengis.referencing.datum.GeodeticDatum geodetic datum} as the
 * geographic CRS on which it was based.
 * <P>
 * An unofficial list of projections and their parameters can
 * be found <A HREF="http://www.remotesensing.org/geotiff/proj_list/">there</A>.
 * Most projections expect the following parameters:
 *  <code>"semi_major"</code> (mandatory),
 *  <code>"semi_minor"</code> (mandatory),
 *  <code>"central_meridian"</code> (default to 0),
 *  <code>"latitude_of_origin"</code> (default to 0),
 *  <code>"scale_factor"</code> (default to 1),
 *  <code>"false_easting"</code> (default to 0) and
 *  <code>"false_northing"</code> (default to 0).
 *
 * @departure extension
 *   This interface is not part of the ISO specification. It has been added in GeoAPI at user
 *   request, in order to provide a way to know the kind of map projection.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 *
 * @see org.opengis.referencing.crs.ProjectedCRS
 * @see <A HREF="http://mathworld.wolfram.com/MapProjection.html">Map projections on MathWorld</A>
 */
public interface Projection extends Conversion {
}
