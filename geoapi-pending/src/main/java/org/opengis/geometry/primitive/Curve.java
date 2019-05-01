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

import java.util.List;
import org.opengis.geometry.coordinate.GenericCurve;
import org.opengis.annotation.Association;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Curve with a positive orientation. {@code Curve} is
 * a descendent subtype of {@link Primitive} through {@link OrientablePrimitive}. It is the basis
 * for 1-dimensional geometry. A curve is a continuous image of an open interval and so could be
 * written as a parameterized function such as
 *
 * <code>c(t):(a,&nbsp;b) → Eⁿ</code>
 *
 * where "t" is a real parameter and Eⁿ is Euclidean space of dimension <var>n</var>
 * (usually 2 or 3, as determined by the coordinate reference system). Any other parameterization
 * that results in the same image curve, traced in the same direction, such as any linear shifts
 * and positive scales such as
 *
 * <code>e(t) = c(a&nbsp;+&nbsp;t(b-a)):(0,1) → Eⁿ</code>,
 *
 * is an equivalent representation of the same curve. For the sake of simplicity, {@code Curve}s
 * should be parameterized by arc length, so that the parameterization operation inherited from
 * {@link GenericCurve} will be valid for parameters between 0 and the length of the curve.
 * <p>
 * Curves are continuous, connected, and have a measurable length in terms of the coordinate system.
 * The orientation of the curve is determined by this parameterization, and is consistent with the
 * tangent function, which approximates the derivative function of the parameterization and shall
 * always point in the "forward" direction. The parameterization of the reversal of the curve defined
 * by
 *
 * <code>c(t):(a,&nbsp;b) → Eⁿ</code>
 *
 * would be defined by a function of the form
 *
 * <code>s(t) = c(a&nbsp;+&nbsp;b&nbsp;-&nbsp;t):(a,&nbsp;b) → Eⁿ</code>.
 *
 * <p>
 * A curve is composed of one or more curve segments. Each curve segment within a curve may be
 * defined using a different interpolation method. The curve segments are connected to one another,
 * with the end point of each segment except the last being the start point of the next segment in
 * the segment list.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 *
 * @see PrimitiveFactory#createCurve
 */
@UML(identifier="GM_Curve", specification=ISO_19107)
public interface Curve extends OrientableCurve, GenericCurve {
    /**
     * Lists the components {@linkplain CurveSegment curve segments} of {@code Curve}, each
     * of which defines the direct position of points along a portion of the curve. The order of
     * the {@linkplain CurveSegment curve segments} is the order in which they are used to trace
     * this {@code Curve}. For a particular parameter interval, the {@code Curve} and
     * {@link CurveSegment} agree.
     *
     * @return the list of curve segments. Should never be {@code null} neither empty.
     *
     * @see CurveSegment#getCurve
     * @see Surface#getPatches
     * @issue https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-63
     */
    @Association("Segmentation")
    @UML(identifier="segment", obligation=MANDATORY, specification=ISO_19107)
    List<? extends CurveSegment> getSegments();

    /**
     * Returns the orientable curves associated with this curve.
     *
     * @return the orientable curves as an array of length 2.
     *
     * @see OrientableCurve#getPrimitive
     * @issue https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-63
     */
    @Association("Oriented")
    @UML(identifier="proxy", obligation=MANDATORY, specification=ISO_19107)
    OrientableCurve[] getProxy();
}
