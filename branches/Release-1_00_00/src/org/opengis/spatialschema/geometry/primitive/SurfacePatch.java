/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.spatialschema.geometry.primitive;

// OpenGIS direct dependencies
import org.opengis.spatialschema.geometry.geometry.GenericSurface;


/**
 * Defines a homogeneous portion of a {@linkplain Surface surface}.
 * Each <code>SurfacePatch</code> shall be in at most one {@linkplain Surface surface}.
 *
 * @UML type GM_SurfacePatch
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface SurfacePatch extends GenericSurface {
    /**
     * Returns the patch which own this surface patch.
     *
     * <blockquote><font size=2>
     * <strong>NOTE:</strong> In this specification, surface patches do not appear except in the
     * context of a surface, and therefore this method should never returns <code>null</code>
     * which would preclude the use of surface patches except in this manner. While this would
     * not affect this specification, allowing <code>null</code> owner allows other standards
     * based on this one to use surface patches in a more open-ended manner.
     * </font></blockquote>
     *
     * @return The owner of this surface patch, or <code>null</code> if none.
     * @UML association surface
     *
     * @see Surface#getPatches
     */
    public Surface getSurface();

    /**
     * Determines the surface interpolation mechanism used for this <code>SurfacePatch</code>.
     * This mechanism uses the control points and control parameters defined in the various
     * subclasses to determine the position of this <code>SurfacePatch</code>.
     *
     * @return The interpolation mechanism.
     * @UML operation interpolation
     */
    public SurfaceInterpolation getInterpolation();

    /**
     * Specifies the type of continuity between this surface patch and its immediate neighbors
     * with which it shares a boundary curve. The sequence of values corresponds to the
     * {@linkplain Ring rings} in the {@linkplain SurfaceBoundary surface boundary} returned by
     * {@link #getBoundary} for this patch. The default value of "0" means simple continuity, which
     * is a mandatory minimum level of continuity. This level is referred to as "C<sup>0</sup>" in
     * mathematical texts. A value of 1 means that the functions are continuous and differentiable
     * at the appropriate end point: "C<sup>1</sup>" continuity. A value of "n" for any integer means
     * <var>n</var>-times differentiable: "C<sup>n</sup>" continuity.
     *
     * @return The type of continuity between this surface patch and its immediate neighbors.
     * @UML operation numDerivativesOnBoundary
     */
    public int getNumDerivativesOnBoundary();

    /**
     * Returns the boundary of this <code>SurfacePatch</code> represented as a collection of
     * {@linkplain OrientableCurve orientable curves} organized into {@linkplain Ring rings}
     * by a {@linkplain SurfaceBoundary surface boundary}. The semantics of this operation is
     * the same as that of {@link Surface#getBoundary()}, except that the curves used here may
     * be not be persistent {@linkplain OrientableCurve orientable curve} instances. Transient
     * data type values of {@linkplain Curve curve} are also valid. In the normal case,
     * <code>SurfacePatch</code>es will share parts of their boundary with the aggregate
     * {@linkplain Surface surface}, and other parts with <code>SurfacePatch</code>es (not
     * necessarily distinct).
     *
     * @return The boundary of this <code>SurfacePatch</code>
     * @UML operation boundary
     */
    public SurfaceBoundary getBoundary();
}
