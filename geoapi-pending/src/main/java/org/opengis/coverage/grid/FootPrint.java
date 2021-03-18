/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2005-2021 Open Geospatial Consortium, Inc.
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

import org.opengis.geometry.Geometry;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * The presentation of an observed or measured space surrounding a sample point in the context
 * of some external {@linkplain CoordinateReferenceSystem coordinate reference system}.
 *
 * @version ISO 19123:2004
 * @author  Martin Schouwenburg
 * @author  Wim Koolhoven
 * @since   GeoAPI 2.1
 */
@UML(identifier="CV_FootPrint", specification=ISO_19123)
public interface FootPrint {
    /**
     * Returns the geometry that shapes the foot print. In the simplest case this
     * can be a point, but it can also be a disc, sphere or hypersphere.
     *
     * @return the geometry that shapes the foot print.
     */
    @UML(identifier="geometry", obligation=MANDATORY, specification=ISO_19123)
    Geometry getGeometry();

    /**
     * Returns the {@linkplain GridPoint grid point} to which this foot print corresponds.
     *
     * @return the grid point to which this foot print corresponds.
     *
     * @see GridPoint#getFootPrints()
     *
     * @todo his role name in ISO 19123, "center", is a misnomer.  By definition, the sample point
     *       represented in the same external coordinate reference system as the footprint, is the
     *       "center" of the footprint.  This "center" attribute associates the footprint with a
     *       grid point, which represents only the index into the grid.  This implementation
     *       specification, therefore, has renamed the association role to "index".
     */
    @UML(identifier="center", obligation=MANDATORY, specification=ISO_19123)
    GridPoint getCenter();
}
