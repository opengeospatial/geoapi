/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2008-2023 Open Geospatial Consortium, Inc.
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
 * Relationship of a Coordinate System (CS) to the Earth, another celestial body or a platform.
 * For {@linkplain org.opengis.referencing.crs.GeodeticCRS geodetic}
 * and {@linkplain org.opengis.referencing.crs.VerticalCRS vertical}
 * coordinate reference systems (<abbr>CRS</abbr>), the datum is known as <dfn>reference frame</dfn>
 * and shall relate the coordinate system to the Earth or other celestial body.
 * With other types of <abbr>CRS</abbr>s, the datum may relate the coordinate system
 * to another physical or virtual object. It may be a moving platform such as a car.
 * The datum itself is not time-dependent, but any transformations of the associated coordinates
 * to an Earth-fixed or other coordinate reference system may contain time-dependent parameters.
 *
 * <h2>Dynamic reference frame</h2>
 * If the subtype of {@code Datum} is geodetic or vertical, the frame-defining parameters may
 * include time evolution to describe the motions of points used to define the reference frame.
 * Then the geodetic or vertical reference frame is
 * {@linkplain org.opengis.referencing.datum.DynamicReferenceFrame dynamic}.
 *
 * @author  OGC Topic 2 (for abstract model and documentation)
 * @author  Martin Desruisseaux (IRD, Geomatys)
 * @version 3.1
 * @since   1.0
 */
package org.opengis.referencing.datum;
