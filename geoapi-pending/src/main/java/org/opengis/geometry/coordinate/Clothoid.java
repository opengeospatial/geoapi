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

import org.opengis.geometry.primitive.CurveSegment;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * The clothoid (or Cornu's spiral), a plane curve whose curvature is a fixed function of
 * its length. In suitably chosen coordinates it is given by Fresnel's integrals:
 *
 * @todo TODO: paste the equation here
 *
 * This geometry is mainly used as a transition curve between curves of type straight
 * line/circular arc or circular arc/circular arc. With this curve type it is possible
 * to achieve a C2-continuous transition between the above mentioned curve types. One
 * formula for the clothoid is <var>A</var>² = <var>R</var>×<var>t</var>
 * where <var>A</var> is a constant, <var>R</var> is the varying radius of curvature along
 * the curve and <var>t</var> is the length along the curve and given in the Fresnel integrals.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 2.0
 */
@UML(identifier="GM_Clothoid", specification=ISO_19107)
public interface Clothoid extends CurveSegment {
    /**
     * Returns an affine mapping that places the curve defined by the Fresnel Integrals
     * into the coordinate reference system of this object.
     */
    @UML(identifier="refLocation", obligation=MANDATORY, specification=ISO_19107)
    AffinePlacement getReferenceLocation();

    /**
     * Gives the value for <var>A</var> in the equations above.
     */
    @UML(identifier="scaleFactor", obligation=MANDATORY, specification=ISO_19107)
    double getScaleFactor();

    /**
     * Returns the arc length distance from the inflection point that will be the
     * {@linkplain #getStartPoint start point} for this curve segment. This shall
     * be lower limit <var>t</var> used in the Fresnel integral and is the value
     * of the constructive parameter of this curve segment at its start point. The
     * start parameter can be either positive or negative. The parameter <var>t</var>
     * acts as a constructive parameter.
     *
     * <div class="note"><b>Note:</b>
     * if 0 lies between the {@linkplain #getStartConstructiveParam start constructive
     * parameter} and {@linkplain #getEndConstructiveParam end constructive parameter} of the
     * clothoid, then the curve goes through the clothoid's inflection point, and the direction
     * of its radius of curvature, given by the second derivative vector, changes sides
     * with respect to the tangent vector. The term "length" for the parameter {@code t}
     * is applicable only in the parameter space, and its relation to arc length after use of
     * the placement, and with respect to the coordinate reference system of the curve is not
     * deterministic.
     * </div>
     */
    @UML(identifier="startParameter", obligation=MANDATORY, specification=ISO_19107)
    double getStartConstructiveParam();

    /**
     * Returns the arc length distance from the inflection point that will be the
     * {@linkplain #getEndPoint end point} for this curve segment. This shall be
     * upper limit <var>t</var> used in the Fresnel integral and is the constructive
     * parameter of this curve segment at its end point. The end constructive param
     * can be either positive or negative.
     */
    @UML(identifier="endParameter", obligation=MANDATORY, specification=ISO_19107)
    double getEndConstructiveParam();
}
