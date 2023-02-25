/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2005-2023 Open Geospatial Consortium, Inc.
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
package org.opengis.coverage;

import java.util.Set;
import org.opengis.geometry.DirectPosition;
import org.opengis.geometry.primitive.Surface;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A coverage whose domain consists of a collection of {@linkplain Surface surfaces}. In most cases,
 * the surfaces that constitute the domain of a coverage are mutually exclusive and exhaustively
 * partition the extent of the coverage. Surfaces or their boundaries may be of any shape. The
 * boundaries of component surfaces often correspond to natural phenomena and are highly irregular.
 * <p>
 * <b>Example:</b> A coverage that represents soil types typically has a spatial domain
 *                 composed of surfaces with irregular boundaries.
 * <p>
 * Any set of polygons can be used as a spatial domain for a discrete surface coverage. Spatial
 * domains composed of congruent polygons are very common. Very often, these domains are composed
 * of congruent rectangles or regular hexagons. The geometry of such a tessellation may be
 * described in terms of a quadrilateral grid or a hexagonal grid. The spatial domain of a
 * discrete surface coverage may also consist of the triangles that compose a TIN, or the
 * polygons of a Thiessen polygon network.
 *
 * @version ISO 19123:2004
 * @author  Alessio Fabiani
 * @author  Martin Desruisseaux (IRD)
 * @since   GeoAPI 2.1
 *
 * @todo evaluate and evaluateInverse
 */
@UML(identifier="CV_DiscreteSurfaceCoverage", specification=ISO_19123)
public interface DiscreteSurfaceCoverage extends DiscreteCoverage {
    /**
     * Returns the TIN coverage associated to this surface coverage, or {@code null} if none.
     * The following constraint must hold:
     * <p>
     * <code>{@linkplain #getElements element}.{@linkplain SurfaceValuePair#getGeometry geometry}
     * == triangleSource.{@linkplain ValueTriangle#getControlValues controlValue}.{@link
     * ValueObject#getGeometry geometry}</code>
     *
     * @return the TIN coverage.
     *
     * @todo Review the constraints. Something must be missing...
     */
    @UML(identifier="triangleSource", obligation=OPTIONAL, specification=ISO_19123)
    TinCoverage getTriangleSource();

    /**
     * Returns the Thiessen polygon coverage associated to this surface coverage,
     * or {@code null} if none. The following constraint must hold:
     * <p>
     * <code>{@linkplain #getElements element}.{@linkplain SurfaceValuePair#getGeometry geometry}
     * == polygonSource.controlValue.{@link ValueObject#getGeometry geometry}</code>
     *
     * @return the thiessen polygon coverage.
     *
     * @todo Review the constraints. Something must be missing...
     */
    @UML(identifier="polygonSource", obligation=OPTIONAL, specification=ISO_19123)
    ThiessenPolygonCoverage getPolygonSource();

    /**
     * Returns the set of <var>surface</var>-<var>value</var> pairs included in this coverage.
     */
    @UML(identifier="element", obligation=OPTIONAL, specification=ISO_19123)
    Set<SurfaceValuePair> getElements();

    /**
     * Returns the set of <var>surface</var>-<var>value</var> pairs that include the
     * {@linkplain DomainObject domain objects} containing the specified direct position.
     */
    @UML(identifier="locate", obligation=OPTIONAL, specification=ISO_19123)
    Set<SurfaceValuePair> locate(DirectPosition p);

    /**
     * Returns the dictionary of <var>surface</var>-<var>value</var> pairs that contain the
     * {@linkplain DomainObject objects} in the domain of the coverage each paired with its
     * record of feature attribute values.
     */
    @UML(identifier="list", obligation=MANDATORY, specification=ISO_19123)
    Set<SurfaceValuePair> list();

    /**
     * Returns the nearest <var>curve</var>-<var>value</var> pair from the specified direct
     * position. This is a shortcut for <code>{@linkplain #find(DirectPosition,int) find}(p,1)</code>.
     */
    @UML(identifier="find", obligation=MANDATORY, specification=ISO_19123)
    SurfaceValuePair find(DirectPosition p);
}
