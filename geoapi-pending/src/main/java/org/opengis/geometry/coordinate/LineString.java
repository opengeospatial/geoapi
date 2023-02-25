/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2004-2023 Open Geospatial Consortium, Inc.
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
import org.opengis.geometry.primitive.CurveSegment;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A sequence of line segments, each having a parameterization like the one
 * {@link LineSegment}. The class essentially combines a
 * {@link List List&lt;LineSegment&gt;} into a single object,
 * with the obvious savings of storage space.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 *
 * @see GeometryFactory#createLineString
 */
@UML(identifier="GM_LineString", specification=ISO_19107)
public interface LineString extends CurveSegment {
    /**
     * Returns a sequence of positions between which the curve is linearly interpolated.
     * The first position in the sequence is the {@linkplain #getStartPoint start Point}
     * of this {@code LineString}, and the last point in the sequence is the
     * {@linkplain #getEndPoint end point} of this {@code LineString}.
     *
     * @return the control points between which the curve is linearly interpolated.
     */
    @UML(identifier="controlPoint", obligation=MANDATORY, specification=ISO_19107)
    PointArray getControlPoints();

    /**
     * Decomposes a line string into an equivalent sequence of line segments.
     *
     * @return the sequence of line segments.
     */
    @UML(identifier="asGM_LineSegment", obligation=MANDATORY, specification=ISO_19107)
    List<LineSegment> asLineSegments();
}
