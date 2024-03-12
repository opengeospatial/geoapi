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
 * Sequence of geodesic segments. The interface essentially combines a
 * <code>Sequence&lt;{@link Geodesic}&gt;</code> into a single object,
 * with the obvious savings of storage space.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 *
 * @see GeometryFactory#createGeodesicString
 */
@UML(identifier="GM_GeodesicString", specification=ISO_19107)
public interface GeodesicString extends CurveSegment {
    /**
     * Returns a sequence of positions between which this {@code GeodesicString} is interpolated
     * using geodesics from the geoid or {@linkplain org.opengis.referencing.datum.Ellipsoid ellipsoid} of the
     * {@linkplain org.opengis.referencing.crs.CoordinateReferenceSystem coordinate reference system} being used.
     * The organization of these points is identical to that in {@link LineString}.
     *
     * @return the control points.
     */
    @UML(identifier="controlPoint", obligation=MANDATORY, specification=ISO_19107)
    PointArray getControlPoints();

    /**
     * The interpolation for a {@code GeodesicString} is
     * "{@linkplain CurveInterpolation#GEODESIC geodesic}".
     *
     * @return always {@link CurveInterpolation#GEODESIC}.
     */
    @UML(identifier="interpolation", obligation=MANDATORY, specification=ISO_19107)
    CurveInterpolation getInterpolation();

    /**
     * Decomposes a geodesic string into an equivalent sequence of geodesic segments.
     *
     * @return the equivalent sequence of geodesic segments.
     */
    @UML(identifier="asGM_Geodesic", obligation=MANDATORY, specification=ISO_19107)
    List<Geodesic> asGeodesics();
}
