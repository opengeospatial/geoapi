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
package org.opengis.coverage.grid.quadrilateral;

import java.util.Set;
import org.opengis.coverage.DomainObject;
import org.opengis.geometry.Geometry;
import org.opengis.temporal.TemporalGeometricPrimitive;


/**
 * Proposed extension of ISO {@link org.opengis.coverage.grid.GridCell}.
 *
 * @author  Alexander Petkov
 */
public interface GridCell extends org.opengis.coverage.grid.GridCell {
    /**
     * This role name is inherited from {@link DomainObject} and associates the grid cell with a
     * geometric object which encodes only the spatial components of the grid index. Spatial axes
     * in the geometric object are specified in the same order as in the grid coordinates.
     * The temporal axis and any categorical axes are omitted.
     *
     * <p>In two spatial dimensions, the geometric object shall be a {@code PolyhedralSurface} composed of
     * a single {@code Polygon}. For three spatial dimensions, the user must specify a
     * {@link org.opengis.geometry.primitive.Solid}
     * object which represents the volume bounded by the eight corners.</p>
     *
     * <p>Because we consider time to be orthogonal to space, these spatial elements may be factored out.
     * The same spatial elements participate at the start time and at the end time.
     * Therefore, they need only be represented once.</p>
     */
    Set<Geometry> getSpatialElements();

    /**
     * This role name is inherited from {@link DomainObject} and associates the grid cell
     * with a {@linkplain org.opengis.temporal.IntervalLength interval} which represents the two
     * {@linkplain org.opengis.temporal.Instant instants}
     * which participate in the Grid Cell. Because there may be only one time axis, there can be
     * only two relevant {@linkplain org.opengis.temporal.Instant instants}.
     * Because we consider time to be orthogonal
     * to space, these temporal elements may be factored out and represented separately from the
     * spatial elements.
     */
    Set<TemporalGeometricPrimitive> getTemporalElements();
}
