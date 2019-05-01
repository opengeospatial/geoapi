/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2005-2019 Open Geospatial Consortium, Inc.
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


/**
 * Proposed extension of ISO {@link org.opengis.coverage.grid.Grid}.
 *
 * @issue https://osgeo-org.atlassian.net/projects/GEO/issues/GEO-82
 *
 * @author  Alexander Petkov
 */
public interface Grid extends org.opengis.coverage.grid.Grid {
    /**
     * Specified in ISO 19123 as a "partition" of an inheritance relation,
     * the valuation facility is recast here as a composition association.
     * This increases clarity and eliminates the required multiple inheritance.
     * The valuation association organizes the multi-dimensional grid
     * into a linear sequence of values according to a limited number of specifiable schemes.
     */
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
    GridPositioning getPositioning();
}
