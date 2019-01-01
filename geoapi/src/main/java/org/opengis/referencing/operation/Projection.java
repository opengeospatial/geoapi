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
package org.opengis.referencing.operation;

import org.opengis.referencing.crs.GeographicCRS;
import org.opengis.referencing.crs.ProjectedCRS;


/**
 * A {@linkplain org.opengis.referencing.operation.Conversion conversion} transforming
 * (<var>longitude</var>,<var>latitude</var>) coordinates to Cartesian coordinates
 * (<var>x</var>,<var>y</var>). Although some map projections can be represented as a
 * geometric process, in general a map projection is a set of formulae that converts geodetic
 * latitude and longitude to plane (map) coordinates. Height plays no role in this process,
 * which is entirely two-dimensional. The same map projection can be applied to many
 * {@linkplain org.opengis.referencing.crs.GeographicCRS geographic CRSs}, resulting in many
 * {@linkplain org.opengis.referencing.crs.ProjectedCRS projected CRSs} each of which is related
 * to the same {@linkplain org.opengis.referencing.datum.GeodeticDatum geodetic datum} as the
 * geographic CRS on which it was based.
 *
 * <p>An unofficial list of projections and their parameters can
 * be found <a href="http://www.remotesensing.org/geotiff/proj_list/">there</a>.
 * Most projections expect the following parameters:
 *  {@code "semi_major"} (mandatory),
 *  {@code "semi_minor"} (mandatory),
 *  {@code "central_meridian"} (default to 0),
 *  {@code "latitude_of_origin"} (default to 0),
 *  {@code "scale_factor"} (default to 1),
 *  {@code "false_easting"} (default to 0) and
 *  {@code "false_northing"} (default to 0).</p>
 *
 * @departure extension
 *   This interface is not part of the ISO specification. It has been added in GeoAPI at user
 *   request, in order to provide a way to know the kind of map projection.
 *
 * @author  Martin Desruisseaux (IRD)
 * @version 3.0
 * @since   1.0
 *
 * @see org.opengis.referencing.crs.ProjectedCRS
 * @see <a href="http://mathworld.wolfram.com/MapProjection.html">Map projections on MathWorld</a>
 */
public interface Projection extends Conversion {
    /**
     * Returns the source CRS, which must be geographic or {@code null}.
     *
     * @since 3.1
     */
    @Override
    GeographicCRS getSourceCRS();

    /**
     * Returns the target CRS, which must be projected or {@code null}.
     *
     * @since 3.1
     */
    @Override
    ProjectedCRS getTargetCRS();
}
