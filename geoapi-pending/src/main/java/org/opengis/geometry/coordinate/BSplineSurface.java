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

import java.util.List;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A rational or polynomial parametric surface that is represented by control points, basis
 * functions and possibly weights. If the weights are all equal then the spline is piecewise
 * polynomial. If they are not equal, then the spline is piecewise rational. If the boolean
 * {@link #isPolynomial isPolynomial} is set to {@code true} then the weights shall all be
 * set to 1.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.1
 */
@UML(identifier="GM_BSplineSurface", specification=ISO_19107)
public interface BSplineSurface extends GriddedSurface {
    /**
     * The algebraic degree of the basis functions for the first and second parameter.
     * If only one value is given, then the two degrees are equal.
     *
     * @return the degrees as an array of length 1 or 2.
     */
    @UML(identifier="degree", obligation=MANDATORY, specification=ISO_19107)
    int[] getDegrees();

    /**
     * Identifies particular types of surface which this spline is being used to approximate.
     * It is for information only, used to capture the original intention. If no such approximation
     * is intended, then this method returns {@code null}.
     *
     * @return the type of surface, or {@code null} if this information is not available.
     */
    @UML(identifier="surfaceForm", obligation=OPTIONAL, specification=ISO_19107)
    BSplineSurfaceForm getSurfaceForm();

    /**
     * Returns two sequences of distinct knots used to define the B-spline basis functions for
     * the two parameters. Recall that the knot data type holds information on knot multiplicity.
     *
     * @return the sequence of knots as an array of length 2.
     */
    @UML(identifier="knot", obligation=MANDATORY, specification=ISO_19107)
    List<Knot>[] getKnots();

    /**
     * Gives the type of knot distribution used in defining this spline. This is for information
     * only and is set according to the different construction-functions.
     *
     * @return the type of knot distribution, or {@code null} if none.
     */
    @UML(identifier="knotSpec", obligation=OPTIONAL, specification=ISO_19107)
    KnotType getKnotSpec();

    /**
     * Returns {@code true} if this is a polynomial spline.
     */
    @UML(identifier="isPolynomial", obligation=MANDATORY, specification=ISO_19107)
    boolean isPolynomial();
}
