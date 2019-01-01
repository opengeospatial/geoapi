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
package org.opengis.coverage.grid;

import org.opengis.referencing.datum.PixelInCell;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Describes the geometry and georeferencing information of the grid coverage.
 * The {@linkplain #getExtent() extent} attribute determines the valid grid coordinates and allows
 * for calculation of grid size. A grid coverage may or may not have georeferencing.
 *
 * <div class="warning"><b>Warning — this class will change</b><br>
 * Current API is derived from OGC <a href="http://www.opengis.org/docs/01-004.pdf">Grid Coverages Implementation specification 1.0</a>.
 * We plan to replace it by new interfaces derived from ISO 19123 (<cite>Schema for coverage geometry
 * and functions</cite>). Current interfaces should be considered as legacy and are included in this
 * distribution only because they were part of GeoAPI 1.0 release. We will try to preserve as much
 * compatibility as possible, but no migration plan has been determined yet.
 * </div>
 *
 * @version <A HREF="http://www.opengis.org/docs/01-004.pdf">Grid Coverage specification 1.0</A>
 * @author  Martin Desruisseaux (IRD)
 * @since   GeoAPI 1.0
 */
@UML(identifier="CV_GridGeometry", specification=OGC_01004)
public interface GridGeometry {
    /**
     * The valid domain of a grid coverage. The {@linkplain GridEnvelope#getLow() lowest} valid grid
     * coordinate is often (but not always) zero. A grid with 512 cells typically have a minimum
     * coordinate of 0 and maximum of 512, with 511 as the {@linkplain GridEnvelope#getHigh() highest}
     * valid index.
     *
     * <div class="note"><b>Note:</b>
     * the attribute name in the OGC 01-004 specification was "<code>gridRange</code>", while
     * the ISO 19123 specification uses "<code>extent</code>" for similar information. This
     * interface uses the ISO name both for consistency with ISO interfaces, and because the
     * <cite>range</cite> term is already used by ISO 19123 for a different meaning.</div>
     *
     * @return the valid domain of a grid coverage.
     *
     * @see org.opengis.coverage.grid.Grid#getExtent()
     */
    @UML(identifier="gridRange", obligation=MANDATORY, specification=OGC_01004)
    GridEnvelope getExtent();

    /**
     * @deprecated Renamed {@link #getExtent()}.
     */
    @Deprecated
    GridEnvelope getGridRange();

    /**
     * Returns the conversion from grid coordinates to real world earth coordinates.
     * The transform is often an affine transform. The coordinate reference system
     * of the real world coordinates is given by the
     * {@link org.opengis.coverage.Coverage#getCoordinateReferenceSystem()} method
     * and maps to {@linkplain PixelInCell#CELL_CENTER pixel center}.
     *
     * @return the conversion from grid coordinates to
     *         {@linkplain org.opengis.coverage.Coverage#getCoordinateReferenceSystem
     *         real world earth coordinates}.
     *
     * @since GeoAPI 2.1
     */
    @UML(identifier="gridToCoordinateSystem", obligation=MANDATORY, specification=OGC_01004)
    MathTransform getGridToCRS();
}
