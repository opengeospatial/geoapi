/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.spatialschema.geometry.primitive;

// J2SE directdependencies
import java.util.List;
import java.util.ArrayList;

// OpenGIS direct dependencies
import org.opengis.util.CodeList;


/**
 * List of codes that may be used to identify the interpolation mechanisms.
 *
 * @UML codelist GM_SurfaceInterpolation
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public final class SurfaceInterpolation extends CodeList {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = -8717227444427181227L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List VALUES = new ArrayList(10);

    /**
     * The interior of the surface is not specified. The assumption is that the surface
     * follows the reference surface defined by the coordinate reference system.
     */
    public static final SurfaceInterpolation NONE = new SurfaceInterpolation(
                                            "NONE");

    /**
     * The interpolation method shall return points on a single plane. The boundary in this
     * case shall be contained within that plane.
     */
    public static final SurfaceInterpolation PLANAR = new SurfaceInterpolation(
                                            "PLANAR");

    /**
     * The surface is a section of a spherical surface.
     */
    public static final SurfaceInterpolation SPHERICAL = new SurfaceInterpolation(
                                            "SPHERICAL");

    /**
     * The surface is a section of a elliptical surface.
     */
    public static final SurfaceInterpolation ELLIPTICAL = new SurfaceInterpolation(
                                            "ELLIPTICAL");

    /**
     * The surface is a section of a conic surface.
     */
    public static final SurfaceInterpolation CONIC = new SurfaceInterpolation(
                                            "CONIC");

    /**
     * The control points are organized into adjoining triangles, which form small planar segments.
     */
    public static final SurfaceInterpolation TIN = new SurfaceInterpolation(
                                            "TIN");

    /**
     * The control points are organized into a 2-dimensional grid and each cell
     * within the grid is spanned by a surface which shall be defined by a family of curves.
     */
    public static final SurfaceInterpolation PARAMETRIC_CURVE = new SurfaceInterpolation(
                                            "PARAMETRIC_CURVE");

    /**
     * The control points are organized into an irregular 2-dimensional grid and
     * each cell within this grid is spanned by a polynomial spline function.
     */
    public static final SurfaceInterpolation POLYNOMIAL_SPLINE = new SurfaceInterpolation(
                                            "POLYNOMIAL_SPLINE");

    /**
     * The control points are organized into an irregular 2-dimensional grid and each cell
     * within this grid is spanned by a rational (quotient of polynomials) spline function.
     */
    public static final SurfaceInterpolation RATIONAL_SPLINE = new SurfaceInterpolation(
                                            "RATIONAL_SPLINE");

    /**
     * The control points are organized into adjoining triangles, each of
     * which is spanned by a polynomial spline function.
     */
    public static final SurfaceInterpolation TRIANGULATED_SPLINE = new SurfaceInterpolation(
                                            "TRIANGULATED_SPLINE");

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    public SurfaceInterpolation(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of <code>SurfaceInterpolation</code>s.
     */
    public static SurfaceInterpolation[] values() {
        synchronized (VALUES) {
            return (SurfaceInterpolation[]) VALUES.toArray(new SurfaceInterpolation[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{SurfaceInterpolation}*/ CodeList[] family() {
        return values();
    }
}
