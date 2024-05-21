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
package org.opengis.geometry.primitive;

import java.util.List;
import org.opengis.geometry.coordinate.GenericCurve;
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
     */
    @UML(identifier="segment", obligation=MANDATORY, specification=ISO_19107)
    List<? extends CurveSegment> getSegments();

    /**
     * Returns the orientable curves associated with this curve.
     *
     * @return the orientable curves as an array of length 2.
     *
     * @see OrientableCurve#getPrimitive
     */
    @UML(identifier="proxy", obligation=MANDATORY, specification=ISO_19107)
    OrientableCurve[] getProxy();
}
