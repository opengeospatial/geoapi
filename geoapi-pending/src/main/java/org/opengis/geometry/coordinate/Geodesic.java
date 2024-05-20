/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2003-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.geometry.coordinate;

import org.opengis.annotation.UML;

import static org.opengis.annotation.Specification.*;


/**
 * Two distinct positions joined by a geodesic curve. The control points of a {@code Geodesic}
 * shall all lie on the geodesic between its start point and end point. Between these two points,
 * a geodesic curve defined from the {@linkplain org.opengis.referencing.datum.Ellipsoid ellipsoid} or geoid model
 * used by the {@linkplain org.opengis.referencing.crs.CoordinateReferenceSystem coordinate reference system} may
 * be used to interpolate other positions. Any other point in the {@link #getControlPoints controlPoint}
 * array must fall on this geodesic.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 */
@UML(identifier="GM_Geodesic", specification=ISO_19107)
public interface Geodesic extends GeodesicString {
}
