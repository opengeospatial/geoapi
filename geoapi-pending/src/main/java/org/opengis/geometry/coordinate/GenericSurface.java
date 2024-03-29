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

import org.opengis.geometry.DirectPosition;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Common interface for {@linkplain org.opengis.geometry.primitive.Surface surface} and
 * {@linkplain org.opengis.geometry.primitive.SurfacePatch surface patch}. {@code Surface}
 * and {@code SurfacePatch} represent sections of surface geometry,
 * and therefore share a number of operation signatures.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 *
 * @todo Investigate why this interface doesn't extends {@link org.opengis.geometry.Geometry},
 *       since it is a cause of difficulty with {@link org.opengis.coverage.Coverage}.
 */
@UML(identifier="GM_GenericSurface", specification=ISO_19107)
public interface GenericSurface {
    /**
     * Returns a vector perpendicular to the {@code GenericSurface} at the
     * {@linkplain DirectPosition direct position} passed, which must be on this
     * {@code GenericSurface}. The upward normal always points upward in a
     * manner consistent with the boundary. This means that the exterior boundary
     * of the surface is counterclockwise when viewed from the side of the surface
     * indicated by the {@code upNormal}. Interior boundaries are clockwise.
     * The side of the surface indicated by the {@code upNormal} is referred
     * to as the "top." The function "upNormal" shall be continuous and the length
     * of the normal shall always be equal to 1.0.
     *
     * <div class="note"><b>Note:</b>
     * the upNormal along a boundary of a solid always points away from the
     * solid. This is a slight semantics problem in dealing with voids within solids, where the
     * upNormal (for sake of mathematical consistency) points into the center of the voided region,
     * which linguistically can be considered the interior of the void. What the confusion is here
     * is that the basic linguistic metaphors used in most languages for "interior of solid" and
     * for "interior of container" use "inward" in inconsistent manners from a topological point
     * of view. The void "in" rock is not inside the rock in the same manner as the solid material
     * that makes up the substance of the rock. Nor is the coffee "in" the cup the same "in" as
     * the ceramic glass "in" the cup. The use of these culturally derived metaphors may not be
     * consistent across all languages, some of which may use different prepositions for these two
     * different concepts. This specification uses the linguistically neutral concept of "interior"
     * derived from mathematics (topology).
     * </div>
     *
     * @param point The point on this {@code GenericSurface} where to compute the upNormal.
     * @return the upNormal unit vector.
     */
    @UML(identifier="upNormal", obligation=MANDATORY, specification=ISO_19107)
    double[] getUpNormal(DirectPosition point);

    /**
     * Returns the sum of the lengths of all the boundary components of this
     * {@code GenericSurface}. Since perimeter, like length, is an accumulation
     * (integral) of distance, its return value shall be in a reference system appropriate
     * for measuring distances.
     *
     * <div class="note"><b>Note:</b>
     * the perimeter is defined as the sum of the lengths of all boundary
     * components. The length of a curve or of a collection of curves is always positive and
     * non-zero (unless the curve is pathological). This means that holes in surfaces will
     * contribute positively to the total perimeter.
     * </div>
     *
     * @return the perimeter.
     * @unitof Length
     */
    @UML(identifier="perimeter", obligation=MANDATORY, specification=ISO_19107)
    double getPerimeter();

    /**
     * Returns the area of this {@code GenericSurface}. The area of a 2-dimensional geometric
     * object shall be a numeric measure of its surface area (in a square unit of distance). Since
     * area is an accumulation (integral) of the product of two distances, its return value shall
     * be in a unit of measure appropriate for measuring distances squared, such as meters squared
     * (m²).
     *
     * <div class="note"><b>Note:</b>
     * consistent with the definition of surface as a set of
     * {@linkplain DirectPosition direct positions}, holes in the surfaces will not contribute to
     * the total area. If the usual Green's Theorem (or more general Stokes' Theorem) integral is
     * used, the integral around the holes in the surface are subtracted from the integral
     * about the exterior of the surface patch.
     * </div>
     *
     * @return the area.
     * @unitof Area
     */
    @UML(identifier="area", obligation=MANDATORY, specification=ISO_19107)
    double getArea();
}
