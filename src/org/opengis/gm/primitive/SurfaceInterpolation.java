/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.gm.primitive;

// J2SE direct dependencies
import java.util.Arrays;
import java.util.Collections;

// OpenGIS direct dependencies
import org.opengis.util.CodeList;


/**
 * GM_SurfaceInterpolation (Figure 20) is a list of codes that may be used to identify
 * the interpolation mechanisms specified by an application schema. Valid values for
 * "interpolation" include, but are not limited, to the following: a) None (none) -
 * the interior of the surface is not specified. The assumption is that the surface
 * follows the reference surface defined by the coordinate reference system. b) Planar
 * (planar) - the interpolation method shall return points on a single plane. The boundary
 * in this case shall be contained within that plane. c) Spherical (spherical), Elliptical
 * (elliptical), Conic (conic) - the surface is a section of a spherical, elliptical
 * or conic surface. d) TIN (tin) - the control points are organized into adjoining
 * triangles, which form small planar segments. e) Parametric Curve (parametricCurve)
 * - the control points are organized into a 2-dimensional grid and each cell within
 * the grid is spanned by a surface which shall be defined by a family of curves. f)
 * Polynomial Spline (polynomialSpline) - the control points are organized into an irregular
 * 2-dimensional grid and each cell within this grid is spanned by a polynomial spline
 * function. g) Rational Spline (rationalSpline) - the control points are organized
 * into an irregular 2-dimensional grid and each cell within this grid is spanned by
 * a rational (quotient of polynomials) spline function. h) Triangulated Spline (triangulatedSpline)
 * - the control points are organized into adjoining triangles, each of which is spanned
 * by a polynomial spline function. If more than one interpolation description fits
 * the method used, then the most restrictive one will be used. GM_SurfaceInterpolation::
 * none planar spherical elliptical conic tin parametricCurve polynomialSpline rationalSpline
 * triangualtedSpline 
 *  
 * @author GeoAPI
 * @version 1.0
 */
public class SurfaceInterpolation //extends CodeList
{
//    public static final NONE = new GM_SurfaceInterpolation(0 , "none" );
//    public static final PLANAR = new GM_SurfaceInterpolation(1 , "planar" );
//    public static final SPHERICAL = new GM_SurfaceInterpolation(2 , "spherical" );
//    public static final ELLIPTICAL = new GM_SurfaceInterpolation(3 , "elliptical" );
//    public static final CONIC = new GM_SurfaceInterpolation(4 , "conic" );
//    public static final TIN = new GM_SurfaceInterpolation(5 , "tin" );
//    public static final PARAMETRICCURVE = new GM_SurfaceInterpolation(6 , "parametricCurve" );
//    public static final POLYNOMIALSPLINE = new GM_SurfaceInterpolation(7 , "polynomialSpline" );
//    public static final RATIONALSPLINE = new GM_SurfaceInterpolation(8 , "rationalSpline" );
//    public static final TRIANGULATEDSPLINE = new GM_SurfaceInterpolation(9 , "triangulatedSpline" );
//
//    /**
//     * List of all enumeration of this type.
//     */
//    private static final List<GM_SurfaceInterpolation> FAMILY = Collections.unmodifiableList(Array.asList( { NONE , PLANAR , SPHERICAL , ELLIPTICAL , CONIC , TIN , PARAMETRICCURVE , POLYNOMIALSPLINE , RATIONALSPLINE , TRIANGULATEDSPLINE } ));
//
//    /**
//     * Constructs an enum with the given name.
//     */
//    private SurfaceInterpolation(int ordinal, String name)
//    {
//        super(ordinal, name);
//    }
//
//    /**
//     * @inheritDoc
//     */
//    public List<GM_SurfaceInterpolation> family()
//    {
//        return FAMILY;
//    }
}
