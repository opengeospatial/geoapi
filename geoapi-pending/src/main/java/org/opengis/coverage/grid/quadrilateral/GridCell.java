/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.coverage.grid.quadrilateral;

import java.util.Set;
import org.opengis.coverage.DomainObject;
import org.opengis.geometry.Geometry;
import org.opengis.temporal.TemporalGeometricPrimitive;
import org.opengis.annotation.Extension;


/**
 * Proposed extension of ISO {@link org.opengis.coverage.grid.GridCell}.
 *
 * @issue http://jira.codehaus.org/browse/GEO-82
 *
 * @author  Alexander Petkov
 */
@Extension
public interface GridCell extends org.opengis.coverage.grid.GridCell {
    /**
     * This role name is inherited from {@link DomainObject} and associates the grid cell with a
     * geometric object which encodes only the spatial components of the grid index. Spatial axes
     * in the geometric object are specified in the same order as in the grid coordinates.
     * The temporal axis and any categorical axes are omitted.
     * <p>
     * In two spatial dimensions, the geometric object shall be a {@link PolyhedralSurface} composed of
     * a single {@link Polygon}. For three spatial dimensions, the user must specify a {@link Solid}
     * object which represents the volume bounded by the eight corners.
     * <p>
     * Because we consider time to be orthogonal to space, these spatial elements may be factored out.
     * The same spatial elements participate at the start time and at the end time.
     * Therefore, they need only be represented once.
     */
    @Extension
    Set<Geometry> getSpatialElements();

    /**
     * This role name is inherited from {@link DomainObject} and associates the grid cell
     * with a {@linkplain Interval interval} which represents the two {@linkplain Instant instants}
     * which participate in the Grid Cell. Because there may be only one time axis, there can be
     * only two relevant {@linkplain Instant instants}. Because we consider time to be orthogonal
     * to space, these temporal elements may be factored out and represented separately from the
     * spatial elements.
     */
    @Extension
    Set<TemporalGeometricPrimitive> getTemporalElements();
}
