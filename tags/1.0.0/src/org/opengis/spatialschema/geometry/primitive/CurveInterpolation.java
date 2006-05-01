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
import org.opengis.spatialschema.geometry.DirectPosition; // For javadoc


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
 */
public final class CurveInterpolation extends CodeList {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 170309206092641598L;

    /**
     * List of all enumerations of this type.
     * Must be declared before any enum declaration.
     */
    private static final List VALUES = new ArrayList(10);

    /**
     * The interpolation mechanism shall return {@linkplain DirectPosition direct positions}
     * on a straight line between each consecutive pair of control points.
     */
    public static final CurveInterpolation LINEAR = new CurveInterpolation("LINEAR");

    /**
     * The interpolation mechanism shall return {@linkplain DirectPosition direct positions} on a
     * geodesic curve between each consecutive pair of control points. A geodesic curve is a curve
     * of shortest length. The geodesic shall be determined in the coordinate reference system of
     * the {@linkplain Curve curve} in which the {@linkplain CurveSegment curve segment} is used.
     */
    public static final CurveInterpolation GEODESIC = new CurveInterpolation("GEODESIC");

    /**
     * For each set of three consecutive control points, the middle one being an even offset from
     * the beginning of the sequence of control points, the interpolation mechanism shall return
     * {@linkplain DirectPosition direct positions} on a circular arc passing from the first point
     * through the middle point to the third point. The sequence of control points shall have an
     * odd number of elements. If the 3 points are co-linear, the circular arc becomes a straight
     * line.
     */
    public static final CurveInterpolation CIRCULAR_ARC_3_POINTS = new CurveInterpolation(
                                          "CIRCULAR_ARC_3_POINTS"); // Circular arc by 3 points

    /**
     * For each consecutive pair of control points, the interpolation mechanism shall return
     * {@linkplain DirectPosition direct positions} on a circular arc passing from the first
     * control point to the second control point, such that the associated control parameter
     * determines the offset of the center of the arc from the center point of the chord,
     * positive for leftward and negative for rightward. This form shall only be used in
     * 2 dimensions because of the restricted nature of the definition technique.
     */
    public static final CurveInterpolation CIRCULAR_ARC_2_POINTS_WITH_BULGE = new CurveInterpolation(
                                          "CIRCULAR_ARC_2_POINTS_WITH_BULGE"); // Circular arc by 2 points and bulge factor

    /**
     * For each set of four consecutive control points, the interpolation mechanism shall return
     * {@linkplain DirectPosition direct positions} on an elliptical arc passing from the first
     * control point through the middle control points in order to the fourth control point. If
     * the 4 control points are co-linear, the arc becomes a straight line. If the 4 control points
     * are on the same circle, the arc becomes a circular one.
     */
    public static final CurveInterpolation ELLIPTICAL = new CurveInterpolation(
                                          "ELLIPTICAL"); // Elliptical arc

    /**
     * Uses a Cornu's spiral or clothoid interpolation.
     */
    public static final CurveInterpolation CLOTHOID = new CurveInterpolation(
                                          "CLOTHOID");

    /**
     * Same as {@linkplain #ELLIPTICAL elliptical arc} but using 5 consecutive control points
     * to determine a conic section.
     */
    public static final CurveInterpolation CONIC = new CurveInterpolation(
                                          "CONIC"); // Conic arc
    /**
     * The control points are ordered as in a line-string, but they are spanned by a polynomial
     * spline function. Normally, the degree of continuity is determined by the degree of the
     * polynomials chosen.
     */
    public static final CurveInterpolation POLYNOMIAL_SPLINE = new CurveInterpolation(
                                          "POLYNOMIAL_SPLINE"); // Polynomial Spline

    /**
     * The control points are interpolated using initial tangents and cubic polynomials, a
     * form of degree 3 polynomial spline.
     */
    public static final CurveInterpolation CUBIC_SPLINE = new CurveInterpolation(
                                          "CUBIC_SPLINE"); // Cubic Spline

    /**
     * The control points are ordered as in a line string, but they are spanned by a
     * rational (quotient of polynomials) spline function. Normally, the degree of continuity
     * is determined by the degree of the polynomials chosen.
     */
    public static final CurveInterpolation RATIONAL_SPLINE = new CurveInterpolation(
                                          "RATIONAL_SPLINE"); // Rational Spline

    /**
     * Constructs an enum with the given name. The new enum is
     * automatically added to the list returned by {@link #values}.
     *
     * @param name The enum name. This name must not be in use by an other enum of this type.
     */
    public CurveInterpolation(final String name) {
        super(name, VALUES);
    }

    /**
     * Returns the list of <code>CurveInterpolation</code>s.
     */
    public static CurveInterpolation[] values() {
        synchronized (VALUES) {
            return (CurveInterpolation[]) VALUES.toArray(new CurveInterpolation[VALUES.size()]);
        }
    }

    /**
     * Returns the list of enumerations of the same kind than this enum.
     */
    public /*{CurveInterpolation}*/ CodeList[] family() {
        return values();
    }
}
