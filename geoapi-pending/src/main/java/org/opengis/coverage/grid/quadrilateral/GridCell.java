/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2005-2012 Open Geospatial Consortium, Inc.
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
