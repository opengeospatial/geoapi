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
import org.opengis.geometry.primitive.CurveInterpolation;
import org.opengis.geometry.primitive.CurveSegment;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Similar to a {@linkplain LineString line string} except that the interpolation is
 * by circular arcs. Since it requires 3 points to determine a circular arc, the
 * {@linkplain #getControlPoints control points} are treated as a sequence of overlapping
 * sets of 3 {@linkplain Position positions}, the start of each arc, some point between the
 * start and end, and the end of each arc. Since the end of each arc is the start of the next,
 * this {@linkplain Position position} is not repeated in the {@linkplain #getControlPoints
 * control points} sequence.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 *
 * @see GeometryFactory#createArcString
 * @see ArcStringByBulge#asArcString
 */
@UML(identifier="GM_ArcString", specification=ISO_19107)
public interface ArcString extends CurveSegment {
    /**
     * Returns the number of circular arcs in the string. Since the interpolation method
     * requires overlapping sets of 3 positions, the number of arcs determines the number
     * of {@linkplain #getControlPoints control points}:
     *
     * <blockquote>
     * <pre>numArc = ({@link #getControlPoints controlPoints}.length - 1)/2</pre>
     * </blockquote>
     *
     * @return the number of circular arcs.
     */
    @UML(identifier="numArc", obligation=MANDATORY, specification=ISO_19107)
    int getNumArc();

    /**
     * Returns the sequence of points used to control the arcs in this string. The first three
     * {@linkplain Position positions} in the sequence determines the first arc. Any three
     * consecutive {@linkplain Position positions} beginning with an odd offset, determine
     * another arc in the string.
     *
     * @return the control points. The array size is <code>2*{@link #getNumArc numArc}&nbsp;+1</code>.
     */
    @UML(identifier="controlPoints", obligation=MANDATORY, specification=ISO_19107)
    PointArray getControlPoints();

    /**
     * The interpolation for a {@code ArcString} is
     * "{@linkplain CurveInterpolation#CIRCULAR_ARC_3_POINTS circular arc by 3 points}".
     *
     * @return always {@link CurveInterpolation#CIRCULAR_ARC_3_POINTS}.
     */
    @UML(identifier="interpolation", obligation=MANDATORY, specification=ISO_19107)
    CurveInterpolation getInterpolation();

    /**
     * Constructs a sequence of arcs that is the geometric equivalent of this arc string.
     *
     * @return the sequence of arcs.
     */
    @UML(identifier="asGM_Arc", obligation=MANDATORY, specification=ISO_19107)
    List<Arc> asArcs();
}
