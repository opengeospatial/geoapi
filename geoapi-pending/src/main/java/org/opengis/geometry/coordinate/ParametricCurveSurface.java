/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2003-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.geometry.coordinate;

import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.primitive.Curve;
import org.opengis.geometry.primitive.Surface;
import org.opengis.geometry.primitive.SurfacePatch;
import org.opengis.geometry.primitive.CurveInterpolation;
import org.opengis.geometry.complex.Complex;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * The surface patches that make up the parametric curve surfaces.
 * {@code ParametricCurveSurface} are all continuous families of curves,
 * given by a constructive function of the form:
 *
 * <blockquote>
 * {@code surface}(<var>s</var>,<var>t</var>):
 * [<var>a</var>,<var>b</var>]×[<var>c</var>,<var>d</var>] → {@link DirectPosition}
 * </blockquote>
 *
 * By fixing the value of either parameter, we have a one-parameter family of curves.
 *
 * <blockquote>
 * c<sub>t</sub>(<var>s</var>) = c<sub>s</sub>(<var>t</var>) =
 * {@code surface}(<var>s</var>,<var>t</var>);
 * </blockquote>
 *
 * <p>The functions on {@code ParametricCurveSurface} shall expose these two families of curves. The
 * first gives us the "horizontal" cross sections c<sub>t</sub>(<var>s</var>), the latter the
 * "vertical" cross sections c<sub>s</sub>(<var>t</var>). The terms "horizontal" and "vertical"
 * refer to the parameter space and need not be either horizontal or vertical curves in the coordinate
 * reference system. The table below lists some possible pairs of types for these surface curves
 * (other representations of these same surfaces are possible).</p>
 *
 * <table class="ogc">
 *   <caption>Examples</caption>
 *   <tr style="background:#CCCCFF" class="TableHeadingColor">
 *     <th>&nbsp;Surface type&nbsp;</th>
 *     <th>&nbsp;Horizontal Curve type&nbsp;</th>
 *     <th>&nbsp;Vertical curve type&nbsp;</th>
 *   </tr><tr>
 *     <td>&nbsp;{@link Cylinder}&nbsp;</td>
 *     <td>&nbsp;Circle, constant radii&nbsp;</td>
 *     <td>&nbsp;Line Segment&nbsp;</td>
 *   </tr><tr>
 *     <td>&nbsp;{@link Cone}&nbsp;</td>
 *     <td>&nbsp;Circle, decreasing radii&nbsp;</td>
 *     <td>&nbsp;Line Segment&nbsp;</td>
 *   </tr><tr>
 *     <td>&nbsp;{@link Sphere}&nbsp;</td>
 *     <td>&nbsp;Circle of constant latitude&nbsp;</td>
 *     <td>&nbsp;Circle of constant longitude&nbsp;</td>
 *   </tr><tr>
 *     <td>&nbsp;{@link BilinearGrid}&nbsp;</td>
 *     <td>&nbsp;Line string&nbsp;</td>
 *     <td>&nbsp;Line string&nbsp;</td>
 *   </tr><tr>
 *     <td>&nbsp;{@link BicubicGrid}&nbsp;</td>
 *     <td>&nbsp;Cubic spline&nbsp;</td>
 *     <td>&nbsp;Cubic spline&nbsp;</td>
 *   </tr>
 * </table>
 *
 * <p>The two partial derivatives of the surface parameterization, <b>i</b> and <b>j</b> are given by:</p>
 *
 * <blockquote>TODO: copy equations there</blockquote>
 *
 * and
 *
 * <blockquote>TODO: copy equations there</blockquote>
 *
 * The default {@linkplain #getUpNormal upNormal} for the surface shall be the vector cross product
 * of these two curve derivatives when they are both non-zero:
 *
 * <blockquote>
 * <b>k</b> = <b>i</b> × <b>j</b>
 * </blockquote>
 *
 * If the coordinate reference system is 2D, then the vector <b>k</b> extends the local coordinate
 * system by supplying an "upward" elevation vector. In this case the vector basis
 * (<b>i</b>,&nbsp;<b>j</b>) must be a right hand system, that is to say, the oriented angle from
 * <b>i</b> to <b>j</b> must be less than 180°. This gives a right-handed "moving frame" of local
 * coordinate axes given by &lt;<b>i</b>, <b>j</b>&gt;. A moving frame is defined to be a continuous
 * function from the geometric object to a basis for the local tangent space of that object. For
 * curves, this is the derivative of the curve, the local tangent. For surfaces, this is a local
 * pair of tangents. Parameterized curve surfaces have a natural moving frame and it shall be used
 * as defined in this paragraph to define the upNormal of the surface.
 *
 * <div class="note"><b>Note:</b>
 * the existence of a viable moving frame is the definition of "orientable"
 * manifold. This is why the existence of a continuous {@linkplain #getUpNormal upNormal} implies
 * that the surface is orientable. Non-orientable surfaces, such as the Möbius band and Klein bottle
 * are counter-intuitive. {@link Surface} forbids their use in application schemas conforming to
 * the ISO 19107 standard. Klein bottles cannot even be constructed in 3D space, but require 4D
 * space for non-singular representations.
 * </div>
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="GM_ParametricCurveSurface", specification=ISO_19107)
public interface ParametricCurveSurface extends SurfacePatch {
    /**
     * Indicates the type of surface curves used to traverse the surface horizontally
     * with respect to the parameter <var>s</var>.
     */
    @UML(identifier="horizontalCurveType", obligation=MANDATORY, specification=ISO_19107)
    CurveInterpolation getHorizontalCurveType();

    /**
     * Indicates the type of surface curves used to traverse the surface vertically with
     * respect to the parameter <var>t</var>.
     */
    @UML(identifier="verticalCurveType", obligation=MANDATORY, specification=ISO_19107)
    CurveInterpolation getVerticalCurveType();

    /**
     * Constructs a curve that traverses the surface horizontally with respect to the parameter
     * <var>s</var>. This curve holds the parameter <var>t</var> constant.
     *
     * <div class="note"><b>Note:</b>
     * the curve returned by this function or by the corresponding vertical curve function, are
     * normally not part of any {@linkplain Complex complex} to which this surface is included.
     * These are, in general, calculated transient values. The exceptions to this may occur at
     * the extremes of the parameter space. The boundaries of the parameter space support for
     * the surface map normally to the boundaries of the target surfaces.
     * </div>
     *
     * @param  t The <var>t</var> value to hold constant.
     * @return the curve that traverses the surface.
     */
    @UML(identifier="horizontalCurve", obligation=MANDATORY, specification=ISO_19107)
    Curve horizontalCurve(double t);

    /**
     * Constructs a curve that traverses the surface vertically with respect to the parameter
     * <var>t</var>. This curve holds the parameter <var>s</var> constant.
     *
     * @param  s The <var>s</var> value to hold constant.
     * @return the curve that traverses the surface.
     */
    @UML(identifier="verticalCurve", obligation=MANDATORY, specification=ISO_19107)
    Curve verticalCurve(double s);

    /**
     * Traverses the surface both vertically and horizontally.
     */
    @UML(identifier="surface", obligation=MANDATORY, specification=ISO_19107)
    DirectPosition surface(double s, double t);
}
