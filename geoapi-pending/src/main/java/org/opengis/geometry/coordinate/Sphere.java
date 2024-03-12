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
 * A {@linkplain GriddedSurface gridded surface} given as a family of circles whose positions vary
 * linearly along the axis of the sphere, and whose radius varies in proportion to the cosine function
 * of the central angle. The horizontal circles resemble lines of constant latitude, and the vertical
 * arcs resemble lines of constant longitude. If the {@linkplain #getControlPoints control points}
 * are sorted in terms of increasing longitude, and increasing latitude, the
 * {@linkplain #getUpNormal upNormal} of a sphere is the outward normal.
 * <p>
 * <strong>Example:</strong> If we take a gridded set of latitudes and longitudes in degrees,
 * (<var>u</var>, <var>v</var>), such as
 *
 * <blockquote><pre>
 * (-90, -180) (-90, -90) (-90, 0) (-90, 90) (-90, 180)
 * (-45, -180) (-45, -90) (-45, 0) (-45, 90) (-45, 180)
 * (  0, -180) (  0, -90) (  0, 0) (  0, 90) (  0, 180)
 * ( 45, -180) ( 45, -90) ( 45, 0) (45, -90) ( 45, 180)
 * ( 90, -180) ( 90, -90) ( 90, 0) (90, -90) ( 90, 180)
 * </pre></blockquote>
 *
 * and map these points to 3D using the usual equations
 * (where <var>R</var> is the radius of the required sphere)
 *
 * <blockquote>
 * <var>z</var> = <var>R</var> sin(<var>u</var>)
 * <var>x</var> = <var>R</var> cos(<var>u</var>) sin(<var>v</var>)
 * <var>y</var> = <var>R</var> cos(<var>u</var>) cos(<var>v</var>)
 * </blockquote>
 *
 * we have a sphere of radius <var>R</var>, centered at (0,&nbsp;0), as a gridded surface. Notice
 * that the entire first row and the entire last row of the control points map to a single point
 * each in 3D Euclidean space, North and South poles respectively, and that each horizontal curve
 * closes back on it self forming a geometric cycle. This gives us a metrically bounded (of finite
 * size), topologically unbounded (not having a boundary, a cycle) surface.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="GM_Sphere", specification=ISO_19107)
public interface Sphere extends GriddedSurface {
}
