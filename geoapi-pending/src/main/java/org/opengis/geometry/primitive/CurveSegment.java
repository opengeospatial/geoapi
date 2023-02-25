/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
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

import org.opengis.geometry.coordinate.GenericCurve;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Defines a homogeneous segment of a {@linkplain Curve curve}.
 * Each {@code CurveSegment} shall be in, at most, one {@linkplain Curve curve}.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 */
@UML(identifier="GM_CurveSegment", specification=ISO_19107)
public interface CurveSegment extends GenericCurve {
    /**
     * Returns the curve which own this curve segment. This method is <em>optional</em> since
     * the association in ISO 19107 is navigable only from {@code Curve} to {@code CurveSegment},
     * not the other way.
     *
     * <div class="note"><b>Note:</b>
     * in the specification, curve segments do not appear except in the context of a curve,
     * and therefore this method should never returns {@code null} which would preclude the
     * use of curve segments except in this manner. While this would not affect the specification,
     * allowing {@code null} owner allows other standards based on ISO 19107 one to use curve
     * segments in a more open-ended manner.
     * </div>
     *
     * @return the owner of this curve segment, or {@code null} if the association is
     *         not available or not implemented that way.
     *
     * @see Curve#getSegments
     * @see SurfacePatch#getSurface
     */
    @UML(identifier="curve", obligation=OPTIONAL, specification=ISO_19107)
    Curve getCurve();

    /**
     * Specifies the curve interpolation mechanism used for this segment. This mechanism
     * uses the control points and control parameters to determine the position of this
     * {@code CurveSegment}.
     *
     * @return the interpolation mechanism used for this segment.
     */
    @UML(identifier="interpolation", obligation=MANDATORY, specification=ISO_19107)
    CurveInterpolation getInterpolation();

    /**
     * Specifies the type of continuity between this curve segment and its immediate neighbors.
     * If this is the first curve segment in the curve, this value is ignored.
     *
     * <div class="note"><b>Note:</b>
     * use of these values is only appropriate when the basic curve definition is an underdetermined
     * system. For example, line strings and segments cannot support continuity above C⁰,
     * since there is no spare control parameter to adjust the incoming angle at the end points of
     * the segment. Spline functions on the other hand often have extra degrees of freedom on end
     * segments that allow them to adjust the values of the derivatives to support C¹
     * or higher continuity.
     * </div>
     *
     * @return the type of continuity between this curve segment and its immediate neighbors.
     *
     * @see #getNumDerivativesInterior
     * @see #getNumDerivativesAtEnd
     */
    @UML(identifier="numDerivativesAtStart", obligation=MANDATORY, specification=ISO_19107)
    int getNumDerivativesAtStart();

    /**
     * Specifies the type of continuity that is guaranteed interior to the curve. The default
     * value of "0" means simple continuity, which is a mandatory minimum level of continuity.
     * This level is referred to as "C⁰" in mathematical texts. A value of 1 means
     * that the function and its first derivative are continuous at the appropriate end point:
     * "C¹" continuity. A value of "n" for any integer means the function and its
     * first <var>n</var> derivatives are continuous: "Cⁿ" continuity.
     *
     * @return the type of continuity that is guaranteed interior to the curve.
     *
     * @see #getNumDerivativesAtStart
     * @see #getNumDerivativesAtEnd
     */
    @UML(identifier="numDerivativesInterior", obligation=MANDATORY, specification=ISO_19107)
    int getNumDerivativesInterior();

    /**
     * Specifies the type of continuity between this curve segment and its immediate neighbors.
     * If this is the last curve segment in the curve, this value is ignored.
     *
     * <div class="note"><b>Note:</b>
     * use of these values is only appropriate when the basic curve definition is an underdetermined
     * system. For example, line strings and segments cannot support continuity above C⁰,
     * since there is no spare control parameter to adjust the incoming angle at the end points of
     * the segment. Spline functions on the other hand often have extra degrees of freedom on end
     * segments that allow them to adjust the values of the derivatives to support C¹
     * or higher continuity.
     * </div>
     *
     * @return the type of continuity between this curve segment and its immediate neighbors.
     *
     * @see #getNumDerivativesAtStart
     * @see #getNumDerivativesInterior
     */
    @UML(identifier="numDerivativesAtEnd", obligation=MANDATORY, specification=ISO_19107)
    int getNumDerivativesAtEnd();



}
