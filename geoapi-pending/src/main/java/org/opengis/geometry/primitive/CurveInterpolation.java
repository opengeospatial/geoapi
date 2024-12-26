/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2003-2024 Open Geospatial Consortium, Inc.
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

import org.opengis.util.CodeList;
import org.opengis.geometry.DirectPosition;         // For javadoc
import org.opengis.annotation.UML;
import org.opengis.geoapi.internal.Vocabulary;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * List of codes that may be used to identify the interpolation mechanisms. As a code list,
 * there is no intention of limiting the potential values of {@code CurveInterpolation}.
 * Subtypes of {@link CurveSegment} can be spawned directly through subclassing, or indirectly
 * by specifying an interpolation method and an associated control parameters record to support
 * it.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 */
@Vocabulary(capacity=10)
@UML(identifier="GM_CurveInterpolation", specification=ISO_19107)
public final class CurveInterpolation extends CodeList<CurveInterpolation> {
    /**
     * Serial number for compatibility with different versions.
     */
    private static final long serialVersionUID = 170309206092641598L;

    /**
     * The interpolation mechanism shall return {@linkplain DirectPosition direct positions}
     * on a straight line between each consecutive pair of control points.
     */
    @UML(identifier="linear", obligation=CONDITIONAL, specification=ISO_19107)
    public static final CurveInterpolation LINEAR = new CurveInterpolation("LINEAR");

    /**
     * The interpolation mechanism shall return {@linkplain DirectPosition direct positions} on a
     * geodesic curve between each consecutive pair of control points. A geodesic curve is a curve
     * of shortest length. The geodesic shall be determined in the coordinate reference system of
     * the {@linkplain Curve curve} in which the {@linkplain CurveSegment curve segment} is used.
     */
    @UML(identifier="geodesic", obligation=CONDITIONAL, specification=ISO_19107)
    public static final CurveInterpolation GEODESIC = new CurveInterpolation("GEODESIC");

    /**
     * For each set of three consecutive control points, the middle one being an even offset from
     * the beginning of the sequence of control points, the interpolation mechanism shall return
     * {@linkplain DirectPosition direct positions} on a circular arc passing from the first point
     * through the middle point to the third point. The sequence of control points shall have an
     * odd number of elements. If the 3 points are co-linear, the circular arc becomes a straight
     * line.
     */
    @UML(identifier="circularArc3Points", obligation=CONDITIONAL, specification=ISO_19107)
    public static final CurveInterpolation CIRCULAR_ARC_3_POINTS = new CurveInterpolation(
                                          "CIRCULAR_ARC_3_POINTS");             // Circular arc by 3 points

    /**
     * For each consecutive pair of control points, the interpolation mechanism shall return
     * {@linkplain DirectPosition direct positions} on a circular arc passing from the first
     * control point to the second control point, such that the associated control parameter
     * determines the offset of the center of the arc from the center point of the chord,
     * positive for leftward and negative for rightward. This form shall only be used in
     * 2 dimensions because of the restricted nature of the definition technique.
     */
    @UML(identifier="circularArc2PointWithBulge", obligation=CONDITIONAL, specification=ISO_19107)
    public static final CurveInterpolation CIRCULAR_ARC_2_POINTS_WITH_BULGE = new CurveInterpolation(
                                          "CIRCULAR_ARC_2_POINTS_WITH_BULGE");  // Circular arc by 2 points and bulge factor

    /**
     * For each set of four consecutive control points, the interpolation mechanism shall return
     * {@linkplain DirectPosition direct positions} on an elliptical arc passing from the first
     * control point through the middle control points in order to the fourth control point. If
     * the 4 control points are co-linear, the arc becomes a straight line. If the 4 control points
     * are on the same circle, the arc becomes a circular one.
     */
    @UML(identifier="elliptical", obligation=CONDITIONAL, specification=ISO_19107)
    public static final CurveInterpolation ELLIPTICAL = new CurveInterpolation(
                                          "ELLIPTICAL");                        // Elliptical arc

    /**
     * Uses a Cornu's spiral or clothoid interpolation.
     */
    @UML(identifier="clothoid", obligation=CONDITIONAL, specification=ISO_19107)
    public static final CurveInterpolation CLOTHOID = new CurveInterpolation(
                                          "CLOTHOID");

    /**
     * Same as {@linkplain #ELLIPTICAL elliptical arc} but using 5 consecutive control points
     * to determine a conic section.
     */
    @UML(identifier="conic", obligation=CONDITIONAL, specification=ISO_19107)
    public static final CurveInterpolation CONIC = new CurveInterpolation(
                                          "CONIC");                             // Conic arc
    /**
     * The control points are ordered as in a line-string, but they are spanned by a polynomial
     * spline function. Normally, the degree of continuity is determined by the degree of the
     * polynomials chosen.
     */
    @UML(identifier="polynomialSpline", obligation=CONDITIONAL, specification=ISO_19107)
    public static final CurveInterpolation POLYNOMIAL_SPLINE = new CurveInterpolation(
                                          "POLYNOMIAL_SPLINE");                 // Polynomial Spline

    /**
     * The control points are interpolated using initial tangents and cubic polynomials, a
     * form of degree 3 polynomial spline.
     */
    @UML(identifier="cubicSpline", obligation=CONDITIONAL, specification=ISO_19107)
    public static final CurveInterpolation CUBIC_SPLINE = new CurveInterpolation(
                                          "CUBIC_SPLINE");                      // Cubic Spline

    /**
     * The control points are ordered as in a line string, but they are spanned by a
     * rational (quotient of polynomials) spline function. Normally, the degree of continuity
     * is determined by the degree of the polynomials chosen.
     */
    @UML(identifier="rationalSpline", obligation=CONDITIONAL, specification=ISO_19107)
    public static final CurveInterpolation RATIONAL_SPLINE = new CurveInterpolation(
                                          "RATIONAL_SPLINE");                   // Rational Spline

    /**
     * Constructs an element of the given name.
     *
     * @param  name  the name of the new element.
     *        This name must not be in use by another element of this type.
     */
    private CurveInterpolation(final String name) {
        super(name);
    }

    /**
     * Returns the list of {@code CurveInterpolation}s.
     *
     * @return the list of codes declared in the current JVM.
     */
    public static CurveInterpolation[] values() {
        return values(CurveInterpolation.class);
    }

    /**
     * Returns the list of codes of the same kind as this code list element.
     * Invoking this method is equivalent to invoking {@link #values()}, except that
     * this method can be invoked on an instance of the parent {@code CodeList} class.
     *
     * @return all code {@linkplain #values() values} for this code list.
     */
    @Override
    public CurveInterpolation[] family() {
        return values();
    }

    /**
     * Returns the curve interpolation that matches the given name, or returns a new value if there is no match.
     * This methods returns the first instance (in declaration order) for which the {@linkplain #name() name}
     * is {@linkplain String#equalsIgnoreCase(String) equals, ignoring case}, to the given name.
     * If no existing instance is found, then a new one is created for the given name.
     *
     * @param  code  the name of the code to fetch or to create.
     * @return a code matching the given name.
     */
    public static CurveInterpolation valueOf(String code) {
        return valueOf(CurveInterpolation.class, code, CurveInterpolation::new).get();
    }
}
