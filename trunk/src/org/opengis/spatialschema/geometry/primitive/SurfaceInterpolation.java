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
import org.opengis.util.CodeList;


/**
 * List of codes that may be used to identify the interpolation mechanisms.
 *
 * @UML codelist GM_SurfaceInterpolation
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @revisit Provide a public constructor for allowing custom codes.
 */
public class SurfaceInterpolation extends CodeList {
    /**
     * The interior of the surface is not specified. The assumption is that the surface
     * follows the reference surface defined by the coordinate reference system.
     */
    public static final SurfaceInterpolation NONE = new SurfaceInterpolation(
                                            "NONE", 0);

    /**
     * The interpolation method shall return points on a single plane. The boundary in this
     * case shall be contained within that plane.
     */
    public static final SurfaceInterpolation PLANAR = new SurfaceInterpolation(
                                            "PLANAR", 1);

    /**
     * The surface is a section of a spherical surface.
     */
    public static final SurfaceInterpolation SPHERICAL = new SurfaceInterpolation(
                                            "SPHERICAL", 2);

    /**
     * The surface is a section of a elliptical surface.
     */
    public static final SurfaceInterpolation ELLIPTICAL = new SurfaceInterpolation(
                                            "ELLIPTICAL", 3);

    /**
     * The surface is a section of a conic surface.
     */
    public static final SurfaceInterpolation CONIC = new SurfaceInterpolation(
                                            "CONIC", 4);

    /**
     * The control points are organized into adjoining triangles, which form small planar segments.
     */
    public static final SurfaceInterpolation TIN = new SurfaceInterpolation(
                                            "TIN", 5);

    /**
     * The control points are organized into a 2-dimensional grid and each cell
     * within the grid is spanned by a surface which shall be defined by a family of curves.
     */
    public static final SurfaceInterpolation PARAMETRIC_CURVE = new SurfaceInterpolation(
                                            "PARAMETRIC_CURVE", 6);

    /**
     * The control points are organized into an irregular 2-dimensional grid and
     * each cell within this grid is spanned by a polynomial spline function.
     */
    public static final SurfaceInterpolation POLYNOMIAL_SPLINE = new SurfaceInterpolation(
                                            "POLYNOMIAL_SPLINE", 7);

    /**
     * The control points are organized into an irregular 2-dimensional grid and each cell
     * within this grid is spanned by a rational (quotient of polynomials) spline function.
     */
    public static final SurfaceInterpolation RATIONAL_SPLINE = new SurfaceInterpolation(
                                            "RATIONAL_SPLINE", 8);

    /**
     * The control points are organized into adjoining triangles, each of
     * which is spanned by a polynomial spline function.
     */
    public static final SurfaceInterpolation TRIANGULATED_SPLINE = new SurfaceInterpolation(
                                            "TRIANGULATED_SPLINE", 9);

    /**
     * List of all enumeration of this type.
     */
    private static final SurfaceInterpolation[] VALUES = new SurfaceInterpolation[] {
                      NONE, PLANAR, SPHERICAL, ELLIPTICAL, CONIC, TIN, PARAMETRIC_CURVE,
                      POLYNOMIAL_SPLINE, RATIONAL_SPLINE, TRIANGULATED_SPLINE };

    /**
     * Constructs an enum with the given name.
     */
    private SurfaceInterpolation(final String name, final int ordinal) {
        super(name, ordinal);
    }

    /**
     * Returns the list of <code>SurfaceInterpolation</code>s.
     */
    public static SurfaceInterpolation[] values() {
        return (SurfaceInterpolation[]) VALUES.clone();
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{SurfaceInterpolation}*/ CodeList[] family() {
        return values();
    }
}
