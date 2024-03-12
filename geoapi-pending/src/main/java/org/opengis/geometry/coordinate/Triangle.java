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

import java.util.List;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A planar polygon defined by 3 corners. That is, a triangle would be the result of a constructor
 * of the form: {@code Polygon(LineString({P1, P2, P3, P1}))} where <var>P₁</var>,
 * <var>P₂</var>, and <var>P₃</var> are three {@linkplain Position positions}.
 * Triangles have no holes. Triangle shall be used to construct
 * {@linkplain TriangulatedSurface triangulated surfaces}.
 * <p>
 * <strong>Note:</strong> The points in a triangle can be located in terms of their corner points
 * by defining a set of barycentric coordinates, three nonnegative numbers <var>c₁</var>,
 * <var>c₂</var>, and <var>c₃</var> such that
 *
 * <var>c₁</var> + <var>c₂</var> + <var>c₃</var> = 1.0.
 *
 * Then, each point <var>P</var> in the triangle can be expressed for some set of barycentric coordinates as:
 *
 * <blockquote>
 * P = <var>c₁</var>⋅<var>P₁</var> +
 *     <var>c₂</var>⋅<var>P₂</var> +
 *     <var>c₃</var>⋅<var>P₃</var>
 * </blockquote>
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="GM_Triangle", specification=ISO_19107)
public interface Triangle extends Polygon {
    /**
     * Returns the triangle corner. The list must contains exactly 3 elements.
     */
    @UML(identifier="corners", obligation=MANDATORY, specification=ISO_19107)
    List<Position> getCorners();

    /**
     * Returns the patch which own this surface patch.
     */
    @UML(identifier="surface", obligation=MANDATORY, specification=ISO_19107)
    TriangulatedSurface getSurface();
}
