/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.gm.primitive;

// J2SE direct dependencies
import java.util.List;
import java.util.Arrays;
import java.util.Collections;

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
    public static final SurfaceInterpolation NONE = new SurfaceInterpolation(0,
                                             "None");

    /**
     * The interpolation method shall return points on a single plane. The boundary in this
     * case shall be contained within that plane.
     */
    public static final SurfaceInterpolation PLANAR = new SurfaceInterpolation(1,
                                             "Planar");

    /**
     * The surface is a section of a spherical surface.
     */
    public static final SurfaceInterpolation SPHERICAL = new SurfaceInterpolation(2,
                                             "Spherical");

    /**
     * The surface is a section of a elliptical surface.
     */
    public static final SurfaceInterpolation ELLIPTICAL = new SurfaceInterpolation(3,
                                             "Elliptical");

    /**
     * The surface is a section of a conic surface.
     */
    public static final SurfaceInterpolation CONIC = new SurfaceInterpolation(4,
                                             "Conic");

    /**
     * The control points are organized into adjoining triangles, which form small planar segments.
     */
    public static final SurfaceInterpolation TIN = new SurfaceInterpolation(5,
                                             "Tin");

    /**
     * The control points are organized into a 2-dimensional grid and each cell
     * within the grid is spanned by a surface which shall be defined by a family of curves.
     */
    public static final SurfaceInterpolation PARAMETRIC_CURVE = new SurfaceInterpolation(6,
                                             "Parametric Curve");

    /**
     * The control points are organized into an irregular 2-dimensional grid and
     * each cell within this grid is spanned by a polynomial spline function.
     */
    public static final SurfaceInterpolation POLYNOMIAL_SPLINE = new SurfaceInterpolation(7,
                                             "Polynomial Spline");

    /**
     * The control points are organized into an irregular 2-dimensional grid and each cell
     * within this grid is spanned by a rational (quotient of polynomials) spline function.
     */
    public static final SurfaceInterpolation RATIONAL_SPLINE = new SurfaceInterpolation(8,
                                             "Rational Spline");

    /**
     * The control points are organized into adjoining triangles, each of
     * which is spanned by a polynomial spline function.
     */
    public static final SurfaceInterpolation TRIANGULATED_SPLINE = new SurfaceInterpolation(9,
                                             "Triangulated Spline");

    /**
     * List of all enumeration of this type.
     */
    private static final List/*<SurfaceInterpolation>*/ VALUES = Collections.unmodifiableList(
        Arrays.asList(new SurfaceInterpolation[] {
                      NONE, PLANAR, SPHERICAL, ELLIPTICAL, CONIC, TIN, PARAMETRIC_CURVE,
                      POLYNOMIAL_SPLINE, RATIONAL_SPLINE, TRIANGULATED_SPLINE } ));

    /**
     * Constructs an enum with the given name.
     */
    private SurfaceInterpolation(int ordinal, String name) {
        super(ordinal, name);
    }

    /**
     * Returns the list of <code>SurfaceInterpolation</code>s.
     */
    public static List values() {
        return VALUES;
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public List/*<SurfaceInterpolation>*/ family() {
        return VALUES;
    }
}
