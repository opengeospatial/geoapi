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
 * A {@linkplain GriddedSurface gridded surface} given as a family of conic sections whose
 * {@linkplain #getControlPoints control points} vary linearly. A 5-point ellipse with all
 * defining positions identical is a point. Thus, a truncated elliptical cone can be given
 * as a 2×5 set of control points
 *
 * &lt;&lt;P1, P1, P1, P1, P1&gt;, &lt;P2, P3, P4, P5, P6&gt;&gt;.
 *
 * P1 is the apex of the cone. P2, P3, P4, P5, and P6 are any five distinct points around
 * the base ellipse of the cone. If the horizontal curves are circles as opposed to ellipses,
 * then a circular cone can be constructed using &lt;&lt;P1, P1, P1&gt;, &lt;P2, P3, P4&gt;&gt;.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="GM_Cone", specification=ISO_19107)
public interface Cone extends GriddedSurface {
}
