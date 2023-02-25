/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.geometry.primitive;

import org.opengis.geometry.coordinate.GenericSurface;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Defines a homogeneous portion of a {@linkplain Surface surface}.
 * Each {@code SurfacePatch} shall be in at most one {@linkplain Surface surface}.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 */
@UML(identifier="GM_SurfacePatch", specification=ISO_19107)
public interface SurfacePatch extends GenericSurface {
    /**
     * Returns the patch which own this surface patch. This method is <em>optional</em> since the
     * association in ISO 19107 is navigable only from {@code Surface} to {@code SurfacePatch},
     * not the other way.
     *
     * <div class="note"><b>Note:</b>
     * in the specification, surface patches do not appear except in the
     * context of a surface, and therefore this method should never returns {@code null} which
     * would preclude the use of surface patches except in this manner. While this would not
     * affect the specification, allowing {@code null} owner allows other standards based on
     * ISO 19107 one to use surface patches in a more open-ended manner.
     * </div>
     *
     * @return the owner of this surface patch, or {@code null} if the association is
     *         not available or not implemented that way.
     *
     * @see Surface#getPatches
     * @see CurveSegment#getCurve
     */
    @UML(identifier="surface", obligation=OPTIONAL, specification=ISO_19107)
    Surface getSurface();

    /**
     * Determines the surface interpolation mechanism used for this {@code SurfacePatch}.
     * This mechanism uses the control points and control parameters defined in the various
     * subclasses to determine the position of this {@code SurfacePatch}.
     *
     * @return the interpolation mechanism.
     */
    @UML(identifier="interpolation", obligation=MANDATORY, specification=ISO_19107)
    SurfaceInterpolation getInterpolation();

    /**
     * Specifies the type of continuity between this surface patch and its immediate neighbors
     * with which it shares a boundary curve. The sequence of values corresponds to the
     * {@linkplain Ring rings} in the {@linkplain SurfaceBoundary surface boundary} returned by
     * {@link #getBoundary} for this patch. The default value of "0" means simple continuity, which
     * is a mandatory minimum level of continuity. This level is referred to as "C⁰" in
     * mathematical texts. A value of 1 means that the functions are continuous and differentiable
     * at the appropriate end point: "C¹" continuity. A value of "n" for any integer means
     * <var>n</var>-times differentiable: "Cⁿ" continuity.
     *
     * @return the type of continuity between this surface patch and its immediate neighbors.
     */
    @UML(identifier="numDerivativesOnBoundary", obligation=MANDATORY, specification=ISO_19107)
    int getNumDerivativesOnBoundary();

    /**
     * Returns the boundary of this {@code SurfacePatch} represented as a collection of
     * {@linkplain OrientableCurve orientable curves} organized into {@linkplain Ring rings}
     * by a {@linkplain SurfaceBoundary surface boundary}. The semantics of this operation is
     * the same as that of {@link Surface#getBoundary()}, except that the curves used here may
     * be not be persistent {@linkplain OrientableCurve orientable curve} instances. Transient
     * data type values of {@linkplain Curve curve} are also valid. In the normal case,
     * {@code SurfacePatch}es will share parts of their boundary with the aggregate
     * {@linkplain Surface surface}, and other parts with {@code SurfacePatch}es (not
     * necessarily distinct).
     *
     * @return the boundary of this {@code SurfacePatch}
     */
    @UML(identifier="boundary", obligation=MANDATORY, specification=ISO_19107)
    SurfaceBoundary getBoundary();
}
