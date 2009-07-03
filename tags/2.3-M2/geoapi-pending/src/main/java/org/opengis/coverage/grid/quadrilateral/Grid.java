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

import org.opengis.annotation.Extension;


/**
 * Proposed extension of ISO {@link org.opengis.coverage.grid.Grid}.
 *
 * @issue http://jira.codehaus.org/browse/GEO-82
 *
 * @author  Alexander Petkov
 */
@Extension
public interface Grid extends org.opengis.coverage.grid.Grid {
    /**
     * Specified in ISO 19123 as a "partition" of an inheritance relation,
     * the valuation facility is recast here as a composition association.
     * This increases clarity and eliminates the required multiple inheritance.
     * The valuation association organizes the multi-dimensional grid
     * into a linear sequence of values according to a limited number of specifiable schemes.
     */
    @Extension
    GridValuesMatrix getValuation();

    /**
     * Specified in ISO 19123 as a "partition" of an inheritance relation,
     * the positioning facility is recast here as a composition association.
     * This increases clarity and eliminates the required multiple inheritance.
     * The positioning association shall link this grid with an object capable of
     * transforming the grid coordinates into a representation in an external coordinate reference system.
     * The associated object may be either a RectifiedGrid or a ReferenceableGrid,
     * but shall not be only a GridPositioning object.
     */
    @Extension
    GridPositioning getPositioning();
}
