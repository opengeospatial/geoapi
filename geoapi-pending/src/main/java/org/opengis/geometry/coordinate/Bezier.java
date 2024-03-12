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
 * Polynomial splines that use Bezier or Bernstein polynomials for interpolation
 * purposes. An <var>n</var>-long control point array shall create a polynomial
 * curve of degree <var>n</var> that defines the entire curve segment. These curves
 * are defined in terms of a set of basis functions called the Bézier or Bernstein
 * polynomials.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 *
 * @todo Add equations from ISO 19107 to the javadoc.
 */
@UML(identifier="GM_Bezier", specification=ISO_19107)
public interface Bezier extends BSplineCurve {
}
