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
import org.opengis.gm.geometry.DirectPosition; // For javadoc


/**
 * List of codes that may be used to identify the interpolation mechanisms. As a code list,
 * there is no intention of limiting the potential values of <code>CurveInterpolation</code>.
 * Subtypes of {@link CurveSegment} can be spawned directly through subclassing, or indirectly
 * by specifying an interpolation method and an associated control parameters record to support
 * it.
 *
 * @UML codelist GM_CurveInterpolation
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @revisit Provide a public constructor for allowing custom codes.
 */
public class CurveInterpolation extends CodeList {
    /**
     * The interpolation mechanism shall return {@link DirectPosition}s
     * on a straight line between each consecutive pair of control points.
     */
    public static final CurveInterpolation LINEAR = new CurveInterpolation(0,
                                           "Linear");

    /**
     * The interpolation mechanism shall return {@link DirectPosition}s on a geodesic curve between
     * each consecutive pair of control points. A geodesic curve is a curve of shortest length. The
     * geodesic shall be determined in the coordinate reference system of the {@link Curve} in which
     * the {@link CurveSegment} is used.
     */
    public static final CurveInterpolation GEODESIC = new CurveInterpolation(1,
                                           "Geodesic");

    /**
     * For each set of three consecutive control points, the middle one being an even offset from
     * the beginning of the sequence of control points, the interpolation mechanism shall return
     * {@link DirectPosition}s on a circular arc passing from the first point through the middle
     * point to the third point. The sequence of control points shall have an odd number of elements.
     * If the 3 points are co-linear, the circular arc becomes a straight line.
     */
    public static final CurveInterpolation CIRCULAR_ARC_3POINTS = new CurveInterpolation(2,
                                           "Circular arc by 3 points");

    /**
     * For each consecutive pair of control points, the interpolation mechanism shall return
     * {@link DirectPosition}s on a circular arc passing from the first control point to the
     * second control point, such that the associated control parameter determines the offset
     * of the center of the arc from the center point of the chord, positive for leftward and
     * negative for rightward. This form shall only be used in 2 dimensions because of the
     * restricted nature of the definition technique.
     */
    public static final CurveInterpolation CIRCULAR_ARC_2POINTS_WITH_BULGE = new CurveInterpolation(3,
                                           "Circular arc by 2 points and bulge factor");

    /**
     * For each set of four consecutive control points, the interpolation mechanism shall return
     * {@link DirectPosition}s on an elliptical arc passing from the first control point through
     * the middle control points in order to the fourth control point. If the 4 control points are
     * co-linear, the arc becomes a straight line. If the 4 control points are on the same circle,
     * the arc becomes a circular one.
     */
    public static final CurveInterpolation ELLIPTICAL = new CurveInterpolation(4,
                                           "Elliptical arc");

    /**
     * Uses a Cornu's spiral or clothoid interpolation.
     */
    public static final CurveInterpolation CLOTHOID = new CurveInterpolation(5,
                                           "Clothoid");

    /**
     * Same as {@linkplain #ELLIPTICAL elliptical arc} but using 5 consecutive control points
     * to determine a conic section.
     */
    public static final CurveInterpolation CONIC = new CurveInterpolation(6,
                                           "Conic arc");
    /**
     * The control points are ordered as in a line-string, but they are spanned by a polynomial
     * spline function. Normally, the degree of continuity is determined by the degree of the
     * polynomials chosen.
     */
    public static final CurveInterpolation POLYNOMIAL_SPLINE = new CurveInterpolation(7,
                                           "Polynomial Spline");

    /**
     * The control points are interpolated using initial tangents and cubic polynomials, a
     * form of degree 3 polynomial spline.
     */
    public static final CurveInterpolation CUBIC_SPLINE = new CurveInterpolation(8,
                                           "Cubic Spline");

    /**
     * The control points are ordered as in a line string, but they are spanned by a
     * rational (quotient of polynomials) spline function. Normally, the degree of continuity
     * is determined by the degree of the polynomials chosen.
     */
    public static final CurveInterpolation RATIONAL_SPLINE = new CurveInterpolation(9,
                                           "Rational Spline");

    /**
     * List of all enumeration of this type.
     */
    private static final List/*<CurveInterpolation>*/ FAMILY = Collections.unmodifiableList(
        Arrays.asList( new CurveInterpolation[] {
                       LINEAR, GEODESIC, CIRCULAR_ARC_3POINTS, CIRCULAR_ARC_2POINTS_WITH_BULGE,
                       ELLIPTICAL, CLOTHOID, CONIC, POLYNOMIAL_SPLINE, CUBIC_SPLINE,
                       RATIONAL_SPLINE } ));

    /**
     * Constructs an enum with the given name.
     */
    private CurveInterpolation(int ordinal, String name) {
        super(ordinal, name);
    }

    /**
     * Returns the list of <code>CurveInterpolation</code>s.
     */
    public List/*<CurveInterpolation>*/ family() {
        return FAMILY;
    }
}
