/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2019 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.geometry.primitive;

import org.opengis.geometry.coordinate.GenericCurve;
import org.opengis.geometry.coordinate.PointArray;
import org.opengis.annotation.Association;
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
     * In the specification, curve segments do not appear except in the context of a curve,
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
     * @issue https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-63
     */
    @Association("Segmentation")
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
     * Use of these values is only appropriate when the basic curve definition is an underdetermined
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
     * Use of these values is only appropriate when the basic curve definition is an underdetermined
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

    /**
     * Returns an ordered array of point values that lie on the {@linkplain CurveSegment curve segment}.
     * In most cases, these will be related to control points used in the construction of the segment.
     * The control points of a curve segment are use to control its shape, and are not always on the
     * curve segment itself. For example in a spline curve, the curve segment is given as a weighted
     * vector sum of the control points. Each weight function will have a maximum within the
     * constructive parameter interval, which will roughly correspond to the point on the curve
     * where it passes closest that the corresponding control point. These points, the values of
     * the curve at the maxima of the weight functions, will be the sample points for the curve
     * segment.
     *
     * @return the control points.
     *
     * @todo This interface has been removed from ISO 19107:2008 draft, since it is now
     *       inherited from {@link GenericCurve}.
     */
    @UML(identifier="samplePoint", obligation=MANDATORY, specification=ISO_19107)
    PointArray getSamplePoints();

    /**
     * Returns an ordered pair of points, which are the start point and end point of the curve.
     * This method operates with the same semantics as that on {@linkplain Curve#getBoundary curve}
     * except that the end points of a {@code CurveSegment} are not necessarily existing
     * {@linkplain Point points} and thus the boundary may contain transient {@linkplain Point points}.
     *
     * <div class="note"><b>Note:</b>
     * The above {@linkplain CurveBoundary curve boundary} will almost always be two distinct
     * positions, but, like {@linkplain Curve curves}, {@code CurveSegment}s can be cycles in
     * themselves. The most likely scenario is that all of the points used will be transients
     * (constructed to support the return value), except for the start point and end point of
     * the aggregated {@linkplain Curve curve}. These two positions, in the case where the
     * {@linkplain Curve curve} is involved in a {@linkplain org.opengis.geometry.complex.Complex complex},
     * will be represented as {@linkplain Point points} in the same complex.
     * </div>
     *
     * @return the sets of positions on the boundary.
     *
     * @todo This interface has been removed from ISO 19107:2008 draft, since it is now
     *       inherited from {@link GenericCurve}.
     */
    @UML(identifier="boundary", obligation=MANDATORY, specification=ISO_19107)
    CurveBoundary getBoundary();

    /**
     * Reverses the orientation of the parameterizations of the segment.
     *
     * @return the reverse of this curve segment.
     */
    @UML(identifier="reverse", obligation=MANDATORY, specification=ISO_19107)
    CurveSegment reverse();
}
