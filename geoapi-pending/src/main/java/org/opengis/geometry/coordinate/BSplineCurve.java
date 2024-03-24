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

import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A piecewise parametric polynomial or rational curve described
 * in terms of control points and basis functions. If the weights in the knots are equal
 * then it is a polynomial spline. If not, then it is a rational function spline. If
 * the Boolean {@link #isPolynomial} is set to {@code true} then the weights shall all be set to 1.
 * A B-spline curve is a piecewise Bézier curve if it is quasi-uniform except that the
 * interior knots have multiplicity "degree" rather than having multiplicity one. In
 * this subtype the knot spacing shall be 1.0, starting at 0.0. A piecewise Bézier curve
 * that has only two knots, 0.0, and 1.0, each of multiplicity (degree+1), is equivalent
 * to a simple Bézier curve.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 *
 * @see GeometryFactory#createBSplineCurve
 */
@UML(identifier="GM_BSplineCurve", specification=ISO_19107)
public interface BSplineCurve extends SplineCurve {
    /**
     * The algebraic degree of the basis functions.
     */
    @UML(identifier="degree", obligation=MANDATORY, specification=ISO_19107)
    int getDegree();

    /**
     * Identifies particular types of curve which this spline is being used to
     * approximate. It is for information only, used to capture the original intention.
     * If no such approximation is intended, then the value of this attribute is {@code null}.
     */
    @UML(identifier="curveForm", obligation=OPTIONAL, specification=ISO_19107)
    SplineCurveForm getCurveForm();

    /**
     * Gives the type of knot distribution used in defining this spline.
     * This is for information only and is set according to the different construction-functions.
     */
    @UML(identifier="knotSpec", obligation=OPTIONAL, specification=ISO_19107)
    KnotType getKnotSpec();

    /**
     * {@code true} if this is a polynomial spline.
     */
    @UML(identifier="isPolynomial", obligation=MANDATORY, specification=ISO_19107)
    boolean isPolynomial();
}
