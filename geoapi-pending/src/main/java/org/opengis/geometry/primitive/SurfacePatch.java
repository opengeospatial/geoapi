/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2019 Open Geospatial Consortium, Inc.
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
     * In the specification, surface patches do not appear except in the
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
     * @issue https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-63
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
